package com.example.oppari2.frame.canvas.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.oppari2.R;
import com.example.oppari2.frame.development.Dev;

public class CommonListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView title;
    private static final String sender = "CommonListHolder";
    private ListClickListener listener;

    public CommonListHolder(@NonNull View itemView, ListClickListener listener) {
        super(itemView);
        itemView.setOnClickListener(this);
        title = (TextView) itemView.findViewById(R.id.item_common_list_text);
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        Dev.m(sender, "OnClick " + String.valueOf(getAdapterPosition()));
        if (listener != null) {
            listener.onClick(v, getAdapterPosition());
        }
    }
}
