package br.odb.minefield.commands;

import br.odb.gameapp.ConsoleApplication;
import br.odb.gameapp.UserMetaCommandLineAction;


public class QuitCommand extends UserMetaCommandLineAction {

	public QuitCommand( ConsoleApplication app) {
		super( app );
	}

	@Override
	public String getHelp() {
		return null;
	}

	@Override
	public int requiredOperands() {
		return 0;
	}

	@Override
	public void run(ConsoleApplication app, String arg1) throws Exception {
	
	}

	@Override
	public String toString() {
		return "quit";
	}
}
