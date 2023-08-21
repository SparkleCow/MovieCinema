package Controllers;

import Entities.Search.SearchEntity;
import Service.MovieServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import Service.MovieService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/*Clase controlador para gestionar las solicitudes*/
public class MovieController implements HttpHandler{

    private MovieService movieService;
    private ObjectMapper objectMapper = new ObjectMapper();
    public MovieController(MovieServiceImp movieServiceImp){
        this.movieService = movieServiceImp;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String httpMethod = exchange.getRequestMethod();
        if(httpMethod.equals("POST")){
            String requestBody = getRequestBody(exchange);
            System.out.print(requestBody);

            SearchEntity search = objectMapper.readValue(requestBody, SearchEntity.class);
            System.out.print(search.getMovieName());
            String response = movieService.findByName(search.getMovieName());
            System.out.print(response);
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            sendResponse(exchange, response);
        }
        else {
            String response = "Unsupported method";
            sendResponse(exchange, response);
        }
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        byte[] responseBytes = response.getBytes("UTF-8");
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(responseBytes);
        os.close();
    }

    private static String getRequestBody(HttpExchange exchange) throws IOException {
        InputStream requestBodyStream = exchange.getRequestBody();
        try (Scanner scanner = new Scanner(requestBodyStream)) {
            return scanner.useDelimiter("\\A").next();
        }
    }
}
