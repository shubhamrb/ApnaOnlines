package com.mamits.apnaonlines.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.data.model.payments.TransactionsDataModel;
import com.mamits.apnaonlines.ui.customviews.CustomCircularImageView;
import com.mamits.apnaonlines.ui.customviews.CustomTextView;
import com.mamits.apnaonlines.viewmodel.fragment.PaymentsViewModel;

import java.util.ArrayList;
import java.util.List;

public class PaymentsAdapter extends RecyclerView.Adapter<PaymentsAdapter.PaymentsViewHolder> {

    private Context mContext;
    public List<TransactionsDataModel> list;
    private PaymentsViewModel mViewModel;
    private Activity activity;


    public PaymentsAdapter(Context mContex, PaymentsViewModel mViewModel) {
        this.mContext = mContex;
        activity = ((Activity) mContex);
        list = new ArrayList<>();
        this.mViewModel = mViewModel;
    }

    @NonNull
    @Override
    public PaymentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.orders_list_item, parent, false);
        return new PaymentsViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentsViewHolder holder, int position) {
        if (list.size() > 0) {
            TransactionsDataModel model = list.get(position);
            holder.txt_date.setText(model.getCreated_at().split(" ")[0]);
            holder.txt_order_id.setText(String.format("#%s", model.getId()));
            holder.txt_username.setText(model.getPayment_type());
            holder.txt_service_category.setText(model.getDescription());
            switch (model.getType()) {
                case "Debit":
                    holder.txt_status.setText("Debit");
                    holder.txt_status.setTextColor(mContext.getResources().getColor(R.color.red_ff2502));
                    break;
                case "Credit":
                    holder.txt_status.setText("Credit");
                    holder.txt_status.setTextColor(mContext.getResources().getColor(R.color.green_39ae00));
                    break;
            }
            holder.txt_price.setText("₹ " + model.getAmount());

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<TransactionsDataModel> orderList) {
        list = orderList;
        notifyDataSetChanged();
    }

    public static class PaymentsViewHolder extends RecyclerView.ViewHolder {
        private CustomTextView txt_date, txt_order_id, txt_username, txt_service_category, txt_status, txt_price;
        private CustomCircularImageView img;

        public PaymentsViewHolder(@NonNull View itemView) {
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
