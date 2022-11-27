package com.example.doctorapp

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.INPUT_SERVICE
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.doctorapp.databinding.FragmentEditorBinding

class EditorFragment : Fragment() {

    private lateinit var viewModel: EditorViewModel
    private val args:EditorFragmentArgs by navArgs()
    private lateinit var binding:FragmentEditorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.let{
            it.setHomeButtonEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_check_circle)
        }
        setHasOptionsMenu(true)

        requireActivity().title =
            if (args.noteId == NEW_NOTE_ID){
                getString(R.string.new_note)
            }else{
                getString(R.string.edit_note)
            }

        viewModel = ViewModelProvider(this).get(EditorViewModel::class.java)

        binding = FragmentEditorBinding.inflate(inflater, container, false)
        binding.editor.setText("")
        //binding.editor.setText("You selected note number " + args.noteId)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object: OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    saveAndReturn()
                }
            }
        )

        viewModel.currentNote.observe(viewLifecycleOwner, Observer {
            binding.editor.setText(it.text)
        })
        viewModel.getNoteById(args.noteId)

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> saveAndReturn()
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("ServiceCast")
    private fun saveAndReturn(): Boolean {

        //close the soft keyboard when returned to the previous screen
        val imm = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)

        viewModel.currentNote.value?.text = binding.editor.text.toString()
        viewModel.updateNote()

        findNavController().navigateUp()
        return true
    }

    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditorViewModel::class.java)
    }*/

}