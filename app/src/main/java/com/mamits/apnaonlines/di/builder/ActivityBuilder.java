package com.mamits.apnaonlines.di.builder;


import com.mamits.apnaonlines.di.module.activity.DashboardActivityModule;
import com.mamits.apnaonlines.di.module.activity.MainActivityModule;
import com.mamits.apnaonlines.di.module.activity.RegisterActivityModule;
import com.mamits.apnaonlines.di.module.fragment.CouponModule;
import com.mamits.apnaonlines.di.module.fragment.CreateCouponModule;
import com.mamits.apnaonlines.di.module.fragment.DashboardFragmentModule;
import com.mamits.apnaonlines.di.module.fragment.HelpSupportModule;
import com.mamits.apnaonlines.di.module.fragment.HomeModule;
import com.mamits.apnaonlines.di.module.fragment.InboxModule;
import com.mamits.apnaonlines.di.module.fragment.MessageModule;
import com.mamits.apnaonlines.di.module.fragment.OrderDetailModule;
import com.mamits.apnaonlines.di.module.fragment.OrdersModule;
import com.mamits.apnaonlines.di.module.fragment.PaymentsModule;
import com.mamits.apnaonlines.di.module.fragment.ServicesModule;
import com.mamits.apnaonlines.di.module.fragment.TransactionsModule;
import com.mamits.apnaonlines.di.scope.ActivityScope;
import com.mamits.apnaonlines.di.scope.FragmentScope;
import com.mamits.apnaonlines.ui.activity.DashboardActivity;
import com.mamits.apnaonlines.ui.activity.MainActivity;
import com.mamits.apnaonlines.ui.activity.RegisterActivity;
import com.mamits.apnaonlines.ui.fragment.DashboardFragment;
import com.mamits.apnaonlines.ui.fragment.dashboard.ServicesFragment;
import com.mamits.apnaonlines.ui.fragment.dashboard.coupons.CouponFragment;
import com.mamits.apnaonlines.ui.fragment.dashboard.InboxFragment;
import com.mamits.apnaonlines.ui.fragment.dashboard.MessageFragment;
import com.mamits.apnaonlines.ui.fragment.dashboard.drawer.HelpSupportFragment;
import com.mamits.apnaonlines.ui.fragment.dashboard.drawer.TransactionsFragment;
import com.mamits.apnaonlines.ui.fragment.dashboard.HomeFragment;
import com.mamits.apnaonlines.ui.fragment.dashboard.PaymentFragment;
import com.mamits.apnaonlines.ui.fragment.dashboard.coupons.CreateCouponFragment;
import com.mamits.apnaonlines.ui.fragment.orders.OrderDetailsFragment;
import com.mamits.apnaonlines.ui.fragment.orders.OrdersFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    @ActivityScope
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {RegisterActivityModule.class})
    @ActivityScope
    abstract RegisterActivity bindRegisterActivity();

    @ContributesAndroidInjector(modules = {DashboardActivityModule.class})
    @ActivityScope
    abstract DashboardActivity bindDashboardActivity();

    @ContributesAndroidInjector(modules = {DashboardFragmentModule.class})
    @FragmentScope
    abstract DashboardFragment bindDashboardFragment();

    @ContributesAndroidInjector(modules = {HomeModule.class})
    @FragmentScope
    abstract HomeFragment bindHomeFragment();

    @ContributesAndroidInjector(modules = {OrdersModule.class})
    @FragmentScope
    abstract OrdersFragment bindOrdersFragment();

    @ContributesAndroidInjector(modules = {OrderDetailModule.class})
    @FragmentScope
    abstract OrderDetailsFragment bindOrderDetailFragment();

    @ContributesAndroidInjector(modules = {PaymentsModule.class})
    @FragmentScope
    abstract PaymentFragment bindPaymentsFragment();

    @ContributesAndroidInjector(modules = {TransactionsModule.class})
    @FragmentScope
    abstract TransactionsFragment bindTransactionsFragment();

    @ContributesAndroidInjector(modules = {HelpSupportModule.class})
    @FragmentScope
    abstract HelpSupportFragment bindHelpSupportFragment();

    @ContributesAndroidInjector(modules = {InboxModule.class})
    @FragmentScope
    abstract InboxFragment bindInboxFragment();

    @ContributesAndroidInjector(modules = {MessageModule.class})
    @FragmentScope
    abstract MessageFragment bindMessageFragment();

    @ContributesAndroidInjector(modules = {CouponModule.class})
    @FragmentScope
    abstract CouponFragment bindCouponFragment();

    @ContributesAndroidInjector(modules = {CreateCouponModule.class})
    @FragmentScope
    abstract CreateCouponFragment bindCreateCouponFragment();

    @ContributesAndroidInjector(modules = {ServicesModule.class})
    @FragmentScope
    abstract ServicesFragment bindServicesFragment();
}
