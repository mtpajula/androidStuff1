package com.example.oppari2.frame.canvas.list;

// https://medium.com/@orafaaraujo/lists-with-recyclerview-8eb9d9e84149

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CommonListAdapter extends RecyclerView.Adapter<CommonListHolder> {

    private static final String sender = "CommonListAdapter";
    private final ArrayList<String> mUsers;
    private int itemLayout;
    private ListClickListener listener;

    public CommonListAdapter(ArrayList users, int itemLayout) {
        mUsers = users;
        this.itemLayout = itemLayout;
    }

    public void setListClickListener(ListClickListener listener) {
        this.listener = listener;
    }

    @Override
    public CommonListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommonListHolder(LayoutInflater.from(parent.getContext())
                .inflate(itemLayout, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(CommonListHolder holder, int position) {
        holder.title.setText(mUsers.get(position));
        //holder.moreButton.setOnClickListener(view -> updateItem(position));
        //holder.deleteButton.setOnClickListener(view -> removerItem(position));
    }

    @Override
    public int getItemCount() {
        return mUsers != null ? mUsers.size() : 0;
    }

    public void updateList(String user) {
        insertItem(user);
    }

    private void insertItem(String user) {
        mUsers.add(user);
        notifyItemInserted(getItemCount());
    }
}