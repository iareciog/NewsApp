package com.example.android.newsapp;

public class News {

    /**
     * Section name of the News
     */
    private String mSectionName;

    /**
     * Publication date of the News
     */
    private String mPublicationDate;

    /**
     * Title of the News
     */
    private String mTitle;

    /**
     * URL for the News
     */
    private String mUrl;

    /**
     * Author of the News
     */
    private String mAuthor;

    /**
     * Constructs a new {@link News} object.
     *
     * @param sectionName is the Name for the section of the News
     * @param title is the title for the news
     * @param url is the URL for the news
     */

    public News(String sectionName, String title, String url){
        mSectionName = sectionName;
        mTitle = title;
        mUrl = url;
    }

    /**
     * Constructs a new {@link News} object.
     *
     * @param sectionName is the Name for the section of the News
     * @param publicationDate  is the date of the publication
     * @param title is the title for the news
     * @param url is the URL for the news
     */

    public News(String sectionName, String publicationDate, String title, String url){
        mSectionName = sectionName;
        mPublicationDate = publicationDate;
        mTitle = title;
        mUrl = url;
    }

    /**
     * Constructs a new {@link News} object.
     *
     * @param sectionName is the Name for the section of the News
     * @param publicationDate  is the date of the publication
     * @param title is the title for the news
     * @param url is the URL for the news
     * @param author is the author of the news
     */

    public News(String sectionName, String publicationDate, String title, String url, String author){
        mSectionName = sectionName;
        mPublicationDate = publicationDate;
        mTitle = title;
        mUrl = url;
        mAuthor = author;
    }

    /** Getter methods for every parameter */

    public String getSectionName() { return mSectionName; }

    public String getPublicationDate() { return mPublicationDate; }

    public String getTitle() { return mTitle; }

    public String getUrl() { return mUrl; }

    public String getAuthor() { return mAuthor; }

}
