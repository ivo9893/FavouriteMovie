package com.example.favouritemovies;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class StarFragment extends DialogFragment implements View.OnClickListener{

    private RatingBar starRating;
    private Button button;
    private int positionClicked;
    private IMovieListener listener;

    public StarFragment(){

    }


    public static StarFragment newInstance(String title){
        StarFragment fragment = new StarFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (IMovieListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        positionClicked = getArguments().getInt("position");
        return inflater.inflate(R.layout.fragment_rate, container);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        starRating = view.findViewById(R.id.starRating);
        button = view.findViewById(R.id.starSubmit);
        view.findViewById(R.id.starSubmit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.addRating(positionClicked, starRating.getRating());
        dismiss();
    }
}
