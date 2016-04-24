package tool;

import java.io.IOException;
import java.net.DatagramSocket;

import net.ReceiveThread;
import myFrame.MyPanel;

public class Game_Net {
	
	public static void connection(MyPanel mp) throws IOException {
		DatagramSocket dsReceive = new DatagramSocket(10086);

		//DatagramSocket dsSend = new DatagramSocket();
		//SendThread st = new SendThread(dsSend);
		ReceiveThread rt = new ReceiveThread(dsReceive,mp);

		//Thread t1 = new Thread(st);
		Thread t2 = new Thread(rt);

		//t1.start();
		t2.start();
	}
}
