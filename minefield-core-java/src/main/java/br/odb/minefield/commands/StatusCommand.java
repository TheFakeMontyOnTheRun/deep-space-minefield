package br.odb.minefield.commands;

import br.odb.gameapp.ConsoleApplication;
import br.odb.gameapp.UserMetaCommandLineAction;
import br.odb.minefield.MinefieldGame;

public class StatusCommand extends UserMetaCommandLineAction {

	public StatusCommand(ConsoleApplication app) {
		super(app);
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
		MinefieldGame game = (MinefieldGame) app;
		app.getClient().printNormal( game.board.toString() );
	}

	@Override
	public String toString() {
		return "status";
	}

}
