import java.util.Scanner;

public class blockchainRun{

    static Blockchain blockchain = new Blockchain();
    static UserList users = new UserList();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        while(true){
            String input = scanner.nextLine();

            if(input.equals("-1")){
                scanner.close();
                break;
            }

            String[] command = input.split(" ");

            if(command.length == 2){ //user command -> mine or create user
                if(command[1].equals("create")){ //   [name] create
                    User u1 = new User(command[0]);
                    users.addUser(u1);
                }else if(command[1].equals("mine")){
                    User u1 = users.getUser(command[0]);
                    execCommand("mine", u1);
                }
            }else if(command.length == 3){ //user user command -> give
                execCommand("give", users.getUser(command[0]), users.getUser(command[1]));
            }
            System.out.println(users);
            System.out.println(blockchain);
        }      
    }

    public static void execCommand(String cmd, User u1, User u2){
        switch(cmd){
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

    public static void execCommand(String cmd, User u1){
        switch(cmd){
            case "mine":
                System.out.println("Mining");
                if(blockchain.newBlock()){
                    u1.addValue(1.0);
                    blockchain.newTransaction(1, u1, u1);
                }
                System.out.println(u1.getValue());
                break;
            default:
                System.out.println("Invalid Command");
        }
    }

}
