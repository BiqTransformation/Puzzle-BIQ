package course.puzzle.server;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONValue;
import org.junit.Test;
public class Server_Tests {
	
	@Test
	public void serverSendJson() throws IOException{
		Server server = new Server();
		server.startServer();
		PrintStream outputStream;
		Socket socket = null;
		 Map obj=new HashMap();    
		  obj.put("name","sonoo");    
		  obj.put("age",new Integer(27));    
		  obj.put("salary",new Double(600000));   
		  String jsonText = JSONValue.toJSONString(obj);  
		  System.out.print(jsonText);
			try {
				socket = new Socket("localhost", 7095);
				outputStream = new PrintStream(socket.getOutputStream());
				outputStream.println(jsonText);
			}
			catch (IOException e) {
			System.err.println(e.getMessage());
			} finally {
			try {
			socket.close();
			} catch (IOException e) {
				// log and ignore
			}
		}
	}

}
