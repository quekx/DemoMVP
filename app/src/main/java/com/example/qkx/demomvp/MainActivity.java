package com.example.qkx.demomvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.qkx.demomvp.welfaredetail.WelfareDetailActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnWelfare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWelfare = (Button) findViewById(R.id.btn_welfare);
        btnWelfare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WelfareDetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
