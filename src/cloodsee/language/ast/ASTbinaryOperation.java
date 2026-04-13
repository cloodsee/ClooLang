package cloodsee.language.ast;

import java.math.BigInteger;

public class ASTbinaryOperation extends ASTexpression {

  public ASTbinaryOperation (int l, String operator,
                             ASTexpression leftOperand,
                             ASTexpression rightOperand ) {
    line = l;
    this.operator = operator;
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
  }

  private final String operator;
  private final ASTexpression leftOperand;
  private final ASTexpression rightOperand;

	public String getOperator() {
    return operator;
  }

	public ASTexpression getLeftOperand() {
    return leftOperand;
  }

	public ASTexpression getRightOperand() {
    return rightOperand;
  }

  public String toString(){
    return leftOperand.toString() + operator + rightOperand.toString();
  }
  
  public Object affect(ASTvariable var, ASTexpression value) {
    
    return value;
  }

  public Object eval(){
    Object leftOp = leftOperand.eval();
    Object rightOp = rightOperand.eval();
    if (leftOp instanceof BigInteger && rightOp instanceof BigInteger) {
      BigInteger leftValue = (BigInteger) leftOp;
      BigInteger rightValue = (BigInteger) rightOp;
      switch (operator) {
        case "+": return leftValue.add(rightValue);
        case "-": return leftValue.subtract(rightValue);
        case "*": return leftValue.multiply(rightValue);
        case "/":
          if (rightValue.equals(BigInteger.ZERO)) {
            System.out.println("error : line " + line + ", division by zero");
            System.exit(-1);
          }
          return leftValue.divide(rightValue);
        case "%": return leftValue.mod(rightValue);
        case "==": return leftValue.equals(rightValue);
        case "<": return leftValue.compareTo(rightValue)<0;
        case ">": return leftValue.compareTo(rightValue)>0;
        case "<=": return leftValue.compareTo(rightValue)<=0;
        case ">=":return leftValue.compareTo(rightValue)>=0;
      }
    }
    if (leftOp instanceof Boolean && rightOp instanceof Boolean) {
      Boolean leftValue = (Boolean) leftOp;
      Boolean rightValue = (Boolean) rightOp;
      switch (operator) {
        case "&&": return leftValue.booleanValue() && rightValue.booleanValue();
        case "||": return leftValue.booleanValue() || rightValue.booleanValue();
		case "==": return leftValue.booleanValue() == rightValue.booleanValue();
      }
    }
    if (leftOp instanceof String || rightOp instanceof String) {
      if (!(leftOp instanceof String))
        leftOp = leftOp.toString();
      if (!(rightOp instanceof String))
        rightOp = rightOp.toString();
      String leftValue = (String) leftOp;
      String rightValue = (String) rightOp;
      switch (operator) {
        case "+": 
          return ""+leftValue + rightValue;
		case "==":
		  return leftValue.equals(rightValue);
      }
    }
    String type1 = "unknown";
    String type2 = "unknown";
    if (leftOp instanceof BigInteger) type1="Integer";
    else if (leftOp instanceof Boolean) type1="Boolean";
    else if (leftOp instanceof String) type1="String";
    if (rightOp instanceof BigInteger) type2="Integer";
    else if (rightOp instanceof Boolean) type2="Boolean";
    else if (rightOp instanceof String) type2="String";
    
    System.err.println("error : line , operator '" + operator + "' not matching for type '" + type1 + "' and type '" + type2 + "'");
    System.exit(-1);
    return null;
  }
}
