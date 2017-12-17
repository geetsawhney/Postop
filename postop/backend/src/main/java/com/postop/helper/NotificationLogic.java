package com.postop.helper;

import com.postop.dao.NotificationDaoImpl;
import com.postop.model.FitnessHistory;
import com.postop.model.Notification;
import com.postop.model.Patient;

import java.sql.Date;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class NotificationLogic {


    private Patient patient;
    private FitnessHistory fitnessHistory;

    public NotificationLogic(Patient patient, FitnessHistory fitnessHistory) {
        this.patient = patient;
        this.fitnessHistory = fitnessHistory;
    }

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

    public String ageStatus() {
        String status = "";
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

    private String sexStatus(String status, String sexStat) {
        if (sexStat.equals("F")) {
            status = incrementStatus(status);
        }

        return diabeticStatus(status, patient.getDiabetic());
    }

    private String diabeticStatus(String status, boolean diabetic) {
        if (diabetic) {
            status = incrementStatus(status);
        }
        return utiVisitCountStatus(status, patient.getUtiVisitCount());

    }

    private String utiVisitCountStatus(String status, int utiCount) {
        if (utiCount >= 3) {
            status = incrementStatus(status);
        }
        return catheterUsageStatus(status, patient.getCatheterUsage());
    }

    private String catheterUsageStatus(String status, boolean catheterUse) {
        if (catheterUse) {
            status = incrementStatus(status);
        }
        return statusDecrease(status, getNoOfDays(patient.getLastVisitDate()));
    }

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

    public long getAge(Date dob) {
        Date currentDate = new Date(System.currentTimeMillis());
        long patientAge = (currentDate.getTime() - dob.getTime()) / 365;
        return TimeUnit.DAYS.convert(patientAge, TimeUnit.MILLISECONDS);
    }

    public long getNoOfDays(Date lastVisitDate) {
        Date currentDate = new Date(System.currentTimeMillis());
        long noOfDays = currentDate.getTime() - lastVisitDate.getTime();
        return TimeUnit.DAYS.convert(noOfDays, TimeUnit.MILLISECONDS);
    }
}