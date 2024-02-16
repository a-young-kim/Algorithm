def DFS(f):
    result_DFS.append(f)
    for x in data:
        if f in x:
            i=x.index(f)
            if x[1-i] not in result_DFS:
                DFS(x[1-i])
    
def BFS(f):
    while True:
        a=[]
        for x in data:
            if f in x:
                i=x.index(f)
                if x[1-i] not in result_BFS and x[1-i] not in a:
                    a.append(x[1-i])
        a.sort()
        if len(a)!=0:
            for x in a:
                result_BFS.append(x)
                result.append(x)

        result.pop(0)
        if len(result)==0:
            break
        
        else:
            f=result[0]



n, m, v=map(int, input().split())
data=[]
for _ in range(0, m):
    a, b=map(int, input().split())
    data.append([a, b])
    data.append([b, a])
data.sort()

result_DFS=[]
DFS(v)

result_BFS=[v]
result=[v]
count=[]
BFS(v)

for i in result_DFS:
    print(i, end=" ")
print()
for i in result_BFS:
    print(i, end=" ")