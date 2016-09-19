package com.iws.futurefaces.weekone;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

	AlbumListFragment albumListFrag;
	static boolean seenWelcome = false;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		if (!seenWelcome)
		{
			Intent welcome = new Intent(this, WelcomeActivity.class);
			startActivity(welcome);
			seenWelcome = true;
		}

		setContentView(R.layout.activity_main);
		albumListFrag = new AlbumListFragment();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, albumListFrag)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toggleView:
				albumListFrag.toggleLayoutManager(item);
                break;
            default:
                break;
        }
        return true;
    }

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		// Checks the orientation of the screen
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			albumListFrag.rotateLayout();
		} else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
			albumListFrag.rotateLayout();
		}
	}
}
