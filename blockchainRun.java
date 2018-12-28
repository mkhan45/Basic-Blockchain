import java.util.Scanner;

public class blockchainRun{

    static Blockchain blockchain = new Blockchain();
    static UserList users = new UserList();

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        while(true){
            String input = "u1 mine";
            System.out.println(input);
            String[] command = input.split(" ");

            if(users.indexOf(command[0].split(",")[0]) != -1){
                execCommand(command[1], users.get(0), null);
            }else{
                users.addUser(new User(command[0]));
                execCommand(command[1], users.get(0), null);
            }

        }      
    }

    public static void execCommand(String cmd, User u1, User u2){
        switch(cmd){
            case "mine":
                System.out.println("Mining");
                if(blockchain.newBlock()){ // proof of work is right
                    u1.addValue(1.0);
                    blockchain.newTransaction(1, u1, u1);
                }
                System.out.println(u1.getValue());
        }
        System.out.println(users);
        System.out.println(blockchain);
    }
    
}