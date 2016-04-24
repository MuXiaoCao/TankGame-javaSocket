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
	 * ���÷���˵�ip
	 */
	public static String ip = "192.168.1.101";
	public static DatagramSocket ds = null;
	public static DatagramPacket dp = null;

	// ������
	boolean dath = false;
	// ����
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

				// �������ݰ�
				byte[] bys = new byte[1024];
				DatagramPacket dp = new DatagramPacket(bys, bys.length);

				// ��������
				ds.receive(dp);

				// ��������
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
