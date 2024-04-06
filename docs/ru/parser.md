# Парсер
## Алгоритм

**Парсер испольльзует алгоритм сорировочной станции:**

* Пока не все токены обработаны:
  * Прочитать токен.
  * Если токен — число, то добавить его в очередь вывода.
  * Если токен — функция, то поместить его в стек.
  * Если токен — разделитель аргументов функции (например, запятая):
    * Пока токен на вершине стека не открывающая скобка:
      * Переложить оператор из стека в выходную очередь.
      * Если стек закончился до того, как был встречен токен открывающая скобка, то в выражении пропущен разделитель аргументов функции (запятая), либо пропущена открывающая скобка.

  * Если токен — оператор op1, то:
    * Пока присутствует на вершине стека токен оператор op2, чей приоритет выше или равен приоритету op1, и при равенстве приоритетов op1 является левоассоциативным:
      * Переложить op2 из стека в выходную очередь;
    * Положить op1 в стек.

  * Если токен — открывающая скобка, то положить его в стек.
  * Если токен — закрывающая скобка:
    * Пока токен на вершине стека не открывающая скобка 
      * Переложить оператор из стека в выходную очередь. 
      * Если стек закончился до того, как был встречен токен открывающая скобка, то в выражении пропущена скобка. 
    * Выкинуть открывающую скобку из стека, но не добавлять в очередь вывода.
    * Если токен на вершине стека — функция, переложить её в выходную очередь.

* Если больше не осталось токенов на входе:
  * Пока есть токены операторы в стеке:
    * Если токен оператор на вершине стека — открывающая скобка, то в выражении пропущена скобка.
    * Переложить оператор из стека в выходную очередь.

## Цепочка обработчиков
![Цепочка обработчиков](/docs/images/chain%20of%20handlers.png)

## Работа с парсером
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