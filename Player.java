
import javax.swing.JOptionPane;

/*
 * @(#)Player.java  6.04.02
 *
 * A base class for a draughts player
 *
 * Dr. Mike Spann
 *
 *
*/

public class Player
{
    char colour = ' ';
    char opposingColour = ' ';
    
    public Player(char colour) {
        this.colour = colour;
        // really crappy - should fix this!!
        if(this.colour == 'b') {
            this.opposingColour = 'w';
        }
        if(this.colour == 'w') {
            this.opposingColour = 'b';
        }
        
        
    }
    
            public int[] scanPiece (DraughtBoard board,int x,int y)
            {
                int position[] = new int[2];

                //define position to x and y
                position[0]=x;
                position[1]=y;
                            
                char tmpPiece = ' ';
                int i = 0; int j = 0;
                //Do a loop to find a white piece
                for(j=position[0]; j<=7; j++) {
                    for(i=position[1]; i<=7; i++) {
                        tmpPiece = board.getPiece(i,j);
                        if(tmpPiece== this.colour)
                        {
                            //If still at same position, no more moves
                            //if(x==i & y==j)
                            //    return null;
                            char direction = ableToMove(board,i,j);
                            
                            if(direction != 'n') {
                                position[0]=i;
                                position[1]=j; 
                                return position;
                            }
                        }
                    }
                    i = 0;
                }
                

                return null;
            }
            
            
            public char ableToMove (DraughtBoard board,int x,int y) {
                boolean ableToMove = true;
                boolean ableToEat = true;
                
                // check it is not going out of bounds...
                if(x+1 >= 7 | x-1 <= 0) {
                    ableToMove = false;
                }
                    
                if(y+1 >= 7 | y-1 <= 0) {
                    ableToMove = false;
                }
                
                if(x+2 >= 7 | x-2 <= 0) {
                    ableToEat = false;
                }

                if(y+2 >= 7 | y-2 <= 0) {
                    ableToEat = false;
                }
                
                // directions we can move in ...
                if(ableToMove == true) {
                    
                    // can we move still...
                    if(board.getPiece(x+1, y+1) == this.opposingColour | board.getPiece(x+1, y+1)== 'e') {
                        if(ableToEat==true & board.getPiece(x+1, y+1) == this.opposingColour)
                            if(board.getPiece(x+2, y+2) == 'e')
                                return 'r';
                            else{ return 'n'; }
                                
                        return 'r';
                    } 
                    
                    if(board.getPiece(x-1, y+1) == this.opposingColour | board.getPiece(x-1, y+1)== 'e') {
                        if(ableToEat==true & board.getPiece(x-1, y+1) == this.opposingColour)
                            if(board.getPiece(x-2, y+2) == 'e')
                                return 'l';
                            else{ return 'n'; }
                        
                        return 'l';
                    }
                    
                    // can we move still...
                    if(board.getPiece(x+1, y-1) == this.opposingColour | board.getPiece(x+1, y-1)== 'e') {
                        if(ableToEat==true & board.getPiece(x+1, y-1) == this.opposingColour)
                            if(board.getPiece(x+2, y-2) == 'e')
                                return 'r';
                            else{ return 'n'; }
                        return 'r';
                    } 
                    
                    if(board.getPiece(x-1, y-1) == this.opposingColour | board.getPiece(x-1, y-1)== 'e') {
                        if(ableToEat==true & board.getPiece(x-1, y-1) == this.opposingColour)
                            if(board.getPiece(x-2, y-2) == 'e')
                                return 'r';
                            else{ return 'n'; }
                        return 'l';
                    }
                      
                }
                
               
                return 'n';

            }
            
