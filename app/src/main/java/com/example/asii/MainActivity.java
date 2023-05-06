package com.example.asii;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    Spinner qis1;
    Spinner qis2;
    Spinner qis3;
    TextView hiiden;


    @Override

    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=findViewById(R.id.spiner);
        qis1=findViewById(R.id.quistion1);
        qis2=findViewById(R.id.quistion2);
        qis3=findViewById(R.id.quistion3);
        hiiden=findViewById(R.id.hidden);

        putdata();

    }

    public void putdata(){
        Subjects sub=new Subjects();
        ArrayAdapter<String>array=new ArrayAdapter<>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sub.finddata());
        spinner.setAdapter(array);
    }


    public void Result(View view) {
        String st1=qis1.getSelectedItem().toString();
        String st2=qis2.getSelectedItem().toString();
        String st3=qis3.getSelectedItem().toString();
        hiiden.setVisibility(view.VISIBLE);
        int counter=0;
        if(st1.equals("1948")){
            counter++;
        }
         if(st2.equals("1967")){
            counter++;
        }
        if(st3.equals("2000")){
            counter++;
        }
        if(counter==3){
            hiiden.setText("The result is : " + counter + " your are hero !! " )  ;
        }
        else {

            hiiden.setText("q1  answer : 1948" +"\n"+" q2 answer :1967 "+"\n" +" q3 answer :  2000" + "\n" + "The result is : " +  counter)   ;

        }





    }
}
