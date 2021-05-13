package com.threads.optimization.throughput;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    private static final String INPUT_FILE ="resources/throughput/war_and_peace.txt";
    private static final int NUMBER_OF_THREADS=1;
    public static void main(String[] args) throws IOException {
        String text = new String(Files.readAllBytes(Paths.get(INPUT_FILE)));
        startServer(text);
    }
    public static void startServer(String text) throws IOException{
        //create server that listens to localhost at port 8000 and has a queue backlog of 0
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        // once server is created create context with a handler i.e if url is pass this handle handles what to do.
        server.createContext("/search", new WordCountHandler(text));
        // Create thread pool with executor so that any equests coming in to the url is handl;ed by a thread from this thread pool
        Executor executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        // set the executor on the server
        server.setExecutor(executor);
        //start the server
        server.start();
    }

    private static class WordCountHandler implements HttpHandler{
        private String text;

        public WordCountHandler(String text) {
            this.text = text;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();
            String[] keyValue = query.split("=");
            String action = keyValue[0];String word = keyValue[1];
            if(!action.equals("word")){
                exchange.sendResponseHeaders(400,0);
                return;
            }
            long count = countWord(word);
            // Set the response to a byte stream to send over network
            byte[] response = Long.toString(count).getBytes();
            exchange.sendResponseHeaders(200, response.length);
            OutputStream outputStream = exchange.getResponseBody();
            //Write the response to output stream
            outputStream.write(response);
            //Close the output stream
            outputStream.close();

        }
        private long countWord(String word){
            long count = 0;
            int index = 0;
            while(index>=0){
                index = text.indexOf(word, index);
                if(index>=0){
                    count++;
                    index++;
                }
            }
            return count;
        }
    }
}
