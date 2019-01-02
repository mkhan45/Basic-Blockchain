import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class blockchainRun {

    static Blockchain blockchain = new Blockchain();
    static UserList users = new UserList();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("Enter input:");
            String input = scanner.nextLine();

            if (input.equals("-1")) {
                scanner.close();
                break;
            }

            String[] command = input.split(" ");

            if (command.length == 2) { // user command -> mine or create user or save or load
                if (command[0].equals("save")) {
                    save(command[1]);
                    System.out.println("Saved to " + command[1]);
                } else if (command[0].equals("load")) {
                    load(command[1]);
                    System.out.println("Loading from " + command[1]);
                }

                else if (command[1].equals("create")) { // [name] create
                    User u1 = new User(command[0]);
                    users.addUser(u1);
                } else if (command[1].equals("mine")) {
                    User u1 = users.getUser(command[0]);
                    execCommand("mine", u1);
                }
            } else if (command.length == 3) { // user user command -> give
                execCommand("give", users.getUser(command[0]), users.getUser(command[1]));
            }
            System.out.println(blockchain);
            System.out.println(users);
        }
    }

    public static void execCommand(String cmd, User u1, User u2) {
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

    public static void execCommand(String cmd, User u1) {
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
            if(verifyChain(newBlockchain))
                blockchain = newBlockchain;
            else
                System.out.println("Invalid blockchain");
            ois.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static boolean verifyChain(Blockchain b){ //reimplemented but it's fine I think
        hashMaker hm = new hashMaker();
        ArrayList<Block> chain = b.getChain();
        for(int i = 0; i < chain.size(); i++){
            try{
                Block b2 = chain.get(i - 1);
                int hashMult = chain.get(i).getHash() * b2.getHash();

                String hash = hm.hash(hashMult);
                if(!hash.contains("39003500"))
                    return false;
            }catch(Exception e){
                System.out.println(e);
            }
        }
        return true;
    }


}
