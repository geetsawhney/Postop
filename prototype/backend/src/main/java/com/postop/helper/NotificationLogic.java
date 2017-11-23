package com.postop.helper;

import com.postop.model.FitnessHistory;
import com.postop.model.Patient;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NotificationLogic {


    private String status;
    private Patient patient;
    private FitnessHistory fitnessHistory;


    public NotificationLogic() {
        status = "L";
    }

    public NotificationLogic(Patient patient, FitnessHistory fitnessHistory) {
        this.status = "L";
        this.patient = patient;
        this.fitnessHistory = fitnessHistory;
    }

    static String statusDecrease(String stat, long numberOfDays) {
        long extraDays = 0;
        if (stat.equals("C")) {
            extraDays = 90 - numberOfDays;
            if (extraDays <= 0) {
                stat = "H";
                extraDays = extraDays * -1;
                numberOfDays = extraDays;
            }
        }
        if (stat.equals("H")) {
            extraDays = 60 - numberOfDays;
            numberOfDays = extraDays;
            if (numberOfDays <= 0) {
                stat = "M";
                numberOfDays = numberOfDays * -1;
            }
        }
        if (stat.equals("M")) {
            extraDays = 30 - numberOfDays;
            numberOfDays = extraDays;
            if (numberOfDays >= 0) {
                return stat;
            } else if (numberOfDays <= 0) {
                stat = "L";
                return stat;
            } else {
                return stat;
            }
        } else if (stat.equals("L")) {
            stat = "L";
            return stat;
        }
        return stat;
    }

    public String ageStatus() {
        //18-40
        if (getAge(patient.getDob()) >= 18 && getAge(patient.getDob()) < 40) {
            status = "L";
            diabeticStatus(status, patient.getDiabetic());
        } else if (getAge(patient.getDob()) >= 40 && getAge(patient.getDob()) < 60) {
            status = "L";
            sexStatus(status, patient.getSex());
        } else if (getAge(patient.getDob()) >= 60) {
            status = "M";
            sexStatus(status, patient.getSex());
        }

        return status;

    }

    private void sexStatus(String stat, String sexStat) {
        if (sexStat.equals("F")) {
            status = incrementStatus(stat);
        }

        diabeticStatus(status, patient.getDiabetic());
    }

    private void diabeticStatus(String stat, boolean diabetic) {
        if (diabetic) {
            status = incrementStatus(stat);
        }
        utiVisitCountStatus(status, patient.getUtiVisitCount());

    }

    public void utiVisitCountStatus(String stat, int utiCount) {
        if (utiCount >= 3) {
            status = incrementStatus(stat);
        }
        catheterUsageStatus(status, patient.getCatheterUsage());
    }

    public void catheterUsageStatus(String stat, boolean catheterUse) {
        if (catheterUse) {
            status = incrementStatus(stat);
        }
        status = statusDecrease(status, getNoOfDays(patient.getLastVisitDate()));
    }

    public String incrementStatus(String stat) {
        if (stat.equals("L")) {
            stat = "M";
        } else if (stat.equals("M")) {
            stat = "H";
        } else if (stat.equals("H")) {
            stat = "C";
        }

        return stat;
    }

    public int getNumberOfNotifications() {
        ageStatus();
        int notificationCount = 0;
        if (status == "C") {
            notificationCount = 8;
        } else if (status == "H") {
            if (fitnessHistory.getStepCount() >= 3000) {
                notificationCount = 8;
            } else if (fitnessHistory.getStepCount() < 3000) {
                notificationCount = 6;
            }
        } else if (status == "M") {
            if (fitnessHistory.getStepCount() >= 3000) {
                notificationCount = 6;
            } else if (fitnessHistory.getStepCount() < 3000) {
                notificationCount = 4;
            }

        } else if (status == "L") {
            if (fitnessHistory.getStepCount() >= 3000) {
                notificationCount = 4;
            } else if (fitnessHistory.getStepCount() < 3000) {
                notificationCount = 2;
            }
        }
        return notificationCount;
    }

    public long getAge(Date dob) {
        long patientAge = (new Date().getTime() - dob.getTime()) / 365;
        return TimeUnit.DAYS.convert(patientAge, TimeUnit.MILLISECONDS);
    }

    public long getNoOfDays(Date lastVisitDate) {
        long noOfDays = new Date().getTime() - lastVisitDate.getTime();
        return TimeUnit.DAYS.convert(noOfDays, TimeUnit.MILLISECONDS);
    }
}