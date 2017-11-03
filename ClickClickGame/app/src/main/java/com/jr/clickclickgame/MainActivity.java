package com.jr.clickclickgame;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    LinearLayout layout;
    TextView text;
    ImageView[] imgs=new ImageView[12];
    ImageView start;
    Random ran;
    int cnt=0;
    int startnum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout=(LinearLayout)findViewById(R.id.layout);
        text=(TextView)findViewById(R.id.text);
        start=(ImageView)findViewById(R.id.start);

    }//onCreate Method...

    public void clickImg(View v){
        cnt++;
        if (Integer.parseInt(v.getTag().toString())==cnt)   { v.setVisibility(View.INVISIBLE);
            if (Integer.parseInt(v.getTag().toString())>=12) {
                cnt=0;
                start.setClickable(true); start.setImageResource(R.drawable.start);
                for(int i=0;i<12;i++) {
                    imgs[i].setClickable(true);
                }
                if(startnum==3){
                    layout.setBackgroundResource(R.drawable.bg4);
                    text.setText("THE END...");
                    start.setVisibility(View.INVISIBLE);
                }

            }
        }else cnt--;
    }//게임Button눌렀을경우 응답값

    public void clickStart(View v){
        int imgnum[]=new int[12];
        switch(startnum){
            case 0:
                text.setText("Start Click Button : 1~12");
                start.setImageResource(R.drawable.ing);
                v.setClickable(false);
                startnum++;
                //랜덤값
                ran=new Random();
                for(int i=0;i<12;i++) {
                    imgnum[i] = ran.nextInt(12);
                    for (int k = 0; k < i; k++) {
                        if (imgnum[k] == imgnum[i]) i--;
                    }
                }//id지정 및 tag지정
                for(int i=0;i<12;i++){
                    imgs[i]=(ImageView)findViewById(R.id.img01+i);
                    imgs[i].setImageResource(R.drawable.num01+imgnum[i]);
                    imgs[i].setTag(imgnum[i]+1);
                    imgs[i].setVisibility(View.VISIBLE);
                }break;
            case 1:
                layout.setBackgroundResource(R.drawable.bg2);
                start.setImageResource(R.drawable.ing);
                v.setClickable(false);
                startnum++;
                text.setText("Start Click Button : A~L");
                //랜덤값
                ran=new Random();
                for(int i=0;i<12;i++) {
                    imgnum[i] = ran.nextInt(12);
                    for (int k = 0; k < i; k++) {
                        if (imgnum[k] == imgnum[i]) i--;
                    }
                }//id지정 및 tag지정
                for(int i=0;i<12;i++){
                    imgs[i]=(ImageView)findViewById(R.id.img01+i);
                    imgs[i].setImageResource(R.drawable.alpa01+imgnum[i]);
                    imgs[i].setTag(imgnum[i]+1);
                    imgs[i].setVisibility(View.VISIBLE);
                }break;
            case 2:
                layout.setBackgroundResource(R.drawable.bg3);
                start.setImageResource(R.drawable.ing);
                v.setClickable(false);
                startnum++;
                text.setText("Start Click Button : MOUSE~PIG");
                //랜덤값
                ran=new Random();
                for(int i=0;i<12;i++) {
                    imgnum[i] = ran.nextInt(12);
                    for (int k = 0; k < i; k++) {
                        if (imgnum[k] == imgnum[i]) i--;
                    }
                }//id지정 및 tag지정
                for(int i=0;i<12;i++){
                    imgs[i]=(ImageView)findViewById(R.id.img01+i);
                    imgs[i].setImageResource(R.drawable.cha01+imgnum[i]);
                    imgs[i].setTag(imgnum[i]+1);
                    imgs[i].setVisibility(View.VISIBLE);
                }break;
        }
    }//start누를 시 응답값

}//MainActivity Class...
