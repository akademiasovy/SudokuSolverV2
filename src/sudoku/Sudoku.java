package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Sudoku extends Canvas {

    public static final int WIDTH = 600, HEIGHT = 600;

    private JFrame frame;
    private Gameplan gameplan;
    private Solver solver;

    public Sudoku() {
        this.setMinimumSize(new Dimension(WIDTH,HEIGHT));
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH,HEIGHT));

        this.initFrame();
        this.gameplan = new Gameplan();
        this.solver = new Solver(this.gameplan);
        this.run();
    }

    private void initFrame() {
        this.frame = new JFrame();

        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setTitle("SudokuSolverV2");

        this.frame.add(this);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);

        this.frame.setVisible(true);
    }

    public void run() {
        new Timer(1000/60, (e)->{
           this.tick();
           this.render();
        }).start();
    }

    public void tick() {
        this.solver.solve();
//        this.gameplan.print();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH,HEIGHT);

        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(24f));
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (!this.gameplan.getTile(row,column).isFree())
                    g.drawString(String.valueOf(this.gameplan.getTile(row,column).getValue()), 100+column*50, 100+row*50);
            }
        }

        g.dispose();
        bs.show();
    }

}
