/*
 * © 2022 tienhuynh-tn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.user;

import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Tien Huynh - Tien Huynh TN - Huynh Le Thuy Tien
 */
@XmlRootElement
public class UserDTO {
    
    private String username;
    private String password;
    private String lastname;
    private boolean role;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String lastname, boolean role) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.role = role;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the role
     */
    public boolean isRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(boolean role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "username=" + username + ", password=" + password + ", lastname=" + lastname + ", role=" + role + '}';
    }

}
