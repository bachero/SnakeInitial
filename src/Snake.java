
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
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
        direction = direction.DOWN;
    }
    
    public Node getHeader(){
        return body.get(0);
    }
    
    public List<Node> getList(){
        return body;
    }
    
    public boolean canMove() {
        Node firstNode = body.get(0);
        if(firstNode.getCol() < 0 || firstNode.getCol() >= AspectsConfig.NUM_COLS || 
                firstNode.getRow() < 0 || firstNode.getRow() >= AspectsConfig.NUM_ROWS){
            move();
            return false;
        } else {
            Node node;
            for (int i = 1; i < body.size(); i++) {
                node = body.get(i);
                if(node.getCol() == firstNode.getCol() && node.getRow() == firstNode.getRow()){
                    move();
                    return false;
                }
            }
            move();
            return true;
        }
    }
    
    public void paintSnake(Graphics g, int squareWidth, int squareHeight) {
        for(Node node: body){
            Util.drawSquare(g, node.getRow(), node.getCol(), squareWidth, squareHeight, Color.red);
        }
    }
    
    public void move() {
        Toolkit.getDefaultToolkit().sync();
        int row = body.get(0).getRow();
        int col = body.get(0).getCol();
        switch (direction) {
            case UP:
                remainingNodesToMove(row - 1, col);
                break;
            case DOWN:
                remainingNodesToMove(row + 1, col);
                break;
            case LEFT:
                remainingNodesToMove(row, col - 1);
                break;
            case RIGHT:
                remainingNodesToMove(row, col + 1);
                break;
        }
    
    }
    
        
    public void remainingNodesToMove(int row, int col){
  
        body.add(0, new Node(row, col));
        if (remainingNodesToCreate == 0) {
            body.remove(body.size() - 1);
        } else {
            remainingNodesToCreate --;
        }
        
    }
        

    public void setRemainingNodes(int remainingNodesToCreate){
        this.remainingNodesToCreate += remainingNodesToCreate;
    }
    
    public void setDirection(Direction direction){
        this.direction = direction;
    }
    
    public Direction getDirection(){
        return direction;
    }
    
    public int getSize(){
       return body.size();
    } 
    
    
}
