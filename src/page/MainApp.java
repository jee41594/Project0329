/*
img ���� ������ �Ϲ� ���� ���� buil path���ش�.

1. ������
2 �츮���� �г��� ������ �Ѵ�. -> customizing
3. �г��� �ҷ�����
4. Ŭ���� ��� �������� ��� ��Ÿ���� -> �ӻ����� �ʰ� �����غ���.
�����͸� ������ �ִ� �͵��� �����ִ� �̸����� �Ǿ������� �����Ѽ� for���� ���� ���¾����Ƿ�
������ �迭ȭ ���Ѽ� �����ؾ� �Ѵ�.
loginForm, content, etc ��� JPanel�̶�� ���� �� �ִ�.
���� �����մ� �̸����ٴ� Jpanel�� �迭�� ���� ����ų �� �ִ�.

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
	
	//url 3�� ����� �������ϱ� �迭��, ��ư�� �迭��, �̹����� �迭��!
	JButton[] menu = new JButton[3];
	URL[] url = new URL[3];
	String[] path = {"/login.png","/content.png","/etc.png"};
	
	//��׵� has a ����� �������� = ���������� �����Ѵ�.
	JPanel p_center;
	LoginForm loginForm;
	Content content;
	Etc etc;
	
	JPanel[] page = new JPanel[3]; //panel���� �迭�� �����.

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
		loginForm = new LoginForm(); //�΃V �� ����
		content = new Content(); //������ ����	 
		etc = new Etc(); //��Ÿ ������ ����
		*/
		
		p_center = new JPanel(); //���������� ���� ��!
		page[0] =  new LoginForm();
		page[1] =  new Content();
		page[2] =  new Etc();
					
		p_center.add(page[0]); //�굵 �г��̹Ƿ� �ٿ��� �Ѵ�.
		p_center.add(page[1]);	//������
		p_center.add(page[2] ); //��Ÿ ������ 
		add(p_center);

		setSize(700, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
	}
	
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		
		for(int i=0; i<page.length; i++){
		//���� ������ menu�� 0��°�̸� �������� index�� ��ġ�ϴ� ���� Ȯ��!
			if(obj == menu[i]){
				page[i].setVisible(true);
			} else {
				page[i].setVisible(false);
			}
		}
	}
		/* �̷��� �ϸ� �ӻ��ϴ�.
		if(obj == menu[0]) {		
			�α����� O, ������/ etc X
			loginForm.setVisible(true);
			content.setVisible(false);
			etc.setVisible(false);
		}
		
		} else if(obj == menu[1]) {
			//������ O, �α�����/ etc X
			loginForm.setVisible(false);
			content.setVisible(true);
			etc.setVisible(false);
			
		} else if(obj == menu[2]) {
			//etc O, ������/ �α����� X
			loginForm.setVisible(false);
			content.setVisible(false);
			etc.setVisible(true);
			
		}
		*/


	
	public static void main(String[] args) {
		new MainApp();
	}
}
