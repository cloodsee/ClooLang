package cloodsee.language.ast;

import java.math.BigInteger;

public class ASTaffectation extends ASTexpression {
  private GlobalEnvironment env;
  private final String name;
  private ASTexpression aff;
  public ASTaffectation(GlobalEnvironment _env, String _name, ASTexpression v) {
    env = _env;
    name = _name;
    aff = v;
  }
  
  public String toString() {
    return name + "="+aff.toString();
  }
  
  public Object eval() {
    return env.setVar(name, aff).eval();
  }
}
