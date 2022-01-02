package GameOfLife;

public class Cell {

    private boolean isAlive;

    public Cell(){
        isAlive = false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}