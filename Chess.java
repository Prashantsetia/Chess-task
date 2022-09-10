import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

class ChessGame{

    private class Player{
        String pname;
        int pnumber;
        int row;
        int col;
    }

    ArrayList<ArrayList<Player>> bd = new ArrayList<>(5);

    ChessGame(){
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<5;i++){
            bd.add(new ArrayList<Player>(5));
        }

        System.out.println("Enter the player with position");

        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                Player p = new Player();
                
                if(i==0){
                    p.pname = "A";
                    System.out.println("Enter the player number you want to place in (" + i + "," +  j + ")");
                    p.pnumber = sc.nextInt();
                    p.row = i;
                    p.col = j;
                    ArrayList<Player> al = this.bd.get(i);
                    al.add(p);                 
                }
                else if(i==4){
                    System.out.println("================================");
                    System.out.println("TURN FOR PLAYER B");
                    System.out.println("================================");
                    p.pname = "B";
                    System.out.println("Enter the player number you want to place in (" + i + "," +  j + ")");
                    p.pnumber = sc.nextInt();
                    p.row = i;
                    p.col = j;
                    ArrayList<Player> al = this.bd.get(i);
                    al.add(p);
                }
                else{
                    p.pname = "X";
                    p.pnumber = 0;
                    p.row = i;
                    p.col = j;
                    ArrayList<Player> al = this.bd.get(i);
                    al.add(p);
                }
            }
        }
    }
    
    public void display(){
        for(int i=0;i<5;i++){
            for(int j=0;j<this.bd.size();j++){
                Player p = bd.get(i).get(j);
                if(p.pnumber==0){
                    System.out.print("---- ");
                }
                else{
                    System.out.print(p.pname + "-P" + p.pnumber + " ");
                }
            }
            System.out.println();
        }
    }

    public int playerLeft(){
        int count = 0;
        for(int i=0;i<bd.size();i++){
            for(int j=0; j<bd.size();j++){
                Player p = bd.get(i).get(j);
                if(p.pnumber!=0){
                    count++;
                }
            }
        }

        return count;
    }

    public void moveF(ArrayList<ArrayList<Player>> bd, int pnumber,String pname){
        Player p=null;
        int flag = 0;
        for(int i=0;i<bd.size();i++){
            for(int j=0;j<bd.size();j++){
                p = bd.get(i).get(j);
                if(p.pname==pname && p.pnumber==pnumber){
                    flag=1;
                    break;
                }
            }

            if(flag==1){
                break;
            }
        }

        int r = p.row;
        int c = p.col;

        System.out.println(r + " | " + c);
        if(pname=="A"){

            if(r<4){
                r++;
                Player pl  = bd.get(r).get(c);
                pl.pname = pname;
                pl.pnumber = pnumber;
                
            }
            
            p.pname = "";
            p.pnumber = 0;
        }
        else{
            if(r>0){
                r--;
                Player pl  = bd.get(r).get(c);
                pl.pname = pname;
                pl.pnumber = pnumber;
            }
            
            p.pname = "";
            p.pnumber = 0;
        }
    }


    public void moveB(ArrayList<ArrayList<Player>> bd, int pnumber,String pname){
        Player p=null;
        int flag = 0;
        for(int i=0;i<bd.size();i++){
            for(int j=0;j<bd.size();j++){
                p = bd.get(i).get(j);
                if(p.pname==pname && p.pnumber==pnumber){
                    flag=1;
                    break;
                }
            }

            if(flag==1){
                break;
            }
        }

        int r = p.row;
        int c = p.col;

        System.out.println(r + " | " + c);
        if(r>0){
            r--;
            Player pl  = bd.get(r).get(c);
            pl.pname = pname;
            pl.pnumber = pnumber;

        }

        p.pname = "";
        p.pnumber = 0;
    }

    public void start(){
        Scanner sc = new Scanner(System.in);
        int turn = 0;
        while(playerLeft()!=0){
            if(turn%2==0){

                System.out.println("Player A's Move: Enter the player number and move you want to choose");
                int pnum = sc.nextInt();
                char ch = sc.next().charAt(0);
                switch(ch){
                    case 'F':
                        moveF(bd, pnum, "A");
                        this.display();
                    break;
                    case 'B':
                    moveB(bd, pnum, "A");
                    this.display();
                    break;
                }
                
            }
            else{
                System.out.println("Player B's Move: Enter the player number and move you want to choose");
                int pnum = sc.nextInt();
                char ch = sc.next().charAt(0);
                switch(ch){
                    case 'F':
                        moveF(bd, pnum, "B");
                        this.display();
                    break;
                    case 'B':
                    moveB(bd, pnum, "B");
                    this.display();
                    break;
                }
            }

            turn++;
        }
    }
}

public class Chess {
    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        game.display();

        game.start();
    }
}
