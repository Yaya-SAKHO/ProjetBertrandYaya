package com.example.ecoledesloustics;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ecoledesloustics.QuizContract.*;

import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EcoleLoustique.db";
    private static final int DATBASE_VERSION = 4;

    private SQLiteDatabase db;


    QuizDbHelper(Context context) {
        super(context, DATABASE_NAME,null, DATBASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionTable.COLUMN_CATEGORY + " TEXT, " +
                QuestionTable.COLUMN_LEVELS_ID + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);

    }


    private void fillQuestionsTable()
    {

        Questions q1 = new Questions("Mais, ou, et, donc, or, ni, car sont des conjonctions de...","Coordination","Subordination","Subjectivité","Conjugaison",1,Questions.CATEGORY_COMPUTERS,Questions.LEVEL1);
        addQuestions(q1);


        Questions q2 = new Questions("Dans quel état d'Asie se trouve la ville de Darjeeling ","Chine","Japon","Inde","Indonésie",3,Questions.CATEGORY_COMPUTERS,Questions.LEVEL2);
        addQuestions(q2);


        Questions q3 = new Questions("D'après la chanson, quel vêtement le bon roi Dagobert a-t-il mis à l'envers ?","sa couronne","son gilet","Sa culotte","Se chaussures",3,Questions.CATEGORY_COMPUTERS,Questions.LEVEL3);
        addQuestions(q3);



        Questions q7 = new Questions(" 2 * 3 ","5","Drivers","2","6",4,Questions.CATEGORY_MATHS,Questions.LEVEL2);
        addQuestions(q7);


        Questions q8 = new Questions("4 * 4","16","8","-2","30",1,Questions.CATEGORY_MATHS,Questions.LEVEL1);
        addQuestions(q8);

        Questions q9 = new Questions(" 4 - 4  ","2 ","1","0","3",4,Questions.CATEGORY_MATHS,Questions.LEVEL2);
        addQuestions(q9);


        Questions q10 = new Questions("4/2","1","2","3","12",2,Questions.CATEGORY_MATHS,Questions.LEVEL1);
        addQuestions(q10);



    }

    private void addQuestions(Questions question){

        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4,question.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER_NR,question.getAnswerNr());
        cv.put(QuestionTable.COLUMN_CATEGORY,question.getCategory());
        cv.put(QuestionTable.COLUMN_LEVELS_ID,question.getLevels());
        db.insert(QuestionTable.TABLE_NAME,null,cv);

    }



    @SuppressLint("Range")
    public ArrayList<Questions> getAllQuestionsWithCategoryAndLevels(int levelsID, String category) {

        ArrayList<Questions> questionList = new ArrayList<>();
        db = getReadableDatabase();



        String Projection[] = {

                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR,
                QuestionTable.COLUMN_CATEGORY,
                QuestionTable.COLUMN_LEVELS_ID
        };

        String selection = QuestionTable.COLUMN_LEVELS_ID + " = ? " +
                " AND " + QuestionTable.COLUMN_CATEGORY + " = ? ";


        String selectionArgs[] = {String.valueOf(levelsID),category} ;

        Cursor c = db.query(QuestionTable.TABLE_NAME,
                Projection,
                selection,
                selectionArgs,
                null,
                null,
                null);


        if (c.moveToFirst()) {
            do {

                Questions question = new Questions();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setCategory(c.getString(c.getColumnIndex(QuestionTable.COLUMN_CATEGORY)));
                question.setCategory(c.getString(c.getColumnIndex(QuestionTable.COLUMN_LEVELS_ID)));

                questionList.add(question);

            } while (c.moveToNext());

        }
        c.close();
        return questionList;

    }




    @SuppressLint("Range")
    public ArrayList<Questions> getAllQuestions() {

        ArrayList<Questions> questionList = new ArrayList<>();
        db = getReadableDatabase();



        String Projection[] = {

                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR
        };



        Cursor c = db.query(QuestionTable.TABLE_NAME,
                Projection,
                null,
                null,
                null,
                null,
                null);


        if (c.moveToFirst()) {
            do {

                Questions question = new Questions();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));

                questionList.add(question);

            } while (c.moveToNext());

        }
        c.close();
        return questionList;

    }


    @SuppressLint("Range")
    public ArrayList<Questions> getAllQuestionsWithCategory(String category) {

        ArrayList<Questions> questionList = new ArrayList<>();
        db = getReadableDatabase();



        String Projection[] = {

                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR,
                QuestionTable.COLUMN_CATEGORY
        };

        String selection = QuestionTable.COLUMN_CATEGORY + " = ? ";
        String selectionArgs[] = {category} ;

        Cursor c = db.query(QuestionTable.TABLE_NAME,
                Projection,
                selection,
                selectionArgs,
                null,
                null,
                null);


        if (c.moveToFirst()) {
            do {

                Questions question = new Questions();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setCategory(c.getString(c.getColumnIndex(QuestionTable.COLUMN_CATEGORY)));

                questionList.add(question);

            } while (c.moveToNext());

        }
        c.close();
        return questionList;

    }

}


