package com.postop.helper;

import java.util.*;
public class NotificationLogic {
	
	private static String sex;
	private static String status;
	private Date dob;
	private static int age;
	private static int utiVisitCount;
	private static boolean catheterUsage;
	private static boolean diabetic;
	private Date lastVisitDate;
	private static int daysLastVisit;
	private static int stepCount;
	private static String notificationMessage;
	
	NotificationLogic()
	{}
	
	public static void main(String[] args)
	{
		age=52;
		sex="F";
		diabetic=true;
		utiVisitCount = 4;
		catheterUsage=false;
		status = "L";
		daysLastVisit = 160;
		stepCount = 5000;
		//call for the first function and pass the current status
		ageStatus("L", age);
	}
	
	static String ageStatus(String Stat, int sentAge)
	{
		//18-40
		if (sentAge>=18 && sentAge<40)
		{
			status ="L";	
			diabeticStatus(status,diabetic);
		}
		else if(sentAge>=40 && sentAge<60)
		{
			status="L";
			sexStatus(status, sex);
		}
		else if(sentAge>=60)
		{
			status="M";
			sexStatus(status, sex);
		}
		return status;
			
	}
	static void sexStatus(String stat, String sexStat)
	{
		if(sexStat =="F")
		{
			status = incrementStatus(stat);
		}
		
		diabeticStatus(status, diabetic);
	}
	static void diabeticStatus(String stat,boolean diabetes )
	{
		if(diabetes==true)
		{
			status = incrementStatus(stat);
		}
		utiVisitCountStatus(status,utiVisitCount);
		
	}
	static void utiVisitCountStatus(String stat, int utiCount)
	{
		if(utiCount>=3)
		{
			status = incrementStatus(stat);
		}
		catheterUsageStatus(status,catheterUsage);
	}
	static void catheterUsageStatus(String stat, boolean catheterUse)
	{
		if(catheterUse==true)
		{
			status = incrementStatus(stat);
		}
		System.out.println("the initial criticality of the patient is: "+ status);
		statusChange(status,daysLastVisit);
		numberOfNotifications();
	}
	static String incrementStatus(String stat)
	{
		if(stat=="L")
		{
			stat="M";
		}
		else if(stat=="M")
		{
			stat="H";
		}
		else if(stat=="H")
		{
			stat="C";
		}
		
		return stat;
	}
	static void statusChange(String stat, int daysCount)
	{
		int extraDays;
		if(stat == "C")
		{
			extraDays = 90-daysCount;
			if(extraDays <=0)
			{
				status = "H";
				extraDays= extraDays*-1;
				statusChange(status, extraDays);
			}
		}
		if(stat == "H")
		{
			extraDays = 60-daysCount;
			if(extraDays <=0)
			{
				status ="M";
				extraDays= extraDays*-1;
				statusChange(status, extraDays);
			}
		}
		if(stat == "M")
		{
			extraDays = 30-daysCount;
			if(extraDays <=0)
			{
				status ="L";
				extraDays= extraDays*-1;
				statusChange(status, extraDays);
			}
		}
		else if(stat =="L")
		{
			status = "L";
		}
		
		//System.out.println("the new status is " + status);
	}
	static void numberOfNotifications()
	{
		System.out.println("the new status is " + status);
		int notificationCount =0;
		if(status=="C")
		{
			notificationCount = 8;
		}
		else if(status=="H")
		{
			if(stepCount>=3000)
			{
				notificationCount = 8;				
			}
			else if(stepCount< 3000)
			{
				notificationCount = 6;
			}
		}
		else if(status=="M")
		{
			if(stepCount>=3000)
			{
				notificationCount = 6;
			}
			else if(stepCount< 3000)
			{
				notificationCount = 4;
			}
			
		}
		else if(status=="L")
		{
			if(stepCount>=3000)
			{
				notificationCount = 4;
			}
			else if(stepCount< 3000)
			{
				notificationCount = 2;
			}
		}
		System.out.println("the number of notifications that need to be sent out are:" + notificationCount);
	}

}
