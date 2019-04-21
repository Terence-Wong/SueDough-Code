import java.io.*;
import java.util.List;


import java.util.LinkedList;
import java.util.HashMap;

public class Lexer{
  static List<Token> tokens = new LinkedList<Token>();
  static HashMap<String,Type> symbols = new HashMap<>(){{
    put("(", Type.SEPARATOR);
    put(")", Type.SEPARATOR);

    put("==", Type.OPERATOR);
    put(">=", Type.OPERATOR);
    put("<=", Type.OPERATOR);
    put("!=", Type.OPERATOR);
    put("-", Type.OPERATOR); 
    put("+", Type.OPERATOR); 
    put("*", Type.OPERATOR); 
    put("/", Type.OPERATOR);
    put("%", Type.OPERATOR); 
    put("^", Type.OPERATOR); 
    put("=", Type.OPERATOR);
    put(">", Type.OPERATOR);
    put("<", Type.OPERATOR);

    put("PRINT", Type.KEYWORD);
    put("IF", Type.KEYWORD);
    put("THEN", Type.KEYWORD);
    put("ELSEIF", Type.KEYWORD);
    put("ELSE", Type.KEYWORD);
    put("ENDIF", Type.KEYWORD);
    put("WHILE", Type.KEYWORD);
    put("ENDWHILE", Type.KEYWORD);
    put("REPEAT", Type.KEYWORD);
    put("UNTIL", Type.KEYWORD);
    put("CASE", Type.KEYWORD);
    put("OF", Type.KEYWORD);
    put("OTHERS", Type.KEYWORD);
    put("ENDCASE", Type.KEYWORD);
    put("FOR", Type.KEYWORD);
    put("ENDFOR", Type.KEYWORD);
    put("BEGIN", Type.KEYWORD);
    put("END", Type.KEYWORD);
    put("EXCEPTION", Type.KEYWORD);
    put("WHEN", Type.KEYWORD);
  }}; 
  public static enum Type{
    IDENTIFIER,
    KEYWORD,
    OPERATOR,
    SEPARATOR,
    LITERAL,
    COMMENT;
  }
  public static void wordHandler(String in){
    //System.out.println(in);
    if(symbols.containsKey(in)){
      tokens.add(new Token(symbols.get(in), in));
    }else{
      tokens.add(new Token(Type.IDENTIFIER, in));
    }
  }

  public static int wordWrapper(int i, String line){
    //'word' wrapping here has potential flaws
    /* will only continue looking for next character if the current
    character is a letter/digit/quote. (breaks if unrecognized symbol) The 
    type of the token is determined by the last character

    asdf"asd --> identifier (asdf"asd)
    asdf"asd"hello  --> literal (asdf"asd")

    */
    Type t = null;
    int j = i;
    boolean quotes = false;
    for(; j < line.length(); ){
      if(Character.isDigit(line.charAt(j))){
        //check if nmber is literal, might have to account for other edge cases
        j++;
        t = Type.LITERAL;
      }else if(line.charAt(j) == '"'){
        if(quotes){
          j++;
          t = Type.LITERAL;
          break;
        }else{
          j++;
          quotes = true;
        }
      }else if(Character.isWhitespace(line.charAt(j))){
        if(quotes){
          j++;
        }else{
          break;
        }
      }else{ //unrecognized character
        //assume identifier (letter, symbol, etc)
        j++;
        t = Type.IDENTIFIER;
      }
    }
    String input = line.substring(i,j);
    //System.out.println(input);
    if(t == Type.IDENTIFIER){
      wordHandler(input); 
    }else if(t == Type.LITERAL){
      tokens.add(new Token(t, input));
    }else{// t == null, error

    }
    return j;
  }

  public static void main(String[]args) throws IOException{
    //going to assume args has the file were going to need
    String fileName = "ExampleProgram.psdc";//args[0];
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    //System.out.println(br.readLine());

    String line = br.readLine();
    while(line != null){

      //System.out.println(line);

      for(int i = 0; i < line.length();){
        if(Character.isWhitespace(line.charAt(i))){
          i++;
        }else{
          i = wordWrapper(i, line);
        }
      }
      line = br.readLine();
    }
    printTokens();
  }

  public static List<Token> tokenize(String filename) throws IOException{
    BufferedReader br = new BufferedReader(new FileReader(filename));
    String line = br.readLine();
    while(line != null){
      for(int i = 0; i < line.length();){
        if(Character.isWhitespace(line.charAt(i))){
          i++;
        }else{
          i = wordWrapper(i, line);
        }
      }
      line = br.readLine();
    }
    return tokens;
  }

  public static void printTokens(){
    for(Token t : tokens){
      System.out.println(t.toString());
      System.out.println("");
    }
  }

}