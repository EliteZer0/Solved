#include <stdio.h>

int main(){
    int year = 0;
    int is_leap = 0;
    scanf("%d", &year);
    
    if(year%4 == 0 && year%100 != 0){
        is_leap = 1;
    }
    
    if(year%4 == 0 && year%400 == 0){
        is_leap = 1;
    }

    printf("%d", is_leap);
    
    return 0;
}