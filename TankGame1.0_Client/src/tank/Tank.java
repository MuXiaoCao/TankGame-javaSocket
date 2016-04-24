package tank;


import javax.swing.JOptionPane;

import tool.Point;
import tool.Tool;

/**
 * �Զ���̹�˸��࣬��¼��̹�˵�������� 1.���꣨���Ͻǣ� 2.̹����ʻ���� 3.̹������ 4.̹��Ѫ�� 5.̹���ٶ�
 * 
 * @author С��
 * 
 */
public class Tank {
	// ̹�˵�����
	private int x;
	private int y;
	// ̹�˵ķ���
	private int dir;
	// ̹�˵�����
	private int type;
	// ̹�˵�Ѫ��
	private int HP;
	// ̹�˵��ٶ�
	private int speed;
	// ̹�˵��ӵ�
	Buttle my_Buttle = null;

	public Buttle getMy_Buttle() {
		return my_Buttle;
	}

	public void setMy_Buttle(Buttle my_Buttle) {
		this.my_Buttle = my_Buttle;
	}

	public Tank(int type) {
		super();
		this.type = type;
	}

	public int getX() {
		return x;
	}

	@Override
	public String toString() {
		return "Tank [HP=" + HP + ", dir=" + dir + ", type=" + type + ", x="
				+ x + ", y=" + y + "]";
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * �ж��Ƿ�����
	 * 
	 * @return trueΪ������falseΪ���
	 */
	public boolean is_Dath() {
		if (this.HP <= 0) {
			if (this.getType() == Tool.myTank1) {
				// ������ʤ
				Tool.game_stop = true;
				Tool.game_over = true;
				JOptionPane.showMessageDialog(null, "������ʤ������");

			} else if (this.getType() == Tool.myTank2) {
				// �̷���ʤ
				Tool.game_stop = true;
				Tool.game_over = true;
				JOptionPane.showMessageDialog(null, "�̷���ʤ������");
			}
			return true;
		}
		return false;
	}

	/**
	 * �ж�ǰ���Ƿ���̹��
	 * 
	 * @param tank
	 *            Ŀ��̹��
	 * @return true��ʾǰ����̹�ˣ���Ҫ������
	 */
	public boolean is_haveTank(Tank tank) {
		if (tank.getHP()<=0) {
			return false;
		}
		switch (dir) {
		case Tool.up:
		case Tool.down:
			if (tank.getDir() == Tool.up || tank.getDir() == Tool.down) {
				if (x >= tank.getX() - 20 && x <= tank.getX() + 20
						&& y >= tank.getY() - 30 && y <= tank.getY() + 30) {
					return true;
				}
			} else {
				if (x >= tank.getX() - 20 && x <= tank.getX() + 30
						&& y <= tank.getY() + 20 && y >= tank.getY() - 30) {
					return true;
				}
			}
			break;
		case Tool.left:
		case Tool.right:
			if (tank.getDir() == Tool.up || tank.getDir() == Tool.down) {
				if (x >= tank.getX() - 30 && x <= tank.getX() + 20
						&& y >= tank.getY() + 30 && y <= tank.getY() - 20) {
					return true;
				}
			} else {
				if (x >= tank.getX() - 30 && x <= tank.getX() + 30
						&& y <= tank.getY() + 20 && y >= tank.getY() - 20) {
					return true;
				}
			}
			break;
		default:
			break;
		}
		return false;
	}

	/**
	 * �ж��Ƿ񵽴�߽�
	 * 
	 * @return trueΪ����߽磬falseΪ������Χ
	 */
	public boolean is_isBorder() {
		switch (dir) {
		case Tool.up:
			if (y <= Tool.border_up) {
				return true;
			}
			break;
		case Tool.down:
			if (y >= Tool.border_down) {
				return true;
			}
			break;
		case Tool.right:
			if (x >= Tool.border_right) {
				return true;
			}
			break;
		case Tool.left:
			if (x <= Tool.border_left) {
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}

	/**
	 * ̹��run���� tankǰ��һ��
	 */
	public void run() {
		switch (dir) {
		case Tool.up:
			y -= speed;
			break;
		case Tool.down:
			y += speed;
			break;
		case Tool.left:
			x -= speed;
			break;
		case Tool.right:
			x += speed;
			break;
		default:
			break;
		}
	}

	/**
	 * �任���������ػ�̹��ʱ���������ʵ��
	 */
	public void changeDir() {
		int n = ((int) (Math.random() * 10 + 1) % 3 + dir) % 4;
		switch (n) {
		case Tool.up:
			dir = Tool.up;
			break;
		case Tool.down:
			dir = Tool.down;
			break;
		case Tool.left:
			dir = Tool.left;
			break;
		case Tool.right:
			dir = Tool.right;
			break;
		default:
			break;
		}
	}

	/**
	 * ̹���ڶ�Ӧ�����ϵ�run�����ڼ���̹���ػ�ʱ��
	 */
	public void run_Up() {
		this.setY(this.getY() - this.getSpeed());
	}

	public void run_Down() {
		this.setY(this.getY() + this.getSpeed());
	}

	public void run_Left() {
		this.setX(this.getX() - this.getSpeed());
	}

	public void run_Right() {
		this.setX(this.getX() + this.getSpeed());
	}

	/**
	 * �����ӵ������㲢��ֵ�ӵ��ĳ�ʼλ�ü����򣬲������Ƿ����ӵ�ʧЧ
	 */
	public void shot() {
		// �ж��ӵ��Ƿ�ʧЧ�����ʧЧ�ӵ���Ϊnull
		if (my_Buttle != null) {
			if (my_Buttle.is_dath1()) {
				my_Buttle = null;
				return;
			}
		} else {
			// �����ӵ��ĳ�ʼλ���Լ�����
			Point p = null;
			switch (dir) {
			case Tool.up:
				p = new Point(x + 6, y);
				break;
			case Tool.down:
				p = new Point(x + 6, y + 30);
				break;
			case Tool.left:
				p = new Point(x, y + 6);
				break;
			case Tool.right:
				p = new Point(x + 30, y + 6);
				break;
			default:
				break;
			}
			my_Buttle = new Buttle(p, dir);
		}
	}
}
