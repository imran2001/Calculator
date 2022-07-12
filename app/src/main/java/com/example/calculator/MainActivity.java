package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView solutiontv, resulttv;
    MaterialButton buttonc,buttonac, buttonbracop, buttonbraccl, buttondot;
    MaterialButton buttonplus, buttonminus, buttonmul, buttondiv, buttoneq;
    MaterialButton button1,button2, button0, button4, button5, button6, button7,button8, button9, button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        solutiontv=findViewById(R.id.solution_tv);
        resulttv=findViewById(R.id.result_tv);
        assign(buttonc, R.id.btn_c);
        assign(buttonac, R.id.btn_ac);
        assign(buttonbracop, R.id.btn_open_bracker);
        assign(buttonbraccl, R.id.btn_close_bracket);
        assign(buttondot, R.id.btn_dot);
        assign(buttonplus, R.id.btn_plus);
        assign(buttonminus, R.id.btn_minus);
        assign(buttonmul, R.id.btn_multiply);
        assign(buttondiv, R.id.btn_divide);
        assign(buttoneq, R.id.btn_equls);
        assign(button0, R.id.btn_zero);
        assign(button1, R.id.btn_1);
        assign(button2, R.id.btn_2);
        assign(button3, R.id.btn_3);
        assign(button4, R.id.btn_4);
        assign(button5, R.id.btn_5);
        assign(button6, R.id.btn_6);
        assign(button7, R.id.btn_7);
        assign(button8, R.id.btn_8);
        assign(button9, R.id.btn_9);

    }

    void assign(MaterialButton btn, int id)
    {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttontext =button.getText().toString();
        String datatocalculate=solutiontv.getText().toString();

        if (buttontext.equals("AC"))
        {
            solutiontv.setText("");
            resulttv.setText("0");
            return;
        }

        if (buttontext.equals("="))
        {
            solutiontv.setText(resulttv.getText());
            return;
        }

        if (buttontext.equals("C")){
            datatocalculate=datatocalculate.substring(0,datatocalculate.length()-1);
        }else{
            datatocalculate =datatocalculate+buttontext;
        }

        solutiontv.setText(datatocalculate);
        String finalresult=getresult(datatocalculate);

        if(!finalresult.equals("Err"))
        {
            resulttv.setText(finalresult);
        }

    }
    String getresult(String data)
    {
        try {
            Context context =Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable =context.initStandardObjects();
            String finalresult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalresult.endsWith(".0")){
                finalresult=finalresult.replace(".0","");
            }
            return finalresult;

        }catch (Exception e){
            return "Err";
        }

    }
}