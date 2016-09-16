package com.iws.futurefaces.weekone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity { //implements AlbumListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new AlbumListFragment())
                    .commit();
        }
    }

//    void OnListFragmentInteractionListener(AlbumCollection.AlbumItem item){
//        Intent intent = new Intent(this, AlbumDetailActivity.class);
//        intent.putExtra("Album", item);
//        ActivityOptionsCompat options = ActivityOptionsCompat.
//                makeSceneTransitionAnimation(this, findViewById(R.id.detail_cover), "cover");
//        startActivity(intent, options.toBundle());
//    }

}
