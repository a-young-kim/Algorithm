from collections import deque

def solution(prices):
    answer=[0]*len(prices)

    for i in range(0, len(prices)):
        for j in range(i, len(prices)):
            if prices[i]>prices[j]:
                answer[i]=j-i
                break

        if j==len(prices)-1:
            answer[i]=j-i


    return answer