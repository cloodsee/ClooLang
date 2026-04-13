package cloodsee.language.ast;
import java.util.HashMap;
import java.math.BigInteger;

public class GlobalEnvironment {
  private HashMap<String,ASTvariable> variables = new HashMap<String,ASTvariable>();
  public GlobalEnvironment() {
    
  }
  
  public ASTexpression addVar(String s, ASTexpression e) {
    if (variables.containsKey(s)) {
      System.err.println("error : line " + e.line +", variable " + s + " already declared.");
      System.exit(-1);
    }
    ASTvariable v = new ASTvariable(s, e);
    variables.put(s,v);
    return getVar(s);
  }
  
  public ASTexpression setVar(String s, ASTexpression e) {
    ASTvariable v = getVar(s);
    v.setValue(e);
    return getVar(s);
  }
  
  public ASTvariable getVar(String s) {
    if (!variables.containsKey(s)) {
      System.err.println("error : var '" + s + "' has not been declared.");
      System.exit(-1);
    }
    return variables.get(s);
  }
}
