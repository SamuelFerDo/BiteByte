package com.example.bitebyte.ui.question.questionone

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class QuestionOneViewModel(application: Application): AndroidViewModel(application) {
    val age = MutableLiveData<String>()
    val weight = MutableLiveData<String>()
    val height = MutableLiveData<String>()
}