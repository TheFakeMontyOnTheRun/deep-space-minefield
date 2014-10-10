package br.odb.minefield.model;

import java.util.ArrayList;
import java.util.Random;

import br.odb.utils.math.Vec2;

public class Board {
	
	Mine[] mines;
	final ArrayList<Flag> flags = new ArrayList<Flag>();
	final int size;
	
	public void poke( Vec2 target ) {

		int x = (int) target.x;
		int y = (int) target.y;
		
		if ( x < 0 || x >= size || y < 0 || y >= size ) {
			
			return;
		}
		
//		if ( flagged[ x][ y ] )	{
//			
//			flagged[ x ][ y ] = false;
//			return;
//		}
//		
//		
//		switch ( map[ x ][ y ] ) {
//			case POSITION_MINE:
//				
//				 map[ x ][ y ] = GameSession.POSITION_MINE_POKED;
//				gameState = GameSession.GAMESTATE_GAMEOVER;
//				return;
//
//			case POSITION_BLANK:					
//					floodUncover( x, y );
//					
//					if ( covered[ x ][ y ] && !flagged[ x ][ y ] )
//						this.remainingTilesToClear--;
//					
//					covered[ x ][ y ] = false;					
//			default:
//					
//					if ( covered[ x ][ y ]  )
//						this.remainingTilesToClear--;
//					
//					covered[ x ][ y ] = false;
//		}
	}
	
	public void flag( Vec2 target ) {
		Flag flag = new Flag();
		flag.position.set( target );;
		
		for ( Flag f : flags ) {
			if ( f.position.equals( flag ) ) {
				return;
			}
		}
		flags.add( flag );
	}
	
	public Board( int size ) {
		this.size = size;
		flags.clear();
		placeRandomMines( size );
	}	
	
	public void placeRandomMines( int n ) {
		
		int x;
		int y;
		Random random = new Random();
		mines = new Mine[ n ];
		
		while ( n > 0) {
			--n;			
			mines[ n ].position.x = random.nextInt( n - 2 ) + 1;
			mines[ n ].position.y = random.nextInt( n - 2 ) + 1;
		}		
	}
}
