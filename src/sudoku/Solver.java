package sudoku;

import sudoku.Gameplan;
import sudoku.Tile;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Solver {

    private Gameplan gameplan;

    public Solver (Gameplan gameplan) {
        this.gameplan = gameplan;
    }

    public void solve() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (this.gameplan.getTile(row,col).isFree()) {
                    Set<Integer> taken = new HashSet<>();
                    for (Tile t : this.gameplan.getRow(row).getTileList()) {
                        if (!t.isFree())
                            taken.add(Integer.valueOf(t.getValue()));
                    }
                    for (Tile t : this.gameplan.getColumn(col).getTileList()) {
                        if (!t.isFree())
                            taken.add(Integer.valueOf(t.getValue()));
                    }
                    for (Tile t : this.gameplan.getSquareFromTile(row, col).getTileList()) {
                        if (!t.isFree())
                            taken.add(Integer.valueOf(t.getValue()));
                    }

                    if (taken.size() == 8) {
                        for (int i = 1; i <= 9; i++) {
                            if (!taken.contains(i)) {
                                System.out.println("row="+row+" | col="+col+" | val="+i);
                                this.gameplan.getTile(row,col).setValue(i);
                                this.gameplan.getTile(row,col).setFree(false);
                                break;
                            }
                        }
                    } else if (taken.size() == 7) {
                        System.out.println("row="+row+" | col="+col+" | 2 possible values");
                    }
                }
            }
        }
    }

}
