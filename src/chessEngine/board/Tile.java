package chessEngine.board;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableBiMap;

import chessEngine.Pieces.Piece;

public abstract class Tile{
    protected final int tileCoordinates;

    private static final Map<Integer, EmptyTile> EMPTY_TILES = createALLEmptyTiles();       //initalize and load all empty tiles so future cache accesses are hits, you also never have to make an empty tile again, so if a space is occupied, don't delete the empty tile, just keep it here
                                                                                            //if if I want the first tile on chess board, use EMPTY_TILE.get(1)
    private static Map<Integer, EmptyTile> createALLEmptyTiles() {
        
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for(int i =0; i<BoardUtils.NUM_TILES; i++){                   //64 empty tiles in chess board filled up
            emptyTileMap.put(i, new EmptyTile(i));
        }

        return ImmutableBiMap.copyOf(emptyTileMap);
    }

    public static Tile createTile(final int tileCoordinates, final Piece piece){                                      //only way to make tile is to use creatTile method, as it's the only public constructor avaliable to oustide classes
        return piece != null ? new OccupiedTile(tileCoordinates, piece) : EMPTY_TILES.get(tileCoordinates);           //if tileCoordinate is 100, make 100 to boot. 
    }

    private Tile(final int tileCoordinates){                  //initalize tiles
        this.tileCoordinates = tileCoordinates;
    }

    public abstract boolean isTileOccupied();   //abstract method to check for if tile is occupied

    public abstract Piece getPiece();           //gets piece of the tile

    public static final class EmptyTile extends Tile{
        EmptyTile(final int coordinate){
            super(coordinate);
        }
        @Override 
        public boolean isTileOccupied(){
            return false;
        }
        @Override
        public Piece getPiece(){
            return null;
        }
    }
 
    public static final class OccupiedTile extends Tile{
        private Piece TilePiece;

        OccupiedTile(int tileCoordinates, Piece TilePiece){
            super(tileCoordinates);
            this.TilePiece = TilePiece;
        }

        @Override
        public boolean isTileOccupied(){
            return true;
        }
        @Override
        public Piece getPiece(){
            return this.TilePiece;
        }
        
    }
}