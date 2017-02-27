package com.mihakot.geoquiz;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mButtonTrue;
    private Button mButtonFalse;

    private ImageButton mButtonNext;
    private ImageButton mButtonPrev;

    private TextView mQuestionText;

    private static final String TAG = "QuizActivity";

    private static final String KEY_INDEX = "index";

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_1, true),
            new Question(R.string.question_2, true),
            new Question(R.string.question_3, false),
            new Question(R.string.question_4, false),
            new Question(R.string.question_5, false),
            new Question(R.string.question_6, true),
            new Question(R.string.question_7, false),
            new Question(R.string.question_8, true),
    };

    private int mCurrentIndex = 0;

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionText.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if (userPressedTrue == answerIsTrue){
            messageResId=R.string.toast_correct;
        }else {
            messageResId=R.string.toast_incorrect;
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate(bundle) called");
        setContentView(R.layout.activity_quiz);

        mQuestionText = (TextView) findViewById(R.id.question_text);

        mButtonFalse = (Button) findViewById(R.id.button_false);
        mButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        mButtonTrue = (Button) findViewById(R.id.button_true);
        mButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mButtonNext = (ImageButton) findViewById(R.id.button_next);
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;

                updateQuestion();
            }
        });
        mButtonPrev = (ImageButton) findViewById(R.id.button_prev);
        mButtonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1) > 0 ? (mCurrentIndex - 1) : 0;
                updateQuestion();
            }
        });
        if(savedInstanceState!=null){
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
        }
        updateQuestion();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstancState){
        super.onSaveInstanceState(savedInstancState);
        Log.i(TAG,"onSaveInstanceState()");
        savedInstancState.putInt(KEY_INDEX,mCurrentIndex);
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }
}
