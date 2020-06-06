
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
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
    
    private List<Player> players;
    //private ScoreBoard scoreBoard;
    private int numRows;
    private int numCols;
    private Snake snake;
    private Food food;
    private Food specialFood;
    private Timer snakeTimer;
    private Timer specialFoodTimer;
    private int deltaTime;
    private int specialFoodDeltaTime;
    private final int INITIAL_DELTA_TIME = 100;
    private final int NORMAL_FOOD = 1;
    private final int SPECIAL_FOOD = 4;
    private boolean specialFoodOn;
    private boolean girando;
    private ScoreBehaviour scoreDelegate; 

    
    /**
     * Creates new form Board
     */
    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if(girando != true){
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if(snake.getDirection() != Direction.RIGHT){
                            snake.setDirection(Direction.LEFT);
                            girando = true;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(snake.getDirection() != Direction.LEFT){
                            snake.setDirection(Direction.RIGHT);
                            girando = true;
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if(snake.getDirection() != Direction.DOWN){
                            snake.setDirection(Direction.UP);
                            girando = true;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(snake.getDirection() != Direction.UP){
                            snake.setDirection(Direction.DOWN);
                            girando = true;
                        }
                        break;
                    default:
                        break;
                }
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
        specialFoodOn = false;
        players = new ArrayList<>();
        setFocusable(true);
        snake = new Snake(5, 5, 1);
        food = new Food(snake, false);
        specialFood = new Food(snake, true);
        MyKeyAdapter keyAdapter = new MyKeyAdapter();
        addKeyListener(keyAdapter);
        deltaTime = INITIAL_DELTA_TIME;
        specialFoodDeltaTime = 15000;
        girando = false;
        createSnakeTimer();
        createSpecialTimer();
        startTimers();
        
    }
    
    public void startTimers() {
        snakeTimer.start();
        specialFoodTimer.start();
    }
    
    public void restartTimers() {
        snakeTimer.restart();
        specialFoodTimer.restart();
        specialFoodTimer.setDelay(specialFoodDeltaTime);
        snakeTimer.setDelay(INITIAL_DELTA_TIME);
        
    }
    
    public void pauseGame(){
        stopTimers();
        JOptionPane.showMessageDialog(null,
            "Pause");
        startTimers();
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
        specialFoodTimer = new Timer(specialFoodDeltaTime, new ActionListener() {
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
                    food = new Food(snake, false);
                    //scoreBoard.incrementScore(NORMAL_FOOD);
                    scoreDelegate.incrementScore(NORMAL_FOOD);
                    System.out.println("Normal");
                } else {
                    System.out.println("Antes");
                    specialFood = new Food(snake, true);
                    snake.setRemainingNodes(4);
                    //scoreBoard.incrementScore(SPECIAL_FOOD);
                    scoreDelegate.incrementScore(SPECIAL_FOOD);
                    System.out.println("Especial");
                    specialFoodOn = false;
                   
               }
               
            }
            girando = false;
            repaint();
            Toolkit.getDefaultToolkit().sync();
        
        
        }
    }
    private void specialLoop(){
        //Method of SnakeFoodTimer
        
        if (!specialFoodOn) {
            specialFood = new Food(snake, true);
            specialFoodOn = true;
            int fast = INITIAL_DELTA_TIME;
            fast -= 5;
            snakeTimer.setDelay(fast);//La serpiente va mas rápido
            //int timeToReturn = 1000;
            //specialFoodTimer.setDelay(timeToReturn);
        } else {
            specialFoodOn = false;
            specialFood.desapear();
            //specialFoodTimer.setDelay(10);
            //snakeTimer.setDelay(INITIAL_DELTA_TIME);
            System.out.println("No va");
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
        this.scoreDelegate = scoreBoard;
    }
    
    public boolean colideFood() {
        return colideNormalFood() || colideSpecialFood();
    }
    
    private boolean colideNormalFood() {
        if(!specialFoodOn){
            Node head = snake.getHeader();
            return food.getPosition().getCol() == head.getCol() && food.getPosition().getRow() == head.getRow();
        }
        return false;
    }

    private boolean colideSpecialFood() {
        if(specialFoodOn){
            Node head = snake.getHeader();
            return specialFood.getPosition().getCol() == head.getCol() && specialFood.getPosition().getRow() == head.getRow();
        }
        return false; 
    }
    
    public void gameOver() {
        stopTimers();
        //int score = scoreBoard.getScore();
        int score = scoreDelegate.getScore();
        JOptionPane.showMessageDialog(null,
        "Game Over.\nYour size has been " + score);
        String nombre = JOptionPane.showInputDialog("Write your name.");
        if(nombre == null || nombre.equals("")){
            System.out.println("Null Name");
        } else {
            Player p = new Player(nombre, score);
            players.add(p);
            Collections.sort(players);
        }
        if(players.size() >= 3){
            JOptionPane.showMessageDialog(null,"High Scores:\n1. " + players.get(0).toString() + "\n2. " + 
            players.get(1).toString() + "\n3. " + players.get(2).toString());
        }
        startNewGame();
    }
        
    
    public void startNewGame() {
        snake = new Snake(5, 5, 1);
        food = new Food(snake, false);
        specialFood = new Food(snake, true);
        //scoreBoard.setScore(0);
        scoreDelegate.resetScore();
        specialFoodOn = false;
        setFocusable(true);
        specialFood = new Food(snake, true);
        MyKeyAdapter keyAdapter = new MyKeyAdapter();
        addKeyListener(keyAdapter);
        deltaTime = INITIAL_DELTA_TIME;
        specialFoodDeltaTime = 15000;
        restartTimers();
        
                
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
        if(specialFoodOn){
            specialFood.paint(g, squareWidth(), squareHeight());
        } else {
            food.paint(g, squareWidth(), squareHeight());
        }
        
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
