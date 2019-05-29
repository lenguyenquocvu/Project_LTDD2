package tdc.edu.vn.exesum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnexe1,btnexe2,btnexe3,btnexe4,btnexe5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnexe3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AnimationAtivity.class);
                startActivity(intent);
            }
        });
        btnexe4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MusicActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        btnexe1 = findViewById(R.id.btnEXE1);
        btnexe2 = findViewById(R.id.btnEXE2);
        btnexe3 = findViewById(R.id.btnEXE3);
        btnexe4 = findViewById(R.id.btnEXE4);
        btnexe5 = findViewById(R.id.btnEXE5);
    }

}
