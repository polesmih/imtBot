# imtBot (java)  
Telegram-бот для расчета индекса массы тела с отправкой пользователю рекомендаций и истории предыдущих ИМТ.   

Бот в Telegram: [@BodyMI_bot](https://t.me/BodyMI_bot)  
[Статья на Хабр](https://habr.com/ru/articles/785752/)
 

## Лицензия  
Этот проект лицензирован по лицензии MIT.  
Подробности в файле LICENSE  

## Автор  
Олеся Пономарева  

## В проекте использованы:  
Java  
MySQL  
GitHub - repository  
Telegram Bot API  

Полный список используемых зависимостей и версий компонентов можно найти в pom.xml  

В проект добавлена база данных для учета пользователей, которая развернута на виртуальном хостинге https://beget.com/ru.

### Демонстрация функционала:  

1. Активация бота и меню    
![1](https://github.com/polesmih/imtBot/assets/77875474/f3583b16-46ba-4797-9941-baf819b2d719)
  
  
2. Приветствие отправка запроса с параметрами и ответ бота с рассчетом ИМТ   
![IMG_6077](https://github.com/polesmih/imtBot/assets/77875474/5a3e923e-56de-4a23-9c12-4df402a6203e)
  
   
3. Отправка истории предыдущих результатов и реакция бота на некорректный запрос     
![IMG_6079](https://github.com/polesmih/imtBot/assets/77875474/2a34980b-0c0f-408d-b8f8-036dd683e8f2)
   
  

  
### Сборка и запуск бота:  
Запуск бота в среде разработки (IntelliJ IDEA) производится через запуск класса Main.java.  
Также возможно создать исполняемый jar-файл и запускать его (как в среде разработки, так и на витртуальных ресурсах). В проекте (pom.xml) реализована возможность создания такого файла через плагин Apache Assembly  
