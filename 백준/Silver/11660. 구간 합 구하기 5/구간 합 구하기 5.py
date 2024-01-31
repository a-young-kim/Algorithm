import sys

n, m=map(int, sys.stdin.readline().split())
n=n+1
data=[0]*n
data_sum=[0]*n

find=[]
for i in range(1, n):
    data[i]=[0]+list(map(int, sys.stdin.readline().split()))
    data_sum[i]=[0]*n
    
data[0]=data_sum[0]=[0]*n

for _ in range(0, m):
    find.append(list(map(int, sys.stdin.readline().split())))

for i in range(1, n):
    for j in range(1, n):
        data_sum[i][j]=data[i][j]+data_sum[i-1][j]+data_sum[i][j-1]-data_sum[i-1][j-1]

for d in find:
    x1=d[0]
    y1=d[1]
    x2=d[2]
    y2=d[3]
    
    print(data_sum[x2][y2]-data_sum[x2][y1-1]-data_sum[x1-1][y2]+data_sum[x1-1][y1-1])