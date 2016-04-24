package tool;

import java.io.IOException;
import java.net.DatagramSocket;

import net.ReceiveThread;
import net.SendThread;
import myFrame.MyPanel;

public class Game_Net {
	
	public static void connection(MyPanel mp) throws IOException {
		//DatagramSocket dsSend = new DatagramSocket();
		DatagramSocket dsReceive = new DatagramSocket(10086);

		//SendThread st = new SendThread(dsSend);
		ReceiveThread rt = new ReceiveThread(dsReceive,mp);

		//Thread t1 = new Thread(st);
		Thread t2 = new Thread(rt);

		//t1.start();
		t2.start();
	}
}
