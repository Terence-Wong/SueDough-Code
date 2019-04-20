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
  public static void main(String[] args) throws IOException{
    String fn = "ExampleProgram.psdc";
    List<Token> tokens = Lexer.tokenize(fn);
    //System.out.println(tokens.get(0).toString());
    Node test = new ProgramNode(new ArrayList<Node>());
    System.out.println(test.toJSONString());
    System.out.println(true);
  }
}
