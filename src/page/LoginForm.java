/*
�α��� ȭ���� ����� Ŭ���� ����
1. center�� south������ ���� borderLayout�� �ʿ�!
2. center���� GridLayout
3. ��ü�� ����Ʈ�ϰ� �޶�ٰ� �ٱ����� flowLayout
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
	
	JPanel container; //borderLayout ������ ����
	JPanel p_center; //Grid������ ����
	JPanel p_south; //��ư�� ���ʿ� �� ����
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
		bt = new JButton("�α���");
		
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
