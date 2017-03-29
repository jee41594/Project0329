/*
 �� �ҽ��� ��� ���ξ����尡 gui�����ϹǷ� �����ϴ� ����
 �����ư�� �������ִ�.
 
�� ���ξ������ ���ѷ����� ���߸��� �ȵȴ�. ��
--> �����尡 ��� �� �۾��� �Ѵ�. 

* how to count progressBar? 
230 : 100% = (5)���� �о���� ������:x%
500 = 230*x

���� ����� : 100*��/��üũ�� -> �޼���ȭ��Ű��

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
	File file; // �о���� ���� ���������, �������
	Thread thread;
	/* 
	 ���縦 ������ ���� ������-> ���θ޼���� �츮�� �˰� �ִ� �� ����ζ� �Ҹ��� ���ø����̼ǿ�  
	 ��� ����ϴ� ������ �����Ѵ�. 
	 �׷��Ƿ� ����� ���ѷ����� �����¿� ���߷����� �ȵȴ�!! 
	 �츮�� thread�� run�� ����ϳ�? �츮�� ���� run�� ����ϳ�? �츮��!
	 */
	long total; //���� ������  ��ü �뷮

	public CopyMain() {
		bar = new JProgressBar();
		bt_open = new JButton("����");
		bt_save = new JButton("����");
		bt_copy = new JButton("�������");
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
		 else if �� ������ ����? switch���� �ִ�.
		 switch���̶� ���� else if ��! switch���� int�� ����..? �ȵ���̵��Ҷ� �ٽ� !
		 switch(��ư) { ���¹�ư�̶��? �����ư�̶��? }
		 */

		// �̺�Ʈ�� ����Ų �̺�Ʈ �ҽ�(�̺�Ʈ ��ü)
		Object obj = e.getSource();
		if (obj == bt_open) {
			open();
		} else if (obj == bt_save) {
			save();
		} else if (obj == bt_copy) {
			//copy();
			
		/*----------------------------------------------------------
			 ������ ���� ���縦 �������� ���� �����忡�� ��Ų��.	
			()�ȿ� runnable�� ������ �ڸ� �־�� �ϴµ�,..? ���� �����ϴϱ� ������
			������ �����ڿ� Runnable ������ü�� �μ��� ������
			Runnable ��ü���� �������� run(){} ����!
		---------------------------------------------------------------*/
			thread = new Thread(this); 
			thread.start(); //�츮 run ���� ( �����岨 x)
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

		// ���Ͽ� ���븦 �ȾҴ�.
		try {
			fis = new FileInputStream(file);
			fos = new FileOutputStream(t_save.getText());

			// ������ ��Ʈ���� ���� ������ �б�
			int data;
			int count =0; //readȽ�� �˱����� count�̿�!
			
			while (true) {
				data = fis.read(); // 1byte �б�
				if (data == -1)break;
				count++;
				fos.write(data); // 1byte ���
				int v = (int)getPercent(count);
				//read�� Ƚ���� �о���̴� �뷮�̴�. read�Ҷ����� ī��Ʈ ���� �Ѵ�.
				//long���� ����� ����. ��� �����ؾ�����? ���α׷��� ���� ������ ��������.
				//bar�� int ���ϴϱ� long�� ��������ȯ ������ ������ long = getPercent(count); �;��ϴµ�,,
				bar.setValue(v);
				//bar.setStringPainted();
			} //����Ϸ����
			JOptionPane.showMessageDialog(this, "����Ϸ�");
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
	 ���� ����� ���ϱ� ����
	 ���� ����� : 100*��/��üũ�� -> �޼���ȭ��Ű��
	 ���� �о���� ������ : ���ϴ� ��, while�����κ��� �Ѱ� ����. -> int currentRead��!
	 ��üũ�� : ���� ���� ������ �� ���� �� �ִ� ��!
	 ȣ���ϴ� ��ü��? while��!
	 long �� ���� int �� Ʋ�� �ڷ����̰� ū������ �����. ��ȯ���� long���� �ȴ�!
	 */
	public long getPercent(int currentRead) {
		return (100*currentRead)/total;		
	}

	public static void main(String[] args) {
		new CopyMain();
	}

}
