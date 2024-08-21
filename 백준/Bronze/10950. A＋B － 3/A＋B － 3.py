tc = int(input())
for i in range(tc) :
    numbers = input().split()
    answer = sum(int(x) for x in numbers)
    print(answer)