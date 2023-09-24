import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;


    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {

        try {
                while (!serverSocket.isClosed()) {
                    Socket socket = serverSocket.accept();
                    System.out.println("A new client is connected");

                    ClientHandler clientHandler = new ClientHandler(socket);

                    Thread thread = new Thread(clientHandler);
                    thread.start();
                }
        } catch (IOException ie) {

        }
    }

    public void closeServerSocket() {

        try {
            this.serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket1 = new ServerSocket(1234);

        Server server = new Server(serverSocket1);

        server.startServer();

    }
}
