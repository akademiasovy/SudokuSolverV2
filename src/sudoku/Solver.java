package sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solver {
    private boolean zmena;
    private Gameplan gameplan;
    private List<Gameplan> stack;

    public Solver (Gameplan gameplan) {
        this.gameplan = gameplan;
        zmena=false;
        stack=new ArrayList<>();
    }

    public void solve() {
        zmena=false;
        if (this.gameplan.isSolved()) return;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (this.gameplan.getTile(row,col).isFree()) {
                    Set<Integer> taken = new HashSet<>();
                    for (Tile t : this.gameplan.getRow(row).getTileList()) {
                        if (!t.isFree())
                            taken.add(t.getValue());
                    }
                    for (Tile t : this.gameplan.getColumn(col).getTileList()) {
                        if (!t.isFree())
                            taken.add(t.getValue());
                    }
                    for (Tile t : this.gameplan.getSquareFromTile(row, col).getTileList()) {
                        if (!t.isFree())
                            taken.add(t.getValue());
                    }

                    if (taken.size() == 8) {
                        for (int i = 1; i <= 9; i++) {
                            if (!taken.contains(i)) {
                                System.out.println("row="+row+" | col="+col+" | val="+i);
                                this.gameplan.getTile(row,col).setValue(i);
                                this.gameplan.getTile(row,col).setFree(false);
                                zmena=true;
                                break;
                            }
                        }
                    } else if (taken.size() == 9 && !stack.isEmpty()) {
                        gameplan=stack.get(stack.size()-1);
                        stack.remove(gameplan);
                    } else if(zmena==false && taken.size()==7){
                        Gameplan gcopy=gameplan.deepCopy();
                        int a = -1;
                        int b = -1;
                        for (int i = 1; i <= 9; i++) {
                            if (!taken.contains(i)) {
                                if (a == -1) {
                                    a = i;
                                } else {
                                    b = i;
                                }

                            }
                        }
                            System.out.println(" >>>>>>>>>>>> a="+a+"b="+b);
                            gcopy.getTile(row,col).setValue(a);
                            stack.add(gcopy);
                            gameplan.getTile(row,col).setValue(b);


                    }

                }

            }
        }

    }

}