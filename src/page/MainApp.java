/*
img 넣을 폴더는 일반 폴더 만들어서 buil path해준다.

1. 디자인
2 우리만의 패널을 만들어야 한다. -> customizing
3. 패널을 불러오고
4. 클릭시 어떤건 가려지고 어떤건 나타날지 -> 속상하지 않게 구현해본다.
데이터를 가지고 있는 것들이 개성있는 이름으로 되어있으면 가르켜서 for문을 돌릴 수는없으므로
순서로 배열화 시켜서 구현해야 한다.
loginForm, content, etc 모두 JPanel이라고 말할 수 있다.
굳이 개성잇는 이름보다는 Jpanel의 배열을 만들어서 가르킬 수 있다.

*/

package page;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainApp extends JFrame implements ActionListener{
	JPanel p_north;
	
	//url 3개 마들기 귀찮으니까 배열로, 버튼도 배열로, 이미지도 배열로!
	JButton[] menu = new JButton[3];
	URL[] url = new URL[3];
	String[] path = {"/login.png","/content.png","/etc.png"};
	
	//얘네도 has a 관계로 가져야함 = 페이지들을 보유한다.
	JPanel p_center;
	LoginForm loginForm;
	Content content;
	Etc etc;
	
	JPanel[] page = new JPanel[3]; //panel들을 배열로 만든다.

	public MainApp() {
		p_north = new JPanel();
		
		for(int i =0; i<path.length; i++) {
			url[i] = this.getClass().getResource(path[i]);
			menu[i] = new JButton(new ImageIcon(url[i]));		
			p_north.add(menu[i]);
			menu[i].addActionListener(this);
		}
		
		add(p_north, BorderLayout.NORTH);	
		
		/*
		loginForm = new LoginForm(); //로긘 폼 생성
		content = new Content(); //컨텐츠 생성	 
		etc = new Etc(); //기타 페이지 생성
		*/
		
		p_center = new JPanel(); //페이지들이 붙을 곳!
		page[0] =  new LoginForm();
		page[1] =  new Content();
		page[2] =  new Etc();
					
		p_center.add(page[0]); //얘도 패널이므로 붙여야 한다.
		p_center.add(page[1]);	//컨텐츠
		p_center.add(page[2] ); //기타 페이지 
		add(p_center);

		setSize(700, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
	}
	
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		
		for(int i=0; i<page.length; i++){
		//내가 선택한 menu가 0번째이면 보여지는 index가 일치하는 것을 확인!
			if(obj == menu[i]){
				page[i].setVisible(true);
			} else {
				page[i].setVisible(false);
			}
		}
	}
		/* 이렇게 하면 속상하다.
		if(obj == menu[0]) {		
			로그인폼 O, 컨텐츠/ etc X
			loginForm.setVisible(true);
			content.setVisible(false);
			etc.setVisible(false);
		}
		
		} else if(obj == menu[1]) {
			//컨텐츠 O, 로그인폼/ etc X
			loginForm.setVisible(false);
			content.setVisible(true);
			etc.setVisible(false);
			
		} else if(obj == menu[2]) {
			//etc O, 컨텐츠/ 로그인폼 X
			loginForm.setVisible(false);
			content.setVisible(false);
			etc.setVisible(true);
			
		}
		*/


	
	public static void main(String[] args) {
		new MainApp();
	}
}
