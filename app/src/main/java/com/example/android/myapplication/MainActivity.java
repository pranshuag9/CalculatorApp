package com.example.android.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private Button b0;
    private Button plus;
    private Button sub;
    private Button mul;
    private Button div;
    private Button mod;
    private Button power;
    private Button closBracket;
    private Button openBracket;
    private ImageButton del;
    private Button clr;

    public static boolean reset = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        typeCast();
        et.setSelection(et.getText().length());
        del.setOnClickListener(this);
    }

    public void typeCast(){
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);
        b4 = findViewById(R.id.btn4);
        b5 = findViewById(R.id.btn5);
        b6 = findViewById(R.id.btn6);
        b7 = findViewById(R.id.btn7);
        b8 = findViewById(R.id.btn8);
        b9 = findViewById(R.id.btn9);
        b0 = findViewById(R.id.btn0);
        plus = findViewById(R.id.btn_plus);
        sub = findViewById(R.id.btn_sub);
        mul = findViewById(R.id.btn_mul);
        div = findViewById(R.id.btn_div);
        mod =  findViewById(R.id.btn_mod);
        power = findViewById(R.id.btn_power);
        openBracket = findViewById(R.id.btn_opening_bracket);
        closBracket = findViewById(R.id.btn_closing_bracket);
        del = findViewById(R.id.btn_del);
        clr = findViewById(R.id.btn_clr);
        et = findViewById(R.id.editText);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_del){
            if(!et.getText().toString().equals("")){
                String value = et.getText().toString();
                if(value.length()>0){
                    value = value.substring(0,value.length()-1);
                    et.setText(value);
                }
            }
        }
    }

    public void editValue(View view) {

        Button b = (Button) view;

        String enteredData = b.getText().toString();


        if (reset == true) {
            et.setText(enteredData);
            reset=false;
        } else {
            String s = et.getText().toString();
            s = s + enteredData;
            et.setText(s);
        }
        et.setSelection(et.getText().length());
    }

    public void clearText(View view) {
        Button b = (Button) view;
        et.setText("0");
        et.setSelection(et.getText().length());
        reset = true;
    }

    public void calculate(View view){
        String text = et.getText().toString();
        Expression equation = new ExpressionBuilder(text).build();

        try{
            double result = equation.evaluate();
            if(Math.floor(result)==result) {
                result = (int) result;
            }

            et.setText(result+"");
            et.setSelection(et.getText().length());
            //reset=true
        }catch (ArithmeticException a){
            et.setText("Error");
        }
    }
}

