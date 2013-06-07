package interpreter;

import exceptions.RunException;
import parser.Expression;
import parser.InitializeStatement;
import parser.Int;
import parser.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: Bertie
 * Date: 07/06/13
 * Time: 14:23
 * To change this template use File | Settings | File Templates.
 */
public class Interpreter {
	private Stack<List<Variable>> variablesStack = new Stack<List<Variable>>();

	public Interpreter() {
		variablesStack.push(new ArrayList<Variable>());
	}

	public void runStatements(List<Statement> statements)
			throws RunException {
		for(Statement statement : statements) {
			try {
				runStatement(statement);
			} catch(RunException e) {
				throw new RunException(
					"Error while running " + statement + ": " + e.getMessage());
			}
		}
	}

	private void runStatement(Statement statement)
			throws RunException {
		if(statement instanceof InitializeStatement) {
			runInitializeStatement((InitializeStatement)statement);
		}
	}

	private void runInitializeStatement(InitializeStatement statement)
			throws RunException {
		String label = statement.getLabel().getWord();
		Variable variable;
		if(statement.getExpr() == null) {
			variable = new Variable(label);
		} else {
			Value value = evaluate(statement.getExpr());
			variable = new Variable(label, value);
		}
		variablesStack.peek().add(variable);
	}

	private Value evaluate(Expression expr)
			throws RunException {
		if(expr instanceof Int) {
			return new IntValue(((Int)expr).getValue());
		}
		throw new RunException("Couldn't evaluate expression");
	}
}
