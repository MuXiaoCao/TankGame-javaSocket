package thread;

import myFrame.MyPanel;
import tank.EnemyTank;
import tool.Tool;

/**
 * �Զ����̵߳����࣬������̹ͨ�˵��߳�
 * 
 * @author С��
 * 
 */
public class TankThread extends MyThread {

	/**
	 * ��̹ͨ�˹��췽��
	 * 
	 * @param type
	 *            ����
	 * @param mp
	 *            ����
	 */
	public TankThread(int type, MyPanel mp) {
		super(type, mp);
		this.setType(type);
		this.setMp(mp);
		this.setTank(new EnemyTank(type));

	}

	/**
	 * ��̹ͨ�˵�run������ʵ�ִ��߳���̹�˵��ƶ��Լ�̹�˵��ػ�
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
				// ̹����һ��
				this.getTank().run();
				// ����ӵ�ʧЧ��̹�˷���һ���ӵ�
				this.getTank().shot();
				// ����ӵ����ڣ��ӵ���һ��
				if (this.getTank().getMy_Buttle() != null) {
					this.getTank().getMy_Buttle().run();
				}
			}
		}
	}
}
