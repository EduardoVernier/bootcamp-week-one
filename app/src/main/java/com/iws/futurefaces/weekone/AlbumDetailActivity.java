package com.iws.futurefaces.weekone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AlbumDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);

        Bundle data = getIntent().getExtras();
        final AlbumCollection.AlbumItem album = (AlbumCollection.AlbumItem) data.getParcelable("Album");

        ImageView cover = (ImageView) findViewById(R.id.detail_cover);
        TextView title = (TextView) findViewById(R.id.detail_title);
        TextView artist = (TextView) findViewById(R.id.detail_artist);
        TextView description = (TextView) findViewById(R.id.detail_description);

        cover.setImageResource(album.coverId);
        title.setText(album.title);
        artist.setText(album.artist);
        description.setText(album.details);

        Button play = (Button) findViewById(R.id.spotify);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
                intent.setData(Uri.parse(album.uri));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
