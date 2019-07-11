package State.Entities;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
public class Prescription {
    @Id
    @GeneratedValue
    @Column(name = "prescription_id")
    private int id;


    private String drug;

    @Temporal(TemporalType.DATE)
    private Date date;

    private int duration;
    private double dailyDose;
    private int numberOfDoses;
    private String doctor;

    @OneToMany(mappedBy = "prescription")
    private List<Administration> administrations;

    @ManyToOne
    @JoinColumn(name = "recovery_id")
    private Recovery recovery;


    //TODO add no Administration, no recoveries constructor

    public Prescription(String drug, Date date, int duration, double dailyDose, int numberOfDoses, String doctor ,
                        Recovery recovery, List<Administration> administration) throws Exception {

        if(duration <= 0 || dailyDose <= 0 || numberOfDoses <= 0) throw new Exception("Prescription: invalid parameters");
        this.date = date;
        this.drug = drug;
        this.duration = duration;
        this.dailyDose = dailyDose;
        this.numberOfDoses = numberOfDoses;
        this.doctor = doctor;
        this.administrations = administration;
        this.recovery = recovery;
    }

    public Prescription(String drug, Date date, int duration, double dailyDose, int numberOfDoses, String doctor,
                        Recovery recovery, Administration ...administrations) throws Exception{
        this(drug, date, duration, dailyDose, numberOfDoses, doctor, recovery, Arrays.asList(administrations));
    }

    public Prescription() {}

    public String toString() {
        return String.format("{%s, %s, %d, %fd, %d, %s, rec_id: %s, adms_is: %s}", drug, date, duration, dailyDose,
                numberOfDoses, dailyDose, recovery, administrations);
    }
    /**
     * GETTERS AND SETTERS
     */

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getDailyDose() {
        return dailyDose;
    }

    public void setDailyDose(double dailyDose) {
        this.dailyDose = dailyDose;
    }

    public int getNumberOfDoses() {
        return numberOfDoses;
    }

    public void setNumberOfDoses(int numberOfDoses) {
        this.numberOfDoses = numberOfDoses;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public Recovery getRecovery() {
        return recovery;
    }
    public void setRecovery(Recovery recovery) {
        this.recovery = recovery;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Administration> getAdministrations() {
        return administrations;
    }

    public void setAdministrations(List<Administration> administrations) {
        this.administrations = administrations;
    }

    public void addToAdministrations(Administration administration) {
        this.administrations.add(administration);
    }

}
