package Service.ConnectionService;

import java.io.IOException;
import java.net.HttpURLConnection;

public interface ConnectionService {
    HttpURLConnection createConnectionResponse(String busqueda) throws IOException;
    HttpURLConnection createConnection() throws IOException;
}
