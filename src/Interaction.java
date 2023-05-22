import java.util.*;

public class Interaction {

        Scanner sc = new Scanner(System.in);
        FileReader fileReader = new FileReader();
        YearlyReport yearlyReport = new YearlyReport();
        MonthlyReport monthlyReport = new MonthlyReport();
        ReportEngine reportEngine = new ReportEngine();

        public void interaction() {
        while (true) {
            printMenu();
            String commandInput = sc.nextLine();
            int command = strCheck(commandInput);
            switch (command) {
                case 1:
                    monthlyReport.loadMonthlyReports(sc, fileReader, reportEngine);
                    break;
                case 2:
                    yearlyReport.loadYearlyReport(sc, fileReader, reportEngine);
                    break;
                case 3:
                    if (checkYearlyReportLoaded(yearlyReport) & checkMonthlyReportsLoaded(monthlyReport)) {
                        reportEngine.verifyReports(yearlyReport.profitLossMap, monthlyReport.monthlyProfitLossMap);
                    }
                    break;
                case 4:
                    if (checkMonthlyReportsLoaded(monthlyReport)) {
                        reportEngine.printMonthlyStatistics(monthlyReport.monthlyReports);
                    }
                    break;
                case 5:
                    if (checkYearlyReportLoaded(yearlyReport)) {
                        reportEngine.printYearlyStatistics(yearlyReport.yearOfReport, yearlyReport.profitLossMap, yearlyReport.yearlyList);
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("[" + commandInput + "] - Такой команды нет!");
                    break;
            }
        }
    }

    public static void printMenu() {
        System.out.println("\nВыберите действие:");
        System.out.println("Считать месячные отчёты ------------------------> (1)");
        System.out.println("Считать годовой отчёт --------------------------> (2)");
        System.out.println("Сверить отчёты ---------------------------------> (3)");
        System.out.println("Вывести информацию обо всех месячных отчётах ---> (4)");
        System.out.println("Вывести информацию о годовом отчёте ------------> (5)");
        System.out.println("Завершить программу ----------------------------> (6)");
    }

    public static int strCheck(String userInput) {
        String str = userInput.trim();
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return 0;
            }
        }
        return Integer.parseInt(str);
    }

    static String yearCheck(String userInputYear) {
        int year = strCheck(userInputYear);
        if (year > 1950 && year < 2050) {
            return "." + year;
        } else {
            return "ERROR";
        }
    }

    public static boolean checkMonthlyReportsLoaded(MonthlyReport monthlyReport) {
        if (monthlyReport.monthlyReports.isEmpty()) {
            System.out.println("Сначала считайте месячные отчеты!");
            return false;
        }
        return true;
    }

    public static boolean checkYearlyReportLoaded(YearlyReport yearlyReport) {
        if (yearlyReport.yearlyList.isEmpty()) {
            System.out.println("Сначала считайте годовой отчет!");
            return false;
        }
        return true;
    }
}

