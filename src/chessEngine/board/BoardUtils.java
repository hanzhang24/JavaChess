package chessEngine.board;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BoardUtils {

    public final static List<Boolean> FIRST_COLUMN = initColumn(0);
    public final static List<Boolean> SECOND_COLUMN = initColumn(1);
    public final static List<Boolean> SEVENTH_COLUMN = initColumn(2);
    public final static List<Boolean> EIGHTH_COLUMN = initColumn(3);
    
    public static final int NUM_TILES_PER_ROW = 8;
    public static final int NUM_TILES = 64;


    private static List<Boolean> initColumn(int columnNumber) {
        final Boolean[] column = new Boolean[NUM_TILES];
        for(int i = 0; i < column.length; i++) {
            column[i] = false;
        }
        do {
            column[columnNumber] = true;
            columnNumber += NUM_TILES_PER_ROW;
        } while(columnNumber < NUM_TILES);
        return Collections.unmodifiableList(Arrays.asList((column)));
    }
    private BoardUtils(){
        throw new RuntimeException("Cannot instantiate this class");
    }

    public static boolean isValidTileCoordinate(final int posssibleMove) {
        return posssibleMove >=0 && posssibleMove < NUM_TILES;
    }
    
}
