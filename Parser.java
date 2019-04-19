import java.io.*;
import java.util.*;

public class Parser{
  public static void main(String[] args) throws IOException{
    String fn = "ExampleProgram.psdc";
    List<Token> tokens = Lexer.tokenize(fn);
    //System.out.println(tokens.get(0).toString());
  }
}