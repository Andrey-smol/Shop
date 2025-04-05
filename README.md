# Задача Магазин

## Описание
В этом задании попрактикуемся с правилами чистого кода и принципами SOLID.

Нужно написать программу-магазин, в которой пользователи заказывают товары. Вам предоставляется свобода в продумывании функциональности вашей программы, как и в проектировании её структуры. В процессе реализации вы должны применить принцип избегания магических чисел, DRY и как минимум 4 из 5 принципов SOLID, при отправке решения в комментарии к работе указать по одному примеру применения каждого принципа в вашем решении со ссылками на конкретные места кода на гитхабе и обоснованием в чём заключалось применение принципа.

Примеры возможностей программы:
* Вывод доступных для покупки товаров
* Фильтрация товаров по ключевым словам, ценам, производителям
* Составление продуктовой корзины пользователя
* Трекинг заказа в системе доставки
* Возврат заказа, повтороение заказа
* Система рейтинга для товаров
* Простая рекомендательная система для покупок

## Реализация
1. Продумайте и зафиксируйте список возможностей вашей программы.
2. Продумайте консольный интерфейс взаимодействия пользователя с вашей программой.
3. Продумайте систему классов, которые вам понадобятся для реализации вашей программы.
4. Напишите вашу программу, явно применив вышеперечисленные принципы хорошего кода.
5. При отправке решения укажите, в чём именно было применение каждого этого принципа (по одному примеру) со ссылками на конкретные места кода на гитхабе.
   
## DRY
(не повторяй свой код) [dry - ](https://github.com/Andrey-smol/Shop/blob/24f9e8a0f655d465bbe472cc52ba99fd098c01bb/src/main/java/ru/netology/Main.java#L231)
Проверка на ввод пользователем строки которую можно перевести в число, вынесена в отдельный метод

## SOLID

- S: принцип единсвенной ответственности (Класс должен выполнять только те функции, для которых он логически предназначен)
  [s - ](https://github.com/Andrey-smol/Shop/blob/24f9e8a0f655d465bbe472cc52ba99fd098c01bb/src/main/java/ru/netology/address/Address.java#L3)
  имеется класс Address который хранит информацию о адресе покупателя
- O: принцип открытости/закрытости (Програмные сущности должны быть открыты для расширения, но закрыты для модификации)
  [o - ](https://github.com/Andrey-smol/Shop/blob/24f9e8a0f655d465bbe472cc52ba99fd098c01bb/src/main/java/ru/netology/storage/Categories.java#L6)
  есть класс Categories товаров, ему передаётся ссылка на объект реализующий интерфейс ICategoriesStorage где хранятся эти категории 
- L: принцип замены Барбары Лисков (Наследуй только тогда, когда можешь играть роль за предка)
  
- I: принцип сегрегации (разделения) интерфейса (Много интерфейсов, специально предназначенных для клиентов, лучше, чем один интерфейс общего назначения)[i - ](https://github.com/Andrey-smol/Shop/blob/058a0bde44659c2ef16c4649766f79444c1ea46b/src/main/java/ru/netology/storage/CustomerProductsAdapter.java#L9)
  связь клиента и администратора со складом можно было сделать напрямую через один интерфейс, но для клиента надо только читать данные, а для администратора возможность их изменять, поэтому для клиента сделан отдельный адаптер с интерфейсом
- D: принцип инверсии зависимостей (Всё зависит от абстракции (интерфейсов), а не от деталей реализации друг друга)
  [d - ](https://github.com/Andrey-smol/Shop/blob/058a0bde44659c2ef16c4649766f79444c1ea46b/src/main/java/ru/netology/storage/CustomerProductsAdapter.java#L22)
  В данный метод передаётся реализация интерфейса и на момент написания метода мы не знаем как он будет реализован. В методе происходит поиск объектов по условию переданному через интерфейс.