package com.example.favouritemovies;

public class Movie {

    private String title;
    private String date;
    private String producer;
    private String genre;
    private float rating;


    public Movie(String title, String date, String producer, String genre, float rating) {
        this.title = title;
        this.date = date;
        this.producer = producer;
        this.genre = genre;
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getProducer() {
        return producer;
    }

    public String getGenre() {
        return genre;
    }
}
