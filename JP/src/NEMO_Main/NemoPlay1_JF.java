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
public class NemoPlay1_JF extends JFrame {
	JTabbedPane nemoTab;
	
	int tmp[]={1,2,3};
	
	public NemoPlay1_JF() {
		
		//NemoPlay1_JF 설정
		setTitle("네모네모 LOGIC - One Player Game");
		setLayout(null);
		setBounds(100,270,822,868);
		
		//JF배경색상
		getContentPane().setBackground(Color.WHITE);
		//JF배경색상
		
		NemoPlay_RootPanel def1;
		NemoPlay_RootPanel def2;
		NemoPlay_RootPanel def3;
		def1=new NemoPlay_RootPanel(tmp[0]);
		def2=new NemoPlay_RootPanel(tmp[1]);
		def3=new NemoPlay_RootPanel(tmp[2]);
		
		
		
		
		nemoTab=new JTabbedPane();
		
		nemoTab.addTab("5x5", def1);
		nemoTab.addTab("10x10", def2);
		nemoTab.addTab("15x15", def3);
		nemoTab.setBounds(0, 0, 805, 830);
	
		

		
		//정답 제출 및 끝내기 Button
		JButton btn1=new JButton("정답 제출");
		btn1.setBounds(600,1, 90, 20);
		btn1.addActionListener(new ActionListener() {
 

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if(NemoPlay_Button.point[tmp[0]-1]==1)
						{System.out.println("성공");
//						nemoTab..nemo;
						}
						
						//yes일때
						//
						//제거
						//nemoTab.remove(def1);
						//no일때
						//얼랏창만 닫음
						else if(NemoPlay_Button.point[tmp[1]-1]==1)
						{System.out.println("성공"); }
						else if(NemoPlay_Button.point[tmp[2]-1]==1)
						{System.out.println("성공"); }		
				} catch (ArrayIndexOutOfBoundsException e2) {
					System.out.println("실패");
				}
			}				
			});
		add(btn1);
		JButton btn2=new JButton("끝내기");
		btn2.setBounds(700,1, 90, 20);
		add(btn2);
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);}	});
		
		//정답 제출 및 끝내기 Button



		
		add(nemoTab);		

		setVisible(true);
	}

	
	
}
