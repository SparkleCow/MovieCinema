import Entities.Search.SearchEntity;
import Service.ConnectionService.ConnectionMovies;
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
    public void testConnectionMovies() throws IOException {
        String busqueda = "oso";
        connectionService = new ConnectionMovies();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connectionService.createConnectionResponse(busqueda).getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        connectionService.createConnectionResponse(busqueda).disconnect();
        System.out.print(response.toString());
        assertTrue(response.toString().equalsIgnoreCase("{\"Title\":\"Oso\",\"Year\":\"2021\",\"Rated\":\"N/A\",\"Released\":\"19 Jul 2021\",\"Runtime\":\"29 min\",\"Genre\":\"Short, Comedy, Drama\",\"Director\":\"Bruno Lourenço\",\"Writer\":\"Telmo Churro, Bruno Lourenço\",\"Actors\":\"António Mortágua, Sofia Pires, Joaquim Carvalho\",\"Plot\":\"The report of the sighting of a lone bear in a border village is initially received with enthusiasm by the locals and by a young forest ranger. But also by an outsider, fervent supporter of the brown bear's return to the north of ...\",\"Language\":\"Portuguese\",\"Country\":\"Portugal\",\"Awards\":\"N/A\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMzc2OWFiMzctZDhjNC00OTJiLWJkOWYtNzY1YWNiYTI3ODc4XkEyXkFqcGdeQXVyNjg1NTY4MA@@._V1_SX300.jpg\",\"Ratings\":[],\"Metascore\":\"N/A\",\"imdbRating\":\"N/A\",\"imdbVotes\":\"14\",\"imdbID\":\"tt15106044\",\"Type\":\"movie\",\"DVD\":\"N/A\",\"BoxOffice\":\"N/A\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}"));
    }
}
