package com.deep1129agg.math;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    TextView textView1, textView2,textView3,textView4,textView5,textView6,textView7,textView8,
            textView9,textView10,textView11,textView12,textView13,textView14, textView15, textView16;
    TextView resultTextView, gameResultTextView;
    TextView[] textViewArray;
    public static boolean gameOn;
    boolean textView1Boolean, textView2Boolean,textView3Boolean,textView4Boolean,textView5Boolean,textView6Boolean
            ,textView7Boolean,textView8Boolean,textView9Boolean,textView10Boolean,textView11Boolean,textView12Boolean,
            textView13Boolean,textView14Boolean, textView15Boolean, textView16Boolean;
    String[] entry = {"1","2","3","4","5","6","7","8","9","2","-","*","/","%","+"};
    int noOfClicks = 0, prevTag = 0;
    String[] textViewforResult = new String[3];
    int[] resultTextViewTag = new int[3];
    int[] resultTextViewIndex = new int[3];
    int operatorCount=0,r;
    boolean[] textViewBooleanArray = new boolean[16];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
        textView10 = findViewById(R.id.textView10);
        textView11 = findViewById(R.id.textView11);
        textView12 = findViewById(R.id.textView12);
        textView13 = findViewById(R.id.textView13);
        textView14 = findViewById(R.id.textView14);
        textView15 = findViewById(R.id.textView15);
        textView16 = findViewById(R.id.textView16);
        resultTextView = findViewById(R.id.resultTextView);
        gameResultTextView = findViewById(R.id.gameResultTextView);
        textViewArray = new TextView[16];
        textViewArray[0] = textView1;
        textViewArray[1] = textView2;
        textViewArray[2] = textView3;
        textViewArray[3] = textView4;
        textViewArray[4] = textView5;
        textViewArray[5] = textView6;
        textViewArray[6] = textView7;
        textViewArray[7] = textView8;
        textViewArray[8] = textView9;
        textViewArray[9] = textView10;
        textViewArray[10] = textView11;
        textViewArray[11] = textView12;
        textViewArray[12] = textView13;
        textViewArray[13] = textView14;
        textViewArray[14] = textView15;
        textViewArray[15] = textView16;

        textViewBooleanArray[0] = textView1Boolean;
        textViewBooleanArray[1] = textView2Boolean;
        textViewBooleanArray[2] = textView3Boolean;
        textViewBooleanArray[3] = textView4Boolean;
        textViewBooleanArray[4] = textView5Boolean;
        textViewBooleanArray[5] = textView6Boolean;
        textViewBooleanArray[6] = textView7Boolean;
        textViewBooleanArray[7] = textView8Boolean;
        textViewBooleanArray[8] = textView9Boolean;
        textViewBooleanArray[9] = textView10Boolean;
        textViewBooleanArray[10] = textView11Boolean;
        textViewBooleanArray[11] = textView12Boolean;
        textViewBooleanArray[12] = textView13Boolean;
        textViewBooleanArray[13] = textView14Boolean;
        textViewBooleanArray[14] = textView15Boolean;
        textViewBooleanArray[15] = textView16Boolean;

        gameOn = true;

