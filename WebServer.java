import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer{
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int portNo;
    private String docroot;

    public WebServer(int portNo){
        this.portNo=portNo;
        this.docroot="docroot";
    }

    private void processConnection() throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
        String buffer, fileName, contentType="";
        File file;
        FileInputStream inFile;
        byte[] fileContents;

        //** Read HTTP Headers ***/
        buffer = in.readLine();
        fileName = parseURL(buffer);
        while(buffer.length()!=0){
            if(buffer.startsWith("Accept:")){
                contentType=parseContentType(buffer);
            }
            buffer = in.readLine();
        }
        System.out.printf("FileName: %s\n",fileName);
        System.out.printf("Content-Type: %s\n",contentType);
        
        //*** Read File *** */
        file = new File(docroot+fileName);
        inFile = new FileInputStream(file);
        fileContents=inFile.readAllBytes();

        //*** HTTP Response */
        out.printf("HTTP/1.1 200 OK\n");
        out.printf("Content-Length: %d\n",fileContents.length);
        out.printf("Content-Type: %s\n\n",contentType);
        clientSocket.getOutputStream().write(fileContents);

        inFile.close();;
        in.close();
        out.close();
    }

    private String parseURL(String buffer) {
        String value="";
        String[] tokens = buffer.split(" ");
        if(tokens.length==3){
            value=tokens[1];
        }
        return value;
    }

    private String parseContentType(String buffer) {
        String value="";
        String[] namevalue = buffer.split(" ");
        String[] tokens = namevalue[1].split(",");
        value=tokens[0];
        return value;
    }

    public void run() throws IOException{
        boolean running = true;
       
        serverSocket = new ServerSocket(portNo);
        System.out.printf("Listen on Port: %d\n",portNo);
        while(running){
            clientSocket = serverSocket.accept();
            processConnection();
            clientSocket.close();
        }
        serverSocket.close();
    }
    public static void main(String[] args0) throws IOException{
        WebServer server = new WebServer(8080);
        server.run();
    }
}
