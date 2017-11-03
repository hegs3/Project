#include <stdio.h>
#include <conio.h>
#include <Windows.h>

void DrawMap();
void move();
int gameover();

char map[11][20] = {
	//1 외부 테두리
	//0 내부 빈공간
	//2 Game벽
	//3 Game box
	//4 user
	//5 도착지점					  
	{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	{1,0,0,0,0,2,2,2,2,2,2,0,0,0,0,0,0,0,0,1},
	{1,0,0,0,0,2,0,0,0,0,2,0,0,0,0,0,0,0,0,1},
	{1,0,0,0,0,2,0,0,0,3,2,2,2,2,0,0,0,0,0,1},
	{1,0,2,2,2,2,0,0,0,0,0,0,0,2,2,2,2,2,0,1},
	{1,0,2,0,0,0,0,0,4,3,0,0,0,2,2,2,2,2,0,1},
	{1,0,2,0,0,0,2,0,0,2,2,0,0,0,0,0,5,2,0,1},
	{1,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,1},
	{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
};
int psi, psj;


void main()
{
	psi = 6;
	psj = 8;
	while (1)
	{
		system("cls");
		DrawMap();
		if (gameover())
		{
			printf("--------GAME CLEARER\n");
			break;
		}
		move();
	}
	printf("\n");
}//END MAIN

int gameover()
{
	if (map[7][16] != 3) return 0;


	for (int i = 0; i < 8; i++)
	{
		for (int j = 0; j < 16; j++)
		{			
			if (map[i][j] == 3) return 0;

		}
	}
	
	return 1;

}

void move()
{
	int user;
	int dx = 0, dy = 0;
	user = getch();
	if (user == 224 || user == 0) user = getch();
	printf("%d\n", user);

	switch (user)
	{
	case 72:
		dx = -1;
		dy = 0;
		break;
	case 80:
		dx = +1;
		dy = 0;
		break;
	case 75:
		dx = 0;
		dy = -1;
		break;
	case 77:
		dx=0;
		dy=+1;
		break;
	}
	
	printf("%d %d\n", dx, dy);
	if (map[psi+dx][psj + dy] == 2) return;
	if (map[psi+dx][psj + dy] == 3)
	{
		if (map[psi + dx * 2][psj + dy*2] == 2) return;
		map[psi][psj] = 0;
		psi += dx;
		psj += dy;
		map[psi][psj] = 4;
		map[psi + dx][psj + dy] = 3;
		return;
	}
	if (map[psi + dx][psj + dy] == 0)
	{
		map[psi][psj] = 0;
		psi += dx;
		psj += dy;
		map[psi][psj] = 4;
		return;
	}
}

void DrawMap()
{
	for (int i = 0; i < 11; i++)
	{
		for (int j = 0; j < 20; j++)
		{
			switch (map[i][j])
			{
			case 0:
				printf("  ");
				break;
			case 1:
				printf("▩");
				break;
			case 2:
				printf("♨");
				break;
			case 3:
				printf("♬");
				break;
			case 4:
				printf("＊");
				break;
			case 5:
				printf("○");
				break;
			}
		}
		printf("\n");
	}
}//END DRAWMAP