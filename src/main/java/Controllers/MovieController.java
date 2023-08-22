package Controllers;

import Entities.Search.SearchEntity;
import Service.ControllerService.ControllerService;
import Service.MovieService.MovieServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import Service.MovieService.MovieService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;

/*Clase controlador para gestionar las solicitudes*/
public class MovieController implements HttpHandler{

    private MovieService movieService;
    private ObjectMapper objectMapper = new ObjectMapper();
    private ControllerService controllerService = new ControllerService();
    public MovieController(MovieServiceImp movieServiceImp){
        this.movieService = movieServiceImp;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String httpMethod = exchange.getRequestMethod();
        if(httpMethod.equals("POST")){
            String requestBody = controllerService.getRequestBody(exchange);
            SearchEntity search = objectMapper.readValue(requestBody, SearchEntity.class);
            String response = movieService.findByName(search.getMovieName());
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            controllerService.sendResponse(exchange, response);
        }
        else {
            String response = "Unsupported method";
            controllerService.sendResponse(exchange, response);
        }
    }
}