            public boolean ableToEat (DraughtBoard board,int x,int y, char direction) {
                
                boolean ableToEat=true;
                

                if(x+1 > 7 | x-1 < 0) 
                    ableToEat = false;
                                   
                if(y+1 > 7 | y-1 < 0) 
                    ableToEat = false;                
                
                if(x+2 > 7 | x-2 < 0) 
                    ableToEat = false;
                
                if(y+2 > 7 | y-2 < 0) 
                    ableToEat = false;
                     
                 
                
                if (ableToEat==true)
                {
                    if(board.getPiece(x+1, y+1) == this.opposingColour & board.getPiece(x+2, y+2) == 'e') 
                        return true; 

                    if(board.getPiece(x-1, y+1) == this.opposingColour & board.getPiece(x-2, y+2) == 'e') 
                        return true; 
                    
                    if(board.getPiece(x+1, y-1) == this.opposingColour & board.getPiece(x+2, y-2) == 'e') 
                        return true; 
                    
                    if(board.getPiece(x-1, y-1) == this.opposingColour & board.getPiece(x-2, y-2) == 'e') 
                        return true; 
                }
                
                return false;
            }
    
          
            public int movePiece(DraughtBoard board)

            {
                
                System.out.print(this.getClass().getSimpleName());
                
                boolean pieceCannotEat=false;
                boolean pieceAbleToMove=false;
                char directionToMove;
                boolean pieceCannotMove=false;
                boolean pieceAbleToEat=false;
                int x=0, y=0;
                int position[] = new int[2];
                
                //assume WhitePlayer
                //Check if any available pieces can eat an opposing piece - loop till cannot find piece to eat
                do
                {
                   
                    position = scanPiece(board,x,y);
                    
                //if scanned all objects and cannot eat
                    if(position==null)
                        pieceCannotEat=true;
                    
                    else
                    {
                    x=position[0];
                    y=position[1];
                    }
                    
                    directionToMove = ableToMove(board,x,y);
                    
                    if (directionToMove=='n')
                    {
                        pieceAbleToMove=false;
                        position = scanPiece(board,x,y);
                    }
                    else
                        pieceAbleToMove=true;
                    
                    //Needs to Check if can eat the piece.
                    if (directionToMove!='n')
                    {
                        pieceAbleToEat = ableToEat(board,x,y,directionToMove);
                        
                        if(pieceAbleToEat==true)
                        {
                            if(this.colour == 'w') 
                            {
                                if(directionToMove=='r')
                                {
                                board.removePiece(x,y);
                                board.moveWhite(x+1, y+1, directionToMove);
                                }
                                
                                if(directionToMove=='l')
                                {
                                board.removePiece(x,y);
                                board.moveWhite(x-1, y+1, directionToMove);
                                }                               
                            } 

                            if(this.colour == 'b') 
                            {
                                if(directionToMove=='r')
                                {
                                board.removePiece(x,y);
                                board.moveBlack(x+1, y-1, directionToMove);
                                }
                                
                                if(directionToMove=='l')
                                {
                                board.removePiece(x,y);
                                board.moveBlack(x-1, y-1, directionToMove);
                                }                               
                            }
                            //System.out.print("player: "+ this.colour +" eating at x:" + x + "y:" + y + "y:" + directionToMove);
                        }
                        else
                        {

                            if (pieceAbleToMove==true)
                            {
                                if(this.colour == 'w') 
                                {
                                    board.moveWhite(x, y, directionToMove);
                                } 

                                if(this.colour == 'b') 
                                {
                                    board.moveBlack(x, y, directionToMove);
                                }

                            }
                        }
                    }
                    
                    
/*                    if (pieceAbleToMove==true)
                    {
                        if(this.colour == 'w') 
                        {
                            board.moveWhite(x, y, directionToMove);
                        } 
                        
                        if(this.colour == 'b') 
                        {
                            board.moveBlack(x, y, directionToMove);
                        }
                       
                    }
*/                    
                    
                
                } while (pieceCannotEat==true);
                
                
                
                if (pieceCannotMove==true)
                    System.out.println("No available piece can be moved");

                
                
                return 0;
            }
            
            

}
