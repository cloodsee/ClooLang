package cloodsee.language.ast;

import java.math.BigInteger;

public class ASTifThenElse extends ASTexpression {
  public ASTexpression cond;
  public ASTexpression bodyIf;
  public ASTexpression bodyElse;
  
  public ASTifThenElse(ASTexpression _cond, ASTexpression branch1, ASTexpression branch2) {
    cond = _cond;
    bodyIf = branch1;
    bodyElse = branch2;
  }
  
  public String toString() {
    return "if " + cond.toString()+"\nthen " + bodyIf.toString()+"\nelse " + bodyElse.toString();
  }
  
  public Object eval() {
      Object condition = cond.eval();
      int evalCond = 0;
      if (condition instanceof Boolean) {
          Boolean c = (Boolean) condition;
          evalCond = c.booleanValue() ? 1:0;
      }
      else if (condition instanceof BigInteger) {
          BigInteger c = (BigInteger) condition;
          evalCond = c.intValue();
      }
      else {
        System.err.println("Error : if condition is not boolean.");
        System.exit(1);
      }
      if (evalCond != 0) return bodyIf.eval();
      else return bodyElse.eval();
  }
}
