package com.example.trendgraphics;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class customerAdapter extends RecyclerView.Adapter<customerAdapter.ViewHolder> {
    private List<Customernew> customerList;

    public customerAdapter(List<Customernew> customerList) {
        this.customerList = customerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_customer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Customernew customer = customerList.get(position);
        holder.email.setText(customer.getEmail());
        holder.description.setText(customer.getDescription());
        holder.amount.setText(customer.getAmount());
    }

    @Override
    public int getItemCount() { return customerList.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView email, description, amount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.customer_email);
            description = itemView.findViewById(R.id.customer_description);
            amount = itemView.findViewById(R.id.customer_amount);
        }
    }
}
