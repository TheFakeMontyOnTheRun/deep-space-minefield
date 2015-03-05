package br.odb.minefield.model;

import java.util.Random;

import br.odb.utils.math.Vec2;

public class Board {
	
	final int size;
	PieceType[][] board;
	boolean[][] covered;
	boolean[][] flagged;
	private int remainingTilesToClear;
	private boolean hasTriggeredAMine;
	
	public int remainingMines() {
		return remainingTilesToClear;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder( "" );
		
		for ( int y = 0; y < size; ++y ) {
			
			for ( int x = 0; x < size; ++x ) {
				
				if ( flagged[ y ][ x ] ) {
					sb.append( 'F' );
				} else if ( covered[ y ][ x ] ) {
					sb.append( '#' );
				} else {
					sb.append( board[ y ][ x ] );
				}
			}
			sb.append( "\n" );
		}
		return sb.toString();
	}
	
	public void poke( Vec2 target ) {

		int x = (int) target.x;
		int y = (int) target.y;
		
		if ( x < 0 || x >= size || y < 0 || y >= size ) {
			return;
		}
		
		if ( flagged[ y ][ x ] )	{
			flagged[ y ][ x ] = false;
			return;
		}
		
		switch ( board[ y ][ x ] ) {
			case MINE:
				covered[ y ][ x ] = false;
				hasTriggeredAMine = true;
				return;

			case BLANK:					
					floodUncover( x, y );
					
					if ( covered[ y ][ x ] && !flagged[ y ][ x ] )
						this.remainingTilesToClear--;
					
					covered[ y ][ x ] = false;					
			default:
					
					if ( covered[ y ][ x ]  )
						this.remainingTilesToClear--;
					
					covered[ y ][ x ] = false;
		}
	}
	
	public void flag( Vec2 target ) {
		
		if ( !covered[ (int) target.y ][ (int) target.x ] ) {
			return;
		}
		
		flagged[ (int) target.y ][ (int) target.x ] = !flagged[ (int) target.y ][ (int) target.x ];
	}
	
	public Board( int size ) {
		this.size = size;

		covered = new boolean[ size ][];
		board = new PieceType[ size ][];
		flagged = new boolean[ size ][];
		for ( int c = 0; c < size; ++c ) {
			covered[ c ] = new boolean[ size ];
			board[ c ] = new PieceType[ size ];
			flagged[ c ] = new boolean[ size ];
			
			for ( int d = 0; d < size; ++d ) {
				board[ c ][ d ] = PieceType.BLANK;
				covered[ c ][ d ] = true;
			}
		}
		
		placeRandomMines( 2 * size );	
		placeNumbersOnBoard();
	}
	
	private void placeNumbersOnBoard() {
		
		PieceType[] values = PieceType.values();
		
		for ( int x = 1; x < size - 1; ++x ) {
			for ( int y = 1; y < size - 1; ++y ) {
				if ( board[ y ][ x ] == PieceType.MINE ) {
					
					if ( board[ y - 1 ][ x - 1 ] != PieceType.MINE)
						board[ y - 1 ][ x - 1 ] = values[ board[ y - 1 ][ x - 1 ].ordinal() + 1 ];
					
					if ( board[ y ][ x - 1 ] != PieceType.MINE)						
						board[ y ][ x - 1] = values[ board[ y ][ x - 1 ].ordinal() + 1 ];
					
					if ( board[ y + 1 ][ x - 1 ] != PieceType.MINE)
						board[ y + 1 ][ x - 1 ] = values[ board[ y + 1 ][ x - 1 ].ordinal() + 1 ];
					
					if ( board[ y - 1 ][ x ] != PieceType.MINE)					
						board[ y - 1][ x ] = values[ board[ y - 1 ][ x ].ordinal() + 1 ];
					
					if ( board[ y + 1 ][ x ] != PieceType.MINE)					
						board[ y + 1 ][ x ] = values[ board[ y + 1 ][ x ].ordinal() + 1 ];
					
					if ( board[ y - 1 ][ x + 1 ] != PieceType.MINE)					
						board[ y - 1 ][ x + 1 ] = values[ board[ y - 1 ][ x + 1 ].ordinal() + 1 ];
					
					if ( board[ y ][ x + 1 ] != PieceType.MINE)					
						board[ y ][ x + 1 ] = values[ board[ y ][ x + 1 ].ordinal() + 1 ];
					
					if ( board[ y + 1 ][ x + 1 ] != PieceType.MINE)					
						board[ y + 1 ][ x + 1 ] = values[ board[ y + 1 ][ x + 1 ].ordinal() + 1 ];				
				}
			}
		}		
	}
	
	private void floodUncover(int x, int y) {
		if ( covered[ y ][ x ] && !flagged[ y ][ x ] ) {
			
			if ( board[ y ][ x ] == PieceType.BLANK ) {
				
				if ( covered[ y ][ x ] )
					this.remainingTilesToClear--;
				
				covered[ y ][ x ] = false;
				
				if ( x > 0)
					floodUncover( x - 1, y );
				
				if ( ( x + 1 ) < size )
					floodUncover( x + 1, y );
				
				if ( y > 0 )
					floodUncover( x, y -1 );
				
				if ( ( y + 1 ) < size )
					floodUncover( x, y + 1);
				
				if ( x > 0 && y > 0 )
					floodUncover( x - 1, y - 1 );
				
				if ( ( x + 1 ) < size && ( y + 1 ) < size )
					floodUncover( x + 1, y + 1 );
				
				if ( y > 0 && ( x + 1 ) < size )
					floodUncover( x + 1, y -1 );
				
				if ( ( y + 1 ) < size && x > 0 )
					floodUncover( x - 1, y + 1);				
				
				
			} if ( board[ y ][ x ] != PieceType.MINE ) {
				
				if ( covered[ y ][ x ] )
					this.remainingTilesToClear--;
				
				covered[ y ][ x ] = false;
			}
		}		
	}	
	
	
	public void placeRandomMines( int n ) {
		
		int x;
		int y;
		Random random = new Random();
		remainingTilesToClear = n;
		
		while ( n > 0) {
			x = random.nextInt();
			y = random.nextInt();
			
			if ( x < 0 ) {
				x = -x;
			}
			
			if ( y < 0 ) {
				y = -y;
			}
			
			x = ( x % ( size - 2 ) ) + 1;
			y = ( y % ( size - 2 ) ) + 1;			
			
			if ( board[ y ][ x ] == PieceType.MINE ) {
				continue;
			}
			
			--n;
			board[ y ][ x ] = PieceType.MINE;
		}		
	}

	public boolean triggeredMine() {
		return hasTriggeredAMine;
	}
}
