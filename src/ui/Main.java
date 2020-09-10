package ui;

/**
 * Main class to run program
 */
public class Main {

    /**
     * Main method to run program - command line arguments are ignored.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        for (String arg : args) System.out.println("Ignored argument: " + arg);
        MainFrame.getInstance();
    }
}
