package com.fundamentals.roomwordssample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

class WordRepository {

    private final WordDao mWordDao;
    private final LiveData<List<Word>> mAllWords;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    void insert(Word word) {
        new InsertAsyncTask(mWordDao).execute(word);
    }

    void deleteAll() {
        new DeleteAllWordsAsyncTask(mWordDao).execute();
    }

    void deleteWord(Word word) {
        new DeleteWordAsyncTask(mWordDao).execute(word);
    }

    // here start the AsyncTasks
    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {

        private final WordDao mWordDao;

        InsertAsyncTask(WordDao dao) {
            mWordDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mWordDao.insert(params[0]);
            return null;
        }
    }

    private static class DeleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {

        private final WordDao mWordDao;

        DeleteAllWordsAsyncTask(WordDao dao) {
            mWordDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mWordDao.deleteAll();
            return null;
        }
    }

    private static class DeleteWordAsyncTask extends AsyncTask<Word, Void, Void> {

        private final WordDao mWordDao;

        DeleteWordAsyncTask(WordDao dao) {
            mWordDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mWordDao.deleteWord(params[0]);
            return null;
        }
    }
}
