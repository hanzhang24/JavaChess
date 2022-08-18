package chessEngine.board;

import chessEngine.Pieces.Piece;

public class Move {
    
    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate; 

    Move(final Board board, final Piece movedPiece, final int destinationCoordinate){   //initalizing a move, the constructor
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public static final class FreeMove extends Move {
        public FreeMove(Board board, Piece movedPiece, int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }

    public static final class AttackMove extends Move {
        final Piece target;
        public AttackMove(Board board, Piece movedPiece, int destinationCoordinate, final Piece target) {
            super(board, movedPiece, destinationCoordinate);
            this.target = target;
        }
    }
}
