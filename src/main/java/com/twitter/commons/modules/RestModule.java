package com.twitter.commons.modules;

import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.twitter.commons.resources.PlaylistResource;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

public class RestModule extends ServletModule {

    @Override
    public void configureServlets(){
        //POJO <-> JSON
        bind(JacksonJsonProvider.class).in(Singleton.class);
        bind(PlaylistResource.class).in(Singleton.class);

        serve("/*").with(GuiceContainer.class);

        //TODO addStatsFilters
    }
}
