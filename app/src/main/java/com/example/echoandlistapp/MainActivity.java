package com.example.echoandlistapp;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.echoandlistapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding b;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.btnText.setOnClickListener(v -> startActivity(new Intent(this, TextEchoActivity.class)));
        b.btnSpinner.setOnClickListener(v -> startActivity(new Intent(this, SpinnerEchoActivity.class)));
        b.btnList.setOnClickListener(v -> startActivity(new Intent(this, ListNiceActivity.class)));
        b.btactvivty.setOnClickListener(v -> startActivity(new Intent(this, Activity.class)));
    }
}
