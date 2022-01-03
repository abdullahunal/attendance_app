package com.example.codeit.attendance.display;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeit.attendance.R;
import com.example.codeit.attendance.consepts.member.Member;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_name;
        public TextView txt_mac;
        public CardView card_view;

        public ViewHolder(View view) {
            super(view);

            card_view = (CardView)view.findViewById(R.id.card_view);
            txt_name = (TextView)view.findViewById(R.id.member_name);
            txt_mac = (TextView)view.findViewById(R.id.member_mac);
        }
    }

    List<Member> list_person;
    ICustomItemClickListener listener;
    public RecyclerViewAdapter(List<Member> list_person, ICustomItemClickListener listener) {

        this.list_person = list_person;
        this.listener = listener;
    }


    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, view_holder.getPosition());
            }
        });

        return view_holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txt_name.setText(list_person.get(position).getName());
        holder.txt_mac.setText(list_person.get(position).getMac());
    }

    @Override
    public int getItemCount() {
        return list_person.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
