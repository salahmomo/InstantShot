package com.fort.saos.instantshot.presenter.albums;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fort.saos.instantshot.R;
import com.fort.saos.instantshot.model.Album;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saldemachki on 25/10/2016.
 */

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder>{

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

    public AlbumsAdapter (Context context, List<Album> albums) {
        this.context = context;
        this.albums = albums;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameAlbum.setText(albums.get(position).getName());
        String uri = albums.get(position).getCoverURI();
        Picasso.with(context)
                .load(uri)
                .fit()
                .centerCrop()
                .into(holder.imageAlbum);
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener){
        this.myOnItemClickListener = onItemClickListener;
    }
}
