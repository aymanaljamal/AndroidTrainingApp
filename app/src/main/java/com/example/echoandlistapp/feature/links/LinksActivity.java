package com.example.echoandlistapp.feature.links;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.echoandlistapp.R;
import com.example.echoandlistapp.data.model.LinkItem;
import com.example.echoandlistapp.data.network.Api;
import com.example.echoandlistapp.data.network.ApiClient;

import java.util.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LinksActivity extends AppCompatActivity {
    private Spinner spTopics;
    private RecyclerView rv;
    private ProgressBar progress;
    private LinksAdapter adapter;
    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);

        spTopics = findViewById(R.id.spTopics);
        rv = findViewById(R.id.rvLinks);
        progress = findViewById(R.id.progress);

        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LinksAdapter(item -> {
            // فتح الرابط في المتصفح
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(item.url));
            startActivity(i);
        });
        rv.setAdapter(adapter);

        api = ApiClient.getClient().create(Api.class);

        loadTopicsAndLinks();
    }

    private void loadTopicsAndLinks() {
        progress.setVisibility(View.VISIBLE);

        api.getTopics().enqueue(new Callback<List<String>>() {
            @Override public void onResponse(Call<List<String>> call, Response<List<String>> res) {
                List<String> topics = new ArrayList<>();
                topics.add("الكل"); // خيار عام
                if (res.isSuccessful() && res.body()!=null) topics.addAll(res.body());

                ArrayAdapter<String> a = new ArrayAdapter<>(LinksActivity.this,
                        android.R.layout.simple_spinner_item, topics);
                a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTopics.setAdapter(a);

                spTopics.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        String t = topics.get(pos);
                        fetchLinks("الكل".equals(t) ? "" : t);
                    }
                    @Override public void onNothingSelected(AdapterView<?> parent) { fetchLinks(""); }
                });

                // أول تحميل
                fetchLinks("");
            }
            @Override public void onFailure(Call<List<String>> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Toast.makeText(LinksActivity.this, "Failed topics: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                // حتى لو فشل المواضيع، حمّل كل الروابط
                fetchLinks("");
            }
        });
    }

    private void fetchLinks(String topic) {
        progress.setVisibility(View.VISIBLE);
        api.getLinks(topic).enqueue(new Callback<List<LinkItem>>() {
            @Override public void onResponse(Call<List<LinkItem>> call, Response<List<LinkItem>> res) {
                progress.setVisibility(View.GONE);
                if (res.isSuccessful() && res.body()!=null) {
                    adapter.setData(res.body());
                } else {
                    Toast.makeText(LinksActivity.this, "Load failed", Toast.LENGTH_SHORT).show();
                }
            }
            @Override public void onFailure(Call<List<LinkItem>> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Toast.makeText(LinksActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
