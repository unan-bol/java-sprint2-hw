import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    public ArrayList<Month> months = new ArrayList<>();
    public void loadFile(int monthNumber, String  path){
        String content = readFileContents(path);
        String[] lines = content.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            String item_name = parts[0];
            boolean is_expense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sum_of_one = Integer.parseInt(parts[3]);

            Month month =  new Month(item_name, is_expense, quantity, sum_of_one, monthNumber);
            months.add(month);
        }
    }

    public void monthReport(){
        for (int i = 1; i < 4; i++) {

            if (i == 1){
                System.out.println("Январь");
            }
            else if (i == 2){
                System.out.println("Февраль");
            }
            else if (i == 3){
                System.out.println("Март");
            }

            HashMap<String, Integer> incomeByMonth = new HashMap<>();
            for (Month month : months) {
                if ((month.monthNumber == i) && (!month.is_expense)){
                    incomeByMonth.put(month.item_name, month.quantity * month.sum_of_one);
                }
            }
            String maxIncomeNames = null;
            for (String name : incomeByMonth.keySet()) {
                if(maxIncomeNames == null){
                    maxIncomeNames = name;
                }
                if (incomeByMonth.get(maxIncomeNames) < incomeByMonth.get(name)){
                    maxIncomeNames = name;
                }
            }
            System.out.println("Самый прибыльный товар: " + maxIncomeNames + ", сумма: " + incomeByMonth.get(maxIncomeNames));

            HashMap<String, Integer> expenseByMonth = new HashMap<>();
            for (Month month : months) {
                if ((month.monthNumber == i) && (month.is_expense)){
                    expenseByMonth.put(month.item_name, month.quantity * month.sum_of_one);
                }
            }
            String maxExpenseNames = null;
            for (String name : expenseByMonth.keySet()) {
                if(maxExpenseNames == null){
                    maxExpenseNames = name;
                }
                if (expenseByMonth.get(maxExpenseNames) < expenseByMonth.get(name)){
                    maxExpenseNames = name;
                }
            }
            System.out.println("Самая большая трата: " + maxExpenseNames + ", сумма: " + expenseByMonth.get(maxExpenseNames));
        }
    }

    public String readFileContents(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }
}


