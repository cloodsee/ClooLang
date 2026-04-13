package cloodsee.language.ast;

public class ASTsequence extends ASTexpression {

  private final ASTexpression[] body;

  public ASTsequence (ASTexpression[] _body) {
    if (_body.length == 0) {
      System.err.println("Expected at least one expression in Sequence");
      System.exit(-1);
    }
    body = _body;
  }

  public String toString(){
    String s = "(\n";
    for (ASTexpression e : body) {
      s += "  " + e.toString() + "\n";
    }
    s+=")";
    return s;
  }

  public Object eval(){
    int i;
    for (i=0; i<body.length-1;++i) {
      body[i].eval();
    }
    return body[i].eval();
  }


}
