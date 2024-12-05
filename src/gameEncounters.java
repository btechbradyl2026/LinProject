import java.util.Scanner;

public class gameEncounters {

    private gameCharacters enemy;
    private gameCharacters chara;

    Scanner scan = new Scanner(System.in);
    private int encounter = 0;
    private int turns = 0;

    public gameEncounters(gameCharacters x) {
        chara = x;
        encounter = (int) (Math.random() * 10);
        encounter = turns + encounter;
        chara.infoCharacter();
        shop();
        encounters();
    }
    public void encounters(boolean death) {
        if (death) {
            encounter = 15;
        }
        encounters();
    }
    public void encounters() {
        if (encounter == 1) {
            shop();
        } else if (encounter == 2) {
            spawn(1);
        } else if (encounter == 3) {
            spawn(1);
        } else if (encounter == 4) {
            spawn(1);
        } else if (encounter == 5) {
            shop();
        } else if (encounter == 6) {
            spawn(1);
        } else if (encounter == 7) {
            spawn(1);
        } else if (encounter == 8) {
            spawn(2);
        } else if (encounter == 9) {
            spawn(2);
        } else if (encounter == 10) {
            shop();
        } else if (encounter == 11) {
            spawn(2);
        } else if (encounter == 12) {
            spawn(1);
        } else if (encounter == 13) {
            spawn(2);
        } else if (encounter == 14) {
            shop();
        } else if (encounter >= 15) {
            dragon();
        }
        encounter = (int) (Math.random() * 10);
        turns ++;
        encounter = turns + encounter;
    }

    public void shop() {
        int selection = 0;
        System.out.println("Welcome to the shop!");
        System.out.println("What would you like to buy?");
        System.out.println("You have rested for a bit and healed " + (turns * 5) + " health");
        chara.damage(-(turns * 5));
        System.out.println("Sword 10 gp, big sword 20 gp, very very big sword 30 gp, (skip)");
        selection = scan.nextInt();
        if (selection == 1) {
            if (chara.getGp() >= 10) {
                chara.updateCharacters(1);
                chara.gold(-10);
            } else {
                System.out.println("Insufficient funds");
            }
        } else if (selection == 2) {
            if (chara.getGp() >= 20) {
                chara.updateCharacters(2);
                chara.gold(-20);
            } else {
                System.out.println("Insufficient funds");
            }
        } else if (selection == 3) {
            if (chara.getGp() >= 30) {
                chara.updateCharacters(3);
                chara.gold(-30);
            } else {
                System.out.println("Insufficient funds");
            }
        } else if (selection == 4) {
            System.out.println("Buy gun? 100gp (1 == yes)");
            selection = scan.nextInt();
            if (selection == 1) {
                if (chara.getGp() >= 100) {
                    System.out.println("Bought gun");
                    chara.updateCharacters(4);
                    chara.gold(-100);
                } else {
                    System.out.println("Insufficient Funds");
                }
            }
        }
    }

    public void dragon() {
        System.out.println("The air grows hot around you");
        System.out.println("Your skin begins to dry out");
        System.out.println("As you look above you see it, 2 flapping wings, 4 legs, a reptilian snout");
        System.out.println("There is no doubt about it, you are up against a dragon.");
        System.out.println();
        spawn(3);
    }

    public void spawn(int select) {
        if (select == 1) {
            enemy = new gameCharacters("Bandit", 25 + (turns * 5));
        } else if (select ==2) {
            enemy = new gameCharacters("Monster", 50  + (turns * 5));
        } else if (select == 3) {
            enemy = new gameCharacters("Dragon", 100 + (turns * 5));
        }
        fight(enemy);
    }

    public void fight(gameCharacters enemy) {
        if (enemy.getName().equals("Bandit")) {
            System.out.println("An average bandit stands before you");
            battle();
        } else if (enemy.getName().equals("Monster")) {
            System.out.println("A Strong Monster stands before you");
            battle();
        } else if (enemy.getName().equals("Dragon")) {
            battle();
        }
    }

    public void battle() {
        int d = 0;
        int sel = 0;
        while (enemy.isAlive()) {
            System.out.println("1. Attack, 2. Enemy info, 3. Player Info");
            System.out.println();
            sel = scan.nextInt();
            if (!chara.isAlive()) {
                System.out.println("You died...");
                System.exit(1);
            }
            if (sel == 1) {
                if (chara.characterWeapon() == 4) {
                    enemy.damage(999);
                    d = 999;
                } else if (chara.characterWeapon() == 2) {
                    d = 15 + (turns * 3);
                    enemy.damage(d);
                } else if (chara.characterWeapon() == 3) {
                    d = 20 + (turns * 4);
                    enemy.damage(d);
                } else if (chara.characterWeapon() == 1) {
                    d = 10 + (turns * 2);
                    enemy.damage(d);
                } else if (chara.characterWeapon() == 5) {
                    enemy.damage(5 + turns);
                    d = 5 + turns;
                }
                System.out.println("You attacked the " + enemy.getName() + " for " + d + " damage!");
                enemyTurn(enemy.getName());
                if (!chara.isAlive()) {
                    System.out.println("You died...");
                    System.exit(1);
                }
            } else if (sel == 2) {
                System.out.println("The " + enemy.getName() + " has " + enemy.getHp() + " hp left");
            } else if (sel == 3) {
                chara.infoCharacter();
            }
        }
        System.out.println("You defeated the enemy!");
        int gain = 0;
        if (enemy.getName().equals("Bandit")) {
            gain =  5 + turns + (int) (Math.random() * 11) + 5;
            System.out.println("You have gotten " + gain + " gold");
            chara.gold(gain);
            chara.damage(-10);
        } else if (enemy.getName().equals("Monster")) {
            gain = 10 + turns + (int) (Math.random() * 11) + 10;
            System.out.println("You have gotten " + gain + " gold");
            chara.gold(gain);
            chara.damage(-20);
        } else if (enemy.getName().equals("Dragon")) {
            System.out.println("You have slain the dragon!");
            System.out.println("You've won the game!");
            chara.damage(-999);
            System.exit(1);
        }
    }

    public void enemyTurn(String e) {
        if (enemy.getName().equals("Bandit")) {
            System.out.println("The Bandit attacks you for " + (2 + turns) + " damage!");
            chara.damage(2 + turns);
        } else if (enemy.getName().equals("Monster")) {
            System.out.println("The Monster attacks you for " + (5 + turns) + " damage!");
            chara.damage(5 + turns);
        } else if (enemy.getName().equals("Dragon")) {
            System.out.println("The Dragon attacks you for " + (20 + turns) + " damage!");
            chara.damage(20 + turns);
        }
    }
}
