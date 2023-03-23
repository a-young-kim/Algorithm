def solution(id_list, report, k):
    answer = [0] * len(id_list)
    myDic = {x : 0 for x in id_list}
    
    report = list(set(report))
    
    for ch in report:
        start , end = ch.split(" ")
        myDic[end] += 1

    for i in range(0, len(report)):
        start , end = report[i].split(" ")

        if myDic[end] >= k:
            answer[id_list.index(start)] += 1
    
        
    return answer