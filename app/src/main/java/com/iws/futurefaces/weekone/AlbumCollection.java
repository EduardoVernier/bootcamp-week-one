package com.iws.futurefaces.weekone;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumCollection {

    public static final List<AlbumItem> ITEMS = new ArrayList<AlbumItem>();
    public static final Map<String, AlbumItem> ITEM_MAP = new HashMap<String, AlbumItem>();

    private static int COUNT = 0;

    private static void addItem(AlbumItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.title, item);
        AlbumCollection.COUNT++;
    }

    public static int getCount() {
        return COUNT;
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static class AlbumItem implements Parcelable {
        public final String title;
        public final String artist;
        public final String details;
        public final String uri;
        public final int coverId;

        public AlbumItem(String title, String artist, String details, String coverFile, String uri,
                          Context current) {
            this.title = title;
            this.uri = uri;
            this.artist = artist;
            this.details = details;
            this.coverId = current.getResources().getIdentifier(coverFile, "drawable", current.getPackageName());
        }

        @Override
        public String toString() {
            return artist;
        }

        // Parcelable magic
        protected AlbumItem(Parcel in) {
            title = in.readString();
            artist = in.readString();
            details = in.readString();
            uri = in.readString();
            coverId= in.readInt();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(title);
            dest.writeString(artist);
            dest.writeString(details);
            dest.writeString(uri);
            dest.writeInt(coverId);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<AlbumItem> CREATOR =
                new Parcelable.Creator<AlbumItem>() {
            @Override
            public AlbumItem createFromParcel(Parcel in) {
                return new AlbumItem(in);
            }

            @Override
            public AlbumItem[] newArray(int size) {
                return new AlbumItem[size];
            }
        };
    }
}
