package br.odb.minefield.commands;

import br.odb.gameapp.ConsoleApplication;
import br.odb.gameapp.UserCommandLineAction;
import br.odb.minefield.MinefieldGame;
import br.odb.utils.math.Vec2;

public class FlagCommand extends UserCommandLineAction {

	
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
		String[] cords = operands.split( "[ ]+" );
		MinefieldGame game = (MinefieldGame) app;
		Vec2 pos = new Vec2();
		pos.x = Integer.parseInt( cords[ 0 ] );
		pos.y = Integer.parseInt( cords[ 1 ] );
		game.board.flag( pos );
	}

	@Override
	public String toString() {
		return "flag";
	}
}
