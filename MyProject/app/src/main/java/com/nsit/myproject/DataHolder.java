package com.nsit.myproject;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DataHolder extends RecyclerView.ViewHolder {
    View v;
    public DataHolder(@NonNull View itemView) {
        super(itemView);
        v=itemView;
    }
    public void setView(Context context, int id, String name, String add, String branch, int age)
    {
        TextView sname=v.findViewById(R.id.name);
        TextView sage=v.findViewById(R.id.age);
        TextView sadd=v.findViewById(R.id.add);
        TextView sbranch=v.findViewById(R.id.branch);
        TextView sid=v.findViewById(R.id.sid);
        sname.setText(name);
        sage.setText(String.valueOf(age));
        sid.setText(String.valueOf(id));
        sbranch.setText(branch);
        sadd.setText(add);

    }
}
