package com.mamits.apnaonlines.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.data.model.services.ServiceDataModel;
import com.mamits.apnaonlines.ui.customviews.CustomCircularImageView;
import com.mamits.apnaonlines.ui.customviews.CustomTextView;
import com.mamits.apnaonlines.viewmodel.fragment.ServicesViewModel;

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.OrdersViewHolder> {

    private Context mContext;
    public List<ServiceDataModel> list;
    private ServicesViewModel mViewModel;
    private Activity activity;
    private deleteListener listener;


    public ServicesAdapter(Context mContex, ServicesViewModel mViewModel, deleteListener listener) {
        this.mContext = mContex;
        activity = ((Activity) mContex);
        list = new ArrayList<>();
        this.mViewModel = mViewModel;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.services_list_item, parent, false);
        return new OrdersViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        if (list.size() > 0) {
            ServiceDataModel model = list.get(position);
            if (model.getVariation().size() != 0) {
                holder.txt_service_type.setText("Type - Variation");
            } else {
                holder.txt_service_type.setText("Type - Simple");
            }
            holder.txt_service_name.setText(model.getName());
            holder.txt_service_category_sub.setText(model.getCategory().getName() + " | " + model.getSubcategory().getName());
            holder.txt_price.setText("₹ " + model.getPrice());

            Glide.with(mContext).load(model.getImage()).into(holder.img);

            holder.btn_delete.setOnClickListener(v -> listener.onDeleteService(String.valueOf(model.getId())));
        }

    }

    public interface deleteListener {
        void onDeleteService(String inventoryId);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<ServiceDataModel> orderList) {
        list = orderList;
        notifyDataSetChanged();
    }

    public static class OrdersViewHolder extends RecyclerView.ViewHolder {
        private CustomTextView txt_date, txt_service_type, txt_service_name, txt_service_category_sub, txt_price;
        private CustomCircularImageView img;
        private ImageView btn_delete;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_service_type = itemView.findViewById(R.id.txt_service_type);
            txt_service_name = itemView.findViewById(R.id.txt_service_name);
            txt_service_category_sub = itemView.findViewById(R.id.txt_service_category_sub);
            txt_price = itemView.findViewById(R.id.txt_price);
            img = itemView.findViewById(R.id.img);
            btn_delete = itemView.findViewById(R.id.btn_delete);

        }
    }
}
