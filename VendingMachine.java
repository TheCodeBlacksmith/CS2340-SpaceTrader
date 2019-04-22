import java.util.Random;

/**
 * Represents a vending machine that dispenses items
 *
 * @author ashah420
 * @version 1.00
 */
public class VendingMachine {

    private static double totalSales = 0;
    private VendingItem[][][] shelf;
    private int luckyChance;
    private Random rand;

    /**
     * Creates a vending machine with 90 items
     * No arg constructor for VendingMachine
     */
    public VendingMachine() {
        shelf = new VendingItem[6][3][5];
        luckyChance = 0;
        rand = new Random();
        restock();
    }

    /**
     * Dispenses an item from the vending machine from selecteed code
     * Swaps successive items forward
     *
     * @param code string representation of item location
     * @return returns the valid item
     */
    public VendingItem vend(String code) {
            // check that code length is 2
        if (code.length() != 2) {
            System.out.println("Code: " + code + " does not exist");
            return null;
        }
        // check that number is within range
        char z = code.charAt(1);
        int y = z - 48;
        if (y > 3 || y < 1) {
            System.out.println("Code: " + code + " does not exist");
            return null;
        }

        // check that letter is within range
        char letter = code.charAt(0);
        int x = letter - 65;
        if (x > 5 || x < 0) {
            System.out.println("Code: " + code + " does not exist");
            return null;
        }
        // start shuffling and make vended item null
        VendingItem itemBought = shelf[x][y - 1][0];
        if (itemBought == null) {
            System.out.println("No item at " + code);
            return null;
        } else {
            for (int i = 0; i < 4; i++) {
                shelf[x][y - 1][i] = shelf[x][y - 1][i + 1];
            }
            shelf[x][y - 1][4] = null;
        }

        if (free()) {
            System.out.println("You have received this item for free!");
        } else {
            totalSales += itemBought.getPrice();
        }
        return itemBought;
    }

    /**
     * Determines if item being dispensed is free
     *
     * @return true or false depending on probability
     */
    private boolean free() {
        int randNum = rand.nextInt(100) + 1;
        if (randNum <= luckyChance || luckyChance == 100) {
            luckyChance = 0;
            return true;
        } else {
            luckyChance += 1;
            return false;
        }
    }

    /**
     * Fills each spot in the vending machine with a
     * randomly chosen item
     */
    public void restock() {
        VendingItem[] vendingItems = VendingItem.values();
        int numItems = vendingItems.length;
        for (int x = 0; x < shelf.length; x++) {
            for (int y = 0; y < shelf[x].length; y++) {
                for (int z = 0; z < shelf[x][y].length; z++) {
                    int randNum = rand.nextInt(numItems);
                    shelf[x][y][z] = vendingItems[randNum];
                }
            }
        }
    }

    /**
     * Retrieves the total sales collected by all machines
     *
     * @return the total sales collected
     */
    public static double getTotalSales() {
        return totalSales;
    }

    /**
     * Determines the number of items left in the machine
     *
     * @return returns the number of non-null items
     */
    public int getNumberOfItems() {
        int count = 0;
        for (int x = 0; x < shelf.length; x++) {
            for (int y = 0; y < shelf[x].length; y++) {
                for (int z = 0; z < shelf[x][y].length; z++) {
                    if (shelf[x][y][z] != null) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Determines the total value of the non-null items
     * left in the machine
     *
     * @return returns the total value
     */
    public double getTotalValue() {
        double sum = 0;
        for (int x = 0; x < shelf.length; x++) {
            for (int y = 0; y < shelf[x].length; y++) {
                for (int z = 0; z < shelf[x][y].length; z++) {
                    if (shelf[x][y][z] != null) {
                        sum += shelf[x][y][z].getPrice();
                    }
                }
            }
        }
        return sum;
    }

    /**
     * Retrieves the luckychance value
     *
     * @return returns the lucky chance
     */
    public int getLuckyChance() {
        return luckyChance;
    }

    /**
     * Generates a representation of the vending machine with all
     * items represented and gives information about the number of
     * items, total value and total sales
     *
     * @return returns the string represenation of the vending machine
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("----------------------------------------------------------"
            + "------------\n");
        s.append("                            VendaTron 9000                "
            + "            \n");
        for (int i = 0; i < shelf.length; i++) {
            s.append("------------------------------------------------------"
                + "----------------\n");
            for (int j = 0; j < shelf[0].length; j++) {
                VendingItem item = shelf[i][j][0];
                String str = String.format("| %-20s ",
                    (item == null ? "(empty)" : item.name()));
                s.append(str);
            }
            s.append("|\n");
        }
        s.append("----------------------------------------------------------"
            + "------------\n");
        s.append(String.format("There are %d items with a total "
            + "value of $%.2f.%n", getNumberOfItems(), getTotalValue()));
        s.append(String.format("Total sales across vending machines "
            + "is now: $%.2f.%n", getTotalSales()));
        return s.toString();
    }

}