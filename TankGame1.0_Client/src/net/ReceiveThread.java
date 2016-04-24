package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import myFrame.MyPanel;
import tank.Buttle;
import tank.MyTank;
import tool.Point;
import tool.Tool;

public class ReceiveThread implements Runnable {

	public MyPanel mp = null;
	/*
	 * 设置服务端的ip
	 */
	public static String ip = "192.168.1.101";
	public static DatagramSocket ds = null;
	public static DatagramPacket dp = null;

	// 结束符
	boolean dath = false;
	// 数据
	String data = null;

	public ReceiveThread(DatagramSocket ds, MyPanel mp) {
		this.ds = ds;
		this.mp = mp;
	}

	@Override
	public void run() {
		try {
			while (true) {

				for (int i = 0; i < Tool.mytank.length; i++) {
					if (Tool.mytank[i] != null) {

						Tool.mytank[i].setHP(0);
					}
				}
				for (int i = 0; i < Tool.enemeytank.length; i++) {
					if (Tool.enemeytank[i] != null) {

						Tool.enemeytank[i].setHP(0);
					}
				}

				// 创建数据包
				byte[] bys = new byte[1024];
				DatagramPacket dp = new DatagramPacket(bys, bys.length);

				// 接收数据
				ds.receive(dp);

				// 解析数据
				ip = dp.getAddress().getHostAddress();
				data = new String(dp.getData(), 0, dp.getLength());
				if (data.equals(886)) {
					break;
				}
				Tool.game_data = data;
			}
			ds.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
