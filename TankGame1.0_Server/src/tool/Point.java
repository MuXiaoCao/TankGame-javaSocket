package tool;

/**
 * 自定义点
 * @author 木小草
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
	 * 判断该点是否在p1和p2所构成的矩形当中
	 * @param p1 矩形的一个端点
	 * @param p2 矩形的另一个端点
	 * @return true表示在该矩形当中
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
