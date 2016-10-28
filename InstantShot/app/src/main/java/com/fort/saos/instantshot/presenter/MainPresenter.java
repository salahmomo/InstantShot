package com.fort.saos.instantshot.presenter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.fort.saos.instantshot.R;
import com.fort.saos.instantshot.usecase.PermissionsUseCase;
import com.fort.saos.instantshot.view.HomeFragment;
import com.fort.saos.instantshot.view.MainActivity;

/**
 * Created by saldemachki on 20/10/2016.
 */

public class MainPresenter {
    public MainActivity mainActivity;
    public HomeFragment homeFragment;

    public MainPresenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    public void initializeHomeFragment(){
        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
        homeFragment = (HomeFragment) fragmentManager.findFragmentByTag(HomeFragment.TAG);
        if (homeFragment == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            homeFragment = new HomeFragment();
            fragmentTransaction.add(R.id.fragment, homeFragment, HomeFragment.TAG);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }
}
