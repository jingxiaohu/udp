package jxh.com;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ChatterClient extends Thread {

    private DatagramSocket s;
    private InetAddress hostAddress;
    private byte[] buf = new byte[10];
    private DatagramPacket dp = new DatagramPacket(buf, buf.length);
    private int id;

    public ChatterClient(int identifier) {
        id = identifier;
        try {
            s = new DatagramSocket();
            hostAddress = InetAddress.getByName("localhost");
//            hostAddress = InetAddress.getByName("139.224.29.103");
//
        } catch (UnknownHostException e) {
            System.err.println("Cannot find host");
            System.exit(1);
        } catch (SocketException e) {
            System.err.println("Can't open socket");
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("ChatterClient starting");
    }

    public void run() {
        try {
            /*for (int i = 0; i < 1; i++) {
                String outMessage = "Client #" + id + ",message #" + i;
                s.send(Dgram.toDatagram(outMessage, hostAddress,
						ChatterServer.INPORT));
                s.receive(dp);
                String rcvd = "Client #" + id + ",rcvd from " + dp.getAddress()
                        + ", " + dp.getPort() + ":" + Dgram.toString(dp);
                System.out.println(rcvd);
            }*/
        	
        	String outMessage = "AA11111111";
            s.send(Dgram.toDatagram(outMessage, hostAddress,
					ChatterServer.INPORT));
            s.receive(dp);
            String rcvd = "Server #" + id + ",rcvd from " + dp.getAddress()
                    + ", " + dp.getPort() + ":" + Dgram.toString(dp);
            System.out.println(rcvd);
        	
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
            new ChatterClient(1).start();
    }
}
