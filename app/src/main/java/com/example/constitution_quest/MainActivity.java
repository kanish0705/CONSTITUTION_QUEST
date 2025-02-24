package com.example.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.constitution_quest.R;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView, scoreTextView;
    private RadioGroup radioGroup;
    private RadioButton option1, option2, option3, option4;
    private Button nextButton;

    private int currentQuestionIndex = 0;
    private int score = 0;

    private String[] questions = {
            "What is the supreme law of India?",
            "Who is known as the Father of the Indian Constitution?",
            "Which part of the Indian Constitution deals with Fundamental Rights?"
    };

    private String[][] options = {
            {"The President", "The Constitution", "The Parliament", "The Supreme Court"},
            {"Mahatma Gandhi", "B. R. Ambedkar", "Jawaharlal Nehru", "Sardar Patel"},
            {"Part III", "Part IV", "Part V", "Part VI"}
    };

    private int[] correctAnswers = {1, 1, 0}; // 1 = Constitution, 1 = B.R. Ambedkar, 0 = Part III

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        questionTextView = findViewById(R.id.question_text_view);
        scoreTextView = findViewById(R.id.score_text_view);
        radioGroup = findViewById(R.id.radio_group); // Corrected ID here
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nextButton = findViewById(R.id.next_button);

        // Load the first question
        loadQuestion();

        // Initially disable the "Next" button until an option is selected
        nextButton.setEnabled(false);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> nextButton.setEnabled(true));

        nextButton.setOnClickListener(v -> {
            checkAnswer();
            currentQuestionIndex++;

            if (currentQuestionIndex < questions.length) {
                loadQuestion();
            } else {
                showFinalScore();
            }
        });
    }

    private void loadQuestion() {
        // Set question and options
        questionTextView.setText(questions[currentQuestionIndex]);
        option1.setText(options[currentQuestionIndex][0]);
        option2.setText(options[currentQuestionIndex][1]);
        option3.setText(options[currentQuestionIndex][2]);
        option4.setText(options[currentQuestionIndex][3]);

        // Clear previous selection
        radioGroup.clearCheck();
        nextButton.setEnabled(false); // Disable until a new selection is made
    }

    private void checkAnswer() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) { // Check if user has selected an option
            int selectedAnswer = -1;
            if (selectedId == R.id.option1) selectedAnswer = 0;
            else if (selectedId == R.id.option2) selectedAnswer = 1;
            else if (selectedId == R.id.option3) selectedAnswer = 2;
            else if (selectedId == R.id.option4) selectedAnswer = 3;

            if (selectedAnswer == correctAnswers[currentQuestionIndex]) {
                score++;
                scoreTextView.setText("Score: " + score);
            }
        }
    }

    private void showFinalScore() {
        questionTextView.setText("Quiz Over! Your final score: " + score);
        radioGroup.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);
    }
}
