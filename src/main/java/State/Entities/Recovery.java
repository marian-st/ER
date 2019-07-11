package State.Entities;

import com.sun.marlin.stats.Monitor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Recovery {
    @Id
    @GeneratedValue
    @Column(name = "recovery_id")
    private int id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String diagnosis;
    private boolean active;
    private Date endDate;
    private String dischargeSummary;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToMany
    @JoinColumn
    private List<Monitoring> monitorings;

    //TODO add constructos
    /**
    * GETTERS AND SETTERS
    **/

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDischargeSummary() {
        return dischargeSummary;
    }

    public void setDischargeSummary(String dischargeSummary) {
        this.dischargeSummary = dischargeSummary;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    @OneToMany
    @JoinColumn(name = "prescritpion_id")
    List<Prescription> prescriptions;

}
