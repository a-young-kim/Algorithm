num=int(input())

data=[]
for i in range(0, num):
    data.append(int(input()))

dp=[0]*(num+3)
r=[0]*(num+3)

dp[0]=data[0]
if num>1:
    dp[1]=data[0]+data[1]

r[0]=1
r[1]=2
if num>2:
    a=max(data[0]+data[2], data[1]+data[2])
    if a==data[0]+data[2]:
        r[2]=1
    else:
        r[2]=2
    dp[2]=a
    for i in range(3, num):
        if r[i-1]!=2:
            a=max(dp[i-1]+data[i], dp[i-2]+data[i])
            if a==dp[i-1]+data[i]:
                r[i]=2
            else:
                r[i]=1
            dp[i]=a
        else:
            a=max(dp[i-2]+data[i], dp[i-3]+data[i-1]+data[i])
            if a==dp[i-2]+data[i]:
                r[i]=1
            else:
                r[i]=2
            dp[i]=a

print(dp[num-1])
