package com.example.android.newsapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends ArrayAdapter<News> {

    /** Separator for author */

    private static final String LOCATION_SEPARATOR = "/";

    /**
     * Constructs a new {@link NewsAdapter}.
     *
     * @param context of the app
     * @param news is the list of earthquakes, which is the data source of the adapter
     */
    public NewsAdapter(Context context, List<News> news) {
        super(context, 0, news);
    }

    /**
     * Returns a list item view that displays information about the earthquake at the given position
     * in the list of earthquakes.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Find the earthquake at the given position in the list of earthquakes
        News currentNews = getItem(position);

        if (currentNews != null) {
            TextView titleView = (TextView) listItemView.findViewById(R.id.title);
            titleView.setText(currentNews.getTitle());

            TextView categoryView = (TextView) listItemView.findViewById(R.id.category);
            categoryView.setText(currentNews.getSectionName());

            // Find the TextView with view ID date
            TextView dateView = (TextView) listItemView.findViewById(R.id.date);
            // Get the publication date
            String publicationDate = currentNews.getPublicationDate();

            if(publicationDate != null){
                // Obtain the Date formatted by parseDate
                Date formatDate = parseDate(publicationDate);
                // Format the date string (i.e. "Mar 3, 1984")
                String formattedDate = formatDate(formatDate);
                // Display the date of the current earthquake in that TextView
                dateView.setText(formattedDate);
                // Find the TextView with view ID time
                TextView timeView = (TextView) listItemView.findViewById(R.id.time);
                // Format the time string (i.e. "4:30PM")
                String formattedTime = formatTime(formatDate);
                // Display the time of the current earthquake in that TextView
                timeView.setText(formattedTime);
            } else {
                //Set the visibility to Gone
                dateView.setVisibility(View.GONE);
            }

            // Find the TextView with view ID author
            TextView authorView = (TextView) listItemView.findViewById(R.id.author);
            // Get the author
            String author = currentNews.getAuthor();
            if(author != null){

                String authorName;

                // Check whether the author string contains the "/" text
                if (author.contains(LOCATION_SEPARATOR)) {
                    // Split the string into different parts (as an array of Strings)
                    String[] parts = author.split(LOCATION_SEPARATOR);
                    // Obtain the part 1 that should be the name
                    authorName = "Author: " + parts[1];
                } else {
                    authorName = author;
                }

                authorView.setText(authorName);
            }else{
                //Set the visibility to Gone
                authorView.setVisibility(View.GONE);
            }


        }
        return listItemView;
    }

    /** Convert the Date to a more readable date */
    private Date parseDate(String inputDate){
        Date date;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);

        try{
            date = dateFormat.parse(inputDate);
        }catch (ParseException ex){
            Log.e("Parse Exception", ex.toString());
            date = null;
        }
        return date;
    }

    /**
     * Return the formatted date string from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy", Locale.US);
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.US);
        return timeFormat.format(dateObject);
    }
}
