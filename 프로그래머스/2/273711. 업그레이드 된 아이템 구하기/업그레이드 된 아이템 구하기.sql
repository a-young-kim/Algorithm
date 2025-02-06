-- 코드를 작성해주세요
/*
아이템 업그레이드 가능
ITEM_A'를 'ITEM_B' 의 PARENT 아이템
PARENT 아이템이 없는 아이템을 ROOT 아이템이라고 합니다.

1. 아이템의 희귀도가 RARE인 아이템
2. 업그레이드 아이템의 아이템 ID, 아이템 명, 아이템 희귀도 출력
3. 결과는 아이템 id 기준으로 내림차순 정렬

업그레드 아이템을 어떻게 다 구할 것인가... => 재귀 모르는데
*/

SELECT ITEM_INFO.ITEM_ID, ITEM_NAME, RARITY
FROM ITEM_INFO
WHERE ITEM_INFO.ITEM_ID IN (
    SELECT ITEM_TREE.ITEM_ID
    FROM (
        SELECT ITEM_ID
        FROM ITEM_INFO
        WHERE RARITY = "RARE"
        ) AS RARE_INFO
    JOIN ITEM_TREE
    ON RARE_INFO.ITEM_ID = ITEM_TREE.PARENT_ITEM_ID
)
ORDER BY ITEM_INFO.ITEM_ID DESC;
