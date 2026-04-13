package cloodsee.language.ast;
public abstract class ASTexpression{
  public int line;
  public abstract String toString();
  public abstract Object eval();

}
