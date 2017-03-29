
package game.word;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JPanel implements ActionListener{
	
	GameWindow gameWindow; //내 주인은 게임윈도우임을 알려준다.
	JPanel container; //borderLayout 적용할 예정
	JPanel p_center; //Grid적용할 예정
	JPanel p_south; //버튼이 남쪽에 들어갈 예정
	JLabel la_id, la_pw;
	JTextField t_id;
	JPasswordField t_pw;
	JButton bt;
	
	GamePanel gamePanel;
	
	
	public LoginForm(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
		container=  new JPanel();
		p_center=  new JPanel();
		p_south=  new JPanel();
		la_id = new JLabel("ID");
		la_pw = new JLabel("Password");
		t_id = new JTextField("batman",15);
		t_pw = new JPasswordField("1234",15);
		bt = new JButton("로그인");
		
		container.setLayout(new BorderLayout());
		p_center.setLayout(new GridLayout(2, 2));
		p_center.add(la_id);
		p_center.add(t_id);
		p_center.add(la_pw);
		p_center.add(t_pw);
		p_south.add(bt);

		container.add(p_center);		
		container.add(p_south,BorderLayout.SOUTH);
		
		add(container);
		
		bt.addActionListener(this);
		setPreferredSize(new Dimension(400,100));
		setBackground(Color.pink);
	}
	
	public void loginCheck() {
		String id = t_id.getText();
		String pw = new String(t_pw.getPassword());
		if(id.equals("batman") && pw.equals("1234")){
			JOptionPane.showMessageDialog(this, "로그인을 축하합니다");	
			gameWindow.setPage(1);
			/*
			 GameWindow.setPage(1); 써야한다..
			 앞으로 JAVA에서 응용프로그램 ㅂ낼때 윈도우에 정보를 담아두거나, 메서드를 보유해서 데이터를 공유하는걸로 개발한다. 
			 모든페이지가 윈도우 접근할 수 있도록정보 줘야한다.
			 */
		} else{
			JOptionPane.showMessageDialog(this, "로그인 정보가 올바르지 않습니다");			
		}
				
	}
	
	public void actionPerformed(ActionEvent e){
		loginCheck();
		
	}
}
