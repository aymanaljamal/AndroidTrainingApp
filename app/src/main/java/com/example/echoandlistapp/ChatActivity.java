package com.example.echoandlistapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView rv;
    private EditText etMessage;
    private ImageButton btnSend;
    private ChatAdapter adapter;

    private Api api;
    private long chatId = 1L;   // غيّرها حسب المحادثة
    private long myUserId = 2L; // المستخدم الحالي

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        rv = findViewById(R.id.rvMessages);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setStackFromEnd(true);
        rv.setLayoutManager(lm);

        adapter = new ChatAdapter(myUserId);
        rv.setAdapter(adapter);

        api = ApiClient.getClient().create(Api.class);

        loadMessages();

        btnSend.setOnClickListener(v -> {
            String text = etMessage.getText().toString().trim();
            if (text.isEmpty()) return;

            api.sendMessage(chatId, myUserId, text).enqueue(new Callback<ApiResponse>() {
                @Override public void onResponse(Call<ApiResponse> call, Response<ApiResponse> res) {
                    if (res.isSuccessful() && res.body()!=null && "success".equalsIgnoreCase(res.body().status)) {
                        etMessage.setText("");
                        loadMessages();
                    } else {
                        String err = "Send failed";
                        try { if (res.errorBody()!=null) err = res.errorBody().string(); } catch (IOException ignored) {}
                        Toast.makeText(ChatActivity.this, err, Toast.LENGTH_SHORT).show();
                    }
                }
                @Override public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Toast.makeText(ChatActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }

    private void loadMessages() {
        api.getMessages(chatId).enqueue(new Callback<List<Message>>() {
            @Override public void onResponse(Call<List<Message>> call, Response<List<Message>> res) {
                if (res.isSuccessful() && res.body()!=null) {
                    adapter.setData(res.body());
                    if (adapter.getItemCount() > 0) rv.scrollToPosition(adapter.getItemCount()-1);
                } else {
                    String err = "Load failed";
                    try { if (res.errorBody()!=null) err = res.errorBody().string(); } catch (IOException ignored) {}
                    Toast.makeText(ChatActivity.this, err, Toast.LENGTH_SHORT).show();
                }
            }
            @Override public void onFailure(Call<List<Message>> call, Throwable t) {
                Toast.makeText(ChatActivity.this, "Failed to load: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
