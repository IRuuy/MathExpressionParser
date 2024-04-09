# Syntactically abstract tree traversal
### Standard bypass without using variables
```java
ExpressionNodeVisitor visitor = new DefaultExpressionNodeVisitor();
VisitorData result = node.accept(visitor);
```

### Standard traversal using variables
```java
Map<String, VisitorData> data = new HashMap<>();
data.put(key, new VisitorData(value));

ExpressionNodeVisitor visitor = new DefaultExpressionNodeVisitor(data);
VisitorData result = node.accept(visitor);
```

### Traversal using custom Visitor
You need to create a class that inherits from `DefaultExpressionNodeVisitor` and override the method that you want to customize.
```java
public class VisitorImpl extends DefaultExpressionNodeVisitor {
    @Override
    public VisitorData visitFunctionNode(FunctionNode node) {
        // code...
    }
}
```
Next, we traverse the syntactically abstract tree using the new Visitor.
```java
ExpressionNodeVisitor visitor = new VisitorImpl();
VisitorData result = node.accept(visitor);
```