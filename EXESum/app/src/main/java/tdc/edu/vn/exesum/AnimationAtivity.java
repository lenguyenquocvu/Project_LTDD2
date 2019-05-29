package tdc.edu.vn.exesum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;

public class AnimationAtivity extends AppCompatActivity {

    Button btnAnimate;
    CheckBox radRun,radRotate;
    ImageView imgTruck;
    AnimationSet animationSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
        animationSet = new AnimationSet(true);
        final RotateAnimation rotateAnimation = new RotateAnimation(0,360,50,50);
        rotateAnimation.setDuration(200);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setRepeatCount(Animation.INFINITE);

        final TranslateAnimation translateAnimation = new TranslateAnimation(0,200,0,0);
        translateAnimation.setDuration(200);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        translateAnimation.setRepeatCount(Animation.INFINITE);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            int repeatCount = 0;
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if(repeatCount%2 == 0){
                    imgTruck.setBackgroundResource(R.drawable.ic_truckreverse);
                }else{
                    imgTruck.setBackgroundResource(R.drawable.ic_truck);
                }
                repeatCount++;
            }
        });

        btnAnimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgTruck.clearAnimation();
                if(radRun.isChecked() && radRotate.isChecked()) {
                    animationSet = new AnimationSet(true);
                    animationSet.addAnimation(translateAnimation);
                    animationSet.addAnimation(rotateAnimation);
                }else if(!radRun.isChecked() || !radRotate.isChecked())
                {
                    if(radRotate.isChecked()){
                        animationSet = new AnimationSet(true);
                        animationSet.addAnimation(rotateAnimation);
                    }else if(radRun.isChecked()) {
                        animationSet = new AnimationSet(true);
                        animationSet.addAnimation(translateAnimation);
                    }else {
                        animationSet = new AnimationSet(true);
                    }
                }
                if(animationSet.getAnimations().size() != 0) {
                    imgTruck.startAnimation(animationSet);
                }
            }
        });


    }

    private void setControl() {
        btnAnimate = findViewById(R.id.btnAnimate);
        radRun = findViewById(R.id.radRun);
        radRotate = findViewById(R.id.radRotate);
        imgTruck = findViewById(R.id.imgTruck);
    }
}
