/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author victoralonso
 */
public class Node {
    private int row;
    private int col;
    
    // Create the constructor and all the methods you need here
    public Node(int r, int c){
        row = r;
        col = c;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
    
    public void setRow(int r){
        row = r;
    }
    
    public void setCol(int c){
        col = c;
    }
}
