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

import java.lang.ref.WeakReference;
import java.util.List;

public class JoinChallengeAdapter extends RecyclerView.Adapter<JoinChallengeAdapter.JoinViewHolder> {
    Context context;
    List<Challenge> challenges;
    private final ClickListener listener;


    public JoinChallengeAdapter(Context context, List<Challenge> challenges, ClickListener listener) {
        this.context = context;
        this.challenges = challenges;
        this.listener = listener;
    }

    @NonNull
    @Override
    public JoinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JoinViewHolder(LayoutInflater.from(context).inflate(R.layout.join_challenge_view, parent, false),listener);
    }

    @Override
    public void onBindViewHolder(@NonNull JoinViewHolder holder, int position) {
        holder.titleView.setText(challenges.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return challenges.size();
    }

    public static class JoinViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        TextView titleView;
        Button button;
        WeakReference<ClickListener> listenerRef;

        public JoinViewHolder(@NonNull View itemView, ClickListener listener) {
            super(itemView);
            titleView = itemView.findViewById(R.id.title);
            listenerRef = new WeakReference<>(listener);
            button = (Button) itemView.findViewById(R.id.button);
        }

        // onClick Listener for view
        @Override
        public void onClick(View v) {
            if (v.getId() == button.getId()) {
                //
            } else {
                //
            }

            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }
}
