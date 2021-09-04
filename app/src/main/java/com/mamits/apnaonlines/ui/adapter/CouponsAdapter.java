package com.mamits.apnaonlines.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.data.model.coupons.CouponsDataModel;
import com.mamits.apnaonlines.ui.customviews.CustomTextView;
import com.mamits.apnaonlines.viewmodel.fragment.CouponViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.CouponsViewHolder> {

    private Context mContext;
    public List<CouponsDataModel> list;
    private CouponViewModel mViewModel;
    private Activity activity;
    private deleteListener listener;

    public CouponsAdapter(Context mContex, CouponViewModel mViewModel, deleteListener listener) {
        this.mContext = mContex;
        activity = ((Activity) mContex);
        list = new ArrayList<>();
        this.mViewModel = mViewModel;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CouponsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.coupons_list_item, parent, false);
        return new CouponsViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull CouponsViewHolder holder, int position) {
        if (list.size() > 0) {
            CouponsDataModel model = list.get(position);
            holder.txt_date.setText(String.format("%s - %s", model.getFrom_date(), model.getTo_date()));
            holder.txt_discount_amount.setText(String.format("%s %s", model.getDiscount_amount(), model.getDiscount_type() == 1 ? "Flat" : "%"));
            holder.txt_coupon.setText(model.getCoupon());
            holder.txt_disc.setText(String.format(Locale.getDefault(), "Per user - %d   |   Per coupon - %d", model.getUsage_limit_per_user(), model.getUsage_limit_per_coupon()));
            switch (model.getIsActive()) {
                case 0:
                    holder.txt_status.setText("Inactive");
                    holder.txt_status.setTextColor(mContext.getResources().getColor(R.color.red_ff2502));
                    break;
                case 1:
                    holder.txt_status.setText("Active");
                    holder.txt_status.setTextColor(mContext.getResources().getColor(R.color.green_39ae00));
                    break;
            }
            holder.txt_min_max_price.setText(String.format(Locale.getDefault(), "₹ min %d - max %d", model.getMin_amount(), model.getMax_amount()));

            holder.btn_delete.setOnClickListener(v -> {
                listener.onDeleteCoupon(String.valueOf(model.getId()));
            });
        }
    }

    public interface deleteListener {
        void onDeleteCoupon(String couponid);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<CouponsDataModel> orderList) {
        list = orderList;
        notifyDataSetChanged();
    }

    public static class CouponsViewHolder extends RecyclerView.ViewHolder {
        private CustomTextView txt_date, txt_discount_amount, txt_coupon, txt_disc, txt_status, txt_min_max_price;
        private ImageView btn_delete;

        public CouponsViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_discount_amount = itemView.findViewById(R.id.txt_discount_amount);
            txt_coupon = itemView.findViewById(R.id.txt_coupon);
            txt_disc = itemView.findViewById(R.id.txt_disc);
            txt_status = itemView.findViewById(R.id.txt_status);
            txt_min_max_price = itemView.findViewById(R.id.txt_min_max_price);
            btn_delete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
