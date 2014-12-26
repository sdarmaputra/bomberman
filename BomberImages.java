package BomberButtiClient;
import java.awt.*;
import javax.swing.*;
/**
 * The class Bomber Images ensures that all necessary images for the game properly and fully loaded before the start of the game
 */
public class BomberImages extends JPanel {
    Image block_dyn, block_stat, strike_mid, strike_hor, strike_ver, player, player2, bomb, bonus_bomb, bonus_strike, bonus_speed;
    
    MediaTracker tracker; //Tracker to track the information about the loading of the images
    
    
    /**
     * Default constructor
     */
    public BomberImages() {
        block_dyn = Toolkit.getDefaultToolkit().getImage(getClass().getResource("dynblock.gif"));
        block_stat = Toolkit.getDefaultToolkit().getImage(getClass().getResource("staticblock.gif"));
        strike_mid = Toolkit.getDefaultToolkit().getImage(getClass().getResource("strike_mid.gif"));
        strike_hor = Toolkit.getDefaultToolkit().getImage(getClass().getResource("strike_horizontal.gif"));
        strike_ver = Toolkit.getDefaultToolkit().getImage(getClass().getResource("strike_vertical.gif"));
        player = Toolkit.getDefaultToolkit().getImage(getClass().getResource("char.gif"));
        player2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("char2.gif"));
        bomb = Toolkit.getDefaultToolkit().getImage(getClass().getResource("bomb.gif"));
        bonus_bomb = Toolkit.getDefaultToolkit().getImage(getClass().getResource("bonus_bomb.gif"));
        bonus_strike = Toolkit.getDefaultToolkit().getImage(getClass().getResource("bonus_expandstrike.gif"));
        bonus_speed = Toolkit.getDefaultToolkit().getImage(getClass().getResource("bonus_speed.gif"));
        loadImages(); //Afbeeldingen laden
    }
    
    /**
     * Feature that keeps the images after export of this function are actually loaded
     */
    private void loadImages() {
        tracker = new MediaTracker(this);
        int i = 1;
        
        /**
         * Add all images to the tracker with a unique ID
         */
        tracker.addImage(block_dyn,i);
        tracker.addImage(block_stat,++i);
        tracker.addImage(strike_mid,++i);
        tracker.addImage(strike_hor,++i);
        tracker.addImage(strike_ver,++i);
        tracker.addImage(player,++i);
        tracker.addImage(player2,++i);
        tracker.addImage(bomb,++i);
        tracker.addImage(bonus_bomb,++i);
        tracker.addImage(bonus_strike,++i);
        tracker.addImage(bonus_speed,++i);
        try {
            tracker.waitForAll(); //Wait until all images are loaded
        }
        catch(InterruptedException e) {
            
        }
    }
    
    /**
     * Get function for the image of inflatable cubes
     * @return 
     */
    public Image getBlockDyn() {
        return block_dyn;
    }
    /**
     * Get function for the image of solid cubes
     * @return 
     */
    public Image getBlockStat() {
        return block_stat;
    }
    /**
     * Get function for the image of strikes in the center
     * @return 
     */
    public Image getStrikeMid() {
        return strike_mid;
    }
    /**
     * Get function for the image of horizontal stripes
     * @return 
     */
    public Image getStrikeHor() {
        return strike_hor;
    }
    /**
     * Get function for the image of vertical strikes
     * @return 
     */
    public Image getStrikeVer() {
        return strike_ver;
    }
    /**
     * Get function for the image of player 1
     * @return 
     */
    public Image getPlayer() {
        return player;
    }
    /**
     * Get function for the image of player 2
     * @return 
     */
    public Image getPlayer2() {
        return player2;
    }
    /**
     * Get function for the image of the bombs
     * @return 
     */
    public Image getBomb() {
        return bomb;
    }
    /**
     * Get function for the image of the bomb bonus
     * @return 
     */
    public Image getBonusBomb() {
        return bonus_bomb;
    }
    /**
     * Get function for the image of the strike bonus
     * @return 
     */
    public Image getBonusStrike() {
        return bonus_strike;
    }
    /**
     * Get function for the image of the speed bonus
     * @return 
     */
    public Image getBonusSpeed() {
        return bonus_speed;
    }
}
