import java.io.*;
import java.util.List;
import java.util.LinkedList;

public class Lexer{
  static List<Token> tokens = new LinkedList<Token>();
  public static enum Type{
    IDENTIFIER,
    KEYWORD,
    OPERATOR,
    SEPARATOR,
    LITERAL,
    COMMENT;
  }
  
  public static class Token{
    public final Type t;   // type of token 
    public final String c; // content
    /*
    add line number and column for error messages
    */
    public Token(Type type, String content){
      t = type;
      c = content;
    }
    public String toString() {
      return "TYPE: " + t.toString() + "\nCONTENT: " + c;
    }
  }
  public static void wordHandler(String in){
    //System.out.println(in);
    switch(in){
      case "PRINT":
        tokens.add(new Token(Type.KEYWORD, "PRINT"));
        break;
      case "IF":
        tokens.add(new Token(Type.KEYWORD, "IF"));
        break;
      case "THEN":
        tokens.add(new Token(Type.KEYWORD, "THEN"));
        break;
      case "ELSE":
        tokens.add(new Token(Type.KEYWORD, "ELSE"));
        break;
      case "ENDIF":
        tokens.add(new Token(Type.KEYWORD, "ENDIF"));
        break;
      case "WHILE":
        tokens.add(new Token(Type.KEYWORD, "WHILE"));
        break;
      case "ENDWHILE":
        tokens.add(new Token(Type.KEYWORD, "ENDWHILE"));
        break;
      case "REPEAT":
        tokens.add(new Token(Type.KEYWORD, "REPEAT"));
        break;
      case "UNTIL":
        tokens.add(new Token(Type.KEYWORD, "UNTIL"));
        break;
      case "CASE":
        tokens.add(new Token(Type.KEYWORD, "CASE"));
        break;
      case "OF":
        tokens.add(new Token(Type.KEYWORD, "OF"));
        break;
      case "OTHERS":
        tokens.add(new Token(Type.KEYWORD, "OTHERS"));
        break;
      case "ENDCASE":
        tokens.add(new Token(Type.KEYWORD, "ENDCASE"));
        break;
      case "FOR":
        tokens.add(new Token(Type.KEYWORD, "FOR"));
        break;
      case "ENDFOR":
        tokens.add(new Token(Type.KEYWORD, "ENDFOR"));
        break;
      case "BEGIN":
        tokens.add(new Token(Type.KEYWORD, "BEGIN"));
        break;
      case "END":
        tokens.add(new Token(Type.KEYWORD, "END"));
        break;
      case "EXCEPTION":
        tokens.add(new Token(Type.KEYWORD, "EXCEPTION"));
        break;
      case "WHEN":
        tokens.add(new Token(Type.KEYWORD, "WHEN"));
        break;
      case "==":
        tokens.add(new Token(Type.OPERATOR, "=="));
        break;
      case ">=":
        tokens.add(new Token(Type.OPERATOR, ">="));
        break;
      case "<=":
        tokens.add(new Token(Type.OPERATOR, "<="));
        break;
      case "!=":
        tokens.add(new Token(Type.OPERATOR, "!="));
        break;
      default:
        tokens.add(new Token(Type.IDENTIFIER, in));
        break;
    }
  }
  public static void main(String[]args) throws IOException{
    //going to assume args has the file were going to need
    String fileName = args[0];
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    //System.out.println(br.readLine());

    String line = br.readLine();
    while(line != null){

      //System.out.println(line);


      for(int i = 0; i < line.length();){
        switch(line.charAt(i)){
          case '(':
            tokens.add(new Token(Type.SEPARATOR, "("));
            i++;
            break;
          case ')':
            tokens.add(new Token(Type.SEPARATOR, ")"));
            i++;
            break;
          case '-':
            tokens.add(new Token(Type.OPERATOR, "-"));
            i++;
            break;
          case '+':
            tokens.add(new Token(Type.OPERATOR, "+"));
            i++;
            break;
          case '*':
            tokens.add(new Token(Type.OPERATOR, "*"));
            i++;
            break;
          case '/':
            tokens.add(new Token(Type.OPERATOR, "/"));
            i++;
            break;
          case '%':
            tokens.add(new Token(Type.OPERATOR, "%"));
            i++;
            break;
          case '^':
            tokens.add(new Token(Type.OPERATOR, "^"));
            i++;
            break;
          case '=':
            tokens.add(new Token(Type.OPERATOR, "="));
            i++;
            break;
          default:
            if(Character.isWhitespace(line.charAt(i))){
              i++;
            }else if(line.charAt(i) =='"'){
              // no way to have /" (escaped double quotes) inside a string atm
              /*int j = i;
              for(; j < line.length();){
                if(Character.isLetter(line.charAt(j))){
                  j++;
                  t = Type.IDENTIFIER;
                }else if(Character.isDigit(line.charAt(i))){
                  // NOT A WHILTe SPACE, LETTER, OR SYMBOL TOKEN TEST CASE
                  // GOES HERE WITHOUT INCREMENTING j
                  // 
                  // check if number literal, might be other edge cases
                  j++;
                  t = Type.KEYWORD;
                }else{
                  t = null;
                  break;
                }
              }
              String input = line.substring(i,j);
              wordHandler(input); 
              i = j+1;*/
            }else{
              //'word' handling here has potential flaws
              /* will only continue looking for next character if the current
                character is a letter/digit. (breaks if unrecognized symbol) The 
                type of the token is determined by the last character
              */
              Type t = null;
              int j = i;
              for(; j < line.length();){
                if(Character.isLetter(line.charAt(j))){
                  j++;
                  t = Type.IDENTIFIER;
                }else if(Character.isDigit(line.charAt(i))){
                  // check if number literal, might be other edge cases
                  j++;
                  t = Type.LITERAL;
                }else{
                  //t = null;
                  break;
                }
              }
              String input = line.substring(i,j);
              if(t == Type.IDENTIFIER){
                wordHandler(input); 
              }else if(t == Type.LITERAL){
                tokens.add(new Token(t, input));
              }else{// t == null, error

              }
              
              i = j;
            }
            break;
        }
      }
      line = br.readLine();
    }
    printTokens();
  }
  public static void printTokens(){
    for(Token t : tokens){
      System.out.println(t.toString());
      System.out.println("");
    }
  }
}