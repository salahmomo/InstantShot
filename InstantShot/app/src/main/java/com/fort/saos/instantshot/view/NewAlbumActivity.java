package com.fort.saos.instantshot.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.fort.saos.instantshot.presenter.NewAlbumPresenter;
import com.fort.saos.instantshot.R;
import com.fort.saos.instantshot.utils.ImageTools;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewAlbumActivity extends AppCompatActivity{
    private static final int SELECT_PICTURE = 0;
    private static final int REQUEST_CAMERA = 1;
    final CharSequence[] items = {"Appareil photo", "Galerie"};

    private byte[] photo = null;
    private String imagePath;
    private String nameAlbum;

    @Bind(R.id.card_view)
    CardView coverCardView;
    @Bind(R.id.cover)
    ImageView coverImageView;
    @Bind(R.id.title)
    EditText titleEditText;
    @Bind(R.id.valider_button)
    Button validerButton;

    NewAlbumPresenter newAlbumPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_album);
        getSupportActionBar().setTitle("Ajout d'un nouvel album");

        ButterKnife.bind(this);
        if (newAlbumPresenter == null){
            newAlbumPresenter = new NewAlbumPresenter(this);
        }

        newAlbumPresenter.onCreate(coverCardView, coverImageView,
                titleEditText, validerButton);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        newAlbumPresenter.onActivityResult(requestCode, resultCode, data);
    }
}
