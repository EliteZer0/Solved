#include <stdio.h>

int main()
{
    int n = 0;
    scanf("%d", &n);
    for (int i = 1; i <= 9; i++)
    {
        printf("%d * %d = %d",n,i,n*i);
        printf("\n");
    }
    return 0;
}