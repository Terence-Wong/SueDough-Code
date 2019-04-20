import java.util.*;

//check Node subclasses for error messages
/*
public static enum Type{
  PROGRAM, 
  ASSIGN, 
  COMPUTE, 
  CALL, 
  LITERAL, 
  VARIABLE, 
  CONDITIONAL;
}*/
abstract class Node{
  //public Type type; 
  public abstract String toJSONString();
}
class ProgramNode extends Node{
  List<Node> tasks;// = new ArrayList<Node>();
  public ProgramNode(List<Node> tasks){
    this.tasks = tasks;
  }
  public String toJSONString(){
    String begin = "{\nType: \"Program\",\nTasks:[\n";
    String end = "]\n}";
    for(Node n : tasks){
      begin += n.toJSONString()+",\n";
    }
    return begin + end;
  } 
}

class AssignmentNode extends Node{
  Node expression, variable;
  public AssignmentNode(Node variable, Node expression){
    this.variable = variable;
    this.expression = expression;
  }
  public String toJSONString(){
    return "{\nType: \"Assign\",\nVariable: " + variable.toJSONString() + ",\nExpression: " + expression.toJSONString() + "\n}";
  }
}

class ComputationNode extends Node{
  String operator;
  Node left, right;
  public ComputationNode(Node expLeft, Node expRight, String operator){
    this.operator = operator;
    left = expLeft;
    right = expRight;
  }
  public String toJSONString(){
    return "{\nType: \"Compute\",\nOperator: \""+ operator +"\",\nLeft: " + left.toJSONString() + ",\nRight: " + right.toJSONString() + "\n}";
  }
}

class FunctionCallNode extends Node{
  String function;
  List<Node> arguments;
  public FunctionCallNode(List<Node> args, String functionName){
    function = functionName;
    arguments = args;
  }
  public String toJSONString(){
    String begin = "{\nType: \"call\",\nFunction: \""+function+"\",\nArguments: [\n";
    String end = "]\n}";
    for(Node n : arguments){
      begin += n.toJSONString() + ",\n";
    }
    return begin + end;
  }
}

class NumericalLiteralNode extends Node{
  double value;
  public NumericalLiteralNode(double v){
    value = v;
  }
  public String toJSONString(){
    return "{\nType: \"NumericalLiteral\",\nValue: "+value+",\n}";
  }
}
class BooleanLiteralNode extends Node{
  boolean value;
  public BooleanLiteralNode(boolean v){
    value = v;
  }
  public String toJSONString(){
    return "{\nType: \"BooleanLiteral\",\nValue: "+value+",\n}";
  }
}
class StringLiteralNode extends Node{
  String value;
  public StringLiteralNode(String v){
    value = v;
  }
  public String toJSONString(){
    return "{\nType: \"StringLiteral\",\nValue: \""+value+"\",\n}";
  }
}

class VariableNode extends Node{
  String name;
  public VariableNode(String name){
    this.name = name;
  }
  public String toJSONString(){
    return "{\nType: \"Variable\",\nValue: \""+name+"\"\n}";
  }
}

class ConditionalNode extends Node{
  boolean loop, checkFirst;
  Node contingency, ifBlock, elseBlock;
  public ConditionalNode(Node condition, Node ifb, Node elseb, boolean loop, boolean checkFirst){
    this.loop = loop;
    this.checkFirst = checkFirst;
    contingency = condition;
    ifBlock = ifb;
    elseBlock = elseb;
  }
  public String toJSONString(){
    return "{\nType: \"Conditional\",\nLoop: " + loop + ",\nCheckFirst: " + checkFirst + ",\nContingency: "
      + contingency.toJSONString() + ",\nIfBlock: " + ifBlock.toJSONString() + ",\nElse: " + elseBlock.toJSONString()
      + "\n}";
  }
}