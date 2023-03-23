def solution(sizes):
    answer = 0
    myX = 0
    myY = 0
    
    for size in sizes:
        x = max(size[0], size[1])
        y = min(size[0], size[1])
        
        myX = max(myX, x)
        myY = max(myY, y)
        
    answer = myX * myY
    return answer