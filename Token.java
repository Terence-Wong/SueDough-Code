public class Token{
  public final Lexer.Type t;   // type of token 
  public final String c; // content
  /*
  add line number and column for error messages
  */
  public Token(Lexer.Type type, String content){
    t = type;
    c = content;
  }
  public String toString() {
    return "TYPE: " + t.toString() + "\nCONTENT: " + c;
  }
}