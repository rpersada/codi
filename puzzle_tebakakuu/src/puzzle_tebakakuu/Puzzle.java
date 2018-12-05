/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tebk;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static tebk.main.frame;
import static tebk.main.puzzle;
import java.awt.CardLayout;
/**
 *
 * @author persada
 */
public class Puzzle extends JPanel{
        Segment[] segments;
        public  static String info;
	Image img;
        //int score =0;
       
       
        
        
       
        

        
	public boolean started = false;
	public boolean mixing = false;
        
        public Puzzle(Image img) {
		this.img = img;
		
		segments = new Segment[9];
		int segmentSize = img.getWidth(null)/3;
		for (int i = 0; i != segments.length; i++) {
			
			segments[i] = new Segment(this, i, segmentSize);
		}
                start();
                addMouseListener(new MouseAdapter() {
                        @Override
			public void mousePressed(MouseEvent e) {
//                            if (!started) 
//                                start();
                            if (!mixing) 
                                onClick(e);
                            else 
                                mixing = false;
			}
		});
                 
        
        
       
        
        
        }
        public void start() {
		started = true;
		
		segments[8].isEmpty = true;
		mix.start();
	}
        Thread mix = new Thread(new Runnable() {
		public void run() {
			mixing = true;
			                 for (int i = 0; i < 50; i++) {
                        
                    
				ArrayList<Integer> possibleMovements = new ArrayList<Integer>();
				for (Segment s : segments) {
					if (s.getPosition().x == segments[8].getPosition().x+1 && s.getPosition().y == segments[8].getPosition().y) possibleMovements.add(s.getID());
					if (s.getPosition().x == segments[8].getPosition().x-1 && s.getPosition().y == segments[8].getPosition().y) possibleMovements.add(s.getID());
					if (s.getPosition().x == segments[8].getPosition().x && s.getPosition().y == segments[8].getPosition().y-1) possibleMovements.add(s.getID());
					if (s.getPosition().x == segments[8].getPosition().x && s.getPosition().y == segments[8].getPosition().y+1) possibleMovements.add(s.getID());
				}
				
				int ind = (int) ((Math.random()*possibleMovements.size()));
				try {
					Point tmp = segments[possibleMovements.get(ind)].getPosition();
					segments[possibleMovements.get(ind)].setPosition(segments[8].getPosition());
					segments[8].setPosition(tmp);
				} catch (Exception e) {}
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {}
			}
		}
	});
        
//    public void setrunTimer(int detikAtas){
//        this.detikAtas = detikAtas;        
//    }   
//    public int getrunTimer(){
//        return this.detikAtas;
//    }
        
        public void onClick(MouseEvent e) {
		for (Segment s : segments) {
			if (s.hitten(e.getPoint())) {
				Point tmp = s.getPosition();
				if (s.move(segments[8].getPosition())) {
					segments[8].setPosition(tmp);
					
			
					boolean done = true;
					for (int i = 0; i != 9; i++) {
						if (segments[i].getPosition().x == ((i <= 2)? i:(i <= 5)? (i-3):(i-6)) && segments[i].getPosition().y == (int) Math.ceil((i/3))) {
							//System.out.println(i+": :)");
						} else {
							//System.out.println(i+": :(");
							done = false;
                                                       
                                                          
						}
					}
					
					if (done) {
                                                
						started = false;
						segments[8].isEmpty = false;
                                                System.out.println("selesai");
                                                
                                                
                                                
                                               
                                                //JOptionPane.showMessageDialog(null, "Berhasil:");
//                                                JOptionPane.showMessageDialog(null, "Skor:"+ score);
                                                new info().setVisible(true);
                                               
					}
                                        
                                    
                                        
                                    
                                        
                                    
                                        
                                    
				}
			}
		}
		repaint();
	}
        
        public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i != segments.length; i++) {
			//System.out.print(segments[i].getID()+((i == 2 || i == 5 || i == 8)? "\n-----\n":"|"));
			segments[i].paint(g);
		}
	}
	
}
	
