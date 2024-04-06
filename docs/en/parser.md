# Parser

## Algorithm
**The parser uses the marshalling yard algorithm:**

* Not all tokens have been processed yet:
* Read the token.
    * If the token is a number, then add it to the output queue.
    * If the token is a function, then put it on the stack.
    * If the token is a separator for the arguments of the function (for example, a comma):
        * While the token is at the top of the stack, there is no opening bracket:
          * Move the operator from the stack to the output queue.
          * If the stack ended before the opening parenthesis token was encountered, then the function argument separator (comma) is omitted in the expression, or the opening parenthesis is omitted.

    * If the token is an op1 operator, then:
        * As long as the op2 operator token is present at the top of the stack, whose priority is higher or equal to the priority of op1, and if the priorities are equal, op1 is left-associative:
            * Move op2 from the stack to the output queue;
        * Put op1 on the stack.

    * If the token is an opening bracket, then put it on the stack.
    * If the token is a closing bracket:
      * While the token is at the top of the stack, it is not an opening bracket
        * Move an operator from the stack to the output queue.
        * If the stack ended before the opening parenthesis token was encountered, then the parenthesis is omitted in the expression.
      * Remove the opening parenthesis from the stack, but do not add it to the output queue.
      * If the token at the top of the stack is a function, put it in the output queue.

* If there are no more tokens left at the input:
  * While there are tokens operators in the stack:
    * If the token operator at the top of the stack is an opening parenthesis, then the parenthesis is omitted in the expression.
    * Move an operator from the stack to the output queue.
## Chain of handlers
![Chain of handlers](/docs/images/chain%20of%20handlers.png)

## Working with the parser
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