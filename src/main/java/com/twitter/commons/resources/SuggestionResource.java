package com.twitter.commons.resources;

import com.google.inject.Inject;
import com.twitter.commons.models.Song;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Path("/suggest/v1")
@Produces(MediaType.APPLICATION_JSON)
public class SuggestionResource  {

    private final Logger logger;
    private final List<Song> songs;

    @Inject
    public SuggestionResource(Logger logger) {
        this.logger = logger;
        this.songs = new ArrayList<Song>();
    }

    @POST
    @Path("like")
    public void like(Song song) {
        logger.info(song.toString());

        if(!songs.contains(song)){
            songs.add(song);
        }
    }

    @GET
    @Path("suggest")
    public Song suggest() {
        logger.info("suggest method called");
        if(songs.size() != 0) {
            Collections.shuffle(this.songs);
            Song d = songs.get(0);

            logger.info("suggesting" + d.toString());
            return d;
        }
        else
            return new Song("New !", 1);
    }
}
