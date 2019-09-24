package com.example.appmv.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appmv.Model.Word
import com.example.appmv.R
import kotlinx.android.synthetic.main.list_item.view.*

class WordListAdapter(private val mContext: Context) :
    ListAdapter<Word, WordListAdapter.WordViewHolder>(object: DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean = oldItem == newItem

    }) {

    private lateinit var mWords: List<Word>

    fun setWords(words: List<Word>) {
        mWords = words
        notifyDataSetChanged()
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView = itemView.findViewById(R.id.textView)
        var descrTextView: TextView = itemView.findViewById(R.id.descrText)
        var duedateTextView: TextView = itemView.findViewById(R.id.dateView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false)
        return WordViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mWords.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        var current = mWords[position]
        holder.titleTextView.text = current.name
        holder.descrTextView.text = current.descr
        holder.duedateTextView.text = current.date
    }


}