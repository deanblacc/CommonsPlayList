package com.twitter.commons.resources;

import com.google.inject.Inject;
import com.twitter.commons.models.Song;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.logging.Logger;

@Path("/music/v1")
@Produces(MediaType.APPLICATION_JSON)
public class PlaylistResource {

    private final Logger logger;
    private final List<Song> library;  //entire library
    private final Map<String, List<Song>> playLists;   //playlist library

    @Inject
    public PlaylistResource(Logger logger) {
        this.logger = logger;
        this.library = new ArrayList<Song>();
        this.playLists = new HashMap<String, List<Song>>();
    }

    @POST
    @Path("addSong")
    public void addSong(Song song) {
        logger.info(song.toString());

        if(!library.contains(song)){
            library.add(song);
        }
    }

    @POST
    @Path("toPlaylist")
    public void addToPlayList(Song song, @QueryParam("name") String name){
         if(!this.library.contains(song))
             library.add(song);

         if(this.playLists.containsKey(name)) {
             this.playLists.get(name).add(song);
         } else {
             logger.warning("playlist " + name + "not found. Creating");
             this.playLists.put(name, new ArrayList<Song>());
             this.playLists.get(name).add(song);
         }
    }

    @GET
    @Path("library")
    public List<Song> library() {
        logger.info("Returning entire library");

        return this.library;
    }

    @GET
    @Path("playList")
    public List<Song> playList(@QueryParam("name") String name) {
        return this.playLists.get(name);
    }

    @GET
    @Path("suggest")
    public Song suggest(){
        Collections.shuffle(this.library);

        return this.library.get(0);
    }

}
