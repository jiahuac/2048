/**
 * Location class for 3D-2048 game
 * Basic data-structure of locations/coordinates
 * @author Jiahua Chen
 * @version 0.01 03.26.2019
 */

public class Loc {
    public int row;
    public int col;
    public int stack;

    public Loc()
    {
        this(0,0,0);
    }

    public Loc(int row, int col, int stack)
    {
        this.row = row;
        this.col = col;
        this.stack = stack;
    }
}
