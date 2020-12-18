package com.projetweb.reminder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Table(name = "vote")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Vote {

    @Id
    @GeneratedValue
    private long id;
    private String type;
    private String choix;


    @OneToOne(cascade = CascadeType.ALL)
    private User votant;


    @OneToOne(cascade = CascadeType.ALL)
    private Sondage reference;


    public User getVotant() {
        return votant;
    }

    public void setVotant(User votant) {
        this.votant = votant;
    }

    public Sondage getReference() {
        return reference;
    }

    public void setReference(Sondage reference) {
        this.reference = reference;
    }

    public String getChoix() {
        return choix;
    }

    public void setChoix(String choix) {
        this.choix = choix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
