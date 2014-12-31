package BomberButtiClient;
import java.awt.*;

/**
 * Bomb class Describes the bombs That can be made by players

 */
public class Bomb {
    int x; //X-bomb
    int y; //Y-bomb
    int countdown; //Countdown; when it is set to 0 to bomb explodes
    boolean exploded; //Is true if the bomb explodes
    boolean madestrike; //Is true if the bomb strike made (by explosion)
    BomberPlayer player; //Object of Bomber Player of the player who placed the bomb
    
    /**
     * Default constructor
     */
    public Bomb() {
        x = 0;
        y = 0;
        countdown = 30;
        exploded = false;
        madestrike = false;
        player = null;
    }
    
    /**
     * Constructor
     * @param player: Object of the player who placed the bomb
     * @param x: X- bom
     * @param y: Y-bom
     */
    public Bomb(BomberPlayer player, int x, int y) {
        this();
        this.x = x;
        this.y = y;
        this.player = player;
    }
    
    /**
     * Get function for the X coordinate of the bomb in the court
     * @return x
     */
    public int getX() {
        return this.x;
    }
    /**
     * Get function for the Y coordinate of the bomb in the court
     * @return y
     */
    public int getY() {
        return this.y;
    }
    /**
     * Get function for the countdown of the bomb
     * @return countdown
     */
    public int getCountdown() {
        return this.countdown;
    }
    /**
     * Get function for the exploded boolean
     * @return exploded
     */
    public boolean getExploded() {
        return this.exploded;
    }
    /**
     * Get function for maggot strike boolean
     * @return madestrike
     */
    public boolean getMadeStrike() {
        return this.madestrike;
    }
    /**
     * Get function for the object of the player who placed the bomb
     * @return player
     */
    public BomberPlayer getPlayer() {
        return this.player;
    }
    /**
     * Get function for the coordinates of the bomb in the court
     * @return 
     */
    public Coord getCoords() {
        return new Coord(x,y);
    }
    /**
     * This function returns true if the bomb explodes
     * @return 
     */
    public boolean isExploded() {
        return this.getExploded();
    }
    /**
     * This function Returned the length of the strike that is going to make this bomb explosion
     * @return 
     */
    public int getStrike() {
        return player.getBombStrike();
    }
    
    
    /**
     * Set function for the X coordinate
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Set function for the Y coordinate
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * Set function for countdown
     * @param countdown 
     */
    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }
    /**
     * Set function for the exploded boolean
     * @param exploded 
     */
    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }
    /**
     * Set function for the maggot strike boolean
     * @param madestrike 
     */
    public void setMadestrike(boolean madestrike) {
        this.madestrike = madestrike;
    }
    /**
     * Function set for the object of the player who placed the bomb
     * @param player 
     */
    public void setPlayer(BomberPlayer player) {
        this.player = player;
    }
    
    /**
     * Function to detonate the bomb with
     */
    public void explode() {
        this.setExploded(true);
    }
    /**
     * Function to turn the strike made boolean to true
     */
    public void striked() {
        this.setMadestrike(true);
    }
    
    /**
     * This happens all the 'logic' of the tree 
     * Invoked every 'timer pulse'
     */
    public void act() {
        countdown--; 
        if (countdown < 1) { //Counter is 0 -> Bomb explodes
            exploded = true; //Bomb has exploded
        }
    }
}
