def solution(today, terms, privacies):
    answer = []
    
    Tyear, Tmonth, Tday = map(int, today.split('.'))
    
    termsDic = {}
    
    for term in terms:
        key, value = term.split()
        termsDic[key] = int(value)
    

    for i, p in enumerate(privacies):
        d, t = p.split(' ')
        year, month, day = map(int, d.split('.'))
        
        month = month + termsDic[t]
        
        while month > 12:
            year += 1
            month -= 12
            
        if Tyear > year:
            answer.append(i + 1)
            
        elif (Tyear == year) and (Tmonth > month):
            answer.append(i + 1)
            
        elif (Tyear == year) and (Tmonth == month) and (Tday >= day):
            answer.append(i + 1)
            
    
    return answer