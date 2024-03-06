import java.util.*;

public class Main {
    static ArrayList<Integer> playerPos=new ArrayList<>();
    static ArrayList<Integer> cpuPos=new ArrayList<>();

    public static void main(String[] args) {
        char[][] gameBoard={
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '},
        };
        printBoard(gameBoard);
        while(true){
            Scanner sc=new Scanner(System.in);
            System.out.print("Enter Position 1 to 9: ");
            int pos=sc.nextInt();
            while(playerPos.contains(pos)||cpuPos.contains(pos)){
                System.out.print("Enter Correct Position: ");
                pos=sc.nextInt();
            }
            placePiece(gameBoard,pos,"player");
            String res=checkWinner();
            if(res.length()>0){
                System.out.println(res);
                break;
            }
            Random rand=new Random();
            int cpuPosition=rand.nextInt(9)+1;
            while(cpuPos.contains(cpuPosition)|| playerPos.contains(cpuPosition)){
                cpuPosition= rand.nextInt(9)+1;
            }
            placePiece(gameBoard,cpuPosition,"cpu");
            printBoard(gameBoard);
            res=checkWinner();
            if(res.length()>0){
                System.out.println(res);
                break;
            }

        }
    }
    public static void printBoard(char[][] gameBoard){
        for(char[] row:gameBoard){
            for(char c:row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard,int pos,String user){
        char symbol='O';
        if(user.equals("player")){
            symbol='X';
            playerPos.add(pos);
        }
        else{
            cpuPos.add(pos);
        }
        switch (pos){
            case 1:
                gameBoard[0][0]=symbol;
                break;
            case 2:
                gameBoard[0][2]=symbol;
                break;
            case 3:
                gameBoard[0][4]=symbol;
                break;
            case 4:
                gameBoard[2][0]=symbol;
                break;
            case 5:
                gameBoard[2][2]=symbol;
                break;
            case 6:
                gameBoard[2][4]=symbol;
                break;
            case 7:
                gameBoard[4][0]=symbol;
                break;
            case 8:
                gameBoard[4][2]=symbol;
                break;
            case 9:
                gameBoard[4][4]=symbol;
                break;
        }
    }

    public static String checkWinner(){
        List<Integer> topRow= Arrays.asList(1,2,3);
        List<Integer> midRow= Arrays.asList(4,5,6);
        List<Integer> bottomRow= Arrays.asList(7,8,9);
        List<Integer> firstCol= Arrays.asList(1,4,7);
        List<Integer> midCol= Arrays.asList(2,5,8);
        List<Integer> endCol= Arrays.asList(3,6,9);
        List<Integer> leftDiagonal= Arrays.asList(1,5,9);
        List<Integer> rightDiagonal= Arrays.asList(3,5,7);

        ArrayList<List> winConditions=new ArrayList<>();
        winConditions.add(topRow);
        winConditions.add(midRow);
        winConditions.add(midCol);
        winConditions.add(bottomRow);
        winConditions.add(firstCol);
        winConditions.add(endCol);
        winConditions.add(leftDiagonal);
        winConditions.add(rightDiagonal);

        for(List l:winConditions){
            if(playerPos.containsAll(l)){
                return "Congratulation You Won!!!";
            }
            else if(cpuPos.containsAll(l)){
                return "Sorry PC Wins :)";
            }
            else if(playerPos.size()+cpuPos.size()==9){
                return "Tie!";
            }
        }
        return "";
    }
}