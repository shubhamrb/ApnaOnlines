package com.mamits.apnaonlines.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mamits.apnaonlines.ui.fragment.dashboard.HomeFragment;
import com.mamits.apnaonlines.ui.fragment.dashboard.InboxFragment;
import com.mamits.apnaonlines.ui.fragment.orders.OrdersFragment;
import com.mamits.apnaonlines.ui.fragment.dashboard.PaymentFragment;
import com.mamits.apnaonlines.ui.fragment.dashboard.ServicesFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter  {

    public ViewPagerAdapter(FragmentManager fm, int page) {
        super(fm, page);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        if (position == 0) {
            fragment = new HomeFragment();
        } else if (position == 1) {
            fragment = new OrdersFragment();
        } else if (position == 2) {
            fragment = new InboxFragment();
        } else if (position == 3) {
            fragment = new ServicesFragment();
        } else {
            fragment = new PaymentFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        if (position == 0) {
            title = "Home";
        } else if (position == 1) {
            title = "Orders";
        } else if (position == 2) {
            title = "Inbox";
        } else if (position == 3) {
            title = "Services";
        } else {
            title = "Payments";
        }
        return title;
    }

}
