package com.fundamentals.roomwordssample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private List<Word> mWords;

    WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if (mWords != null) {
            String word = mWords.get(position).getWord();
            holder.bind(word);
        } else {
            // SOS: Covers the case where setWords has not been called yet
            holder.bind("No Word");
        }
    }

    void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    Word getWordAtPosition(int position) {
        return mWords.get(position);
    }

    // SOS: getItemCount is called many times, and when it is first called, mWords will be null
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

    class WordViewHolder extends RecyclerView.ViewHolder {

        private final TextView mWordView;

        private WordViewHolder(View view) {
            super(view);
            mWordView = view.findViewById(R.id.textView);
        }

        void bind(String word) {
            mWordView.setText(word);
        }
    }
}
