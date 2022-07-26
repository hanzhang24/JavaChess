package chessEngine.Pieces;

import java.util.Set;

import chessEngine.Alliance;
import chessEngine.board.Board;
import chessEngine.board.Move;

public class Knight extends Piece{
    Knight(int PiecePosition, Alliance pieceAlliance) {
        super(PiecePosition, pieceAlliance);
    }

    @Override
    public Set<Move> calculatedLegalMoves(Board board){
        return null;
    }
}
