package com.example.ua.kpi.comsys.io8218;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FragmentFilms extends Fragment {

    //json link 
    private static String jsonURL = "https://run.mocky.io/v3/278f74b0-de01-4ef0-9421-065e1efcf00d";

    private FloatingActionButton addButton;
    View v;
    protected static RecyclerViewAdapter recyclerAdapter;
    private RecyclerView myRecycleView;
    protected static List<Film> listFilm;
    private Film deletedFilm = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.films_fragment, container, false);
        myRecycleView = v.findViewById(R.id.films_recyclerview);

        listFilm = new ArrayList<>();
        GetData getData = new GetData();
        getData.execute();
        
        return v;
    }

    public class GetData extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(jsonURL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream inputStream = urlConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                    int data = inputStreamReader.read();

                    while (data != -1) {
                        current += (char) data;
                        data = inputStreamReader.read();
                    }
                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("Films");

                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject object = jsonArray.getJSONObject(i);

                    Film film = new Film();
                    film.setFilmName(object.getString("Title"));
                    film.setFilmYear(object.getString("Year"));
                    film.setFilmType(object.getString("Type"));
                    film.setFilmPoster(object.getString("Poster"));

                    listFilm.add(film);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            PutDataIntoRecyclerView(listFilm);
        }
    }

    private void PutDataIntoRecyclerView(List<Film> listFilm) {
        recyclerAdapter = new RecyclerViewAdapter(getContext(), listFilm);
        myRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        myRecycleView.setAdapter(recyclerAdapter);
    }
}
