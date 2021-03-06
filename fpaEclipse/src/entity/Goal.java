package entity;
//
// This file was generated by the JPA Modeler
//


import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity

public class Goal {

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private double monthlyContributions;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private double lumpSum;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic
    private double targetAmount;

    
    @ManyToOne(optional = true, targetEntity = RiskLevel.class)
    private RiskLevel goalLevelRiskProfile;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic
    private String name;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private double totalShortfall;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Id @GeneratedValue(strategy=GenerationType.AUTO) 
    private Long id;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private double potentialValue;

    
    @ManyToOne(optional = true, targetEntity = Client.class)
    private Client client1;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic
    private Date startDate;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic
    private int periodYears;

    public Goal() {

    }

    public double getMonthlyContributions() {
        return this.monthlyContributions;
    }

    public void setMonthlyContributions(double monthlyContributions) {
        this.monthlyContributions = monthlyContributions;
    }

    public double getLumpSum() {
        return this.lumpSum;
    }

    public void setLumpSum(double lumpSum) {
        this.lumpSum = lumpSum;
    }

    public double getTargetAmount() {
        return this.targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public RiskLevel getGoalLevelRiskProfile() {
        return this.goalLevelRiskProfile;
    }

    public void setGoalLevelRiskProfile(RiskLevel goalLevelRiskProfile) {
        this.goalLevelRiskProfile = goalLevelRiskProfile;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalShortfall() {
        return this.totalShortfall;
    }

    public void setTotalShortfall(double totalShortfall) {
        this.totalShortfall = totalShortfall;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPotentialValue() {
        return this.potentialValue;
    }

    public void setPotentialValue(double potentialValue) {
        this.potentialValue = potentialValue;
    }

    public Client getClient1() {
        return this.client1;
    }

    public void setClient1(Client client1) {
        this.client1 = client1;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getPeriodYears() {
        return this.periodYears;
    }

    public void setPeriodYears(int periodYears) {
        this.periodYears = periodYears;
    }
}
