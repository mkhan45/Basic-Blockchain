class Transaction{
    private String sender;
    private String recipient;
    private double amount;

    public Transaction(String send, String recieve, int amnt){
        sender = send;
        recipient = recieve;
        amount = amnt;
    }

    public String toString(){
        String s = "";
        s += "Sender: " + sender;
        s += "\n Recipient: " + recipient;
        s += "\n Amount: " + amount;
        return s;
    }
}