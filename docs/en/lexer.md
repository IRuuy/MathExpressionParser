## Create token stream

### Standard set of lexemes
```java
Lexer lexer = new Lexer();
TokenStream stream = lexer.getTokenStream(statement);
```

### Customized set of lexemes
You can either assemble a list of tokens
yourself or use the `LexemeListFactory`, however, in the first case
, the standard list of tokens must be included in the list of tokens.
You can do this using: `LexemeListFactory.generateStandardLexemes()`.

The implementation is in the tests - [LexerTest](/src/test/java/ru/shulgindaniil/token/LexerTest.java), at the `getTokensWithCustomOptionsTest` method, or in the contract example below.
```java
List<TokenType> lexemeList = LexemeListFactory.getLexemeList(
        List.of(CustomUnaryOperator.values()),
        List.of(CustomBinaryOperator.values()),
        List.of(CustomFunction.values())
);

Lexer lexer = new Lexer(lexemeList);
TokenStream stream = lexer.getTokenStream(statement);
```

## Lexemes
### Operations
<table>
    <tr>
        <th>Priority</th>
        <th>Type of operation</th>
        <th>Operators</th>
    </tr>
    <tr>
        <td>1</td>
        <td>Unary</td>
        <td>~ <i>(Unary minus)</i></td>
    </tr>
    <tr>
        <td>2</td>
        <td>Binary</td>
        <td>* <i>(Multiplication)</i>, / <i>(Division)</i>, % <i>(Remainder of division)</i></td>
    </tr>
    <tr>
        <td>3</td>
        <td>Binary</td>
        <td>+ <i>(Addition)</i>, + <i>(Subtraction)</i></td>
    </tr>
    <tr>
        <td>4</td>
        <td>Binary</td>
        <td>=, >, <, >=, <=, (<>, or !=) <i>(Comparison operators)</i></td>
    </tr>
    <tr>
        <td>5</td>
        <td>Unary</td>
        <td>NOT <i>(Logical NOT)</i></td>
    </tr>
    <tr>
        <td>6</td>
        <td>Binary</td>
        <td>AND <i>(Logical AND)</i></td>
    </tr>
    <tr>
        <td>7</td>
        <td>Binary</td>
        <td>OR <i>(Logical OR)</i>, XOR <i>(Exclusive OR)</i></td>
    </tr>
</table>

### Functions
**The number of arguments is unlimited!**
<table>
    <tr>
        <th>Function</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>MAX</td>
        <td>Finding the maximum number</td>
    </tr>
    <tr>
        <td>MIN</td>
        <td>Finding the minimum number</td>
    </tr>
  <tr>
        <td>AVG</td>
        <td>Calculating the arithmetic mean</td>
    </tr>
</table>

### Standard lexemes
<table>
    <tr>
        <th>Lexeme</th>
        <th>Regular expression</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>IDENTIFIER</td>
        <td>[A-Za-zА-Яа-я0-9_]*</td>
        <td>Name of the variable</td>
    </tr>
    <tr>
        <td>STRING</td>
        <td>\"(.*)\"</td>
        <td>String</td>
    </tr>
    <tr>
        <td>NUMBER</td>
        <td>[0-9]+</td>
        <td>Number</td>
    </tr>
    <tr>
        <td>LPAR</td>
        <td>\(</td>
        <td>The left opening parentheses</td>
    </tr>
    <tr>
        <td>RPAR</td>
        <td>\)</td>
        <td>The right closing parentheses</td>
    </tr>
    <tr>
        <td>SPACE</td>
        <td>[\s\t]</td>
        <td>Spaces (space, tab and new line)</td>
    </tr>
    <tr>
        <td>COMMA</td>
        <td>,</td>
        <td>A comma as a separator for function arguments</td>
    </tr>
</table>
