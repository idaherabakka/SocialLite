package com.example.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Challenge;
import com.example.model.User;
import com.example.sociallite.R;

import java.util.List;

public class TeamProgressionAdapter extends RecyclerView.Adapter<TeamProgressionAdapter.TeamProgressionViewHolder> {
    Context context;
    List<Challenge> challenges;
    private final MyAdapterListener listener;
    User user;


    public TeamProgressionAdapter(Context context, List<Challenge> challenges, MyAdapterListener listener) {
        this.context = context;
        this.challenges = challenges;
        this.listener = listener;
        this.user = user;
    }

    public void filterList(List<Challenge> filterlist) {
        challenges = filterlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TeamProgressionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeamProgressionViewHolder(LayoutInflater.from(context).inflate(R.layout.join_challenge_view, parent, false),listener, user);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamProgressionViewHolder holder, int position) {
        holder.challengeID.setText(challenges.get(position).getID());
        holder.titleView.setText(challenges.get(position).getTitle());

        String id = (String) holder.challengeID.getText();
        if (user.getChallengesJoined().contains(id)) {
            holder.button.setText("Joined");
            holder.button.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return challenges.size();
    }
    public interface MyAdapterListener {
        void buttonOnClick(View v, int position, TextView id);
    }

    public static class TeamProgressionViewHolder extends RecyclerView.ViewHolder {
        TextView challengeID;
        TextView titleView;
        Button button;
        MyAdapterListener listener;
        User user;

        public TeamProgressionViewHolder(@NonNull View itemView, MyAdapterListener listener, User user) {
            super(itemView);
            this.titleView = itemView.findViewById(R.id.title);
            this.challengeID = itemView.findViewById(R.id.ID);
            this.listener = listener;
            this.button = (Button) itemView.findViewById(R.id.button);

            String id = (String) challengeID.getText();
            if (user.getChallengesJoined().contains(id)) {
                button.setText("Joined");
                button.setEnabled(false);
            }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.buttonOnClick(v, getAdapterPosition(), challengeID);
                    button.setText("Joined");
                    button.setEnabled(false);
                }
            });
        }
    }}