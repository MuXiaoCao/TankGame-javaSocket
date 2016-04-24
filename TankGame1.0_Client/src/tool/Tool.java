package tool;

import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import tank.EnemyTank;
import tank.MyTank;
import tank.Tank;

/**
 * 工具类 包含了界面的参数，坦克的参数以及区域参数
 * 
 * @author 小草
 *
 */
public final class Tool {

	public static Tank[] mytank = new MyTank[2];
	public static Tank[] enemeytank = new EnemyTank[4];
	// 界面编码
	// 界面宽度
	public static int width = 753;
	// 界面高度
	public static int high = 500;
	// 界面边界
	public static int border_up = 0;
	public static int border_down = high - 30;
	public static int border_left = 0;
	public static int border_right = width - 30;

	// 子弹的射程
	public static final int buttle_length = 800;
	// 子弹的速度
	public static final int buttle_speel = 8;

	// 坦克编码
	// 坦克位置
	// public static Point myTank1_Point = new Point(width / 2 + 55,high - 31);
	// public static Point myTank2_Point = new Point(width / 2 - 75,high - 31);
	public static Point myTank1_Point = new Point(423, 431);
	public static Point myTank2_Point = new Point(293, 431);
	public static Point point_1 = new Point(1, 1);
	public static Point point_2 = new Point(737 / 3, 1);
	public static Point point_3 = new Point(737 * 2 / 3, 1);
	public static Point point_4 = new Point(737 - 30, 1);
	// 坦克的类型
	public static final int myTank1 = -1;
	public static final int myTank2 = 0;
	public static final int enemyTank_1 = 1;
	public static final int enemyTank_2 = 2;
	public static final int enemyTank_3 = 3;
	public static final int enemyTank_4 = 4;
	// 坦克方向
	public static final int up = 0;
	public static final int down = 1;
	public static final int left = 2;
	public static final int right = 3;
	// 坦克的速度
	public static final int speed_1 = 1;
	public static final int speed_2 = 2;
	public static final int speed_3 = 3;
	public static final int speed_4 = 4;
	// 坦克的血量
	public static final int HP_1 = 1;
	public static final int HP_2 = 2;
	public static final int HP_3 = 3;
	public static final int HP_4 = 4;

	// 障碍区域
	public static HashMap<Point, Point> dengerSize = new HashMap<Point, Point>();
	// 游戏是否结束的标志
	public static boolean game_over = false;
	// 游戏是否暂停的标志
	public static boolean game_stop = false;
	// 传送数据
	public static String game_data;
	public static String my_data;

	/**
	 * 刷新初始点和边界点
	 */
	public static void refresh() {
		border_down = high - 30;
		border_right = width - 30;
	}

	/**
	 * 添加危险区域
	 * 
	 * @param t
	 *            添加t坦克的所属区域
	 */
	public static void add_size(Tank t) {
		Point p = new Point(t.getX(), t.getY());
		Point p1 = null;
		switch (t.getDir()) {
		case Tool.up:
		case Tool.down:
			p1 = new Point(p.getX() + 20, p.getY() + 30);
			break;
		case Tool.right:
		case Tool.left:
			p1 = new Point(p.getX() + 30, p.getY() + 20);
			break;
		default:
			break;
		}
		dengerSize.put(p, p1);
	}

	/**
	 * 判断坦克t是否到达不可入区域，即碰壁
	 * 
	 * @param t
	 *            目标坦克
	 * @return true为碰壁，需要停止，false为在活动区域，无需停止
	 */
	public static boolean is_touth(Tank t) {
		if (dengerSize.isEmpty()) {
			return false;
		}
		Point p = new Point(t.getX(), t.getY());
		Set<Entry<Point, Point>> entry = dengerSize.entrySet();
		for (Entry<Point, Point> entry2 : entry) {

		}
		return false;
	}
}
