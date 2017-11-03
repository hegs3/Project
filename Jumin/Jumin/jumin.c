#include <stdio.h>
//1. 13자리의 주민번호 입력
//2. 19자리까지 입력 가능
//3. 입력된 숫자(문자형)를 각 변수에 주입
//4. 문자형 숫자를 계산을 위한 변수에 주입
//5.
//12[(각 자리의 정해진 숫자 곱샘 234567892345)%11=i >>> 11-i=m]+1(m)

void main()
{	//' ' ==32                   '-' == 45                 '0'==48           '9' == 57
	char seat[20] = {0}; //개인정보 입력(공백, - 인식을 위한 변수)
	int num[20];//개인정보 입력 변수(문자형 > 정수형 을위한 변수)
	int sum=0; //0~6자리 비교연산
	int	sum2=0;//1~12자리의 총합
	int m = 0;//13자리 비교 값 연산
	int k;

	printf("입력 : ");
	for (k = 0; k < 20; k++) 
	{
		scanf("%c", &seat[k]);
		if (seat[k] == 10) break;
		num[k] = seat[k] - 48;
	}
	num[k] = 0;
	
	if (num[13]==0)
	{
		for (int i = 0; i < 8; i++)
		{
			sum = num[i] * (i+2);
			sum2 += sum;
		}
		for (int i = 8; i <12 ; i++)
		{
			sum = num[i] * (i -6);
			sum2 += sum;
		}
		m = (11 - (sum2 % 11)) % 10;
		if (num[12] == m) printf("주민 number oK\n");
		else printf("Personal DATA NO!!!!!\n");
	}
	else if (seat[6] == 32 || seat[6] == 45)
	{
		for (int i = 0; i < 6; i++)
		{
			sum = num[i] * (i + 2);
			sum2 += sum; 
		}
		for (int i = 7; i < 9; i++)
		{
			sum = num[i] * (i + 1);
			sum2 += sum; 
		}
		for (int i = 9; i <13; i++)
		{
			sum = num[i] * (i - 7);
			sum2 += sum;
		}
		m = (11 - (sum2 % 11)) % 10;
		if (num[13] == m) printf("주민 number oK\n");
		else printf("Personal DATA NO!!!!!\n");
	}
	else if (num[16] == 0)
	{
		for (int i = 0; i < 20; i++)
		{

			if (num[i] < 0 || num[i] > 9)
			{
				printf("Personal DATA NO!!!!\n"); break;
			}
			else 
			{
				printf("카드 number ok\n"); break;
			}
		}
	}
	else printf("Personal DATA NO!!!!!\n");
}
