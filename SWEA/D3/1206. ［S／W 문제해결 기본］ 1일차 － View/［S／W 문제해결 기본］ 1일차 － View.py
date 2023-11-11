def checkSun(apartments, target):
    sorted_list = sorted(apartments, reverse=True)
    index = apartments.index(sorted_list[0])
    if sorted_list[0] == target:
        return sorted_list[0] - sorted_list[1], 2

    else:
        return 0, 0

for test_case in range(1, 11):
    T = int(input())
    result = 0
    apartments = list(map(int, input().split(' ')[:-1]))
    num = 2
    while num < len(apartments):
        count, index = checkSun(apartments[num - 2: num + 3], apartments[num])
        result += count
        num = num + index + 1
    print(f"#{test_case} {result}")