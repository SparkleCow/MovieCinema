import Entities.Search.SearchEntity;
import Service.ConnectionService.ConnectionService;
import Service.MovieService.MovieService;
import Service.MovieService.MovieServiceImp;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertTrue;

public class MovieServiceTest {

    private ConnectionService connectionService;
    @Test
    public void testGetMovieByName() {
        MovieService movieService = new MovieServiceImp();
        SearchEntity busqueda = new SearchEntity("Amor");
        String resultado = movieService.findByName(busqueda.getMovieName());
        assertTrue(resultado.contains(busqueda.getMovieName()));
    }

    //Prueba las conexiones a otros servicios externos
    @Test
    public void testConnectionService() throws IOException {
        connectionService = new ConnectionService("https://jsonplaceholder.typicode.com",
                "", "/", "todos/1");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connectionService.createConecction().getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        connectionService.createConecction().disconnect();
        response.toString();
        assertTrue(response.toString().equalsIgnoreCase("{  \"userId\": 1,  \"id\": 1,  \"title\": \"delectus aut autem\",  \"completed\": false}"));
    }
}
