package practice;

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

public class PracticeCopyMain extends JFrame implements ActionListener {
	JProgressBar bar;
	JButton bt_open, bt_save, bt_copy;
	JTextField t_open, t_save;
	JFileChooser chooser;
	File file; // 읽어들일 파일 멤버변수로, 복사원본


	public PracticeCopyMain() {
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
			copy();
		}
	}

	public void open() {
		int result = chooser.showOpenDialog(this);

		if (result == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			String path = file.getAbsolutePath();
			t_open.setText(path);
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
			while (true) {
				data = fis.read(); // 1byte 읽기
				if (data == -1)break;
				fos.write(data); // 1byte 출력

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


		
	public static void main(String[] args) {
		new PracticeCopyMain();
	}

}
