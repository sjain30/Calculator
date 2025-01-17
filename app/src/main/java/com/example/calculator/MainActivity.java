package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText result;
    private EditText newnum;
    private TextView displayOperation;

    // variables to hold operations

    private Double operand1 = null;
    private Double operand2 = null;
    private String pendingOperation = "=";

    private static final String Pendingoperation=null;
    private static final String Operand1=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        newnum = findViewById(R.id.newnum);
        displayOperation = findViewById(R.id.operation);

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonac = findViewById(R.id.buttonac);
        Button buttonneg = findViewById(R.id.buttonneg);

        Button buttonpercent = findViewById(R.id.buttonpercent);
        Button buttonDot = findViewById(R.id.buttonDot);
        Button buttonEquals = findViewById(R.id.buttonEquals);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonPlus = findViewById(R.id.buttonAdd);

        View.OnClickListener remove = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newnum.setText("");
                result.setText("");
                displayOperation.setText("");
                operand1 = null;
                operand2 = null;

            }
        };

        buttonac.setOnClickListener(remove);

        View.OnClickListener listener = new View.OnClickListener() { //onclicklistener for
            @Override                                                  // the buttons to enter
            public void onClick(View view) {                        // no. in new num
                Button b = (Button) view;
                newnum.append(b.getText().toString()); //adds the clicked num to newnum
            }
        };

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);

        final View.OnClickListener oplistener = new View.OnClickListener() { //listener for the operations
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String op = b.getText().toString();         //
                String value = newnum.getText().toString(); //gets the no. entered in newnum
                try {
                    Double doublevalue = Double.valueOf(value); //check if any no. is entered
                    performOperation(doublevalue, op);// perform calculation
                } catch (NumberFormatException e) {
                    newnum.setText("");
                }

                pendingOperation = op;
                displayOperation.setText(pendingOperation);                    // display the operation pressed
            }
        };
        buttonEquals.setOnClickListener(oplistener);
        buttonDivide.setOnClickListener(oplistener);
        buttonMinus.setOnClickListener(oplistener);
        buttonMultiply.setOnClickListener(oplistener);
        buttonPlus.setOnClickListener(oplistener);

        buttonneg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = newnum.getText().toString();
                if(value.length() == 0) {
                    newnum.setText("-");
                } else {
                    try {
                        Double doubleValue = Double.valueOf(value);
                        doubleValue *= -1;
                        newnum.setText(doubleValue.toString());
                    } catch(NumberFormatException e) {
                        // newNumber was "-" or ".", so clear it
                        newnum.setText("");
                    }
                }

            }
        });


        buttonpercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = newnum.getText().toString();
                    try {
                        Double doubleValue = Double.valueOf(value);
                        doubleValue /= 100;
                        newnum.setText(doubleValue.toString());
                    } catch(NumberFormatException e) {
                        // newNumber was "-" or ".", so clear it
                        newnum.setText("");
                    }
                }

            });

    }




    private void performOperation(Double value, String op) {
        boolean error=false;
        if (null == operand1) {
            operand1 = value;
        } else {
            operand2 = value;

            if (pendingOperation.equals("=")) {
                pendingOperation = op;
            }
            switch (pendingOperation) {
                case "=":
                    operand1 = operand2;
                    break;
                case "÷":
                    if (operand2 == 0) {
                        error=true;
                    } else {
                        operand1 /= operand2;
                    }
                    break;
                case "x":
                    operand1 *= operand2;
                    break;
                case "-":
                    operand1 -= operand2;
                    break;
                case "+":
                    operand1 += operand2;
                    break;

            }
        }
        if (!error) {
            result.setText(operand1.toString());
        }
        else {
            result.setText("ERROR");
            operand1=0.0;
        }
        newnum.setText("");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(Pendingoperation,pendingOperation);
        if(operand1!=null)
            outState.putDouble(Operand1,operand1);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString(Pendingoperation);
        operand1=savedInstanceState.getDouble(Operand1);
        displayOperation.setText(pendingOperation);
    }
}

























