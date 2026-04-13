package cloodsee.language.ast;

public class ASTstring extends ASTexpression {

  private final String str;

  public ASTstring (String _str) {
    str = _str.replace("\"","");
  }

  public String toString(){
    return "\""+str+"\"";
  }

  public Object eval(){
    return str;
  }

}
