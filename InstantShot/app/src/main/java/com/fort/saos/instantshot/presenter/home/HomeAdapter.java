package com.fort.saos.instantshot.presenter.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fort.saos.instantshot.model.Album;
import com.fort.saos.instantshot.model.Photo;
import com.fort.saos.instantshot.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saldemachki on 20/10/2016.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imageAlbum;
        public TextView nameAlbum;

        public ViewHolder(View v) {
            super(v);
            imageAlbum = (ImageView) v.findViewById(R.id.album_image);
            nameAlbum = (TextView) v.findViewById(R.id.album_name);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (myOnItemClickListener != null)
                myOnItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    private Context context;
    private List<Album> albums = new ArrayList<>();
    OnItemClickListener myOnItemClickListener;

    public HomeAdapter (Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener){
        this.myOnItemClickListener = onItemClickListener;
    }
}
