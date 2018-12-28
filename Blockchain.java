import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;

class Blockchain {
    ArrayList<Block> chain;
    ArrayList<Transaction> currentTransactions;
    hashMaker hm = new hashMaker();

    public Blockchain() {
        chain = new ArrayList<Block>();
        chain.add(new Block());
        currentTransactions = new ArrayList<Transaction>();
    }

    public boolean newBlock() {
        Block b = new Block(chain.size(), chain.get(chain.size() - 1).getHash());
        b.proofOfWork(hm);
        if (testBlock(b, hm)) {
            chain.add(b);
            return true;
        }
        return false;
    }

    public boolean newTransaction(int value, User u1, User u2) {
        Block b = chain.get(chain.size() - 1);
        Transaction t = new Transaction(u1, u2, value);
        b.addTransaction(t);
        return true;
    }

    public Block lastBlock() {
        return chain.get(chain.size() - 1);
    }

    public boolean testBlock(Block b, hashMaker h) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            Block b2 = chain.get(b.index() - 1);
            int hashMult = b2.getHash() * b.getHash();

            if (h.hash(hashMult).contains("420"))
                return true;
            // return false;
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return true;
        }
    }

    public String toString() {
        String s = "";
        for (Block b : chain)
            s += b.toString() + "\n";
        return s;
    }

}