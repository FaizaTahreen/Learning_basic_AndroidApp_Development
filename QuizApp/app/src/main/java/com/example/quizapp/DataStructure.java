package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class DataStructure extends AppCompatActivity implements View.OnClickListener {

    RadioGroup rg;
    Button next;
    RadioButton r1,r2,r3,r4;
    TextView res,que;
    String question[]={"What is the worst case time complexity of linear search algorithm ?",
            "Which of the following is the dynamic programming approach ?",
            "The minimum number of edges required to create a cyclid graph of n vertices is ",
            "Which of the following uses memoization?",
            "Which sorting is not stable ?"};
    String op1[]={"O(1)", "Fabonaci", "n","Greedy apporach","Selection sort"};
    String op2[]={"O(n)", "Tower of hanoi", "-n+1","Divide and Conquer apporach","Bubble sort"};
    String op3[]={"O(logn)", "Dijkstra shortest path", "-n-1","Dynamic Programming apporach","Merge sort"};
    String op4[]={"O(n^2)", "All Above ", "-2n","None of above","Insertion sort"};
    String ans[]={"O(n^2)", "All Above","n","Dynamic Programming apporach","Selection sort"};
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