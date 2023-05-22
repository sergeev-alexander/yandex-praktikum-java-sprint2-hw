import java.util.*;

public class MonthlyReport {

    int yearOfMonthlyReport;
    HashMap<Integer, Double> monthlyProfitLossMap;
    HashMap<Integer, ArrayList<String>> monthlyReports;

    MonthlyReport() {
        monthlyReports = new HashMap<>();
    }

    public void loadMonthlyReports(Scanner sc, FileReader fileReader, ReportEngine reportEngine) {
        System.out.println("За какой год считать месячные отчеты?\nВведите год в формате \"YYYY\"");
        String userInputYear = sc.nextLine();
        String yearReportName = Interaction.yearCheck(userInputYear);
        if (yearReportName.equals("ERROR")) {
            System.out.println("[" + userInputYear + "] - неверный формат года!");
        } else {
            System.out.println("Сколько месячных отчетов вы хотите считать?");
            String userInputQuantity = sc.nextLine();
            int quantity = Interaction.strCheck(userInputQuantity);
            if (quantity < 1 || quantity > 12) {
                System.out.println("[" + userInputQuantity + "] - Неверное количество отчетов");
            } else {
                int uploadCount = 0;
                for (int i = 1; i <= quantity; i++) {
                    String fileName = String.format("m%s%d%d.csv", yearReportName, 0, i);
                    ArrayList<String> list = fileReader.readFileContents(fileName);
                    if (list.isEmpty()) {
                        System.out.println("Попробуйте снова!");
                        monthlyReports.put(i, list);
                    } else {
                        list.remove(0);
                        monthlyReports.put(i, list);
                        uploadCount++;
                        System.out.println("Отчет за " + reportEngine.monthName(i) + " успешно считан и сохранен!");
                    }
                }
                monthlyProfitLossMap = reportEngine.monthlyReportsToProfitLossMap(monthlyReports);
                yearOfMonthlyReport = Interaction.strCheck(userInputYear);
                System.out.println(uploadCount + " отчетов считаны и сохранены!");
            }
        }
    }
}
