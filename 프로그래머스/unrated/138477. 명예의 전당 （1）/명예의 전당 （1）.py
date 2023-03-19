def solution(k, score):
    answer = []
    
    for i in range(1, len(score) + 1):
        myList = sorted(score[:i])
        
        if i < k:
            answer.append(myList[0])
            
        else:
            answer.append(myList[i - k])
    
    
    return answer