/*
���ϸ� ��������,,
user�� �ܾ �ٲ� �� ���� ȭ�鿡 �ѷ�������! 

p_center �ٽñ׷����ϴϱ� �������ؾ� �Ѵ�.

- ���� �������� �Ϸ���?
���� ��������! Thread��......................
Thread ������ ��_��

-������ ����Ϸ��� ��� �ؾ��ϳ�?
���� �츮�� �������� y�ʿ�.....�� y�� ��������� �ƴ϶� ��ü��� �繰�� �ϳ� ����
y�� ��ü�� ������.

 */

package game.word;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class GamePanel extends JPanel implements ItemListener, Runnable, ActionListener{
	GameWindow gameWindow; // �� ������ �������������� �˷��ش�.

	JPanel p_west; // ���� ��Ʈ�� ����
	JPanel p_center; // �ܾ� �׷��� ó�� ����

	//p_west �������ϱ�
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
	Thread thread; //�ܾ������ ������ ������!
	boolean flag = true; //������ ���� �� �ִ� �������� �ʿ��ϴ�. -> ������ �������̴�.
	//int y=100; //�ܾ��� ���� y�� ��. �ܾ �������� �� ������. -> ���� �ʿ����!
	ArrayList<Word> words = new ArrayList<Word>();  //�¾�� word�� ��Ƴ��� ��!
	

	public GamePanel(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
		
		setLayout(new BorderLayout());

		p_west = new JPanel();
		
		//p_center = new JPanel()
		//�� ������ ���ݺ��� �׸��� �׸� ����. �۾��� �ø��� ����
		p_center = new JPanel(){
			public void paint(Graphics g) {
				//�����׸� �����
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, 750, 700);
				
				//�ٽñ׸���
				g.setColor(Color.blue);
				//����� ���� 1���� ���,,�������ϰ�� how..?
				//g.drawString("����", 0, y);
				//render�� g�� �ʿ��ϹǷ� ���⼭ ȣ���Ѵ�.
				//��� ����鿡 ���� renderȣ��!
				for(int i=0; i<words.size(); i++){
					words.get(i).render(g);				
				}
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
		
		bt_start.addActionListener(this);
		bt_pause.addActionListener(this);

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
				//�غ�� �ܾ ȭ�鿡 �����ֱ�
				createWord();
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
	
	//�ܾ ����� ��
	public void createWord() {
		for(int i=0; i<wordList.size(); i++) {
			String name = wordList.get(i);
			Word word = new Word(name, (i*(75)+10), 100);
			
			//�¾�� word���� words Array�� �㤷�� -> ���� ��ü ��� �����
			words.add(word);
			//�갡 ȭ�鿡 ���ϸ� ����. Word�� �����ϰ��ִ� tick�̶� render�� ȣ��� ���� ���� ��������
			//������ ����ο��� tick�� render��� ȣ���ؾ���
		}
	}
	
	//���ӽ���
	public void startGame() {
		//���� �ִ� thread�� �ѹ��� �޸𸮿� �ö�� �� ���ٸ�?
		if(thread==null){
			thread = new Thread(this); //�̷��� �ؾ� �츮�� Runnable�� ����
			thread.start();	
		}
	}
	
	//��������
	public void pauseGame() {	
	}

	/* ���� ���̻� �ǹ�x..!!
	//�ܾ� �������� ȿ��..? �ȿ��������ұ�. -> paint �޼��� ���ʿ� �Ǹ����� �ִ�.
	public void down() {	
		//��� �ܾ���� y�� ������Ű��
		//y+=20;
		//p_center�� �Ͽ��� �׸��� �ٽ� �׸��� ����
		p_center.repaint();
	}*/

	public void itemStateChanged(ItemEvent e) {
		getWord();	
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==bt_start){
			startGame();		
		} else if(obj==bt_pause) {
			pauseGame();	
		}	
	}

	
	public void run() {
		while(flag){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//down();
			//��� �ܾ�鿡 ���ؼ� tick()
			for(int i=0; i<words.size();i++){
				words.get(i).tick();				
			}
			//render�� ���⼭ ȣ�� �Ұ���!! ��ġ�� repaint�� �������
			p_center.repaint();
		}
	}
}
