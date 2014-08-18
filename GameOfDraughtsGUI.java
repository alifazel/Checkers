/*
 * @(#)GameOfDraughtsGUI.java  25.02.02
 *
 * Simple GUI attached to the game of Draughts
 *
 * Dr. Mike Spann
 *
 *
*/

import java.io.*;
import java.text.*;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;


class GameOfDraughtsFrame extends JFrame implements ActionListener
{

	public GameOfDraughtsFrame()
	{
		setTitle("Draughts");
        setSize(800,700);
        addWindowListener(new WindowAdapter()
        {
        	public void windowClosing(WindowEvent e)
            {
            	System.exit(0);
            }
        } );



		/***
		* Add the menu bar
	 	***/

		JMenuBar mbar=new JMenuBar();
		setJMenuBar(mbar);

		JMenu GameMenu=new JMenu("Play");

        NewGameItem=new JMenuItem("New Game");
        NewGameItem.addActionListener(this);
        GameMenu.add(NewGameItem);
        GameMenu.addSeparator();

        NextMoveItem=new JMenuItem("Next Move");
        NextMoveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                                              InputEvent.CTRL_MASK));
        NextMoveItem.addActionListener(this);
       	GameMenu.add(NextMoveItem);
        GameMenu.addSeparator();

        ExitItem=new JMenuItem("Exit");
       	ExitItem.addActionListener(this);
        GameMenu.add(ExitItem);
        GameMenu.addSeparator();

        mbar.add(GameMenu);

        JMenu SelectWhitePlayerMenu=new JMenu("Select White Player");

       	BasicWhiteItem=new JMenuItem("Basic");
        BasicWhiteItem.addActionListener(this);
        SelectWhitePlayerMenu.add(BasicWhiteItem);
        SelectWhitePlayerMenu.addSeparator();

        AdvancedWhiteItem=new JMenuItem("Advanced");
        AdvancedWhiteItem.addActionListener(this);
        SelectWhitePlayerMenu.add(AdvancedWhiteItem);
        SelectWhitePlayerMenu.addSeparator();

        mbar.add(SelectWhitePlayerMenu);

        JMenu SelectBlackPlayerMenu=new JMenu("Select Black Player");

        BasicBlackItem=new JMenuItem("Basic");
        BasicBlackItem.addActionListener(this);
        SelectBlackPlayerMenu.add(BasicBlackItem);
        SelectBlackPlayerMenu.addSeparator();

        AdvancedBlackItem=new JMenuItem("Advanced");
        AdvancedBlackItem.addActionListener(this);
        SelectBlackPlayerMenu.add(AdvancedBlackItem);
        SelectBlackPlayerMenu.addSeparator();

        mbar.add(SelectBlackPlayerMenu);


		/***
	    * Set up the board
		***/

		board=new DraughtBoard();

		/***
	   	* Add the board drawing area
		* panel
		***/

		Container contentPane=getContentPane();

		boardPanel=new DraughtBoardPanel(board);

		contentPane.add(boardPanel,"Center");

		boardPanel.draw(0,0);

	}


	public void actionPerformed(ActionEvent evt)
	{

		Object source=evt.getSource();
        if (source==NewGameItem)
		{
       	  /***
       	  * Reset board for a new game
          ***/


		  board.setBoard();

		  nextMoveColor=DraughtBoard.WHITE;	// White has first move

		  endOfGame=false;

		  boardPanel.draw(0,0);

		}
        else if ((source==NextMoveItem)&&(!endOfGame))
        {
          /***
          * Move the next piece
          ***/
                JOptionPane.showMessageDialog(null, nextMoveColor);
		  if (nextMoveColor==DraughtBoard.WHITE)
	      {
		    whitePlayer.movePiece(board);
		    nextMoveColor=DraughtBoard.BLACK;
		  }
		  else
		  {
		    blackPlayer.movePiece(board);
		    nextMoveColor=DraughtBoard.WHITE;
		  }

		  int ws=board.getWhiteScore(); int bs=board.getBlackScore();
		  boardPanel.draw(ws,bs);	// re-draw the board

		  if ((board.getWhitePiecesLeft()==0)||(board.getBlackPiecesLeft()==0))
		  {
		    endOfGame=true;

			System.out.println("End of Game");

	  	    if (ws>bs)
              JOptionPane.showMessageDialog(this,"White wins! "," ",JOptionPane.PLAIN_MESSAGE);
	  	    else if (ws<bs)
              JOptionPane.showMessageDialog(this,"Black wins! "," ",JOptionPane.PLAIN_MESSAGE);
	  	    else if (ws==bs)
              JOptionPane.showMessageDialog(this,"Drawn Game! "," ",JOptionPane.PLAIN_MESSAGE);

		  }
        }
		else if (source==ExitItem)
	    {
		  /***
		  * Quit the game
		  ***/

		  System.exit(0);
		}
		else if (source==BasicWhiteItem)
		{
		  System.out.println("Not implemented1");
		  /* Plug in basic white player here */
		}
		else if (source==AdvancedWhiteItem)
		{
		  System.out.println("Not implemented2");
		  /* Plug in advanced white player here */
		}
		else if (source==BasicBlackItem)
		{
		  System.out.println("Not implemented3");
		  /* Plug in basic black player here */
		}
		else if (source==AdvancedBlackItem)
		{
		  System.out.println("Not implemented4");
		  /* Plug in advanced black player here */
		}

	}

	private JMenuItem NewGameItem,NextMoveItem,ExitItem;

	private JMenuItem BasicWhiteItem,BasicBlackItem,AdvancedWhiteItem,AdvancedBlackItem;

	private DraughtBoard board;

	private DraughtBoardPanel boardPanel;

	private Player whitePlayer=new Player('w');

	private Player blackPlayer=new Player('b');

	private char nextMoveColor=DraughtBoard.WHITE;

	private boolean endOfGame=false;

}


