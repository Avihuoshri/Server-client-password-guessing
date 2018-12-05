import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;







/**
 * @author avihu Oshri  and  Orel bracha
 * 
 *  this class represents a client that tries to enter passwords from  million password text file to log in the server
 * */


public class Client 
{
	
	public Client() {
		
	}
	/**
	 * In this function, The server waits and listen to the different passwords that in the file
	 * Once it get the right password  - The server stops running.
	 * 
	 *  PORT                   -Final variable that represents the server's open port
	 *  passwordFile      -Represent a File variable that receive the file path
	 *  fr                         - Reading character files 
	 *  br                        - Reads text from a character-input stream, buffering characters so as to provide for the efficient reading of characters, arrays, and lines
	 *  passwordFound -Represents a boolean flag that changes when the password was found
	 *  password           -Represents a String variable that holds the password that the client tries to connect with
	 *  checkIfFound      -Represents a String variable that holds the answer string from the server
	 *  socket                 -Represent an end point for communication between two machines
	 *  is                         -Represent An InputStreamReader is a bridge from byte streams to character streams
	 *  socketOutput      -Representing an output stream of bytes
	 *  csInput                -Reads text from a character-input stream, buffering characters so as toprovide for the efficient reading of characters, arrays, and lines
	 *  csOutput             -Prints formatted representations of objects to a text-output stream
	 * @param passwordFilePath represents the path of the file
	 * */
	public static void OpenSession(String passwordFilePath) //
	{
		final int PORT = 1379 ;
		try {
			
			File passwordFile = new File(passwordFilePath);
			FileReader fr = new FileReader(passwordFile);
			BufferedReader br = new BufferedReader(fr);
			boolean passwordFound = false ;
			String password ;
			String checkIfFound = "";
			
			Socket socket = new Socket("localhost" , PORT);	
			InputStreamReader is  = new InputStreamReader(socket.getInputStream());
			OutputStream socketOutput = socket.getOutputStream() ;			
			
			BufferedReader csInput = new BufferedReader(is) ;
			PrintWriter csOutput = new PrintWriter(socketOutput , true) ;
			System.out.println("Reading from " + passwordFile.getName() );
		
			while(  ((password = br.readLine())!=null) && (passwordFound == false)    )
		
				{					
					System.out.println("Trying \" " + password + " \" as a password\n" );
					csOutput.println(password);
					checkIfFound=csInput.readLine();
					if(checkIfFound.equals("WAS FOUND"))
					{
						passwordFound = true ;
						System.out.println("\nYou successfully logged in!!!!\n");
						System.out.println("the password was " + password +"\n" );
					}
					
					
				}
		
					if(password == null)
					{
						System.out.println("\npassword was not found try diffrent password file");
					}
					csInput.close();
					csOutput.close();
					socket.close();
				} catch (UnknownHostException e) 
				{
					e.printStackTrace();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
		
	}
	
	
	public static void main(String[] args) 
	{
		OpenSession("C:\\Users\\Avihu\\Desktop\\Milion_passwords.txt");
	}
}
