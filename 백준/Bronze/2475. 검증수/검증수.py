a, b, c, d, e = map(int, input().split())
sum = a*a + b*b + c*c + d*d + e*e
answer = sum%10
print(answer)