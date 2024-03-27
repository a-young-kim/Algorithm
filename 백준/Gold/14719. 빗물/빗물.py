import sys
input=sys.stdin.readline

h, w=map(int, input().split())
data=list(map(int, input().split()))

result=0
stack=[data[0]]

for d in data:
    if stack[0]<=d:
        if len(stack)>=2:
            a=min(stack[0], d)
            result=result+a*len(stack)-sum(stack)
        stack=[d]
    else:
        stack.append(d)
while True:
    if not stack or len(stack)==1:
        print(result)
        break
    else:
        a=max(stack[1:])
        b=stack[1:].index(a)+1
        
        result=result+a*b-sum(stack[1:b+1])
        stack=stack[b:]