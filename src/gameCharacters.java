public class gameCharacters {
    private String name;
    private int hp;
    private int gp;
    private boolean alive = true;
    private boolean sword;
    private boolean bigSword;
    private boolean veryVeryBigSword;
    private boolean gun;

    public void updateCharacters(int item) {
        if (item == 1) {
            sword = true;
        } else if (item == 2) {
            bigSword = true;
            sword = false;
        } else if (item == 3) {
            veryVeryBigSword = true;
            bigSword = false;
            sword = false;
        } else if (item == 4) {
            gun = true;
            veryVeryBigSword = false;
            bigSword = false;
            sword = false;
        }
    }

    public gameCharacters(String name, int h) {
        this.name = name;
        hp = h;
    }
    public gameCharacters(String name) {
        if (name.equals("demo")) {
            hp = 50;
            gp = 40;
        }
        this.name = name;
        hp = (int) (Math.random() * 41 + 10);
        gp = (int) (Math.random() * 50 + 1);
        alive = true;
    }

    public void infoCharacter() {
        System.out.println("Name: " + getName());
        System.out.println("Coins: " + gp);
        System.out.println("HP: " + hp);
        if (sword) {
            System.out.println("Character has a sword");
        } else if (bigSword) {
            System.out.println("Character has a big sword");
        } else if (veryVeryBigSword) {
            System.out.println("Character has a very very big sword");
        } else if (gun) {
            System.out.println("Character has a gun");
        } else {
            System.out.println("You do not currently have a weapon");
        }
    }

    public boolean isAlive() {
        if (hp <= 0) {
            alive = false;
        }
        return alive;
    }

    public void damage(int d) {
        hp -= d;
    }

    public void gold(int g) {
        gp += g;
    }

    public int getHp() {
        return hp;
    }

    public int getGp() {
        return gp;
    }

    public String getName() {
        return name;
    }

    public int characterWeapon() {
        if (sword) {
            return 1;
        } else if (bigSword) {
            return 2;
        } else if (veryVeryBigSword) {
            return 3;
        } else if (gun) {
            return 4;
        }
        return 5;
    }
}
