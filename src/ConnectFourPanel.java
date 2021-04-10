import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ConnectFourPanel extends JPanel {

    Board board = new Board();
    JFrame gameFrame;
    BoardPanel boardPanel;
    ImageIcon imagine = new ImageIcon("piesa3.png");
    JButton button;
    JLabel score;

    int nr = 0;
    int scoreR;
    int scoreY;

    /**
     * Constructor unde se realizeaza fereasta pentru joc si care cuprinde elementele care se regasesc pe tabla de joc (butoane, meniu etc.)
     */
    ConnectFourPanel() {
        Board.createBoard();
        gameFrame = new JFrame("Connect Four");

        JMenuBar meniu=new JMenuBar();
        JMenu file=new JMenu("File");
        JMenuItem exitMenu=new JMenuItem("Exit");
        exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenuItem restartGame=new JMenuItem("Restart");
        restartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.setVisible(false);
                new ConnectFourPanel();
            }
        });

        file.add(restartGame);
        file.add(exitMenu);
        meniu.add(file);
        gameFrame.setJMenuBar(meniu);

        gameFrame.setSize(new Dimension(700, 660));
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setLocation(400, 100);
        boardPanel = new BoardPanel();
        gameFrame.add(boardPanel, BorderLayout.CENTER);
        gameFrame.setVisible(true);

        button = new JButton("Reset");
        button.setForeground(Color.BLUE);
        button.setFont(new Font("Ink Free", Font.BOLD, 19));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.setVisible(false);
                new ConnectFourPanel();
            }
        });
        ResetButton rstButton=new ResetButton();
        gameFrame.add(rstButton, BorderLayout.SOUTH);

        score = new JLabel();
        score.setText("      The Red Player's Score: "+scoreR+"             "+"The Yellow Player's Score: "+scoreY);
        score.setFont(new Font("Ink Free", Font.BOLD, 21));
        Border border=new LineBorder(Color.BLACK,2);
        score.setBorder(border);
        Score scr=new Score();
        gameFrame.add(scr,BorderLayout.NORTH);
    }

    /**
     * Clasa prin intermediul careia creez un buton de reset pentru joc
     */
    class ResetButton extends JPanel{

        public ResetButton(){
            super(new BorderLayout());
            add(button);
            setPreferredSize(new Dimension(80,40));
        }
    }

    /**
     * Clasa prin intermediul careia creez un un panou pentru afisarea scorului
     */
    class Score extends JPanel{

        public Score(){
            super(new BorderLayout());
            add(score);
            setBackground(Color.white);
            setPreferredSize(new Dimension(80,40));
        }
    }

    /**
     * Clasa prin intermediul careia creez tabla de joc din interfata grafica
     */
    private class BoardPanel extends JPanel {
        ArrayList<Cerculet> cercuri;

        /**
         * Constructor prin intermediul caruia delimitez cele 6*7 zone ale tablei de joc
         */
        BoardPanel() {
            super(new GridLayout(6,7));
            cercuri = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    Cerculet cerculet = new Cerculet(i, j);
                    cercuri.add(cerculet);
                    add(cerculet);
                }
            }
            setPreferredSize(new Dimension(700, 600));
            validate();
        }
    }

    /**
     * Clasa prin intermediul careia functionarea jocului este posibila
     */
    private class Cerculet extends JPanel {
        int i, j;

        /**
         * Constructor prin intermediul caruia plasez o pieza pe tabla de joc si verific toate conditiile care determina un castigator
         * @param i acest parametru reprezinta indexul liniei pe care se afla o piesa pe tabla de joc
         * @param j acest parametru reprezinta indexul coloanei pe care se afla o piesa pe tabla de joc
         */
        Cerculet(int i, int j) {
            super(new GridBagLayout());
            this.i = i;
            this.j = j;
            createCerc();
            addMouseListener(new MouseListener() {
                /**
                 * Metoda prin intermediul careia se stabileste castigatorul unui joc. Cand se apasa click o piesa de joc este pusa pe tabla. Aici se apeleaza toate metodele care verifica daca cineva a castigat.
                 * @param e
                 */
                @Override
                public void mouseClicked(MouseEvent e) {
                    nr++;
                    System.out.println(nr);

                    int winner = 0;
                    int col = j;
                    System.out.println("col= " + col);
                    if (nr % 2 == 1) {
                        int row = PlayerOne.dropRed(col);
                        if(row == -1)
                        {
                            JOptionPane.showMessageDialog(gameFrame, "This column is full! You just lost your turn!"," ",JOptionPane.WARNING_MESSAGE);
                        }
                        System.out.println("randul " + row);
                        dropColor(Color.red, row);
                        if (PlayerOne.checkRedVertically() == 1 || PlayerOne.checkRedHorizontally() == 1 || PlayerOne.checkFirstRedDiagonal() == 1 || PlayerOne.checkSecondRedDiagonal() == 1) {
                            Board.printBoard();
                            System.out.println("FINAL JOC");
                            scoreR++;
                            score.setText("      The Red Player's Score: "+scoreR+"             "+"The Yellow Player's Score: "+scoreY);
                            int n= JOptionPane.showConfirmDialog(gameFrame,"SCORE: " + scoreR + " - " + scoreY +"   The red player won!   Play again?"," ", JOptionPane.YES_NO_OPTION);
                            if(n == 0)
                            {
                                Board.createBoard();
                                dropWhite();
                                nr = 0;
                            }
                            else
                                System.exit(0);
                            winner = 1;
                        }
                    } else {
                        int row = PlayerTwo.dropYellow(col);
                        if(row == -1)
                        {
                            JOptionPane.showMessageDialog(gameFrame, "This column is full! You just lost your turn!"," ",JOptionPane.WARNING_MESSAGE);
                        }
                        System.out.println("randul " + row);
                        dropColor(Color.yellow, row);
                        if (PlayerTwo.checkYellowHorizontally() == 1 || PlayerTwo.checkYellowVertically() == 1 || PlayerTwo.checkFirstYellowDiagonal() == 1 || PlayerTwo.checkSecondYellowDiagonal() == 1) {
                            Board.printBoard();
                            System.out.println("FINAL JOC");
                            scoreY++;
                            score.setText("      The Red Player's Score: "+scoreR+"             "+"The Yellow Player's Score: "+scoreY);
                            int n= JOptionPane.showConfirmDialog(gameFrame,"SCORE: " + scoreR + " - " + scoreY +"   The yellow player won!   Play again?"," ", JOptionPane.YES_NO_OPTION);
                            if(n == 0)
                            {
                                Board.createBoard();
                                dropWhite();
                                nr = 0;
                            }
                            else
                                System.exit(0);
                            winner = 1;

                        }
                    }
                    if (winner == 0)
                        Board.printBoard();
                    if(Board.isFull() == 1){
                        JOptionPane.showMessageDialog(gameFrame, "No more moves available!"," ",JOptionPane.WARNING_MESSAGE);
                        Board.createBoard();
                        dropWhite();
                        nr = 0;
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }

        /**
         * Metoda prin intermediul careia creez design-ul tablei de joc. Aici pun o imagine in fiecare dintre cele 6x7 zone ale tablei.
         */
        private void createCerc() {
            setBackground(Color.white);
            add(new JLabel(imagine));
        }

        /**
         * Metoda prin intermediul careia "colorez" piesa de joc
         * @param c parametrul c reprezinta culoarea pe care o va avea piesa de joc abia plasata pe tabla
         * @param row parametrul row indica randul pe care se afla piesa de joc abia plasata pe tabla
         */
        private void dropColor(Color c, int row) {
            for (Cerculet cerculet : boardPanel.cercuri) {
                if (cerculet.i == row && cerculet.j == this.j)
                    cerculet.setBackground(c);
            }
        }

        /**
         * Metoda prin intermediul careia golesc tabla de joc de piesele care exista pe ea
         */
        private void dropWhite(){
            for(Cerculet cerculet : boardPanel.cercuri)
                cerculet.setBackground(Color.white);

        }
    }

}