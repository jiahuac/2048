/**
 * Grid class for 3D-2048 game
 * Basic data-structure of tile objects
 * @author Jiahua Chen
 * @version 0.01 03.26.2019
 */
public class Grid
{
    /** given maximum size of the game grid (dimensions) */
    private static final int MAX_SIZE = 3;

    /** myGrid object, consisting of 3d Tile object array */
    private Tile[][][] myGrid;

    /** default constructor constructs a grid of MAX_SIZE */
    public Grid()
    {
        this(MAX_SIZE);
    }

    /** constructs a grid of dimensions/side length size, with all empty tiles */
    public Grid(int size)
    {
        myGrid = new Tile[size][size][size];
        for (int row = 0; row < myGrid.length; row++)
        {
            for (int col = 0; col < myGrid[0].length; col++)
            {
                for (int stack = 0; stack < myGrid[0][0].length; stack++)
                {
                    myGrid[row][col][stack] = new Tile();
                }
            }
        }
    }

    /**
     * sets a coordinate in the grid with power pow
     * @param row of target Tile
     * @param col of target Tile
     * @param stack of target Tile
     * @param pow to set target Tile
     * @return if set was successful, i.e. if it changed the power at all
     */
    public boolean setTile(int row, int col, int stack, int pow)
    {
        try { return myGrid[row][col][stack].setPower(pow); }
        catch (ArrayIndexOutOfBoundsException e) { return false; }
    }

    /**
     * gets tile at target location
     * @param row of target Tile
     * @param col of target Tile
     * @param stack of target Tile
     * @return tile at location
     */
    public Tile getTile(int row, int col, int stack)
    {
        try { return myGrid[row][col][stack]; }
        catch (ArrayIndexOutOfBoundsException e) { return null; }
    }
}
