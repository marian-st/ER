package Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Recovery implements Entry{
    @Id
    @GeneratedValue
    @Column(name = "recovery_id")
    private int id;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    private String diagnosis;
    private boolean active;
    private Date endDate;
    private String dischargeSummary;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToMany(mappedBy = "recovery")
    private List<Monitoring> monitorings = new ArrayList<>();

    @OneToMany(mappedBy = "recovery")
    private List<Prescription> prescriptions = new ArrayList<>();

    //TODO add constructors

    /**
    * GETTERS AND SETTERS
    **/

    public Recovery () {}
    public Recovery(Date start, Date end, String diagnosis, boolean active, String dischargeSummary) {
        this.startDate = start;
        this.endDate = end;
        this.diagnosis = diagnosis;
        this.active = active;
        this.dischargeSummary = dischargeSummary;
    }

    public Recovery(Date start, Date end, String diagnosis, boolean active, String dischargeSummary, Patient patient) {
        this(start, end, diagnosis, active, dischargeSummary);
        this.patient = patient;
    }

    public Recovery(Date start, Date end, String diagnosis, boolean active, String dischargeSummary, Patient patient,
                    List<Monitoring> monitorings, List<Prescription> prescriptions) {
        this(start, end, diagnosis, active, dischargeSummary, patient);
        this.monitorings.addAll(monitorings);
        this.prescriptions.addAll(prescriptions);
    }


    public Recovery(Date start, Date end, String diagnosis, boolean active, String dischargeSummary,
                    List<Monitoring> monitorings, List<Prescription> prescriptions) {
        this(start, end, diagnosis, active, dischargeSummary);
        this.monitorings.addAll(monitorings);
        this.prescriptions.addAll(prescriptions);
    }


    public int getId() {
        return this.id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date date) {
        this.startDate = date;
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

    public void addToPrescriptions(Prescription prescription) {this.prescriptions.add(prescription);}

    public List<Monitoring> getMonitorings() {
        return monitorings;
    }

    public void setMonitorings(List<Monitoring> monitorings) {
        this.monitorings = monitorings;
    }

    public void addToMonitorings(Monitoring monitoring) {this.monitorings.add(monitoring);}
}
