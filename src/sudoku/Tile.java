package sudoku;

public class Tile {

    private boolean free;
    private int value;

    public Tile() {
        this.free = true;
        this.value = 0;
    }

    public Tile(int value) {
        if (value != 0) this.free = false; else this.free = true;
        this.value = value;
        if (this.value < 0) this.value = 0;
        else if (this.value > 9) this.value = 9;
    }

    public void nextVal() {
        if (this.value == 9) {
            this.value = 0;
            this.free = true;
        } else {
            this.value++;
            this.free = false;
        }
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        if(value>0) free=false;
        else free=true;
    }
}
