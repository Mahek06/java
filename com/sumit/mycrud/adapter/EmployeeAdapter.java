package com.sumit.mycrud.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.sumit.mycrud.ApiCalls;
import com.sumit.mycrud.FormActivity;
import com.sumit.mycrud.R;
import com.sumit.mycrud.model.Employee;

import java.io.IOException;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>{
    List<Employee> employeeList = null;
    Context context;
    ApiCalls apiCalls;

    public EmployeeAdapter(List<Employee> employeeList, AppCompatActivity activity) {
        this.employeeList = employeeList;
        this.context = activity;
        this.apiCalls = new ApiCalls();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.card, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, int position) {
        final Employee employee = employeeList.get(position);
        holder.name.setText(employee.getEmp_name());
        holder.gender.setText(employee.getEmp_gender());
        holder.salary.setText(String.valueOf(employee.getSalary()));
        if(employee.getEmp_status().equals("ACTIVE")){
            holder.edit.setVisibility(View.VISIBLE);
            holder.delete.setForeground(context.getDrawable(android.R.drawable.ic_menu_close_clear_cancel));
            holder.edit.setOnClickListener(view -> {
                Intent intent = new Intent(context, FormActivity.class);
                intent.putExtra("Employee",employee);
                context.startActivity(intent);
            });
            holder.delete.setOnClickListener(view -> {
                try {
                    apiCalls.callapi("delete",(AppCompatActivity) context,employee.getEmp_id(),null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }else{
            holder.edit.setVisibility(View.INVISIBLE);
            holder.delete.setForeground(context.getDrawable(android.R.drawable.ic_menu_add));
            holder.delete.setOnClickListener(view -> {
                try {
                    apiCalls.callapi("activate",(AppCompatActivity) context,employee.getEmp_id(),null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,salary,gender;
        public ImageButton edit,delete;
        public ViewHolder(View itemView) {
            super(itemView);
            this.edit = itemView.findViewById(R.id.edit);
            this.delete = itemView.findViewById(R.id.delete);
            this.name =  itemView.findViewById(R.id.name);
            this.salary =  itemView.findViewById(R.id.salary);
            this.gender =  itemView.findViewById(R.id.gender);
        }
    }

}
