package com.example.doctorapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.doctorapp.data.NoteEntity
import com.example.doctorapp.data.SampleDataProvider

class MainViewModel : ViewModel() {
    val notesList = MutableLiveData<List<NoteEntity>>()

    init {
        notesList.value = SampleDataProvider.getNotes()
    }
}