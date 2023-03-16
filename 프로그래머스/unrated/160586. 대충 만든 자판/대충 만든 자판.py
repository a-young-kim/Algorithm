def solution(keymap, targets):
    answer = []
    myList = {}
    
    for i in range(0, len(keymap)):
        for j in range(0, len(keymap[i])):
            if keymap[i][j] not in myList:
                myList[keymap[i][j]] = j + 1
                
            else:
                myList[keymap[i][j]] = min(myList[keymap[i][j]], j + 1)
         
    for i in range(0, len(targets)):
        sum = 0
        for j in range(0, len(targets[i])):
            if targets[i][j] in myList:
                sum += myList[targets[i][j]]
            else:
                sum = -1
                break
        answer.append(sum)
    
    return answer