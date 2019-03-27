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
     * increments a tile at a given location
     * @param row of target Tile
     * @param col of target Tile
     * @param stack of target Tile
     * @return new power of target tile
     */
    public int incrementTile(int row, int col, int stack)
    {
        return myGrid[row][col][stack].incrementPower();
    }

    /**
     * prints the Grid
     * @return String visual representation of grid
     */
    public String toString()
    {
        String out = "";
        for (Tile[][] stack : myGrid)
        {
            for (Tile[] row : stack)
            {
                for (Tile box : row)
                {
                    out += box + " ";
                }
                out += "\n";
            }
            out += "\n";
        }
        return out;
    }

    /**
     * checks if grid is fully filled
     * @return true if filled, else false
     */
    public boolean isFilled()
    {
        for (Tile[][] stack : myGrid)
        {
            for (Tile[] row : stack)
            {
                for (Tile box : row)
                {
                    if (box.isEmpty()) { return false; }
                }
            }
        }
        return true;
    }

    /**
     * attempts to insert a random tile at an empty location,
     * given the grid is not filled
     * @return value of inserted Tile
     */
    public int insertRandom()
    {
        int rRow = (int) (Math.random() * myGrid.length);
        int rCol = (int) (Math.random() * myGrid.length);
        int rStack = (int) (Math.random() * myGrid.length);
        if (myGrid[rRow][rCol][rStack].isEmpty())
        { return myGrid[rRow][rCol][rStack].initiate(); }
        else { return this.insertRandom(); }
    }

    /**
     * attempts to insert a random tile at an empty location of an unknown grid,
     * checks if grid if fully filled first
     * @return value of inserted new Tile, or 0 if grid is full
     */
    public int newTile()
    {
        if (this.isFilled()) { return 0; }
        else { return this.insertRandom(); }
    }

    public int tryShift()
    {
        return 0; /***/
    }

    public static void main(String[] args)
    {
        Grid gameGrid = new Grid();
        System.out.println(gameGrid);

        System.out.println(gameGrid.newTile());
        System.out.println(gameGrid.newTile());
        System.out.println(gameGrid.newTile());
        System.out.println(gameGrid.newTile());
        System.out.println(gameGrid.newTile());
        System.out.println(gameGrid.newTile());

        System.out.println(gameGrid);
    }
}
