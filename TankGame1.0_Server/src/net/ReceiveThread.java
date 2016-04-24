package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import myFrame.MyPanel;
import tank.MyTank;
import thread.MytankThread;
import tool.Tool;

public class ReceiveThread implements Runnable {

	MytankThread mytankThread = null;
	public MyPanel mp = null;
	/**
	 * 设置客户端的ip
	 */
	public static String ip = "192.168.1.101";
	public static DatagramSocket ds = null;
	public static DatagramPacket dp = null;

	//结束符
	boolean dath = false;
	//数据
	String data = null;
	public ReceiveThread(DatagramSocket ds,MyPanel mp) {
		this.ds = ds;
		this.mp = mp;
		MytankThread[] mytankThreads = (MytankThread[]) mp.getMytankThread();
		mytankThread = mytankThreads[1];
	}

	@Override
	public void run() {
		try {
			while (true) {
				// 创建数据包
				byte[] bys = new byte[1024];
				DatagramPacket dp = new DatagramPacket(bys, bys.length);

				// 接收数据
				ds.receive(dp);

				// 解析数据
				ip = dp.getAddress().getHostAddress();
				data = new String(dp.getData(), 0, dp.getLength());
				if (data.equals("886")) {
					break;
				}
				//System.out.println("from " + ip + " data is " + data);
				for (int i = 0; i < data.length(); i++) {
					switch (data.charAt(i)) {
					case 'w':
						mytankThread.getTank().setDir(Tool.up);
						if (!mytankThread.getTank().is_isBorder()) {
							mytankThread.getTank().run_Up();
						}
						break;
					case 's':
						mytankThread.getTank().setDir(Tool.down);
						if (!mytankThread.getTank().is_isBorder()) {
							mytankThread.getTank().run_Down();
						}
						break;
					case 'd':
						mytankThread.getTank().setDir(Tool.right);
						if (!mytankThread.getTank().is_isBorder()) {
							mytankThread.getTank().run_Right();
						}
						break;
					case 'a':
						mytankThread.getTank().setDir(Tool.left);
						if (!mytankThread.getTank().is_isBorder()) {
							mytankThread.getTank().run_Left();
						}
						break;
					case 'j':
						MyTank my_tank = (MyTank) (mytankThread.getTank());
						my_tank.my_shot();
						break;
					default:
						break;
					}
				}
			}
			ds.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
