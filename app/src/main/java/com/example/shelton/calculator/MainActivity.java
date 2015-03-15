package com.example.shelton.calculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button[] numbers = new Button[10];
    Button[] operators = new Button[6];
    Button[] edits = new Button[4];
    TextView input, equation;

    StringBuffer inBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inBuffer = new StringBuffer();

        input = (TextView) findViewById(R.id.num);
        equation = (TextView) findViewById(R.id.equation);
        input.setText("");
        equation.setText("");

        numbers[0] = (Button) findViewById(R.id.b0);
        numbers[1] = (Button) findViewById(R.id.b1);
        numbers[2] = (Button) findViewById(R.id.b2);
        numbers[3] = (Button) findViewById(R.id.b3);
        numbers[4] = (Button) findViewById(R.id.b4);
        numbers[5] = (Button) findViewById(R.id.b5);
        numbers[6] = (Button) findViewById(R.id.b6);
        numbers[7] = (Button) findViewById(R.id.b7);
        numbers[8] = (Button) findViewById(R.id.b8);
        numbers[9] = (Button) findViewById(R.id.b9);

        operators[0] = (Button) findViewById(R.id.b_equals);
        operators[1] = (Button) findViewById(R.id.b_plus);
        operators[2] = (Button) findViewById(R.id.b_minus);
        operators[3] = (Button) findViewById(R.id.b_times);
        operators[4] = (Button) findViewById(R.id.b_divide);
        operators[5] = (Button) findViewById(R.id.b_mod);

        edits[0] = (Button) findViewById(R.id.b_sign);
        edits[1] = (Button) findViewById(R.id.b_point);
        edits[2] = (Button) findViewById(R.id.b_clear);
        edits[3] = (Button) findViewById(R.id.b_backspace);

        for(Button b : numbers) b.setOnClickListener(this);
        for(Button b : operators) b.setOnClickListener(this);
        for(Button b : edits) b.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.b0:
                input.setText(input.getText() + "0");
                break;
            case R.id.b1:
                input.setText(input.getText() + "1");
                break;
            case R.id.b2:
                input.setText(input.getText() + "2");
                break;
            case R.id.b3:
                input.setText(input.getText() + "3");
                break;
            case R.id.b4:
                input.setText(input.getText() + "4");
                break;
            case R.id.b5:
                input.setText(input.getText() + "5");
                break;
            case R.id.b6:
                input.setText(input.getText() + "6");
                break;
            case R.id.b7:
                input.setText(input.getText() + "7");
                break;
            case R.id.b8:
                input.setText(input.getText() + "8");
                break;
            case R.id.b9:
                input.setText(input.getText() + "9");
                break;
            case R.id.b_point:
                input.setText(input.getText() + ".");
                break;
            case R.id.b_plus:
                equation.setText("" + equation.getText() + input.getText() + " + ");
                inBuffer.append("+");
                input.setText("");
                break;
            case R.id.b_minus:
                equation.setText("" + equation.getText() + input.getText() + " - ");
                inBuffer.append("-");
                input.setText("");
                break;
            case R.id.b_times:
                equation.setText("" + equation.getText() + input.getText() + " × ");
                inBuffer.append("*");
                input.setText("");
                break;
            case R.id.b_divide:
                equation.setText("" + equation.getText() + input.getText() + " ÷ ");
                inBuffer.append("/");
                input.setText("");
                break;
            case R.id.b_mod:
                equation.setText("" + equation.getText() + input.getText() + " % ");
                inBuffer.append("%");
                input.setText("");
                break;
            case R.id.b_clear:
                input.setText("");
                equation.setText("");
                break;
            case R.id.b_backspace:
                input.setText(input.getText().subSequence(0, input.getText().length() - 1));
                break;
            case R.id.b_sign:
                if(input.getText().charAt(0) != '-') {
                    input.setText("-" + input.getText());
                } else {
                    input.setText(input.getText().subSequence(1, input.getText().length()));
                }
                break;
            case R.id.b_equals:
                equation.setText("" + equation.getText() + input.getText());
                input.setText("Currently no expression evaluator =(");
                break;
        }
    }
}
