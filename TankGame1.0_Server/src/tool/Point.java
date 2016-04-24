package tool;

/**
 * �Զ����
 * @author ľС��
 *
 */
public class Point {
	private int x;
	private int y;
	public int getX() {
		return x;
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
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * �жϸõ��Ƿ���p1��p2�����ɵľ��ε���
	 * @param p1 ���ε�һ���˵�
	 * @param p2 ���ε���һ���˵�
	 * @return true��ʾ�ڸþ��ε���
	 */
	public boolean is_contain(Point p1, Point p2) {
		if ((x-p1.getX())*(x-p2.getX())<=0) {
			if ((y-p1.getY())*(y-p2.getY())<=0) {
				return true;
			}
		}
		return false;
	}
}
