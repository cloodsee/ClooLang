package cloodsee.language;

import cloodsee.language.ast.*;
import cloodsee.language.frontend.*;

import java.io.File;

public class Main{

  private static boolean debug = false;
  public static void main(String[] args) {
    if (args.length < 1 || args.length > 2) {
      System.out.println("expecting a mdl file");
      System.exit(1);
    }else{
      if (args.length>1) {
          if (args[1].equals("/debug"))
            debug = true;
          else {
            System.err.println("Error : unexpected argument : " + args[1]);
            System.exit(-1);
          }
      }
      if (debug)
        System.out.println(args[0]);
      File f = new File (args[0]);
      TigParser parser = new TigParser();
      try {
        ASTprogram p = parser.getProg(f);
        if (debug) {
          System.out.println(p.toString());
          System.out.println("---");
        }
        Object o = p.eval();
        if (debug) {
          System.out.println("\n---");
          System.out.println("value : "+ o + "\n");
        }
      } catch (Exception e) {
        System.err.println(e);
        System.exit(-1);
      }
    }
  }
}
