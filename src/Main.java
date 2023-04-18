import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        YearlyReport yearlyReport = new YearlyReport();

        MonthlyReport monthlyReport = new MonthlyReport();;

        Checker checker =  new Checker(yearlyReport, monthlyReport);

        while(true){
            printMenu();
            int command = scanner.nextInt();
            if (command==1){
                monthlyReport.loadFile(1,"resources/m.202101.csv");
                monthlyReport.loadFile(2,"resources/m.202102.csv");
                monthlyReport.loadFile(3,"resources/m.202103.csv");
                System.out.println("Считывание файлов прошло успешно");
            }else if (command==2){
                yearlyReport.loadFile("resources/y.2021.csv");
                System.out.println("Считывание файлов прошло успешно");
            }else if (command==3){
                if (!monthlyReport.months.isEmpty()) {
                    if (!yearlyReport.years.isEmpty()) {
                        boolean answer = checker.check();
                        System.out.println("Результат проверки " + answer);
                    } else{
                        System.out.println("Считайте годовой отчет");
                    }
                } else{
                    System.out.println("Считайте месячный отчет");
                }
            }else if (command==4){
                if (!monthlyReport.months.isEmpty()) {
                    monthlyReport.monthReport();
                }
                else{
                    System.out.println("Считайте месячный отчет");
                }
            }else if (command==5) {
                if (!yearlyReport.years.isEmpty()) {
                    yearlyReport.yearReport();
                } else{
                    System.out.println("Считайте годовой отчет");
                }
            }else if (command==0) {
                System.out.println("Выполняется команда: Выход");
                System.out.println("Пока!");
                break;
            }else{
                System.out.println("Такой команды  нет");
            }

        }
    }


    static void printMenu(){
        System.out.println("Что вы хотите сделать?");
        System.out.println("1.Считать все месячные отчёты");
        System.out.println("2.Считать годовой отчёт");
        System.out.println("3.Сверить отчёты");
        System.out.println("4.Вывести информацию о всех месячных отчётах");
        System.out.println("5.Вывести информацию о годовом отчёте");
        System.out.println("0.Выход");
    }
}

