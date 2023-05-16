import java.util.*;

public class MonthlyReport {

    int yearOfMonthlyReport;
    HashMap<Integer, Double> monthlyProfitLossMap;


    HashMap<Integer, ArrayList<String>> monthlyReports;

    MonthlyReport() {
        monthlyReports = new HashMap<>();
    }

    public HashMap<Integer, Double> monthlyReportsToProfitLossMap(HashMap<Integer, ArrayList<String>> monthlyReports) {
        HashMap<Integer, Double> resultMap = new HashMap<>();
        for (int i = 1; i <= monthlyReports.size(); i++) {
            double sum = 0;
            for (int j = 0; j < monthlyReports.get(i).size(); j++) {
                String[] arr = monthlyReports.get(i).get(j).split(",");
                if (arr[1].equals("TRUE")) {
                    sum -= (Double.parseDouble(arr[2]) * Double.parseDouble(arr[3]));
                } else {
                    sum += (Double.parseDouble(arr[2]) * Double.parseDouble(arr[3]));
                }
            }
            resultMap.put(i, sum);
        }
        return resultMap;
    }
}
