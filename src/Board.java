
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.List;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author victoralonso
 */
public class Board extends javax.swing.JPanel {
    
    private List<Player> player;
    private ScoreBoard scoreBoard;
    private int numRows;
    private int numCols;
    private Snake snake;
    private Food food;
    private Food specialFood;
    private Timer snakeTimer;
    private Timer specialFoodTimer;
    private int deltaTime;
    private final int INITIAL_DELTA_TIME = 300;
    private final int NORMAL_FOOD = 1;
    private final int SPECIAL_FOOD = 4;
    private boolean specialFoodOn;
    
    /**
     * Creates new form Board
     */
    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(snake.getDirection() != Direction.RIGHT){
                        snake.setDirection(Direction.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(snake.getDirection() != Direction.LEFT){
                        snake.setDirection(Direction.RIGHT);
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(snake.getDirection() != Direction.DOWN){
                        snake.setDirection(Direction.UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(snake.getDirection() != Direction.UP){
                        snake.setDirection(Direction.DOWN);
                    }
                    break;
                default:
                    break;
            }
            repaint();
        }
    }
    
    
    public Board() {
        initComponents();
        myInit();
       
    }
    
    private void myInit() {
        // Finish this method
        setFocusable(true);
        snake = new Snake(5, 5, 1);
        food = new Food(snake, false);
        MyKeyAdapter keyAdapter = new MyKeyAdapter();
        addKeyListener(keyAdapter);
        deltaTime = INITIAL_DELTA_TIME;
        createSnakeTimer();
        createSpecialTimer();
        startTimers();
        
    }
    
    public void startTimers() {
        snakeTimer.start();
        specialFoodTimer.start();
    }
    
     private void createSnakeTimer(){
        snakeTimer = new Timer(deltaTime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                loop();
            }
        });
    }
     
    private void createSpecialTimer(){
        specialFoodTimer = new Timer(deltaTime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                specialLoop();
            }
        });
    }
     
    private void loop(){
        //Method of SnakeTimer
        if(!snake.canMove()){
            gameOver(); 
        } else {
           if(colideFood()){
               if(colideNormalFood()){
                    snake.setRemainingNodes(1);
                    food = new Food(snake, true);
                    scoreBoard.setScore(snake.getSize());
                } else {
                    snake.setRemainingNodes(4);
                    food = new Food(snake, true);
                    scoreBoard.setScore(snake.getSize());
                   
               }
               repaint();
           }
        
        
        }
    }
    private void specialLoop(){
        //Method of SnakeFoodTimer
         if (!specialFoodOn) {
            specialFood = new Food(snake, true);
            specialFoodOn = true;
            int randomTimer = (int) (Math.random() * 10000 + 30000);
            specialFoodTimer.setDelay(randomTimer);
        } else {
            food = new Food(snake, false);
            specialFoodOn = false;
            int randomTimer = (int) (Math.random() * 5000 + 7000);
            specialFoodTimer.setDelay(randomTimer);
        }
        repaint();
            
    } 
    
    public Board(int numRows, int numCols) {
        // Finish this method
        initComponents();
        myInit();
        this.numRows = numRows;
        this.numCols = numCols;
    }
    
    public void setScoreBoard(ScoreBoard scoreBoard){
        this.scoreBoard = scoreBoard;
    }
    
    public boolean colideFood() {
        return colideNormalFood() || colideSpecialFood();
    }
    
    private boolean colideNormalFood() {
        Node head = snake.getHeader();
        return food.getPosition().getCol() == head.getCol() || food.getPosition().getRow() == head.getRow();
    }

    private boolean colideSpecialFood() {
        if(specialFoodOn){
            Node head = snake.getHeader();
            return specialFood.getPosition().getCol() == head.getCol() || specialFood.getPosition().getRow() == head.getRow();
        }
        return false; 
    }
    
    public void gameOver() {
        stopTimers();
    }
    
    private int squareWidth(){
        return getWidth() / AspectsConfig.NUM_COLS;
    }
    
    private int squareHeight(){
        return getHeight() / AspectsConfig.NUM_ROWS;
    }
    
   
    
    private void drawBoard(Graphics2D g2d){
        for(int row = 0; row < AspectsConfig.NUM_ROWS; row++){
            for(int col = 0; col < AspectsConfig.NUM_COLS; col++){
                Util.drawSquare(g2d, row, col, squareWidth(), squareHeight(), Color.DARK_GRAY);
            }
        }
    }
    
    public void stopTimers(){
        snakeTimer.stop();
        specialFoodTimer.stop();
    }
    
    @Override 
    protected void paintComponent(Graphics g)  {
        // Finish this method
        // Paint the Snake and the food here
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawBoard(g2d);
        snake.paintSnake(g2d, squareWidth(), squareHeight());
        food.paint(g, squareWidth(), squareHeight());
    }
    
    
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
