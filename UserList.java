import java.io.Serializable;
import java.util.ArrayList;

class UserList implements Serializable{
    private static final long serialVersionUID = 135596086452185148L;
    private ArrayList<User> users;

    public UserList(){
        users = new ArrayList<User>();
    }

    public int indexOf(String name){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getName().equals(name))
                return i;
        }

        return -1;
    }

    public void addUser(User u){
        users.add(u);
    }

    public User getUser(String name){
        for(User u : users){
            if(u.getName().equals(name))
                return u;
        }
        return new User("null");
    }

    public String toString(){
        String s = "";
        for(User u : users)
            s += u.toString() + "\n";
        return s;
    }

    public User get(int i){
        return users.get(i);
    }

    public boolean contains(String name){
        return indexOf(name) != -1;
    }
}
