  

def solution(X, Y):
    answer = ''
    
    result = []

    for i in range(0, 10):
        result.append(min(X.count(str(i)), Y.count(str(i))))

    for i in range(9, -1, -1):
        answer += str(i) * result[i]
        
    if answer == '':
        answer = '-1'

    elif answer[0] == '0':
        answer = '0'
        
    return answer