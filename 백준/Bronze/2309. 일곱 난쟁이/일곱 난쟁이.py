data=[]
for i in range(0, 9):
    data.append(int(input()))

data.sort(reverse=True)#큰수부터
result=[]
for i in range(0, 9):
    s=sum(data)
    if s-data[i]<=100:
        continue
    else:
        s=s-data[i]
        for j in range(i, 9):
            if s-data[j]==100:
                result.append(i)
                result.append(j)
                break
        if len(result)!=0:
            break

for i in range(8, -1, -1):
    if i not in result:
        print(data[i])