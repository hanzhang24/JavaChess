package chessEngine.Pieces;

import java.util.Set;

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
    public abstract Set<Move> calculatedLegalMoves(final Board board);
}
