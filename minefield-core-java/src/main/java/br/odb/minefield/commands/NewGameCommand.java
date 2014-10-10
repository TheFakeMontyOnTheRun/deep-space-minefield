package br.odb.minefield.commands;


import br.odb.gameapp.ConsoleApplication;
import br.odb.gameapp.UserCommandLineAction;
import br.odb.minefield.MinefieldMainApp;
import br.odb.minefield.model.Board;


public class NewGameCommand extends UserCommandLineAction {

	public NewGameCommand( MinefieldMainApp app) {
		super( );
	}

	@Override
	public String getHelp() {
		return null;
	}

	@Override
	public int requiredOperands() {
		return 1;
	}

	@Override
	public void run(ConsoleApplication app, String operand ) throws Exception {
		MinefieldMainApp game = (MinefieldMainApp) app;
		game.board = new Board( Integer.parseInt( operand ) );
	}

	@Override
	public String toString() {
		return "new-game";
	}

}
