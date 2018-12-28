import java.util.ArrayList;

class UserList{
    private ArrayList<User> users;

    public UserList(){
        users = new ArrayList<User>();
    }

    public int indexOf(String name){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getName() == name)
                return i;
        }

        return -1;
    }

    public void addUser(User u){
        users.add(u);
    }
}
