package NEMO_Main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import NEMO_Play.NemoPlay_Button;
public class NemoPlayCup_JF extends JFrame {
	JTabbedPane nemoTab;
	
	int tmp=4;
	
	public NemoPlayCup_JF() {
		
		//NemoPlay1_JF ����
		setTitle("�׸�׸� LOGIC - CUP Game");
		setLayout(null);
		setBounds(100,270,822,868);
		
		//JF������
		getContentPane().setBackground(Color.WHITE);
		//JF������
		
		NemoPlay_RootPanel def;
		def=new NemoPlay_RootPanel(tmp);
		
		
		
		
		nemoTab=new JTabbedPane();
		nemoTab.addTab("20x20", def);
		nemoTab.setBounds(0, 0, 805, 830);
		
		
		//���� ���� �� ������ Button
		JButton btn1=new JButton("���� ����");
		btn1.setBounds(600,1, 90, 20);
		btn1.addActionListener(new ActionListener() {
 

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if(NemoPlay_Button.point[tmp-1]==1)
						{System.out.println("����");}	
				} catch (ArrayIndexOutOfBoundsException e2) {
					System.out.println("����");
				}
			}				
			});
		add(btn1);
		JButton btn2=new JButton("������");
		btn2.setBounds(700,1, 90, 20);
		add(btn2);
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);}	});
		JButton btn3=new JButton("Time ");
		btn2.setBounds(700,1, 90, 20);
		add(btn2);
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}	});
		//���� ���� �� ������ Button



		
		add(nemoTab);		

		setVisible(true);
	}

	
	
}
