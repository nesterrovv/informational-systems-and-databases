/*
#6 
Получить список студентов, зачисленных ровно первого сентября 2012
года на первый курс очной формы обучения.
В результат включить:
номер группы
номер, фамилию, имя и отчество студента
номер и состояние пункта приказа
Для реализации использовать подзапрос с IN
*/
SELECT 
  УЧЕНИКИ.ГРУППА AS "Номер группы", 
  УЧЕНИКИ.ИД AS "Номер студента", 
  ЛЮДИ.ФАМИЛИЯ AS "Фамилия", 
  ЛЮДИ.ИМЯ AS "Имя", 
  ЛЮДИ.ОТЧЕСТВО AS "Отчество", 
  УЧЕНИКИ.П_ПРКОК_ИД AS "Номер приказа", 
  УЧЕНИКИ.СОСТОЯНИЕ AS "Состояние приказа" 
FROM 
  Н_УЧЕНИКИ AS УЧЕНИКИ 
  JOIN Н_ЛЮДИ AS ЛЮДИ ON (
    УЧЕНИКИ.ЧЛВК_ИД = ЛЮДИ.ИД
  ) 
WHERE 
  (
    УЧЕНИКИ.ИД IN (
      SELECT 
        ИД 
      FROM 
        Н_УЧЕНИКИ AS УЧЕНИКИ 
      WHERE 
        (
          УЧЕНИКИ.ГРУППА LIKE '%1' 
          AND (
            УЧЕНИКИ.ВИД_ОБУЧ_ИД = 1 
            OR УЧЕНИКИ.ВИД_ОБУЧ_ИД = 3
          ) 
          AND УЧЕНИКИ.НАЧАЛО = '01.09.2012'
        )
    )
  );
