package com.fort.saos.instantshot.presenter.albums;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fort.saos.instantshot.model.Album;
import com.fort.saos.instantshot.utils.AlbumSharedPreferenciesManager;
import com.fort.saos.instantshot.view.AlbumsFragment;

import java.util.List;

/**
 * Created by saldemachki on 25/10/2016.
 */

public class AlbumsPresenter {
    private AlbumsFragment albumsFragment;

    private RecyclerView albumList;
    private AlbumsAdapter albumsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    List<Album> albums;


    public AlbumsPresenter(AlbumsFragment albumsFragment){
        this.albumsFragment = albumsFragment;
    }


    public void initializeAlbumList(RecyclerView albumList){
        this.albumList = albumList;
        mLayoutManager = new LinearLayoutManager(albumsFragment.getContext());
        albumList.setLayoutManager(mLayoutManager);
        albums = AlbumSharedPreferenciesManager.getAlbums(albumsFragment.getContext());

        albumsAdapter = new AlbumsAdapter(albumsFragment.getContext(), albums);
        albumsAdapter.setOnItemClickListener(new AlbumsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //go to movie element
/*                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.gotToVideoFragment(position, etag);*/
            }
        });

        albumList.setAdapter(albumsAdapter);
    }

    public void onResume() {
        albums = AlbumSharedPreferenciesManager.getAlbums(albumsFragment.getContext());
        albumsAdapter = new AlbumsAdapter(albumsFragment.getContext(), albums);
        albumList.setAdapter(albumsAdapter);
        albumsAdapter.notifyDataSetChanged();
    }
}
