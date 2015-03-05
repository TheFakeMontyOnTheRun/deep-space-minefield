package br.odb.minefield.model;

public enum PieceType {
	BLANK('.'),
	NEAR1('1'),
	NEAR2('2'),
	NEAR3('3'),
	NEAR4('4'),
	NEAR5('5'),
	NEAR6('6'),
	NEAR7('7'),
	NEAR8('8'),
	NEAR9('9'),
	MINE('*');
	
	private char representation;

	PieceType( char representation ) {
		this.representation = representation;
	}
	
	@Override
	public String toString() {
		return "" + representation;
	}
}
