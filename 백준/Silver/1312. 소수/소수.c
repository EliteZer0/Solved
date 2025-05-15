#include <stdio.h>

int main() {
    int A, B, N;
    scanf("%d %d %d", &A, &B, &N);
    
    A = A % B;
    for (int i = 0; i < N - 1; i++) {
        A = (A * 10) % B;
    }

    int ans = (A * 10) / B;
    printf("%d\n", ans);
    
    return 0;
}