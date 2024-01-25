import sys

input=sys.stdin.readline

n=int(input())
dp=[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
num=11
while True:
    if len(dp)>n:
        break

    else:
        data=list(map(int, list(str(dp[-1]))))
        data[len(data)-1]=data[len(data)-1]+1

        for i in range(len(data)-1, -1, -1):
            if len(data)>10:
                break
            elif i!=len(data)-1 and data[i]==10:
                data[i]=i
                data[i+1]=data[i+1]+1

            elif i!=len(data)-1 and data[i]==data[i+1]:
                data[i]=data[i]+1
                data[i+1]=len(data)-2-i
        
        if data[0]==10:
            d=[]
            for i in range(len(data), -1, -1):
                d.append(i)
            data=d

        dp.append(int("".join(list(map(str, data)))))

        if len(data)>10:
            break

if len(dp)>1023:
    print(-1)

else:
    print(dp[n])
