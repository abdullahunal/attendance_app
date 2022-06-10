package com.example.codeit.attendance.persistence;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeit.attendance.R;
import com.example.codeit.attendance.consepts.member.Member;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {

    List<Member> members;
    Context context;
    DBHelper dbHelper;

    public MemberAdapter(List<Member> members, Context context) {
        this.members = members;
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,
            @SuppressLint("RecyclerView") int position) {

        final Member member = members.get(position);

        holder.editText_Name.setText(member.getName());
        holder.editText_Mac.setText(member.getMac());

        holder.button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = holder.editText_Name.getText().toString();
                String stringMac = holder.editText_Mac.getText().toString();

                dbHelper.updateUser(new Member(member.getId(), stringName, stringMac));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.button_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteUser(member.getId());
                members.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        EditText editText_Name;
        EditText editText_Mac;
        Button button_Edit;
        Button button_Delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            editText_Name = itemView.findViewById(R.id.edittext_name);
            editText_Mac = itemView.findViewById(R.id.edittext_mac);
            button_Delete = itemView.findViewById(R.id.button_delete);
            button_Edit = itemView.findViewById(R.id.button_edit);

        }
    }
}
