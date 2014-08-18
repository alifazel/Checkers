/*
 * @(#)DraughtBoard.java  6.04.02
 *
 * Represents the state of the draught board
 *
 * Dr. Mike Spann
 *
 *
*/


public class DraughtBoard
{

	public static final char BLACK='b';	//represents a black piece
	public static final char WHITE='w';	//represents a white piece
	public static final char EMPTY='e';	//board position is empty

	public static final char LEFT='l';	//a left move
	public static final char RIGHT='r';	//a right move

	public DraughtBoard()
	{

	 	/***
		* Initialize the board layout
	  	***/

	  	board=new char[8][8];

	  	for (int x=0; x<8; x++)
	      for (int y=0; y<8; y++)
	        board[x][y]=EMPTY;

	  	setBoard();

	}

	public void setBoard()
	{

    	/***
        * Initialize the board layout
        ***/

        whitePiecesLeft=12;

        blackPiecesLeft=12;

        whiteScore=0;

        blackScore=0;

        for (int x=0; x<8; x++)
          for (int y=0; y<8; y++)
            board[x][y]=EMPTY;

	  	board[0][0]=board[2][0]=board[4][0]=board[6][0]=WHITE;
	  	board[1][1]=board[3][1]=board[5][1]=board[7][1]=WHITE;
	  	board[0][2]=board[2][2]=board[4][2]=board[6][2]=WHITE;

	  	board[1][7]=board[3][7]=board[5][7]=board[7][7]=BLACK;
	  	board[0][6]=board[2][6]=board[4][6]=board[6][6]=BLACK;
	  	board[1][5]=board[3][5]=board[5][5]=board[7][5]=BLACK;

	}

	public char getPiece(int x, int y) {return board[x][y];}
        
	public int getWhiteScore() { return whiteScore;}

	public int getBlackScore() { return blackScore;}

	public int getWhitePiecesLeft() { return whitePiecesLeft;}

	public int getBlackPiecesLeft() { return blackPiecesLeft;}


        public boolean legalMove(int x, int y, char direction, char colour)
        {
          if (direction == 'l') {
              if(x==0) {
                  return false;
              }
             /* if(colour == 'b') {
                  if(this.getPiece())
              }
              if(colour == 'w') {
                  
              }*/
             
             
          }
          
          if (direction == 'r') {
              if(x==7) {
                  return false;
              }          
          }
          
          return true;
        }
        
	public int moveBlack(int x, int y, char direction)
	{
            //check if its possible    
            
            // x,y,left
            
            boolean checkMove = legalMove(x,y,direction,'b');
            
            
            if (checkMove==true)
            {
                if (direction=='l')
                {
                    int xDirection=x;
                    board[x][y]=EMPTY;
                    xDirection--;
                    board[xDirection][y-1]=BLACK;
                }
            
                
                if (direction=='r')
                {
                    int xDirection=x;
                    board[x][y]=EMPTY;
                    xDirection++;
                    board[xDirection][y-1]=BLACK;   //issue on line
                }
            } else 
            {
                System.out.println("Error: You have tried to move in an incorrect position.");
            }

            
	  	/***
	  	* Moves a black piece in a specified direction
	  	***/

	  	return 0;

	}


	public int moveWhite(int x, int y, char direction)
	{
            
            boolean checkMove = legalMove(x,y,direction,'w');
            
            
            if (checkMove==true)
            {
                if (direction=='l')
                {
                    int xDirection=x;
                    board[x][y]=EMPTY;
                    xDirection--;
                    board[xDirection][y+1]=WHITE;
                    
                }
            
                
                if (direction=='r')
                {
                    int xDirection=x;
                    board[x][y]=EMPTY;
                    xDirection++;
                    board[xDirection][y+1]=WHITE;   //issue on line
                }
            } else 
            {
                System.out.println("Error: You have tried to move in an incorrect position.");
            }

	  	/***
	  	* Moves a white piece in a specified direction
	  	***/

	  	return 0;

	}
        
        public int removePiece(int x, int y)
        {
            board[x][y]=EMPTY;
            return 0;
        }

	private char board[][];

	private int whitePiecesLeft=12;

   	private int blackPiecesLeft=12;

    private int whiteScore=0;       // The number of white pieces over the black line

   	private int blackScore=0;       // The number of black pieces over the white line
}

