#include <stdio.h>
#define BIG_ENDIAN 0 
#define LITTLE_ENDIAN 1 
int TestByteOrder() {
	short int word = 0x0001;
	char *byte = (char *) &word;
	return (byte[0] ? LITTLE_ENDIAN : BIG_ENDIAN);
}

int main(){
	printf("%d\n", TestByteOrder());
	return 1;
}