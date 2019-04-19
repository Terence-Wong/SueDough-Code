

public class SymbolGenerator{
  public static void main(String[] args) {
    for(int i = 0; i < Symbols.KeyWords.length; i++){
      System.out.println("case \""+Symbols.KeyWords[i]+"\":");
      System.out.println("  tokens.add(new Token(Type.KEYWORD, \""+Symbols.KeyWords[i]+"\"));");
      //System.out.println("  i++;");
      System.out.println("  break;");
    }
    for(int i = 0; i < Symbols.Operators.length; i++){
      System.out.println("case \""+Symbols.Operators[i]+"\":");
      System.out.println("  tokens.add(new Token(Type.OPERATOR, \""+Symbols.Operators[i]+"\"));");
      //System.out.println("  i++;");
      System.out.println("  break;");
    }
    for(int i = 0; i < Symbols.Separators.length; i++){
      System.out.println("case \""+Symbols.Separators[i]+"\":");
      System.out.println("  tokens.add(new Token(Type.SEPARATOR, \""+Symbols.Separators[i]+"\"));");
      //System.out.println("  i++;");
      System.out.println("  break;");
    }
  }
}