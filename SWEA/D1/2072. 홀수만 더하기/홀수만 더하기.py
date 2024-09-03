#시도 횟수
T = int(input())
#시도 반복
for test_case in range(1, T+1) :
    ns = input().split()
    n = [int(x) for x in ns]
    odd = [x for x in n if x%2==1]
    s = sum(odd)
    print("#" + str(test_case) + " " + str(s))