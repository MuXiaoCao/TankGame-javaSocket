package thread;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

import tank.Buttle;
import tank.MyTank;
import tank.Tank;
import tool.Tool;
import myFrame.MyPanel;

/**
 * ����̹�ˣ��̳����Զ����̣߳������������̣߳�ֻʵ�ֽӿڹ���
 * 
 * @author С��
 * 
 */
public class MytankThread extends MyThread implements KeyListener {

	private static final Tank MyTank = null;
	// �������ҵĲ������ͼ�¼�����ڼ�¼��Ӧ���Ƿ��»����ɿ�
	boolean up, down, left, right;
	public static boolean w, s, a, d;
	public static boolean shot;
	/**
	 * ���췽��
	 * 
	 * @param type
	 *            ̹������
	 * @param mp
	 *            ����
	 */
	public MytankThread(int type, MyPanel mp) {
		super(type, mp);
		this.setType(type);
		this.setMp(mp);
		this.setTank(new MyTank(type));
	}

	@Override
	public void run() {
		while (this.getTank().getHP() > 0 &&!Tool.game_over) {
			while ( !Tool.game_stop) {
				try {
					new Thread().sleep(10);
					// ̹���ӵ���һ��
					MyTank m = (MyTank) this.getTank();
					Iterator<Buttle> it = m.getMy_Buttles().iterator();
					while (it.hasNext()) {
						it.next().run();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (this.getType() == Tool.myTank1) {
					if (up) {
						this.getTank().setDir(Tool.up);
						if (!this.getTank().is_isBorder()) {
							this.getTank().run_Up();
						}

					} else if (down) {
						this.getTank().setDir(Tool.down);
						if (!this.getTank().is_isBorder()) {
							this.getTank().run_Down();
						}
					} else if (left) {
						this.getTank().setDir(Tool.left);
						if (!this.getTank().is_isBorder()) {
							this.getTank().run_Left();
						}
					} else if (right) {
						this.getTank().setDir(Tool.right);
						if (!this.getTank().is_isBorder()) {
							this.getTank().run_Right();
						}
					}

				} else if (this.getType() == Tool.myTank2) {

					if (w) {
						this.getTank().setDir(Tool.up);
						if (!this.getTank().is_isBorder()) {
							this.getTank().run_Up();
						}

					} else if (s) {
						this.getTank().setDir(Tool.down);
						if (!this.getTank().is_isBorder()) {
							this.getTank().run_Down();
						}
					} else if (a) {
						this.getTank().setDir(Tool.left);
						if (!this.getTank().is_isBorder()) {
							this.getTank().run_Left();
						}
					} else if (d) {
						this.getTank().setDir(Tool.right);
						if (!this.getTank().is_isBorder()) {
							this.getTank().run_Right();
						}
					}
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * �����Ƿ��ж�Ӧ�����£�������¶�Ӧ��¼��Ϊtrue����ʵ��̹�˵�run����
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("��������");
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			//System.out.println(up);
			up = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_W:
			w = true;
			break;
		case KeyEvent.VK_S:
			s = true;
			break;
		case KeyEvent.VK_A:
			a = true;
			break;
		case KeyEvent.VK_D:
			d = true;
			break;
		case KeyEvent.VK_J:
			shot = true;
			break;
		default:
			break;
		}

	}

	/**
	 * �����Ƕ�Ӧ���Ƿ��ͷţ�����ͷţ���Ӧ��¼��Ϊflase
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_W:
			w = false;
			break;
		case KeyEvent.VK_S:
			s = false;
			break;
		case KeyEvent.VK_A:
			a = false;
			break;
		case KeyEvent.VK_D:
			d = false;
			break;
		case KeyEvent.VK_J:
			shot = false;
			break;
		default:
			break;
		}

	}
}
