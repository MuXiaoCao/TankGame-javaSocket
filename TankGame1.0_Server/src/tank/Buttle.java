package tank;

import tool.Point;
import tool.Tool;

/**
 * 自定义子弹类，有位置,方向属性，run方法，is_dath方法
 * 
 * @author 木小草
 *
 */
public class Buttle {
	// 子弹的起始位置属性
	private Point pos = null;
	// 子弹的当前位置
	private Point current_pos = null;
	// 子弹的方向属性
	private int dir;

	// 构造方法
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
	 * 判断子弹是否失效，1.子弹超出射程失效 2.子弹击中目标失效
	 * 
	 * @param tank
	 *            目标坦克
	 * @return true为子弹失效
	 */
	public boolean is_dath1() {
		// 子弹超出射程失效
		if (Math.abs(current_pos.getX() - pos.getX()) >= Tool.buttle_length
				|| Math.abs(current_pos.getY() - pos.getY()) >= Tool.buttle_length) {
			return true;
		}
		return false;
	}
	public boolean is_dath2(Tank tank) {
		// 子弹击中目标失效
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
	 * 子弹的run方法
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
