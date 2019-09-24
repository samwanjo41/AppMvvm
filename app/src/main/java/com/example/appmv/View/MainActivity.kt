package com.example.appmv.View

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmv.Adapter.WordListAdapter
import com.example.appmv.Model.Word
import com.example.appmv.R
import com.example.appmv.Utils.*
import com.example.appmv.ViewModel.WordViewModel

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), WordListAdapter.ItemClickListener{
    override fun onItemClick(view: View, position: Int) {
        val intent = Intent(this, NewNoteActivity::class.java)
        intent.putExtra(EXTRA_KEY_TITLE, mAdapter.getWords()[position].name)
        intent.putExtra(EXTRA_KEY_DESCR, mAdapter.getWords()[position].descr)
        intent.putExtra(EXTRA_KEY_DATE, mAdapter.getWords()[position].date)
        startActivityForResult(intent, NEW_NOTE_ACIVITY_REQUEST_CODE)
    }

    private lateinit var mrecyclerView: RecyclerView
    private lateinit var mAdapter: WordListAdapter
    private var mWords: List<Word> = mutableListOf<Word>()

    private lateinit var mWordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mrecyclerView = findViewById(R.id.recyclerView)
        mAdapter = WordListAdapter(this, this)
        mAdapter.setWords(mWords)
        mrecyclerView.adapter = mAdapter
        mrecyclerView.layoutManager = LinearLayoutManager(this)

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        mWordViewModel.getAllWords().observe(this, Observer { words ->
            words?.let {
                mAdapter.setWords(it)
            }
        })


        fab.setOnClickListener { view ->
            val intent = Intent(this, NewNoteActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACIVITY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == NEW_NOTE_ACIVITY_REQUEST_CODE && resultCode == RESULT_SAVE){
            data?.let {
                val word = Word(it.getStringExtra(EXTRA_KEY_TITLE), it.getStringExtra(
                    EXTRA_KEY_DESCR), it.getStringExtra(EXTRA_KEY_DATE))
                mWordViewModel.insertWord(word)
            }
        } else if(requestCode == NEW_NOTE_ACIVITY_REQUEST_CODE && resultCode == RESULT_DELETE){
            data?.let {
                val word = mWordViewModel.getWordByName(it.getStringExtra(EXTRA_KEY_TITLE))
                mWordViewModel.deleteWord(word!!)
            }
        }

        else  {
            Toast.makeText(this, "Note not  saved", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_clear_list -> {
                clearListAction()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun clearListAction() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.app_name))
        builder.setMessage(getString(R.string.msg_clear_list))

        builder.setPositiveButton("OK"){
            dialog, _ ->
            mWordViewModel.deleteAllWords()
            Toast.makeText(this, getString(R.string.clear_the_list), Toast.LENGTH_SHORT).show()

        }

        builder.setNegativeButton("NO"){
                dialog, _ ->
            dialog.dismiss()

        }
        val dialog = builder.create()
        dialog.show()
    }
}
