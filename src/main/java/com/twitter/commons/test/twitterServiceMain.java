package com.twitter.commons.test;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceFilter;
import com.twitter.common.application.AbstractApplication;
import com.twitter.common.application.AppLauncher;
import com.twitter.common.application.Lifecycle;
import com.twitter.common.application.modules.HttpModule;
import com.twitter.common.application.modules.LogModule;
import com.twitter.common.application.modules.StatsModule;
import com.twitter.common.net.http.GuiceServletConfig;
import com.twitter.common.net.http.HttpServerDispatch;
import com.twitter.commons.modules.RestModule;
import org.mortbay.jetty.servlet.Context;

import java.util.logging.Logger;

/**
 * Test project for using Twitter Commons libraries.
 * The application acts as a sandwich maker. Create a PB&J Sandwich ! (Im quite hungry)
 *
 * This is the main class. From here we add the serviceModules.
 */
public class twitterServiceMain extends AbstractApplication {
    @Inject
    private Logger logger;

    @Inject
    private Lifecycle lifeCycle;

    @Inject
    private HttpServerDispatch server;

    @Inject
    private GuiceServletConfig servletConfig;

    @Override
    public void run(){
        logger.info("CommonsPlaylist Service Started. Let's make some playlists.");

        configureContext();

        lifeCycle.awaitShutdown();
    }

    @Override
    public Iterable<? extends Module> getModules() {
        return java.util.Arrays.asList(
                new HttpModule(),
                new LogModule(),
                new RestModule(),
                new StatsModule()
        );
    }

    public static void main(String[] args) {
        AppLauncher.launch(twitterServiceMain.class, args);
    }

    private void configureContext() {
        Context context = server.getRootContext();
        context.addFilter(GuiceFilter.class, "/suggest/*", 0);
        context.addEventListener(servletConfig);

    }
}
