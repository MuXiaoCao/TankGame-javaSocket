package tank;

import tool.Point;
import tool.Tool;

/**
 * �Զ����ӵ��࣬��λ��,�������ԣ�run������is_dath����
 * 
 * @author ľС��
 *
 */
public class Buttle {
	// �ӵ�����ʼλ������
	private Point pos = null;
	// �ӵ��ĵ�ǰλ��
	private Point current_pos = null;
	// �ӵ��ķ�������
	private int dir;

	// ���췽��
	public Buttle(Point pos, int dir) {
		super();
		this.pos = pos;
		this.current_pos = new Point(pos.getX(), pos.getY());
		this.dir = dir;
	}

	public Point getCurrent_pos() {
		return current_pos;
	}

	public void setCurrent_pos(Point current_pos) {
		this.current_pos = current_pos;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	/**
	 * �ж��ӵ��Ƿ�ʧЧ��1.�ӵ��������ʧЧ 2.�ӵ�����Ŀ��ʧЧ
	 * 
	 * @param tank
	 *            Ŀ��̹��
	 * @return trueΪ�ӵ�ʧЧ
	 */
	public boolean is_dath1() {
		// �ӵ��������ʧЧ
		if (Math.abs(current_pos.getX() - pos.getX()) >= Tool.buttle_length
				|| Math.abs(current_pos.getY() - pos.getY()) >= Tool.buttle_length) {
			return true;
		}
		return false;
	}
	public boolean is_dath2(Tank tank) {
		// �ӵ�����Ŀ��ʧЧ
		if (tank == null) {
			return false;
		}
		Point p1 = new Point(tank.getX(), tank.getY());
		Point p2 = null;
		switch (tank.getDir()) {
		case Tool.up:
		case Tool.down:
			p2 = new Point(p1.getX() + 20, p1.getY() + 30);
			if (current_pos.is_contain(p1, p2)) {
				return true;
			}
		case Tool.left:
		case Tool.right:
			p2 = new Point(p1.getX() + 30, p1.getY() + 20);
			if (current_pos.is_contain(p1, p2)) {
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}
	/**
	 * �ӵ���run����
	 */
	public void run() {
		switch (dir) {
		case Tool.up:
			current_pos.setY(current_pos.getY() - Tool.buttle_speel);
			break;
		case Tool.down:
			current_pos.setY(current_pos.getY() + Tool.buttle_speel);
			break;
		case Tool.left:
			current_pos.setX(current_pos.getX() - Tool.buttle_speel);
			break;
		case Tool.right:
			current_pos.setX(current_pos.getX() + Tool.buttle_speel);
			break;
		default:
			break;
		}
	}

}
