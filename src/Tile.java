/**
 * Tile class for 3D-2048 game
 * Individual Tile objects for the game grid
 * @author Jiahua Chen
 * @version 0.01 03.26.2019
 */

public class Tile
{
    /** power of the individual tiles, -1 if empty **/
    private int power;

    /** constructs an empty tile */
    public Tile()
    {
        this(-1);
    }

    /** constructs a tile with power pow */
    public Tile(int pow)
    {
        this.power = pow;
    }

    /**
     * attempts to set the power to pow
     * @return true if successful; false if not (or if power already equals power)
     */
    public boolean setPower(int pow)
    {
        if (this.power == pow) { return false; }
        else
        {
            this.power = pow;
            return true;
        }
    }

    /**
     * attempts to get the power of the tile
     * @return power of the tile
     */
    public int getPower()
    {
        return this.power;
    }

    /**
     * toString method of the Tile
     * @return integer value of tile (2^power), or " " if empty
     */
    public String toString()
    {
        if ( this.isEmpty() ) { return " "; }
        else { return String.valueOf((int) Math.pow(2, this.power)); }
    }

    /**
     * checks if tile is empty
     * @return true if tile is empty, else false
     */
    public boolean isEmpty()
    {
        return this.power == -1;
    }

    public static void main(String[] args)
    {
        Tile tile1 = new Tile();
        System.out.println(tile1);
        System.out.println(tile1.isEmpty());
        tile1.setPower(3);
        System.out.println(tile1);
        System.out.println(tile1.getPower());
    }
}