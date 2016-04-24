package tank;

import java.util.ArrayList;
import java.util.Iterator;

import tool.Point;
import tool.Tool;

/**
 * ����̹���࣬�̳��Զ���̹�ˣ������˼���̹�����͵�����ֵ
 * 
 * @author С��
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
		// ��ʼλ���趨
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
		// ��������
		this.setType(type);
		// ����Ѫ����Ĭ��ΪHP_1,��Ϊһ��Ѫ
		this.setHP(Tool.HP_1);
		// ����ǰ������Ĭ��Ϊ����
		this.setDir(Tool.up);
		// ����ǰ���ٶȣ�Ĭ��Ϊ�ļ��ٶ�
		this.setSpeed(Tool.speed_4);
	}

	/**
	 * �������������
	 */
	public void my_shot() {
		System.out.println(my_Buttles.size());
		// �ж��ӵ��Ƿ�ʧЧ�����ʧЧ�ӵ��Ƴ�
		Iterator<Buttle> it = my_Buttles.iterator();
		while (it.hasNext()) {
			if (it.next().is_dath1()) {
				it.remove();
			}
		}

		// �����ӵ��ĳ�ʼλ���Լ�����
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