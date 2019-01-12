package com.codecool.app;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WebHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String path = httpExchange.getRequestURI().getPath();
        try {
            for (Method method : Class.forName("com.codecool.app.WebHandler").getMethods()) {
            //TODO check annotation and path then fire method
                Annotation annotation = method.getAnnotation(WebRoute.class);
                if(annotation instanceof WebRoute){
                    WebRoute webRoute = (WebRoute) annotation;
                    System.out.println(webRoute.path());
                    if(webRoute.path().equalsIgnoreCase(path)){
                        method.invoke(this, httpExchange);
                    }
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
            System.out.println("");
        }
    }

    @WebRoute(path = "/index")
    public void onIndex(HttpExchange httpExchange) throws IOException {
        String response = "Welcome to index";
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
