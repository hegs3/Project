package NEMO_Main;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NemoSelect extends JFrame{
	Toolkit toolkit=Toolkit.getDefaultToolkit();
		
	public NemoSelect(){
		//메인(NemoSelect) 모드 셀렉트 프레임의 생성자/////////////////////////////////////
		setTitle("네모네모 LOGIC - MODE Select");
		setBounds(100,0,500,250);
		setAlwaysOnTop(true); 
		setResizable(false);
		
		//메인(NemoSelect) 모드 셀렉트 Panel 객체 생성////////////////
		SelectPanel selPanel=new SelectPanel(); 
		selPanel.setLayout(null);
		
		//버튼 객체
		JButton selBtn1=new JButton("1p.");
//		JButton selBtn2=new JButton("2p.");
//		JButton selBtn3=new JButton("nemocup");
		JButton	selBtn4=new JButton("nemo  EX");
		JButton selBtn5=new JButton("nemo END");
		//버튼 객체
		
		//버튼 속성
		//이미지 추가 할것... 아...
		selBtn1.setBounds(350,70, 120, 50);
//		selBtn2.setBounds(350,70, 120, 50);
//		selBtn3.setBounds(200,140, 120, 50);
		selBtn4.setBounds(350,140, 120, 20);
		selBtn5.setBounds(350,170, 120, 20);
		//버튼 속성
		
		//판넬에 버튼 추가
		selPanel.add(selBtn1);
//		selPanel.add(selBtn2);
//		selPanel.add(selBtn3);
		selPanel.add(selBtn4);
		selPanel.add(selBtn5);
		//판넬에 버튼 추가

//		//저장된 정답 배열 불러오기
//				File f=new File("PointArray/5x5");
//				try {FileInputStream pointArray=new FileInputStream(f);
//					byte[] pointByte=new byte[100];
//					
//					String tt=new String(pointByte);
//					
//					System.out.println(tt);
//				} catch (FileNotFoundException e1) {}
//				//저장된 정답 배열 불러오기
				
				
		//버튼에 대한 ActionListener////////////////////
		//버튼1
		selBtn1.addActionListener(new ActionListener() {
			@Override	
			public void actionPerformed(ActionEvent e) {new NemoPlay1_JF();	}
		});
		//버튼2
//		selBtn2.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {new NemoPlay2_JF();	}
//		});
//		//버튼3
//		selBtn3.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {new NemoPlayCup_JF();}
//		});
//		//버튼txt
		selBtn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().open(new File("comp/comp.png"));
				} catch (IOException e1) {
					System.out.println("파일이 존재하지 않습니다.");
				}
			}
		});
		//버튼END
		selBtn5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		//버튼에 대한 ActionListener////////////////////
		
		
		add(selPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	//메인(NemoSelect) 모드 셀렉트 프레임의 생성자/////////////////////////////////////
	
	
	
	//메인(SelectPanel) 모드 셀렉트 Panel Class/////////////////////////////////////
	public class SelectPanel extends JPanel{
		
		Image bgimg;
		int width,height;// 판넬 가로세로
		
		//SelectPanel의 생성자 함수//////////////////////
		public SelectPanel() {	
			bgimg=toolkit.getImage("img/bg/test.png");
			bgimg=bgimg.getScaledInstance(510, 250, Image.SCALE_SMOOTH);
		}
		//SelectPanel의 생성자 함수//////////////////////
		
		
		//버튼에 대한 ActionListener////////////////////
		@Override
		protected void paintComponent(Graphics g) {

		//가로세로 길이
		width=getWidth();//게임화면 가로
		height=getHeight();//게임화면 세로
		//가로세로 길이
		
		
		//Selectpanel()의 PAINTCOMPONENT....
		g.clearRect(0, 0, width, height);
		g.drawImage(bgimg, 0, 0, this);
		g.setFont(new Font("Press Start 2P",Font.BOLD, 35));
		g.setColor(new Color(65,175,57));
		g.drawString("N N",10,65);
		g.drawString("E E",10,100);
		g.drawString("M M",10,135);
		g.drawString("O O",10,170);
		g.setColor(new Color(111,111,111));
		g.setFont(new Font("Press Start 2P",Font.BOLD, 21));
		g.drawString("LOGIC",10,200);
		}
		//Selectpanel()의 PAINTCOMPONENT....
	
		
		//버튼에 대한 ActionListener////////////////////
	}
	//메인(SelectPanel) 모드 셀렉트 Panel Class/////////////////////////////////////
	
	
	
	//main METHOD...............
	public static void main(String[] args) {
	
		new NemoSelect();
	}
	//main METHOD...............
}
