/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle_tebakakuu;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author persada
 */
public class main extends JPanel{
        static JFrame frame;
	static Puzzle puzzle;
     
        void exec(){
            frame = new JFrame("");
		frame.setSize(377, 390);
		puzzle = new Puzzle(new ImageIcon(main.class.getResource("Cendrawasih.jpg")).getImage());
		frame.add(puzzle);
		
		frame.setLocationRelativeTo(null);
		
		frame.setResizable(false);
		frame.setVisible(true);
//		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (!puzzle.started) 
                                    puzzle.start();
                                else if (!puzzle.mixing) 
                                    puzzle.onClick(e);
				else 
                                    puzzle.mixing = false;
			}
		});
        }
        public static void main(String [] args) {
		
                frame = new JFrame("");
		frame.setSize(222, 390);
	
		puzzle = new Puzzle(new ImageIcon(main.class.getResource("")).getImage());
		
		frame.add(puzzle);
		
		frame.setLocationRelativeTo(null);
		
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (!puzzle.started) 
                                    puzzle.start();
                                else if (!puzzle.mixing) 
                                    puzzle.onClick(e);
				else 
                                    puzzle.mixing = false;
			}
		});
	}
}
