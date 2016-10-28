package com.fort.saos.instantshot.view;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.fort.saos.instantshot.presenter.MainPresenter;
import com.fort.saos.instantshot.R;

import butterknife.Bind;

public class MainActivity extends AppCompatActivity implements AlbumFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener{

    MainPresenter mainPresenter = null;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (mainPresenter == null){
            mainPresenter = new MainPresenter(this);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != toolbar)
            setSupportActionBar(toolbar);

        mainPresenter.initializeHomeFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_new_album:
                Intent intent = new Intent(getBaseContext(), NewAlbumActivity.class);
                startActivity(intent);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
