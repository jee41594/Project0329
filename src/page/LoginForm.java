/*
로그인 화면을 담당할 클래스 정의
1. center와 south영역을 갖는 borderLayout이 필요!
2. center안은 GridLayout
3. 전체는 컴팩트하게 달라붙게 바까쪽은 flowLayout
 */
package page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JPanel{
	
	JPanel container; //borderLayout 적용할 예정
	JPanel p_center; //Grid적용할 예정
	JPanel p_south; //버튼이 남쪽에 들어갈 예정
	JLabel la_id, la_pw;
	JTextField t_id;
	JPasswordField p_pw;
	JButton bt;
	
	public LoginForm() {
		container=  new JPanel();
		p_center=  new JPanel();
		p_south=  new JPanel();
		la_id = new JLabel("ID");
		la_pw = new JLabel("Password");
		t_id = new JTextField(20);
		p_pw = new JPasswordField(20);
		bt = new JButton("로그인");
		
		container.setLayout(new BorderLayout());
		p_center.setLayout(new GridLayout(2, 2));
		p_center.add(la_id);
		p_center.add(t_id);
		p_center.add(la_pw);
		p_center.add(p_pw);
		p_south.add(bt);

		container.add(p_center);		
		container.add(p_south,BorderLayout.SOUTH);
		
		add(container);
		
		setPreferredSize(new Dimension(700, 500));
		setBackground(Color.pink);
		
	}

}
