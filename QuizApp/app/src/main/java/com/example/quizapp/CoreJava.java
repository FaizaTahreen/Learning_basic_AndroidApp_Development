package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CoreJava extends AppCompatActivity implements View.OnClickListener {

    RadioGroup rg;
    Button next;
    RadioButton r1,r2,r3,r4;
    TextView res,que;
    String question[]={"who is the founder of Java Language ? ", "What is the correct Syntax for java main method ?",
            "Which of the following is not a java keyword ?", "An Object Could be ....",
            "What is the default value of the local variables ?"};
    String op1[]={"ken", "public void main(String[] arg)", "static","anything","null"};
    String op2[]={"Andy Rubin", "public static void main(String[] arg)", "try","an algorithm","0"};
    String op3[]={"james Gosling", "public static void main()", "Integer","a data container","depends on the data type"};
    String op4[]={"Denis Richi", "none of above", "new","a program","no default value for local variable"};
    String ans[]={"james Gosling", "public static void main(String[] arg)","Integer",
            "anything","no default value for local variable"};
    int i=0;
    int marks=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core_java);
        rg=findViewById(R.id.rg);
        res=findViewById(R.id.res);
        next=findViewById(R.id.next);
        r1=findViewById(R.id.r1);
        r2=findViewById(R.id.r2);
        r3=findViewById(R.id.r3);
        r4=findViewById(R.id.r4);
        que=findViewById(R.id.que);
        setQuestion();
        next.setOnClickListener(this);

    }
    private  void setQuestion()
    {
        que.setText(question[i]);
        r1.setText(op1[i]);
        r2.setText(op2[i]);
        r3.setText(op3[i]);
        r4.setText(op4[i]);
    }
    @Override
    public void onClick(View view) {
        if (i==4)
        {
            rg.setVisibility(View.INVISIBLE);
            que.setVisibility(View.INVISIBLE);
            RadioButton r=findViewById(rg.getCheckedRadioButtonId());
            if(r.getText().toString().equals(ans[i]))
            {
                marks+=10;
            }
            res.setText("Result is "+marks);
        }
        RadioButton r=findViewById(rg.getCheckedRadioButtonId());
        if(r.getText().toString().equals(ans[i]))
        {
            marks+=10;
        }
        if(i!=4){
            i++;
        }
        if(i==4)
        {
            next.setText("SUBMIT");
        }
        setQuestion();
        rg.clearCheck();

    }
}