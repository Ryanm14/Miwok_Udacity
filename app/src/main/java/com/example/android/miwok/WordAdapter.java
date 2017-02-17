package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ryan Miles on 2/10/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResource;


    public WordAdapter(Context context, List<Word> objects, int colorResource) {
        super(context, 0, objects);
        mColorResource = colorResource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        LinearLayout linearLayout = (LinearLayout) listItemView.findViewById(R.id.translation_linear_layout);
        int  color = ContextCompat.getColor(getContext(), mColorResource);
        linearLayout.setBackgroundColor(color);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView translateTextView = (TextView) listItemView.findViewById(R.id.translate_name);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        translateTextView.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView englishTextView = (TextView) listItemView.findViewById(R.id.english_name);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        englishTextView.setText(currentWord.getDefaultTranslation());


        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
        if (currentWord.hasImage()) {
            iconView.setImageResource(currentWord.getImageResourceId());
        } else {
            iconView.setVisibility(View.GONE);
        }


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