//        for(int i = 0 ; i<16; i++) {
//            textViewArray[i].setOnClickListener(this);
//
//        }

        for (int i = 0; i<16; i++) {
            textViewArray[i].setOnClickListener(this);
            fillEntry(i);
            textViewBooleanArray[i]=false;
        }
        setResultValue();
    }

    public void fillEntry(int i){
        Random rand = new Random();
        int r = rand.nextInt(15);
        textViewArray[i].setText(entry[r]);
    }

    public void setResultValue(){
        Random rand = new Random();
        r = rand.nextInt(25)+25;
        resultTextView.setText(String.valueOf(r));

    }

    @Override
    public void onClick(View v) {
        if(gameOn == true) {
            String viewTag = v.getTag().toString();
            int intTag = Integer.valueOf(viewTag);
            for (int i = 0; i < 16; i++) {
                if (i == intTag) {
                    if (prevTag == 0 || intTag == prevTag + 1 || intTag == prevTag - 1 || intTag == prevTag + 4 || intTag == prevTag - 4
                            || prevTag == intTag) {
                        if (textViewBooleanArray[i] == false) {
                            Log.i("CLicked", viewTag + " ");
                            textViewforResult[noOfClicks] = textViewArray[intTag].getText().toString();
                            resultTextViewTag[noOfClicks] = intTag;
                            if (textViewforResult[noOfClicks].equals("-")) {
                                resultTextViewIndex[noOfClicks] = 10;
                            } else if (textViewforResult[noOfClicks].equals("*")) {
                                resultTextViewIndex[noOfClicks] = 11;
                            } else if (textViewforResult[noOfClicks].equals("/")) {
                                resultTextViewIndex[noOfClicks] = 12;
                            } else if (textViewforResult[noOfClicks].equals("%")) {
                                resultTextViewIndex[noOfClicks] = 13;
                            } else if (textViewforResult[noOfClicks].equals("+")) {
                                resultTextViewIndex[noOfClicks] = 14;
                            } else {
                                resultTextViewIndex[noOfClicks] = Integer.valueOf(textViewArray[intTag].getText().toString());
                            }

                            noOfClicks++;
                            textViewArray[i].setBackgroundColor(Color.rgb(165, 143, 250));
                            textViewBooleanArray[i] = true;
                            if (noOfClicks == 3) {
                                calculateResult(resultTextViewTag, textViewforResult);
                            } else {
                                prevTag = intTag;
                                //                    will fix this later.

                            }

                        } else {
                            textViewArray[i].setBackgroundColor(Color.rgb(255, 237, 217));
                            noOfClicks--;
                            textViewBooleanArray[i] = false;
                        }

                    }

                }
            }

        }

    }

    public  void calculateResult(int[] resultTextViewTag, String[] textViewforResult){
        int[] operand = new int[2];
        int operator = 0 ;
        int k=0,result=0;
        prevTag = 0;
        noOfClicks=0;
        for(int i = 0 ; i<16; i++) {
            textViewArray[i].setBackgroundColor(Color.rgb(255, 237, 217));
            textViewBooleanArray[i]=false;
        }
        try {
            for (int i = 0; i < 3; i++) {
                if (textViewforResult[i].equals("-")) {
                    operator = 10;
                } else if (textViewforResult[i].equals("*")) {
                    operator = 11;
                } else if (textViewforResult[i].equals("/")) {
                    operator = 12;
                } else if (textViewforResult[i].equals("%")) {
                    operator = 13;
                } else if (textViewforResult[i].equals("+")) {
                    operator = 14;
                } else {
                    operand[k++] = Integer.valueOf(textViewforResult[i]);
                }
            }
            if(k!=2){
                throw new Exception();
            }

        if(operator == 10){
            result = Math.abs(operand[0]-operand[1]);
        }else if(operator == 11){
            result = operand[0]*operand[1];
        }else if(operator == 12){
            if(operand[1]!=0) {
                result = operand[0] / operand[1];
            }else{
                Toast.makeText(this, "Can't divide by zero", Toast.LENGTH_SHORT).show();
            }
        }else if(operator == 13){
            if(operand[1]!=0) {
                result = operand[0] % operand[1];
            }else{
                Toast.makeText(this, "Can't divide by zero", Toast.LENGTH_SHORT).show();
            }
        }else if(operator == 14){
            result = operand[0]+operand[1];
        }

        fillEntry(resultTextViewTag[0]);
        fillEntry(resultTextViewTag[1]);
        textViewArray[resultTextViewTag[2]].setText(String.valueOf(result));

        if(noOfOperators() == 0){
            Toast.makeText(this, "No more moves available", Toast.LENGTH_SHORT).show();

            for (int i = 0; i<16; i++) {
                fillEntry(i);
                textViewBooleanArray[i]=false;
            }
        }
        Log.i("result", result+" ");
        }catch (Exception e){
            Toast.makeText(this, "Invalid move", Toast.LENGTH_SHORT).show();
        }
    }

    public void playAgainButton(View view){
        for (int i = 0; i<16; i++) {
            fillEntry(i);
            textViewBooleanArray[i]=false;
            gameOn=true;
            gameResultTextView.setText(" ");
        }
        setResultValue();
    }

    public int noOfOperators(){
        operatorCount=0;
        for(int i = 0; i<16; i++ ){
            String value = textViewArray[i].getText().toString();
           if(value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/") || value.equals("%") ){
                operatorCount++;
            } else if(r == Integer.valueOf(value)){
               gameOn=false;
               gameResultTextView.setText("You Won!");
           }
        }
        return operatorCount;
    }

}



