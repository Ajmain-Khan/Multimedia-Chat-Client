package server.assets;

import java.io.Serializable;

/**
 * User class, defines a user with the following attributes:
 * - name
 * - profileImage
 */
public class Users implements Serializable {

    String name;
    String profileImage;

    // Accessors and Mutators
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return profileImage;
    }

    public void setPicture(String profileImage) {
        this.profileImage = profileImage;
    }


}
