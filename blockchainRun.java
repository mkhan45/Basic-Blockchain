import java.util.Scanner;

public class blockchainRun{

    static Blockchain blockchain = new Blockchain();
    static UserList users = new UserList();

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input");

        while(true){
            String input = scanner.next();
            String[] command = input.split(" ");

            if(users.indexOf(command[0].split(",")[0]) != -1){
                break;
            }else{
                users.addUser(new User(command[0]));
            }

        }      
        scanner.close();
    }

    public void execCommand(String cmd, User u1, User u2){
        switch(cmd){
            case "mine":
                if(blockchain.newBlock()) // proof of work is right
                    u1.addValue(1.0);
                System.out.println(u1.getValue());
        }
    }
    
}