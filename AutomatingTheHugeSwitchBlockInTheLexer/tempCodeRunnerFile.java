
public class SingleSymbolGenerator{
  public static void main(String[] args) {
    for(int i = 0; i < SingleSymbols.Separators.length; i++){
      System.out.println("case ''"+SingleSymbols.Separators[i]+"':");
      System.out.println("  tokens.add(new Token(Type.SEPARATOR, \""+SingleSymbols.Separators[i]+"\"));");
      System.out.println("  i++;");
      System.out.println("  break;");
    }
    for(int i = 0; i < SingleSymbols.Operators.length; i++){
      System.out.println("case ''"+SingleSymbols.Operators[i]+"':");
      System.out.println("  tokens.add(new Token(Type.OPERATOR, \""+SingleSymbols.Operators[i]+"\"));");
      System.out.println("  i++;");
      System.out.println("  break;");
    }
  }
}
