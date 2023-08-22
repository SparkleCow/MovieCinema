package Service.ConnectionService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionMovies implements ConnectionService{

    private String serviceDirection = "http://www.omdbapi.com/?apikey=";
    private String apiKey = "12c40410";
    private String searchParameters = "&t=";

    @Override
    public HttpURLConnection createConnectionResponse(String busqueda) throws IOException {
        URL url = new URL(serviceDirection+apiKey+searchParameters+busqueda);
        //Retorna la conexion realizada con la respuesta a la busqueda realizada
        return (HttpURLConnection) url.openConnection();
    }

    @Override
    public HttpURLConnection createConnection() throws IOException {
        URL url = new URL("http://www.omdbapi.com/?apikey=");
        //Retorna la conexion realizada con el servicio externo
        return (HttpURLConnection) url.openConnection();
    }
}
