package com.example.echoandlistapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Date;
// لو عندك Glide:
// import com.bumptech.glide.Glide;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ME = 1;
    private static final int TYPE_OTHER = 2;

    private final long myUserId;
    private final List<Message> data = new ArrayList<>();
    private final SimpleDateFormat inFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    private final SimpleDateFormat outFmt = new SimpleDateFormat("HH:mm", Locale.getDefault());

    public ChatAdapter(long myUserId) {
        this.myUserId = myUserId;
    }

    public void setData(List<Message> list){
        data.clear();
        if(list!=null) data.addAll(list);
        notifyDataSetChanged();
    }

    public int getItemCount(){ return data.size(); }

    @Override
    public int getItemViewType(int position) {
        Message m = data.get(position);
        return (m.sender_id == myUserId) ? TYPE_ME : TYPE_OTHER;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_ME){
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_msg_me, parent, false);
            return new MeVH(v);
        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_msg_other, parent, false);
            return new OtherVH(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder h, int position) {
        Message m = data.get(position);
        String time = formatTime(m.created_at);

        if(h instanceof MeVH){
            MeVH vh = (MeVH) h;
            vh.tvBody.setText(m.body);
            vh.tvTime.setText(time);
            loadAvatar(vh.ivAvatar, m.sender_id, true);
        } else {
            OtherVH vh = (OtherVH) h;
            vh.tvBody.setText(m.body);
            vh.tvTime.setText(time);
            loadAvatar(vh.ivAvatar, m.sender_id, false);
        }
    }

    private String formatTime(String createdAt){
        try {
            Date d = inFmt.parse(createdAt);
            return (d!=null) ? outFmt.format(d) : "";
        } catch (ParseException e) {
            return "";
        }
    }

    private void loadAvatar(ImageView iv, long senderId, boolean isMe){
        // مكان مناسب لتحميل صورة المرسِل:
        // لو عندك روابط صور حقيقية، استعمل Glide:
        // Glide.with(iv).load(url).circleCrop().into(iv);
        // حاليا بنحط صور من الموارد بحسب المرسل:

        int res;
        if(isMe){
            res = R.drawable.ic_avatar_me;     // أضف صورة بديل لك
        } else {
            // مثال: لو عندك 1 = طارق, 2 = أيمن, 3 = لينا…
            if(senderId == 1L)      res = R.drawable.ic_avatar_tariq;
            else if(senderId == 2L) res = R.drawable.ic_avatar_ayman;
            else if(senderId == 3L) res = R.drawable.ic_avatar_lina;
            else                    res = R.drawable.ic_avatar_other; // افتراضي
        }
        iv.setImageResource(res);
        // لو بدك دائري بدون Glide، خليه مستطيل مؤقتًا أو اعمل shape mask لاحقًا
    }

    // === ViewHolders ===
    static class MeVH extends RecyclerView.ViewHolder {
        TextView tvBody, tvTime;
        ImageView ivAvatar;
        MeVH(View v){
            super(v);
            tvBody = v.findViewById(R.id.tvBody);
            tvTime = v.findViewById(R.id.tvTime);
            ivAvatar = v.findViewById(R.id.ivAvatar);
        }
    }

    static class OtherVH extends RecyclerView.ViewHolder {
        TextView tvBody, tvTime;
        ImageView ivAvatar;
        OtherVH(View v){
            super(v);
            tvBody = v.findViewById(R.id.tvBody);
            tvTime = v.findViewById(R.id.tvTime);
            ivAvatar = v.findViewById(R.id.ivAvatar);
        }
    }
}
