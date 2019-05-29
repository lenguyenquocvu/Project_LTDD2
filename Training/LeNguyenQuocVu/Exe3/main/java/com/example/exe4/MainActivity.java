package com.example.exe4;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btn_change;
    private Button btn_move;
    boolean clicked = false;
    private Button btn_add;
    private LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
        container.setLayoutTransition(layoutTransition);
        final Intent changeActivity = new Intent(MainActivity.this, SecondActivity.class);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(changeActivity);
            }
        });

        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!clicked){
                    btn_move.animate().translationX(400).withLayer();
                    clicked = true;
                } else {
                    btn_move.animate().translationX(0).withLayer();
                    clicked = false;
                }
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.addView(new Button(MainActivity.this));
            }
        });
    }

    private void setControl() {
        btn_change=(Button)findViewById(R.id.btn_change);
        btn_move=(Button)findViewById(R.id.btn_move);
        btn_add=(Button)findViewById(R.id.btn_add);
        container=(LinearLayout)findViewById(R.id.activity_main);
    }
}
