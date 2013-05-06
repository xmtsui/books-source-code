#include <stdio.h>

char* doReverse(char* ch, int n)
{
	int i=0;
	for(i=(n-1)>>1; i>=0; --i)
	{
		char tmp = ch[i];
		ch[i] = ch[n-1-i];
		ch[n-1-i] = tmp;
	}
	return ch;
}
int main()
{
	char ch[] = {'1','2','3','4'};
	char* tmp = doReverse(ch,4);
	int i=0;
	for(;i<4;++i)
	{
		printf("%c", tmp[i]);
	}
	printf("\n");
}