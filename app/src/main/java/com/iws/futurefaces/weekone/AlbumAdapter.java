package com.iws.futurefaces.weekone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iws.futurefaces.weekone.AlbumCollection.AlbumItem;
import com.iws.futurefaces.weekone.AlbumListFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link AlbumItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    private final List<AlbumItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context context;

    public AlbumAdapter(List<AlbumItem> items, OnListFragmentInteractionListener listener, Context current) {
        mValues = items;
        mListener = listener;
        context = current;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).title);
        holder.mContentView.setText(mValues.get(position).artist);
        int drawableResourceId = context.getResources().getIdentifier(mValues.get(position).coverFile, "drawable", context.getPackageName());
        holder.mIdImageView.setImageResource(drawableResourceId);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mIdImageView;
        public final TextView mIdView;
        public final TextView mContentView;
        public AlbumItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdImageView = (ImageView) view.findViewById(R.id.thumbnail);
            mIdView = (TextView) view.findViewById(R.id.title);
            mContentView = (TextView) view.findViewById(R.id.artist);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
