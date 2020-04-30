package com.example.favouritemovies;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder>{

    private List<Movie> movies;
    private Context context;
    private onLongItemClickListener longItemClickListener;

    public MovieAdapter(List<Movie> movies){
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.itm_movie, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder,final int position) {
        Movie movie = movies.get(position);

        holder.setTitle(movie.getTitle());
        holder.setGenre(movie.getGenre());
        holder.setProducer(movie.getProducer());
        holder.setYear(movie.getDate());
        holder.setRating(String.valueOf(movie.getRating()));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(longItemClickListener != null){
                    longItemClickListener.ItemLongClicked(v, position);
                }
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void addMovie(Movie movie){
        movies.add(movie);
        notifyDataSetChanged();
    }

    public void deleteMovie(int index){
        movies.remove(index);
        notifyDataSetChanged();
    }

    public void updateRating(int index, float rating){
        movies.get(index).setRating(rating);
        notifyItemChanged(index);
    }

    public void setOnLongItemClickListener(onLongItemClickListener onLongItemClickListener) {
        longItemClickListener = onLongItemClickListener;
    }

    public interface onLongItemClickListener {
        void ItemLongClicked(View v, int position);
    }
}

class MovieViewHolder extends RecyclerView.ViewHolder{

    private TextView title;
    private TextView producer;
    private TextView genre;
    private TextView year;
    private TextView rating;

    public MovieViewHolder(@NonNull View view){
        super(view);

        title = view.findViewById(R.id.title);
        producer = view.findViewById(R.id.producer);
        genre = view.findViewById(R.id.genre);
        year = view.findViewById(R.id.year);
        rating = view.findViewById(R.id.rating);

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    public TextView getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating.setText(rating);
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public TextView getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer.setText(producer);
    }

    public TextView getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre.setText(genre);
    }

    public TextView getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year.setText(year);
    }
}
