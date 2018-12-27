class Transaction{
    private String sender;
    private String recipient;
    private double amount;

    public Transaction(String send, String recieve, String amnt){
        sender = send;
        recipient = recieve;
        amount = amnt;
    }
}