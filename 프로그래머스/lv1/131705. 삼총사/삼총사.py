def solution(number):
    answer = 0
    result = []
    for i in range(0, len(number) - 2):
        for j in range(i + 1, len(number) - 1):
            for k in range(j + 1, len(number)):
                if number[i] + number[j] + number[k] == 0:
                    result.append([i, j, k])
        
        answer = len(result)
                
    return answer