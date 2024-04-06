# MathExpressionParser
![JUnit](https://img.shields.io/badge/JUnit5-black?style=for-the-badge&logo=java&link=https%3A%2F%2Fwww.java.com%2Fen%2F)
![Java](https://img.shields.io/badge/java-black?style=for-the-badge&logo=openjdk&link=https%3A%2F%2Fspring.io)
![Maven](https://img.shields.io/badge/Maven-black?style=for-the-badge&logo=apachemaven)

**[Russian-language documentation](/docs/ru/readme.md)**

## The purpose of the work
This project solves the following problem: in the case when there is a need to build, syntactically bypass the abstract tree of mathematical expressions, **with easy customization of tokens, priorities of operations** without the need to edit the grammar and re-engineer the parser code.

This solution has one non-obvious drawback. The parser works according to the sorting station algorithm, because of this, there is a need for a separate operation for the unary minus - "~".

## Table of content
* [Detailed description of the logic of the components](#detailed-description-of-the-logic-of-the-components)
* [Get Started](#get-started)
  * [Working with the parser](#working-with-the-parser)
  * [Traversing a constructed syntactically abstract tree](#traversing-a-constructed-syntactically-abstract-tree)
* [References](#references)

## Detailed description of the logic of the components
* [Lexer](docs/en/lexer.md#lexer)
  * [Create token stream](docs/en/lexer.md#create-token-stream)
    * [Standard set of lexemes](docs/en/lexer.md#standard-set-of-lexemes)
    * [Customized set of lexemes](docs/en/lexer.md#customized-set-of-lexemes)
  * [Lexemes](docs/en/lexer.md#lexemes)
    * [Operations](docs/en/lexer.md#operations)
    * [Lexemes](docs/en/lexer.md#functions)
    * [Standard lexemes](docs/en/lexer.md#standard-lexemes)
* [Parser](docs/en/parser.md#parser)
  * [Working with the parser](docs/en/parser.md#working-with-the-parser)
  * [Algorithm](docs/en/parser.md#algorithm)
  * [Chain of handlers](docs/en/parser.md#chain-of-handlers)


## Get Started
### Working with the parser
For the default case, you can use the `MathExpressionParserFacade` object:
```java
MathExpressionParserFacade parserFacade = new MathExpressionParserFacade();
parserFacade.parse(statement);
```

Underneath the facade lies the following logic, it is recommended to use the following method to work with the parser.
```java
Lexer lexer = new Lexer();
Parser parser = new MathExpressionParserFactory().create();
TokenStream stream = lexer.getTokenStream(statement);
ExpressionNode node = parser.parse(stream);
```
### Traversing a constructed syntactically abstract tree
The parsing result can be bypassed using - `DefaultExpressionNodeVisitor`:
```java
ExpressionNodeVisitor visitor = new DefaultExpressionNodeVisitor();
node.accept(visitor);
```

You can also add `DefaultExpressionNodeVisitor` to variables, which will be taken into account when traversing the tree:
```java
Map<String, VisitorData> data = new HashMap<>();
data.put("key", new VisitorData("value"));

ExpressionNodeVisitor visitor = new DefaultExpressionNodeVisitor(data);
node.accept(visitor);
```

## References
1. https://en.wikipedia.org/wiki/Shunting_yard_algorithm
2. https://habr.com/ru/articles/524874/