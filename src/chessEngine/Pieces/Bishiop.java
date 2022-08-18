package chessEngine.Pieces;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import chessEngine.Alliance;
import chessEngine.board.Board;
import chessEngine.board.BoardUtils;
import chessEngine.board.Move;
import chessEngine.board.Tile;

public class Bishiop extends Piece{

private final static int[] VECTOR_MOVE_COORDINATES = {-9, -7, 7, 9};

    Bishiop(int PiecePosition, Alliance pieceAlliance) {
        super(PiecePosition, pieceAlliance);
    }

    @Override
    public List<Move> calculatedLegalMoves(Board board) {
       
        final List<Move> LegalMoves = new ArrayList<>();

        for(final int candidateCoordinateOffset: VECTOR_MOVE_COORDINATES){      //loop throuhg each vector direction
            
            int NextPosition = this.PiecePosition;

            while(BoardUtils.isValidTileCoordinate(NextPosition)) {          //if this position is valid, move on

                if(isFirstColumnExclusion(NextPosition, candidateCoordinateOffset) || isEighthColumnExclusion(NextPosition, candidateCoordinateOffset)){
                    break;
                }

                NextPosition += candidateCoordinateOffset;                   //add the vector offset

                if(BoardUtils.isValidTileCoordinate(NextPosition)){          //if it's a valid Coordinate

                    if(BoardUtils.isValidTileCoordinate(NextPosition)){ //this takes care of anything above first row and below eight row
                        final Tile destination = board.getTile(NextPosition);       //get the tile at this position from the board
                        if(!destination.isTileOccupied()){                          //if the tile is not occupied run a freemove
                            LegalMoves.add(new Move.FreeMove(board, this, NextPosition));
                        }
                        else{                                                       //otherwise run a occupied move
                            final Piece TargetPiece = destination.getPiece();
                            final Alliance TargetAlliance = TargetPiece.getAlliance();
                            if(this.pieceAlliance != TargetAlliance){
                                LegalMoves.add(new Move.AttackMove(board, this, NextPosition, TargetPiece));
                            }
                        }
                    }

                    break;
                }
            }
        }
        return ImmutableList.copyOf(LegalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int possibleMove) {          //if in first column, cannot go left diagonals
        return BoardUtils.FIRST_COLUMN.get(currentPosition) && possibleMove == -9 || possibleMove == 7;
    }
    
    private static boolean isEighthColumnExclusion(final int currentPosition, final int possibleMove) {         //if in eight column, cannot go right diagonals
        return BoardUtils.EIGHTH_COLUMN.get(currentPosition) && possibleMove == -7 || possibleMove == 9;
    }    
}
