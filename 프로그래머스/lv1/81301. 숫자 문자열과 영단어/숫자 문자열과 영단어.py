def solution(s):
    answer = 0
    numDic = {'zero' : 0, 'one' : 1, 'two' : 2, 'three' : 3, \
             'four' : 4, 'five' : 5, 'six' : 6, 'seven' : 7, \
             'eight' : 8, 'nine' : 9}
    
    ch = ""
    for i in range(0, len(s)):
        try:
            num = int(s[i])
            
            if ch == '':
                answer = answer * 10 + num
                
            else:
                answer = answer * 100 + numDic[ch] * 10 + num
                
            ch = ''
            
        except:
            ch += s[i]
            
            if ch in numDic:
                answer = answer * 10 + numDic[ch] 
                ch = ''
            
    return answer