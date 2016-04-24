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

import javax.naming.directory.DirContext;
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
 * 自定义画板，继承了JPanel 实现声明坦克线程以及重绘的功能
 * 
 * @author 小草
 * 
 */
public class MyPanel extends JPanel implements Runnable {

	// 定义两个己方坦克和四个敌方坦克
	MyThread[] mytankThread = new MytankThread[2];
	MyThread[] enemytankThread = new TankThread[4];

	// 用于run方法sleep
	Thread t = new Thread();
	DatagramSocket ds = null;

	public MyPanel() {
		super();

		Tool.mytank[0] = new MyTank(Tool.myTank1);
		Tool.mytank[1] = new MyTank(Tool.myTank2);
		Tool.enemeytank[0] = new EnemyTank(Tool.enemyTank_1);
		Tool.enemeytank[1] = new EnemyTank(Tool.enemyTank_2);
		Tool.enemeytank[2] = new EnemyTank(Tool.enemyTank_3);
		Tool.enemeytank[3] = new EnemyTank(Tool.enemyTank_4);

		mytankThread[0] = new MytankThread(Tool.myTank1, this);
		mytankThread[1] = new MytankThread(Tool.myTank2, this);
		enemytankThread[0] = new TankThread(Tool.enemyTank_1, this);
		enemytankThread[1] = new TankThread(Tool.enemyTank_2, this);
		enemytankThread[2] = new TankThread(Tool.enemyTank_3, this);
		enemytankThread[3] = new TankThread(Tool.enemyTank_4, this);
		try {
			DatagramSocket ds = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// 设置画板底色为黑色
		this.setBackground(Color.BLACK);
		if (Tool.game_data==null) {
			return;
		}
		String[] tank = Tool.game_data.split("@");
		for (int i = 1; i < tank.length; i++) {
			paintTank(tank[i], g);
		}
	}

	/**
	 * 画线程t的坦克
	 * 
	 * @param t
	 *            线程
	 * @param g
	 *            画笔
	 */
	public void paintTank(String t, Graphics g) {

		if (t==null) {
			return;
		}
		String[] data = t.split(":");

		int type = Integer.valueOf(data[0]);
		// 获得所画坦克的坐标（左上角）
		int x = Integer.valueOf(data[1]);
		int y = Integer.valueOf(data[2]);
		
		int dir = Integer.valueOf(data[3]);
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
		switch (dir) {
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
		// 绘子弹
		g.setColor(Color.RED);
		int x1 = 0;
		int y1;
		for (int i = 4; i < data.length; i++) {
			if (i%2==0) {
				x1 = Integer.valueOf(data[i]);
			}else {
				y1 = Integer.valueOf(data[i]);
				g.fillOval(x1, y1, 8, 8);
			}
		}
	}

	/**
	 * mypanel线程run方法 对所有坦克进行重绘，时间间隔为10ms
	 */
	@Override
	public void run() {
		while (!Tool.game_over) {
			while (!Tool.game_stop) {
				try {
					t.sleep(10);
					data_refresh();
					ds = new DatagramSocket();
					// System.out.println(Tool.game_data);
					if (Tool.my_data != null) {
						// 创建数据包
						byte[] bys;

						bys = Tool.my_data.getBytes();

						DatagramPacket dp = new DatagramPacket(bys, bys.length,
								InetAddress.getByName(ReceiveThread.ip), 10086);
						// 发送数据
						ds.send(dp);
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
		if (ds != null) {
			ds.close();
		}
	}

	/**
	 * 刷新传送数据
	 */
	private void data_refresh() {
		Tool.my_data = "";
		MytankThread mytank = (MytankThread) mytankThread[1];
		if (mytank.w) {
			Tool.my_data += "w";
		} else if (mytank.s) {
			Tool.my_data += "s";
		} else if (mytank.a) {
			Tool.my_data += "a";
		} else if (mytank.d) {
			Tool.my_data += "d";
		} else if (mytank.shot) {
			Tool.my_data += "j";
		}
		// System.out.println("data_refresh:data--" + Tool.game_data);
	}
}
