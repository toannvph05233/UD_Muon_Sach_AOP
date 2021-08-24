package codegym.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;


public class User{

    @Pattern(regexp = "^[a-zA-Z0-9]+@[a-z]+\\.[a-z]+$",message = "sai pattern")
    private String email;

    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "sai kieu cua password")
    private String password;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}