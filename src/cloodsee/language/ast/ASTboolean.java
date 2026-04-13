package cloodsee.language.ast;

public class ASTboolean extends ASTexpression {

  private final String description;
  private final Boolean value;

  public ASTboolean (String description) {
    this.description=description;
    this.value = Boolean.parseBoolean(description);
  }

	public boolean getValue() {
    return value.booleanValue();
  }

  public String toString(){
    return this.value.toString();
  }

  public Object eval(){
    return value;
  }


}
