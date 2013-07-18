package com.twitter.commons.modules;

import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.twitter.commons.resources.SuggestionResource;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

public class RestModule extends ServletModule {

    @Override
    public void configureServlets(){
        //POJO <-> JSON
        bind(JacksonJsonProvider.class).in(Singleton.class);
        bind(SuggestionResource.class).in(Singleton.class);

        serve("/*").with(GuiceContainer.class);

        //TODO addStatsFilters

    }
}
