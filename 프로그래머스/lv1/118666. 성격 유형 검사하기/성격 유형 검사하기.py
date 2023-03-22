def solution(survey, choices):
    answer = ''
    mbti = 'RTCFJMAN'
    myDic = {'A': 0, 'N': 0, 'C': 0, 'F': 0, 'M': 0, 'J': 0, 'R': 0, 'T': 0}
    
    for i in range(0, len(choices)):
        if choices[i] > 4:
            myDic[survey[i][1]] += choices[i] - 4
            
        elif choices[i] < 4:
            myDic[survey[i][0]] += 4 - choices[i]
    print(myDic)
    for i in range(0, 4):
        if myDic[mbti[i * 2]] >= myDic[mbti[i * 2 + 1]]:
            answer += mbti[i * 2]
            
        else:
            answer += mbti[i * 2 + 1]
    return answer