package chessEngine.Pieces;

import java.util.List;

import chessEngine.Alliance;
import chessEngine.board.Board;
import chessEngine.board.Move;

public abstract class Piece {
    
    protected final int PiecePosition;
    protected final Alliance pieceAlliance;

    Piece(final int PiecePosition, final Alliance pieceAlliance){
        this.PiecePosition = PiecePosition;
        this.pieceAlliance = pieceAlliance;
    }
    public int getPiecePosition(){
        return this.PiecePosition;
    }
    public Alliance getAlliance(){
        return this.pieceAlliance;
    }
    public abstract List<Move> calculatedLegalMoves(final Board board);
}
