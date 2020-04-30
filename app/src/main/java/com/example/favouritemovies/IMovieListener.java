package com.example.favouritemovies;

public interface IMovieListener {

     void addMovie(Movie movie);
     void deleteMovie(int index);
     void addRating(int index, float rating);
}
