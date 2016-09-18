package com.iws.futurefaces.weekone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iws.futurefaces.weekone.AlbumCollection.AlbumItem;

import java.util.ArrayList;
import java.util.List;

public class AlbumListFragment extends Fragment
		implements AlbumAdapter.OnListFragmentInteractionListener {

	private static int mColumnCount = 2;
	private static final String ARG_COLUMN_COUNT = "column-count";
	private List<AlbumItem> albumList;
	private AlbumAdapter adapter;

	@SuppressWarnings("unused")
	public static AlbumListFragment newInstance(int columnCount) {
		AlbumListFragment fragment = new AlbumListFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_COLUMN_COUNT, columnCount);
		fragment.setArguments(args);
		return fragment;
	}

    /**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public AlbumListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments() != null) {
			mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.album_list_view, container, false);
		// Set the adapter
		if (view instanceof RecyclerView) {
			Context context = view.getContext();

			// Create 2 types of item view: list and grid
			RecyclerView recyclerView = (RecyclerView) view;
			if (mColumnCount <= 1) {
				recyclerView.setLayoutManager(new LinearLayoutManager(context));
			} else {
				recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
			}
			albumList = new ArrayList<AlbumItem>();
			adapter = new AlbumAdapter(albumList, this, mColumnCount, context);
			recyclerView.setAdapter(adapter);
			prepareAlbums();
		}
		return view;
	}

	// Get album info from resources
	private void prepareAlbums() {
		for (int i = 0; i < getResources().getStringArray(R.array.titles).length; i++) {
			String title = getContext().getResources().getStringArray(R.array.titles)[i];
			String artist = getContext().getResources().getStringArray(R.array.artists)[i];
			String description = getContext().getResources().getStringArray(R.array.descriptions)[i];
			String uri = getContext().getResources().getStringArray(R.array.uri)[i];
			String coverStr = "cover_"+i;
			albumList.add(new AlbumItem(title, artist, description, coverStr, uri, getContext()));
		}
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onDetach() {
		super.onDetach();
    }

	// Launch detail activity when cards are interacted with
    @Override
    public void onListFragmentInteraction(AlbumItem item) {
        Intent intent = new Intent(getContext(), AlbumDetailActivity.class);
        intent.putExtra("Album", item);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation((Activity) getContext(),
                        (View) getActivity().findViewById(R.id.container), "cover");
        startActivity(intent, options.toBundle());
    }

	public void toggleLayoutManager() {

		mColumnCount = (mColumnCount == 1)? 2 : 1;
		Context context = (Context) getContext();
		RecyclerView view = (RecyclerView) getView();
		if (context == null) // Virtual device sometimes has null context
			return;

		adapter = new AlbumAdapter(albumList, this, mColumnCount, context);
		view.setAdapter(adapter);

		if (mColumnCount == 1) {
			view.setLayoutManager(new LinearLayoutManager(context));
		} else {
			if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
				view.setLayoutManager(new GridLayoutManager(context, mColumnCount+1));
			else
				view.setLayoutManager(new GridLayoutManager(context, mColumnCount));
		}
	}

	public void rotateLayout() {

		Context context = (Context) getContext();
		if (context == null) // Virtual device sometimes has null context
			return;
		RecyclerView view = (RecyclerView) getView();

		adapter = new AlbumAdapter(albumList, this, mColumnCount, context);
		view.setAdapter(adapter);

		if (mColumnCount == 1) {
			view.setLayoutManager(new LinearLayoutManager(context));
		} else {
			if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
				view.setLayoutManager(new GridLayoutManager(context, mColumnCount+1));
			else
				view.setLayoutManager(new GridLayoutManager(context, mColumnCount));
		}
	}


}
