package com.projetweb.reminder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "sondage")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Sondage {

    @Id
    @GeneratedValue
    private int id;
    private String ville;
    private String lieu;
    private String name;
    private String date;
    private String description;
    private String participants;



    @OneToOne(cascade = CascadeType.ALL)
    private User creator;

    @OneToOne(mappedBy = "reference")
    private Vote vote;

    public int getId() {
        return id;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String avis) {
        this.description = avis;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }
}



