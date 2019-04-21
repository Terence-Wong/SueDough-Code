import java.io.*;
import java.util.*;
/*
produces Abstract Syntax Tree

organize list of tokens from lexer into a tree

Key problems:
- organize operations in correct order:  3+5*2 -> 3+(5*2)
- organize bracketed items in correct order: fitting 3*(5+2) into AST
- 
*/ 
public class Parser{
  static ArrayList<Node> program = new ArrayList<Node>();
  static List<Token> tokens;
  public static enum State{
    REGULAR, 
    ASSIGNMENT, 
    EXPRESSION,
    SEMI_EXPRESSION;
  }
  public static void main(String[] args) throws IOException{
    String fn = "ExampleProgram.psdc";
    tokens = Lexer.tokenize(fn);
    
    recurse(State.REGULAR);

    Node programNode = new ProgramNode(program);

  }

  public static void recurse(State state){

  }
}
