#include <stdio.h>
#include <stdlib.h>
#include <time.h>
void main()
{
	srand(time(NULL));

	int a = rand() % 10;
	int b = rand() % 10;
	int c = rand() % 10;
	
	printf("�߱����� start\n");
	for (int i=10;i>0;i--) 
	{
		printf("\n");
		printf("###################\n");
		printf("--->���� ��ȸ %d��\n", i);
		int us;
		printf("--->���� �Է� : "); 
		scanf("%d", &us); 

		int hun = us / 100; //100�� �ڸ�
		int ten = (us % 100) / 10; // 10�� �ڸ�
		int one = us % 10; //1�� �ڸ�

		int str=0; //��Ʈ����ũ ����
		if (hun == a)	str++;
		if (ten == b)	str++;
		if (one == c)	str++;
	

		int ball = 0; //�� ����
		if (ten == a) ball++;
		if (one == a) ball++;
		if (hun == b) ball++;
		if (one == b) ball++;
		if (hun == c) ball++;
		if (ten == c) ball++;
		
		if (hun == a&& ten == b && one == c) { printf("\n----VICTORY--THE END----"); break; }
		printf("--->%d - ST / %d - BL\n", str, ball); // ��� ���
		printf("###################\n");
		printf("��ǻ�� ����:%d %d %d", a, b, c);
		printf("\n");
		//printf("100���ڸ� ��:%d \n", hun);
		//printf("10���ڸ� ��:%d\n", ten);
		//printf("1���ڸ� ��:%d\n", one);
	}

}
/* 2���� ��
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


if (hun == 0) printf("\n�߸��� �Է��Դϴ�.");
else if (ten == 0) printf("\n�߸��� �Է��Դϴ�.");
else if (one == 0) printf("\n�߸��� �Է��Դϴ�.");
*/
