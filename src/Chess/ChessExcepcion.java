package Chess;

import boardgame.BoardException;

public class ChessExcepcion extends BoardException  {
	private static final long serialVersionUID = 1L;
	
	public ChessExcepcion(String msg) {
		super(msg);
	}

}
