#include <stdio.h>

int main()
{
    int n = 0;
    scanf("%d", &n);
    for (int i = 1; i <= n; i++)
    {   
        for (int j = 0; j < i; j++){
            printf("*");
        }
        for (int k = 0; k<2*(n-i); k++){
            printf(" ");
        }
        for (int l = 0; l < i; l++){
            printf("*");
        }
        printf("\n");
    }
    
    for (int i = 1; i < n; i++)
    {   
        for (int j = 0; j < n-i; j++){
            printf("*");
        }
        for (int k = 0; k<2*i; k++){
            printf(" ");
        }
        for (int l = 0; l < n-i; l++){
            printf("*");
        }
        printf("\n");
    }
    
    return 0;
}