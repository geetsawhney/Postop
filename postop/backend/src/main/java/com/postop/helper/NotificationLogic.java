package com.postop.helper;

import com.postop.dao.NotificationDaoImpl;
import com.postop.model.FitnessHistory;
import com.postop.model.Notification;
import com.postop.model.Patient;

import java.sql.Date;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * Computes the number of notifications to be sent out to a patient\
 * based on the medical conditions and fitness activity
 * @author Rohit Aakash, Geet Sawhney
 */
public class NotificationLogic {

    private Patient patient;
    private FitnessHistory fitnessHistory;

    /**
     * Parameterized constructor for initializing Patient and FitnessHistory instances
     * @param patient: an instance of Patient
     * @param fitnessHistory: fitness data of the patient
     */
    public NotificationLogic(Patient patient, FitnessHistory fitnessHistory) {
        this.patient = patient;
        this.fitnessHistory = fitnessHistory;
    }

    /**
     * Decreases the criticality status based on the number of days
     * @param status: current status of patient criticality
     * @param numberOfDays: number of days since the last hospital visit
     * @return updated status string
     */
    private String statusDecrease(String status, long numberOfDays) {
        if (status.equals("C")) {
            numberOfDays = 90 - numberOfDays;
            if (numberOfDays <= 0) {
                status = "H";
                numberOfDays *= -1;
            }
        }
        if (status.equals("H")) {
            numberOfDays = 60 - numberOfDays;
            if (numberOfDays <= 0) {
                status = "M";
                numberOfDays *= -1;
            }
        }
        if (status.equals("M")) {
            numberOfDays = 30 - numberOfDays;
            if (numberOfDays <= 0) {
                status = "L";
            }
        }

        return status;
    }

    /**
     * Computes and returns status based on the age of the patient
     * @return updated status string
     */
    public String ageStatus() {
        String status = "L";
        if (getAge(patient.getDob()) >= 18 && getAge(patient.getDob()) < 40) {
            status = "L";
            status = diabeticStatus(status, patient.getDiabetic());
        } else if (getAge(patient.getDob()) >= 40 && getAge(patient.getDob()) < 60) {
            status = "L";
            status = sexStatus(status, patient.getSex());
        } else if (getAge(patient.getDob()) >= 60) {
            status = "M";
            status = sexStatus(status, patient.getSex());
        }
        return status;
    }

    /**
     * Computes and returns the status based on the gender of the patient
     * @param status: current status of the patient
     * @param sexStat: gender of the patient
     * @return updated status string
     */
    private String sexStatus(String status, String sexStat) {
        if (sexStat.equals("F")) {
            status = incrementStatus(status);
        }

        return diabeticStatus(status, patient.getDiabetic());
    }

    /**
     * Computes and returns updated status based on if the patient is diabetic
     * @param status: current status
     * @param diabetic: whether the patient is diabetic
     * @return updated status string
     */
    private String diabeticStatus(String status, boolean diabetic) {
        if (diabetic) {
            status = incrementStatus(status);
        }
        return utiVisitCountStatus(status, patient.getUtiVisitCount());

    }

    /**
     * Computes and returns the updated status based on the number of times
     * @param status: current status
     * @param utiCount: number of times the patient has had UTI
     * @return updated status string
     */
    private String utiVisitCountStatus(String status, int utiCount) {
        if (utiCount >= 3) {
            status = incrementStatus(status);
        }
        return catheterUsageStatus(status, patient.getCatheterUsage());
    }

    /**
     * Computes and returns the updated status string based on catheter usage
     * @param status: current status
     * @param catheterUse: whether the patient uses catheter
     * @return updated status string
     */
    private String catheterUsageStatus(String status, boolean catheterUse) {
        if (catheterUse) {
            status = incrementStatus(status);
        }
        return statusDecrease(status, getNoOfDays(patient.getLastVisitDate()));
    }

    /**
     * Increases the criticality status to a higher level
     * @param status: current status
     * @return updated status string
     */
    private String incrementStatus(String status) {
        if (status.equals("L")) {
            status = "M";
        } else if (status.equals("M")) {
            status = "H";
        } else if (status.equals("H")) {
            status = "C";
        }

        return status;
    }


    /**
     * Computes and returns the number of notifications to be sent out based on the criticality status
     * @return number of notifications
     * @throws SQLException
     */
    public int getNumberOfNotifications() throws SQLException {

        String status = ageStatus();
        int noOfDays = (int) getNoOfDays(patient.getLastVisitDate());
        Notification notification = new NotificationDaoImpl().getNotification(status);

        int numberOfNotifications = notification.getStart();
        if ((status.equals("C") && noOfDays > 28) || (status.equals("H") && noOfDays > 21)
                || (status.equals("M") && noOfDays > 14) || (status.equals("L") && noOfDays > 7)) {
            numberOfNotifications = Math.max(notification.getStart() - noOfDays / notification.getInterval(), notification.getEnd());
        }

        return numberOfNotifications + fitnessHistory.getStepCount() / 3000 + fitnessHistory.getCaloriesExpended() / 1000;
    }

    /**
     * Computes age of the patient
     * @param dob: date of birth for the patient
     * @return age
     */
    public long getAge(Date dob) {
        Date currentDate = new Date(System.currentTimeMillis());
        long patientAge = (currentDate.getTime() - dob.getTime()) / 365;
        return TimeUnit.DAYS.convert(patientAge, TimeUnit.MILLISECONDS);
    }

    /**
     * Computes the number of days since the last hospital visit
     * @param lastVisitDate: date when patient last visited the hospital
     * @return number of days
     */
    public long getNoOfDays(Date lastVisitDate) {
        Date currentDate = new Date(System.currentTimeMillis());
        long noOfDays = currentDate.getTime() - lastVisitDate.getTime();
        return TimeUnit.DAYS.convert(noOfDays, TimeUnit.MILLISECONDS);
    }
}