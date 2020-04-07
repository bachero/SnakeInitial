
import java.awt.Graphics;
import java.util.ArrayList;
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
public class Snake {
    
    private Direction direction;
    private List<Node> body;
    private int remainingNodesToCreate = 0;
    
    
    public Snake(int row, int col, int size) { // Initial position of the head of the snake and number of inital nodes
        // Finish this method
        body = new ArrayList<>(); 
        Node node = new Node(row, col - size);
        body.add(node);
    }
    
    public boolean canMove(int row, int col) {
        // Finish this method
        return true;
    }
    
    public void paint(Graphics g, int squareWidth, int squareHeight) {
        // Finish this method. Call Util.drawSquare()
    }
    
    public void move() {
        // Finish this method
        int row = body.get(0).getRow();
        int col = body.get(0).getCol();
        switch (direction) {
            case UP:
                if (direction == Direction.DOWN) {
                    body.add(0, new Node(row - 1, col));
                }
                break;
            case DOWN:
                if (direction != Direction.UP) {
                
                }
                break;
            case LEFT:
                if (direction != Direction.RIGHT) {
            
                }
                break;
            case RIGHT:
                if (direction != Direction.LEFT) {
               
                }
                break;
        }


        
    }
    
    
    
}
