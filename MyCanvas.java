package firsttry;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;






public class MyCanvas extends Canvas implements KeyListener {
	
	//private static final Clip AudioSystem = null;
	//global variables - accessible in all methods
	Goodguy link = new Goodguy(200,700,100,100,"files/Skull tropper.png");
	Background startscreen = new Background (0,0,1000,800,"files/Startscreen.png");
	Background background = new Background (0,0,1000,800,"files/tiltedtowers2.png");
	Background gameover = new Background (0,0,1000,800,"files/Restart.png");
	LinkedList badguys = new LinkedList();
	
	int Question = 0;
	boolean Correct = false;
	boolean startGame = false;
	boolean restart = false;
	boolean gameOver = false;
	

	public MyCanvas() {
		this.setSize(1000,800);
		this.addKeyListener(this);
		this.setVisible(true);
		
		Random rand = new Random();
		int winwidth = this.getWidth();
		int winheight = this.getHeight();
		for(int i = 0; i<5; i++) {
			Badguy bg = new Badguy(rand.nextInt(winwidth),rand.nextInt(600),50, 50, "files/default.png");
			Rectangle r = new Rectangle(100,100,30,30);
			if (r.contains(link.getxCoord(), link.getyCoord())) {
				System.out.println("badguy on top of link");
				continue;
			}
			badguys.add(bg); 
		}
		
		TimerTask repeatedTask = new TimerTask() {

            public void run() {
            	int dir = rand.nextInt(3);
                for(int i = 0; i < badguys.size(); i++) {// draw bad guys
                	dir = rand.nextInt(3);
                	Badguy bg = (Badguy) badguys.get(i);
                	if (dir == 1) {
                		bg.setxCoord(bg.getxCoord() + 10);
                	} else if (dir == 3) {
                		bg.setxCoord(bg.getxCoord() - 10);
               	
                	} else if (dir == 0) {
                		bg.setyCoord(bg.getyCoord() + 10);
                		
                	} else if (dir == 2) {
                		bg.setyCoord(bg.getyCoord() - 10);
                	}
                	
                	
                	
                    
                    
                }

                repaint();

            }

        };

        Timer timer = new Timer("Timer");

         

        long delay  = 500L;

        long period = 500L;

        timer.scheduleAtFixedRate(repeatedTask, delay, period);
	
		try {
			File yourFile = new File ("files/dancetherapy2.wav");
		AudioInputStream stream;
		AudioFormat format;
		DataLine.Info info;
		Clip clip;
		
		stream = AudioSystem.getAudioInputStream(yourFile);
		format = stream.getFormat();
		info = new DataLine.Info(Clip.class, format);
		clip = (Clip) AudioSystem.getLine(info);
		clip.open(stream);
		clip.start();
		}
		catch (Exception e ) {
			System.out.println(e);
		}  
	
		
		
	}


	public void paint(Graphics g) {
		if (startGame == false) {
			g.drawImage(startscreen.getImg(), startscreen.getxCoord(), startscreen.getyCoord(), startscreen.getWidth(), startscreen.getHeight(), this);

		} if (startGame == true || restart == true) {
			g.drawImage(background.getImg(), background.getxCoord(), background.getyCoord(), background.getWidth(), background.getHeight(), this);
			g.drawImage(link.getImg(), link.getxCoord(), link.getyCoord(), link.getWidth(), link.getHeight(), this);
			

			for(int i = 0; i < badguys.size(); i++) {
				Badguy bg = (Badguy) badguys.get(i);
				g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight(), this); 
				Rectangle r = new Rectangle(bg.getxCoord(), bg.getyCoord(), bg.getHeight(), bg.getWidth());
				Rectangle l = new Rectangle(link.getxCoord(), link.getyCoord(), link.getHeight(), link.getWidth());
				
				if (r.intersects(l)) {
					System.out.println("Badguy hit by link");
					Question = Question + 1;
					badguys.remove(i);
				}
				

				if (Correct == true) {
					g.setColor(Color.WHITE);
					g.drawString("Correct! Well done!", 50, 20);
					//Correct = false;
				}
				
				
				
			} 
			
			if (Question == 1) {
				g.setColor(Color.WHITE);
				g.drawString("What is 16 + 32?", 50, 50);
				g.drawString("a. 48", 50, 70);
				g.drawString("b. 33", 50, 90);
				g.drawString("c. 29", 50, 110);
				g.drawString("d. 47", 50, 130);
				
				
			}
			
			if (Question == 2) {
				g.setColor(Color.WHITE);
				g.drawString("What is 39 + 13?", 50, 50);
				g.drawString("a. 48", 50, 70);
				g.drawString("b. 33", 50, 90);
				g.drawString("c. 52", 50, 110);
				g.drawString("d. 47", 50, 130);
				
				
			}
			
			if (Question == 3) {
				g.setColor(Color.WHITE);
				g.drawString("What is 49 + 27?", 50, 50);
				g.drawString("a. 48", 50, 70);
				g.drawString("b. 76", 50, 90);
				g.drawString("c. 29", 50, 110);
				g.drawString("d. 47", 50, 130);
				
				
			}
			
			if (Question == 4) {
				g.setColor(Color.WHITE);
				g.drawString("What is 102 + 69?", 50, 50);
				g.drawString("a. 123", 50, 70);
				g.drawString("b. 171", 50, 90);
				g.drawString("c. 195", 50, 110);
				g.drawString("d. 12", 50, 130);
			}
			
			if (Question == 5) {
				g.setColor(Color.WHITE);
				g.drawString("What is 372 + 791?", 50, 50);
				g.drawString("a. 1162", 50, 70);
				g.drawString("b. 1153", 50, 90);
				g.drawString("c. 1163", 50, 110);
				g.drawString("d. 1263", 50, 130);
			}	
		
		if (gameOver == true) {
			g.drawImage(gameover.getImg(), gameover.getxCoord(), gameover.getyCoord(), gameover.getWidth(), gameover.getHeight(), this);

		}
		
	
		repaint();
		
		}
		
			
		
	}
	
	
	@Override
	public void keyPressed(java.awt.event.KeyEvent e) {
		int key = e.getKeyCode();
		System.out.println(e);
		link.moveIt(e.getKeyCode(),this.getWidth(),this.getHeight());
		
		if (key == KeyEvent.VK_SPACE) {
			Projectile bullet = new Projectile(link.getxCoord(), link.getyCoord(),30,30,"files/bullet.png");
			bullet.add(bullet);
		}
		
		if (key == KeyEvent.VK_A) {
			if (Question == 1) {
				Correct = true;
			}
			
			if (Question == 2) {
				gameOver = true;
			}
			
			if (Question == 3) {
				gameOver = true;
			}
			
		}
		
		if (key == KeyEvent.VK_B) {
			if (Question == 1) {
				gameOver = true;
			}
			
			if (Question == 2) {
				gameOver = true;
			}
			
			if (Question == 3) {
				Correct = true;
			}
		}
		
		if (key == KeyEvent.VK_C) {
			if (Question == 1) {
				gameOver = true;
			}
			
			if (Question == 2) {
				Correct = true;
			}
			
			if (Question == 3) {
				gameOver = true;
			}
		}
		
		if (key == KeyEvent.VK_D) {
			if (Question == 1) {
				gameOver = true;
			}
			
			if (Question == 2) {
				gameOver = true;
			}
			
			if (Question == 3) {
				gameOver = true;
			}
		}
		
		if (key == KeyEvent.VK_ENTER) {
			if (startGame == false) {
				startGame = true;
			}
			
			if (gameOver == true) {
				gameOver = false;
				restart = true;
			}
		}
		
	}
	  


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}



