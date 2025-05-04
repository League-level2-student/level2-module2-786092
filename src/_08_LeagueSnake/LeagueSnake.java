package _08_LeagueSnake;

import java.util.ArrayList;

import processing.core.PApplet;

public class LeagueSnake extends PApplet {
    static final int WIDTH = 500;
    static final int HEIGHT = 500;
    
    /*
     * Game variables
     * 
     * Put all the game variables here.
     */
    Segment head;
    int foodX;
    int foodY;
    int direction = UP;
    int pieces = 0;
    ArrayList<Segment> segments = new ArrayList<Segment>();
    
    
    /*
     * Setup methods
     * 
     * These methods are called at the start of the game.
     */
    
    @Override
    public void settings() {
        size(WIDTH,HEIGHT);
    }

    @Override
    public void setup() {
       head = new Segment(250,250);
       frameRate(20);
       dropFood();
       
    }

    void dropFood() {
        // Set the food in a new random location
        foodX = ((int)random(50)*10);
        foodY = ((int)random(50)*10);
    }

    /*
     * Draw Methods
     * 
     * These methods are used to draw the snake and its food
     */

    @Override
    public void draw() {
        background(0,0,200);
        drawFood();
        move();
        drawSnake();
        if(head.x==foodX && head.y==foodY) {
        	eat();
        }
    }

    void drawFood() {
        // Draw the food
    	fill(230,70,160);
        rect(foodX,foodY,10,10);
    }

    void drawSnake() {
        // Draw the head of the snake followed by its tail
    	fill(0,200,0);
    	rect(head.x,head.y,10,10);
    	manageTail();
    }

    void drawTail() {
        // Draw each segment of the tail
        fill(0,130,0);
        for(Segment s: segments) {
        	rect(s.x,s.y,10,10);
        }
    }

    /*
     * Tail Management methods
     * 
     * These methods make sure the tail is the correct length.
     */

    void manageTail() {
        // After drawing the tail, add a new segment at the "start" of the tail and
        // remove the one at the "end"
        // This produces the illusion of the snake tail moving.
    	checkTailCollision();
    	drawTail();
    	segments.add(new Segment(head.x,head.y));
    	segments.remove(0);

    }

    void checkTailCollision() {
        // If the snake crosses its own tail, shrink the tail back to one segment
        for(Segment s: segments) {
        	if(head.x == s.x && head.y == s.y ) {
        	    pieces=1;
        	    segments.clear();
        	    segments.add(new Segment(head.x,head.y));
        	    break;
        	}
        }
    }

    /*
     * Control methods
     * 
     * These methods are used to change what is happening to the snake
     */

    @Override
    public void keyPressed() {
        // Set the direction of the snake according to the arrow keys pressed
        if(key==CODED) {
        	if(direction!=DOWN && keyCode==UP) {
        		direction = UP;
        	}
        	else if(direction!=UP && keyCode==DOWN) {
        		direction = DOWN;
        	}
        	else if(direction!=LEFT && keyCode==RIGHT) {
        		direction = RIGHT;
        	}
        	else if(direction!=RIGHT && keyCode==LEFT) {
        		direction = LEFT;
        		System.out.println("Move LEft");
        	}
        }
    }

    void move() {
        // Change the location of the Snake head based on the direction it is moving.

        
        if (direction == UP) {
        	head.y-=10;
        	
        } 
        else if (direction == DOWN) {
            head.y+=10; 
            
        } 
        else if (direction == LEFT) {
            head.x-=10;
        } 
        else if (direction == RIGHT) {
            head.x+=10;
        }
        checkBoundaries();
    }     

    void checkBoundaries() {
        // If the snake leaves the frame, make it reappear on the other side
        if(head.y<0) {
        	head.y = HEIGHT-10;
        }
        else if(head.y+10>HEIGHT) {
        	head.y = 10;
        }
        else if(head.x<0) {
        	head.x = WIDTH-10;
        }
        else if(head.x+10>WIDTH) {
        	head.x = 10;
        }
    }

    void eat() {
        // When the snake eats the food, its tail should grow and more
        // food appear
    	pieces+=1;
        dropFood();
        segments.add(new Segment(head.x,head.y));
    }

    static public void main(String[] passedArgs) {
        PApplet.main(LeagueSnake.class.getName());
    }
}
