/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.registration;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class RegistrationDTO implements Serializable {

    private String username;
    private String password;
    private String fullname;
    private boolean admin;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    public RegistrationDTO(String username, String password, String fullname, boolean admin) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.admin = admin;
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
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the admin
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }


}
