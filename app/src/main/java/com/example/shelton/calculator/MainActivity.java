package com.example.shelton.calculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import evaluator.Evaluator;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button[] numbers = new Button[10];
    Button[] operators = new Button[6];
    Button[] edits = new Button[4];
    TextView input, equation;

    boolean complete = false, error = false, numIn = false;
    Evaluator evaluator;
    StringBuffer inBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        evaluator = new Evaluator();
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

        if(complete) {
            inBuffer.delete(0, inBuffer.length());
            equation.setText("");

            if((v.getId() == R.id.b_plus || v.getId() == R.id.b_minus || v.getId() == R.id.b_times ||
                    v.getId() == R.id.b_divide || v.getId() == R.id.b_mod) && !error) {
                inBuffer.append(input.getText());
                equation.setText(input.getText());
                numIn = true;
            }

            input.setText("");

            error = false;
            complete = false;
        }

        switch(v.getId()) {
            case R.id.b0:
                if(input.length() >= 1 && input.getText().toString().charAt(0) == '0'){}
                else input.setText(input.getText() + "0");
                numIn = true;
                break;
            case R.id.b1:
                if(input.length() == 1 && input.getText().toString().charAt(0) == '0')
                    input.setText("1");
                else input.setText(input.getText() + "1");
                numIn = true;
                break;
            case R.id.b2:
                if(input.length() == 1 && input.getText().toString().charAt(0) == '0')
                    input.setText("2");
                else input.setText(input.getText() + "2");
                numIn = true;
                break;
            case R.id.b3:
                if(input.length() == 1 && input.getText().toString().charAt(0) == '0')
                    input.setText("3");
                else input.setText(input.getText() + "3");
                numIn = true;
                break;
            case R.id.b4:
                if(input.length() == 1 && input.getText().toString().charAt(0) == '0')
                    input.setText("4");
                else input.setText(input.getText() + "4");
                numIn = true;
                break;
            case R.id.b5:
                if(input.length() == 1 && input.getText().toString().charAt(0) == '0')
                    input.setText("5");
                else input.setText(input.getText() + "5");
                numIn = true;
                break;
            case R.id.b6:
                if(input.length() == 1 && input.getText().toString().charAt(0) == '0')
                    input.setText("6");
                else input.setText(input.getText() + "6");
                numIn = true;
                break;
            case R.id.b7:
                if(input.length() == 1 && input.getText().toString().charAt(0) == '0')
                    input.setText("7");
                else input.setText(input.getText() + "7");
                numIn = true;
                break;
            case R.id.b8:
                if(input.length() == 1 && input.getText().toString().charAt(0) == '0')
                    input.setText("8");
                else input.setText(input.getText() + "8");
                numIn = true;
                break;
            case R.id.b9:
                if(input.length() == 1 && input.getText().toString().charAt(0) == '0')
                    input.setText("9");
                else input.setText(input.getText() + "9");
                numIn = true;
                break;
            case R.id.b_point:
                boolean hasPoint = false;
                for(char c : input.getText().toString().toCharArray()) {
                    if(c == '.') {
                        hasPoint = true;
                        break;
                    }
                }
                if(!hasPoint) input.setText(input.getText() + ".");
                break;
            case R.id.b_plus:
                if(numIn) {
                    equation.setText("" + equation.getText() + input.getText() + " + ");
                    inBuffer.append(input.getText() + "+");
                    input.setText("");
                    numIn = false;
                } else if(equation.length() > 0) {
                    equation.setText(equation.getText().subSequence(0, (equation.getText().length() - 3)) + " + ");
                    inBuffer.deleteCharAt(inBuffer.length() - 1);
                    inBuffer.append("+");
                }
                break;
            case R.id.b_minus:
                if(numIn) {
                    equation.setText("" + equation.getText() + input.getText() + " - ");
                    inBuffer.append(input.getText() + "-");
                    input.setText("");
                    numIn = false;
                } else if(equation.length() > 0) {
                    equation.setText(equation.getText().subSequence(0, (equation.getText().length() - 3)) + " - ");
                    inBuffer.deleteCharAt(inBuffer.length() - 1);
                    inBuffer.append("-");
                }
                break;
            case R.id.b_times:
                if(numIn) {
                    equation.setText("" + equation.getText() + input.getText() + " × ");
                    inBuffer.append(input.getText() + "*");
                    input.setText("");
                    numIn = false;
                } else if(equation.length() > 0) {
                    equation.setText(equation.getText().subSequence(0, (equation.getText().length() - 3)) + " × ");
                    inBuffer.deleteCharAt(inBuffer.length() - 1);
                    inBuffer.append("*");
                }
                break;
            case R.id.b_divide:
                if(numIn) {
                    equation.setText("" + equation.getText() + input.getText() + " ÷ ");
                    inBuffer.append(input.getText() + "/");
                    input.setText("");
                    numIn = false;
                } else if(equation.length() > 3) {
                    equation.setText(equation.getText().subSequence(0, (equation.getText().length() - 3)) + " ÷ ");
                    inBuffer.deleteCharAt(inBuffer.length() - 1);
                    inBuffer.append("/");
                }
                break;
            case R.id.b_mod:
                if(numIn) {
                    equation.setText("" + equation.getText() + input.getText() + " % ");
                    inBuffer.append(input.getText() + "%");
                    input.setText("");
                    numIn = false;
                } else if(equation.length() > 0) {
                    equation.setText(equation.getText().subSequence(0, (equation.getText().length() - 3)) + " % ");
                    inBuffer.deleteCharAt(inBuffer.length() - 1);
                    inBuffer.append("%");
                }
                break;
            case R.id.b_clear:
                input.setText("");
                equation.setText("");
                inBuffer.delete(0, inBuffer.length());
                numIn = false;
                break;
            case R.id.b_backspace:
                if(input.length() > 0) input.setText(input.getText().subSequence(0, input.getText().length() - 1));
                if(input.length() == 1 && input.getText().charAt(0) == '-') input.setText("");
                if(input.length() == 0) numIn = false;
                break;
            case R.id.b_sign:
                if(input.length() > 0) {
                    if (input.getText().charAt(0) != '-') {
                        input.setText("-" + input.getText());
                    } else {
                        input.setText(input.getText().subSequence(1, input.length()));
                    }
                }
                break;
            case R.id.b_equals:
                if(numIn) {
                    equation.setText("" + equation.getText() + input.getText() + " =");
                    inBuffer.append(input.getText());

                    double ans = evaluator.eval(inBuffer.toString());
                    if((int) ans == ans) input.setText("" + (int)ans);
                    else input.setText("" + ans);

                    numIn = false;
                    complete = true;
                }
                break;
        }
    }
}
