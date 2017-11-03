//ASCII�ڵ� 1byte ǥ���: '//UNI�ڵ� 2byte ǥ���: "
#include <stdio.h>// #- ��ó����
#include <conio.h>// getch(); �ֿܼ� ����� ���
#include <Windows.h>//system("cls"); �ý��ۿ� ����� ���������� ���

//��ũ�� ���
#define up 72
#define down 80
#define left 75
#define right 77

int checkGameOver();
void drawMap();// �Լ��� ������ Ÿ��
void move();
//��������
char map[10][10] = 
{//��ĭ EMPTY 0  //�� WALL 1  //���� BOX 2
//2byte�� Ư������(�����ڷ�)�� ����ϱ� ���� ���ڷ� �����س���
	{1,1,1,1,1,1,1,1,1,1},
	{1,2,0,0,1,1,0,0,0,1},
	{1,0,0,0,1,1,0,0,0,1},
	{1,0,0,0,1,1,0,0,0,1},
	{1,0,0,0,1,1,0,0,0,1},
	{1,0,0,0,0,0,0,0,1,1},
	{1,1,1,0,0,0,0,0,0,1},
	{1,1,1,0,0,0,0,0,0,1},
	{1,0,0,0,1,1,1,0,0,1},
	{1,1,1,1,1,1,1,1,1,1}
};
int posY, posX;



void main()
{
	//�������� ���� ��ġ
	posY = 1; posX = 1;

	while(1)
	{
	//�ܼ�â�� ȭ���� Ŭ����
		system("cls");//�ý��ۿ� ����� ������ �Լ�
		drawMap();//�迭�� �� ����Լ� ȣ��
		//������������ �˻�

		if (checkGameOver())
		{
			printf("Stage Clear~~~\n\n");
			break;
		}
		move(); // Ű���带 �Է¹޾� �̵���Ű�� �Լ� ȣ��
	}

	

}//main()...

//���� ���� üũ �Լ�

int checkGameOver()
{
	for (int y = 0; y < 10; y++)
	{
		for (int x = 0; x < 10; x++)
		{
			if (map[y][x] == 0) return 0;
	
		}
	}
	//������� Ŀ���� �����ߴٸ�
	//�迭��� 100���߿��� 0�� �Ѱ��� ���ٵ� ���� �ǹ���
	return 1;
}

void move()//Ű���� �Է¹޾� ����Ű�� ���� �̵� ��Ű�±���� ���� �Լ�
{
	//������� ����Ű �Է¹ޱ�.. ���� �����̱�(scanf ��� x ���ͷ� �Է��� �������ϱ� ����)
	//getch(); - 4byte - �� �������� ���� �� ����(char) > int ������ ��ȯ
	//Ű���带 ������ ���� �Է��� ��
	//����Ű�� �������� ��� 224 : Ű������ �ѹ��� ����Ű�� �����ϱ� ������
	//����Ű�� ������ 2�� ���� �Ѵ�
	int key;
	key = getch();
	if (key == 224 || key == 0) key = getch();

	switch (key)
	{			
	case up:
		//��ĭ�� ����ִ°�> ��ĭ�� 0�̳�?
		//Ȥ��, ��ĭ�� 0�� �ƴϳ�? �ƴϸ� �׸���...
		if (map[posY-1][posX]!=0) return;
		posY--;
		map[posY][posX] = 2;
		break;
	case down:
		//�Ʒ�ĭ�� 0�� �ƴϳ�? �ƴϸ� �׸���...
		if (map[posY+1][posX]!=0) return;
		posY++;
		map[posY][posX] = 2;
		break;
	case left:
		if (map[posY][posX-1]!=0) return;
		posX--;
		map[posY][posX] = 2;
		break;
	case right:
		if (map[posY][posX+1]!=0) return;
		posX++;
		map[posY][posX] = 2;
		break;
	}
}



void drawMap()//�迭�� ���� ����ϴ� ����� ���� �Լ�
{
	//�迭�� �� ���
	for (int y = 0; y < 10; y++)
	{
		for (int x = 0; x < 10; x++)
		{
			switch (map[y][x])
			{
			case 0: //��ĭ
				printf("  ");
				break;
			case 1: //��
				printf("��");
				break;
			case 2: // ����
				printf("��");
				break;
			}
		}
		printf("\n");
	}
}//drawMap()...



//����� �Է� �˰���
 /*int ch;
 ch=getch();//getch �Լ� ��� ��
 if (ch>200)ch = getch();//getch �Լ� ��� ��

 switch (ch)
 {
 case 72:
 printf("up");
 break;
 case 80:
 printf("down");
 break;
 case 75:
 printf("left");
 break;
 case 77:
 printf("right");
 break;
 default:
 printf("�߸��� �Է��Դϴ�");
 }*/
