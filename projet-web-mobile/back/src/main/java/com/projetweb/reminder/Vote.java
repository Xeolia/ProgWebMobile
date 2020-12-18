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
    private int votant;

    private int reference;

    public int getVotant() {
        return votant;
    }

    public void setVotant(int votant) {
        this.votant = votant;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
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
