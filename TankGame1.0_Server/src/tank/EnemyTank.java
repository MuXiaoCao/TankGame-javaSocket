package tank;

import tool.Tool;

/**
 * ����̹���࣬�̳����Զ���̹�ˣ�ʵ���˶Եз�̹�˵���������
 * �з�̹�˹����ĸ��ȼ���Ѫ���ֱ�Ϊ1-4���ȼ����ٶ�1-2���ȼ�
 * ���������λ���ĸ�
 * @author С��
 *
 */
public class EnemyTank extends Tank{

	public EnemyTank(int type) {
		super(type);
		//��������
		switch (type) {
		case Tool.enemyTank_1:
			this.setX(Tool.point_1.getX());
			this.setY(Tool.point_1.getY());
			this.setSpeed(Tool.speed_1);
			this.setHP(Tool.HP_1);
			break;
		case Tool.enemyTank_2:
			this.setX(Tool.point_2.getX());
			this.setY(Tool.point_2.getY());
			this.setSpeed(Tool.speed_1);
			this.setHP(Tool.HP_2);
			break;
		case Tool.enemyTank_3:
			this.setX(Tool.point_3.getX());
			this.setY(Tool.point_3.getY());
			this.setSpeed(Tool.speed_2);
			this.setHP(Tool.HP_3);
			break;
		case Tool.enemyTank_4:
			this.setX(Tool.point_4.getX());
			this.setY(Tool.point_4.getY());
			this.setSpeed(Tool.speed_2);
			this.setHP(Tool.HP_4);
			break;
		default:
			break;
		}
		this.setType(type);
		int dir = (int)(Math.random()*10+1)%4;
		switch (dir) {
		case 0:
			this.setDir(Tool.up);
			break;
		case 1:
			this.setDir(Tool.down);
			break;
		case 2:
			this.setDir(Tool.left);
			break;
		case 3:
			this.setDir(Tool.right);
			break;
		default:
			break;
		}
	}
	

}
