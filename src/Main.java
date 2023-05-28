public class Main {
    public static void main(String[] args) {
        if (args.length < 1)
        {
            System.out.println("\nNo name provided; please provide a name.\n");
            System.out.println("\tUSAGE: SingleArgMain <name>");
        }
        else
        {
            System.out.println("Hello " + args[0] + "!");
        }
    }
}