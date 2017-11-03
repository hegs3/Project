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
	public int[][] result;	// Ŭ���� ����ȭ
	public static int[][] strInt;
	Integer[][] resI,strI;
	JButton [][] btn;  //Ŭ�� �� ���� ��ȭ
	
	
	
	//����Ʈ(NemoPlay_Def) �÷��� �ǳ��� ������/////////////////////////////////////
	public NemoPlay_Button(int nemoTmp) {
		setSize(1000	,1000);
		this.nemoTmp=nemoTmp;//��������� Tmp(1,2,3)�� ����
		//��ư������ GridLayout
		GridLayout nemoGrid=new GridLayout(0,puzznum*nemoTmp);
		setLayout(nemoGrid);
		//��ư������ GridLayout
		
		//����� �� �� ��ư ArrayList
		result=new int[puzznum*nemoTmp][puzznum*nemoTmp];
		btn=new JButton[puzznum*nemoTmp][puzznum*nemoTmp];
		//����� �� �� ��ư ArrayList
//		NemoPlay_NorthNum.setresultArr(result);
		//Button ����
		for(int i=0;i<puzznum*nemoTmp;i++){
			for(int j=0;j<puzznum*nemoTmp;j++){
				btn[i][j]=new JButton();
				btn[i][j].setBackground(Color.WHITE);
				btn[i][j].addActionListener(this);
				add(btn[i][j]);}
		}//Button ����

		
	}	//����Ʈ(NemoPlay_Def) �÷��� �ǳ��� ������/////////////////////////////////////
	



	//Def_paintCOMP/////////////////////////////
	@Override
	protected void paintComponent(Graphics g) {
		g.clearRect(0, 0, 500, 500);}
	//Def_paintCOMP/////////////////////////////
	
	
	//��ư�� ���� ActionListener////////////////////
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<puzznum*nemoTmp;i++){	for(int j=0;j<puzznum*nemoTmp;j++){
			if(e.getSource()==btn[i][j]){
				if(result[i][j]==0){btn[i][j].setBackground(Color.black);result[i][j]=1;}
				else if(result[i][j]==1){btn[i][j].setBackground(Color.WHITE);result[i][j]=0;}	}	}	}
//		����üũ...
		savePoint();
		try {
			if(compare()){
				point[nemoTmp-1]=1;
				
				}else point[nemoTmp-1]=0;	
		} catch (ArrayIndexOutOfBoundsException e2) {}
		
	}//��ư�� ���� ActionListener////////////////////
	
	
	
	//����迭/////////////////////////////////////
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
	}	//����迭/////////////////////////////////////
	
	
	//���䰪 �� �޼ҵ�
	public boolean compare(){
		for(int y=0; y<puzznum*nemoTmp; y++){
			for(int x=0; x<puzznum*nemoTmp; x++){
				if(result[y][x]!=strInt[y][x]) return false;}	}
		
		return true;
	}//���䰪 �� �޼ҵ�

}
