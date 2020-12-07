package com.zelinskiyrk.store.base.config;

import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionEventListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LoggerFactory.getLogger("string").info("session created");
    }
}
