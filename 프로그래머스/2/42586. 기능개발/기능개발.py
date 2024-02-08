import queue
from re import A
def solution(progresses, speeds):
    answer = []
    q_program=queue.Queue()
    q_speeds=queue.Queue()
    
    for pro in progresses:
        q_program.put(pro)

    for sp in speeds:
        q_speeds.put(sp)

    day=0
    while q_program.qsize()!=0:
        pre_day=day
        program=q_program.get()
        speed=q_speeds.get()

        while True:
            if program+speed*day>99:
                break

            day=day+1

        if pre_day==day:
            answer[-1]=answer[-1]+1

        else:
            answer.append(1)

    return answer