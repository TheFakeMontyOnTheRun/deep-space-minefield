package br.odb.minefield.model;

public class Mine extends GameElement {
	private boolean wentOff = true;
	
	public void blow() {
		wentOff = true;
	}
	
	public boolean isBlown() {
		return wentOff;
	}	
}
