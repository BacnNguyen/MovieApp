package gst.trainingcourse.movie.ui.fragment.search

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.data.model.SearchResponse
import gst.trainingcourse.movie.databinding.FragmentSearchBinding
import gst.trainingcourse.movie.helper.LinearVerticalItemDecoration
import gst.trainingcourse.movie.ui.fragment.base.BaseFragment
import gst.trainingcourse.movie.ui.fragment.search.adapter.SearchAdapter
import gst.trainingcourse.movie.utils.setOnSingleTouch
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val viewModel by viewModels<SearchViewModel>()

    private val autoCompleteAdapter by lazy {
        ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line
        )
    }

    private val speechRecognizer: SpeechRecognizer by lazy {
        SpeechRecognizer.createSpeechRecognizer(requireContext())
    }

    private val speechRecognizerIntent: Intent by lazy {
        Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        ).putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
    }
    private val searchAdapter by lazy { SearchAdapter(this::onSearchResultClick) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAutoCompleteTextView()
        setupRecyclerViewSearchResult()
        setupMicListener()
        observeData()
    }

    private fun setupMicListener() {
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
            }

            override fun onBeginningOfSpeech() {
                binding.autoCompleteTextView.hint = "Listening..."
            }

            override fun onRmsChanged(rmsdB: Float) {
            }

            override fun onBufferReceived(buffer: ByteArray?) {
            }

            override fun onEndOfSpeech() {
            }

            override fun onError(error: Int) {
                binding.imageMic.setImageResource(R.drawable.ic_mic)
                speechRecognizer.stopListening()
                binding.autoCompleteTextView.hint = ""
            }

            override fun onResults(results: Bundle?) {
                binding.imageMic.setImageResource(R.drawable.ic_mic)
                results?.let { result ->
                    val data = result.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    data?.let {
                        binding.autoCompleteTextView.setText(it[0].toString())
                        viewModel.search(data[0].toString())
                        binding.autoCompleteTextView.hint = ""
                    }
                }
            }

            override fun onPartialResults(partialResults: Bundle?) {
            }

            override fun onEvent(eventType: Int, params: Bundle?) {
            }

        })

        binding.imageMic.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                speechRecognizer.stopListening()
            }
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                when {
                    ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.RECORD_AUDIO
                    ) == PackageManager.PERMISSION_GRANTED -> {
                        startRecording()
                    }
                    shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO) -> {
                        showReason()
                    }
                    else -> {
                        requestPermissionRecord()
                    }
                }
            }
            false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()
    }

    private fun showReason() {
        AlertDialog.Builder(requireContext()).setTitle("Permission denied!!!")
            .setMessage("You should accept this permission for searching with speech")
            .setNegativeButton(
                R.string.no
            ) { _, _ ->
                Toast.makeText(
                    requireContext(),
                    "You can not access to use this!!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setPositiveButton(R.string.yes) { _, _ -> requestPermissionRecord() }
            .show()
    }

    private fun requestPermissionRecord() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.RECORD_AUDIO),
            CODE_RECORD
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            CODE_RECORD -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startRecording()
                } else {
                    showReason()
                }
            }
        }
    }

    private fun startRecording() {
        binding.imageMic.setImageResource(R.drawable.ic_mic)
        speechRecognizer.startListening(speechRecognizerIntent)
    }

    private fun observeData() {
        viewModel.history.observe(viewLifecycleOwner) { history ->
            autoCompleteAdapter.apply {
                clear()
                addAll(history.map { it.content })
            }
        }

        viewModel.searchResponse.observe(viewLifecycleOwner) {
            if (it != null) {
                searchAdapter.submitList(it.results)
            }
        }
    }

    private fun setupAutoCompleteTextView() {
        binding.autoCompleteTextView.apply {
            setAdapter(autoCompleteAdapter)

            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val inputMethodManager =
                        activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    inputMethodManager?.hideSoftInputFromWindow(applicationWindowToken, 0)
                    this.clearFocus()
                    viewModel.search(this.text.toString())
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
        }
    }

    private fun setupRecyclerViewSearchResult() {
        binding.recyclerView.apply {
            adapter = searchAdapter
            addItemDecoration(LinearVerticalItemDecoration(resources.getDimensionPixelOffset(R.dimen.dp_8)))
        }
    }

    private fun onSearchResultClick(item: SearchResponse.Result, position: Int) {
        when (item.mediaType) {
            "person" -> Toast.makeText(
                requireContext(),
                "This function not supported",
                Toast.LENGTH_SHORT
            ).show()

            "tv" -> {
                val action = SearchFragmentDirections.actionSearchToTvShowDetail(item.id)
                findNavController().navigate(action)
            }

            else -> {
                val action = SearchFragmentDirections.actionSearchToMovieDetail(item.id)
                findNavController().navigate(action)
            }
        }
    }

    companion object {
        const val TAG = "SearchFragment"
        const val CODE_RECORD = 1234
        fun newInstance() = SearchFragment()
    }
}