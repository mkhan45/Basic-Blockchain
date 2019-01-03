import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class blockchainRun {

    static Blockchain blockchain = new Blockchain();
    static Scanner scanner = new Scanner(System.in);
    static UserList users = blockchain.getUsers();

    public static void main(String[] args) {

        while (true) {
            System.out.println("Enter input:");
            String input = scanner.nextLine();

            if (input.equals("-1")) {
                scanner.close();
                break;
            }

            String[] command = input.split(" ");

            switch (command.length) {
            case 2:
                switch (command[0]) {
                case "save":
                    save(command[1]);
                    System.out.println("Saved to " + command[1]);
                    break;
                case "load":
                    load(command[1]);
                    System.out.println("Loading from " + command[1]);
                    break;
                default: // means command is mine or create or error
                    switch(command[1]){
                        case "create":
                            createUser(command[0]);
                            break;
                        case "mine":
                            User u1 = users.getUser(command[0]);
                            execCommand("mine", u1);
                        default:
                            System.out.println("Invalid command");
                    }
                }
                break;
            case 3: //is a give command
                if(users.contains(command[0]) && users.contains(command[1]))
                    execCommand("give", users.getUser(command[0]), users.getUser(command[1]));
                else
                    System.out.println("One or more user doesn't exist");
                break;
            default:
                System.out.println("Invalid command");
            }

            System.out.println(blockchain);
            System.out.println(users);
        }
    }

    public static void execCommand(String cmd, User u1, User u2) { //is kind of stupid and should be removed
        switch (cmd) {
        case "give":
            System.out.println("Amount?");
            String line = scanner.nextLine();
            double value = Double.parseDouble(line);
            System.out.println(u1 + " giving " + value + " to " + u2);
            blockchain.newTransaction(value, u1, u2);
            u1.addValue(-1.0 * value);
            u2.addValue(value);
            break;
        default:
            System.out.println("Invalid Command");
        }
    }

    public static void execCommand(String cmd, User u1) { //still kind of stupid
        if(u1 == null){
            System.out.println("Invalid user");
            return;
        }

        switch (cmd) {
        case "mine":
            System.out.println("Mining");
            if (blockchain.newBlock()) {
                u1.addValue(1.0);
                blockchain.newTransaction(1, u1, u1);
            }
            System.out.println(u1.getValue());
            break;
        default:
            System.out.println("Invalid Command");
        }
    }

    public static void save(String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(blockchain);
            oos.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void load(String filename) {
        try {
            FileInputStream fin = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fin);
            Blockchain newBlockchain = (Blockchain) ois.readObject();
            if (verifyChain(newBlockchain)){
                blockchain = newBlockchain;
                users = blockchain.getUsers();
            }
            else
                System.out.println("Invalid blockchain");
            ois.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static boolean verifyChain(Blockchain b) { // reimplemented but it's fine I think
        hashMaker hm = new hashMaker();
        ArrayList<Block> chain = b.getChain();
        for (int i = 1; i < chain.size(); i++) {
            try {
                Block b2 = chain.get(i - 1);
                int hashMult = chain.get(i).getHash() * b2.getHash();

                String hash = hm.hash(hashMult);
                if (!hash.contains("39003500") || chain.get(i).getHash() < b2.getHash())
                    return false;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return true;
    }

    public static void createUser(String name) {
        if (!users.contains(name)) {
            User u1 = new User(name);
            users.addUser(u1);
        } else
            System.out.println("User already exists");
    }

}
