package course.puzzle.server;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
	
	
	//Ctors
	public Server(int threads , int port) throws IOException{
		this.threads = threads;
		this.port = port;
		serverSocket = new ServerSocket(port);
	}
	
	public Server() throws IOException{
		threads=4;
		port = 7095;
		serverSocket = new ServerSocket(port);
	}


	
//	public void startServer() throws IOException{
//		System.out.println("Server started");
//		boolean run=true;
//		try{
//			while(run){
//				socket = serverSocket.accept();
//				readJson(socket);
//			}
//		}
//		catch(Exception e){
//			System.out.println(e.getMessage());
//			}
//		finally{
//				socket.close();
//			}		
//	}
	
	public Puzzle readJson(String jsonFromClient){

		Gson gson = new Gson();
		Puzzle puzzleFromJson = gson.fromJson(jsonFromClient, Puzzle.class);
		System.out.println(puzzleFromJson);		
		return puzzleFromJson;
		
	}
	
	

}
