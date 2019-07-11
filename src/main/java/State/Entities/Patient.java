package State.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue
    @Column(name = "patient_id")
    private int id;
    private String name;
    private String surname;
    private String fiscalCode;
    private String placeOfBirth;

    @Temporal(TemporalType.DATE)
    private Date birthDay;

    @OneToMany(mappedBy = "patient")
    private List<Administration> administrations = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "recovery_id")
    private List<Recovery> recoveries = new ArrayList<>();

    public Patient() { }

    //TODO add constructors with administrations and recoveries
    public Patient(String name, String surname, String fiscalCode, String placeOfBirth, Date birthDay) {
        this.name = name;
        this.surname = surname;
        this.fiscalCode = fiscalCode;
        this.placeOfBirth = placeOfBirth;
        this.birthDay = birthDay;
    }

    public String toString() {
        return String.format("{%s, %s, %s, %s, %s}", name, surname, fiscalCode, placeOfBirth, birthDay);
    }

    /**
     * GETTERS AND SETTERS
     */

    /*
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public List<Administration> getAdministrations() {
        return administrations;
    }

    public void setAdministrations(List<Administration> administrations) {
        this.administrations = administrations;
    }

    public void addToAddministrations(Administration administration) {
        this.administrations.add(administration);
    }

    public List<Recovery> getRecoveries() {
        return recoveries;
    }

    public void setRecoveries(List<Recovery> recoveries) {
        this.recoveries = recoveries;
    }

    public void addToRecoveries(Recovery recovery) {
        this.recoveries.add(recovery);
    }

}
