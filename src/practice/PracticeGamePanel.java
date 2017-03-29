package practice;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PracticeGamePanel extends JPanel {
	GameWindow gameWindow; // �� ������ �������������� �˷��ش�.

	JPanel p_west; // ���� ��Ʈ�� ����
	JPanel p_center; // �ܾ� �׷��� ó�� ����

	// p_west �������ϱ�
	JLabel la_user; // ���� �α����� ��� �ߴ�
	JLabel la_score; // ���� ����
	Choice choice; // �ܾ�� drop�ڽ�
	JTextField t_input; // ���� �ܾ� �Է�â
	JButton bt_start; // ���� ���� ��ư
	JButton bt_pause; // ���� ���߱�
	String res = "C:/java_workspace2/Project0329/res";
	
	public PracticeGamePanel(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
		
		setLayout(new BorderLayout());

		p_west = new JPanel();
		p_center = new JPanel();

		la_user = new JLabel("�����");
		la_score = new JLabel("30��");
		choice = new Choice();
		t_input = new JTextField(10);
		bt_start = new JButton("Start");
		bt_pause = new JButton("Stop");

		p_west.setPreferredSize(new Dimension(150, 700));
		p_west.setBackground(Color.lightGray);
		p_west.add(la_user);
		p_west.add(choice);
		p_west.add(t_input);
		p_west.add(bt_start);
		p_west.add(bt_pause);
		p_west.add(la_score);
		
		add(p_west, BorderLayout.WEST);
	
		setBackground(Color.cyan);
		setVisible(false); // ���ʿ� loginForm�����ֱ� ���� gamePanel�� false��
		setPreferredSize(new Dimension(900, 700));
		
		getCategory();

	}
	
	//���̽� ������Ʈ�� ä���� ���� �� ����
	public void getCategory() {
		//choice���� res���� txt�� �͸� ��Ƴ���. -> ���丮 �����Ϸ��� file�̿��ؾ��Ѵ�.
		//file class �ʿ��ϴ�.
		
		//res ���͸��� ���� �� ���� ����.
		File file = new File(res);
		//���丮, ����, �̹��� �� ���� ���ϸ� ���� �����Ѵ�. �̷����ϸ� ����+���͸� ���� �ִ�.
		File[] files = file.listFiles();
		//�������̷� ���������Ƿ� ���� ã������Ѵ�.
		for(int i=0; i<files.length; i++){
			if(files[i].isFile()){
				//���߿����� Ȯ���ڰ� txt�ΰ��� ã��? -> Ȯ������ ������ .!
				String name = files[i].getName(); //memo.txt
				//.�� Ư�����ڰ� �ƴ� �������� �˷��ֱ� ���� \\����! �迭�� �޾�����.
				String[] arr = name.split("\\."); 
				//split�� ���� �迭�� ��������� -> memo.txt��������� .�տ� memo�� 0��°
				//.�ڿ� txt�� 1��°�� �迭�� ������. �׷��Ƿ�
				//arr[1].equals�� txt��� = �޸����̶��
				if(arr[1].equals("txt")){ 
					choice.add(name);	
				}
				
			}
			
		}
		
	}

}
