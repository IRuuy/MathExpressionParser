# Обход синтаксически абстрактного дерева
### Стандартный обход без использования переменных
```java
ExpressionNodeVisitor visitor = new DefaultExpressionNodeVisitor();
VisitorData result = node.accept(visitor);
```

### Стандартных обход с использованием переменных
```java
Map<String, VisitorData> data = new HashMap<>();
data.put(key, new VisitorData(value));

ExpressionNodeVisitor visitor = new DefaultExpressionNodeVisitor(data);
VisitorData result = node.accept(visitor);
```

### Обход используя кастомный Visitor
Необходимо создать класс, который наследуется от `DefaultExpressionNodeVisitor` и переопределить метод, который вы хотите кастомизировать.
```java
public class VisitorImpl extends DefaultExpressionNodeVisitor {
    @Override
    public VisitorData visitFunctionNode(FunctionNode node) {
        // code...
    }
}
```
Далее обходим синтаксически абстрактное дерево с использованием нового Visitor.
```java
ExpressionNodeVisitor visitor = new VisitorImpl();
VisitorData result = node.accept(visitor);
```