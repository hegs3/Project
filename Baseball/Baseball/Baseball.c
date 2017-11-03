#include <stdio.h>
#include <stdlib.h>
#include <time.h>
void main()
{
	srand(time(NULL));

	int a = rand() % 10;
	int b = rand() % 10;
	int c = rand() % 10;
	
	printf("야구게임 start\n");
	for (int i=10;i>0;i--) 
	{
		printf("\n");
		printf("###################\n");
		printf("--->남은 기회 %d번\n", i);
		int us;
		printf("--->정수 입력 : "); 
		scanf("%d", &us); 

		int hun = us / 100; //100의 자리
		int ten = (us % 100) / 10; // 10의 자리
		int one = us % 10; //1의 자리

		int str=0; //스트라이크 변수
		if (hun == a)	str++;
		if (ten == b)	str++;
		if (one == c)	str++;
	

		int ball = 0; //볼 변수
		if (ten == a) ball++;
		if (one == a) ball++;
		if (hun == b) ball++;
		if (one == b) ball++;
		if (hun == c) ball++;
		if (ten == c) ball++;
		
		if (hun == a&& ten == b && one == c) { printf("\n----VICTORY--THE END----"); break; }
		printf("--->%d - ST / %d - BL\n", str, ball); // 결과 출력
		printf("###################\n");
		printf("컴퓨터 정답:%d %d %d", a, b, c);
		printf("\n");
		//printf("100의자리 수:%d \n", hun);
		//printf("10의자리 수:%d\n", ten);
		//printf("1의자리 수:%d\n", one);
	}

}
/* 2개중 하
if(
hun == a S
ten == a B
one == a B

hun == b B
ten == b S
one == b B

hun == c B
ten == c B
one == c S


if (hun == 0) printf("\n잘못된 입력입니다.");
else if (ten == 0) printf("\n잘못된 입력입니다.");
else if (one == 0) printf("\n잘못된 입력입니다.");
*/
