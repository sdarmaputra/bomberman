package BomberButtiClient;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 */
public class BomberMap{
    public BomberGame game; //Object of Bomber Game
    int mapWidth; 
    int mapHeight; 
    
    /**
     *
     */
    public boolean[][] strikeGrid; //Grid of the bomb strikes
    public Block[][] blockGrid; //Grid of cubes
    public Bonus[][] bonusGrid; //Grid bonuses
    
    ArrayList<Bomb> bombGrid = new ArrayList<>(); //"Grid" (ArrayList) of bombs
    
    /**
     * Default constructor
     */
    public BomberMap() {
        this.mapWidth = 29;
        this.mapHeight = 29;
        strikeGrid = new boolean[30][30];
        bonusGrid = new Bonus[30][30];
        initBlocks(); //initialize cubes
        initStrikes(); //Initialize Strike grid
        initBonuses(); //Bonus initialize Grid
    }
    
    /**
     * Constructor
     * @param game
     */
    public BomberMap(BomberGame game) {
        this();
        this.game = game;
    }
    
    /**
     * Here the blocks are initialized
     */
    private void initBlocks() {
        blockGrid =  new Block[30][30];
        
        //Grid with null values count
        for (int i=0;i<30;i++) {
            for(int j=0;j<30;j++) {
                blockGrid[i][j] = null;
            }
        }
        
        //upper row of filling with solid cubes
        for (int i = 0; i <= mapWidth-1; i++) {
            blockGrid[i][0] = new Block(this, i,0, false);
        }
       
        //bottom row of filling with solid cubes
        for (int i = 0; i <= mapWidth-1; i++) {
            blockGrid[i][mapHeight-1] = new Block(this, i,mapHeight-1, false);
        }
                
        //left-hand column filled with solid cubes
        for (int i = 1; i <= mapHeight-1; i++) {
            blockGrid[0][i] = new Block(this, 0,i, false);
        }
                
        //right column filled with solid cubes
        for (int i = 0; i <= mapHeight-2; i++) {
            blockGrid[mapWidth-1][i] = new Block(this, mapWidth-1,i, false);
        }
        
        
        //'between' blocks
        for (int i = 2; i <= mapWidth-3; i=i+2) { 
            for (int j = 2; j <= mapHeight-3; j=j+2) {
                blockGrid[i][j] = new Block(this, i,j, false);
            }
        }
        
        //Inflatable blocks that can contain bonuses
        int minVal = 0;
        int maxVal = 27;
        int randomVali;
        int randomValj;
        for (int i = 0; i <= mapWidth-1; i++) {
            for (int j = 0; j <= mapHeight-1; j++) {
                randomVali = minVal + (int)(Math.random() * ((maxVal - minVal) +1));
                randomValj = minVal + (int)(Math.random() * ((maxVal - minVal) +1));
                if (((!((randomVali<3) && (randomValj<3)))) && (!((randomVali>25) && (randomValj>25)))){ //Inflatable blocks that can contain bonuses
                    if (blockGrid[randomVali][randomValj] == null) {
                        blockGrid[randomVali][randomValj] = new Block(randomVali,randomValj,true);
                    }
                }
            }
        }
    }
    
    /**
     * initialize Strikes
     */
    private void initStrikes() {
        //Grid filling with null values
        for (int i = 0; i < 30; i++) { 
            for (int j = 0; j < 30; j++) {
                this.strikeGrid[i][j] = false;
            }
        }
    }
    
    /**
     * initialize bonuses
     */
    private void initBonuses() {
        //Grid filling with null values
        for (int i = 0; i < 30; i++) { 
            for (int j = 0; j < 30; j++) {
                this.bonusGrid[i][j] = null;
            }
        }
    }
    
    /**
     * This function is used to check if there is a strike at a specific coordinate
     * @param c: Coordinate of the location to verify
     * @return true if there is a strike at the specified location
     */
    public boolean isStrike(Coord c) {
        return strikeGrid[c.getX()][c.getY()];
    }
    
