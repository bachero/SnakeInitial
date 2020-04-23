
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
    
    public Food(Snake snake) { 
        // We pass Snake to the constructor because if the randomnly generated food
        // falls on the Snake you have to create another position for the food
        position = generateFood(snake);
    }
    
    public Node generateFood(Snake snake){
        position = new Node((int)(Math.random()*(Board.NUM_ROWS -1)), (int)(Math.random()*(Board.NUM_COLS -1)));
        List<Node> posSnake = snake.getList();
        boolean colision = false;
        boolean notColision = true;
        Node food = null;
        while(!colision){    
            for(Node a: posSnake){
                if(a == position){
                    notColision = false;
                    break;
                }
            }
            colision = true;
        }
        if(notColision){
            food = position;
        }
        return food;
    }
    
    public void paint(Graphics g, int squareWidth, int squareHeight) {
        // Finish this method. Call Util.drawSquare()
    }
    
    // Create all the methods you need here
}
