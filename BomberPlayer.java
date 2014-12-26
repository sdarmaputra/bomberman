package BomberButtiClient;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
*The class Bomber Player includes all data from the player
* Which player has its own object of this class
*/
public class BomberPlayer {
    BomberMap map; 
    BomberGame game; 
    int id; //Each player has a unique ID
    int x; 
    int y; 
    boolean isDead; //True if player is dead
    int speed; //Indication of speed at which the character can move on the playing field
    int totalBombs; //Total number of trees that the player can simultaneously
    int bombStrike; //Scope of the bows of the bombs that explains this player
    int usedBombs; //Number of bombs that the player has used
    int direction; //Direction in which the player moves on the field (to be performed at this.act ()
    boolean dropBomb; //True if player wants to lay a bomb (performed at this.act ())
    ArrayList<Bomb> bombs = new ArrayList<>(); //tracking the bombs laid by this player
    String name; //The username of this player
    int[] keys;
    private static final int DIR_UP = 1;
    private static final int DIR_DOWN = 2;
    private static final int DIR_LEFT = 3;
    private static final int DIR_RIGHT = 4;
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;
    private static final int BOMB = 4;
    
    /**
    * Default constructor
    */
    public BomberPlayer() {
        keys = new int[5];
        id = 0;
        x = 0;
        y = 0;
        isDead = false;
        speed = 1;
        totalBombs = 1;
        bombStrike = 0;
        usedBombs = 0;
        direction = 0;
        dropBomb = false;
        name = "Unknown player";
    }
    
    
    /**
    * Constructor
    * @param game
    * @param map
    * @param id
    * @param x
    * @param y
    */
    public BomberPlayer(BomberGame game, BomberMap map, int id, int x, int y, String name) {
        this();
        this.game = game;
        this.map = map;
        this.id = id;
        this.x = x;
        this.y = y;
        this.name = name;
    }
    
    
    /**
     * Control Keys pick for this player from the keyconfig class
     */
    public void loadKeys() {
        KeyConfig kC = new KeyConfig();
        keys[UP] = kC.keys[id-1][UP];
        keys[DOWN] = kC.keys[id-1][DOWN];
        keys[LEFT] = kC.keys[id-1][LEFT];
        keys[RIGHT] = kC.keys[id-1][RIGHT];
        keys[BOMB] = kC.keys[id-1][BOMB];
    }
    
    
    /**
   
     * @return 
     */
    public BomberMap getMap() {
        return this.map;
    }
    /**
    
     * @return 
     */
    public BomberGame getGame() {
        return this.game;
    }
    /**
    
     * @return 
     */
    public int getId() {
        return this.id;
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
   
     * @return 
     */
    public Coord getCoords() {
        return new Coord(this.x,this.y);
    }
    /**
   
     * @return 
     */
    public boolean getIsDead() {
        return this.isDead;
    }
    /**
  
     * @return 
     */
    public int getSpeed() {
        return this.speed;
    }
    /**
 
     * @return 
     */
    public int getTotalBombs() {
        return this.totalBombs;
    }
    /**

     * @return 
     */
    public int getBombStrike() {
        return this.bombStrike;
    }
    /**
  
     * @return 
     */
    public int getUsedBombs() {
        return this.usedBombs;
    }
    /**
     * Get function for the ArrayList of the bombs laid by this player
     * @return 
     */
    public ArrayList<Bomb> getBombs() {
        return this.bombs;
    }
    /**
    
     * @return 
     */
    public String getName() {
        return this.name;
    }
    
    /**
    * Set functions; So that external classes can set the variables of these classes
    */
    public void setMap(BomberMap map) {
        this.map = map;
    }
    public void setGame(BomberGame game) {
        this.game = game;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setTotalBombs(int totalBombs) {
        this.totalBombs = totalBombs;
    }
    public void setBombStrike(int bombStrike) {
        this.bombStrike = bombStrike;
    }
    public void setUsedBombs(int usedBombs) {
        this.usedBombs = usedBombs;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
    public void setDropBomb(boolean dropBomb) {
        this.dropBomb = dropBomb;
    }
    public void setBombs(ArrayList<Bomb> bombs) {
        this.bombs = bombs;
    }
    
    /**
    * Semi-set functions
    */
    public void incSpeed(int a) {
        speed = speed+a;
    }
    public void decSpeed(int a) {
        speed = speed-a;
    }
    public void incTotalBombs(int a) {
        totalBombs = totalBombs+a;
    }
    public void decTotalBombs(int a) {
        totalBombs = totalBombs-a;
    }
    public void incBombStrike(int a) {
        bombStrike = bombStrike+a;
    }
    public void decBombStrike(int a) {
        bombStrike = bombStrike-a;
    }
    
    
    /**
    * Is performed by pressing a button
    * @param evt: Contains information key pressed
    */
    public void keyPressed(KeyEvent evt) {
        
        if (evt.getKeyCode() == keys[UP]) {
            this.direction = DIR_UP;
        }
        else if (evt.getKeyCode() == keys[DOWN]) {
            this.direction = DIR_DOWN;
        }
        else if (evt.getKeyCode() == keys[LEFT]) {
            this.direction = DIR_LEFT;
        }
        else if (evt.getKeyCode() == keys[RIGHT]) {
            this.direction = DIR_RIGHT;
        }
        else if (evt.getKeyCode() == keys[BOMB]) {
            dropBomb = true;
        }
        
    }
    
    /**
    * Is performed at the release of a key
    * @param evt: Contains information about the release button
    */
    public void keyReleased(KeyEvent evt) {
        if ((evt.getKeyCode() == keys[UP]) && (direction == DIR_UP)) {
            direction = 0;
        }
        if ((evt.getKeyCode() == keys[DOWN]) && (direction == DIR_DOWN)) {
            direction = 0;
        }
        if ((evt.getKeyCode() == keys[LEFT]) && (direction == DIR_LEFT)) {
            direction = 0;
        }
        if ((evt.getKeyCode() == keys[RIGHT]) && (direction == DIR_RIGHT)) {
            direction = 0;
        }
    }
    
    
    
    /**
    * Players will be 'killed'
    */
    public void kill() {
        this.isDead = true;
    }
    
    /**
    * Is performed at each "pulse timer", from here everything is performed every
    */
    public void act() {
        //Loop all the bombs laid by the player to remove the unexploded bombs from the list
        for (Iterator i = bombs.listIterator(); i.hasNext();) {
            Bomb b = (Bomb) i.next(); //Current 'bomb' in the course
            if (b.isExploded()) {
                i.remove();
            } 
        }
        
        
    
        Coord c = new Coord(x,y); 
        if (map.isStrike(c)) { 
            this.kill(); 
            game.endGame(); 
        }
        else {
          
            map.checkBonus(this);
            
            
            
            if (dropBomb) { 
                if (bombs.size() < totalBombs) { 
                    Bomb b = new Bomb(this,x,y); 
                    bombs.add(b); 
                    map.createBomb(b); 
                    usedBombs++; 
                }
            }
            dropBomb = false;
            
            //Control or player wants to move
            switch(direction) {
                case DIR_UP: //Players will move upwards
                    c.decY(); //Current coordinate one box up
                    if (!map.isObstacle(c)) {
                        this.y--;
                    } //n-> Move Up
                    break;
                case DIR_DOWN: //Player wants to move downwardly
                    c.incY(); //Current coordinate 1 box down
                    if (!map.isObstacle(c)) {
                        this.y++;
                    } //n-> Move Down
                    break;
                case DIR_LEFT: //Players will move to the left
                    c.decX(); //Current coordinate one box to the left
                    if (!map.isObstacle(c)) {
                        this.x--;
                    } //n-> move left
                    break;
                case DIR_RIGHT: //Players will move to the right
                    c.incX(); //Current coordinate one box to the right
                    if (!map.isObstacle(c)) {
                        this.x++;
                    } //n->naar rechts verlaatsen
                    break;
                default: //Player does not want to move -> nothing
            }
        }
    }
    
    /**
    *Here the player is drawn to the program
    * @param g: Contains the Graphics object to where it needs to be drawn
    */
    public void draw(Graphics g) {
        if (id == 1) {
            g.drawImage(game.getImages().getPlayer(), x*10, y*10, null);
        }
        else {
            g.drawImage(game.getImages().getPlayer2(), x*10, y*10, null);
        }
    }
}