    /**
     * This function is used to check whether there is a block on a specific coordinate state
     * @param c: Coordinate of the location to verify
     * @return true if the specified location a block state
     */
    public boolean isBlock(Coord c) {
        if (blockGrid[c.getX()][c.getY()] != null) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * This function is used to check if a bomb is a coordinate
     * @param c: Coordinate of the location to verify
     * @return true if there is a bomb in the specified location
     */
    public boolean isBomb(Coord c) {
        Coord c2;
        int x1,x2,y1,y2,j;
        j = 0;
        x1 = c.getX(); 
        y1 = c.getY();
        for (Bomb i : bombGrid) { //Walk through all the bombs
            c2 = i.getCoords();
            x2 = c2.getX();
            y2 = c2.getY();
            if ((x1 == x2) && (y1 == y2)) {
                j++;
            }
        }
        if (j > 0) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * This function is used to check whether the specified location can be hit by strike
     * @param c: Coordinate of the location to verify
     * @return Coordinate of the location to verify
     */
    public boolean isStrikable(Coord c) {
        if (blockGrid[c.getX()][c.getY()] != null) {
            if (blockGrid[c.getX()][c.getY()].isDestructable()) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return true;
        }
    }
    
    /**
     * This function is used to check whether an obstacle to the specified location is where the player can not continue
     * @param c: Coordinate of the location to verify
     * @return true if the specified location is an obstacle
     */
    public boolean isObstacle(Coord c) {
        if ((!isBomb(c)) && (!isBlock(c))) {
            return false;
        }
        else {
            return true;
        }
    }
    
    /**
     * This function is called by Bomber Player to add a bomb on the map
     * @param b: Object of the bomb, created by the player
     */
    public void createBomb(Bomb b) {
        bombGrid.add(b);
    }
    
    /**
     * Function to check whether a player is a bonus, if yes -> assign this bonus to player
     * @param player: Object of the player
     */
    public void checkBonus(BomberPlayer player) {
        int x = player.getX();
        int y = player.getY();
        if (bonusGrid[x][y] != null) { //There is a bonus in this location -> to give player
            bonusGrid[x][y].rewardTo(player);
            bonusGrid[x][y] = null;
        }
    }
    
    /**
     * This function is invoked at each timer pulse BomberMan
     * This contains all the logic of the folder
     */
    public void act() {
        //Remove unexploded bombs from the grid
        for (Iterator i = bombGrid.listIterator(); i.hasNext();) {
            Bomb bomb = (Bomb) i.next(); 
                if (bomb.isExploded()) {
                i.remove();
            }
        }
        
        //Remove all strikes (strikes remain but one pulse)
        initStrikes();
        
        //Strike for making bombs exploded bombs and being hit by a strike detonate
        int bombsDetonatedByStrike = 0; //counter for the number of trees that explodes is because they have been hit by a strike
        do {
            for (Iterator i = bombGrid.listIterator(); i.hasNext();) { //Walk through the bomb Grid
                Bomb b = (Bomb) i.next(); //current bomb
                if (bombsDetonatedByStrike == 0) {
                    b.act();
                } //Bom 'acten' (puls geven naar bom om countdown-1,..
                bombsDetonatedByStrike = 0;
                Coord c = b.getCoords(); //Coordinate getting the bomb
                int cx = c.getX();  
                int cy = c.getY(); 
                
                if (strikeGrid[cx][cy]) {
                    b.explode();
                } //Bomb detonated
                
                if ((b.isExploded()) && (!b.getMadeStrike())) { //Bomb explodes and strike still not worked out? -> Create Strike
                    bombsDetonatedByStrike++;
                    int cstrike = b.getStrike(); //Get the length of strike
                    int cx_min = cx-cstrike;
                    int cx_max = cx+cstrike;
                    int cy_min = cy-cstrike;
                    int cy_max = cy+cstrike;
                    
                    strikeGrid[cx][cy] = true;
                    boolean strikeBlocked;
                    int k;
                    //From center upwards
                    strikeBlocked = false;
                    k = cy;
                    do {
                        k--;
                        if (isStrikable(new Coord(cx,k))) {
                            strikeGrid[cx][k] = true;
                            if (blockGrid[cx][k] != null) {
                                strikeBlocked = true;
                            }
                        }
                        else {
                            strikeBlocked = true;
                        }
                    } while ((k >= cy_min) && (!strikeBlocked));
                    
                    
                    
                    //From center towards below
                    strikeBlocked = false;
                    k = cy;
                    do {
                        k++;
                        if (isStrikable(new Coord(cx,k))) {
                            strikeGrid[cx][k] = true;
                            if (blockGrid[cx][k] != null) {
                                strikeBlocked = true;
                            }
                        }
                        else {
                            strikeBlocked = true;
                        }
                    } while ((k <= cy_max) && (!strikeBlocked));
                    
                    //From center to the left
                    strikeBlocked = false;
                    k = cx;
                    do {
                        k--;
                        if (isStrikable(new Coord(k,cy))) {
                            strikeGrid[k][cy] = true;
                            if (blockGrid[k][cy] != null) {
                                strikeBlocked = true;
                            }
                        }
                        else {
                            strikeBlocked = true;
                        }
                    } while ((k >= cx_min) && (!strikeBlocked));
                    
                    //From center to the right
                    strikeBlocked = false;
                    k = cx;
                    do {
                        k++;
                        if (isStrikable(new Coord(k,cy))) {
                            strikeGrid[k][cy] = true;
                            if (blockGrid[k][cy] != null) {
                                strikeBlocked = true;
                            }
                        }
                        else {
                            strikeBlocked = true;
                        }
                    } while ((k <= cx_max) && (!strikeBlocked));
                    b.striked(); //Bomb made a strike
                }
            }
        } while(bombsDetonatedByStrike > 0);
        
        
        /**
         * Damage caused by strikes (out bombs)
         */
        for (int i = 0; i < 30;  i++) {
            for (int j = 0; j < 30; j++) {
                if (strikeGrid[i][j]) {
                    //check bonuses are hit by strike
                    if (bonusGrid[i][j] != null) { //bonus hit by strike
                        bonusGrid[i][j] = null; //remove bonus
                    }
                    
                    //check blocks have been hit by a strike
                    if (blockGrid[i][j] != null) { //strike hits a block
                        if (blockGrid[i][j].isDestructable()) { //is normally always
                            blockGrid[i][j] = null; //remove block
                            bonusGrid[i][j] = new Bonus(this, i,j,true); //bonus element (random)
                        }
                    }
                }
            }
        }
    }
    
    public void draw(Graphics g) {
        /**
         * @todo: Ensure that a distinction can be made in strikes:
         *Strike at the center must get picture strike_mid
          * Horizontal strikes: strike_hor
          * Vertical strikes: strike_ver
         */
        
        /**
         * diced signs
         */
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) { 
                if (blockGrid[i][j] != null) {
                    blockGrid[i][j].draw(g);
                }
            }
        }
        
        /**
         * drawing bonuses
         */
        for (int i=0;i<30;i++) { 
            for (int j = 0; j < 30; j++) { 
                if (bonusGrid[i][j] != null) {
                    bonusGrid[i][j].draw(g);
                }
            }
        }
        
        
        /**
         * bombs signs
         */
        for (Bomb i : bombGrid) {
            i.draw(g);
        }
        
        /**
         * Strikes signs
         */
        for (int i=0;i<30;i++) {
            for (int j=0;j<30;j++) {
                if (strikeGrid[i][j]) {
                    g.drawImage(game.getImages().getStrikeMid(), i*10, j*10, null);
                }
            }
        }
    }
}
