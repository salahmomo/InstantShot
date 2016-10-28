package com.fort.saos.instantshot.presenter.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fort.saos.instantshot.R;
import com.fort.saos.instantshot.view.AlbumsFragment;
import com.fort.saos.instantshot.view.ContactsFragment;
import com.fort.saos.instantshot.view.HomeFragment;
import com.fort.saos.instantshot.view.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

import static java.security.AccessController.getContext;

/**
 * Created by saldemachki on 20/10/2016.
 */

public class HomePresenter {

    private HomeFragment homeFragment;

    TabLayout tabLayout;
    ViewPager viewPager;

    public HomePresenter(HomeFragment homeFragment){
        this.homeFragment = homeFragment;
    }

    public void onCreateView(TabLayout tabLayout, ViewPager viewPager) {
        this.tabLayout = tabLayout;
        this.viewPager = viewPager;

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
//        setupTabIcons();
    }

    private void setupViewPager(ViewPager viewPager) {
        HomePagerAdapter adapter = new HomePagerAdapter(homeFragment.getChildFragmentManager());
        adapter.addFragment(new ContactsFragment(), "Contacts");
        adapter.addFragment(new AlbumsFragment(), "Albums");
        viewPager.setAdapter(adapter);
    }

    /*private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }*/
}
