package com.example.echoandlistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.echoandlistapp.databinding.ActivityNewTextBinding;

public class Activity extends AppCompatActivity {


    private ActivityNewTextBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewTextBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = binding.editText1.getText().toString();
                String text2 = binding.editText2.getText().toString();
                Intent intent = new Intent(Activity.this, secondActivty.class);
                intent.putExtra("EXTRA_TEXT_1", text1);
                intent.putExtra("EXTRA_TEXT_2", text2);
                startActivity(intent);
            }
        });
    }
}
