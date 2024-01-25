import sys, time
input=sys.stdin.readline

num_of_node, num_of_branch=map(int, input().split())
data=[]

for i in range(0, num_of_branch):
    node1, node2, cost=map(int, input().split())
    small_node=min(node1, node2)
    big_node=max(node1, node2)
    data.append([small_node, big_node, cost])
data=sorted(data, key=lambda x:x[2])
result_node=[x for x in range(0, num_of_node+1)]
count=0
result=[]
s=time.time()

def end_find(end):
    if result_node[end]==end:
        return end

    else:
        result_node[end]=end_find(result_node[end])
        return result_node[end]

def start_find(start):
    if result_node[start]==start:
        return start

    else:
        result_node[start]=start_find(result_node[start])
        return result_node[start]

def set_find(end, start):
    if result_node[end]==end:
        result_node[end]=start
        return start

    else:
        result_node[end]=set_find(result_node[end], start)
        return result_node[end]
for i in range(0, num_of_branch):
    start=data[i][0]
    end=data[i][1]
    cost=data[i][2]

    if i==0:
        result_node[end]=start
        count=count+cost

    else:        
        end_find(end)
        start_find(start)
        if result_node[end]!=result_node[start]:
            result_node[end]=set_find(end, start)
            count=count+cost
  
print(count)