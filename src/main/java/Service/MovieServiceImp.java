package Service;

import Controllers.MovieController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MovieServiceImp implements MovieService{

    private final String API_KEY="12c40410";
    @Override
    public String findByName(String name){
        try {
            // URL de la API de peliculas omdbapi con su respectiva key.
            URL url = new URL("http://www.omdbapi.com/?apikey="+API_KEY+"&t="+name);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return new Exception().getMessage();
        }
    }
}
