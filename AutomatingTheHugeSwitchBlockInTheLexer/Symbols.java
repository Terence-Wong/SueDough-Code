
public class Symbols{
  //for whole word symbols
  public static String[] Separators = {
    "(",
    ")"
  };
  public static String[] Operators = {
    "==",
    ">=",
    "<=",
    "!=",
    "-", 
    "+", 
    "*", 
    "/",
    "%", 
    "^", 
    "=",
    ">",
    "<",
  };
  public static String[] KeyWords = {
    "PRINT",
    "IF",
    "THEN",
    "ELSE",
    "ENDIF",
    "WHILE",
    "ENDWHILE",
    "REPEAT",
    "UNTIL",
    "CASE",
    "OF",
    "OTHERS",
    "ENDCASE",
    "FOR",
    "ENDFOR",
    "BEGIN",
    "END",
    "EXCEPTION",
    "WHEN"
  };
}
/*
case '(':
            tokens.add(new Token(Type.SEPARATOR, '('));
            i++;
            break;
*/