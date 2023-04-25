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
import com.example.sociallite.R;

import java.util.List;

public class JoinChallengeAdapter extends RecyclerView.Adapter<JoinChallengeAdapter.JoinViewHolder> {
    Context context;
    List<Challenge> challenges;
    private final MyAdapterListener listener;


    public JoinChallengeAdapter(Context context, List<Challenge> challenges, MyAdapterListener listener) {
        this.context = context;
        this.challenges = challenges;
        this.listener = listener;
    }

    public void filterList(List<Challenge> filterlist) {
        challenges = filterlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public JoinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JoinViewHolder(LayoutInflater.from(context).inflate(R.layout.join_challenge_view, parent, false),listener);
    }

    @Override
    public void onBindViewHolder(@NonNull JoinViewHolder holder, int position) {
        holder.challengeID.setText(challenges.get(position).getID());
        holder.titleView.setText(challenges.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return challenges.size();
    }

    public static MyAdapterListener onClickListener;

    public interface MyAdapterListener {
        void buttonOnClick(View v, int position, TextView id);
    }

    public static class JoinViewHolder extends RecyclerView.ViewHolder {
        TextView challengeID;
        TextView titleView;
        Button button;
        MyAdapterListener listener;

        public JoinViewHolder(@NonNull View itemView, MyAdapterListener listener) {
            super(itemView);
            this.titleView = itemView.findViewById(R.id.title);
            this.challengeID = itemView.findViewById(R.id.ID);
            this.listener = listener;
            this.button = (Button) itemView.findViewById(R.id.button);
            //button.setOnClickListener(this);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.buttonOnClick(v, getAdapterPosition(), challengeID);
                    button.setText("Joined");
                }
            });
        }
    }
}
