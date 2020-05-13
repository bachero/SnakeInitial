
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author victoralonso
 */
public class Food {
    private Node position;
    private boolean isSpecial;
    
    public Food(Snake snake, boolean isSpecial) { 
        // We pass Snake to the constructor because if the randomnly generated food
        // falls on the Snake you have to create another position for the food
        position = generateFood(snake);
        this.isSpecial = isSpecial;
    }
    
    public Node generateFood(Snake snake){
        List<Node> posSnake = snake.getList();
        boolean colision = true;
        Node food = null;
        while(colision){
            position = new Node((int)(Math.random()*(AspectsConfig.NUM_ROWS -1)), (int)(Math.random()*(AspectsConfig.NUM_COLS -1)));
            colision = false;
            for(Node a: posSnake){
                if(a == position){
                    colision = true;
                    break;
                }
            }
        }
        return position;
    }
    
    public void paint(Graphics g, int squareWidth, int squareHeight) {
        // Finish this method. Call Util.drawSquare()
        if(isSpecial){
            Util.drawSquare(g, position.getRow(), position.getCol(), squareWidth, squareHeight, Color.RED);
        } else {
            Util.drawSquare(g, position.getRow(), position.getCol(), squareWidth, squareHeight, Color.GREEN);
        }
        
    }
    
    public void desapear() {    
        position.setCol(-1);
        position.setRow(-1);
    }
    
    public boolean isSpecial(){
        return isSpecial;
    }
    
    public Node getPosition(){
        return position;
    }
    
    public void setSpecial(boolean food) {
        this.isSpecial = food;
    }
            
    // Create all the methods you need here
}
