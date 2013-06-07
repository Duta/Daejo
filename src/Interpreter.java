import statements.Statement;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Bertie
 * Date: 07/06/13
 * Time: 14:23
 * To change this template use File | Settings | File Templates.
 */
public class Interpreter {
	public Interpreter() {

	}

	public void runStatements(List<Statement> statements) {
		for(Statement statement : statements) {
			runStatement(statement);
		}
	}

	public void runStatement(Statement statement) {
		// TODO
	}
}
