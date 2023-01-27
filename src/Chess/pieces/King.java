package Chess.pieces;

import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.Color;
import boardgame.Board;
import boardgame.Position;

public class King extends ChessPiece {
	
	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	@Override
	public String toString() {
		return "K";
	}
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
		}
	
	
	private boolean testHookCastling(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
 	}

	@Override
	public boolean[][] possibleMoves() {
		boolean [][] mat = new boolean [getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		//above
		p.setValues(position.getRow() - 1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//below
				p.setValues(position.getRow() + 1, position.getColumn());
				if(getBoard().positionExists(p) && canMove(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
		//Left
			    p.setValues(position.getRow(), position.getColumn() - 1);
				if(getBoard().positionExists(p) && canMove(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}		
		//Right
			    p.setValues(position.getRow(), position.getColumn() + 1);
				if(getBoard().positionExists(p) && canMove(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}				
				
		//nw
			    p.setValues(position.getRow() - 1, position.getColumn() - 1);
				if(getBoard().positionExists(p) && canMove(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}		
				
		//ne
			    p.setValues(position.getRow() - 1, position.getColumn() + 1);
				if(getBoard().positionExists(p) && canMove(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}				
				
		//sw
			    p.setValues(position.getRow() + 1, position.getColumn() -1);
				if(getBoard().positionExists(p) && canMove(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}	
				
		//se
			    p.setValues(position.getRow() + 1, position.getColumn() + 1);
				if(getBoard().positionExists(p) && canMove(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}				
				
				
		// #specialmove castling	
				
		if(getMoveCount() == 0 && !chessMatch.getCheck()) {
			// # specialmove castling Kingside rook
			Position posT1 = new Position(position.getRow(), position.getColumn() + 3);
			if(testHookCastling(posT1)) {
				Position p1 =  new Position(position.getRow(), position.getColumn() + 1);
				Position p2 =  new Position(position.getRow(), position.getColumn() + 2);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}
		}	
		// #specialmove castling	
		
				if(getMoveCount() == 0 && !chessMatch.getCheck()) {
					// # specialmove castling queenside rook
					Position posT2 = new Position(position.getRow(), position.getColumn() - 4);
					if(testHookCastling(posT2)) {
						Position p1 =  new Position(position.getRow(), position.getColumn() - 1);
						Position p2 =  new Position(position.getRow(), position.getColumn() - 2);
						Position p3 =  new Position(position.getRow(), position.getColumn() - 3);
						if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
							mat[position.getRow()][position.getColumn() - 2] = true;
						}
					}
				}
				
		return mat;
	}

}
