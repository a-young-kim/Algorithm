import sys, queue
input=sys.stdin.readline

num, num_of_compare=map(int, input().split())

find=queue.Queue()
data=[[] for x in range(0, num+1)]
data_count=[0 for x in range(0, num+1)]
data_count[0]=-1
result=[]

for i in range(0, num_of_compare):
    first, second=map(int, input().split())
    data_count[second]=data_count[second]+1
    data[first].append(second)

for i in range(1, num+1):
        if data_count[i]==0:
            data_count[i]=-1
            find.put(i)

while find.empty()!=True:

    d=find.get()
    result.append(d)

    for i in data[d]:
        data_count[i]=data_count[i]-1
        if data_count[i]==0:
            find.put(i)


for r in result:
    print(r, end=" ")