def solution(id_list, report, k):
    answer = []
    myDic = {}
    idDic = {}
    
    report = list(set(report))
    for id in id_list:
        myDic[id] = 0
        idDic[id] = []
        
    for ch in report:
        start , end = ch.split(" ")
        idDic[start].append(end)
        myDic[end] += 1
                
    result = []
    for id in id_list:
        if myDic[id] >= k:
            result.append(id)
            
    for id in id_list:
        count = 0
        for name in idDic[id]:
            if name in result:
               count+= 1
            
        answer.append(count)
            
        
    return answer