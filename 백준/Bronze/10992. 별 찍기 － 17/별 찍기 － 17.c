#include <stdio.h>

int main()
{   int n = 0;
    scanf("%d", &n);
    
    for(int i = 0; i<n; i++){
        for(int j =n-i-1; j>0; j--){
            printf(" ");
        }
        printf("*");
        if(i>0 && i<n-1){
            for(int j =0; j<2*i-1; j++){
                printf(" ");
            }
            printf("*");
        }
        if(i == n-1){
            for(int j = 1; j<2*n-1;j++){
                printf("*");
            }
        }
        printf("\n");
    }

    return 0;
}