package com.example.favouritemovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements IMovieListener{

    private RecyclerView lv;
    private MovieAdapter adapter;
    private int positionClicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieSource movieSource = new MovieSource();
        movieSource.addDefaultMovies();

        lv = findViewById(R.id.listView);
        adapter = new MovieAdapter(movieSource.getMovies());
        registerForContextMenu(lv);
        adapter.setOnLongItemClickListener(new MovieAdapter.onLongItemClickListener() {
            @Override
            public void ItemLongClicked(View v, int position) {
                positionClicked = position;
                v.showContextMenu();
            }
        });
        lv.setAdapter(adapter);
        lv.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.add_movie:
                FragmentManager fm = getSupportFragmentManager();
                AddFragment fragment = AddFragment.newInstance("Add new movie");
                fragment.show(fm, "fragment_add"); return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if(v.getId() == R.id.listView){
            MenuInflater infater = getMenuInflater();
            infater.inflate(R.menu.menu_items, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.delete_movie:
                deleteMovie(positionClicked);
                return true;
            case R.id.rate_movie:

                Bundle bundle = new Bundle();
                bundle.putInt("position", positionClicked);

                FragmentManager fm = getSupportFragmentManager();
                StarFragment fragment = StarFragment.newInstance("Rate us !");
                fragment.setArguments(bundle);
                fragment.show(fm, "star_fragment");
                return true;

            default: return true;
        }
    }

    @Override
    public void addMovie(Movie movie) {
        adapter.addMovie(movie);
    }

    @Override
    public void deleteMovie(int index) {
        adapter.deleteMovie(index);
    }

    @Override
    public void addRating(int index, float rating) {
        adapter.updateRating(index, rating);
    }
}
