package thread;

import myFrame.MyPanel;
import tank.EnemyTank;
import tool.Tool;

/**
 * 自定义线程的子类，用于普通坦克的线程
 * 
 * @author 小草
 * 
 */
public class TankThread extends MyThread {

	/**
	 * 普通坦克构造方法
	 * 
	 * @param type
	 *            类型
	 * @param mp
	 *            画板
	 */
	public TankThread(int type, MyPanel mp) {
		super(type, mp);
		this.setType(type);
		this.setMp(mp);
		this.setTank(new EnemyTank(type));

	}

	/**
	 * 普通坦克的run方法，实现此线程下坦克的移动以及坦克的重绘
	 */
	@Override
	public void run() {
		while (this.getTank().getHP() > 0 &&!Tool.game_over) {
			while ( !Tool.game_stop) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 坦克走一步
				this.getTank().run();
				// 如果子弹失效，坦克发射一发子弹
				this.getTank().shot();
				// 如果子弹存在，子弹走一步
				if (this.getTank().getMy_Buttle() != null) {
					this.getTank().getMy_Buttle().run();
				}
			}
		}
	}
}
