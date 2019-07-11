package State.Entities;

import javax.persistence.*;
import java.util.Arrays;
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

    @OneToMany
    @JoinColumn(name = "recovery_id")
    private List<Recovery> recoveries;

    @ManyToOne
    @JoinColumn(name = "administration_id")
    private Administration administration;

    //TODO add no Administration, no recoveries constructor

    public Prescription(String drug, Date date, int duration, double dailyDose, int numberOfDoses, String doctor ,
                        Administration administration, List<Recovery> recoveries) throws Exception {
        this.drug = drug;
        this.date = date;
        if(duration <= 0 || dailyDose <= 0 || numberOfDoses <= 0) throw new Exception("Prescription: invalid parameters");
        this.duration = duration;
        this.dailyDose = dailyDose;
        this.numberOfDoses = numberOfDoses;
        this.doctor = doctor;
        this.administration = administration;
        if (recoveries.size() != 0) this.recoveries.addAll(recoveries);
    }

    public Prescription(String drug, Date date, int duration, double dailyDose, int numberOfDoses, String doctor ,
                        Administration administration, Recovery ...recoveries) throws Exception{
        this(drug, date, duration, dailyDose, numberOfDoses, doctor, administration, Arrays.asList(recoveries));
    }

    public Prescription() {}


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

    public List<Recovery> getRecoveries() {
        return recoveries;
    }

    public void setRecoveries(List<Recovery> recoveries) {
        this.recoveries = recoveries;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
