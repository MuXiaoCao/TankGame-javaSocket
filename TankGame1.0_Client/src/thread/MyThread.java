package thread;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import myFrame.MyPanel;
import tank.Tank;

/**
 * �Զ�����̸߳��࣬
 * 		ʵ�ּ����ӿ��Ա�����ʵ�ּ���
 * 		��д�̵߳�run�������Ա�����ʹ��
 * @author С��
 *
 */
public class MyThread extends Thread implements KeyListener{
	
	//̹������
	private int type;
	//��װ̹�˶���
	private Tank tank=null;
	//ʵ���ػ�Ļ���
	private Graphics g = null;
	//ʵ���ػ�Ļ���
	private MyPanel mp = null;
	//���췽��
	public MyThread(int type,MyPanel mp) {
		super();
	}
	public Graphics getG() {
		return g;
	}
	public void setG(Graphics g) {
		this.g = g;
	}
	public MyPanel getMp() {
		return mp;
	}
	public void setMp(MyPanel mp) {
		this.mp = mp;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Tank getTank() {
		return tank;
	}
	public void setTank(Tank tank) {
		this.tank = tank;
	}

	@Override
	public void run() {
		super.run();	
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
