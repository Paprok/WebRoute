package com.codecool.app;


import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        //TODO nie wiem o chuj wam chodzi? mam zrobić metodę tworzącą contexty po klasowych annotacjach?
        server.createContext("/index", new WebHandler());
        server.start();
    }
}
