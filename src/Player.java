
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Player implements Comparable <Player>, Serializable {
    
    private String namePlayer;
    private int score;
    
    
    public Player(String p1, int i){
        namePlayer = p1;
        score = i;
    }
    
    public Player(String p1){
        namePlayer = p1;
    }
    
    public String getPlayer(){
        return namePlayer;
        
    }
    
    public void setPlayer(String newPlayer){
        namePlayer = newPlayer;
    }
    
    
    public int getScore(){
        return score;
    }
    
    @Override
    public String toString(){
        return "Name: " + namePlayer + "  Score: " + score;
    }

    @Override
    public int compareTo(Player arg0) {
        if(score < arg0.score){
            return 1;
        } else if(score > arg0.score){
            return -1;
        } else {
            return 0;
        }
    }

}
