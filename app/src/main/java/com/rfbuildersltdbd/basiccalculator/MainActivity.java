package com.rfbuildersltdbd.basiccalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnEqual, btnDot, btnZero;
    Button btnMplus, btnMminus, btnHistory;
    Button btnPlus, btnMinus, btnDiv, btnMult;
    Button btnClear, btnCross;
    TextView showCalculation, showResult;
    static double value1 = 0, value2 = 0;
    static boolean dotTest = true;
    static double memoryResult = 0;
    int checkOperation = 0;
    String fromView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.button_id1);
        btn2 = (Button) findViewById(R.id.button_id2);
        btn3 = (Button) findViewById(R.id.button_id3);
        btn4 = (Button) findViewById(R.id.button_id4);
        btn5 = (Button) findViewById(R.id.button_id5);
        btn6 = (Button) findViewById(R.id.button_id6);
        btn7 = (Button) findViewById(R.id.button_id7);
        btn8 = (Button) findViewById(R.id.button_id8);
        btn9 = (Button) findViewById(R.id.button_id9);
        btnZero = (Button) findViewById(R.id.button_idZero);
        btnDot = (Button) findViewById(R.id.button_idDot);
        btnPlus = (Button) findViewById(R.id.button_idPlus);
        btnMinus = (Button) findViewById(R.id.button_idMinus);
        btnMult = (Button) findViewById(R.id.button_idMultiplication);
        btnDiv = (Button) findViewById(R.id.button_idDivision);
        btnHistory = (Button) findViewById(R.id.button_idHistory);
        btnMplus = (Button) findViewById(R.id.button_idMplus);
        btnMminus = (Button) findViewById(R.id.button_idMminus);
        btnEqual = (Button) findViewById(R.id.button_idEqual);
        showCalculation = (TextView) findViewById(R.id.text_view_id);
        showResult = (TextView) findViewById(R.id.text_view_id2);
        btnClear = (Button) findViewById(R.id.button_idCancel);
        btnCross = (Button) findViewById(R.id.button_idCross);

        btnMplus = (Button) findViewById(R.id.button_idMplus);
        btnMinus = (Button) findViewById(R.id.button_idMminus);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        btnMplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromView = showCalculation.getText().toString();
                value1 = Double.parseDouble(fromView);
                double result = value1 + memoryResult;
                showCalculation.setText(String.valueOf(result));
                //showCalculation.setText(((chars) result));
                showResult.setText(value1 + " + " + memoryResult +" = " + result);
                memoryResult = result;

            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromView = showCalculation.getText().toString();
                value1 = Double.parseDouble(fromView);
                double result = memoryResult - value1;
                showCalculation.setText(String.valueOf(result));
                //showCalculation.setText(((chars) result));
                showResult.setText(memoryResult + " - " + value1 +" = " + result);
                memoryResult = result;
            }
        });
        btnCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromView = showCalculation.getText().toString();
                if(fromView.length() >= 2)
                    showCalculation.setText(fromView.substring(0, fromView.length() - 1));
                else
                    showCalculation.setText("0");
            }
        });
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dotTest){
                showCalculation.append(".");
                dotTest = false;
                }
            }
        });


        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromView = showCalculation.getText().toString();
                value1 = Double.parseDouble(fromView);
                showCalculation.setText("0");
                showResult.setText(fromView + " " + "+" + " ");
                checkOperation = 1;
                dotTest = true;
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    fromView = showCalculation.getText().toString();
                    value1 = Double.parseDouble(fromView);
                    showCalculation.setText("0");
                    showResult.setText(fromView + " " + "-" + " ");
                    checkOperation = 2;
                    dotTest = true;
            }
        });
        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromView = showCalculation.getText().toString();
                value1 = Double.parseDouble(fromView);
                showCalculation.setText("0");
                showResult.setText(fromView + " " + "*" + " ");
                checkOperation = 3;
                dotTest = true;
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromView = showCalculation.getText().toString();
                value1 = Double.parseDouble(fromView);
                showCalculation.setText("0");
                showResult.setText(fromView + " " + "/" + " ");
                checkOperation = 4;
                dotTest = true;
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (checkOperation){
                    case 1: {
                        fromView = showCalculation.getText().toString();
                        value2 = Double.parseDouble(fromView);
                        double result = value1 + value2;
                        showCalculation.setText(String.valueOf(result));
                        //showCalculation.setText(((chars) result));
                        showResult.setText(value1 + " + " + value2 +" = " + result);
                        memoryResult = result;
                        break;
                    }
                    case 2: {
                        fromView = showCalculation.getText().toString();
                        value2 = Double.parseDouble(fromView);
                        double result = value1 - value2;
                        showCalculation.setText(String.valueOf(result));
                        showResult.setText(value1 + " - " + value2 +" = " + result);
                        memoryResult = result;
                        break;
                    }
                    case 3: {
                        fromView = showCalculation.getText().toString();
                        value2 = Double.parseDouble(fromView);
                        double result = value1 * value2;
                        showCalculation.setText(String.valueOf(result));
                        showResult.setText(value1 + " * " + value2 +" = " + result);
                        memoryResult = result;
                        break;
                    }
                    case 4: {
                        fromView = showCalculation.getText().toString();
                        value2 = Double.parseDouble(fromView);
                        if(value2 == 0){
                            showResult.setText("Cannot Divided by Zero");
                            break;
                        }
                        double result = value1 / value2;
                        showCalculation.setText(String.valueOf(result));
                        showResult.setText(value1 + " / " + value2 +" = " + result);
                        memoryResult = result;
                        break;
                    }
                    default:
                        showCalculation.setText("0");
                        break;

                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalculation.setText("0");
                value2 = 0;
                value1 = 0;
                dotTest = true;
                showResult.setText("0");
            }
        });

    }

    @Override
    public void onClick(View view) {
        String fromView = showCalculation.getText().toString();
        double check = Double.parseDouble(fromView);
        Button b = (Button) view;
        if (fromView.length() == 1 && check == 0)
            showCalculation.setText(b.getText());
        else
            showCalculation.append(b.getText());
    }
}
