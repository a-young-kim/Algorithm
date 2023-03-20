def solution(n, m, section):
    answer = 0
    
    start = section[0]
    
    for i, s in enumerate(section):
        if start <= s:
            start = s + m
            answer += 1
            
        
        
    return answer