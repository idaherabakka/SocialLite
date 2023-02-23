package com.example.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sociallite.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

    Context context;
    List<Challenge> challenges;

    public MyAdapter(Context context, List<Challenge> challenges) {
        this.context = context;
        this.challenges = challenges;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.challenge_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleView.setText(challenges.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return challenges.size();
    }
}
