/*
#5
Выведите таблицу со средним возрастом студентов во всех группах 
(Группа, Средний возраст), где средний возраст равен среднему возрасту в группе 1101.
*/
SELECT 
  УЧЕНИКИ.ГРУППА AS "ГРУППА", 
  (
    SELECT 
      AVG(
        extract(
          YEAR 
          FROM 
            age(
              Н_ЛЮДИ.ДАТА_РОЖДЕНИЯ
            )
        ) :: INTEGER
      ) AS "СРЕДНИЙ ВОЗРАСТ" 
    FROM 
      Н_УЧЕНИКИ 
      JOIN Н_ЛЮДИ ON (
        Н_ЛЮДИ.ИД = Н_УЧЕНИКИ.ЧЛВК_ИД
      ) 
    WHERE 
      (
        Н_УЧЕНИКИ.ГРУППА = '1101'
      )
  ) AS "СРЕДНИЙ ВОЗРАСТ" 
FROM 
  Н_УЧЕНИКИ AS УЧЕНИКИ 
GROUP BY 
  (УЧЕНИКИ.ГРУППА);
