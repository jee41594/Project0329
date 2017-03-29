/*
 이 윈도우는 크기가 결정되어 있지 않아야 한다.
 왜? 윈도우 안에 들어오게 될 패널이 그 크기를
 결정하게 되므로
 로그인 기능일 때는 작게, 게임 본 화면에서는 크게!
 
1. 페이지 수가 많지 않아서 배열로 x
2. pack() 활용  -> 우리는 윈도우 창을 조절하는게 아니고 내용물의 위치를 정하는 것!
3. how to change the panel like using LoginForm or GamePanel?
	- 컨트롤 타워는윈도우이므로 윈도우 안에다가 어떤 페이지를 가져올지 메서드를 만든다.
	- 게임패널 미리 만들자
 */
package practice;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JFrame {
	LoginForm loginForm;
	PracticeGamePanel gamePanel;
	
	//페이지에 하나씩 담아서 index로 표현하자.
	JPanel[] page = new JPanel[2];
	
	public GameWindow() {
		setLayout(new FlowLayout());
		
		page[0] = new LoginForm(this);
		page[1] = new PracticeGamePanel(this);
		
		add(page[0]);
		add(page[1]);
		
		setPage(0);	
		
		setVisible(true);

	}
	
	//윈도우 안에 어떤 패널이 올지를 결정해주는메서드
	//메서드하면 장점? 또 사요앟ㄹ 수 있다.
	//()안에 원하는 패널이 뭘지 메세지를 주자. -> index를 넘기겠다.
	public void setPage(int index){		
		for(int i=0; i<page.length; i++){
			if(i==index){
				page[i].setVisible(true);	
			} else{
				page[i].setVisible(false);
				
			}
		} 
		pack();
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new GameWindow();
	}
}
