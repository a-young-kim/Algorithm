alphabets = ['A', 'E', 'I', 'O', 'U']
wordDic = {}
def setDic(str):
    wordDic[str] = len(wordDic)
    if len(str) > 4: return
    
    for i in range(0, 5):
        setDic(str + alphabets[i])

def solution(word):
    answer = 0
    setDic("")
    
    answer = wordDic[word]
    return answer