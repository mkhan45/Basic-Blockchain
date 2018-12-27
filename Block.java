new java.util.Date()

class Block{
    private int index;
    private int timestamp;
    private ArrayList<Transaction> transactions;
    private int proof;
    private String previousHash;

    public Block(int index, int proof, String previousHash){
        this.index = index;
        this.proof = proof;
        this.previousHash = previousHash;
        transactions = new ArrayList<Transaction>;
        timestamp = System.currentTimeMillis();
    }
}