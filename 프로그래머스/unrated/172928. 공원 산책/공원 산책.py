def solution(park, routes):
    answer = []
    
    h = len(park)
    x = len(park[0])
    
    for i in range(0, len(park)):
        if 'S' in park[i]:
            answer = [i, park[i].index('S')]
            break
            
            
    for i in range(0, len(routes)):
        op, n = routes[i].split(' ')
        
        if op == 'W':
            for j in range(1, int(n) + 1):
                if answer[1] - int(n) < 0:
                    break
                    
                elif park[answer[0]][answer[1] - j] == 'X':
                    break
                    
                elif j == int(n):
                    answer = [answer[0], answer[1] - int(n)]
                    
        elif op == 'E':
            for j in range(1, int(n) + 1):
                if answer[1] + int(n) >= x:
                    break
                    
                elif park[answer[0]][answer[1] + j] == 'X':
                    break
                    
                elif j == int(n):
                    answer = [answer[0], answer[1] + int(n)]
                    
        elif op == 'N':
            for j in range(1, int(n) + 1):
                if answer[0] - int(n) < 0:
                    break
                    
                elif park[answer[0] - j][answer[1]] == 'X':
                    break
                    
                elif j == int(n):
                    answer = [answer[0] - int(n), answer[1]]
                    
        elif op == 'S':
            for j in range(1, int(n) + 1):
                if answer[0] + int(n) >= h:
                    break
                    
                elif park[answer[0] + j][answer[1]] == 'X':
                    break
                    
                elif j == int(n):
                    answer = [answer[0] + int(n), answer[1]]
            
                    
                
                
                
            
    return answer