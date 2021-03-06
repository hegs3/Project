package NEMO_Play;
import java.awt.Graphics;

import javax.swing.JPanel;

public class NemoPlay_NorthNum extends JPanel {

	int nemoTmp;
	
	
	int[][][] valus=new int[4][50][]; //5*5	
	public NemoPlay_NorthNum(int tmp) {
		nemoTmp=tmp;
		
		
		valus[0][0]=new int[] {1};
		valus[0][1]=new int[] {4};
		valus[0][2]=new int[] {1,2};
		valus[0][3]=new int[] {2};
		valus[0][4]=new int[] {3};
		valus[1][0]=new int[] {2};
		valus[1][1]=new int[] {1};
		valus[1][2]=new int[] {7};
		valus[1][3]=new int[] {4,2};
		valus[1][4]=new int[] {4,4};
		valus[1][5]=new int[] {1,7};
		valus[1][6]=new int[] {1,4,2};
		valus[1][7]=new int[] {2,7};
		valus[1][8]=new int[] {4,1};
		valus[1][9]=new int[] {3,2};
		valus[2][0]=new int[] {1,5};
		valus[2][1]=new int[] {1,9};
		valus[2][2]=new int[] {1,2,1,1};
		valus[2][3]=new int[] {4,2};
		valus[2][4]=new int[] {2,2};
		valus[2][5]=new int[] {1,2,1};
		valus[2][6]=new int[] {1,1,1,3};
		valus[2][7]=new int[] {1,1,1,3};
		valus[2][8]=new int[] {1,1,1,3};
		valus[2][9]=new int[] {1,2,2};
		valus[2][10]=new int[] {2,2};
		valus[2][11]=new int[] {2,2,1};
		valus[2][12]=new int[] {4,1,1};
		valus[2][13]=new int[] {5,4,1};
		valus[2][14]=new int[] {2,10};
		valus[3][0]=new int[] {1,5};
		valus[3][1]=new int[] {1,3,2};
		valus[3][2]=new int[] {1,2,4};
		valus[3][3]=new int[] {1,7};
		valus[3][4]=new int[] {1,8};
		valus[3][5]=new int[] {1,2,2};
		valus[3][6]=new int[] {1,8};
		valus[3][7]=new int[] {1,8};
		valus[3][8]=new int[] {1,3,5};
		valus[3][9]=new int[] {1,1,5,2};
		valus[3][10]=new int[] {1,4,2,1};
		valus[3][11]=new int[] {1,8,1};
		valus[3][12]=new int[] {3,4,4,1,1};
		valus[3][13]=new int[] {2,1,1,4,1};
		valus[3][14]=new int[] {1,8,3,1};
		valus[3][15]=new int[] {1,3,2,10};
		valus[3][16]=new int[] {3,4,3,2};
		valus[3][17]=new int[] {1,3};
		valus[3][18]=new int[] {3,3,5};
		valus[3][19]=new int[] {1,11,1};
		
		

	}
	@Override
	protected void paintComponent(Graphics g) {

	int width=getWidth();
	int height=getHeight();
	int arr1=0;
	arr1=nemoTmp-1;
		for(int i=0;i<NemoPlay_Button.puzznum*nemoTmp;i++){
			g.drawLine((width/NemoPlay_Button.puzznum)/nemoTmp*(i+1),0,(width/NemoPlay_Button.puzznum)/nemoTmp*(i+1),height);
			for(int j=0;j<valus[arr1][i].length;j++){
				g.drawString(valus[arr1][i][j]+"",
						((width/NemoPlay_Button.puzznum)/nemoTmp/2)*((i+1)*2-1)-2, (height-j*15)-10);
			}
			}
		}
	
	


}
