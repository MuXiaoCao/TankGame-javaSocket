package tool;

import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import tank.EnemyTank;
import tank.MyTank;
import tank.Tank;

/**
 * ������ �����˽���Ĳ�����̹�˵Ĳ����Լ��������
 * 
 * @author С��
 *
 */
public final class Tool {

	public static Tank[] mytank = new MyTank[2];
	public static Tank[] enemeytank = new EnemyTank[4];
	// �������
	// ������
	public static int width = 753;
	// ����߶�
	public static int high = 500;
	// ����߽�
	public static int border_up = 0;
	public static int border_down = high - 30;
	public static int border_left = 0;
	public static int border_right = width - 30;

	// �ӵ������
	public static final int buttle_length = 800;
	// �ӵ����ٶ�
	public static final int buttle_speel = 8;

	// ̹�˱���
	// ̹��λ��
	// public static Point myTank1_Point = new Point(width / 2 + 55,high - 31);
	// public static Point myTank2_Point = new Point(width / 2 - 75,high - 31);
	public static Point myTank1_Point = new Point(423, 431);
	public static Point myTank2_Point = new Point(293, 431);
	public static Point point_1 = new Point(1, 1);
	public static Point point_2 = new Point(737 / 3, 1);
	public static Point point_3 = new Point(737 * 2 / 3, 1);
	public static Point point_4 = new Point(737 - 30, 1);
	// ̹�˵�����
	public static final int myTank1 = -1;
	public static final int myTank2 = 0;
	public static final int enemyTank_1 = 1;
	public static final int enemyTank_2 = 2;
	public static final int enemyTank_3 = 3;
	public static final int enemyTank_4 = 4;
	// ̹�˷���
	public static final int up = 0;
	public static final int down = 1;
	public static final int left = 2;
	public static final int right = 3;
	// ̹�˵��ٶ�
	public static final int speed_1 = 1;
	public static final int speed_2 = 2;
	public static final int speed_3 = 3;
	public static final int speed_4 = 4;
	// ̹�˵�Ѫ��
	public static final int HP_1 = 1;
	public static final int HP_2 = 2;
	public static final int HP_3 = 3;
	public static final int HP_4 = 4;

	// �ϰ�����
	public static HashMap<Point, Point> dengerSize = new HashMap<Point, Point>();
	// ��Ϸ�Ƿ�����ı�־
	public static boolean game_over = false;
	// ��Ϸ�Ƿ���ͣ�ı�־
	public static boolean game_stop = false;
	// ��������
	public static String game_data;
	public static String my_data;

	/**
	 * ˢ�³�ʼ��ͱ߽��
	 */
	public static void refresh() {
		border_down = high - 30;
		border_right = width - 30;
	}

	/**
	 * ���Σ������
	 * 
	 * @param t
	 *            ���t̹�˵���������
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
	 * �ж�̹��t�Ƿ񵽴ﲻ�������򣬼�����
	 * 
	 * @param t
	 *            Ŀ��̹��
	 * @return trueΪ���ڣ���Ҫֹͣ��falseΪ�ڻ��������ֹͣ
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
