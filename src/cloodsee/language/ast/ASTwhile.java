package cloodsee.language.ast;

import java.math.BigInteger;

public class ASTwhile extends ASTexpression {
  public ASTexpression cond;
  public ASTexpression body;
  
  public ASTwhile(int _line,ASTexpression _cond, ASTexpression branch) {
    line=_line;
    cond = _cond;
    body = branch;
  }
  
  public String toString() {
    return "while " + cond.toString()+"\ndo " + body.toString();
  }
  
  private boolean evalCondition(ASTexpression conditionToEval) {
    Object condition = conditionToEval.eval();
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
        System.err.println("Error : while condition is not boolean.");
        System.exit(1);
      }
      return evalCond != 0;
  }
  
  public Object eval() {
      Object condition = cond.eval();
      Object lastEval = null;
      while (evalCondition(cond)) {
        lastEval = body.eval();
      }
      return lastEval;
  }
}
