/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle_tebakakuu;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import static puzzle_tebakakuu.main.frame;
import static puzzle_tebakakuu.main.puzzle;
/**
 *
 * @author persada
 */
public class Puzzle extends JPanel{
    Segment[] segments;
	Image img;
        CardLayout cl;
        
        
       
        
	
	public boolean started = false;
	public boolean mixing = false;
    
        public Puzzle(Image img) {
		this.img = img;
                
		//init 9 segments
		segments = new Segment[9];
		int segmentSize = img.getWidth(null)/3;
		for (int i = 0; i != segments.length; i++) {
			//Pass the Segment the instance of the Game, its position (by the index) and its size
			segments[i] = new Segment(this, i, segmentSize);
		}
                addMouseListener(new MouseAdapter() {
                        @Override
			public void mousePressed(MouseEvent e) {
                            if (!started) 
                                start();
                            else if (!mixing) 
                                onClick(e);
                            else 
                                mixing = false;
			}
		});
	}
        public void start() {
		started = true;
		//remove segment 8 (the one in the bottom right)
		segments[8].isEmpty = true;
		mix.start();
	}
        //This Thread shuffles the Segments by switching one of the neighboring segments of the empty one with the empty one
	Thread mix = new Thread(new Runnable() {
		public void run() {
			mixing = true;
			while (mixing) {
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
					Thread.sleep(100);
				} catch (InterruptedException e) {}
			}
		}
	});
        
        //Check if the user clicked onto a seg,emt amd of possible move it onto the empty one
	public void onClick(MouseEvent e) {
		for (Segment s : segments) {
			if (s.hitten(e.getPoint())) {
				Point tmp = s.getPosition();
				if (s.move(segments[8].getPosition())) {
					segments[8].setPosition(tmp);
					
					//Check if user is done by comparing it's position with the one it should have
					boolean done = true;
					for (int i = 0; i != 9; i++) {
						if (segments[i].getPosition().x == ((i <= 2)? i:(i <= 5)? (i-3):(i-6)) && segments[i].getPosition().y == (int) Math.ceil((i/3))) {
							//System.out.println(i+": :)");
						} else {
							//System.out.println(i+": :(");
							done = false;
                                                        
						}
                                                
					}
					
					if   (done) {
						started = false;
						segments[8].isEmpty = false;
                                                informasi1 i1 = new informasi1 ();
                                                i1.setVisible(true);
                                                this.dispose();
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
        
        

    private void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
