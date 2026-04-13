package cloodsee.language.ast;

import java.math.BigInteger;

public class ASTvariable extends ASTexpression {
  private ASTexpression value = null;
  private final String declarDesc;
  private final String name;
  public ASTvariable(String _name, ASTexpression v) {
    declarDesc = v.toString();
    name = _name;
    value = v;
  }
  
  public void setValue(ASTexpression v) {
    Object o = v.eval();
    if (o instanceof BigInteger) {
      value = new ASTinteger((BigInteger) o);
    }
    if (o instanceof String) {
      value = new ASTstring("\""+(String) o+"\"");
    }
  }
  
  public String toString() {
    return name;
  }
  
  public String getName() {
    return this.name;
  }
  
  public Object eval() {
    setValue(value);
    return value.eval();
  }
}
