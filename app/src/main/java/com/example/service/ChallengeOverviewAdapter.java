package com.example.service;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Challenge;
import com.example.sociallite.R;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.List;

public class ChallengeOverviewAdapter extends RecyclerView.Adapter<ChallengeOverviewAdapter.OverviewViewHolder> {

    Context context;
    List<Challenge> challenges;
    private final MyAdapterListener listener;

    public ChallengeOverviewAdapter(Context context, List<Challenge> challenges, MyAdapterListener listener) {
        this.context = context;
        this.challenges = challenges;
        this.listener = listener;
    }

    public static JoinChallengeAdapter.MyAdapterListener onClickListener;
    public interface MyAdapterListener {
        void buttonOnClick(View v, int position, TextView id);
    }

    @NonNull
    @Override
    public OverviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OverviewViewHolder(LayoutInflater.from(context).inflate(R.layout.overview_challenge_view, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull OverviewViewHolder holder, int position) {
        holder.challengeID.setText(challenges.get(position).getID());
        holder.titleView.setText(challenges.get(position).getTitle());
    }


    @Override
    public int getItemCount() {
        return challenges.size();
    }

    public static class OverviewViewHolder extends RecyclerView.ViewHolder{
        TextView titleView;
        TextView challengeID;
        Button button;
        MyAdapterListener listener;


        public OverviewViewHolder(@NonNull View itemView, MyAdapterListener listener) {
            super(itemView);
            this.titleView = itemView.findViewById(R.id.button);
            this.listener = listener;
            this.button = (Button) itemView.findViewById(R.id.button);
            this.challengeID = itemView.findViewById(R.id.ID);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.buttonOnClick(v, getAdapterPosition(), challengeID);
                }
            });
        }
    }
}
