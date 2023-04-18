import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    public ArrayList<Year> years = new ArrayList<>();
    public void loadFile(String path){
       String content = readFileContents(path);
       String[] lines = content.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean is_expenses = Boolean.parseBoolean(parts[2]);

            Year year = new Year(month, amount, is_expenses);
            years.add(year);
        }
    }
    
    public void yearReport(){
        System.out.println("Год 2021");

        int monthIncome = 0;
        int monthExpense = 0;
        for (int i = 1; i < 4; i++) {
            int expense = 0;
            int income = 0;
            for (Year year : years) {
                if ((year.month == i) && (!year.is_expenses)){
                    income = year.amount;
                    monthIncome += income;
                }else if ((year.month == i) && (year.is_expenses)){
                    expense = year.amount;
                    monthExpense += expense;
                }

            }
            System.out.println("Прибыль за " + i + " месяц: " + (income-expense));
        }
        System.out.println("Средний расход за все месяцы в году: " + monthExpense/3);
        System.out.println("Средний доход за все месяцы в году: " + monthIncome/3);
    }

    public String readFileContents(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }
}
