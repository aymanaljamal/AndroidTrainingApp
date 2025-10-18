package com.example.echoandlistapp.feature.playground;
import android.content.Intent;
import android.os.Bundle;import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.echoandlistapp.R;

public class secondActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_print);
        Intent intent = getIntent();
        String text1 = intent.getStringExtra("EXTRA_TEXT_1");
        String text2 = intent.getStringExtra("EXTRA_TEXT_2");
        TextView textView1 = findViewById(R.id.textView221);
        TextView textView2 = findViewById(R.id.textView22);
        textView1.setText(text1);
        textView2.setText(text2);
    }
}
