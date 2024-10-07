package com.example.labonecalculator;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result, solution;

    MaterialButton buttonC, buttonOpenBracket, buttonCloseBracket;
    MaterialButton buttonZero, buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine;
    MaterialButton buttonPlus, buttonSubtract, buttonMultiply, buttonDivide, buttonEquals;
    MaterialButton buttonAC, buttonDecimal;

    /**
     *
     * @param savedInstanceState creates the onCreate method in order
     * to create all of the buttons visible on the screen of the
     * calculator app
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        result = findViewById(R.id.results);

        solution = findViewById(R.id.solutions);

        assignID(buttonC,R.id.buttonC);

        assignID(buttonOpenBracket,R.id.buttonOpenBracket);

        assignID(buttonCloseBracket,R.id.buttonCloseBracket);

        assignID(buttonZero,R.id.buttonZero);

        assignID(buttonOne,R.id.buttonOne);

        assignID(buttonTwo,R.id.buttonTwo);

        assignID(buttonThree,R.id.buttonThree);

        assignID(buttonFour,R.id.buttonFour);

        assignID(buttonFive,R.id.buttonFive);

        assignID(buttonSix,R.id.buttonSix);

        assignID(buttonSeven,R.id.buttonSeven);

        assignID(buttonEight,R.id.buttonEight);

        assignID(buttonNine,R.id.buttonNine);

        assignID(buttonPlus,R.id.buttonPlus);

        assignID(buttonSubtract,R.id.buttonSubtract);

        assignID(buttonMultiply,R.id.buttonMultiply);

        assignID(buttonDivide,R.id.buttonDivide);

        assignID(buttonAC,R.id.buttonAC);

        assignID(buttonDecimal,R.id.buttonDecimal);

    }

    /**
     *
     * @param button Creates a button in order to display the button and cause functionality
     * @param id Creates the id variable of type int, assigning the id of the buttons to
     * the button variable
     */
    void assignID(MaterialButton button, int id) {

        button = findViewById(id);
        button.setOnClickListener(this);

    }

    /**
     *
     * @param view Creates an on click listener that listens to the functions the buttons perform
     * on the calculator
     */
    @Override
    public void onClick(View view) {

        MaterialButton myButton = (MaterialButton) view;

        String buttonText = myButton.getText().toString();

        String finalCalculation = solution.getText().toString();

        if (buttonText.equals("AC")) {

            solution.setText("");

            result.setText("0");

            return;

        }

        if (buttonText.equals("=")) {

            solution.setText(result.getText());

            return;

        }

        if (buttonText.equals("C")) {

            finalCalculation = finalCalculation.substring(0, finalCalculation.length() - 1);

        } else {

            finalCalculation = finalCalculation + buttonText;

        }

        solution.setText(finalCalculation);

        String finalResut = getResults(finalCalculation);

        if (!finalResut.equals("Error!")) {

            result.setText(finalResut);

        }
    }

    /**
     *
     * @param data Creates the getResults method in order to add functionality
     * to the numbers on the calculator app and perform operations
     * @return
     */
    String getResults(String data) {

        try {

            Context context = Context.enter();

            context.setOptimizationLevel(-1);

            Scriptable scriptable = context.initSafeStandardObjects();

            return context.evaluateString(scriptable, data, "Javascript", 1, null).toString();



        } catch (Exception e) {

            return "Error!";

        }
    }
}