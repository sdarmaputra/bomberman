package BomberButtiClient;

/**
 * The Coord class describes a coordinate of an x, y plane
 */
public class Coord {
    int x; //X-coördinaat
    int y; //Y-coördinaat
    
    /**
     * Default constructor
     */
    public Coord() {
        this.x = 0;
        this.y = 0;
    }
    
    /**
     * Constructor
     * @param x
     * @param y
     */
    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    
    
    /**
     * @return 
     */
    public int getX() {
        return this.x;
    }
    /**
     * @return 
     */
    public int getY() {
        return this.y;
    }
    
    /**
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }
    
    
    /**
     * Function to increase the X-coordinate by 1
     */
    public void incX() {
        this.x++;
    }
    /**
     *Function to increase the X-coordinate of a certain value
     * @param a: Value that specifies the  X-coordinate is incremented
     */
    public void incX(int a) {
        x = x+a;
    }
    /**
     * Mode is set to reduce the X-coordinate by 1
     */
    public void decX() {
        this.x--;
    }
    /**
     * Function in order to reduce the X-coordinate of a certain value
     * @param a: Value by which the X coordinate is reduced
     */
    public void decX(int a) {
        x = x-a;
    }
    /**
     * Function to increase the Y-coordinate by 1
     */
    public void incY() {
        this.y++;
    }
    /**
     * Function to increase the Y-coordinate of a certain value
     * @param a: Value that specifies the Y coordinate is incremented
     */
    public void incY(int a) {
        y = y+a;
    }
    /**
     * Function in order to reduce the Y-coordinate by 1
     */
    public void decY() {
        this.y--;
    }
    /**
     * Function in order to reduce the Y-coordinate of a certain value
     * @param a: Value that specifies the Y coordinate is decreased
     */
    public void decY(int a) {
        y = y-a;
    }
}
