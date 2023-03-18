def solution(s, skip, index):
    answer = ''
    # 아스키 코드 97 = a, 122 = z
    alpha = []
    skip_num = []
    
    for ch in skip:
        skip_num.append(ord(ch))
        
    for i in range(97, 123):
        if i not in skip_num:
            alpha.append(chr(i))

    for ch in s:
        ch_num = ord(ch)
        count = 0 
        for n in skip_num:
            if ch_num > n:
                count += 1
                
        ch_num = ch_num - count
        
        myNum = ch_num + index - 97
        answer += alpha[myNum % len(alpha)]
            
    return answer