package com.elsa.checklist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elsa.checklist.databinding.ItemChecklistBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

public class ChecklistItemAdapter extends RecyclerView.Adapter<ChecklistItemAdapter.ViewHolder> {
    ArrayList<Map<String, Object>> mData;
    Context mContext;
    LayoutInflater mInflater;
    ClickListener mListener;

    public ChecklistItemAdapter(ArrayList<Map<String, Object>> mData, Context mContext, ClickListener mListener) {
        this.mData = mData;
        this.mContext = mContext;
        this.mListener = mListener;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemChecklistBinding.inflate(mInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ChecklistItemAdapter.ViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemChecklistBinding binding;

        public ViewHolder(@NonNull @NotNull ItemChecklistBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Map<String, Object> item) {
            String desc = (String) item.get("name");
            binding.label.setText(desc);
            itemView.setOnClickListener(view -> mListener.onClick(item));
        }
    }
}
