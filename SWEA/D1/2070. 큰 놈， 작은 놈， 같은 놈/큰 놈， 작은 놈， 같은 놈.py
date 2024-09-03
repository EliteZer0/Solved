T = int(input())
for test_case in range(1, T + 1):
    a,b = map(int, input().split())
    if (a>b) : sym = ">"
    elif (a<b) : sym = "<"
    else : sym = "="
    print("#" + str(test_case) + " " + sym)