def solution(storey):
    answer = 0

    while storey != 0 :
        one = int(storey % 10)
        ten = int(storey % 100 / 10)
        
        print(storey, " ", one, " " , ten, " " , answer)
        
        if one > 5:
            answer += 10 - one
            storey += 10
        
        elif one == 5 and ten >= 5:
            answer += 10 - one
            storey += 10
        
        else:
            answer += one
        
        storey = int(storey/10)
        
    return answer