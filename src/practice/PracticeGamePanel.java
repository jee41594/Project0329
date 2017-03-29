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
	GameWindow gameWindow; // 내 주인은 게임윈도우임을 알려준다.

	JPanel p_west; // 왼쪽 컨트롤 영역
	JPanel p_center; // 단어 그래픽 처리 영역

	// p_west 디자인하기
	JLabel la_user; // 게임 로그인한 사람 뜨는
	JLabel la_score; // 게임 점수
	Choice choice; // 단어선택 drop박스
	JTextField t_input; // 게임 단어 입력창
	JButton bt_start; // 게임 시작 버튼
	JButton bt_pause; // 게임 멈추기
	String res = "C:/java_workspace2/Project0329/res";
	
	public PracticeGamePanel(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
		
		setLayout(new BorderLayout());

		p_west = new JPanel();
		p_center = new JPanel();

		la_user = new JLabel("지헤님");
		la_score = new JLabel("30점");
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

}
