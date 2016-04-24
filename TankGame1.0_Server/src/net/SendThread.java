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

	// ��������
	boolean dath = false;

	public SendThread(DatagramSocket ds) {
		this.ds = ds;
	}

	@Override
	public void run() {
		try {
			// ��װ����¼��
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

				// �������ݰ�
				byte[] bys = line.getBytes();
				DatagramPacket dp = new DatagramPacket(bys, bys.length,
						InetAddress.getByName(ReceiveThread.ip), 10086);
				// ��������
				ds.send(dp);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		// �ͷ���Դ
		ds.close();
	}

}
