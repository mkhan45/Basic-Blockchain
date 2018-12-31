class User{
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
}