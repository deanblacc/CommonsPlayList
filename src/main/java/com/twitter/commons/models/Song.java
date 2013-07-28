package com.twitter.commons.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Song {
    private String name;
    private int lengthSecs;

    //private ArrayList<String> belongsTo = new ArrayList<String>();

    public Song() {
        //empty constructor
    }
    public Song(String name, int lengthSecs) {
        this.name = name;
        this.lengthSecs = lengthSecs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLengthSecs() {
        return lengthSecs;
    }

    public void setLengthSecs(int lengthSecs) {
        this.lengthSecs = lengthSecs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (name != null ? !name.equals(song.getName()) : song.getName() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
