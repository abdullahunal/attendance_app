package com.example.codeit.attendance.display;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.codeit.attendance.R;

import java.util.List;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private final Context context;
    private final List<String> items;

    public DisplayAdapter(Context context, List<String> items) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.user_item_list, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.serial_number.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView serial_number;

        public MyViewHolder(View itemView) {
            super(itemView);
            serial_number = (TextView) itemView.findViewById(R.id.rvConnectedDevices);
        }
    }
}