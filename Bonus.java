package BomberButtiClient;
import java.awt.*;

/**
 * Bonus class describes the possible bonuses that can occur during the game
 */
public class Bonus {
    BomberMap map; 
    int x; 
    int y; 
    int type; 
    boolean rendeemed; // True if the bonus is used up
    
    /**
     * Default constructor
     */
    public Bonus() {
        x = 0;
        y = 0;
        type = 0;
        rendeemed = false;
    }
    
    /**
     * Constructor
     * @param map
     * @param x
     * @param y
     * @param randomType:If true, it is determined around a type, with false, type 0 uses
     */
    public Bonus(BomberMap map, int x, int y, boolean randomType) {
        this();
        this.x = x;
        this.y = y;
        this.map = map;
        if (randomType) {
            this.type = setRandomBonusType();
        }
    }
    
    
    /**
     * This function selects a random bonus type
     * @return: Random type integer
     */
    private int setRandomBonusType() {
        int minVal = 0; 
        int maxVal = 10; 
        int randomVal;
        randomVal = minVal + (int)(Math.random() * ((maxVal - minVal) +1)); //Determine random number between minimum and maximum
        return randomVal;
    }
    
    
    /**
     * @return x
     */
    public int getX() {
        return this.x;
    }
    /**
     * @return y
     */
    public int getY() {
        return this.y;
    }
    /**
     * @return type
     */
    public int getType() {
        return this.type;
    }
    /**
     * @return rendeemed
     */
    public boolean getRendeemed() {
        return this.rendeemed;
    }
    
    /**
     * Verification that the bonus is at a particular location
     * @param x
     * @param y
     * @return true if the bonus is located at coordinate (x, y)
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
     * @param type 
     */
    public void setType(int type) {
        this.type = type;
    }
    /**
     * @param rendeemed 
     */
    public void setRendeemed(boolean rendeemed) {
        this.rendeemed = rendeemed;
    }
    
    
    /**
     * This function is called when the bonus is 'used' / 'recognized' by a player
     * @param player: Object of class Bomber Player of the player using the bonus
     */
    public void rewardTo(BomberPlayer player) {
        switch(type) { //Switch on the type of bonus
            case 1: //extra bomb bonus
                player.incTotalBombs(1); //increase total bombs variable of the player with one
                break;
            case 2: //strike bonus
                player.incBombStrike(1); //increase bomb strike variable of the player with one
                break;
            case 3: //speed bonus
                player.incSpeed(1); //variable speed of the player is increased by 1
                break;
            default:
        }
        this.rendeemed = true; //bonus 'worthless' make so that it can not be recorded again
    }
    
    /**
     * Here, the bonus will be drawn
     * @param g: Contains the Graphics object to where there might be need signed
     */
    public void draw(Graphics g) {
        Image I;
        boolean noBonus = false;
        I = map.game.getImages().getBonusBomb();
        switch(type) {
            case 1:
                I = map.game.getImages().getBonusBomb();
                break;
            case 2:
                I = map.game.getImages().getBonusStrike();
                break;
            case 3:
                I = map.game.getImages().getBonusSpeed();
                break;
            default:
                noBonus = true;
        }
        
        if (!noBonus) {
            g.drawImage(I, x*20, y*20, null);
        }
    }
}
