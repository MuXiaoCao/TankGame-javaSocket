package tank;

import java.util.ArrayList;
import java.util.Iterator;

import tool.Point;
import tool.Tool;

/**
 * 己方坦克类，继承自定义坦克，设置了己方坦克类型的属性值
 * 
 * @author 小草
 *
 */
public class MyTank extends Tank {

	ArrayList<Buttle> my_Buttles = new ArrayList<Buttle>();

	public ArrayList<Buttle> getMy_Buttles() {
		return my_Buttles;
	}

	public void setMy_Buttles(ArrayList<Buttle> my_Buttles) {
		this.my_Buttles = my_Buttles;
	}

	public MyTank(int type) {
		super(type);
		// 初始位置设定
		switch (type) {
		case Tool.myTank1:
			this.setX(Tool.myTank1_Point.getX());
			this.setY(Tool.myTank1_Point.getY());
			break;
		case Tool.myTank2:
			this.setX(Tool.myTank2_Point.getX());
			this.setY(Tool.myTank2_Point.getY());
			break;
		default:
			break;
		}
		// 设置类型
		this.setType(type);
		// 设置血量，默认为HP_1,即为一滴血
		this.setHP(Tool.HP_1);
		// 设置前进方向，默认为向上
		this.setDir(Tool.up);
		// 设置前进速度，默认为四级速度
		this.setSpeed(Tool.speed_4);
	}

	/**
	 * 可以连发的射击
	 */
	public void my_shot() {
		System.out.println(my_Buttles.size());
		// 判断子弹是否失效，如果失效子弹移除
		Iterator<Buttle> it = my_Buttles.iterator();
		while (it.hasNext()) {
			if (it.next().is_dath1()) {
				it.remove();
			}
		}

		// 设置子弹的初始位置以及方向
		Point p = null;
		switch (this.getDir()) {
		case Tool.up:
			p = new Point(this.getX() + 6, this.getY());
			break;
		case Tool.down:
			p = new Point(this.getX() + 6, this.getY() + 30);
			break;
		case Tool.left:
			p = new Point(this.getX(), this.getY() + 6);
			break;
		case Tool.right:
			p = new Point(this.getX() + 30, this.getY() + 6);
			break;
		default:
			break;
		}
		my_Buttles.add(new Buttle(p, this.getDir()));
	}

	@Override
	public String toString() {
		return "MyTank [toString()=" + super.toString() + "]";
	}

	
	
}