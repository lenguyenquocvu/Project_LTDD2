package com.example.exe4;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class SecondActivity extends AppCompatActivity {
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setControl();
        setEvent();
    }

    private void setControl() {
        linearLayout = (LinearLayout) findViewById(R.id.activity_second);
    }

    private void setEvent() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);


        ValueAnimator objectAnimator = ObjectAnimator.ofObject(linearLayout, "backgroundColor", new ArgbEvaluator(),
                ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, R.color.colorPrimaryDark));
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.setDuration(600);  // default is 300.
        objectAnimator.start();
    }
}
