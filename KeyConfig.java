package BomberButtiClient;
import java.awt.event.KeyEvent;
/**
 * The keyconfig classe represents the control buttons for the various characters in
 */
public class KeyConfig {
    public int keys[][];
    int UP = 0;
    int DOWN = 1;
    int LEFT = 2;
    int RIGHT = 3;
    int BOMB = 4;
    
    /**
     * Default constructor
     */
    public KeyConfig() {
        initKeys();
    }
    
    /**
     * Function where the array keys will be set
     */
    private void initKeys() {
        keys = new int[2][5];
        
        /**
         * Player 1
         */
        keys[0][UP] = KeyEvent.VK_W; 
        keys[0][DOWN] = KeyEvent.VK_S; 
        keys[0][LEFT] = KeyEvent.VK_A; 
        keys[0][RIGHT] = KeyEvent.VK_D; 
        keys[0][BOMB] = KeyEvent.VK_X; 
        
        
        /**
         * Player 2
         */
        keys[1][UP] = KeyEvent.VK_I; 
        keys[1][DOWN] = KeyEvent.VK_K; 
        keys[1][LEFT] = KeyEvent.VK_J; 
        keys[1][RIGHT] = KeyEvent.VK_L; 
        keys[1][BOMB] = KeyEvent.VK_M; 
    }
    
    
    public int[] getKeys(int id) {
        if (--id == 0) { 
            return keys[0];
        }
        else {
            return keys[1];
        }
    }
}
