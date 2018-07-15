package com.kunaalkumar.ignis.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.slice.Slice;
import androidx.slice.SliceProvider;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.ListBuilder.RowBuilder;


public class MySliceProvider extends SliceProvider {
    /**
     * Instantiate any required objects. Return true if the provider was successfully created,
     * false otherwise.
     */
    @Override
    public boolean onCreateSliceProvider() {
        return true;
    }

    /**
     * Converts URL to content URI (i.e. content://com.kunaalkumar.ignis...)
     */
    @Override
    @NonNull
    public Uri onMapIntentToUri(@Nullable Intent intent) {
        // Note: implementing this is only required if you plan on catching URL requests.
        // This is an example solution.
        Uri.Builder uriBuilder = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT);
        if (intent == null) return uriBuilder.build();
        Uri data = intent.getData();
        if (data != null && data.getPath() != null) {
            String path = data.getPath().replace("/", "");
            uriBuilder = uriBuilder.path(path);
        }
        Context context = getContext();
        if (context != null) {
            uriBuilder = uriBuilder.authority(context.getPackageName());
        }
        return uriBuilder.build();
    }

    /**
     * Construct the Slice and bind data if available.
     */
    public Slice onBindSlice(Uri sliceUri) {

        if (sliceUri.getPath().equals("/test")) {
            ListBuilder listBuilder = new ListBuilder(getContext(), sliceUri, ListBuilder.INFINITY);

            RowBuilder row1 = new RowBuilder(listBuilder).setTitle("This is an Ignis slice.").setSubtitle("More coming soon.");
            listBuilder.addRow(row1);
            return listBuilder.build();

        } else
            return null;
    }

    /**
     * Slice has been pinned to external process. Subscribe to data source if necessary.
     */
    @Override
    public void onSlicePinned(Uri sliceUri) {
        // When data is received, call context.contentResolver.notifyChange(sliceUri, null) to
        // trigger MySliceProvider#onBindSlice(Uri) again.
    }

    /**
     * Unsubscribe from data source if necessary.
     */
    @Override
    public void onSliceUnpinned(Uri sliceUri) {
        // Remove any observers if necessary to avoid memory leaks.
    }
}
