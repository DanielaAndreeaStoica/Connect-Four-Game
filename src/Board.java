/**
 * Aceasta clasa construieste tabla de joc
 */
public class Board {
    //dimensiunile tablei de joc
    final static int height = 6;
    final static int width = 7;
    final static int bottomRow = height - 1;
    //tabla de joc
    static char[][] board = new char[height][width];

    /**
     * Metoda care creeaza tabla de joc si umplu toate casutele din matrice cu un punct, asta semnificand faptul ca acele casute sunt goale
     */
    public static void createBoard(){
        for(int i=0;i<height;i++)
            for(int j=0;j<width;j++)
                board[i][j]='.';
    }

    /**
     * Metoda care afiseaza tabla de joc in consola
     */
    public static void printBoard(){
        for(int i=0;i<height;i++) {
            for (int j = 0; j < width; j++)
                System.out.print(board[i][j]);
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Metoda care verifica daca tabla de joc este plina
     * @return metoda returneaza 1 daca tabla de joc este plina si 0 in caz contrar
     */
    public static int isFull(){
        int full = 1;
        for(int i=0;i<height;i++) {
            for (int j = 0; j < width; j++)
                if (board[i][j] == '.') {
                    full = 0;
                    break;
                }
            if(full == 0)
                break;
        }
        return full;
    }
}
