package tank;

import tool.Tool;

/**
 * 敌人坦克类，继承了自定义坦克，实现了对敌方坦克的属性设置
 * 敌方坦克共分四个等级，血量分别为1-4个等级，速度1-2个等级
 * 方向随机，位置四个
 * @author 小草
 *
 */
public class EnemyTank extends Tank{

	public EnemyTank(int type) {
		super(type);
		//定义类型
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
