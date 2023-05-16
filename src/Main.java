import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FileReader fileReader = new FileReader();
        YearlyReport yearlyReport = new YearlyReport();
        MonthlyReport monthlyReport = new MonthlyReport();
        ReportEngine reportEngine = new ReportEngine();

        while (true) {
            printMenu();
            String commandInput = sc.nextLine();
            int command = strCheck(commandInput);
            switch (command) {
                case 1:
                    loadMonthlyReports(sc, fileReader, monthlyReport, reportEngine);
                    break;
                case 2:
                    loadYearlyReport(sc, fileReader, yearlyReport);
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

    public static void loadMonthlyReports(Scanner sc, FileReader fileReader, MonthlyReport monthlyReport, ReportEngine reportEngine) {
        System.out.println("За какой год считать месячные отчеты?\nВведите год в формате \"YYYY\"");
        String userInputYear = sc.nextLine();
        String yearReportName = yearCheck(userInputYear);
        if (yearReportName.equals("ERROR")) {
            System.out.println("[" + userInputYear + "] - неверный формат года!");
        } else {
            System.out.println("Сколько месячных отчетов вы хотите считать?");
            String userInputQuantity = sc.nextLine();
            int quantity = strCheck(userInputQuantity);
            if (quantity < 1 || quantity > 12) {
                System.out.println("[" + userInputQuantity + "] - Неверное количество отчетов");
            } else {
                int uploadCount = 0;
                for (int i = 1; i <= quantity; i++) {
                    String fileName = String.format("m%s%d%d.csv", yearReportName, 0, i);
                    ArrayList<String> list = fileReader.readFileContents(fileName);
                    if (list.isEmpty()) {
                        System.out.println("Попробуйте снова!");
                        return;
                    } else {
                        monthlyReport.monthlyReports.put(i, list);
                        monthlyReport.monthlyReports.get(i).remove(monthlyReport.monthlyReports.get(i).get(0));
                        uploadCount++;
                        System.out.println("Отчет за " + reportEngine.monthName(i) + " успешно считан и сохранен!");
                    }
                }
                monthlyReport.monthlyProfitLossMap = monthlyReport.monthlyReportsToProfitLossMap(monthlyReport.monthlyReports);
                monthlyReport.yearOfMonthlyReport = strCheck(userInputYear);
                System.out.println(uploadCount + " отчетов считаны и сохранены!");
            }
        }
    }

    public static void loadYearlyReport(Scanner sc, FileReader fileReader, YearlyReport yearlyReport) {
        System.out.println("За какой год считать годовой отчет?\nВведите год в формате \"YYYY\"");
        String userInputYear = sc.nextLine();
        String yearReportName = yearCheck(userInputYear);
        if (yearReportName.equals("ERROR")) {
            System.out.println("[" + userInputYear + "] - неверный формат года!");
        } else {
            String fileName = "y" + yearReportName + ".csv";
            ArrayList<String> list = fileReader.readFileContents(fileName);
            if (list.isEmpty()) {
                System.out.println("Попробуйте снова!");
            } else {
                yearlyReport.yearlyList = list;
                yearlyReport.yearlyList.remove(0);
                System.out.println("Отчет за " + userInputYear + " считан и сохранен.");
                yearlyReport.profitLossMap = yearlyReport.yearlyListToProfitLossMap(yearlyReport.yearlyList);
                yearlyReport.yearOfReport = strCheck(userInputYear);
            }
        }
    }

    public static String yearCheck(String userInputYear) {
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

