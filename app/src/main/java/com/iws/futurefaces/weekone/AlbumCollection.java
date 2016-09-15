package com.iws.futurefaces.weekone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class AlbumCollection {

    public static final List<AlbumItem> ITEMS = new ArrayList<AlbumItem>();
    public static final Map<String, AlbumItem> ITEM_MAP = new HashMap<String, AlbumItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {

            addItem(createDummyItem(i));
        }
    }

    private static void addItem(AlbumItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.title, item);
    }

    private static AlbumItem createDummyItem(int position) {
        return new AlbumItem(String.valueOf(position), "Item " + position, makeDetails(position), "cover_0");
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class AlbumItem {
        public final String title;
        public final String artist;
        public final String details;
        public final String coverFile;

        public AlbumItem(String title, String artist, String details, String coverFile) {
            this.title = title;
            this.artist = artist;
            this.details = details;
            this.coverFile = coverFile;
        }

        @Override
        public String toString() {
            return artist;
        }
    }
}
