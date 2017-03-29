/*
파일명 가져오기,,
user가 단어를 바꿀 때 마다 화면에 뿌려지도록! 

p_center 다시그려야하니까 재정의해야 한다.

- 고등어 내려오게 하려면?
몇초 간격으로! Thread로......................
Thread 만들어보자 ㅎ_ㅎ

-여러개 출력하려면 어떻게 해야하나?
현재 우리는 여러개의 y필요.....이 y를 멤버변수가 아니라 객체라는 사물로 하나 만들어서
y를 객체로 만들자.

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
	GameWindow gameWindow; // 내 주인은 게임윈도우임을 알려준다.

	JPanel p_west; // 왼쪽 컨트롤 영역
	JPanel p_center; // 단어 그래픽 처리 영역

	//p_west 디자인하기
	JLabel la_user; // 게임 로그인한 사람 뜨는
	JLabel la_score; // 게임 점수
	Choice choice; // 단어선택 drop박스
	JTextField t_input; // 게임 단어 입력창
	JButton bt_start; // 게임 시작 버튼
	JButton bt_pause; // 게임 멈추기
	String res = "C:/java_workspace2/Project0329/res/";
	
	FileInputStream fis;
	InputStreamReader reader; //파일을 대상으로 문자스트림
	BufferedReader buffr; //문자 기반 버퍼스트림
	
	//보관하려면? 조사한 단어를 ArrayList에 담아놓자! 게임에 써먹으려고!!
	ArrayList<String> wordList = new ArrayList<String>(); //현재 size는 0
	Thread thread; //단어게임을 진행할 쓰레드!
	boolean flag = true; //게임을 멈출 수 있는 변수값이 필요하다. -> 쓰레드 제어방법이다.
	//int y=100; //단어의 현재 y축 값. 단어를 내려오게 할 변수값. -> 이제 필요없다!
	ArrayList<Word> words = new ArrayList<Word>();  //태어나는 word들 담아놓는 곳!
	

	public GamePanel(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
		
		setLayout(new BorderLayout());

		p_west = new JPanel();
		
		//p_center = new JPanel()
		//이 영역은 지금부터 그림을 그릴 영역. 글씨를 올리기 위해
		p_center = new JPanel(){
			public void paint(Graphics g) {
				//기존그림 지우기
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, 750, 700);
				
				//다시그리기
				g.setColor(Color.blue);
				//현재는 고등어 1개씩 출력,,여러개일경우 how..?
				//g.drawString("고등어", 0, y);
				//render는 g가 필요하므로 여기서 호출한다.
				//모든 워드들에 대한 render호출!
				for(int i=0; i<words.size(); i++){
					words.get(i).render(g);				
				}
			}			
		};

		la_user = new JLabel("Jee 님");
		la_score = new JLabel("30점");
		choice = new Choice();
		t_input = new JTextField(10);
		bt_start = new JButton("Start");
		bt_pause = new JButton("Stop");

		p_west.setPreferredSize(new Dimension(150, 700));
		p_west.setBackground(Color.lightGray);
		
		choice.add("카테고리 선택▼ ");
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
		setVisible(false); // 최초에 loginForm보여주기 위해 gamePanel은 false로
		setPreferredSize(new Dimension(900, 700));
		
		getCategory();
		
	}
	
	//초이스 컴포넌트에 채워질 파일 명 조사
	public void getCategory() {
		//choice에서 res에서 txt인 것만 잡아낸다. -> 디렉토리 접근하려면 file이용해야한다.
		//file class 필요하다.
		//res 디렉터리를 전부 다 조사 들어간다.
		File file = new File(res);
		//디렉토리, 파일, 이미지 등 에서 파일만 따로 빼야한다. 이렇게하면 파일+디렉터리 섞여 있다.
		File[] files = file.listFiles();
		//마구잡이로 섞여있으므로 파일 찾아줘야한다.
		for(int i=0; i<files.length; i++){
			if(files[i].isFile()){
				//이중에서도 확장자가 txt인것을 찾자? -> 확장자의 기준은 .!
				String name = files[i].getName(); //memo.txt
				//.이 특수문자가 아닌 문자임을 알려주기 위해 \\해줌! 배열로 받아진다.
				String[] arr = name.split("\\."); 
				//split에 의해 배열이 만들어진다 -> memo.txt라고했을때 .앞에 memo는 0번째
				//.뒤에 txt는 1번째로 배열이 들어가진다. 그러므로
				//arr[1].equals가 txt라면 = 메모장이라면
				if(arr[1].equals("txt")){ 
					choice.add(name);	
				}				
			}			
		}
	}
	
	//단어 읽어오기 파일명은 ? choice의 component에 있음
	public void getWord() {
		//인덱스 받아오는
		int index = choice.getSelectedIndex();		
		//첫번째 요소는 뺴고!!!
		if(index!=0){
			String name = choice.getSelectedItem();
			System.out.println(res+name);
			
			try {
				fis = new FileInputStream(res+name);
				reader = new InputStreamReader(fis, "utf-8");
				//한글자씩 읽히므로 BufferedReader써서 줄대로 읽히도록 빨대로 감싼다.
				//스트림을 버퍼 처리 수준까지 올림!!
				buffr = new BufferedReader(reader);
				
				String data;
				while(true){
					data = buffr.readLine(); //이게바로 한줄!
					if(data==null)break;
					System.out.println(data);
					//담자
					wordList.add(data);
				}
				//준비된 단어를 화면에 보여주기
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
	
	//단어를 만드는 곳
	public void createWord() {
		for(int i=0; i<wordList.size(); i++) {
			String name = wordList.get(i);
			Word word = new Word(name, (i*(75)+10), 100);
			
			//태어나는 word들을 words Array에 담ㅇ자 -> 워드 객체 명단 만들기
			words.add(word);
			//얘가 화면에 보일릭 없다. Word가 보유하고있는 tick이랑 render가 호출된 적이 없기 때문ㅇㅔ
			//게임의 심장부에서 tick과 render계속 호출해야함
		}
	}
	
	//게임시작
	public void startGame() {
		//위에 있는 thread가 한번도 메모리에 올라온 적 없다면?
		if(thread==null){
			thread = new Thread(this); //이렇게 해야 우리의 Runnable이 수행
			thread.start();	
		}
	}
	
	//게임중지
	public void pauseGame() {	
	}

	/* 이제 더이상 의미x..!!
	//단어 내려오는 효과..? 어떤효과를줘야할까. -> paint 메서드 안쪽에 실마리가 있다.
	public void down() {	
		//모든 단어들의 y값 증가시키고
		//y+=20;
		//p_center로 하여금 그림을 다시 그리게 유도
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
			//모든 단어들에 대해서 tick()
			for(int i=0; i<words.size();i++){
				words.get(i).tick();				
			}
			//render는 여기서 호출 불가능!! 그치만 repaint는 해줘야함
			p_center.repaint();
		}
	}
}
