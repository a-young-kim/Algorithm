def solution(s):
    answer = 0
    
    same = 0
    diff = 0
    myCh = s[0]
    
    for i, ch in enumerate(s):
        if ch == myCh:
            same += 1
        
        else:
            diff += 1
        
        if same == diff:
            print(ch)
            answer += 1
            same == 0
            diff == 0
            if len(s) > i + 1:
                myCh = s[i + 1]
                
        elif (same != 0) and (i == len(s) - 1):
            answer += 1
            
        
    return answer