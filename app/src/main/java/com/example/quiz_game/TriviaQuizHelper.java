package com.example.quiz_game;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


class TriviaQuizHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "TQuiz.db";

    //If you want to add more questions or wanna update table values
    //or any kind of modification in db just increment version no.
    private static final int DB_VERSION = 3;
    //Table name
    private static final String TABLE_NAME = "TQ";
    //Id of question
    private static final String UID = "_UID";
    //Question
    private static final String QUESTION = "QUESTION";
    //Option A
    private static final String OPTA = "OPTA";
    //Option B
    private static final String OPTB = "OPTB";
    //Option C
    private static final String OPTC = "OPTC";
    //Option D
    private static final String OPTD = "OPTD";
    //Answer
    private static final String ANSWER = "ANSWER";
    //So basically we are now creating table with first column-id , sec column-question , third column -option A, fourth column -option B , Fifth column -option C , sixth column -option D , seventh column - answer(i.e ans of  question)
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + OPTD + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    //Drop table query
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    TriviaQuizHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //OnCreate is called only once
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //OnUpgrade is called when ever we upgrade or increment our database version no
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    void allQuestion() {
        ArrayList<TriviaQuestion> arraylist = new ArrayList<>();


        arraylist.add(new TriviaQuestion("What is the name of the process that sends one qubit of information using two bits of classical information?", "Super Dense Coding", "Quantum Entanglement", "Quantum Programming", "Quantum Teleportation","Quantum Teleportation"));

        arraylist.add(new TriviaQuestion("The teapot often seen in many 3D modeling applications is called what?", "Pixar Teapot", "3D Teapot", "Utah Teapot","Tennessee Teapot" ,"Utah Teapot"));

        arraylist.add(new TriviaQuestion("This mobile OS held the largest market share in 2012.", "Android", "BlackBerry", "Symbian", "iOS","iOS"));

        /*
        arraylist.add(new TriviaQuestion("If you were to code software in this language you'd only be able to type 0's and 1's.", "JavaScript", "C++", "Python", "Binary","Binary"));

        arraylist.add(new TriviaQuestion("What does the acronym HTML stand for?", "Hyper Text Markup Language", "Highly Technical Machine Learning", "Home Tool Markup Language", "Hyperlink and Text Management Language", "Hyper Text Markup Language"));

        arraylist.add(new TriviaQuestion("In programming, what does 'GUI' stand for?", "Graphical User Interface", "General User Instruction", "Gaming Under Influence", "Global User Input", "Graphical User Interface"));

        arraylist.add(new TriviaQuestion("Which programming language is often used for artificial intelligence and machine learning?", "Java", "C#", "Python", "Ruby", "Python"));

        arraylist.add(new TriviaQuestion("What is the primary purpose of SQL in the context of databases?", "Styling and Querying Language", "Sequential Query Language", "Structured Query Language", "Simple Question Language", "Structured Query Language"));

        arraylist.add(new TriviaQuestion("Which of the following is a fundamental data structure in computer science?", "Array", "List", "Queue", "All of the above", "All of the above"));

        arraylist.add(new TriviaQuestion("What is the concept of encapsulation in object-oriented programming?", "Binding data and methods together", "Inheriting properties from a superclass", "Hiding the implementation details", "Implementing multiple interfaces", "Hiding the implementation details"));

        arraylist.add(new TriviaQuestion("What is the purpose of the 'break' statement in programming?", "Terminate the program", "Exit the loop or switch statement", "Create a line break in the output", "Pause the execution temporarily", "Exit the loop or switch statement"));

        arraylist.add(new TriviaQuestion("What is the role of a compiler in software development?", "Execute the program", "Translate source code into machine code", "Debug the program", "Manage memory allocation", "Translate source code into machine code"));

        arraylist.add(new TriviaQuestion("What does the acronym API stand for in the context of software development?", "Application Programming Interface", "Advanced Programming Instruction", "Automated Program Integration", "Application Process Interface", "Application Programming Interface"));

        arraylist.add(new TriviaQuestion("Which sorting algorithm has an average-case time complexity of O(n log n)?", "Bubble Sort", "Insertion Sort", "QuickSort", "Selection Sort", "QuickSort"));

        arraylist.add(new TriviaQuestion("Which data structure does FILO apply to?", "Queue", "Heap", "Tree", "Stack","Stack"));

        arraylist.add(new TriviaQuestion("Which kind of algorithm is Ron Rivest not famous for creating?", "Hashing algorithm", "Asymmetric encryption", "Stream cipher", "Secret sharing scheme","Secret sharing scheme"));

        arraylist.add(new TriviaQuestion("What was the first company to use the term 'Golden Master'?", "Apple", "Microsoft", "Google", "IBM","Apple"));

        arraylist.add(new TriviaQuestion("Which computer language would you associate Django framework with?", "C#", "Python", "Java", "C++","Python"));

        arraylist.add(new TriviaQuestion("What five-letter word is the motto of the IBM Computer company?", "Click", "Logic", "Pixel", "Think","Think"));


        arraylist.add(new TriviaQuestion("Which operating system was released first?", "Windows", "Linux", "OS/2", "Mac OS","Mac OS"));
         */
        this.addAllQuestions(arraylist);

    }


    private void addAllQuestions(ArrayList<TriviaQuestion> allQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (TriviaQuestion question : allQuestions) {
                values.put(QUESTION, question.getQuestion());
                values.put(OPTA, question.getOptA());
                values.put(OPTB, question.getOptB());
                values.put(OPTC, question.getOptC());
                values.put(OPTD, question.getOptD());
                values.put(ANSWER, question.getAnswer());
                db.insert(TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }


    List<TriviaQuestion> getAllOfTheQuestions() {

        List<TriviaQuestion> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String coloumn[] = {UID, QUESTION, OPTA, OPTB, OPTC, OPTD, ANSWER};
        Cursor cursor = db.query(TABLE_NAME, coloumn, null, null, null, null, null);


        while (cursor.moveToNext()) {
            TriviaQuestion question = new TriviaQuestion();
            question.setId(cursor.getInt(0));
            question.setQuestion(cursor.getString(1));
            question.setOptA(cursor.getString(2));
            question.setOptB(cursor.getString(3));
            question.setOptC(cursor.getString(4));
            question.setOptD(cursor.getString(5));
            question.setAnswer(cursor.getString(6));
            questionsList.add(question);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionsList;
    }
}
