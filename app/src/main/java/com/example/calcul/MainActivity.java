package com.example.calcul;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    boolean flag = true;
    public Map<String, Integer> MAIN_MATH_OPERATIONS;

     {
        MAIN_MATH_OPERATIONS = new HashMap<String, Integer>();
        MAIN_MATH_OPERATIONS.put("*", 1);
        MAIN_MATH_OPERATIONS.put("/", 1);
        MAIN_MATH_OPERATIONS.put("+", 2);
        MAIN_MATH_OPERATIONS.put("-", 2);
    }

    private ExpressionParser expressionParser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expressionParser =new ExpressionParser( MAIN_MATH_OPERATIONS );
    }

    public void onClick(View view) {
        ((TextView) findViewById(R.id.textviev)).setText(this.expressionParser.calculateExpression((( TextView )findViewById(R.id.textviev)).getText().toString()).toString());
        flag=true;
    }

    public void onClick2(View view) {
        if(flag){
            onClickC(view);
        }
        TextView tv = (TextView)findViewById(R.id.textviev);
        String str= tv.getText().toString()+((Button)view).getText();
        tv.setText(str);
        flag=false;
    }

    public void onClickC(View view) {
        ((TextView)findViewById(R.id.textviev)).setText("");
    }
}
