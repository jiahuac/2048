import java.util.ArrayList;

/**
 * Grid class for 3D-2048 game
 * Basic data-structure of tile objects
 * @author Jiahua Chen
 * @version 0.02 04.02.2019
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
                    myGrid[row][col][stack] = new Tile(new Loc(row, col, stack));
                }
            }
        }
    }

    /**
     * gets tile at target location
     * @param target loc of target Tile
     * @return tile at location
     */
    public Tile getTile(Loc target)
    {
        try { return myGrid[target.row][target.col][target.stack]; }
        catch (ArrayIndexOutOfBoundsException e) { return null; }
    }

    /**
     * sets a coordinate in the grid with power pow
     * @param target loc of target Tile
     * @param pow to set target Tile
     * @return if set was successful, i.e. if it changed the power at all
     */
    public boolean setTile(Loc target, int pow)
    {
        try { return myGrid[target.row][target.col][target.stack].setPower(pow); }
        catch (ArrayIndexOutOfBoundsException e) { return false; }
    }

    /**
     * increments a tile at a given location
     * @param target loc of target Tile
     * @return new power of target tile
     */
    public int incrementTile(Loc target)
    {
        return myGrid[target.row][target.col][target.stack].incrementPower();
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
        return this.getEmpty().size() == 0;
    }

    /**
     * attempts to insert a random tile at an empty location of an unknown grid,
     * checks if grid if fully filled first
     * @return power of inserted new Tile, or 0 if grid is full
     */
    public int newTile()
    {
        ArrayList<Loc> emptyLocs = this.getEmpty();
        if (emptyLocs.size() == 0) { return 0; }
        Loc iniLoc = emptyLocs.get((int) (Math.random() * emptyLocs.size()));
        return myGrid[iniLoc.row][iniLoc.col][iniLoc.stack].initiate();
    }

    /**
     * finds all the empty tiles in the array
     * @return array of all empty tiles
     */
    public ArrayList<Loc> getEmpty()
    {
        ArrayList<Loc> emptyLocs = new ArrayList<Loc>();
        for (Tile[][] stack : myGrid)
        {
            for (Tile[] row : stack)
            {
                for (Tile box : row)
                {
                    if (box.isEmpty()) { emptyLocs.add(box.getLoc()); }
                }
            }
        }
        return emptyLocs;
    }

    /**
     * tries to shift tile from loc1 to loc2
     * if (r2, c2, s2) is an empty tile
     * @return
     */
    public boolean tryShift(Loc loc1, Loc loc2)
    {
        if (this.getTile(loc2).isEmpty())
        {
            myGrid[loc2.row][loc2.col][loc2.stack] = this.getTile(loc1);
            myGrid[loc1.row][loc1.col][loc1.stack] = new Tile();
            return true;
        }
        else { return false; }
    }
    
    public int tryCombine(Loc loc1, Loc loc2)
    {
        if (this.getTile(loc1).getPower() == this.getTile(loc2).getPower())
        {
            myGrid[loc1.row][loc1.col][loc1.stack] = new Tile();
            return myGrid[loc2.row][loc2.col][loc2.stack].incrementPower();
        }
        else { return 0; }
    }



    public static void main(String[] args)
    {
        // Constructs an empty gamegrid using the default constructor
        // ... so it's a 3x3x3 grid filled with tiles of power 0
        Grid gameGrid = new Grid();
        
        System.out.println("gameGrid toString: ");
        // Prints the gameGrid, testing toString. Expected - for all:
        System.out.println(gameGrid);
        
        System.out.print("Tile at 2,2,2 getTile: ");
        // Tries to get the tile at location 2,2,2
        System.out.println(gameGrid.getTile(new Loc(2,2,2)));
        
        // Sets the tile at 2,2,2 to power 3
        gameGrid.setTile(new Loc(2,2,2), 3);
        
        System.out.println("gameGrid toString: ");
        // Prints the gameGrid, testing toString. Expected - for all:
        // except a 2^3 = 8 at the lower r corner (3,3,3) position
        // (as arrays start at 0, so 2,2,2 really references the 3,3,3 pos)
        System.out.println(gameGrid);

        
        System.out.print("Tile at 2,2,2 (2nd time): ");
        // Tries again to get tile at loc 2,2,2; 
        // Should now return the tile's toString 2^3 = 8
        System.out.println(gameGrid.getTile(new Loc(2,2,2)));
        System.out.print("Tile at 2,2,2 power: ");
        // Now tries the power, which should return 3
        System.out.println(gameGrid.getTile(new Loc(2,2,2)).getPower());
        
        // The code intentionally catches ArrayIndexOutOfBoundsException
        // and throws a null reference instead. I might use this later on. 
        System.out.println(gameGrid.getTile(new Loc(4,4,4)));
    }
}

