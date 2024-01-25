import sys
import time

input=sys.stdin.readline
word_num, alphabet_num=map(int, input().split())
words=[]
alphabets_in_words=[]
nessecerry_alphabets={"a":1, "n":1, "t":1, "i":1, "c":1}

for i in range(0, word_num):
    word=list(input().rstrip())
    word=set(word[4:-4])
    insert_word=[]
    for w in word:
        if w not in nessecerry_alphabets:
            insert_word.append(w)
            if w not in alphabets_in_words:
                alphabets_in_words.append(w)
    words.append(insert_word)


count=0
result=True
def find(i):
    global count, result
    if len(nessecerry_alphabets)==alphabet_num or len(nessecerry_alphabets)==len(alphabets_in_words)+5:
        no_count=0
        for word in words:
            for alphabet in word:
                if nessecerry_alphabets.get(alphabet)==None:
                    no_count=no_count+1
                    break
        count=max(len(words)-no_count, count)
        return 

    else:
        for j in range(i, len(alphabets_in_words)):
            nessecerry_alphabets[alphabets_in_words[j]]=1
            find(j+1)
            del nessecerry_alphabets[alphabets_in_words[j]]
    

if alphabet_num<5:
    print(0)
elif alphabet_num>=len(alphabets_in_words)+5:
    print(len(words))
else:
    find(0)
    print(count)