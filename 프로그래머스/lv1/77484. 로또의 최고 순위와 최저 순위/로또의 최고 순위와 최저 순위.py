def solution(lottos, win_nums):
    answer = [0, 0]
    
    lottos.sort()
    win_nums.sort()
    
    cnt = 0
    for num in lottos:
        if num == 0:
            cnt += 1
            
        else:
            for win in win_nums:
                if win == num:
                    answer[0] += 1
                    answer[1] += 1
                    break
                    
    answer[0] = 7 - answer[0] - cnt
    answer[1] = 7 - answer[1] 
    
    if answer[1] == 7:
        answer[1] = 6
    
    if answer[0] == 7:
        answer[0] = 6
        
    return answer