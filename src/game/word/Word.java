/*
���ӿ� ������ ��� �ܾ ���� y���� ���� ����,
�뷮���� ��������� �ϱ� ������
�ᱹ ���뼺�� ���� �ڵ������� Ŭ������ ����
 
 ��ԤӤ��� ����� 2���� �� �ʿ�
 ������ ��ȭ,
 �ٽñ׸���
 
 */

package game.word;

import java.awt.Graphics;

public class Word {
	String name; //�� ��ü�� ��Ե� �ܾ�!! �۾�!!
	int y;
	int x;
	int velX; //�ܾ ������ �ӵ�
	int velY; 
	//int interval; -> �񵿱������ �Ұ���
	
	
	//�� �ܾ �¾ �� ���߾�� �� �ʱ�ȭ ��
	public Word(String name, int x, int y) {
		this.name=name;
		this.x=x;
		this.y=y;
	}
	
	//�� ��ü�� �ݿ��� ������ ��ȭ �ڵ�
	public void tick() {
		y+=5;		
	}
	
	//�ݿ��� �����͸� �̿��Ͽ� ȭ�鿡 �׸���
	public void render(Graphics g) {
		g.drawString(name, x, y);
		
	}
	
	
	
	

}