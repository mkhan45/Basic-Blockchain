import java.io.Serializable;

class Transaction implements Serializable{
    private static final long serialVersionUID = 1041050558716633540L;
    private User sender;
    private User recipient;
    private double amount;

    public Transaction(User send, User recieve, double amnt){
        sender = send.copy();
        recipient = recieve.copy();
        amount = amnt;
    }

    public String toString(){
        String s = "";
        s += "\tSender: " + sender;
        s += "\n\t\t Recipient: " + recipient;
        s += "\n\t\t Amount: " + amount;
        return s + "\n";
    }
}