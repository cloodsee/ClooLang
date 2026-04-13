package cloodsee.language.ast;
public class ASTfactory {

  public ASTprogram newProgram(ASTexpression body) {
    return new ASTprogram(body);
  }

  public ASTunaryOperation newUnaryOperation(String operator,
                                             ASTexpression operand) {
    return new ASTunaryOperation(operator, operand);
  }

	public ASTbinaryOperation newBinaryOperation(int line, String operator,
                                               ASTexpression leftOperand, ASTexpression rightOperand) {
    return new ASTbinaryOperation(line, operator, leftOperand, rightOperand);
  }

	public ASTinteger newIntegerConstant(String value) {
    return new ASTinteger(value);
  }
  
  public ASTboolean newBooleanConstant(String value) {
    return new ASTboolean(value);
  }
  
  public ASTifThenElse newIfThenElse(ASTexpression cond, ASTexpression branch1, ASTexpression branch2) {
    return new ASTifThenElse(cond, branch1, branch2);
  }
  
  public ASTfunction newFunction(String s, ASTexpression[] p, int line) {
    return new ASTfunction(s,p, line);
  }
  
  public ASTsequence newSequence(ASTexpression[] body) {
    return new ASTsequence(body);
  }
  
  public ASTstring newStringConstant(String s) {
    return new ASTstring(s);
  }
  
  public ASTaffectation newAffectation(GlobalEnvironment env, String var, ASTexpression value) {
    return new ASTaffectation(env, var, value);
  }
  
  public ASTwhile newWhile(int l, ASTexpression cond, ASTexpression body) {
    return new ASTwhile(l, cond, body);
  }
}
