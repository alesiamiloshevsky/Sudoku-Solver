import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SudokuGUI {
    static class GUI {
        public void setUp() {
            JFrame frame = new JFrame("Sudoku");
            JTextField[][] grid = new JTextField[9][9];
            JPanel panel = new JPanel();
            JPanel boardPanel = new JPanel();
            JButton solve = new JButton("Solve");
            JButton clear = new JButton("Clear");

            int[][] board = new int[9][9];

            frame.add(panel);
            panel.add(boardPanel);
            boardPanel.setLayout(new GridLayout(9, 9));

            solve.setFont(solve.getFont().deriveFont(30f));
            clear.setFont(clear.getFont().deriveFont(30f));

            panel.add(solve);
            panel.add(clear);

            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    grid[i][j]=new JTextField(2);
                    grid[i][j].setFont(grid[i][j].getFont().deriveFont(35f));
                    boardPanel.add(grid[i][j]);
                }
            }

            clear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i = 0; i < 9; i++){
                        for(int j = 0; j < 9; j++){
                            grid[i][j].setText("");
                        }
                    }
                }
            });

            solve.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                int len = grid[i][j].getText().length();
                                if (len > 1) {
                                    throw new Exception();
                                } else if (grid[i][j].getText().equals(".") || grid[i][j].getText().equals(" ") || grid[i][j].getText().equals("")) {
                                    board[i][j] = 0;
                                } else {
                                    board[i][j] = Integer.parseInt(grid[i][j].getText());
                                }
                            }
                        }

                        int[][] finalBoard = SudokuSolver.solver(board);
                        for(int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                int num = finalBoard[i][j];
                                grid[i][j].setText(num + "");
                            }
                        }
                    }
                    catch(Exception exception) {
                        System.out.println(exception);
                        JOptionPane.showMessageDialog(null, "Invalid Input");
                    }
                }
            });

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 560);
            frame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setUp();
    }
}
