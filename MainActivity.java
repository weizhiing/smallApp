package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.*;
import android.widget.*;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    //当前展示str
    String str = "";
    //展示框
    EditText et;
    int c = 0, flag = 0;//c记录上个符号（1-4）或是否有符号（0）或符号是否使用（0）   //flag 记录显示数字时候刷新（当输入运算符后，输入数字刷新）
    double b = 0.0, g = 0.0;     //g记录当前显示的数字（运算符之后的）  b 记录运算符之前的数字
    View vi;  //记录上一个view，是数字还是运算符

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 1, "退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == 1) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    //计算方法
    public double calculater() {
        switch (c) {
            case 0:
                b = g;
                break;
            case 1:
                b = b + g;
                break;
            case 2:
                b = b - g;
                break;
            case 3:
                b = b * g;
                break;
            case 4:
                b = b / g;
                break;
        }
        c = 0;
        return b;
    }
    public void onClick(View v,int number,Button[] symbol) {
        if (str != "") {
            if (vi == symbol[0] || vi == symbol[1] || vi == symbol[2] || vi == symbol[3]) {
                c = number+1;
            } else {
                g = Double.parseDouble(str);
                calculater();
                str = "" + b;
                et.setText(str);
                c = number+1;
                flag = 1;
                vi = v;
            }
        }
    }
    public void onClick(View v, int number) {
        if (flag == 1) {
            str = "";
            str += number;
            et.setText(str);
            flag = 0;
        } else {
            if (number == 0) {
                char[] ch1 = str.toCharArray();
                if (!(ch1.length == 1 && ch1[0] == '0')) {
                    str += 0;
                    et.setText(str);
                }
            } else {
                str += number;
                et.setText(str);
            }
        }
        vi = v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //获得按键
        final Button[] number = new Button[10];
        final Button[] symbol = new Button[11];


        symbol[0] = (Button) findViewById(R.id.buttonAdd);
        symbol[1] = (Button) findViewById(R.id.buttonSubtract);
        symbol[2] = (Button) findViewById(R.id.buttonMultiply);
        symbol[3] = (Button) findViewById(R.id.buttonDivide);
        symbol[4] = (Button) findViewById(R.id.buttonIs);
        symbol[5] = (Button) findViewById(R.id.buttonPoint);
        symbol[6] = (Button) findViewById(R.id.buttonCe);
        symbol[7] = (Button) findViewById(R.id.buttonAc);
        symbol[8] = (Button) findViewById(R.id.PlusAndMinus);
        symbol[9] = (Button) findViewById(R.id.squareRoot);
        symbol[10] = (Button) findViewById(R.id.square);

        number[0] = (Button) findViewById(R.id.button0);
        number[1] = (Button) findViewById(R.id.button1);
        number[2] = (Button) findViewById(R.id.button2);
        number[3] = (Button) findViewById(R.id.button3);
        number[4] = (Button) findViewById(R.id.button4);
        number[5] = (Button) findViewById(R.id.button5);
        number[6] = (Button) findViewById(R.id.button6);
        number[7] = (Button) findViewById(R.id.button7);
        number[8] = (Button) findViewById(R.id.button8);
        number[9] = (Button) findViewById(R.id.button9);

        et = (EditText) findViewById(R.id.textView1);

        et.setText(str);

        //设定数字按键
        for (int i = 0; i < 10; i++) {
            final int x = i;
            number[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    MainActivity.this.onClick(v, x);
                }
            });
        }
        //设定符号键

        //加
        for(int i=0;i<4;i++){
           final int x=i;
            symbol[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                   MainActivity.this.onClick(v,x,symbol);
                }});
        }


        //等号
        symbol[4].setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (str != "" && vi != symbol[0] && vi != symbol[1] && vi != symbol[2] && vi != symbol[3]) {
                    g = Double.parseDouble(str);
                    calculater();
                    str = "" + b;
                    et.setText(str);
                    flag = 1;
                    vi = v;

                }
            }
        });


        //小数点
        symbol[5].setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (str == "") {
                    str += ".";
                    et.setText(str);
                } else {
                    char ch1[];
                    int x = 0;
                    ch1 = str.toCharArray();
                    for (int i = 0; i < ch1.length; i++)
                        if (ch1[i] == '.')
                            x++;
                    if (x == 0) {
                        str += ".";
                        et.setText(str);
                    }
                }

            }
        });

        //CE
        symbol[6].setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                str = "";
                et.setText(str);
                vi = v;

            }
        });
        //AC
        symbol[7].setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                b = 0.0;
                c = 0;
                g = 0.0;
                str = "";
                et.setText(str);

            }
        });
        // +/-
        symbol[8].setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (vi != symbol[5] && str != "") {
                    char ch = str.charAt(0);
                    if (ch == '-')
                        str = str.replace("-", "");
                    else
                        str = "-" + str;
                    et.setText(str);
                }
            }
        });
        // √
        symbol[9].setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (!str.equals("") && !str.equals("0") && Pattern.matches(".*[0-9]+.*", str)) {
                    double a = Double.parseDouble(str);
                    str = Math.sqrt(a) + "";
                    et.setText(str);
                }
            }
        });
        // square
        symbol[10].setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (!str.equals("")) {
                    double a = Double.parseDouble(str);
                    str = "" + a * a;
                    et.setText(str);
                }
            }
        });
    }


}
