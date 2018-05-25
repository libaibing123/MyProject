package com.example.myrikao0517.ui.classfiy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.myrikao0517.bean.ProductsBean;

import java.util.List;

public class XrvListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ProductsBean.DataBean> list;
    private LayoutInflater inflater;

    private OnListItemClickListener onListItemClickListener;

    public interface OnListItemClickListener {
        void OnItemClick(ProductsBean.DataBean dataBean);
    }

    public XrvListAdapter(Context context, List<ProductsBean.DataBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
