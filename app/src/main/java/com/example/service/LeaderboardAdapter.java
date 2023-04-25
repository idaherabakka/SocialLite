package com.example.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Points;
import com.example.sociallite.R;

import java.lang.ref.WeakReference;
import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.JoinViewHolder> {
    Context context;
    List<Points> points;


    public LeaderboardAdapter(Context context, List<Points> points ) {
        this.context = context;
        this.points = points;
    }

    @NonNull
    @Override
    public LeaderboardAdapter.JoinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LeaderboardAdapter.JoinViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_leaderboard, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardAdapter.JoinViewHolder holder, int position) {
        holder.titleView.setText(String.format("My name is %s and I'm %d years old.", points.get(position).getUserId(), points.get(position).getChallengePoints()));
    }

    @Override
    public int getItemCount() {
        return points.size();
    }

    public static class JoinViewHolder extends RecyclerView.ViewHolder{
        TextView titleView;
        Button button;
        WeakReference<ClickListener> listenerRef;

        public JoinViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.title);
            //listenerRef = new WeakReference<>(listener);
        }

    }
}
