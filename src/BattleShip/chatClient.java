package BattleShip;

import java.io.*;
import java.net.*;

public class chatClient {
	public static void main(String[] args) throws IOException {
    	String serverHostname = new String ("127.0.0.1");
        if (args.length > 0)
        	serverHostname = args[0];
        System.out.println ("Attemping to connect to host " + serverHostname + " on port 10008.");
        Socket chatSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
        	chatSocket = new Socket(serverHostname, 10008);
        	out = new PrintWriter(chatSocket.getOutputStream(), true);
        	in = new BufferedReader(new InputStreamReader(chatSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.exit(1);
        } catch (IOException e) {
            System.exit(1);
        }
        
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        System.out.println ("Type \"bye\" to close");
        while ((userInput = stdIn.readLine()) != null) 
        	{
        		
        		out.println(userInput);
        		if (userInput.equals("Bye."))
        			break;
        		System.out.println("echo: " + in.readLine());
        	}
        out.close();
        in.close();
        stdIn.close();
        chatSocket.close();
    }
}
