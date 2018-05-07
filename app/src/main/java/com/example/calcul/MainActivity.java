package com.example.calcul;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<String> example = new ArrayList<String>();
    boolean flagBrackers = false;
    String exampleStr = new String();

    private ExpressionParser2 expressionParser = null;
    private TextView textView =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expressionParser = new ExpressionParser2();
        this.textView = ((TextView) findViewById(R.id.textviev));
    }

    public void onClick(View view) {
        if(!exampleStr.equals(""))
        {
            this.example.add(exampleStr);
            exampleStr="";
        }
        Double ex= this.expressionParser.calculate(this.example.listIterator());
        onClickC(view);
        this.example.add(ex.toString());
        PrintText(ex.toString());
    }

    public void onClick2(View view) {
        String str = ((Button)view).getText().toString();
        if(!this.expressionParser.isOperator(str)) {
           this.exampleStr+=str;
            PrintText(exampleStr);
        }else {
            PrintText(str);
            if(!exampleStr.equals("")) {
                this.example.add(this.exampleStr);
            }
            this.example.add(str);
            this.exampleStr="";
        }
    }

    public void onClickC(View view) {
        PrintText("");
        this.example.clear();
    }

    public void onClickBrackets(View view) {
        if(!exampleStr.equals("")) {
            this.example.add(this.exampleStr);
        }
        if (flagBrackers) {
            if (this.expressionParser.isNumber(this.textView.getText().toString())) {
                this.example.add(")");
                flagBrackers = false;
            }
            } else if (this.expressionParser.isOperator(this.textView.getText().toString())) {
                this.example.add("(");
                flagBrackers = true;
            }
            PrintText(this.example.get(example.size() - 1));
    }



    public void onClickC2(View view) {
        if(!exampleStr.equals(""))
        {
            this.example.add(exampleStr);
            exampleStr="";
        }
        if(!this.example.isEmpty())
        {
            this.example.remove(this.example.size() - 1);
            if(!this.example.isEmpty())
                PrintText(this.example.get(example.size() - 1));
            else
                PrintText("");
        }
    }

    private void PrintText(String str) {
       this.textView.setText(str);
    }

}
