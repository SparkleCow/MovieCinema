package Service.MovieService;

import Service.ConnectionService.ConnectionMovies;
import Service.ConnectionService.ConnectionService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MovieServiceImp implements MovieService {

    private ConnectionService connectionService;
    @Override
    public String findByName(String name){
        try {
            // URL de la API de peliculas omdbapi con su respectiva key.
            connectionService = new ConnectionMovies();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connectionService.createConnectionResponse(name).getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connectionService.createConnectionResponse(name).disconnect();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return new Exception().getMessage();
        }
    }
}
