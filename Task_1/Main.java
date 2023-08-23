
import java.util.*;
public class Main {
    public static void main(String[] args){
        // range 1 to 100N
        int low=1,high=100;
        Random rand=new Random();
        boolean play=true;

        
        while(play){
            Scanner sc=new Scanner(System.in);
            int num=rand.nextInt(high);
            int user=0,limit=5;
            
            System.out.println("\n------------------------ Guess The number between 1-100 ------------------------");
                //Game
                while(user!=num){
                    
                     System.out.println("Attempts left " +limit+ "!:\n");
                    if(limit==0){
                        System.out.println("********************************* You Lossed number is "+num+" *****************************");
                         break;
                        
                    }
                    user=sc.nextInt();
                    if(user<=0 || user>=100){
                        System.out.println("Invalid Range "+low+"-"+high);
                        continue;
                    }
                    if(user==num){
                        System.out.println("********************************* You win number is "+num+" *****************************");
                        System.out.println("Press Q to exit game or Press Enter to continue\n");
                        Scanner input = new Scanner(System.in);
                        
                        String i = input.nextLine();
                        if(i.equalsIgnoreCase("q")){
                            play=false;
                            break;
                        }
                    }
                    if(user>num)System.out.println("Try to guess smaller number");
                    if(user<num)System.out.println("Try to guess bigger number");
                    limit--;
        
                }
        }
        
    }
}
