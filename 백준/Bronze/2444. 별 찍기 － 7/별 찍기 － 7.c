#include <stdio.h>

int main()
{
    int n = 0;
    scanf("%d", &n);
    for (int i = 0; i < n; i++)
    {   
        for (int j = 1; j<n-i; j++){
            printf(" ");
        }
        for (int k = 0; k < 2*i+1; k++){
            printf("*");
        }
        printf("\n");
    }
    for (int i = 0; i < n-1; i++)
    {   
        for (int j = 0; j<i+1; j++){
            printf(" ");
        }
        for (int k = 0; k < 2*(n-i-1)-1; k++){
            printf("*");
        }
        printf("\n");
    }
    return 0;
}