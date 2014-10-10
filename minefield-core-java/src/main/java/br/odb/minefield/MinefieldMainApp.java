package br.odb.minefield;

import br.odb.gameapp.ConsoleApplication;
import br.odb.gameapp.UserCommandLineAction;
import br.odb.minefield.commands.ClearCommand;
import br.odb.minefield.commands.FlagCommand;
import br.odb.minefield.commands.NewGameCommand;
import br.odb.minefield.commands.PokeCommand;
import br.odb.minefield.commands.QuitCommand;
import br.odb.minefield.commands.StatusCommand;
import br.odb.minefield.model.Board;

public class MinefieldMainApp extends ConsoleApplication {

	public Board board;

    
    public MinefieldMainApp() {

    }

    public static void main(String[] args) {

        // This will probably never change.
        MinefieldMainApp doom = (MinefieldMainApp) new MinefieldMainApp()
                .setAppName("Minefield!")
                .setAuthorName("Daniel 'MontyOnTheRun' Monteiro")
                .setLicenseName("3-Clause BSD").setReleaseYear(2014);
        doom.createDefaultClient();
        doom.start();
    }

    @Override
    public void log(String tag, String message) {
        getClient().printVerbose(tag + ":" + message);

    }


    @Override
    public void onDataEntered(String entry) {

        if (entry == null || entry.length() == 0) {
            return;
        }

        super.onDataEntered(entry);

        try {

            runCmd(entry);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    protected void doQuit() {
        this.continueRunning = false;
    }

    @Override
    public ConsoleApplication init() {
        continueRunning = true;

        for (UserCommandLineAction cmd : new UserCommandLineAction[]{
            new QuitCommand(this), new NewGameCommand( this ), new ClearCommand(), new PokeCommand(), new FlagCommand(),
            new StatusCommand(this)}) {

            this.registerCommand(cmd);
        }

        return super.init();
    }

	public void updateVisuals(MinefieldMainApp game) {
		try {
			new StatusCommand( game).run(game, "");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
