package com.iws.futurefaces.weekone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class AlbumDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);

        Bundle data = getIntent().getExtras();
        AlbumCollection.AlbumItem album = (AlbumCollection.AlbumItem) data.getParcelable("Album");

        ImageView cover = (ImageView) findViewById(R.id.detail_cover);
        TextView title = (TextView) findViewById(R.id.detail_title);
        TextView artist = (TextView) findViewById(R.id.detail_artist);
        TextView description = (TextView) findViewById(R.id.detail_description);

        cover.setImageResource(album.coverId);
        title.setText(album.title);
        artist.setText(album.artist);
        description.setText(album.details);

    }
}
