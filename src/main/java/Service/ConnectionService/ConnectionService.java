package Service.ConnectionService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

//Clase para gestionar la conexion con un servicio externo.
public class ConnectionService {
    private String serviceDirection;
    private String apiKey = "";
    private String searchParameters = "";
    private String busqueda = "";

    //Constructores
    public ConnectionService(String serviceDirection, String apiKey, String searchParameters, String busqueda) {
        this.serviceDirection = serviceDirection;
        this.apiKey = apiKey;
        this.searchParameters = searchParameters;
        this.busqueda = busqueda;
    }

    public ConnectionService(String serviceDirection, String apiKey, String busqueda){
        this.serviceDirection = serviceDirection;
        this.apiKey = apiKey;
        this.busqueda = busqueda;
    }

    public ConnectionService(String serviceDirection, String apiKey){
        this.serviceDirection = serviceDirection;
        this.apiKey = apiKey;
    }

    public ConnectionService(String serviceDirection){
        this.serviceDirection = serviceDirection;
    }

    //Crea una conexion con un servicio externo.
    public HttpURLConnection createConecction() throws IOException {
        URL url = new URL(serviceDirection+apiKey+searchParameters+busqueda);
        //Retorna la conexion realizada con el servicio externo
        return (HttpURLConnection) url.openConnection();
    }
}
