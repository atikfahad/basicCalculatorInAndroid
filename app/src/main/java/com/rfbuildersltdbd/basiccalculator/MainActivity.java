package com.rfbuildersltdbd.basiccalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences.Editor;

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
    static int historyCount = 0;
    Editor editor;
    SharedPreferences pref;
    String historySingle = "";
    boolean resultClicked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getApplicationContext().getSharedPreferences("myPref", 0);
        editor = pref.edit();

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
        btnMminus = (Button) findViewById(R.id.button_idMminus);


        btnHistory = (Button) findViewById(R.id.button_idHistory);

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                //String countValue = ;
                intent.putExtra("historyCount", Integer.toString(historyCount));
                //intent.putExtra("historyCount", historyCount);
                //String test = "Something";
                //showResult.append((Integer.toString( historyCount)));
                for(int i = historyCount; i > 0; i--){
                    intent.putExtra(i + ".", pref.getString(i + ".", null));
                    //test.concat(pref.getString(historyCount + ".", null));
                    //showResult.append(pref.getString(historyCount + ".", "Nothing is here"));

                }/*
                for(int i = 0; i > historyCount; i--){
                    intent.putExtra(historyCount + ".", pref.getString(historyCount + ".", null));
                    //test.concat(pref.getString(historyCount + ".", null));
                    showResult.append(pref.getString(historyCount + ".", "Nothing is here"));

                } */
                //getResources().getString("s");
                //editor.putString("10.", "nothing");
                //editor.commit();
                //test.concat(pref.getString( "10.", "Nothing Defined"));
                //showResult.setText(pref.getString( "1.", "Nothing Defined"));
                //intent.putExtra("1.", pref.getString("1.", null));
                startActivity(intent);
                finish();
            }
        });

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
        btnMminus.setOnClickListener(new View.OnClickListener() {
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
                if(dotTest && !resultClicked){
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
                        historySingle = value1 + " + " + value2 +" = " + result;
                        showResult.setText(historySingle);
                        memoryResult = result;
                        editor.putString(++historyCount + ".", historySingle);
                        editor.commit();
                    //    showResult.setText(pref.getString("1.", null));
                        resultClicked = true;
                        break;
                    }
                    case 2: {
                        fromView = showCalculation.getText().toString();
                        value2 = Double.parseDouble(fromView);
                        double result = value1 - value2;
                        showCalculation.setText(String.valueOf(result));
                        historySingle = value1 + " - " + value2 +" = " + result;
                        showResult.setText(historySingle);
                        editor.putString(++historyCount + ".", historySingle);
                        editor.commit();
                        memoryResult = result;
                     //   showCalculation.setText(pref.getString("1.", null));
                        resultClicked = true;
                        break;
                    }
                    case 3: {
                        fromView = showCalculation.getText().toString();
                        value2 = Double.parseDouble(fromView);
                        double result = value1 * value2;
                        showCalculation.setText(String.valueOf(result));
                        historySingle = value1 + " * " + value2 +" = " + result;
                        showResult.setText(historySingle);
                        editor.putString(++historyCount + ".", historySingle);
                        editor.commit();
                        memoryResult = result;
                        resultClicked = true;
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
                        historySingle = value1 + " / " + value2 +" = " + result;
                        showResult.setText(historySingle);
                        editor.putString(++historyCount + ".", historySingle);
                        editor.commit();
                        memoryResult = result;
                        resultClicked = true;
                        break;
                    }
                    default:
                        showCalculation.setText("0");
                        resultClicked = true;
                        checkOperation = 0;
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
                checkOperation = 0;
            }
        });

    }

    @Override
    public void onClick(View view) {
        if(resultClicked){
            showCalculation.setText("0");
            resultClicked = false;
        }
        String fromView = showCalculation.getText().toString();
        double check = Double.parseDouble(fromView);
        Button b = (Button) view;
        if (fromView.length() == 1 && check == 0)
            showCalculation.setText(b.getText());
        else
            showCalculation.append(b.getText());
    }
}
