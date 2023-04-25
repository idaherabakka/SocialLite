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

public class ChallengeOverviewAdapter extends RecyclerView.Adapter<ChallengeOverviewAdapter.OverviewViewHolder> {

    Context context;
    List<Challenge> challenges;
    private final MyAdapterListener listener;

    public ChallengeOverviewAdapter(Context context, List<Challenge> challenges, MyAdapterListener listener) {
        this.context = context;
        this.challenges = challenges;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OverviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OverviewViewHolder(LayoutInflater.from(context).inflate(R.layout.overview_challenge_view, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull OverviewViewHolder holder, int position) {
        holder.titleView.setText(challenges.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return challenges.size();
    }

    public static JoinChallengeAdapter.MyAdapterListener onClickListener;

    public interface MyAdapterListener {
        void buttonOnClick(View v, int position, TextView id);
    }
    public static class OverviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView titleView;
        Button button;
        WeakReference<MyAdapterListener> listenerRef;

        public OverviewViewHolder(@NonNull View itemView, MyAdapterListener listener) {
            super(itemView);
            titleView = itemView.findViewById(R.id.button);
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
            //listenerRef.get().buttonOnClick(v, getAdapterPosition(), challengeID);
        }
    }
}
