import java.io.Serializable;
import java.util.*;

class Block implements Serializable{
    private static final long serialVersionUID = 6011393147475410066L;
    private int index;
    private long timestamp;
    private ArrayList<Transaction> transactions;
    private int proof;
    private int previousHash;

    public Block(int index, int previousHashNumber) {
        this.index = index;
        this.previousHash = previousHashNumber;
        transactions = new ArrayList<Transaction>();
        timestamp = System.currentTimeMillis();
    }

    public Block() { // original block probably not the best implementation
        index = 0;
        previousHash = 1;
        proof = 1;
        transactions = new ArrayList<Transaction>();
        timestamp = System.currentTimeMillis();
    }

    public int proofOfWork(hashMaker h) {
        try {
            int numberToTest = previousHash;
            while (true) {
                int hashMult = previousHash * numberToTest;
                String hash = h.hash(hashMult);
                System.out.println(hash);
                if (hash.contains("39003500")) {
                    proof = numberToTest;
                    return proof;
                }
                numberToTest++;
            }
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }

    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public int getHash() {
        return proof;
    }

    public int index() {
        return index;
    }

    public String toString() {
        String s = "\t{ \t Index:" + index + "\t Hash: " + proof + "\n";
        for (Transaction t : transactions)
            s += "\t" + t.toString() + "\n";
        s += "\t}";
        return s;
    }

    public long getTimestamp() {
        return timestamp;
    }
}