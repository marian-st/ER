package State.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Administration{
    @Id
    @GeneratedValue
    @Column(name = "administration_id")
    private int id;

    @Temporal(TemporalType.DATE)
    private Date date;
    private int hour;
    private double dose;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    //TODO add no prescription, no patient constructor
    public Administration() { }
    public Administration(Date date, int hour, double dose, String notes, Patient patient, Prescription prescription)
            throws Exception {
        if (hour < 0 || hour > 23 || dose <= 0) throw new Exception("Administration: invalid parameters");
        this.date = date;
        this.hour = hour;
        this.dose = dose;
        this.notes = notes;
        this.patient = patient;
        this.prescription = prescription;
    }

    public String toString() {
        return String.format("{%s, %d, %fd, %s, %s, %s}", date, hour, dose, notes, patient, prescription);
    }

    /**
     * GETTERS AND SETTERS
     */

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public double getDose() {
        return dose;
    }

    public void setDose(double dose) {
        this.dose = dose;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}
