import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 

/*
 * @author avihu Oshri  and  Orel bracha
 * 
 * This class represents a server that hold a password and wait for a client
 * to try guess its password
 * 
 */
public class Server 
{

	/**
	 * In this function, The server waits and listen to the different passwords that in the file
	 * Once it get the right password  - The server stops running.
	 * 
	 *  PASSWORD         final variable that represents the server password 
	 *  PORT                    final variable that represents the server's open port
	 *  socketserver        represents the socket of the server (that connected to the client)  
	 *  serverSoc             enable the server socket to receive requests from clients
	 *  ssOutput               represents the output data that the server sends to the client
	 *  ssInput                  represents the output data that the server receive from the client
	 *  clientPassword    represents the password that the client tries to enter to connect
	 *  loggedIn              boolean flag changes when the password is correct 
	 * 
	 * */
	public static void serverListen ()
	{
		
		final String PASSWORD = "Avihu&OrelPassword" ;
		final int PORT = 1379 ;
		try {
			System.out.println("Waiting for connection");
					ServerSocket socketserver = new ServerSocket(PORT);
					Socket serverSoc = socketserver.accept();
					PrintWriter ssOutput = new PrintWriter(serverSoc.getOutputStream(), true);
					BufferedReader ssInput = new BufferedReader(new InputStreamReader(serverSoc.getInputStream())); ;
					String clientPassword ;
					boolean loggedIn = false ;	
					System.out.println("accepted");
			
					
			while( loggedIn == false)
					{
						clientPassword=ssInput.readLine();
						
						System.out.println(clientPassword);
						if(ssInput != null)
						{
						
								if(PASSWORD.equals(clientPassword))
								{
									 loggedIn = true ;
									 ssOutput.println("WAS FOUND");
									 
								}
								ssOutput.println("TRY AGAIN");
						}
						
					
					}
						socketserver.close();
					   } catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
				   }
	}
	
	public static void main(String[] args)
	{
		serverListen();
	}
}
