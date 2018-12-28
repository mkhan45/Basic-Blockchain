import java.util.ArrayList;

class Blockchain{
    ArrayList<Block> chain;
    ArrayList<Transaction> currentTransactions;

    public Blockchain(){
        chain = new ArrayList<Block>();
        chain.add(new Block());
        currentTransactions = new ArrayList<Transaction>();
    }

    public boolean newBlock(){
        Block b = new Block(chain.size(), chain.get(chain.size() - 1).getHash());
        b.proofOfWork();
        if(testBlock(b)){
            chain.add(b);
            return true;
        }
        return false;
    }

    public boolean newTransaction(){
        return true;
    }


    public Block lastBlock(){
        return chain.get(chain.size() - 1);
    }

    public boolean testBlock(Block b){
        Block b2 = chain.get(b.index() - 1);
        int hashMult = b2.getHash() * b.getHash();
        Integer hashInteger = Integer.valueOf(hashMult);
        int hashcode = hashInteger.hashCode();

        if(hashcode % 100 == 0)
            return true;
        return false;
    }


}