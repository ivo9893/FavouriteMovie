package com.example.favouritemovies;

import java.util.ArrayList;

public class MovieSource {

    ArrayList<Movie> movies;

    public MovieSource(){
        movies = new ArrayList<>();
    }

    public void addDefaultMovies(){
        movies.add(new Movie("Batman", "2000", "Steven", "Action", 0.0f));
        movies.add(new Movie("Notebook", "2005", "Anthony", "Romantic", 0.0f));
        movies.add(new Movie("Anabelle", "2020", "Mike", "Horror", 0.0f));

    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }
}
