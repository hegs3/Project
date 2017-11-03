package com.jr.finenews;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.youtube.player.YouTubePlayerSupportFragment;


/**
 * Created by alfo6-6 on 2017-08-23.
 */

public class CustomLayout extends RelativeLayout{

    Context context;
//    RelativeLayout viewlayout;
    int mGetDownX;
    int mDistanceX;//뷰의
    int mTouchDownX;//이벤트 발생 좌표

    int mGetDownY;
    int mDistanceY;//뷰의
    int mTouchDownY;//이벤트 발생 좌표

    int width;
    int height;//디스플레이 크기

    float hRate;//이미지가 어느정도 왔는지의 비율
    float rRate;//height에 대한 실제 비율1~0
    float textRate;
    float fRate;
    int x;

    boolean isMiniSize=false;
    boolean isMiniSized=false;
    boolean isActionUp=false;
    boolean isDraged=false;



    CustomLayout cm;
    LinearLayout text;
    RelativeLayout player;
    View fragment2Bottom;
    YouTubePlayerSupportFragment yf;
    FragmentManager fm;
    GestureDetector gd;
    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }
    public void getData(int width,int height,View fragment2Bottom, YouTubePlayerSupportFragment yf,FragmentManager fm){
        this.fm=fm;
        this.yf=yf;
        this.width=width;
        this.height=height;
        this.fragment2Bottom=fragment2Bottom;
        cm=(CustomLayout) findViewById(R.id.layout);
        text=(LinearLayout)findViewById(R.id.text_layout);

        player= (RelativeLayout) findViewById(R.id.test1234);



        gd=new GestureDetector(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                if(isMiniSized){
                    actUp(cm);
                }
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

                return false;
            }
            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                return false;
            }
        });
        cm=this;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        gd.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(isActionUp){
                    return false;
                }
                if(isMiniSize){
                    return false;
                }

                cm.setPivotX(0);                            //고정 x좌표
                text.setPivotY(0);                          //텍스트 고정 y좌표

                mGetDownY=(int)event.getY();
                mTouchDownY =(int)event.getRawY();          //이벤트가 발생한 곳의 touch 좌표(진짜 좌표)
                mDistanceY = mTouchDownY-(int)cm.getY();     //이벤트가 발생한 곳의 touch 좌표에서 view의 y좌표를 빼므로서 좌표의 흔들림을 막아줌

                mGetDownX=(int)event.getX();
                mTouchDownX =(int)event.getRawX();          //이벤트가 발생한 곳의 touch 좌표(진짜 좌표)
                mDistanceX = mTouchDownX-(int)cm.getX();     //이벤트가 발생한 곳의 touch 좌표에서 view의 y좌표를 빼므로서 좌표의 흔들림을 막아줌


                break;
            case MotionEvent.ACTION_MOVE:





                fRate=1-cm.getX()/(width/2);
                hRate=1-cm.getY()/height;
                rRate=1-(cm.getY()/(height-player.getHeight()));
                textRate=1-((cm.getY()*6)/(height-player.getHeight()));
                if (isMiniSized&&mTouchDownY<=event.getY()+20){//터치 다운한 지점보다 move한지점

                    if (isMiniSized) {
                        int x= (int)event.getRawX();
                        int dx= x- mTouchDownX;
                        mTouchDownX= x;
                        cm.setX(cm.getX()+dx);
                        if(cm.getX()<=0)cm.setX(0);
                        cm.setAlpha(fRate);
                        if(cm.getAlpha()<=0)cm.setAlpha(0);
                        cm.setY(height-player.getHeight());
                        if(cm.getY()>=height-player.getHeight()){
                            isDraged=true;
                        }
                    }
                    else actCancel(cm);
                }else if(!isDraged){
                    cm.setScaleX(hRate); //view의 x크기 조절
                    cm.setScaleY(hRate); //view의 y크기 조절
                    cm.setY(event.getRawY()-mDistanceY); //view의 y좌표 세팅

                    text.setAlpha(textRate);
                    fragment2Bottom.setAlpha(rRate);
                    text.setRotationX((-(1-hRate)*90)*10);
                    if((-(1-hRate)*90)*10<=-90&&text.getVisibility()==View.VISIBLE) {text.setVisibility(View.VISIBLE);text.setRotationX(-90);}
                    else if((-(1-hRate)*90)*10>-90&&text.getVisibility()==View.INVISIBLE) text.setVisibility(View.VISIBLE);
                }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////


                if(rRate>=0.5){isMiniSize=false;}
                else if(rRate<0.5){
                    isMiniSize=true;
                    if(event.getY()<0){isMiniSized=false;}
                }


                if(cm.getY()+player.getHeight()>=height){
                    cm.setY(height-player.getHeight());
                }
                if(cm.getY()<0) {cm.setY(0);}
                break;



            //////////////////////////////////////////////////////////////////////////////////////
            case MotionEvent.ACTION_UP:
                if(rRate>=0.5){
                    actUp(cm);
                } else if(rRate<0.5){
                    if(isMiniSize&&!isMiniSized){
                        text.setVisibility(View.VISIBLE);
                        actDown(cm);
                    }else if(isMiniSized){
                        if(fRate<=0.5) {
                            actDel(cm);


                        }else actCancel(cm);
                    }
                }
                break;

        }
        return false;
    }


    public void actDel(final View v){

        ObjectAnimator alpha=ObjectAnimator.ofFloat(v,"alpha",v.getAlpha(),0.0f);
        alpha.setRepeatCount(0);
        alpha.setDuration(300);
        ObjectAnimator tran=ObjectAnimator.ofFloat(v,"translationX",v.getX(),width);
        tran.setRepeatCount(0);
        tran.setDuration(300);


        AnimatorSet ani=new AnimatorSet();
        ani.play(alpha);
        ani.play(tran);
        ani.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                v.setVisibility(View.GONE);
//                v.setScaleX(1);
//                v.setScaleY(1);
//                v.setX(0);
//                v.setY(0);
//                v.setAlpha(1);

//                v.setVisibility(View.GONE);


            }
            @Override
            public void onAnimationCancel(Animator animation) {
            }
            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        ani.start();
    }
    public void actCancel(View v){



        ObjectAnimator alpha=ObjectAnimator.ofFloat(v,"alpha",v.getAlpha(),1.0f);
        alpha.setRepeatCount(0);
        alpha.setDuration(300);
        ObjectAnimator tran=ObjectAnimator.ofFloat(v,"translationX",v.getX(),0);
        tran.setRepeatCount(0);
        tran.setDuration(300);

        AnimatorSet ani=new AnimatorSet();
        ani.play(alpha);
        ani.play(tran);
        ani.start();
        isDraged=false;



    }
    public void actUp(View v){

        v.setPivotX(0);
        v.setY(0);//목적지
        v.setScaleX(1);//목적지의 사이즈
        v.setScaleY(1);

        ObjectAnimator rotate=ObjectAnimator.ofFloat(text,"rotationX",text.getRotationX(),0.0f);
        rotate.setRepeatCount(0);
        rotate.setDuration(500);
        ObjectAnimator alpha=ObjectAnimator.ofFloat(text,"alpha",text.getAlpha(),1.0f);
        alpha.setRepeatCount(0);
        alpha.setDuration(500);
        ObjectAnimator alphaBack=ObjectAnimator.ofFloat(fragment2Bottom,"alpha",fragment2Bottom.getAlpha(),1.0f);
        alpha.setRepeatCount(0);
        alpha.setDuration(500);

        AnimatorSet anit=new AnimatorSet();
        anit.play(rotate);
        anit.play(alpha);
        anit.play(alphaBack);
        anit.start();


        ScaleAnimation ani=new ScaleAnimation(hRate,1,hRate,1,0,height+player.getHeight());
        ani.setDuration(500);
        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isActionUp=true;
                isMiniSized=false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isActionUp=false;
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(ani);

    }
    public void actDown(final View v){
        v.setPivotX(0);
        v.setScaleX(1);
        v.setScaleY(1);

        ObjectAnimator alphaBack=ObjectAnimator.ofFloat(fragment2Bottom,"alpha",fragment2Bottom.getAlpha(),0.0f);
        alphaBack.setRepeatCount(0);
        alphaBack.setDuration(500);
        alphaBack.start();
        ScaleAnimation scale=new ScaleAnimation(hRate,0.422f,hRate,0.422f,0,v.getY()+player.getHeight());
        TranslateAnimation trans=new TranslateAnimation(0,0,0,(height-player.getHeight())*rRate);
        AnimationSet setAnimation= new AnimationSet(false);
        setAnimation.addAnimation(scale);
        setAnimation.addAnimation(trans);
        setAnimation.setDuration(500);
        setAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isActionUp=true;
                isMiniSized=true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isActionUp=false;
                v.clearAnimation();
                v.setY(height-player.getHeight());
                v.setScaleX(0.422f);
                v.setScaleY(0.422f);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(setAnimation);


    }



    public void onAirCreate(){
        fragment2Bottom.setVisibility(VISIBLE);
        cm.setVisibility(VISIBLE);
        yf.getView().setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 320, getResources().getDisplayMetrics())));


        if(!yf.isVisible()) {

            FragmentTransaction fmt=fm.beginTransaction();
            fmt.add(R.id.test1234,yf).addToBackStack(null).commit();
        }



        actCancel(cm);
        actUp(cm);
        cm.setX(0);
        cm.setY(0);
    }
    public void onAirDelete(){
        actDel(cm);
        fragment2Bottom.setVisibility(INVISIBLE);


    }

}
