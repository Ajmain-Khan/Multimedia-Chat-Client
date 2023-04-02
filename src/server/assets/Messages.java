package server.assets;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Messages class that handles that defines messages, and attributes associated with them:
 * name - name of user sending message
 * messageType - type of the message (ie User string)
 * message - the message itself
 */
public class Messages implements Serializable {

    private String name;
    private Types messageType;
    private String message;
    private String image;
    private ArrayList<Users> userList;
    private ArrayList<Users> userObjects;
    private int userCount;

    public Messages() {
    }

    // Accessors and Mutators

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = message;
    }

    public Types getType() {
        return messageType;
    }

    public void setType(Types type) {
        this.messageType = type;
    }

    public ArrayList<Users> getUsers() {
        return userObjects;
    }

    public void setUsers(ArrayList<Users> users) {
        this.userObjects = users;
    }

    public ArrayList<Users> getUserlist() {
        return userList;
    }

    public void setUserlist(HashMap<String, Users> userList) {
        this.userList = new ArrayList<>(userList.values());
    }

    public int getUserCount() {
        return this.userCount;
    }

    public void setUserCount(int count) {
        this.userCount = count;
    }
}
