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

    public enum Status {
        ACCEPT("A"), DECLINE("D"), MAYBE("M");
        private String status;

        private Status(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }


    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User votant;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sondage_id", referencedColumnName = "id")
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
}
