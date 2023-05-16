import java.util.*;

public class YearlyReport {

    int yearOfReport;
    ArrayList<String> yearlyList;
    HashMap<Integer, Double> profitLossMap;

    YearlyReport() {
        yearlyList = new ArrayList<>();
    }


    public HashMap<Integer, Double> yearlyListToProfitLossMap(ArrayList<String> yearlyList) {
        HashMap<Integer, Double> yearlyMap = new HashMap<>();
        int num = 1;
        double sum = 0;
        for (int i = 0; i < yearlyList.size() - 1; i += 2) {
            String[] arr1 = yearlyList.get(i).split(",");
            if (arr1[2].equals("true")) {
                sum -= Double.parseDouble(arr1[1]);
            } else {
                sum += Double.parseDouble(arr1[1]);
            }
            String[] arr2 = yearlyList.get(i + 1).split(",");
            if (arr2[2].equals("true")) {
                sum -= Double.parseDouble(arr2[1]);
            } else {
                sum += Double.parseDouble(arr2[1]);
            }
            yearlyMap.put(num, sum);
            sum = 0;
            num++;
        }
        return yearlyMap;
    }
}

