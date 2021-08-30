package com.mamits.apnaonlines.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.data.model.orders.OrdersDataModel;
import com.mamits.apnaonlines.ui.customviews.CustomCircularImageView;
import com.mamits.apnaonlines.ui.customviews.CustomTextView;
import com.mamits.apnaonlines.viewmodel.fragment.OrdersViewModel;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {

    private Context mContext;
    public List<OrdersDataModel> list;
    private OrdersViewModel mViewModel;
    private Activity activity;


    public OrdersAdapter(Context mContex, OrdersViewModel mViewModel) {
        this.mContext = mContex;
        activity = ((Activity) mContex);
        list = new ArrayList<>();
        this.mViewModel = mViewModel;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.orders_list_item, parent, false);
        return new OrdersViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        if (list.size() > 0) {
            OrdersDataModel model = list.get(position);
            holder.txt_date.setText(model.getOrderdatetime());
            holder.txt_order_id.setText(String.format("#%s", model.getOrder_id()));
            holder.txt_username.setText(model.getUsers().getName());
            holder.txt_service_category.setText(model.getProducts().getName());
            switch (model.getStatus()) {
                case 1:
                    holder.txt_status.setText("Pending");
                    holder.txt_status.setTextColor(mContext.getResources().getColor(R.color.yellow_ffb302));
                    break;
                case 2:
                    holder.txt_status.setText("Accept");
                    holder.txt_status.setTextColor(mContext.getResources().getColor(R.color.green_39ae00));
                    break;
                case 3:
                    holder.txt_status.setText("Reject");
                    holder.txt_status.setTextColor(mContext.getResources().getColor(R.color.red_ff2502));
                    break;
                case 4:
                    holder.txt_status.setText("Cancel");
                    holder.txt_status.setTextColor(mContext.getResources().getColor(R.color.red_ff2502));
                    break;
                case 5:
                    holder.txt_status.setText("Complete");
                    holder.txt_status.setTextColor(mContext.getResources().getColor(R.color.green_39ae00));
                    break;
            }
            holder.txt_price.setText("₹ " + model.getOrder_amount());

            Glide.with(mContext).load(model.getProducts().getProduct_image()).into(holder.img);
            holder.itemView.setOnClickListener(v -> gotoOrderDetail(v, position));
        }

    }

    private void gotoOrderDetail(View v, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("order", list.get(position));
        NavOptions options = new NavOptions.Builder()
                .setEnterAnim(R.anim.slide_out_right)
                .setExitAnim(R.anim.slide_in).setPopEnterAnim(0).setPopExitAnim(R.anim.slide_out1)
                .build();
        NavController navController = Navigation.findNavController(v);
        navController.navigate(R.id.nav_order_details, bundle, options);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<OrdersDataModel> orderList) {
        list = orderList;
        notifyDataSetChanged();
    }

    public static class OrdersViewHolder extends RecyclerView.ViewHolder {
        private CustomTextView txt_date, txt_order_id, txt_username, txt_service_category, txt_status, txt_price;
        private CustomCircularImageView img;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_order_id = itemView.findViewById(R.id.txt_order_id);
            txt_username = itemView.findViewById(R.id.txt_username);
            txt_service_category = itemView.findViewById(R.id.txt_service_category);
            txt_status = itemView.findViewById(R.id.txt_status);
            txt_price = itemView.findViewById(R.id.txt_price);
            img = itemView.findViewById(R.id.img);

        }
    }
}
