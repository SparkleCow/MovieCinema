package Service.ControllerService;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class ControllerService {

    public void sendResponse(HttpExchange exchange, String response) throws IOException {
        byte[] responseBytes = response.getBytes("UTF-8");
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(responseBytes);
        os.close();
    }

    public String getRequestBody(HttpExchange exchange) throws IOException {
        InputStream requestBodyStream = exchange.getRequestBody();
        try (Scanner scanner = new Scanner(requestBodyStream)) {
            return scanner.useDelimiter("\\A").next();
        }
    }
}
