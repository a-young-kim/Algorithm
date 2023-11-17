T = int(input())


def find(predict):
    result, first = 0, 0
    first = predict.pop()
    numberSum = 0

    while len(predict) != 0:
        second = predict.pop()
        if result == 0 and first - second > 0:
            result = first - second
            numberSum = first - second

        else:
            numberSum = numberSum + first - second
            if numberSum > 0:
                result += numberSum

            else:
                numberSum = 0

        first = second

    return result

for test_case in range(1, T + 1):
    N = int(input())
    predict = list(map(int, input().split(" ")))
    print(f"#{test_case} {find(predict)}")