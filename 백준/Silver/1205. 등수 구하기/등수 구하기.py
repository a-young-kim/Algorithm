n, my_score, p=map(int, input().split())

if n!=0:
    score=[x for x in map(int, input().split())]
    if n==p and score[n-1]>=my_score:
        print("-1")
    elif score[n-1]>my_score:
        print(n+1)
    else:
        for i in range(0, n):
            if score[i]<=my_score:
                print(i+1)
                break

               
else:
    print("1")