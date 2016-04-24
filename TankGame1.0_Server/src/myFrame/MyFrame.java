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
 * �Զ��崰�ڣ��̳���JFrame ���ô������ԣ�����ӹ���
 * 
 * @author С��
 * 
 */
public class MyFrame extends JFrame implements ActionListener {

	Thread t = null;
	MyPanel mp = null;
	JMenuBar bar = null; // �˵���
	JMenu jm1 = null; // �˵�ѡ��
	JMenu jm2 = null;
	JMenuItem ji = null; // �Ӳ˵�
	JMenuItem ji1 = null;
	JMenuItem ji2 = null;
	JMenuItem ji3 = null;

	// ���
	ReceiveThread player = null;
	public MyFrame() throws HeadlessException {
		super();

		bar = new JMenuBar();
		jm1 = new JMenu("��Ϸ����");
		jm2 = new JMenu("����");
		ji = new JMenuItem("��Ϸ����");
		ji1 = new JMenuItem("��ͣ");
		ji2 = new JMenuItem("�˳�");
		ji3 = new JMenuItem("����");
		jm2.add(ji);
		jm1.add(ji1);
		jm1.add(ji2);
		jm1.add(ji3);
		bar.add(jm1);
		bar.add(jm2);
		this.setJMenuBar(bar);
		// ���ô��ڳ�ʼλ��Ϊ��Ļ�м�
		this.setLocation((int) Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth() / 4, (int) Toolkit.getDefaultToolkit()
				.getScreenSize().getHeight() / 4);
		// ���ñ���
		this.setTitle("̹�˴�ս1.0");
		// ���ô��ڴ�С
		this.setSize(tool.Tool.width, tool.Tool.high);
		// �����Զ��廭�����
		mp = new MyPanel();
		// ����mp���߳�
		t = new Thread(mp);
		t.start();
		// ��ӻ���
		this.add(mp);
		
		//��������߳�
		try {
			Game_Net.connection(mp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// ����Ϊ���ڿɼ�
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ji2) {// �˳��˵�
			Tool.game_over = true;
			System.exit(0);
		} else if (e.getSource() == ji) { // ��Ϸ������ʾ
			 //Tool.game_stop = true;
			 JOptionPane.showMessageDialog(null, "1 'w s a d'������ɫ̹��    "
			 + "2 '�� �� �� ��'���ƻ�ɫ̹��" + "  3 'j 0' �ֱ��Ƿ����ӵ�", "��Ϸ����",
			 JOptionPane.QUESTION_MESSAGE);
			
		} else if (e.getSource() == ji1) {// ��ͣ
			Tool.game_stop = true;
		} else if (e.getSource() == ji3) {// ����
			Tool.game_stop = false;
		}

	}

	public void addListener() {
		// �����˵�����
		ji.addActionListener(this);
		// ��ͣ�˵�����
		ji1.addActionListener(this);
		// �˳��˵�����
		ji2.addActionListener(this);
		// �����˵�����
		ji3.addActionListener(this);
		// ʵ����������̹�˵ļ���
		this.addKeyListener(mp.mytankThread[0]);
		//this.addKeyListener(mp.mytankThread[1]);
	}

}
