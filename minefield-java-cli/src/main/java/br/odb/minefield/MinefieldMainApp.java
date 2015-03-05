package br.odb.minefield;

import br.odb.minefield.model.Board;

public class MinefieldMainApp {

	public Board board;

    public static void main(String[] args) {

        // This will probably never change.
        MinefieldGame doom = (MinefieldGame) new MinefieldGame()
                .setAppName("Minefield!")
                .setAuthorName("Daniel 'MontyOnTheRun' Monteiro")
                .setLicenseName("3-Clause BSD").setReleaseYear(2014);
        doom.createDefaultClient();
        doom.start();
    }
}
