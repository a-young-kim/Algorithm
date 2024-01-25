import sys

input=sys.stdin.readline

hole, using_count=map(int, input().split())
data=list(input().split())

current_data=[]
count=0
for i in range(0, len(data)):
    if len(current_data)<hole and data[i] not in current_data:
        current_data.append(data[i])
    
    elif data[i] in current_data:
        continue
    
    else:
        find_max=0
        for current in current_data:
            if current not in data[i+1:]:
                max_text=current
                break
            else:
                max_num=data[i+1:].index(current)+i+1
                find_max=max(max_num, find_max)
                if find_max==max_num:
                    max_text=current
        current_data.remove(max_text)
        current_data.append(data[i])
        count=count+1

print(count)
