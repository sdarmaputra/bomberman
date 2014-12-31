package BomberButtiClient;
import java.awt.*;

/**
 * The Block class describes the blocks on the screen, both fixed and inflatable cubes
 */
public class Block {
    BomberMap map; //Map object
    int x; //X-koordinaat
    int y; //Y-koordinat
    private boolean destructable; //True if the block can be inflated
    
    /**
     * Default constructor
     */
    public Block() {
        map = null;
        x = 0;
        y = 0;
        destructable = false;
    }
    
    /**
    * Constructor
     * @param map
     * @param x
     * @param y
     * @param destructable
     */
    public Block(BomberMap map, int x, int y, boolean destructable) {
        this();
        this.x = x;
        this.y = y;
        this.destructable = destructable;
        this.map = map;
    }
    
    /**
     * Get function for the X location of the marker on the map *return x
     * @return x
     */
    public int getX() {
        return this.x;
    }
    /**
     * Get function for the Y location of the marker on the map *return y
     * @return y
     */
    public int getY() {
        return this.y;
    }
    /**
     * Get function for *return boolean destructible destructible
     * @return 
     */
    public boolean getDestructable() {
        return this.destructable;
    }
    /**
     * Return true if the block can be inflated
     * @return 
     */
    public boolean isDestructable() {
        return this.getDestructable();
    }
    
    
    /**
     * Set key systems for the X location of the marker on the map
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Set key systems for the Y location of the marker on the map
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * Set key systems for the destructable boolean
     * @param destructable 
     */
    public void setDestructable(boolean destructable) {
        this.destructable = destructable;
    }
    /**
     * General set key systems which all variables can be reset
     * @param x
     * @param y
     * @param destructable 
     */
    public void setBlock(int x, int y, boolean destructable) {
        this.x = x;
        this.y = y;
        this.destructable = destructable;
    }
    
    
    /**
     * Check whether the block is located in this location
     * @param x: X-coördinaat
     * @param y: Y-coördinaat
     * @return: True if the block is located at the coordinate of the specified x, y parameters standing
     */
    public boolean isAt(int x, int y) {
        if ((this.x == x) && (this.y == y)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Here the block is drawn
     * @param g: Contains the Graphics object to where it needs to be drawn
     */
    public void draw(Graphics g) {
        if (this.destructable) {
            g.drawImage(map.game.getImages().getBlockDyn(), x*20, y*20, null);
        }
        else {
            g.drawImage(map.game.getImages().getBlockStat(), x*20, y*20, null);
        }
    }
}
