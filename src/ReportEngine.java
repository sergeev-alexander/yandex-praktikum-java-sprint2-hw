import java.util.*;

public class ReportEngine {

    public void verifyReports(HashMap<Integer, Double> yearMap, HashMap<Integer, Double> monthMap) {
        boolean isEqual = Objects.equals(yearMap, monthMap);
        if (isEqual) {
            System.out.println("Сверка прошла успешно!");
        } else {
            for (int i = 1; i <= yearMap.size(); i++) {
                if (!yearMap.get(i).equals(monthMap.get(i))) {
                    System.out.println("Расхождение в " + i + " месяце!");
                    System.out.println("В годовом отчете сумма ---> " + yearMap.get(i));
                    System.out.println((monthMap.get(i) == null) ? ("Отчет за " + i + " месяц не считан!") : ("В месячном отчете сумма --> " + monthMap.get(i)));
                }
            }
        }
    }

    public void printYearlyStatistics(int yearOfReport, HashMap<Integer, Double> yearMap, ArrayList<String> yearlyList) {
        System.out.println("Статистика годового отчета за " + yearOfReport + " год:");
        for (int i = 1; i <= yearMap.size(); i++) {
            System.out.println("Прибыль / убыток за " + monthName(i) + " " + yearOfReport + " года --> " + yearMap.get(i));
        }
        int monthQuantity = yearlyList.size() - 1;
        double income = 0;
        double expenses = 0;
        for (int i = 0; i < monthQuantity; i++) {
            String[] arr = yearlyList.get(i).split(",");
            if (Boolean.parseBoolean(arr[2])) {
                expenses += Double.parseDouble(arr[1]);
            } else {
                income += Double.parseDouble(arr[1]);
            }
        }
        System.out.println("Средний расход за все имеющиеся операции в " + yearOfReport + " году --> " + (expenses / monthQuantity));
        System.out.println("Средний доход за все имеющиеся операции в " + yearOfReport + " году ---> " + (income / monthQuantity));
    }

    public void printMonthlyStatistics(HashMap<Integer, ArrayList<String>> map) {
        for (int i = 1; i <= map.size(); i++) {
            System.out.println("Статистика за " + monthName(i) + ":");
            String maxExpenseArticle = "";
            double maxExpense = 0;
            String maxProfitArticle = "";
            double maxProfit = 0;
            for (int j = 0; j < map.get(i).size(); j++) {
                String[] arr = map.get(i).get(j).split(",");
                if (Boolean.parseBoolean(arr[1])) {
                    if (Integer.parseInt(arr[2]) * Double.parseDouble(arr[3]) > maxExpense) {
                        maxExpense = (Integer.parseInt(arr[2]) * Double.parseDouble(arr[3]));
                        maxExpenseArticle = arr[0];
                    }
                } else {
                    if (Integer.parseInt(arr[2]) * Double.parseDouble(arr[3]) > maxProfit) {
                        maxProfit = (Integer.parseInt(arr[2]) * Double.parseDouble(arr[3]));
                        maxProfitArticle = arr[0];
                    }
                }
            }
            System.out.println("Самый прибыльный товар - \"" + maxProfitArticle + "\" на сумму --> " + maxProfit);
            System.out.println("Самая большая трата - \"" + maxExpenseArticle + "\" на сумму --> " + maxExpense);
        }
    }

    String monthName(int month) {
        switch (month) {
            case 1:
                return "январь";
            case 2:
                return "февраль";
            case 3:
                return "март";
            case 4:
                return "апрель";
            case 5:
                return "май";
            case 6:
                return "июнь";
            case 7:
                return "июль";
            case 8:
                return "август";
            case 9:
                return "сентябрь";
            case 10:
                return "октябрь";
            case 11:
                return "ноябрь";
            case 12:
                return "декабрь";
            default:
                return ("[" + month + "] - Некорректный номер месяца!");
        }
    }
}


