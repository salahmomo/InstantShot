package com.fort.saos.instantshot.presenter;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.fort.saos.instantshot.R;
import com.fort.saos.instantshot.model.Album;
import com.fort.saos.instantshot.model.Photo;
import com.fort.saos.instantshot.utils.AlbumSharedPreferenciesManager;
import com.fort.saos.instantshot.utils.ImageTools;
import com.fort.saos.instantshot.utils.PhoneUserSharedPreferenciesManager;
import com.fort.saos.instantshot.view.NewAlbumActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by saldemachki on 21/10/2016.
 */

public class NewAlbumPresenter implements DialogInterface.OnClickListener, View.OnClickListener{
    private NewAlbumActivity newAlbumActivity;

    CardView coverCardView;
    ImageView coverImageView;
    EditText titleEditText;
    Button validerButton;

    private static final int SELECT_PICTURE = 0;
    private static final int REQUEST_CAMERA = 1;
    final CharSequence[] items = {"Appareil photo", "Galerie"};

    private byte[] photo = null;
    private String imagePath = null;
    private String nameAlbum = null;



    public NewAlbumPresenter(NewAlbumActivity newAlbumActivity) {
        this.newAlbumActivity = newAlbumActivity;
    }

    public void onCreate(CardView coverCardView, ImageView coverImageView,
                         EditText titleEditText, Button validerButton){
        this.coverCardView = coverCardView;
        this.coverImageView = coverImageView;
        this.titleEditText = titleEditText;
        this.validerButton = validerButton;
        coverCardView.setOnClickListener(this);
        validerButton.setOnClickListener(this);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == newAlbumActivity.RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Bitmap photoBitMap = ImageTools.getBitMapFromUri(newAlbumActivity.getContentResolver(), data.getData());
                photoBitMap = Bitmap.createScaledBitmap(photoBitMap, 1344, 1344, false);
                photo = ImageTools.convertBitmapToByte(photoBitMap);
                Picasso.with(newAlbumActivity.getBaseContext())
                        .load(data.getData())
                        .into(coverImageView);
                imagePath = data.getData().toString();
                Log.d("NewAlbum", imagePath);
            }
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (items[which].equals(items[0])) {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            newAlbumActivity.startActivityForResult(cameraIntent, REQUEST_CAMERA);
        } else if (items[which].equals(items[1])) {
            Intent intent = new Intent(
                    Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            newAlbumActivity.startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_PICTURE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_view:
                pickImage();
                return;
            case R.id.valider_button:
                validateAlbum();
                return;
        }
    }

    private void pickImage() {
        Intent intentGalery = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intentGalery.setType("image/*");
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        Intent chooser = Intent.createChooser(intentGalery, "Choisissez une image ou prenez une photo");
        chooser.putExtra("android.intent.extra.INITIAL_INTENTS", new Intent[]{cameraIntent});
        newAlbumActivity.startActivityForResult(chooser, SELECT_PICTURE);
    }

    private void validateAlbum(){
        nameAlbum = titleEditText.getText().toString();
        if (nameAlbum != null && imagePath != null) {
            Album album = new Album(nameAlbum, imagePath, "", PhoneUserSharedPreferenciesManager.getPhoneUser(newAlbumActivity), new ArrayList<Photo>());
            AlbumSharedPreferenciesManager.saveAlbum(newAlbumActivity, album);
            newAlbumActivity.finish();
        }
        else {
            // show what is missing
        }
    }
}
