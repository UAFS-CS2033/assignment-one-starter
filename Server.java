import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int portNo;

    public Server(int portNo){
        this.portNo=portNo;
    }

    private void processConnection() throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
        String buffer;

        /*****************************************
         *  1. Read HTTP Request From Browser    *
         *****************************************/
        buffer=in.readLine();
        while(buffer.length()!=0){  
            System.out.printf("%s\n",buffer);
            buffer=in.readLine();
        }
        System.out.printf("------------------------------\n");

        /*****************************************
         *  2. Write HTTP Response to Browser    *
         *****************************************/
        out.printf("HTTP/1.1 200 OK\n");
        out.printf("Content-Type: text/html\n");
        out.printf("Content-Length: 20\n\n");
        out.printf("<h1>Hello World</h1>\n\n");

        in.close();
        out.close();
    }

    public void run() throws IOException{
        boolean running = true;
       
        serverSocket = new ServerSocket(portNo);
        System.out.printf("Listen on Port: %d\n",portNo);
        while(running){
            clientSocket = serverSocket.accept();
            //** Application Protocol
            processConnection();
            clientSocket.close();
        }
        serverSocket.close();
    }
    public static void main(String[] args0) throws IOException{
        Server server = new Server(8080);
        server.run();
    }
}
