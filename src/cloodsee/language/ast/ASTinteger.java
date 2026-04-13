package cloodsee.language.ast;
import java.math.BigInteger;

public class ASTinteger extends ASTexpression {

  private final String description;
  private final BigInteger value;

  public ASTinteger (String description) {
    this.description=description;
    this.value=new BigInteger(description);
  }
  public ASTinteger (BigInteger i) {
    this.description=i.toString();
    this.value=i;
  }
  

	public BigInteger getValue() {
    return value;
  }

  public String toString(){
    return value.toString();
  }

  public Object eval(){
    return value;
  }


}
