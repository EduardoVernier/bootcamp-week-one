package com.iws.futurefaces.weekone;

import android.content.Context;
import android.os.Bundle;
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

/**
 * A fragment representing a list of Items.
 * <p>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class AlbumListFragment extends Fragment {

	private int mColumnCount = 2;
	private static final String ARG_COLUMN_COUNT = "column-count";
	private OnListFragmentInteractionListener mListener;
	private List<AlbumItem> albumList;
	private AlbumAdapter adapter;

	@SuppressWarnings("unused")
	public static AlbumListFragment newInstance(int columnCount) {
		AlbumListFragment fragment = new AlbumListFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_COLUMN_COUNT, columnCount);
		fragment.setArguments(args);
		return fragment;
	}	/**
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
		View view = inflater.inflate(R.layout.album_item_list, container, false);

		// Set the adapter
		if (view instanceof RecyclerView) {
			Context context = view.getContext();

			RecyclerView recyclerView = (RecyclerView) view;
			if (mColumnCount <= 1) {
				recyclerView.setLayoutManager(new LinearLayoutManager(context));
			} else {
				recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
			}
			albumList = new ArrayList<AlbumItem>();
			adapter = new AlbumAdapter(albumList, mListener, context);
			recyclerView.setAdapter(adapter);
			prepareAlbums();
		}
		return view;
	}

	private void prepareAlbums() {
		for (int i = 0; i < 5; i++) {
			String title = getContext().getResources().getStringArray(R.array.titles)[i];
			String artist = getContext().getResources().getStringArray(R.array.artists)[i];
			String description = getContext().getResources().getStringArray(R.array.descriptions)[i];
			String coverStr = "cover_"+i;
			albumList.add(new AlbumItem(title, artist, description, coverStr, getContext()));
		}
		adapter.notifyDataSetChanged();
	}


//	@Override
//	public void onAttach(Context context) {
//		super.onAttach(context);
//		if (context instanceof OnListFragmentInteractionListener) {
//			mListener = (OnListFragmentInteractionListener) context;
//		} else {
//			throw new RuntimeException(context.toString()
//					+ " must implement OnListFragmentInteractionListener");
//		}
//	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnListFragmentInteractionListener {
		// TODO: Update argument type and name
		void onListFragmentInteraction(AlbumItem item);
	}
}
