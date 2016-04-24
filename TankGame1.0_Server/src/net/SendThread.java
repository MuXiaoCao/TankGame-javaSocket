package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendThread implements Runnable {

	private DatagramSocket ds;

	// 结束符号
	boolean dath = false;

	public SendThread(DatagramSocket ds) {
		this.ds = ds;
	}

	@Override
	public void run() {
		try {
			// 封装键盘录入
			BufferedReader br = new BufferedReader(new InputStreamReader(new InputStream() {
				
				@Override
				public int read() throws IOException {
					// TODO Auto-generated method stub
					return 0;
				}
			}));
			String line = null;
			while ((line = br.readLine()) != null) {
				if ("886".equals(line)) {
					dath = true;
					break;
				}

				// 创建数据包
				byte[] bys = line.getBytes();
				DatagramPacket dp = new DatagramPacket(bys, bys.length,
						InetAddress.getByName(ReceiveThread.ip), 10086);
				// 发送数据
				ds.send(dp);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		// 释放资源
		ds.close();
	}

}
