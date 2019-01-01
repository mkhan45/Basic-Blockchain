class Transaction{
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
        s += "Sender: \n" + sender;
        s += "\n Recipient: \n" + recipient;
        s += "\n Amount: " + amount;
        return s;
    }
}