package cloodsee.language.ast;

import java.math.BigInteger;

public class ASTfunction extends ASTexpression {
  private String function;
  private ASTexpression[] params;
  private final int line;
  public ASTfunction(String s, ASTexpression[] p, int l) {
    function = s;
    params = p;
    line = l;
  }
  
  public String toString() {
    String s = function + "(";
    if (params.length == 0)
      return s+")";
    s+=params[0].toString();
    for (int i = 1 ; i< params.length ; ++i) {
      s += "," + params[i].toString();
    }
    return s+")";
  }
  
  public Object eval() {
    switch (function) {
    case "print" :
        if (params.length == 0) {
          System.err.println("error : line " + line + " no parameters given for function print.");
          System.exit(-1);
        }
        String s = "";
        for (int i= 0 ; i< params.length ; ++i) {
          s += params[i].eval();
        }
        System.out.print(s);
        return s;
    case "println" :
        s = "\n";
        if (params == null) return s;
        for (int i= 0 ; i< params.length ; ++i) {
          s += params[i].eval();
        }
        System.out.print(s);
        return s;
	case "exit" :
		return funcExit();
    default:
        System.err.println("error : line " + line + ", function '" + function + "' does not exist.");
        System.exit(-1);
        return "'" + function + "' not defined";
    }
  }
  
  private Object funcExit() {
		switch (params.length) {
			case 0:
				System.exit(0);
			case 1:
			Object value = params[0].eval();
				if (!(value instanceof BigInteger)) {
					System.err.println("error : line " + line + " exit() functions only accepts ints.");
					System.exit(-1);
				} else {
					BigInteger returnCode = (BigInteger) value;
					System.exit(returnCode.intValue());
				}
		}
		System.err.println("error : line " + line + " too many parameters given for function exit.");
		return "Invalid parameters";
	}
}
