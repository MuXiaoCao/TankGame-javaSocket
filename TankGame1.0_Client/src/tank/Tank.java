package tank;


import javax.swing.JOptionPane;

import tool.Point;
import tool.Tool;

/**
 * 自定义坦克父类，记录了坦克的五大属性 1.坐标（左上角） 2.坦克行驶方向 3.坦克类型 4.坦克血量 5.坦克速度
 * 
 * @author 小草
 * 
 */
public class Tank {
	// 坦克的坐标
	private int x;
	private int y;
	// 坦克的方向
	private int dir;
	// 坦克的类型
	private int type;
	// 坦克的血量
	private int HP;
	// 坦克的速度
	private int speed;
	// 坦克的子弹
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
	 * 判断是否阵亡
	 * 
	 * @return true为阵亡，false为存活
	 */
	public boolean is_Dath() {
		if (this.HP <= 0) {
			if (this.getType() == Tool.myTank1) {
				// 蓝方获胜
				Tool.game_stop = true;
				Tool.game_over = true;
				JOptionPane.showMessageDialog(null, "蓝方获胜！！！");

			} else if (this.getType() == Tool.myTank2) {
				// 绿方获胜
				Tool.game_stop = true;
				Tool.game_over = true;
				JOptionPane.showMessageDialog(null, "绿方获胜！！！");
			}
			return true;
		}
		return false;
	}

	/**
	 * 判断前方是否有坦克
	 * 
	 * @param tank
	 *            目标坦克
	 * @return true表示前方有坦克，需要换方向
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
	 * 判断是否到达边界
	 * 
	 * @return true为到达边界，false为正常范围
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
	 * 坦克run方法 tank前进一步
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
	 * 变换方向，用于重绘坦克时，如果碰壁实现
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
	 * 坦克在对应方向上的run，用于己方坦克重绘时用
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
	 * 发射子弹，计算并赋值子弹的初始位置及方向，并检验是否有子弹失效
	 */
	public void shot() {
		// 判断子弹是否失效，如果失效子弹变为null
		if (my_Buttle != null) {
			if (my_Buttle.is_dath1()) {
				my_Buttle = null;
				return;
			}
		} else {
			// 设置子弹的初始位置以及方向
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
