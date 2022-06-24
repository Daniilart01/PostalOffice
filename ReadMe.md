	Проект является имитацией работы почтового приложения, работающего через БД
Запуск:
* Скачать Oracle Database EE, SQL Developer
* При установке указать и запомнить любой %password%
* Запустить SQL Plus, Логин: system, пароль %password%
* В SQL Plus выполнить следующие команды:
1. Alter session set container = XEPDB1; (Enter)
2. Create tablespace Postal_Office (Enter)
3. Datafile '(...path to oracle ...)\oradata\XE\XEPDB1\Postal_Office.dbf' (Enter)
4. Size 200M;(Enter)
5. Create user danil (Enter)
6. Identified by danil (Enter)
7. Default tablespace Postal_Office (Enter)
8. Quota unlimited on Postal_Office;(Enter)
9. Grant DBA to danil;(Enter)
          
* Открыть SQL Developer
* Нажать +
* Ввести любое имя
* username: danil
* password: danil
* Внизу окна выбрать Service name и ввести XEPDB1
* Нажать Connect
* После подключения в верхнем левом углу выбрать File и открыть файл "Postal Office.sql"
* Когда файл откроется нажать F5, затем ОК
           
Настройка БД закончена, теперь открываем в IntellIj IDEA разархивированный проект, 
идем File->Project Structure->Libraries->+->Java->ищем в папке проекта ojdbc11.jar, подключаем
           
Проект готов к запуску (открыть в IDEA файл App и нажать Ctrl+Shift+F10