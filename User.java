import java.io.Serializable;

class User implements Serializable{
    private static final long serialVersionUID = -6081311999959994642L;
    private double value;
    private String name;

    public User(String n){
        name = n;
        value = 0.0;
    }

    public String getName(){
        return name;
    }

    public double getValue(){
        return value;
    }

    public void addValue(double d){
        value += d;
    }

    public String toString(){
        return "(" + name + ", " + value + ")";
    }

    public User copy(){
        User newUser = new User(name);
        newUser.addValue(value);
        return newUser;
    }
}