import java.util.Scanner;
import java.util.Random;
/**
 * Client code for for VendingMachine classes.
 *
 * @author yoyoyoyo
 * @version 1.00
 */
public class VendingWorld {
    /**
     * Runner
     * @param args Program arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder clear = new StringBuilder();



        int desiredWins;
        System.out.print("Points to win: 3");
        desiredWins=3;
        
        int playerWins=0;
        int computerWins=0;
        String userMove;
        
        
        while((playerWins<desiredWins) && (computerWins<desiredWins))   
        {
            Random r = new Random();
            int rr = r.nextInt(10);
            String computerMove = "rock";

            if (rr < 3) {
                computerMove = "paper";
            } else if (rr < 7) {
                computerMove = "scissors";
            }
            System.out.print("Rock, Paper or Scissors? ");
            userMove=sc.next();
            {
            if (!((userMove.equalsIgnoreCase("rock")) || (userMove.equalsIgnoreCase("paper")) || (userMove.equalsIgnoreCase("scissors"))))
                System.out.println("Please choose 'rock', 'paper', or 'scissors'!");
            
            if(computerMove.equalsIgnoreCase("rock") && userMove.equalsIgnoreCase("paper"))
                {
                playerWins++;
                System.out.println("The computer chose rock, so you win! ("+playerWins+"-"+computerWins+")");
                }
            else if (computerMove.equalsIgnoreCase("rock") && userMove.equalsIgnoreCase("scissors"))
                {
                computerWins++;
                System.out.println("The computer chose rock, so you lose. ("+playerWins+"-"+computerWins+")");
                }
            else if (computerMove.equalsIgnoreCase("rock") && userMove.equalsIgnoreCase("rock"))
                {
                System.out.println("The computer chose rock, so it's a tie. ("+playerWins+"-"+computerWins+")");
                }
            else if (computerMove.equalsIgnoreCase("paper") && userMove.equalsIgnoreCase("scissors"))
                {
                playerWins++;
                System.out.println("The computer chose paper, so you win! ("+playerWins+"-"+computerWins+")");
                }
            else if (computerMove.equalsIgnoreCase("paper") && userMove.equalsIgnoreCase("rock"))
                {
                computerWins++;
                System.out.println("The computer chose paper, so you lose. ("+playerWins+"-"+computerWins+")");
                }
            else if (computerMove.equalsIgnoreCase("paper") && userMove.equalsIgnoreCase("paper"))
                {
                System.out.println("The computer chose paper, so it's a tie. ("+playerWins+"-"+computerWins+")");
                }
            else if (computerMove.equalsIgnoreCase("scissors") && userMove.equalsIgnoreCase("rock"))
                {
                playerWins++;
                System.out.println("The computer chose scissors, so you win! ("+playerWins+"-"+computerWins+")");
                }
            else if (computerMove.equalsIgnoreCase("scissors") && userMove.equalsIgnoreCase("paper"))
                {
                computerWins++;
                System.out.println("The computer chose scissors, so you lose. ("+playerWins+"-"+computerWins+")");
                }
            else if (computerMove.equalsIgnoreCase("scissors") && userMove.equalsIgnoreCase("scissors"))
                {
                System.out.println("The computer chose scissors, so it's a tie. ("+playerWins+"-"+computerWins+")");
                }
            }
        }
        boolean omar;
        if (playerWins>computerWins) {
            System.out.println("Congratulations! You won!");
            System.out.println("You can now choose from the vending machine!!!");
            omar = true;
        }
        else {
            System.out.println("Sorry, you lost. Better luck next time!");
            omar = false;
        }
        
    
        if (omar) {
            for (int i = 0; i < 100; i++) {
                clear.append("\n");
            }
            VendingMachine culc = new VendingMachine();
            VendingMachine coc = new VendingMachine();
            VendingMachine current = null;
            System.out.println(clear);
            do {
                System.out.println("Which machine would you like to visit?");
                System.out.println("(1) Market Place");
                System.out.println("(2) Space Ship");
                System.out.println("(3) Quit");

                String choice = sc.nextLine();

                if (choice.equals("1")) {
                    current = culc;
                } else if (choice.equals("2")) {
                    current = coc;
                } else if (choice.equals("3")) {
                    System.exit(0);
                } else {
                    System.out.println(clear);
                    System.out.println("Lets Enter the vending machine!");
                    continue;
                }
                System.out.println(clear);

                String item;
                do {
                    System.out.println(current);
                    System.out.println("Enter your choice (or type 'back' to go"
                        + " back to the main menu or"
                        + " 'restock' to restock the machine):");
                    item = sc.nextLine().trim();
                    if (item.equalsIgnoreCase("restock")) {
                        System.out.println(clear);
                        current.restock();
                        System.out.println("You have restocked the machine.");
                    } else if (!item.equalsIgnoreCase("back")) {
                        System.out.println(clear);
                        VendingItem vendedItem = current.vend(item);
                        System.out.println("You dispensed " + vendedItem);
                        System.out.println("You got more " + vendedItem);


                        if (vendedItem.name().charAt(0) == 't') {
                            System.out.println("choose a planet to travel to");
                            for (int i = 0; i<VendingItem.SolarNames.length; i++) {
                                System.out.println((i+ 1) + ") " + VendingItem.SolarNames[i]);
                            }
                            int newPlanet = sc.nextInt();
                            sc.nextLine();
                            System.out.println("You traveled to " + VendingItem.SolarNames[newPlanet - 1]);
                        }

                    } else {
                        System.out.println(clear);
                    }
                } while (!item.equalsIgnoreCase("back"));
            } while (true);
        }
    }
}
