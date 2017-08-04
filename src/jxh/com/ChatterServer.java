package jxh.com;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatterServer {
    static final int INPORT = 1712;
    private byte[] buf = new byte[10];
    private DatagramPacket dp = new DatagramPacket(buf, buf.length);
    private DatagramSocket socket;

    public ChatterServer() {
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
            int count = 0;//计数器
            socket = new DatagramSocket(INPORT);// 创建一接收消息的对象，而不是每次接收消息都创建一个
            System.out.println("Server started .........");
            System.out.println("开启计数器 count=........"+count+"  当前时间="+sf.format(new Date()));
            while (true) {
                socket.receive(dp);
                //接收到客户端的消息
                String clientMessage = Dgram.toString(dp);
                if(clientMessage != null){
                    count++;
                    String rcvd = clientMessage + ",from address:"
                            + dp.getAddress() + ",port:" + dp.getPort();
                    System.out.println("From Client:"+rcvd+"  计数器  count=........"+count+"  当前时间="+sf.format(new Date()));
                    String echoString = "BB11111111";
                    DatagramPacket echo = Dgram.toDatagram(echoString,dp.getAddress(), dp.getPort());
                    //将数据包发送给客户端
                    socket.send(echo);
                }

            }
        } catch (SocketException e) {
            System.err.println("Can't open socket");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Communication error");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatterServer();
    }
}
