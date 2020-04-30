package com.example.favouritemovies;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddFragment extends DialogFragment implements View.OnClickListener{

    private EditText title;
    private EditText genre;
    private EditText year;
    private EditText producer;
    private Button submit;
    private IMovieListener listener;


    public AddFragment(){

    }

    public static AddFragment newInstance(String title){
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (IMovieListener) context;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title = view.findViewById(R.id.etTitle);
        genre = view.findViewById(R.id.etGenre);
        year = view.findViewById(R.id.etYear);
        producer = view.findViewById(R.id.etProducer);
        submit = view.findViewById(R.id.submit);

        view.findViewById(R.id.submit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.submit) {
            Movie movie = new Movie(title.getText().toString(), genre.getText().toString(), year.getText().toString(), producer.getText().toString(), 0.0f);
            listener.addMovie(movie);
            dismiss();
        }
    }
}
