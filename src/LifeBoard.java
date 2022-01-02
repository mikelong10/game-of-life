package GameOfLife;

import java.awt.*;

public class LifeBoard
{

    private Cell[][] board;
    public static final int ROWS=300, COLS=500, SIZE=10;

    public LifeBoard()
    {

        this.board = new Cell[ROWS][COLS];
        for(int r = 0; r < board.length; r++)
        {
            for(int c = 0; c < board[0].length; c++)
            {
                board[r][c] = new Cell();
            }
        }
    }

    public void draw(Graphics2D g2)
    {
        for(int r = 0; r < board.length; r++)
        {
            for(int c = 0; c < board[0].length; c++)
            {
                g2.setColor(Color.BLACK);
                g2.drawRect(c*SIZE, r*SIZE, SIZE, SIZE);
                if(board[r][c].isAlive())
                {
                    g2.setColor(Color.YELLOW);
                    g2.fillRect(c*SIZE + 1, r*SIZE + 1, SIZE - 1, SIZE - 1);
                }
            }
        }
    }

    //returns how many of the 8 cells around [row][col] are alive
    public int numNeighbors(int row, int col)
    {
        int count = 0;
        for (int r = row - 1; r <= row + 1; r++)
        {
            for (int c = col - 1; c <= col + 1; c++)
            {
                if(isValidCell(r, c))
                {
                    if(board[r][c].isAlive())
                    {
                        count++;
                    }
                }
            }
        }
        if(board[row][col].isAlive())
        {
            count--;
        }
        return count;
    }

    //returns true if [r][c] is a legal index in board, false if not.
    public boolean isValidCell(int r, int c){

        return (r >= 0 && r < board.length && c >= 0 && c < board[0].length);

    }

    /*applies the rules of Life to determine the next board state.
    1. Any live cell with two or three live neighbours survives.
    2. Any dead cell with three live neighbours becomes a live cell.
    3. All other live cells die in the next generation.
        Similarly, all other dead cells stay dead.  */
    public void nextGen(){
        Cell[][] nextBoard = new Cell[board.length][board[0].length];

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                int n = numNeighbors(r, c);
                nextBoard[r][c] = new Cell();
                if(board[r][c].isAlive())
                {
                    if(n == 2 || n == 3)
                    {
                        nextBoard[r][c].setAlive(true);
                    }
                }
                else
                {
                    if(n == 3)
                    {
                        nextBoard[r][c].setAlive(true);
                    }
                }
            }
        }
        board = nextBoard;
    }

    //turns a living cell off, and a dead cell on.
    public void toggleCell(int r, int c)
    {
        board[r][c].setAlive(!board[r][c].isAlive());
    }

    public void insertPattern(int r, int c, String name)
    {
        int[][] pattern = RLE_Reader.getPattern(name);

        for (int row = 0; row < pattern.length; row++)
        {
            for (int col = 0; col < pattern[0].length; col++)
            {
                if(pattern[row][col] == 0)
                {
                    board[r+row][c+col].setAlive(false);
                }
                else
                {
                    board[r+row][c+col].setAlive(true);
                }
            }
        }

    }

}