class DraughtBoardPanel extends JPanel
{

	public DraughtBoardPanel(DraughtBoard b)
	{
	  	setLayout(new GridLayout(8,8,2,2));
	  	Border border=BorderFactory.createRaisedBevelBorder();
	  	setBorder(border);

	  	board=b;
	}

	public void paintComponent(Graphics g)
	{
	  	super.paintComponent(g);

	  	/***
	  	* Draw board squares
	  	***/

	  	Color col=Color.gray;
	  	g.setColor(col);
	  	for (int x=0; x<8; x++)
	  	{
	      for (int y=0; y<8; y++)
	      {
	        g.fillRect(100+50*x,100+50*y,50,50);
	        if (col==Color.gray)	// Toggle colors
		      col=Color.white;
	        else
		      col=Color.gray;
	        g.setColor(col);
	      }
	      if (col==Color.gray)	// Toggle colors
	        col=Color.white;
	      else
	       col=Color.gray;
	      g.setColor(col);
	    }

	  	/***
	  	* Draw pieces on the white squares
	  	***/

	  	for (int x=0; x<8; x++)
	      for (int y=0; y<8; y++)
	      {
	        char pieceCol=board.getPiece(x,y);
	        if (pieceCol==DraughtBoard.WHITE)
	        {
		      if (y!=7)	// Don't draw whites on black line
		      {
	            g.setColor(Color.white);
	            g.fillOval(110+50*x,110+50*y,30,30);
		      }
	        }
	        else if (pieceCol==DraughtBoard.BLACK)
	        {
		      if (y!=0)	// Don't draw blacks on white line
		      {
	            g.setColor(Color.black);
	            g.fillOval(110+50*x,110+50*y,30,30);
		      }
	        }
	      }

	 	/***
	  	* Draw white and black scores
	  	***/

        Font f=new Font("Helvetica",Font.BOLD,15);
	    g.setFont(f);
	  	g.setColor(Color.black);

        String ws="White score = " + whiteScore;
        g.drawString(ws,100,600);
        String bs="Black score = " + blackScore;
        g.drawString(bs,350,600);

	}

	public void draw(int ws, int bs)
	{

	  	/***
	  	* Set the scores
	  	***/

	  	whiteScore=ws; blackScore=bs;

	  	/***
	  	* Re-draw panel
	  	***/

	  	repaint();
	}


	private DraughtBoard board;

	private int whiteScore=0;

	private int blackScore=0;
}


public class GameOfDraughtsGUI
{
	public static void main(String[]args)
	{

		JFrame frame=new GameOfDraughtsFrame();

		frame.show();
	}
}
