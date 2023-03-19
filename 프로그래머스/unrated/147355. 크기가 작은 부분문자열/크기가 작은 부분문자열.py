def solution(t, p):
    answer = 0
    myList = []
    
    for i in range(0, len(t) - len(p) + 1):
        myList.append(int(t[i:i + len(p)]))
            
       
    for num in myList:
        if int(p) >= num:
            answer += 1
            
    return answer