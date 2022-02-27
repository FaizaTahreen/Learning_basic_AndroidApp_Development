package com.nsit.myproject;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

public class BirdDataHandler extends RecyclerView.ViewHolder {
    View v;
    public BirdDataHandler(@NonNull View itemView) {

        super(itemView);
        v=itemView;
    }
    public void setView(Context context, String title, String img)
    {
        ImageView iv=v.findViewById(R.id.image);
        TextView name=v.findViewById(R.id.title);
        name.setText(title);
        Picasso.get().load(img).into(iv);
        Animation animation= AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
        iv.setAnimation(animation);
    }
}
