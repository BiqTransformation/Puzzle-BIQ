package course.puzzle.server;


import java.awt.font.NumericShaper;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;


import course.puzzle.puzzle.Puzzle;
import course.puzzle.puzzle.PuzzlePiece;


/*
 * This class represents the Server component of the Puzzle
 * get 2 optional parameters rotate and port
 */
public class Server {
	private int threads;
	private int port;
	private ServerSocket serverSocket;
	private Socket socket;
	private static final int DEFAULTTHREADS = 4;
	private static final int DEFAULTPORT = 7095;
	private static final String THREADS ="-threads";
	private static final String PORT ="-port";
	private static Logger log = LogManager.getLogger(Server.class);
	private ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
	
	
	//Ctors
	public Server(int threads , int port) throws IOException{
		this.threads = threads;
		this.port = port;
		//serverSocket = new ServerSocket(port);
	}
	


	
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            boolean stop = false;
            int clientid = 1;
            while (!stop) {
                Socket socket = serverSocket.accept(); //blocking...
                try {
                    ClientHandler clientHandler = new ClientHandler(this, socket, clientid++);
                    clientHandlers.add(clientHandler);
                    clientHandler.start();
                    
                    
                }
                catch (IOException e) {
                    // TODO: log
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendResponse(int id) {
        for(ClientHandler client : clientHandlers) {
            client.sendMessage(id);
        }
    }

    private void unregister(ClientHandler clientHandler) {
        clientHandlers.remove(clientHandler);
    }
    
    public Puzzle readJson(String jsonFromClient){

		Gson gson = new Gson();
		Puzzle puzzleFromJson = gson.fromJson(jsonFromClient, Puzzle.class);
		System.out.println(puzzleFromJson);		
		return puzzleFromJson;
		
	}
	
	public static void main(String[] args) throws IOException {
		int threads = validateThreads(args);
		int port = validatePort(args);
		Server server = new Server(threads,port);
		server.run();
			
		}
		 
		
	

	private static int validatePort(String[] args) {
		int port = DEFAULTPORT;
		for(int i =0;i<args.length;i++){
			if(args[i].equals(PORT)){
				try{
					port = Integer.parseInt(args[i+1]);
				}
				catch(NumberFormatException e){
					log.debug(e.getMessage());
				}
				
			}
		}
		return port;
	}

	private static int validateThreads(String[] args) {
		int threads = DEFAULTTHREADS;
		for(int i=0;i<args.length;i++){
			if(args[i].equals(THREADS)){
				try{
					threads = Integer.parseInt(args[i+1]);
				}
				catch(NumberFormatException e){
					log.debug(e.getMessage());
				}
								
			}
		}
	return threads;
	}

    
    
    
    

    private static class ClientHandler extends Thread {
        private Server server;
        private Socket socket;
        private final int id;
        private PrintStream outputStream;

        public ClientHandler(Server server, Socket socket, int id) throws IOException {
            this.server = server;
            this.socket = socket;
            this.id = id;
            outputStream = new PrintStream(socket.getOutputStream());
        }

        @Override
        public void run() {
            try {
                String msg = "";
                BufferedReader inputStream;               
                inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));               
                //while (!msg.equals("!")) {
                    msg = inputStream.readLine(); 
                    System.out.println("got the json: " + msg);
                    //outputStream.println("got the json: " + msg);
                    server.sendResponse(id);
                    
                //}
            } catch (Exception e) {
                System.out.println("client disconnected during !");
            }
            finally {
                try {
                    server.unregister(this);
                    socket.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }

        public void sendMessage(int id) {           
                outputStream.println("ID = " + id);
            }
        

    }
	
	
	
	
	
	
	

	

	
		
	

}
