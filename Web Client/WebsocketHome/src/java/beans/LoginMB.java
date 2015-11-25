/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author renzo
 */
@ManagedBean
@SessionScoped
public class LoginMB {

    /**
     * Creates a new instance of LoginMB
     */
    
    String username;
    String password;

    public LoginMB() {
    }
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String goLog(){
        System.out.println("US: "+username+" PAS: "+password);
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
            context.redirect("console2.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
