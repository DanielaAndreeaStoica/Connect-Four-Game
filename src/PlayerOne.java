import java.util.Random;
import java.util.Scanner;

/**
 * Aceasta clasa o sa implementeze metode care realizeaza miscarile pe care primul jucator (jucatorul rosu) le poate face
 */
public class PlayerOne extends Board {

    /**
     *Metoda prin intermediul careia plasez o piesa rosie
     * @param column reprezinta coloana pe care jucatorul doreste sa plaseze piesa rosie de joc
     * @return metoda returneaza numarul randului pe care se afla piesa de joc abia plasata pe coloana column primita ca parametru
     */
    public static int dropRed(int column){

        int level = 1;

        System.out.println("Randul jucatorului 1: ");

        while(true){

            if(column > width){
                System.out.println("Aceasta coloana nu exista!");
                break;
            }

            if(board[bottomRow][column] == '.'){
                board[bottomRow][column]='R';
                return bottomRow;
            }
            else
            {
                if(board[bottomRow][column] == 'R' || board[bottomRow][column] == 'Y')
                    if(board[bottomRow-level][column] == '.'){
                        board[bottomRow-level][column] = 'R';
                        return bottomRow-level;
                    }
            }
            level++;
            if (level == height) {
                System.out.println("Aceasta coloana este plina!");
                break;
            }
        }
        return -1;
    }

    /**
     * Metoda prin intermediul careia verific daca exista o succesiune de 4 piese rosi pe orizontala
     * @return metoda returneaza 1 daca s-a gasit o astfel de succesiune si 0 in caz contrar
     */
    public static int checkRedHorizontally(){

        int connect=0;

        for(int i=0; i<height; i++)
        {
            int numberOfDotsConnected=0;
            for(int j=0; j<width; j++)
            {
                if(board[i][j] == 'R')
                {
                    numberOfDotsConnected++;
                    if(numberOfDotsConnected == 4)
                    {
                        System.out.println("Jucatorul 1 a castigat!");
                        connect=1;
                        break;
                    }
                }
                else
                    numberOfDotsConnected=0;
            }
            if(connect == 1)
                break;
        }
        return connect;
    }

    /**
     * Metoda prin intermediul careia verific daca exista o succesiune de 4 piese rosi pe verticala
     * @return metoda returneaza 1 daca s-a gasit o astfel de succesiune si 0 in caz contrar
     */
    public static int checkRedVertically(){

        int connect=0;

        for(int i=0; i<width; i++)
        {
            int numberOfDotsConnected=0;
            for(int j=0; j< height; j++)
            {
                if(board[j][i] == 'R')
                {
                    numberOfDotsConnected++;
                    if(numberOfDotsConnected == 4)
                    {
                        System.out.println("Jucatorul 1 a castigat!");
                        connect=1;
                        break;
                    }
                }
                else
                    numberOfDotsConnected=0;
            }
            if(connect == 1)
                break;
        }
        return connect;
    }

    /**
     * Metoda prin intermediul careia verific daca exista o succesiune de 4 piese rosi pe diagonala
     * @return metoda returneaza 1 daca s-a gasit o astfel de succesiune si 0 in caz contrar
     */
    public static int checkFirstRedDiagonal(){

        int connect=0;

        for(int i=5; i>2; i--)
        {
            for(int j=3; j<width; j++)
            {
                int numberOfDotsConnected=0;
                if(board[i][j] == 'R')
                {
                    numberOfDotsConnected++;
                    int level = 1;
                    while(level != 4)
                    {
                        if(board[i-level][j-level] == 'R')
                        {
                            numberOfDotsConnected++;
                            level++;
                        }
                        else
                            break;
                    }
                }

                if(numberOfDotsConnected == 4)
                {
                    connect = 1;
                    System.out.println("Jucatorul 1 a castigat!");
                    break;
                }
            }

            if(connect == 1)
                break;
        }
        return connect;
    }

    /**
     * Metoda prin intermediul careia verific daca exista o succesiune de 4 piese rosi pe diagonala
     * @return metoda returneaza 1 daca s-a gasit o astfel de succesiune si 0 in caz contrar
     */
    public static int checkSecondRedDiagonal(){

        int connect = 0;

        for(int i=5; i>=3; i--)
        {
            for(int j=0; j<=3; j++)
            {
                int numberOfDotsConnected=0;
                if(board[i][j]=='R')
                {
                    numberOfDotsConnected++;
                    int level=1;
                    while(level != 4)
                    {
                        if(board[i-level][j+level] == 'R')
                        {
                            numberOfDotsConnected++;
                            level++;
                        }
                        else break;
                    }
                }
                if(numberOfDotsConnected == 4)
                {
                    connect = 1;
                    System.out.println("Jucatorul 1 a castigat!");
                    break;
                }
            }
            if(connect == 1)
                break;
        }
        return connect;
    }
}
