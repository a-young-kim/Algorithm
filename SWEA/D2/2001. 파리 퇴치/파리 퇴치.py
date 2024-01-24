def find(n, m, nArray, mArray, total):
    result = []
    for i in range(n * n):
        row = i // n
        col = i % n
        sum = 0
        try:
            for j in range(m):
                for k in range(m):
                    sum += nArray[row + k][col + j]

            result.append(sum)

        except:
            continue

    return result




T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    n, m = map(int, input().split(" "))
    nArray = []
    for i in range(n):
        nArray.append(list(map(int, input().split(" "))))

    mArray = [[0 for _ in range(m)] for _ in range(m)]
    result = find(n, m, nArray, mArray, 0)
    print(f"#{test_case} {max(result)}")