import java.util.Scanner;

public class game {
    Scanner scan = new Scanner(System.in);
    gameCharacters chara;
    gameEncounters encount;
    public game() {

    }
    public void start() {
        System.out.println("Welcome to the game");
        System.out.println("Please name your character and help us slay the dragon!");
        chara = new gameCharacters(scan.nextLine());
        encount = new gameEncounters(chara);
        while (chara.isAlive()) {
            next();
        }
        System.out.println("You died...");
        System.exit(1);
    }

    public void next() {
        System.out.println("What would you like to do? 1: info, 2: next, 3: face off");
        System.out.println();
        int selec = 0;
        selec = scan.nextInt();
        if (selec == 1) {
            chara.infoCharacter();
        } else if (selec == 2) {
            encount.encounters();
        } else if (selec == 3) {
            encount.encounters(true);
        }
    }
}