/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shared;
import java.awt.*;
/**
 *
 * @author kromatin
 */
public class Drawer {
    private Block block;
    private Bomb bomb;
    private BomberPlayer player;
    private Bonus bonus;
    private BomberMap map;
    private BomberGame game;
    private Graphics g;

     /**
     * Here the block is drawn
     * @param g: Contains the Graphics object to where it needs to be drawn
     */
        public void drawBlock(Graphics g, Block block) {
            //Block block2 = block;
            this.block = block;
            this.g = g;
            if (block.getDestructable()) {
                g.drawImage(getMap().game.getImages().getBlockDyn(), block.x*20, block.y*20, null);
            }
            else {
                g.drawImage(getMap().game.getImages().getBlockStat(), block.x*20, block.y*20, null);
            }
        }
        
    void drawBomb(Graphics g, Bomb bomb) {
        this.bomb = bomb;
        this.g = g;
        this.player = bomb.getPlayer();
        g.drawImage(game.getImages().getBomb(), bomb.x*20, bomb.y*20, null);
    }
    
    public void drawBonus(Graphics g, Bonus bonus) {
        this.g = g;
        this.bonus = bonus;
        Image I;
        boolean noBonus = false;
        I = map.game.getImages().getBonusBomb();
        switch(bonus.type) {
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
            g.drawImage(I, bonus.x*20, bonus.y*20, null);
        }
    }
    
    public void drawPlayer(Graphics g, BomberPlayer player) {
        this.player = player;
        this.g = g;
        if (player.id == 1) {
            g.drawImage(game.getImages().getPlayer(), player.x*20, player.y*20, null);
        }
        else {
            g.drawImage(game.getImages().getPlayer2(), player.x*20, player.y*20, null);
        }
    }
    /**
     * @return the block
     */
    public Block getBlock() {
        return block;
    }

    /**
     * @param block the block to set
     */
    public void setBlock(Block block) {
        this.block = block;
    }

    /**
     * @return the bomb
     */
    public Bomb getBomb() {
        return bomb;
    }

    /**
     * @param bomb the bomb to set
     */
    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
    }

    /**
     * @return the player
     */
    public BomberPlayer getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(BomberPlayer player) {
        this.player = player;
    }

    /**
     * @return the bonus
     */
    public Bonus getBonus() {
        return bonus;
    }

    /**
     * @param bonus the bonus to set
     */
    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    /**
     * @return the map
     */
    public BomberMap getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(BomberMap map) {
        this.map = map;
    }

    /**
     * @return the game
     */
    public BomberGame getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(BomberGame game) {
        this.game = game;
    }
    
    
}
