package cloodsee.language.frontend;
import cloodsee.language.ast.*;
import cloodsee.language.grammar.*;

import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * Cette classe décrit à ANTRL comment construire un AST.
 * C'est un Listener : à chaque pas de l'analyse syntaxique, ANTRL
 * appelle cette classe, qui crée un ASTNode correspondant à ce qui
 * a été reconnu.
 * Un Listener ne peut pas renvoyer de valeur, nous utilisons donc des
 * champs du contexte ctx pour transferer l'information d'une règle
 * de grammaire à l'autre.
 *
 */
public class TigListener implements TiggrammarListener {

	/*
	 * La classe est paramétrée par une fabrique qui indique comment
	 * créer les instances concrètes d'AST.
	 */
	protected ASTfactory factory;
  protected GlobalEnvironment env = new GlobalEnvironment();
	public TigListener(ASTfactory factory) {
		super();
		this.factory = factory;
	}


	/*
	 * ANTLRGrammarBaseListener, automatiquement généré, fournit
	 * un squelette d'objet "Listener".
	 * Il suffit de redéinir les méthodes qui nous intéressent : celles de la
	 * forme "exit<règle>".
	 */

	@Override
	public void exitProg(TiggrammarParser.ProgContext ctx) {
		ctx.node = factory.newProgram(ctx.body.node);
	}


	@Override
	public void	exitConstInteger(
			TiggrammarParser.ConstIntegerContext ctx) {
		ctx.node = factory.newIntegerConstant(ctx.intConst.getText());
	}

	@Override
	public void exitUnary(TiggrammarParser.UnaryContext ctx) {
		ctx.node = factory.newUnaryOperation(ctx.op.getText(), ctx.arg.node);
	}

	@Override
	public void exitBinary(TiggrammarParser.BinaryContext ctx) {
    ctx.node = factory.newBinaryOperation(ctx.getStart().getLine(),ctx.op.getText(), ctx.arg1.node, ctx.arg2.node);
	}
	
	
	@Override	public void exitConstBool(TiggrammarParser.ConstBoolContext ctx) {
	  ctx.node = factory.newBooleanConstant(ctx.boolConst.getText());
	}

	@Override	public void enterIfThenElse(TiggrammarParser.IfThenElseContext ctx) {}
	@Override	public void exitIfThenElse(TiggrammarParser.IfThenElseContext ctx) {
	  ctx.node = factory.newIfThenElse(ctx.cond.node, ctx.branch1.node, ctx.branch2.node);
	}
	
	@Override	public void enterFunction(TiggrammarParser.FunctionContext ctx) {}
	@Override	public void exitFunction(TiggrammarParser.FunctionContext ctx) {
	  ctx.node = factory.newFunction(ctx.func.getText(),toExpressions(ctx.args), ctx.getStart().getLine());
	}
	
	@Override	public void enterSequence(TiggrammarParser.SequenceContext ctx) {}
	@Override	public void exitSequence(TiggrammarParser.SequenceContext ctx) {
	  ctx.node = factory.newSequence(toExpressions(ctx.body));
	}
	
	@Override	public void enterConstString(TiggrammarParser.ConstStringContext ctx) {}
	@Override	public void exitConstString(TiggrammarParser.ConstStringContext ctx) {
	  ctx.node = factory.newStringConstant(ctx.stringConst.getText());
	}
	
	@Override	public void enterDeclaration(TiggrammarParser.DeclarationContext ctx) {}
	@Override	public void exitDeclaration(TiggrammarParser.DeclarationContext ctx) {
	  ctx.node = env.addVar(ctx.name.getText(), ctx.value.node);
	}
	
	@Override	public void enterVariable(TiggrammarParser.VariableContext ctx) {}
	@Override	public void exitVariable(TiggrammarParser.VariableContext ctx) {
	  ctx.node = env.getVar(ctx.name.getText());
	}
	
	@Override	public void enterAffectation(TiggrammarParser.AffectationContext ctx) {}
	@Override	public void exitAffectation(TiggrammarParser.AffectationContext ctx) {
	  ctx.node = new ASTaffectation(env, ctx.arg1.getText(), ctx.arg2.node);
	}
	
	@Override	public void enterWhile(TiggrammarParser.WhileContext ctx) {}
	@Override	public void exitWhile(TiggrammarParser.WhileContext ctx) {
	  ctx.node = factory.newWhile(ctx.getStart().getLine(), ctx.cond.node, ctx.branch.node);
	}

	@Override	public void enterEveryRule(ParserRuleContext arg0) {}
	@Override	public void exitEveryRule(ParserRuleContext arg0) {}
	@Override	public void visitErrorNode(ErrorNode arg0) {}
	@Override	public void visitTerminal(TerminalNode arg0) {}
	@Override	public void enterProg(TiggrammarParser.ProgContext ctx) {}
	@Override	public void enterConstInteger(TiggrammarParser.ConstIntegerContext ctx) {}
	@Override	public void enterBinary(TiggrammarParser.BinaryContext ctx) {}
	@Override	public void enterUnary(TiggrammarParser.UnaryContext ctx) {}
	@Override	public void enterConstBool(TiggrammarParser.ConstBoolContext ctx) {}
	
	public ASTexpression[] toExpressions(List<TiggrammarParser.ExprContext> ctxs) {
	  if (ctxs == null) return new ASTexpression[0];
	  ASTexpression[] r = new ASTexpression[ctxs.size()];
	  int pos = 0;
	  for (TiggrammarParser.ExprContext e : ctxs) {
	    r[pos++] = e.node;
	  }
	  return r;
	}
}
