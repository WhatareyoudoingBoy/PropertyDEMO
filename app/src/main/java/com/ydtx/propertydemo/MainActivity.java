package com.ydtx.propertydemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv;

    private Button touming,suofang,xuanzhuan,weiyi,zuheyi,zuheer,chongfuyi,chongfuer,yanse,paowuxian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);

        touming =(Button)findViewById(R.id.touming);
        touming.setOnClickListener(this);
        suofang =(Button)findViewById(R.id.button);
        suofang.setOnClickListener(this);
        xuanzhuan =(Button)findViewById(R.id.button2);
        xuanzhuan.setOnClickListener(this);
        weiyi=(Button)findViewById(R.id.button3);
        weiyi.setOnClickListener(this);
        zuheyi=(Button)findViewById(R.id.button4);
        zuheyi.setOnClickListener(this);
        zuheer=(Button)findViewById(R.id.button5);
        zuheer.setOnClickListener(this);
        chongfuyi=(Button)findViewById(R.id.button6);
        chongfuyi.setOnClickListener(this);
        chongfuer=(Button)findViewById(R.id.button7);
        chongfuer.setOnClickListener(this);
        yanse=(Button)findViewById(R.id.button8);
        yanse.setOnClickListener(this);
        paowuxian=(Button)findViewById(R.id.button9);
        paowuxian.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //透明
            case R.id.touming:
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv, "alpha", 0f, 1f);

                objectAnimator.setDuration(1000);

                objectAnimator.start();
                break;
            //缩放
            case R.id.button:
                /**动画组合**/

                PropertyValuesHolder objectAnimatorScaleX = PropertyValuesHolder.ofFloat("scaleX", 0f, 1f);

                PropertyValuesHolder objectAnimatorScaleY = PropertyValuesHolder.ofFloat("scaleY", 0f, 1f);

                /**同时播放两个动画**/

                ObjectAnimator.ofPropertyValuesHolder(iv, objectAnimatorScaleX, objectAnimatorScaleY).setDuration(1000).start();
                break;
            //旋转
            case R.id.button2:
                ObjectAnimator objectAnimatorScale = ObjectAnimator.ofFloat(iv, "rotation", 0f, 360f);

                objectAnimatorScale.setDuration(1000);

                objectAnimatorScale.start();
                break;
            //位移
            case R.id.button3:
                ObjectAnimator objectAnimatorTranslate = ObjectAnimator.ofFloat(iv, "translationX", 0f, 500f);

                objectAnimatorTranslate.setDuration(1000);

                objectAnimatorTranslate.start();
                break;
            //组合一
            case R.id.button4:
                AnimatorSet animatorSetGroup1 = new AnimatorSet();

                ObjectAnimator objectAnimatorScaleX1 = ObjectAnimator.ofFloat(iv, "scaleX", 0f, 1f);

                ObjectAnimator objectAnimatorScaleY1 = ObjectAnimator.ofFloat(iv, "scaleY", 0f, 1f);

                ObjectAnimator objectAnimatorRotateX1 = ObjectAnimator.ofFloat(iv, "rotationX", 0f, 360f);

                ObjectAnimator objectAnimatorRotateY1 = ObjectAnimator.ofFloat(iv, "rotationY", 0f, 360f);

                animatorSetGroup1.setDuration(1000);

                animatorSetGroup1.play(objectAnimatorScaleX1).with(objectAnimatorScaleY1)

                        .before(objectAnimatorRotateX1).before(objectAnimatorRotateY1);

                animatorSetGroup1.start();
                break;
            //组合二
            case R.id.button5:
                AnimatorSet animatorSetGroup2 = new AnimatorSet();

                ObjectAnimator objectAnimatorTranslate2 = ObjectAnimator.ofFloat(iv, "translationX", 0f, 500f);

                ObjectAnimator objectAnimatorRotateX2 = ObjectAnimator.ofFloat(iv, "rotationX", 0f, 360f);

                ObjectAnimator objectAnimatorRotateY2 = ObjectAnimator.ofFloat(iv, "rotationY", 0f, 360f);

                animatorSetGroup2.setDuration(1000);

                animatorSetGroup2.play(objectAnimatorTranslate2).after(objectAnimatorRotateX2)

                        .after(objectAnimatorRotateY2);

                animatorSetGroup2.start();
                break;
            //重复一
            case R.id.button6:
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(iv, "alpha", 0f, 1f);

                objectAnimator2.setDuration(500);

                objectAnimator2.setRepeatCount(3);

                objectAnimator2.start();
                break;
            //重复二
            case R.id.button7:
                ObjectAnimator objectAnimatorTranslate3 = ObjectAnimator.ofFloat(iv, "translationX", -50f, 50f);

                objectAnimatorTranslate3.setDuration(500);

                objectAnimatorTranslate3.setRepeatCount(3);

                objectAnimatorTranslate3.start();
                break;
            //动态改变背景颜色
            case R.id.button8:
                ObjectAnimator objectAnimatorBg = ObjectAnimator.ofInt(iv, "backgroundColor", Color.BLUE, Color.YELLOW, Color.RED);

                objectAnimatorBg.setDuration(3000);

                objectAnimatorBg.start();
                break;
            //抛物线
            case R.id.button9:
                parabola();
                break;

        }
    }

    private void parabola() {

        ValueAnimator valueAnimator = new ValueAnimator();

        valueAnimator.setDuration(3000);

        valueAnimator.setObjectValues(new PointF(0, 0));

        valueAnimator.setInterpolator(new LinearInterpolator());

        valueAnimator.setEvaluator(new TypeEvaluator<PointF>()

        {



            @Override

            public PointF evaluate(float fraction, PointF startValue,

                                   PointF endValue)

            {

                /**x方向200px/s ，则y方向0.5 * 200 * t**/

                PointF point = new PointF();

                point.x = 200 * fraction * 3;

                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);

                return point;

            }

        });



        valueAnimator.start();

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()

        {

            @Override

            public void onAnimationUpdate(ValueAnimator animation)

            {

                PointF point = (PointF) animation.getAnimatedValue();

                iv.setX(point.x);

                iv.setY(point.y);



            }

        });

    }
    }

