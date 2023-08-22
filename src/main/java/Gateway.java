import Controllers.MovieController;
import Service.MovieService.MovieServiceImp;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Gateway {

        private final static int SERVER_PORT = 8080;

        // Iniciando el servidor
        public static void main(String[] args) throws IOException {
            HttpServer server = HttpServer.create(new InetSocketAddress(SERVER_PORT), 0);
            addCORSContext(server,"/movies", new MovieController(new MovieServiceImp()));
            server.createContext("/", exchange -> {

                String path = exchange.getRequestURI().getPath();
                String contentType = "text/html";

                if (path.endsWith(".js")) {
                    contentType = "application/javascript";
                } else if (path.endsWith(".css")) {
                    contentType = "text/css";
                }

                try (InputStream inputStream = Gateway.class.getResourceAsStream("/static" + path)) {
                    if (inputStream != null) {
                        byte[] fileContent = inputStream.readAllBytes();
                        exchange.getResponseHeaders().set("Content-Type", contentType);
                        exchange.sendResponseHeaders(200, fileContent.length);
                        OutputStream os = exchange.getResponseBody();
                        os.write(fileContent);
                        os.close();
                    } else {
                        // Recurso no encontrado
                        exchange.sendResponseHeaders(404, 0);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    exchange.sendResponseHeaders(500, 0);
                }
            });
            Executor executor = Executors.newFixedThreadPool(10);
            server.setExecutor(executor);
            server.start();
            System.out.println("Server started on port " + SERVER_PORT);
        }

        private static void addCORSContext(HttpServer server, String path, HttpHandler handler) {
            HttpContext context = server.createContext(path, exchange -> {
                // Configurar cabeceras CORS
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
                handler.handle(exchange);
            });
        }
}
