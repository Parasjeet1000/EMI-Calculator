package com.example.assignment1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.lang.Math;

public class MainActivity extends AppCompatActivity {

    private Button calc;
    private EditText principal;
    private EditText interest;
    private EditText years;
    private int amount;
    private double percent;
    private int term;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calc = (Button) findViewById(R.id.calculate);
        principal = (EditText) findViewById(R.id.principal);
        interest = (EditText)findViewById(R.id.interest);
        years = (EditText)findViewById(R.id.years);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Converts input from Edittext to a integer or double values and stored in their respective variables
                amount = Integer.valueOf(principal.getText().toString());
                percent = Double.parseDouble(interest.getText().toString());
                term = Integer.valueOf(years.getText().toString());
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //Print out for monthly payment to user through dialog box
                builder.setMessage("Your Monthly EMI payment based on these figures is $"+ String.format("%.2f", emi_calculator(amount, percent, term)) + "/monthly");
                builder.setTitle("Payment Results");
                //Lets user quit dialog with doen button
                builder.setPositiveButton("Done", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // When the user click yes button then app will close
                    dialog.dismiss();
                });
                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();
                // Show the Alert Dialog box
                alertDialog.show();
                principal.setText("");
                interest.setText("");
                years.setText("");
            }
        });
    }
    static float emi_calculator(int p, double r, int t)
    {
        float emi;

        r = r / (12 * 100); // one month interest
        t = t * 12; // one month period
        emi = (float) ((p * r * (float)Math.pow(1 + r, t)) / (float)(Math.pow(1 + r, t) - 1));

        return (emi);
    }
}