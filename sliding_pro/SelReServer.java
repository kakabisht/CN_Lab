import java.lang.System;
import java.net.*; 
import java.io.*;
import java.util.*;

public class SelReServer 
{
    static ServerSocket Serversocket;
    
    public static void main(String[] args)  
    {

        try 
        {
            int v[] = new int[10]; 
            int n = 0;
            Random rands = new Random();
            int rand = 0; 
            Serversocket = new ServerSocket(8011);//port
            System.out.println("waiting for connection");
            Socket client = Serversocket.accept();
            System.out.println("Connection established.");
            DataOutputStream out = new DataOutputStream(
                    client.getOutputStream());
            DataInputStream in = new DataInputStream(
                    client.getInputStream());
            int p = in.read();
            System.out.println("No of frame is:" + p);

            for (int i = 0; i < p; i++) {
                v[i] = in.read();
                System.out.println(v[i]);
                
            }
            rand = rands.nextInt(p);   //random packet being generated or lost
            v[rand] = -1;
            for (int i = 0; i < p; i++)
             {
                    System.out.println("Received frame is: " + v[i]);

                }
            for (int i = 0; i < p; i++)
                if (v[i] == -1) {
                    System.out.println("Request to retransmit frame"
                            + (i+1) + " again!");
                    n = i;
                    out.write(n);
                    out.flush();
				}

			System.out.println();
			
			System.out.println("Retransmitted frame:" +(n+1));
					v[n] = in.read();  // getting the lost frame.
					System.out.println(v[n]);
            System.out.println("Exiting");
        } catch (Exception e) {
            System.out.println(e);
        }
            
    }
}