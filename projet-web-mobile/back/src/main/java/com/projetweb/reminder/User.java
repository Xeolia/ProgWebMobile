package com.projetweb.reminder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;

@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class User {


    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String ville;
    private String adresse;
    private String pays;
    private String codePostal;


    public String getVille() {return ville;}

    public void setVille(String ville) {this.ville = ville;}

    public String getAdresse() {return adresse;}

    public void setAdresse(String adresse) {this.adresse = adresse;}

    public String getPays() {return pays;}

    public void setPays(String pays) {this.pays = pays;}

    public String getCodePostal() {return codePostal;}

    public void setCodePostal(String codePostal) {this.codePostal = codePostal;}

    public void setEmail(String email) { this.email = email; }

    public String getEmail() { return email; }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
}
