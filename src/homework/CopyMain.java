/*
 이 소스의 경우 메인쓰레드가 gui제어하므로 복사하는 동안
 실행버튼이 눌러져있다.
 
★ 메인쓰레드는 무한루프에 빠뜨리면 안된다. ★
--> 쓰레드가 대신 이 작업을 한다. 

* how to count progressBar? 
230 : 100% = (5)현재 읽어들인 데이터:x%
500 = 230*x

현재 진행률 : 100*현/전체크기 -> 메서드화시키자

*/


package homework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class CopyMain extends JFrame implements ActionListener, Runnable {
	JProgressBar bar;
	JButton bt_open, bt_save, bt_copy;
	JTextField t_open, t_save;
	JFileChooser chooser;
	File file; // 읽어들일 파일 멤버변수로, 복사원본
	Thread thread;
	/* 
	 복사를 실행할 전용 쓰레드-> 메인메서드는 우리가 알고 있는 그 실행부라 불리는 어플리케이션에  
	 운영을 담당하는 역할을 수행한다. 
	 그러므로 절대로 무한루프나 대기상태에 빠뜨려서는 안된다!! 
	 우리는 thread의 run을 써야하나? 우리가 만든 run을 써야하나? 우리꺼!
	 */
	long total; //원본 파일의  전체 용량

	public CopyMain() {
		bar = new JProgressBar();
		bt_open = new JButton("열기");
		bt_save = new JButton("저장");
		bt_copy = new JButton("복사실행");
		t_open = new JTextField(35);
		t_save = new JTextField(35);
		chooser = new JFileChooser("C:/html_workspace/images");

		bar.setPreferredSize(new Dimension(450, 50));
		//bar.setBackground(Color.pink);
		//bar.setValue(0);
		bar.setString("0%");

		setLayout(new FlowLayout());
		add(bar);
		add(bt_open);
		add(t_open);
		add(bt_save);
		add(t_save);
		add(bt_copy);

		bt_open.addActionListener(this);
		bt_save.addActionListener(this);
		bt_copy.addActionListener(this);

		setSize(500, 200);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e) {

		/*
		 else if 를 대응할 것은? switch문이 있다.
		 switch문이랑 다중 else if 문! switch문은 int만 가능..? 안드로이드할때 다시 !
		 switch(버튼) { 여는버튼이라면? 저장버튼이라면? }
		 */

		// 이벤트를 일으킨 이벤트 소스(이벤트 주체)
		Object obj = e.getSource();
		if (obj == bt_open) {
			open();
		} else if (obj == bt_save) {
			save();
		} else if (obj == bt_copy) {
			//copy();
			
		/*----------------------------------------------------------
			 메인이 직접 복사를 수행하지 말고 쓰레드에게 시킨다.	
			()안에 runnable을 구현한 자를 넣어야 하는데,..? 내가 구현하니까 나넣음
			쓰레드 생성자에 Runnable 구현객체를 인수로 넣으면
			Runnable 객체에서 재정의함 run(){} 수행!
		---------------------------------------------------------------*/
			thread = new Thread(this); 
			thread.start(); //우리 run 수행 ( 쓰레드꺼 x)
		}
	}

	public void open() {
		int result = chooser.showOpenDialog(this);

		if (result == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			String path = file.getAbsolutePath();
			t_open.setText(path);
			total = file.length();
		}
	}

	public void save() {
		int result = chooser.showOpenDialog(this);

		if (result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			String path = file.getAbsolutePath();
			t_save.setText(path);
		}
	}

	public void copy() {
		FileInputStream fis = null;
		FileOutputStream fos = null;

		// 파일에 빨대를 꽂았다.
		try {
			fis = new FileInputStream(file);
			fos = new FileOutputStream(t_save.getText());

			// 생성된 스트림을 통해 데이터 읽기
			int data;
			int count =0; //read횟수 알기위해 count이용!
			
			while (true) {
				data = fis.read(); // 1byte 읽기
				if (data == -1)break;
				count++;
				fos.write(data); // 1byte 출력
				int v = (int)getPercent(count);
				//read의 횟수가 읽어들이는 용량이다. read할때마다 카운트 세야 한다.
				//long으로 결과값 받자. 어디에 적용해야하지? 프로그레스 바의 비율로 적용하자.
				//bar는 int 원하니까 long을 강제형변환 해주자 원래는 long = getPercent(count); 와야하는데,,
				bar.setValue(v);
				//bar.setStringPainted();
			} //복사완료시점
			JOptionPane.showMessageDialog(this, "복사완료");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void run() {
		copy();
	}
	
	/*
	 현재 진행률 구하기 공식
	 현재 진행률 : 100*현/전체크기 -> 메서드화시키자
	 현재 읽어들인 데이터 : 변하는 것, while문으로부터 넘겨 받자. -> int currentRead로!
	 전체크기 : 내가 파일 선택할 때 얻을 수 있는 것!
	 호출하는 주체는? while문!
	 long 과 옆에 int 가 틀린 자료형이고 큰쪽으로 맞춘다. 반환형도 long으로 된다!
	 */
	public long getPercent(int currentRead) {
		return (100*currentRead)/total;		
	}

	public static void main(String[] args) {
		new CopyMain();
	}

}
