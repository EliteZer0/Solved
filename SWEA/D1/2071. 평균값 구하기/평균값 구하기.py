T = int(input())
for test_case in range(1, T + 1):
    arr = list(map(int, input().split()))
    s = sum(arr)
    m = round(s/10)
    print("#" + str(test_case) + " " + str(m))