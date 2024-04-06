# Лексер
## Создание потока лексем

### Стандарный набор лексем
```java
Lexer lexer = new Lexer();
TokenStream stream = lexer.getTokenStream(statement);
```

### Кастомизированный набор лексем
Вы можете, как сами собрать список лексем,
так и использовать `LexemeListFactory`,  однако в первом случае
стандарный список лексем обязан быть включен в список лексем.
Сделать это можно используя: `LexemeListFactory.generateStandardLexemes()`.

Реализация есть в тестах лексера - [LexerTest](/src/test/java/ru/shulgindaniil/token/LexerTest.java), в методе `getTokensWithCustomOptionsTest`, либо в асбтрактном примере ниже.

```java
List<TokenType> lexemeList = LexemeListFactory.getLexemeList(
        List.of(CustomUnaryOperator.values()),
        List.of(CustomBinaryOperator.values()),
        List.of(CustomFunction.values())
);

Lexer lexer = new Lexer(lexemeList);
TokenStream stream = lexer.getTokenStream(statement);
```
## Лексемы
### Операции
<table>
    <tr>
        <th>Приоритет</th>
        <th>Тип операции</th>
        <th>Операторы</th>
    </tr>
    <tr>
        <td>1</td>
        <td>Унарная</td>
        <td>~ <i>(Унарный минус)</i></td>
    </tr>
    <tr>
        <td>2</td>
        <td>Бинарная</td>
        <td>* <i>(Умножение)</i>, / <i>(Деление)</i>, % <i>(Остаток деления)</i></td>
    </tr>
    <tr>
        <td>3</td>
        <td>Бинарная</td>
        <td>+ <i>(Сложение)</i>, + <i>(Вычитание)</i></td>
    </tr>
    <tr>
        <td>4</td>
        <td>Бинарная</td>
        <td>=, >, <, >=, <=, (<>, либо !=) <i>(Операторы сравнения)</i></td>
    </tr>
    <tr>
        <td>5</td>
        <td>Унарная</td>
        <td>NOT <i>(Логическое НЕ)</i></td>
    </tr>
    <tr>
        <td>6</td>
        <td>Бинарная</td>
        <td>AND <i>(Логическое И)</i></td>
    </tr>
    <tr>
        <td>7</td>
        <td>Бинарная</td>
        <td>OR <i>(Логическое ИЛИ)</i>, XOR <i>(Исключающее ИЛИ)</i></td>
    </tr>
</table>

### Функции
**Колличество аргументов не ограничено!**
<table>
    <tr>
        <th>Функция</th>
        <th>Описание</th>
    </tr>
    <tr>
        <td>MAX</td>
        <td>Поиск максимального числа</td>
    </tr>
    <tr>
        <td>MIN</td>
        <td>Поиск минимального числа</td>
    </tr>
  <tr>
        <td>AVG</td>
        <td>Подсчёт среднего арифметического</td>
    </tr>
</table>

### Стандартные лексемы
<table>
    <tr>
        <th>Лексема</th>
        <th>Регулярное выражение</th>
        <th>Описание</th>
    </tr>
    <tr>
        <td>IDENTIFIER</td>
        <td>[A-Za-zА-Яа-я0-9_]*</td>
        <td>Название переменной</td>
    </tr>
    <tr>
        <td>STRING</td>
        <td>\"(.*)\"</td>
        <td>Строка</td>
    </tr>
    <tr>
        <td>NUMBER</td>
        <td>[0-9]+</td>
        <td>Число</td>
    </tr>
    <tr>
        <td>LPAR</td>
        <td>\(</td>
        <td>Левая открывающая скобка</td>
    </tr>
    <tr>
        <td>RPAR</td>
        <td>\)</td>
        <td>Правая закрывающая скобка</td>
    </tr>
    <tr>
        <td>SPACE</td>
        <td>[\s\t]</td>
        <td>Пробелы (пробел, табуляция и новая строка)</td>
    </tr>
    <tr>
        <td>COMMA</td>
        <td>,</td>
        <td>Запятая, как разделитель аргументов функции</td>
    </tr>
</table>
