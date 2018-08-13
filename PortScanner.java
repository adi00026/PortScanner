 import java.net.*;
 import java.util.ArrayList;

public class PortScanner {

    public static void main(String args[]) {
        
        System.out.println(" ");
        System.out.println(" #################  PORT SCANNER  #################");
        
        try { 
            if (!args[1].equals("-p")) {
                System.out.println("Usage ---- ");
                System.out.println("java PortScanner [IP address] -p [start port]-[end port]");
                System.out.println("java PortScanner [IP address] -p [port]");
            } else {
                args[2] = args[2].replaceAll("\\s", "");
                if (args[2].indexOf("-") == -1) {
                    scan(args[0], Integer.parseInt(args[2]), Integer.parseInt(args[2]));
                } else {
                    int start = Integer.parseInt(args[2].substring(0, args[2].indexOf('-')));
                    int stop = Integer.parseInt(args[2].substring(args[2].indexOf('-') + 1));
                    scan(args[0], start, stop);
                }
            }
        } catch (Exception e) {
            System.out.println("");
            System.out.println(" Please check the format of your inputs. ");
            System.out.println("");
        }
    }

    private static void scan(String ipAddress, int startPort, int stopPort) {
        
        ArrayList<Integer> open = new ArrayList<Integer>();
        ArrayList<Integer> closed = new ArrayList<Integer>();
        boolean flag = true;
        System.out.println(" ");
        System.out.println("  Connecting to " + ipAddress + "....");
        for (int i = startPort; i <= stopPort; i++) {
            try {
                SocketAddress ip = new InetSocketAddress(ipAddress, i);
                Socket socket = new Socket();
                socket.connect(ip, 1000);
            	socket.close();
                open.add(i);
            	System.out.println("  Port " + i + " is open");
       		} catch (SocketTimeoutException e) {
                System.out.println("  Cannot connect to IP address " + ipAddress);
                System.out.println(" ");
                flag = false;
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("  Port " + i + " is outside the specified range of valid port values.");
                System.out.println(" ");
            } catch (Exception e) {
       	        // e.printStackTrace();
                closed.add(i);   		
            }
      	}

        if (flag) {
            System.out.println(" ");
            System.out.println(" *******************  Summary  *******************");
            System.out.println(" ");
            System.out.println(" " + (stopPort - startPort + 1) + " ports were scanned.");
            System.out.println(" There are " + open.size() + " open ports at " + ipAddress);
            System.out.println(" There are " + closed.size() + " closed ports at " + ipAddress);
            System.out.println(" ");
        }

    }
}