package com.example.appmv.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.appmv.Model.Word
import com.example.appmv.Model.WordRepository

class WordViewModel(application: Application): AndroidViewModel(application) {
    private val wordRepository:WordRepository
    private val allWords: LiveData<List<Word>>

    init {
        wordRepository = WordRepository(application)
        allWords = wordRepository.getAllWords()
    }

    fun insertWord(word: Word) {
        wordRepository.insertWord(word)
    }

    fun deleteWord(word: Word){
        wordRepository.deleteWord(word)
    }

    fun deleteAllWords(){
        wordRepository.deleteAllWords()
    }

    fun getAllWords(): LiveData<List<Word>>{
        return allWords
    }

    fun getWordByName(name:String): Word? {
        return wordRepository.getWordByName(name)
    }
}