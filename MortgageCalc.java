package com.javalessons.task3.task;


public class MortgageCalc {

    public static void main(String[] args) {

        System.out.println("для начала работы введите через пробел срок кредитования, сумму, процент, " +
                "суммарный доход, количество членов семьи, [опционально планируемая доп. плата]");
        final int SHORT_LENGTH = 5;
        final int HEIGHT_LENGTH = 6;

        if (args.length >= SHORT_LENGTH && args.length <= HEIGHT_LENGTH) {
            int currentMonth = 0;

            Calculator calc;

            int years = Integer.parseInt(args[0]);
            float loanAmount = Float.parseFloat(args[1]);
            float interestRate = Float.parseFloat(args[2]);
            float summaryIncome = Float.parseFloat(args[3]);
            int familyMembers = Integer.parseInt(args[4]);

            float extraMonthlyPayment = 0.0f;

            if (args.length == HEIGHT_LENGTH) {
                System.out.println("берём входные параметры ветка 2");
                extraMonthlyPayment = Float.parseFloat(args[5]);
            }
            System.out.printf("y = %3d, loan = %12.2f, intrstRate = %7.2f, sumInc = %10.2f, " +
                            "members = %2d, extra = %10.2f %n",
                    years, loanAmount, interestRate, summaryIncome, familyMembers, extraMonthlyPayment);

            calc = new Calculator(years, loanAmount, interestRate, extraMonthlyPayment);
//                System.out.println("Введите количество членов семьи (от 1 до 5)");
//                Scanner sc = new Scanner(System.in);
//                familyMembers = sc.nextInt();
//                System.out.println("членов семьи = " + familyMembers);
//                System.out.println("Введите суммарный доход семьи");
//                summaryIncome = sc.nextFloat();
//                System.out.println("доход = " + summaryIncome);
//                int years = 10;
//                float loanAmount = 3000000;
//                float interestRate = 10;
//                float extraMonthlyPayment = 0F;
//                calc = new Calculator(years, loanAmount, interestRate);

            float pmnt = calc.Payment();
            if (((summaryIncome * 0.5 > pmnt) && (familyMembers == 1)) ||
                    ((summaryIncome * 0.45 > pmnt) && (familyMembers == 2)) ||
                    ((summaryIncome * 0.35 > pmnt) && (familyMembers == 3)) ||
                    ((summaryIncome * 0.30 > pmnt) && (familyMembers == 4)) ||
                    ((summaryIncome * 0.25 > pmnt) && (familyMembers == 5))) {

                System.out.println("MONTH, STARING_BALANCE, PAYMENT,     INTEREST,  PRINCIPAL,   ENDING_BALANCE, TOTAL_INTEREST");
                do {
                    currentMonth++;
                    float payment = calc.Payment() + calc.getExtraMonthlyPayment();
                    float stBal = calc.getEndingBalance();
                    calc.setStaringBalance(stBal);
                    float endBal = calc.EndingBalance();
                    calc.setEndingBalance(endBal);
                    float principal = calc.Principal();
                    float interest = calc.Interest();
                    float totInterest = calc.getTotalInterest() + interest;
                    calc.setTotalInterest(totInterest);
                    if (stBal < payment) {
                        payment = principal = stBal;
                        calc.setEndingBalance(-0.0001F);
                        endBal = calc.getEndingBalance();
                    }
                    System.out.printf("%3d; %13.2f; %14.2f; %10.2f; %10.2f; %12.2f; %12.2f%n",
                            currentMonth, stBal, payment, interest, principal, endBal, totInterest);
                } while ((calc.getEndingBalance() >= 0) /*&& currentMonth < calc.getMonth()*/);

            } else {
                System.out.printf("К нашему сожалению кредит на %10.2f руб." +
                        "при доходе %9.2f руб. на срок %3d лет " +
                        "и ежемесечном платеже %8.2f руб. НЕВОЗМОЖЕН :(", loanAmount, summaryIncome, years, pmnt);
            }
        }
    }
}

