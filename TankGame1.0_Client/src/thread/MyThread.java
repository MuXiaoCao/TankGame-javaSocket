package thread;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import myFrame.MyPanel;
import tank.Tank;

/**
 * 自定义的线程父类，
 * 		实现监听接口以便子类实现监听
 * 		重写线程的run方法，以便子类使用
 * @author 小草
 *
 */
public class MyThread extends Thread implements KeyListener{
	
	//坦克类型
	private int type;
	//封装坦克对象
	private Tank tank=null;
	//实现重绘的画笔
	private Graphics g = null;
	//实现重绘的画板
	private MyPanel mp = null;
	//构造方法
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
