#include <stdio.h>
//1. 13�ڸ��� �ֹι�ȣ �Է�
//2. 19�ڸ����� �Է� ����
//3. �Էµ� ����(������)�� �� ������ ����
//4. ������ ���ڸ� ����� ���� ������ ����
//5.
//12[(�� �ڸ��� ������ ���� ���� 234567892345)%11=i >>> 11-i=m]+1(m)

void main()
{	//' ' ==32                   '-' == 45                 '0'==48           '9' == 57
	char seat[20] = {0}; //�������� �Է�(����, - �ν��� ���� ����)
	int num[20];//�������� �Է� ����(������ > ������ ������ ����)
	int sum=0; //0~6�ڸ� �񱳿���
	int	sum2=0;//1~12�ڸ��� ����
	int m = 0;//13�ڸ� �� �� ����
	int k;

	printf("�Է� : ");
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
		if (num[12] == m) printf("�ֹ� number oK\n");
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
		if (num[13] == m) printf("�ֹ� number oK\n");
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
				printf("ī�� number ok\n"); break;
			}
		}
	}
	else printf("Personal DATA NO!!!!!\n");
}
