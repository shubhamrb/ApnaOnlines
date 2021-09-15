package com.mamits.apnaonlines.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mamits.apnaonlines.ui.fragment.dashboard.CategoryFragment;
import com.mamits.apnaonlines.ui.fragment.dashboard.HomeFragment;
import com.mamits.apnaonlines.ui.fragment.dashboard.InboxFragment;
import com.mamits.apnaonlines.ui.fragment.dashboard.PaymentFragment;
import com.mamits.apnaonlines.ui.fragment.dashboard.coupons.CouponFragment;
import com.mamits.apnaonlines.ui.fragment.dashboard.services.ServicesFragment;
import com.mamits.apnaonlines.ui.fragment.orders.OrdersFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        if (position == 0) {
            fragment = new CategoryFragment();
        } else if (position == 1) {
            fragment = new HomeFragment();
        } else if (position == 2) {
            fragment = new OrdersFragment();
        } else if (position == 3) {
            fragment = new InboxFragment();
        } else if (position == 4) {
            fragment = new ServicesFragment();
        } else if (position == 5) {
            fragment = new PaymentFragment();
        } else {
            fragment = new CouponFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        if (position == 0) {
            title = "Category";
        } else if (position == 1) {
            title = "Home";
        } else if (position == 2) {
            title = "Orders";
        } else if (position == 3) {
            title = "Chat";
        } else if (position == 4) {
            title = "Services";
        } else if (position == 5) {
            title = "Payments";
        } else {
            title = "Coupons";
        }
        return title;
    }

}
