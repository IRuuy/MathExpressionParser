# Парсер математических выражений

![JUnit](https://img.shields.io/badge/JUnit5-black?style=for-the-badge&logo=java&link=https%3A%2F%2Fwww.java.com%2Fen%2F)
![Java](https://img.shields.io/badge/java-black?style=for-the-badge&logo=openjdk&link=https%3A%2F%2Fspring.io)
![Maven](https://img.shields.io/badge/Maven-black?style=for-the-badge&logo=apachemaven)

## Цель работы
Данные проект решает следующую задачу: в случае, когда есть необходимость построить, обойти синтаксически асбтрактное дерево математических выражений, **с легкой кастомизацией лексем, приоритетов операций** без необходимости править граммитику и перегененировать код парсера.

У данного решения есть один неочевидный недостаток. Парсер работает по алгоритму сортировочной станции, из-за этого для унарного минуса есть необходимость в отдельной операции - "~".

## Оглавление
* [Детальное описание компонентов](#детальное-описание-компонентов)
* [Руководство пользователя](#руководство-пользователя)
    * [Работа с парсером](#работа-с-парсером)
    * [Обход построенного синтаксически абстрактного дерева](#обход-построенного-синтаксически-абстрактного-дерева)
* [Источники](#источники)

# Детальное описание компонентов
* [Лексер](lexer.md#лексер)
    * [Создание потока лексем](lexer.md#создание-потока-лексем)
        * [Стандарный набор лексем](lexer.md#стандарный-набор-лексем)
        * [Кастомизированный набор лексем](lexer.md#кастомизированный-набор-лексем)
    * [Лексемы](lexer.md#лексемы)
        * [Операции](lexer.md#операции)
        * [Функции](lexer.md#функции)
        * [Стандартные лексемы](lexer.md#стандартные-лексемы)
* [Парсер](parser.md#парсер)
  * [Парсер](parser.md#работа-с-парсером)
  * [Алгоритм](parser.md#алгоритм)
  * [Работа с парсером](parser.md#цепочка-обработчиков)

## Руководство пользователя
### Работа с парсером
Для случая по умолчанию, можно использовать объект `MathExpressionParserFacade`:
```java
MathExpressionParserFacade parserFacade = new MathExpressionParserFacade();
parserFacade.parse(statement);
```

Под фасадом лежит следующая логика, рекомендуется использовать следующий способ для работы с парсером.
```java
Lexer lexer = new Lexer();
Parser parser = new MathExpressionParserFactory().create();
TokenStream stream = lexer.getTokenStream(statement);
ExpressionNode node = parser.parse(stream);
```
### Обход построенного синтаксически абстрактного дерева
Результат парсинга можно обойти при помощи - `DefaultExpressionNodeVisitor`:
```java
ExpressionNodeVisitor visitor = new DefaultExpressionNodeVisitor();
node.accept(visitor);
```
Можно так же прокинуть в `DefaultExpressionNodeVisitor` переменные, которые будут учитываться при обходе дерева:
```java
Map<String, VisitorData> data = new HashMap<>();
data.put("key", new VisitorData("value"));

ExpressionNodeVisitor visitor = new DefaultExpressionNodeVisitor(data);
node.accept(visitor);
```

## Источники
1. https://en.wikipedia.org/wiki/Shunting_yard_algorithm
2. https://habr.com/ru/articles/524874/