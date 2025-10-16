package com.example.echoandlistapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.echoandlistapp.databinding.ActivityListNiceBinding;
import com.google.android.material.snackbar.Snackbar;
import java.util.Random;

public class ListNiceActivity extends AppCompatActivity {
    private ActivityListNiceBinding b;
    private SimpleAdapter adapter;
    private final Random rnd = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityListNiceBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        adapter = new SimpleAdapter((row, pos) -> {

            Snackbar.make(b.rv, "You selected: " + row.title, Snackbar.LENGTH_SHORT).show();
        });

        b.rv.setLayoutManager(new LinearLayoutManager(this));
        b.rv.setAdapter(adapter);

        for (int i = 1; i <= 5; i++) {
            adapter.add(new SimpleAdapter.Row("Item #" + i, "A simple and nice description"));
        }

        b.fab.setOnClickListener(v -> {
            int n = 100 + rnd.nextInt(900);
            adapter.add(new SimpleAdapter.Row("New Item " + n, "Added via FAB ✨"));
            b.rv.smoothScrollToPosition(0);
        });
    }
}
