def solution(cards1, cards2, goal):
    answer = 'Yes'
    
    cards1_num = 0
    cards2_num = 0
    
    for i in range(0, len(goal)):
        if (cards1_num < len(cards1)) and (goal[i] == cards1[cards1_num]):
            cards1_num += 1
            continue
        
        elif (cards2_num < len(cards2)) and (goal[i] == cards2[cards2_num]):
            cards2_num += 1
            continue
            
        else:
            answer = 'No'
            break
            
    return answer