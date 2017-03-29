/*
게임에 등장할 대상 단어가 각각 y축을 따로 갖고,
대량으로 만들어져야 하기 때문에
결국 재사용성을 위한 코드집합인 클래스로 가자
 
 어떤게ㅣㅁ을 만들든 2개는 꼭 필요
 물리량 변화,
 다시그리기
 
 */

package game.word;

import java.awt.Graphics;

public class Word {
	String name; //이 객체가 담게될 단어!! 글씨!!
	int y;
	int x;
	int velX; //단어가 움직일 속도
	int velY; 
	//int interval; -> 비동기식으로 불가능
	
	
	//이 단어가 태어날 때 갖추어야 할 초기화 값
	public Word(String name, int x, int y) {
		this.name=name;
		this.x=x;
		this.y=y;
	}
	
	//이 객체에 반영될 데이터 변화 코드
	public void tick() {
		y+=5;		
	}
	
	//반영된 데이터를 이용하여 화면에 그리기
	public void render(Graphics g) {
		g.drawString(name, x, y);
		
	}
	
	
	
	

}
