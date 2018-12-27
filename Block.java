new java.util.Date()

class Block{
    private int index;
    private int timestamp;
    private ArrayList<Transaction> transactions;
    private int proof;
    private int previousHash;

    public Block(int index, int proof, int previousHashNumber){
        this.index = index;
        this.proof = proof;
        this.previousHash = previousHash;
        transactions = new ArrayList<Transaction>;
        timestamp = System.currentTimeMillis();
    }

    public Block(){ //original block probably not the best implementation
        index = 0;
        previousHash = 1;
        proof = 1;
        transactions = new ArrayList<Transaction>;
        timestamp = System.currentTimeMillis;
    }

    public int proofOfWork(){
        int numberToTest = 0;
        while (true) {
            if ((previousHashNumber * numberToTest).hashCode().startsWith("00")) 
                return numberToTest;
            numberToTest++
        }
    }

    public ArrayList<Transaction> getTransactions(){
        return transactions;
    }

    public void addTransaction(Transaction t){
        transactions.add(t);
    }
}