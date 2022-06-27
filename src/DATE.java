import java.util.Scanner;

public class DATE {
    static int dd;
    static int mm;
    static int yyyy;

    static String[] monthShort = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
            "Aug", "Sep", "Oct", "Nov", "Dec"};

    public static void formatDate(int format) {
        switch (format) {
            case 1 -> System.out.println(dd + "/" + mm + "/" + yyyy);
            case 2 -> System.out.println(mm + "/" + dd + "/" + yyyy);
            case 3 -> System.out.println(dd + "-" + monthShort[mm] + "-" + yyyy);
        }
    }

    public static void checkDate (){

        if (mm < 1 || mm > 12)
            System.out.println("Wrong input!");
        else
            printMonth(yyyy, mm);
    }


    public static void firstDayOfTheYear(){

    }

    public static void findDayOfTheWeek(){
        boolean flag_for_leap = isLeapYear(yyyy);

        /*Declaring and initialising the given informations
         * and arrays*/
        String[] day = {"Sunday", "Monday", "Tuesday",
                "Wednesday", "Thursday", "Friday",
                "Saturday"};
        int[] m_no = {0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5};

        /*Generalised check to find any Year Value*/
        int j;
        if ((yyyy / 100) % 2 == 0) {
            if ((yyyy / 100) % 4 == 0)
                j = 6;
            else
                j = 2;
        } else {
            if (((yyyy / 100) - 1) % 4 == 0)
                j = 4;
            else
                j = 0;
        }

        /*THE FINAL FORMULA*/
        int total = (yyyy % 100) + ((yyyy % 100) / 4) + dd
                + m_no[mm - 1] + j;
        if (flag_for_leap) {
            if ((total % 7) > 0)
                System.out.println(day[(total % 7) - 1]);
            else
                System.out.println(day[6]);
        } else
            System.out.println(day[(total % 7)]);
    }
    public static void findDate (){

    }

    static void printMonth(int year, int month) {
        //Print the headings of the calendar
        printMonthTitle(year, month);
        //Print the body of the calendar
        printMonthBody(year, month);
    }
    static void printMonthTitle(int year, int month) {

        if (year < 0) {
            System.out.println(getMonthName(month) + " " + year + " " + "BC");
        } else if (year > 0) {
            System.out.println(getMonthName(month) + " " + year + " " + "AD");
        }else
            System.out.println(getMonthName(month) + " " + year);
        System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
    }
    static String getMonthName(int month) {
        return switch (month) {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> null;
        };
    }
    static void printMonthBody(int year, int month) {
        // Get start day of the week for the first date in the month
        int startDay = getStartDay(year, month);
        // Get number of days in the month
        int numberOfDaysInMonth = getNumberOfDaysInMonth(year, month);
        // Pad space before the first day of the month
        int i;
        for (i = 0; i < startDay; i++)
            System.out.print("    ");
        for (i = 1; i <= numberOfDaysInMonth; i++) {
            if (i < 10)
                System.out.print("   " + i);
            else
                System.out.print("  " + i);
            if ((i + startDay) % 7 == 0)
                System.out.println();
        }
        System.out.println();
    }
    /**
     * Get the start day of the first day in a month
     */
    static int getStartDay(int year, int month) {
        //Get total number of days since 1/1/1800
        int startDay0 = 1;
        int totalNumberOfDays = getTotalNumberOfDays(year, month);
        //Return the start day
        return (totalNumberOfDays + startDay0) % 7;
    }
    static int getTotalNumberOfDays(int year, int month) {
        int total = 0;
        //Get the total days from 0 to year - 1
        for (int i = 0; i < year; i++)
            if (isLeapYear(i))
                total = total + 366;
            else
                total = total + 365;
        //Add days from January to the month prior to the calendar month
        for (int i = 1; i < month; i++)
            total = total + getNumberOfDaysInMonth(year, i);
        return total;
    }
    static int getNumberOfDaysInMonth(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
        }
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
        }
        if (month == 2)
            return isLeapYear(year) ? 29 : 28;
        return 0;
    }
    static boolean isLeapYear(int year) {
        return year % 4 == 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a year: ");
        yyyy = sc.nextInt();
        System.out.print("Enter a month (from 1-12)  : ");
        mm = sc.nextByte();
        System.out.println("Enter date:");
        dd = sc.nextByte();


        System.out.println("Choose date format from below:");
        System.out.println("1: dd/MM/yyyy");
        System.out.println("2: MM/dd/yyyy");
        System.out.println("3: dd-MMM-yyyy");

        int format = sc.nextInt();

        formatDate(format);
        System.out.println();

        findDayOfTheWeek();
        System.out.println();

        printMonth (yyyy,mm);
        System.out.println();
        checkDate();
    }
}
