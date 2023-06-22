package com.example.asii;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataItemAdapter extends RecyclerView.Adapter<DataItemAdapter.ViewHolder> {
    private List<DataItem> dataItems;

    public DataItemAdapter(List<DataItem> dataItems) {
        this.dataItems = dataItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataItem dataItem = dataItems.get(position);
        holder.bind(dataItem);
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewData;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewData = itemView.findViewById(R.id.textViewData);
        }

        public void bind(DataItem dataItem) {
            String result = "Q1: " + dataItem.getAnswer1() + " | " +
                    "Q2: " + dataItem.getAnswer2() + " | " +
                    "Q3: " + dataItem.getAnswer3() + " | " +
                    "Result: " + dataItem.getResult();
          //  textViewData.setText(result);
        }
    }
}