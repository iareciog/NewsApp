package com.example.android.newsapp;

public class News {

    /**
     * Default translation for the word
     */
    private String mSectionName;

    /**
     * Miwok translation for the word
     */
    private String mPublicationDate;

    /**
     * Audio resource ID for the word
     */
    private String mTitle;

    /**
     * URL for the info
     */
    private String mUrl;

    /**
     * Constructs a new {@link News} object.
     *
     * @param sectionName          is the magnitude (size) of the earthquake
     * @param publicationDate  is the city location of the earthquake
     * @param title is the time in milliseconds (from the Epoch) when the
     *                           earthquake happened
     */

    public News(String sectionName, String publicationDate, String title){
        mSectionName = sectionName;
        mPublicationDate = publicationDate;
        mTitle = title;
    }

    public News(String sectionName, String publicationDate, String title, String url){
        mSectionName = sectionName;
        mPublicationDate = publicationDate;
        mTitle = title;
        mUrl = url;
    }

    public String getSectionName() { return mSectionName; }

    public String getPublicationDate() { return mPublicationDate; }

    public String getTitle() { return mTitle; }

    public String getUrl() { return mUrl; }


}
