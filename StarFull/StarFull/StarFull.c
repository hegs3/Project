//ASCII코드 1byte 표기법: '//UNI코드 2byte 표기법: "
#include <stdio.h>// #- 전처리기
#include <conio.h>// getch(); 콘솔용 입출력 헤더
#include <Windows.h>//system("cls"); 시스템에 명령을 내리기위한 헤더

//매크로 상수
#define up 72
#define down 80
#define left 75
#define right 77

int checkGameOver();
void drawMap();// 함수의 프로토 타입
void move();
//전역변수
char map[10][10] = 
{//빈칸 EMPTY 0  //벽 WALL 1  //상자 BOX 2
//2byte의 특수문자(ㅁ한자류)를 사용하기 위해 숫자로 지정해놓음
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
	//내상자의 시작 위치
	posY = 1; posX = 1;

	while(1)
	{
	//콘솔창의 화면을 클리어
		system("cls");//시스템에 명령을 내리는 함수
		drawMap();//배열의 값 출력함수 호출
		//게임종료인지 검사

		if (checkGameOver())
		{
			printf("Stage Clear~~~\n\n");
			break;
		}
		move(); // 키보드를 입력받아 이동시키는 함수 호출
	}

	

}//main()...

//게임 종료 체크 함수

int checkGameOver()
{
	for (int y = 0; y < 10; y++)
	{
		for (int x = 0; x < 10; x++)
		{
			if (map[y][x] == 0) return 0;
	
		}
	}
	//여기까지 커서가 도달했다면
	//배열요소 100개중에서 0이 한개도 없다든 것을 의미함
	return 1;
}

void move()//키보드 입력받아 방향키에 따라 이동 시키는기능을 가진 함수
{
	//사용자의 방향키 입력받기.. 상자 움직이기(scanf 사용 x 엔터로 입력을 마무리하기 떄문)
	//getch(); - 4byte - 한 문자형만 받을 수 있음(char) > int 형으로 변환
	//키보드를 누르는 순간 입력이 됨
	//방향키의 정수형은 모두 224 : 키보드의 넘버락 방향키가 존재하기 때문에
	//방향키는 변수에 2번 저장 한다
	int key;
	key = getch();
	if (key == 224 || key == 0) key = getch();

	switch (key)
	{			
	case up:
		//위칸이 비어있는가> 윗칸이 0이냐?
		//혹은, 위칸이 0이 아니냐? 아니면 그만둬...
		if (map[posY-1][posX]!=0) return;
		posY--;
		map[posY][posX] = 2;
		break;
	case down:
		//아래칸이 0이 아니냐? 아니면 그만둬...
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



void drawMap()//배열의 값을 출력하는 기능을 가진 함수
{
	//배열의 값 출력
	for (int y = 0; y < 10; y++)
	{
		for (int x = 0; x < 10; x++)
		{
			switch (map[y][x])
			{
			case 0: //빈칸
				printf("  ");
				break;
			case 1: //벽
				printf("▩");
				break;
			case 2: // 상자
				printf("★");
				break;
			}
		}
		printf("\n");
	}
}//drawMap()...



//사용자 입력 알고리즘
 /*int ch;
 ch=getch();//getch 함수 사용 법
 if (ch>200)ch = getch();//getch 함수 사용 법

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
 printf("잘못된 입력입니다");
 }*/
