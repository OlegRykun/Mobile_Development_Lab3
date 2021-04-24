package com.example.ua.kpi.comsys.io8218;

public class Film {
    private String filmName;
    private String filmYear;
    private String filmType;
    private String filmPoster;

    public Film(String filmName, String filmYear, String filmType, String filmPoster) {
        this.filmName = filmName;
        this.filmYear = filmYear;
        this.filmType = filmType;
        this.filmPoster = filmPoster;
    }

    public Film() {

    }

    public String getFilmName() {
        return filmName;
    }

    public String getFilmYear() {
        return filmYear;
    }

    public String getFilmType() {
        return filmType;
    }
    
    public String getFilmPoster() {
        return filmPoster;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public void setFilmYear(String filmYear) {
        this.filmYear = filmYear;
    }

    public void setFilmType(String filmType) {
        this.filmType = filmType;
    }

    public void setFilmPoster(String filmPoster) {
        this.filmPoster = filmPoster;
    }
}
