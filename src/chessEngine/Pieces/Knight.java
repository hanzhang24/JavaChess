package chessEngine.Pieces;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import chessEngine.Alliance;
import chessEngine.board.*;

public class Knight extends Piece{
    private final static int[] MOVE_OFFSETS = {-17, -15, -10, -6, 6, 10 ,15, 17};
    
    Knight(int PiecePosition, Alliance pieceAlliance) {
        super(PiecePosition, pieceAlliance);
    }

    @Override
    public List<Move> calculatedLegalMoves(final Board board){
        int PotentialMove;
        List<Move> LegalMoves = new ArrayList<>();
        for(final int possibleMove: MOVE_OFFSETS){
            PotentialMove = possibleMove + this.PiecePosition;
            if(isFirstColumnExclusion(this.PiecePosition, possibleMove) || isSecondColumnExclusion(this.PiecePosition, possibleMove) ||isSeventhColumnExclusion(this.PiecePosition, possibleMove) ||isEighthColumnExclusion(this.PiecePosition, possibleMove)) {
             continue;
            }
            if(BoardUtils.isValidTileCoordinate(possibleMove)){ //this takes care of anything above first row and below eight row
                final Tile destination = board.getTile(PotentialMove);
                if(!destination.isTileOccupied()){
                    LegalMoves.add(new Move.FreeMove(board, this, PotentialMove));
                }
                else{
                    final Piece TargetPiece = destination.getPiece();
                    final Alliance TargetAlliance = TargetPiece.getAlliance();
                    if(this.pieceAlliance != TargetAlliance){
                        LegalMoves.add(new Move.AttackMove(board, this, PotentialMove, TargetPiece));
                    }
                }
            }
        }

        return ImmutableList.copyOf(LegalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int possibleMove) {
        return BoardUtils.FIRST_COLUMN.get(currentPosition) && ((possibleMove == -17) ||
                (possibleMove == -10) || (possibleMove == 6) || (possibleMove == 15));
    }

    private static boolean isSecondColumnExclusion(final int currentPosition, final int possibleMove) {
        return BoardUtils.SECOND_COLUMN.get(currentPosition) && ((possibleMove == -10) || (possibleMove == 6));
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition,  final int possibleMove) {
        return BoardUtils.SEVENTH_COLUMN.get(currentPosition) && ((possibleMove == -6) || (possibleMove == 10));
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int possibleMove) {
        return BoardUtils.EIGHTH_COLUMN.get(currentPosition) && ((possibleMove == -15) || (possibleMove == -6) ||
                (possibleMove == 10) || (possibleMove == 17));
    }    
}
