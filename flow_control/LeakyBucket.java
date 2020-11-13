import java.util.*;
public class LeakyBucket {
    public static void main (String[] args) { 
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Bucket Size: ");
        int bs=sc.nextInt();
        System.out.print("Enter the Outgoing Rate: ");
        int or=sc.nextInt();
        if(or>bs){
            System.out.print("OutGoing rate must be less than bucket size");
            System.out.println("exit..."); 
            System.exit(1); 
        }
        else{
            System.out.print("Enter the number of inputs: ");
            int n=sc.nextInt();
            System.out.println("\n");
            int stored=0;
            while(n!=0){
                System.out.print("Enter the Input packet size: ");
                int ips=sc.nextInt();
                if(ips<=bs-stored){
                    stored+=ips;
                    System.out.println("Buffer Size: "+stored+" Out of Bucket Size: "+bs);
                }
                else{
                    System.out.println("Packet loss = "+(ips-(bs-stored))); 
                    stored=bs;
                    System.out.println("Buffer Size: "+stored+" Out of Bucket Size: "+bs);  
                }
                stored-=or;
                if(stored<0){
                    stored=0;
                }
                System.out.println("Out of: "+bs+" ,packets left in buffer after Outgoing are: "+ stored+"\n");
                n-=1;
            }
        }
        sc.close();
    }
}

