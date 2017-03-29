/*
 �� ������� ũ�Ⱑ �����Ǿ� ���� �ʾƾ� �Ѵ�.
 ��? ������ �ȿ� ������ �� �г��� �� ũ�⸦
 �����ϰ� �ǹǷ�
 �α��� ����� ���� �۰�, ���� �� ȭ�鿡���� ũ��!
 
1. ������ ���� ���� �ʾƼ� �迭�� x
2. pack() Ȱ��  -> �츮�� ������ â�� �����ϴ°� �ƴϰ� ���빰�� ��ġ�� ���ϴ� ��!
3. how to change the panel like using LoginForm or GamePanel?
	- ��Ʈ�� Ÿ�����������̹Ƿ� ������ �ȿ��ٰ� � �������� �������� �޼��带 �����.
	- �����г� �̸� ������
 */
package practice;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JFrame {
	LoginForm loginForm;
	PracticeGamePanel gamePanel;
	
	//�������� �ϳ��� ��Ƽ� index�� ǥ������.
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
	
	//������ �ȿ� � �г��� ������ �������ִ¸޼���
	//�޼����ϸ� ����? �� ���ۤ� �� �ִ�.
	//()�ȿ� ���ϴ� �г��� ���� �޼����� ����. -> index�� �ѱ�ڴ�.
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
