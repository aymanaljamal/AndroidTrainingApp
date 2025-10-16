package com.example.echoandlistapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.echoandlistapp.databinding.ItemRowBinding;
import java.util.ArrayList;
import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.VH> {

    public interface OnItemClickListener {
        void onItemClick(Row r, int position);
    }

    public static class Row {
        public final String title, subtitle;
        public Row(String t, String s){ title = t; subtitle = s; }
    }

    static class VH extends RecyclerView.ViewHolder {
        final ItemRowBinding b;
        VH(ItemRowBinding binding){
            super(binding.getRoot());
            this.b = binding;
        }
    }

    private final List<Row> data = new ArrayList<>();
    private final OnItemClickListener listener;

    public SimpleAdapter(OnItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRowBinding binding = ItemRowBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new VH(binding);
    }

    @Override public void onBindViewHolder(@NonNull VH h, int position) {
        Row r = data.get(position);
        h.b.tvTitle.setText(r.title);
        h.b.tvSubtitle.setText(r.subtitle);

        h.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(r, h.getBindingAdapterPosition());
        });
    }

    @Override public int getItemCount() { return data.size(); }

    public void add(Row r){
        data.add(0, r);
        notifyItemInserted(0);
    }
}
