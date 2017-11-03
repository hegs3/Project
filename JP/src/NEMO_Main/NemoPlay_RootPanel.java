package NEMO_Main;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import NEMO_Play.NemoPlay_Button;
import NEMO_Play.NemoPlay_NorthNum;
import NEMO_Play.NemoPlay_WestNum;
public class NemoPlay_RootPanel extends JPanel {

	
	public NemoPlay_RootPanel(int nemoTmp) {
		setLayout(null);
		setBackground(Color.WHITE);
		JButton nemoHint=new JButton("HINT Click");
		nemoHint.setBorderPainted(false);
		nemoHint.setFocusable(false);
		nemoHint.setBackground(Color.WHITE);
		nemoHint.setBounds(0,0,200,200);
		add(nemoHint);
		
		nemoHint.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
			try {Desktop.getDesktop().open(new File("img/hint/"+NemoPlay_Button.puzznum*nemoTmp+".jpg"));} catch (IOException e1) {System.out.println("파일이 존재하지 않습니다.");}}	});

		
		NemoPlay_WestNum nemoWestNum= new NemoPlay_WestNum(nemoTmp);
		NemoPlay_NorthNum nemoNorthNum= new NemoPlay_NorthNum(nemoTmp);
		NemoPlay_Button nemoButtonPanel=new NemoPlay_Button(nemoTmp);
		
		nemoWestNum.setBounds(0, 200,200,600);
		nemoNorthNum.setBounds(200, 0,600,200);
		nemoButtonPanel.setBounds(200, 200,600,600);
		add(nemoWestNum);
		add(nemoNorthNum);
		add(nemoButtonPanel);

	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawLine(0, 200, 200, 200);
		g.drawLine(200, 0, 200, 200);
		g.setColor(Color.WHITE);
	}
	
}
