import sys
input=sys.stdin.readline

x, y=map(int, input().split())

a=max(x, y)
b=min(x, y)
while True:
    r=a%b
    if r!=0:
        a=b
        b=r
    elif r==0:
        break

print(b)
print(x*y//b)