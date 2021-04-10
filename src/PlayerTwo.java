import java.util.Scanner;

/**
 * Aceasta clasa o sa implementeze metode care realizeaza miscarile pe care al doilea jucator (jucatorul galben) le poate face
 */
public class PlayerTwo extends Board{

    /**
     * Metoda prin intermediul careia plasez o piesa galbena
     * @param column reprezinta coloana pe care jucatorul doreste sa plaseze piesa galbena de joc
     * @return metoda returneaza numarul randului pe care se afla piesa de joc abia plasata pe coloana column primita ca parametru
     */
    public static int dropYellow(int column){

        int level = 1;

        System.out.println("Randul jucatorului 2: ");

        while(true){

            if(column > width){
                System.out.println("Aceasta coloana nu exista!");
                break;
            }

            if(board[bottomRow][column] == '.'){
                board[bottomRow][column]='Y';
                return bottomRow;
            }
            else
            {
                if(board[bottomRow][column] == 'R' || board[bottomRow][column] == 'Y')
                    if(board[bottomRow-level][column] == '.'){
                        board[bottomRow-level][column] = 'Y';
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
     * Metoda prin intermediul careia verific daca exista o succesiune de 4 piese galbene pe orizontala
     * @return metoda returneaza 1 daca s-a gasit o astfel de succesiune si 0 in caz contrar
     */
    public static int checkYellowHorizontally(){

        int connect=0;

        for(int i=0; i<height; i++)
        {
            int numberOfDotsConnected=0;
            for(int j=0; j<width; j++)
            {
                if(board[i][j] == 'Y')
                {
                    numberOfDotsConnected++;
                    if(numberOfDotsConnected == 4)
                    {
                        System.out.println("Jucatorul 2 a castigat!");
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
     * Metoda prin intermediul careia verific daca exista o succesiune de 4 piese galbene pe verticala
     * @return metoda returneaza 1 daca s-a gasit o astfel de succesiune si 0 in caz contrar
     */
    public static int checkYellowVertically(){

        int connect=0;

        for(int i=0; i<width; i++)
        {
            int numberOfDotsConnected=0;
            for(int j=0; j< height; j++)
            {
                if(board[j][i] == 'Y')
                {
                    numberOfDotsConnected++;
                    if(numberOfDotsConnected == 4)
                    {
                        System.out.println("Jucatorul 2 a castigat!");
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
     * Metoda prin intermediul careia verific daca exista o succesiune de 4 piese galbene pe diagonala
     * @return metoda returneaza 1 daca s-a gasit o astfel de succesiune si 0 in caz contrar
     */
    public static int checkFirstYellowDiagonal(){

        int connect=0;

        for(int i=5; i>2; i--)
        {
            for(int j=3; j<width; j++)
            {
                int numberOfDotsConnected=0;
                if(board[i][j] == 'Y')
                {
                    numberOfDotsConnected++;
                    int level = 1;
                    while(level != 4)
                    {
                        if(board[i-level][j-level] == 'Y')
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
                    System.out.println("Jucatorul 2 a castigat!");
                    break;
                }
            }

            if(connect == 1)
                break;
        }
        return connect;
    }

    /**
     * Metoda prin intermediul careia verific daca exista o succesiune de 4 piese galbene pe diagonala
     * @return metoda returneaza 1 daca s-a gasit o astfel de succesiune si 0 in caz contrar
     */
    public static int checkSecondYellowDiagonal(){

        int connect = 0;

        for(int i=5; i>=3; i--)
        {
            for(int j=0; j<=3; j++)
            {
                int numberOfDotsConnected=0;
                if(board[i][j]=='Y')
                {
                    numberOfDotsConnected++;
                    int level=1;
                    while(level != 4)
                    {
                        if(board[i-level][j+level] == 'Y')
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
                    System.out.println("Jucatorul 2 a castigat!");
                    break;
                }
            }
            if(connect == 1)
                break;
        }
        return connect;
    }
}
