def solution(wallpaper):
    answer = []
    
    min_x = float("inf")
    min_y = float("inf")
    max_x = float("-inf")
    max_y = float("-inf")
    
    length_y = len(wallpaper[0])
    length_x = len(wallpaper)
    
    for x in range(0, length_x):
        for y in range(0, length_y):
            if wallpaper[x][y] == '#':
                min_y = min(min_y, y)
                min_x = min(min_x, x)
                
                max_y = max(max_y, y)
                max_x = max(max_x, x)
    
    answer = [min_x, min_y, max_x + 1, max_y + 1]
    
    return answer