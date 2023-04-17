import java.util.ArrayList;
import java.util.HashMap;

public class Checker {
    public YearlyReport yearlyReport;
    public MonthlyReport monthlyReport;

    public Checker(YearlyReport yearlyReport, MonthlyReport monthlyReport) {
        this.yearlyReport = yearlyReport;
        this.monthlyReport = monthlyReport;
    }

    public boolean check(){
        boolean check = true;
        HashMap<Integer, ArrayList> sumByMonth = new HashMap<>();
        for (int i = 1; i < 4; i++) {
            ArrayList<Integer> incomeAndExpense = new ArrayList<>();
            int sumIncome = 0;
            int sumExpenses = 0;
            for (Month month : monthlyReport.months) {
                if (month.monthNumber == i) {
                    if (!month.is_expense) {
                        sumExpenses += month.sum_of_one * month.quantity;
                    } else if (month.is_expense) {
                        sumIncome += month.sum_of_one * month.quantity;
                    }
                }

            }
            incomeAndExpense.add(sumExpenses);
            incomeAndExpense.add(sumIncome);
            sumByMonth.put(i, incomeAndExpense);

        }

        HashMap<Integer, ArrayList> sumByYear = new HashMap<>();
        for (int i = 1; i < 4; i++) {
            ArrayList<Integer> expenseAndIncome = new ArrayList<>();
            int expense = 0;
            int income = 0;
            for (Year year : yearlyReport.years) {
                if ((year.month == i) && (!year.is_expenses)) {
                    expense = year.amount;
                } else if ((year.month == i) && (year.is_expenses)) {
                    income = year.amount;
                }
            }
            expenseAndIncome.add(expense);
            expenseAndIncome.add(income);
            sumByYear.put(i, expenseAndIncome);
        }

        for (int i = 1; i<4 ; i++) {
            ArrayList<Integer> month = sumByMonth.get(i);
            ArrayList<Integer> year = sumByYear.get(i);
            int sumYearExpense = year.get(0);
            int sumMonthExpense = month.get(0);
            int sumYearIncome = year.get(1);
            int sumMonthIncome = year.get(1);
            if(sumMonthExpense != sumYearExpense){
                check = false;
                System.out.println("Ошибка данных в месяце " + i);
            }else if(sumMonthIncome != sumYearIncome){
                check = false;
                System.out.println("Ошибка данных в месяце " + i);
            }
        }
        return check;
    }

}
