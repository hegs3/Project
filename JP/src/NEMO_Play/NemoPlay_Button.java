package NEMO_Play;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.JobAttributes.DialogType;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.stream.FileImageOutputStream;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import NEMO_Play.NemoPlay_NorthNum;


public class NemoPlay_Button extends JPanel implements ActionListener{
	public static final int puzznum=5;
	public int nemoTmp;
	public static int point[]={0};
	public int[][] result;	// 클릭시 값변화
	public static int[][] strInt;
	Integer[][] resI,strI;
	JButton [][] btn;  //클릭 시 색깔 변화
	
	
	
	//디폴트(NemoPlay_Def) 플레이 판넬의 생성자/////////////////////////////////////
	public NemoPlay_Button(int nemoTmp) {
		setSize(1000	,1000);
		this.nemoTmp=nemoTmp;//멤버변수에 Tmp(1,2,3)값 저장
		//버튼을위한 GridLayout
		GridLayout nemoGrid=new GridLayout(0,puzznum*nemoTmp);
		setLayout(nemoGrid);
		//버튼을위한 GridLayout
		
		//결과값 비교 및 버튼 ArrayList
		result=new int[puzznum*nemoTmp][puzznum*nemoTmp];
		btn=new JButton[puzznum*nemoTmp][puzznum*nemoTmp];
		//결과값 비교 및 버튼 ArrayList
//		NemoPlay_NorthNum.setresultArr(result);
		//Button 생성
		for(int i=0;i<puzznum*nemoTmp;i++){
			for(int j=0;j<puzznum*nemoTmp;j++){
				btn[i][j]=new JButton();
				btn[i][j].setBackground(Color.WHITE);
				btn[i][j].addActionListener(this);
				add(btn[i][j]);}
		}//Button 생성

		
	}	//디폴트(NemoPlay_Def) 플레이 판넬의 생성자/////////////////////////////////////
	



	//Def_paintCOMP/////////////////////////////
	@Override
	protected void paintComponent(Graphics g) {
		g.clearRect(0, 0, 500, 500);}
	//Def_paintCOMP/////////////////////////////
	
	
	//버튼에 대한 ActionListener////////////////////
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<puzznum*nemoTmp;i++){	for(int j=0;j<puzznum*nemoTmp;j++){
			if(e.getSource()==btn[i][j]){
				if(result[i][j]==0){btn[i][j].setBackground(Color.black);result[i][j]=1;}
				else if(result[i][j]==1){btn[i][j].setBackground(Color.WHITE);result[i][j]=0;}	}	}	}
//		정답체크...
		savePoint();
		try {
			if(compare()){
				point[nemoTmp-1]=1;
				
				}else point[nemoTmp-1]=0;	
		} catch (ArrayIndexOutOfBoundsException e2) {}
		
	}//버튼에 대한 ActionListener////////////////////
	
	
	
	//정답배열/////////////////////////////////////
	private void savePoint() {
		try {
			File file=new File("PointArray/"+puzznum*nemoTmp+"x"+puzznum*nemoTmp+".txt");
			FileInputStream fis=new FileInputStream(file);	
			byte[] bytes=new byte[puzznum*nemoTmp*puzznum*nemoTmp];
			fis.read(bytes);
			strInt=new int[puzznum*nemoTmp][puzznum*nemoTmp];
			String str=new String(bytes);
			char[] strChar=str.toCharArray();
			for(int i=0;i<puzznum*nemoTmp;i++){
				for(int j=0;j<puzznum*nemoTmp;j++){
					strInt[i][j]=strChar[(i*puzznum*nemoTmp)+j]-48;	}	}
			fis.close();
		} catch (FileNotFoundException e1) {} catch (IOException e1) {}
	}	//정답배열/////////////////////////////////////
	
	
	//정답값 비교 메소드
	public boolean compare(){
		for(int y=0; y<puzznum*nemoTmp; y++){
			for(int x=0; x<puzznum*nemoTmp; x++){
				if(result[y][x]!=strInt[y][x]) return false;}	}
		
		return true;
	}//정답값 비교 메소드

}
