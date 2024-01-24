for test_case in range(1, 10 + 1):
    num = int(input())
    numList = list(map(int, input().rstrip().split(" ")))

    numList.sort()
    count = 0
    smallList = [numList.pop(0)]
    bigList = [numList.pop()]

    while num > count:
        if len(numList) == 0:
            break

        if numList[0] > smallList[0]:
            smallList[0] += 1
            count += 1

            if numList[-1] < bigList[-1]:
                bigList[-1] -= 1

            elif numList[-1] >= bigList[-1]:
                bigList.append(numList.pop() - 1)


        elif numList[0] <= smallList[0]:
            smallList.append(numList.pop(0))

        smallList.sort()
        bigList.sort()




    print(f"#{test_case} {max(bigList) - min(smallList)}")