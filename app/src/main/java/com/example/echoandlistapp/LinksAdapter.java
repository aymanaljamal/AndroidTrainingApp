package com.example.echoandlistapp;

import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

public class LinksAdapter extends RecyclerView.Adapter<LinksAdapter.VH> {

    public interface OnItemClick { void onClick(LinkItem item); }

    private final List<LinkItem> data = new ArrayList<>();
    private final OnItemClick onItemClick;

    public LinksAdapter(OnItemClick onItemClick) { this.onItemClick = onItemClick; }

    public void setData(List<LinkItem> items) {
        data.clear();
        if (items != null) data.addAll(items);
        notifyDataSetChanged();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvTitle, tvTopic;
        VH(View v){ super(v);
            tvTitle = v.findViewById(R.id.tvTitle);
            tvTopic = v.findViewById(R.id.tvTopic);
        }
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_link, parent, false);
        return new VH(v);
    }

    @Override public void onBindViewHolder(@NonNull VH h, int pos) {
        LinkItem item = data.get(pos);
        h.tvTitle.setText(item.title);   // نعرض الاسم فقط (وخلّيت الموضوع صغير تحت)
        h.tvTopic.setText(item.topic);
        h.itemView.setOnClickListener(v -> onItemClick.onClick(item));
    }

    @Override public int getItemCount() { return data.size(); }
}
