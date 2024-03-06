def solution(phone_book):
    answer = True
    phone_book=sorted(phone_book, key=lambda x: (x,len))

    for i in range(0, len(phone_book)-1):
        if phone_book[i]==phone_book[i+1][:len(phone_book[i])]:
            answer=False
            break
    return answer