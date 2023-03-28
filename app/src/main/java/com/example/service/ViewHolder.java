package com.example.service;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sociallite.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView titleView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.name);
    }
}
