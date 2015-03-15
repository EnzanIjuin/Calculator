package com.example.shelton.calculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button[] numbers = new Button[10];
    Button[] operators = new Button[6];
    Button[] edits = new Button[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Toast.makeText(this, "Button was clicked!", Toast.LENGTH_LONG).show();
    }
}
