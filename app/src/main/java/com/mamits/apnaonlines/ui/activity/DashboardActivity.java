package com.mamits.apnaonlines.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.mamits.apnaonlines.BR;
import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.databinding.ActivityDashboardBinding;
import com.mamits.apnaonlines.ui.base.BaseActivity;
import com.mamits.apnaonlines.ui.navigator.activity.DashboardActivityNavigator;
import com.mamits.apnaonlines.viewmodel.activity.DashboardActivityViewModel;

import javax.inject.Inject;

public class DashboardActivity extends BaseActivity<ActivityDashboardBinding, DashboardActivityViewModel>
        implements DashboardActivityNavigator, View.OnClickListener, NavController.OnDestinationChangedListener {

    String TAG = "DashboardActivity";
    @Inject
    DashboardActivityViewModel mViewModel;
    ActivityDashboardBinding binding;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavController mNavController;

    @Override
    public int getBindingVariable() {
        return BR.dashboardView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        binding = getViewDataBinding();
        mViewModel = getMyViewModel();
        mViewModel.setNavigator(this);

        /*drawer layout*/
        mDrawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, new Toolbar(this),
                R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                binding.mainView.setTranslationX(slideOffset * drawerView.getWidth());
                binding.drawerLayout.bringChildToFront(drawerView);
                binding.drawerLayout.requestLayout();
                binding.mainView.setTranslationX(slideOffset * drawerView.getWidth());
            }
        };
        binding.drawerLayout.addDrawerListener(mDrawerToggle);

        /*clicks*/
        binding.btnToggle.setOnClickListener(this);
        binding.navDrawer.btnClose.setOnClickListener(this);
        binding.navDrawer.btnLogout.setOnClickListener(this);
        binding.navDrawer.btnChangePass.setOnClickListener(this);
        binding.navDrawer.btnTransactions.setOnClickListener(this);
        binding.navDrawer.btnHelp.setOnClickListener(this);
        binding.navDrawer.btnProfile.setOnClickListener(this);

        /*set user data*/
        String[] nameSplit = mViewModel.getmDataManger().getUsername().split(" ");
        String nameCode = (nameSplit[0].charAt(0) + nameSplit[nameSplit.length - 1].substring(0, 1)).toUpperCase();
        binding.navDrawer.nameCode.setText(nameCode);
        binding.navDrawer.userName.setText(mViewModel.getmDataManger().getUsername());
        String num = mViewModel.getmDataManger().getUserNumber().trim().substring(0, 7) + "XXX";
        binding.navDrawer.userNumber.setText(num);

        if (savedInstanceState == null) {
            setUpNavigation();
        }
    }

    private void setUpNavigation() {
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        mNavController.addOnDestinationChangedListener(this);
    }


    @Override
    protected DashboardActivityViewModel getMyViewModel() {
        return mViewModel;
    }


    @Override
    public void onBackPressed() {
        try {
            if (mNavController != null && mNavController.getCurrentDestination() != null && mNavController.getCurrentDestination().getId() != 0) {
                mNavController.popBackStack();
            } else {
                super.onBackPressed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        NavDestination navDestination = mNavController.getCurrentDestination();

        NavOptions options = new NavOptions.Builder()
                .setEnterAnim(R.anim.slide_out_right)
                .setExitAnim(R.anim.slide_in).setPopEnterAnim(0).setPopExitAnim(R.anim.slide_out1)
                .build();
        switch (v.getId()) {
            case R.id.btn_toggle:
                binding.drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.btn_close:
                binding.drawerLayout.closeDrawers();
                break;
            case R.id.btn_change_pass:
                binding.drawerLayout.closeDrawers();
                if (navDestination != null && navDestination.getId() != R.id.nav_transactions) {
                    mNavController.navigate(R.id.nav_change_pass, null, options);
                }
                break;
            case R.id.btn_transactions:
                binding.drawerLayout.closeDrawers();
                if (navDestination != null && navDestination.getId() != R.id.nav_transactions) {
                    mNavController.navigate(R.id.nav_transactions, null, options);
                }
                break;
            case R.id.btn_help:
                binding.drawerLayout.closeDrawers();
                if (navDestination != null && navDestination.getId() != R.id.nav_help) {
                    mNavController.navigate(R.id.nav_help, null, options);
                }
                break;
            case R.id.btn_profile:
                binding.drawerLayout.closeDrawers();
                if (navDestination != null && navDestination.getId() != R.id.nav_profile) {

                    mNavController.navigate(R.id.nav_profile, null, options);
                }
                break;
            case R.id.btn_logout:

                new Handler().postDelayed(() -> {
                    mViewModel.getmDataManger().clearAllPreference();
                    startActivity(new Intent(this, MainActivity.class));
                    finishAffinity();
                }, 200);
                break;
        }
    }

    @Override
    public void showLoader() {

    }

    @Override
    public void hideLoader() {

    }

    @Override
    public void checkValidation(int type, String message) {

    }

    @Override
    public void throwable(Throwable it) {

    }

    @Override
    public void checkInternetConnection(String message) {

    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        hideKeyboard();
    }
}