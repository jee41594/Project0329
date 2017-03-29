/*
���ϸ� ��������,,
user�� �ܾ �ٲ� �� ���� ȭ�鿡 �ѷ�������! 

p_center �ٽñ׷����ϴϱ� �������ؾ� �Ѵ�.
 */

package game.word;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel implements ItemListener{
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
	String res = "C:/java_workspace2/Project0329/res/";
	
	FileInputStream fis;
	InputStreamReader reader; //������ ������� ���ڽ�Ʈ��
	BufferedReader buffr; //���� ��� ���۽�Ʈ��
	
	//�����Ϸ���? ������ �ܾ ArrayList�� ��Ƴ���! ���ӿ� ���������!!
	ArrayList<String> wordList = new ArrayList<String>(); //���� size�� 0

	public GamePanel(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
		
		setLayout(new BorderLayout());

		p_west = new JPanel();
		
		//p_center = new JPanel()
		//�� ������ ���ݺ��� �׸��� �׸� ����. �۾��� �ø��� ����
		p_center = new JPanel(){
			public void paint(Graphics g) {
				g.drawString("����", 200, 300);
			}			
		};

		la_user = new JLabel("Jee ��");
		la_score = new JLabel("30��");
		choice = new Choice();
		t_input = new JTextField(10);
		bt_start = new JButton("Start");
		bt_pause = new JButton("Stop");

		p_west.setPreferredSize(new Dimension(150, 700));
		p_west.setBackground(Color.lightGray);
		
		choice.add("ī�װ� ���á� ");
		choice.setPreferredSize(new Dimension(135, 40));
		choice.addItemListener(this);	
		
		p_west.add(la_user);
		p_west.add(choice);
		p_west.add(t_input);
		p_west.add(bt_start);
		p_west.add(bt_pause);
		p_west.add(la_score);
		
		add(p_west, BorderLayout.WEST);
		add(p_center);
	
		//setBackground(Color.cyan);
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
	
	//�ܾ� �о���� ���ϸ��� ? choice�� component�� ����
	public void getWord() {
		//�ε��� �޾ƿ���
		int index = choice.getSelectedIndex();		
		//ù��° ��Ҵ� ����!!!
		if(index!=0){
			String name = choice.getSelectedItem();
			System.out.println(res+name);
			
			try {
				fis = new FileInputStream(res+name);
				reader = new InputStreamReader(fis, "utf-8");
				//�ѱ��ھ� �����Ƿ� BufferedReader�Ἥ �ٴ�� �������� ����� ���Ѵ�.
				//��Ʈ���� ���� ó�� ���ر��� �ø�!!
				buffr = new BufferedReader(reader);
				
				String data;
				while(true){
					data = buffr.readLine(); //�̰Թٷ� ����!
					if(data==null)break;
					System.out.println(data);
					//����
					wordList.add(data);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				
				if(buffr!=null){
					try {
						buffr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				if(reader!=null){
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				if(fis!=null){
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
		}
	}
	
	public void itemStateChanged(ItemEvent e) {
		getWord();	
	}

}
