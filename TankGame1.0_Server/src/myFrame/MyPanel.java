package myFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Iterator;

import javax.swing.JPanel;

import net.ReceiveThread;
import tank.Buttle;
import tank.EnemyTank;
import tank.MyTank;
import tank.Tank;
import thread.MyThread;
import thread.MytankThread;
import thread.TankThread;
import tool.Point;
import tool.Tool;

/**
 * �Զ��廭�壬�̳���JPanel ʵ������̹���߳��Լ��ػ�Ĺ���
 * 
 * @author С��
 * 
 */
public class MyPanel extends JPanel implements Runnable {

	// ����run����sleep
	Thread t = new Thread();
	// ������������̹�˺��ĸ��з�̹��
	MyThread[] mytankThread = new MytankThread[2];
	MyThread[] enemytankThread = new TankThread[4];

	DatagramSocket ds = null;

	public MyPanel() {
		super();
		// ʵ�ֵз�̹�˵��ĸ��߳�
		mytankThread[0] = new MytankThread(Tool.myTank1, this);
		mytankThread[1] = new MytankThread(Tool.myTank2, this);
		enemytankThread[0] = new TankThread(Tool.enemyTank_1, this);
		enemytankThread[1] = new TankThread(Tool.enemyTank_2, this);
		enemytankThread[2] = new TankThread(Tool.enemyTank_3, this);
		enemytankThread[3] = new TankThread(Tool.enemyTank_4, this);
		mytankThread[0].start();
		mytankThread[1].start();
		for (int i = 0; i < 4; i++) {
			enemytankThread[i].start();
		}
		try {
			ds = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			DatagramSocket ds = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MyThread[] getMytankThread() {
		return mytankThread;
	}

	public void setMytankThread(MyThread[] mytankThread) {
		this.mytankThread = mytankThread;
	}

	@Override
	public void paint(Graphics g) {
		if (Tool.game_stop) {
			return;
		}
		super.paint(g);
		// ���û����ɫΪ��ɫ
		this.setBackground(Color.BLACK);
		// ��õ�ǰ����Ŀ�ߣ���ֵ��������Ŀ������
		Tool.width = (int) this.getSize().getWidth();
		Tool.high = (int) this.getSize().getHeight();
		// ˢ�µ�ǰ���ڵĳ�ʼ��ͱ߽��
		Tool.refresh();
		// �ж�̹���Ƿ���
		refresh();
		// ����̹�˻�û��ʣ���ʵ������̹�˵��ػ�
		for (MyThread myThread : mytankThread) {
			if (myThread != null && myThread.getTank().getHP() > 0) {
				myThread.setG(g);
				myThread.myPaint();
			}
		}
		for (MyThread tankThread2 : enemytankThread) {
			if (tankThread2 != null && tankThread2.getTank().getHP() > 0) {
				tankThread2.setG(g);
				tankThread2.myPaint();
			}
		}
	}

	/**
	 * ˢ��̹�˵�Ѫ��
	 */
	public void refresh() {

		int hp;
		MyTank myTank = null;
		Iterator<Buttle> it = null;
		// �ж��Ƿ���ж���
		for (int i = 0; i < mytankThread.length; i++) {
			for (int j = 0; j < mytankThread.length; j++) {

				if (mytankThread[i].getTank().getHP() > 0
						&& mytankThread[j].getTank().getHP() > 0 && i != j) {

					// �õ�����̹��
					myTank = (MyTank) mytankThread[i].getTank();
					// ��������̹�˵��ӵ�
					it = myTank.getMy_Buttles().iterator();
					while (it.hasNext()) {
						if (it.next().is_dath2(mytankThread[j].getTank())) {
							// �����еĵз�̹Ѫ����һ
							hp = mytankThread[j].getTank().getHP() - 1;
							mytankThread[j].getTank().setHP(hp);
							// �ҷ�̹��Ѫ����һ
							hp = mytankThread[i].getTank().getHP();
							mytankThread[i].getTank().setHP(hp + 1);
							// �ӵ�ʧЧ
							it.remove();
						}
					}
				}
			}
		}

		for (int i = 0; i < enemytankThread.length; i++) {
			for (int j = 0; j < mytankThread.length; j++) {
				// �ж��ҷ�̹���Ƿ���ез�̹��

				if (enemytankThread[i].getTank().getHP() > 0 // �ų��Ѿ�������̹��
						&& mytankThread[j].getTank().getHP() > 0) {
					// �õ�����̹��
					myTank = (MyTank) mytankThread[j].getTank();
					// ��������̹�˵��ӵ�
					it = myTank.getMy_Buttles().iterator();
					while (it.hasNext()) {
						if (it.next().is_dath2(enemytankThread[i].getTank())) {
							// �����еĵз�̹Ѫ����һ
							hp = enemytankThread[i].getTank().getHP() - 1;
							enemytankThread[i].getTank().setHP(hp);
							// �ҷ�̹��Ѫ����һ
							hp = mytankThread[j].getTank().getHP();
							mytankThread[j].getTank().setHP(hp + 1);
							// �ӵ�ʧЧ
							it.remove();
						}
					}
					// �жϵз�̹���Ƿ�����ҷ�̹��
					if (enemytankThread[i].getTank().getMy_Buttle() != null) {

						if (enemytankThread[i].getTank().getMy_Buttle()
								.is_dath2(mytankThread[j].getTank())) {
							// �����е��ҷ�̹��Ѫ����һ
							hp = mytankThread[j].getTank().getHP() - 1;
							mytankThread[j].getTank().setHP(hp);
							// �ӵ�ʧЧ
							enemytankThread[i].getTank().setMy_Buttle(null);
						}

					}
				}
			}
		}
	}

	/**
	 * ���߳�t��̹��
	 * 
	 * @param t
	 *            �߳�
	 * @param g
	 *            ����
	 */
	public void paintTank(MyThread t, Graphics g) {

		Tank tank = t.getTank();
		int type = t.getType();
		// �ж��Ƿ񵽴�߽磬���Ǹı䷽����ֹ�ػ�
		if (tank.is_isBorder()) {
			tank.changeDir();
			// return;
		}
		// �ж�ǰ���Ƿ���̹��
		for (int j = 0; j < enemytankThread.length; j++) {
			if (tank.is_haveTank(enemytankThread[j].getTank())
					&& tank.getType() != enemytankThread[j].getType()) {
				tank.changeDir();
				// return;
			}
		}
		for (int j = 0; j < mytankThread.length; j++) {
			if (tank.is_haveTank(mytankThread[j].getTank())
					&& tank.getType() != mytankThread[j].getType()) {
				tank.changeDir();
				// return;
			}
		}
		// �������̹�˵����꣨���Ͻǣ�
		int x = tank.getX();
		int y = tank.getY();
		// System.out.println("tank:" + x + "---" + y);
		switch (type) {
		case Tool.myTank1:
			g.setColor(Color.GREEN);
			break;
		case Tool.myTank2:
			g.setColor(Color.BLUE);
			break;
		case Tool.enemyTank_1:
			g.setColor(Color.ORANGE);
			break;
		case Tool.enemyTank_2:
			g.setColor(Color.PINK);
			break;
		case Tool.enemyTank_3:
			g.setColor(Color.YELLOW);
			break;
		case Tool.enemyTank_4:
			g.setColor(Color.RED);
			break;
		default:
			break;
		}
		switch (tank.getDir()) {
		case Tool.up:
			g.fill3DRect(x, y, 5, 30, true);
			g.fill3DRect(x + 15, y, 5, 30, true);
			g.fill3DRect(x + 5, y + 5, 10, 20, true);
			g.setColor(Color.BLUE);
			g.fillOval(x + 5, y + 10, 10, 10);
			g.setColor(Color.red);
			g.drawLine(x + 10, y + 10, x + 10, y);
			break;
		case Tool.down:
			g.fill3DRect(x, y, 5, 30, true);
			g.fill3DRect(x + 15, y, 5, 30, true);
			g.fill3DRect(x + 5, y + 5, 10, 20, true);
			g.setColor(Color.BLUE);
			g.fillOval(x + 5, y + 10, 10, 10);
			g.setColor(Color.red);
			g.drawLine(x + 10, y + 15, x + 10, y + 30);
			break;
		case Tool.left:
			g.fill3DRect(x, y, 30, 5, true);
			g.fill3DRect(x, y + 15, 30, 5, true);
			g.fill3DRect(x + 5, y + 5, 20, 10, true);
			g.setColor(Color.BLUE);
			g.fillOval(x + 10, y + 5, 10, 10);
			g.setColor(Color.red);
			g.drawLine(x, y + 10, x + 15, y + 10);
			break;
		case Tool.right:
			g.fill3DRect(x, y, 30, 5, true);
			g.fill3DRect(x, y + 15, 30, 5, true);
			g.fill3DRect(x + 5, y + 5, 20, 10, true);
			g.setColor(Color.BLUE);
			g.fillOval(x + 10, y + 5, 10, 10);
			g.setColor(Color.red);
			g.drawLine(x + 30, y + 10, x + 15, y + 10);
			break;
		default:
			break;
		}
		// ���ӵ�
		if (t.getTank() instanceof EnemyTank) {
			if (t.getTank().getMy_Buttle() != null) {
				Point p = t.getTank().getMy_Buttle().getCurrent_pos();
				g.setColor(Color.RED);
				g.fillOval(p.getX(), p.getY(), 8, 8);
			}
		} else {
			MyTank myTank = (MyTank) (t.getTank());
			Iterator<Buttle> it = myTank.getMy_Buttles().iterator();
			while (it.hasNext()) {
				Point p = it.next().getCurrent_pos();
				g.setColor(Color.RED);
				g.fillOval(p.getX(), p.getY(), 8, 8);
			}
		}
	}

	/**
	 * ˢ��game_data��ÿ�����̹�˵����͡�λ�á������ӵ�λ����Ϣ
	 */
	private void data_refresh() {
			
		Tool.game_data = "";			
	
		String data = "";
		for (int i = 0; i < enemytankThread.length; i++) {
			EnemyTank tank = (EnemyTank) enemytankThread[i].getTank();
			if (tank.getHP() > 0) {
				// ����
				data += "@" + tank.getType();
				// λ��
				data += ":" + tank.getX() + ":" + tank.getY();
				// ����
				data += ":" + tank.getDir();
				// �ӵ�
				if (tank.getMy_Buttle()!=null) {
					data += ":" + tank.getMy_Buttle().getCurrent_pos().getX() + ":"
							+ tank.getMy_Buttle().getCurrent_pos().getY();
				}
			}
		}
		for (int i = 0; i < mytankThread.length; i++) {
			MyTank tank = (MyTank) mytankThread[i].getTank();
			if (tank.getHP() > 0) {
				// ����
				data += "@" + tank.getType();
				// λ��
				data += ":" + tank.getX() + ":" + tank.getY();
				// ����
				data += ":" + tank.getDir();
				// �ӵ�
				Iterator<Buttle> it = tank.getMy_Buttles().iterator();
				while (it.hasNext()) {
					Point point = it.next().getCurrent_pos();
					data += ":" + point.getX() + ":" + point.getY();
				}
			}
		}
		Tool.game_data = data;
	}

	/**
	 * mypanel�߳�run���� ������̹�˽����ػ棬ʱ����Ϊ100ms
	 */
	@Override
	public void run() {
		while (!Tool.game_over) {
			while (!Tool.game_stop) {
				try {
					t.sleep(10);
					if (ReceiveThread.ip != null) {
						// �������ݰ�
						byte[] bys;
						data_refresh();
						if (Tool.game_data == "") {
							bys = "886".getBytes();
						}else {
							bys = Tool.game_data.getBytes();
						}
						DatagramPacket dp = new DatagramPacket(bys, bys.length,
								InetAddress.getByName(ReceiveThread.ip), 10086);
						// ��������
						ds.send(dp);
						//System.out.println("data -- " + Tool.game_data);
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.repaint();
			}
		}
		if (ds!=null) {
			ds.close();
		}
	}
}
