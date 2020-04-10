
import java.awt.Color;
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
        for(int i=0; i < size; i++){
            Node node = new Node(row, col - i);
            body.add(node);
        }
       
    }
    
    public boolean canMove(int row, int col) {
        // Finish this method   
        if(row >= 0 || col >= 0 || row <= Board.NUM_ROWS || col <= Board.NUM_COLS){
            return true;    
        }
        return false;
    }
    
    public void paintSnake(Graphics g, int squareWidth, int squareHeight) {
        for(Node node: body){
            Util.drawSquare(g, node.getRow(), node.getCol(), squareWidth, squareHeight, Color.red);
        }
    }
    
    public void move() {
        int row = body.get(0).getRow();
        int col = body.get(0).getCol();
        switch (direction) {
            case UP:
                body.add(0, new Node(row + 1, col));
                break;
            case DOWN:
                body.add(0, new Node(row - 1, col));
                break;
            case LEFT:
                body.add(0, new Node(row, col - 1));
                break;
            case RIGHT:
                body.add(0, new Node(row, col + 1));
                break;
        }


        
    }
    
    
    
}
