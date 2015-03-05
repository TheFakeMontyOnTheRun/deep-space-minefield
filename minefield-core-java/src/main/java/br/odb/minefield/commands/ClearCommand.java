package br.odb.minefield.commands;

import br.odb.gameapp.ConsoleApplication;
import br.odb.gameapp.UserCommandLineAction;

public class ClearCommand extends UserCommandLineAction {

	
	@Override
	public String getHelp() {
		return null;
	}

	@Override
	public int requiredOperands() {
		return 2;
	}

	@Override
	public void run(ConsoleApplication app, String operands) throws Exception {
	}

	@Override
	public String toString() {
		return "clear";
	}

}
