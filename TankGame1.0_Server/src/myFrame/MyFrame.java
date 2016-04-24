package myFrame;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import net.ReceiveThread;
import tool.Game_Net;
import tool.Tool;

/**
 * 自定义窗口，继承了JFrame 设置窗口属性，并添加功能
 * 
 * @author 小草
 * 
 */
public class MyFrame extends JFrame implements ActionListener {

	Thread t = null;
	MyPanel mp = null;
	JMenuBar bar = null; // 菜单栏
	JMenu jm1 = null; // 菜单选项
	JMenu jm2 = null;
	JMenuItem ji = null; // 子菜单
	JMenuItem ji1 = null;
	JMenuItem ji2 = null;
	JMenuItem ji3 = null;

	// 玩家
	ReceiveThread player = null;
	public MyFrame() throws HeadlessException {
		super();

		bar = new JMenuBar();
		jm1 = new JMenu("游戏设置");
		jm2 = new JMenu("帮助");
		ji = new JMenuItem("游戏规则");
		ji1 = new JMenuItem("暂停");
		ji2 = new JMenuItem("退出");
		ji3 = new JMenuItem("继续");
		jm2.add(ji);
		jm1.add(ji1);
		jm1.add(ji2);
		jm1.add(ji3);
		bar.add(jm1);
		bar.add(jm2);
		this.setJMenuBar(bar);
		// 设置窗口初始位置为屏幕中间
		this.setLocation((int) Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth() / 4, (int) Toolkit.getDefaultToolkit()
				.getScreenSize().getHeight() / 4);
		// 设置标题
		this.setTitle("坦克大战1.0");
		// 设置窗口大小
		this.setSize(tool.Tool.width, tool.Tool.high);
		// 创建自定义画板对象
		mp = new MyPanel();
		// 开启mp的线程
		t = new Thread(mp);
		t.start();
		// 添加画板
		this.add(mp);
		
		//创建玩家线程
		try {
			Game_Net.connection(mp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 设置为窗口可见
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ji2) {// 退出菜单
			Tool.game_over = true;
			System.exit(0);
		} else if (e.getSource() == ji) { // 游戏规则提示
			 //Tool.game_stop = true;
			 JOptionPane.showMessageDialog(null, "1 'w s a d'控制蓝色坦克    "
			 + "2 '↑ ↓ ← →'控制黄色坦克" + "  3 'j 0' 分别是发射子弹", "游戏帮助",
			 JOptionPane.QUESTION_MESSAGE);
			
		} else if (e.getSource() == ji1) {// 暂停
			Tool.game_stop = true;
		} else if (e.getSource() == ji3) {// 继续
			Tool.game_stop = false;
		}

	}

	public void addListener() {
		// 帮助菜单监听
		ji.addActionListener(this);
		// 暂停菜单监听
		ji1.addActionListener(this);
		// 退出菜单监听
		ji2.addActionListener(this);
		// 继续菜单监听
		ji3.addActionListener(this);
		// 实现两个己方坦克的监听
		this.addKeyListener(mp.mytankThread[0]);
		//this.addKeyListener(mp.mytankThread[1]);
	}

}
