package kata;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Kata {

    
    // метод для калькулятора
    public static String calc(String input) throws InputMismatchException {
        //будем использовать массивы стринговых чисел, арифиметические операции будут проходить с индексами элементов массива, которые идентичны смыслу стрингового элемента 
        String[] arrayArab = {"1", "2", "3", "4", "5", "6", "7", "8", "9"}; 
        String[] arrayRoman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] arrayRomanResult = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        ArrayList<String> listArabic = new ArrayList<>(Arrays.asList(arrayArab)); //перевод в arrayList, чтобы использовать методы arrayList 
        ArrayList<String> listRoman = new ArrayList<>(Arrays.asList(arrayRoman));

        String result = input; //присваиваем параметор переменной

        String[] s = input.split("[+-/*]");  //разбиваем входную строку на две части, разделяясь по арифметическому знаку. Загоняем в масси результат
        String[] sTrim = new String[s.length]; //проходим по новому массиву и обрезаем пробелы на элементах
        for (int i = 0; i < s.length; i++) {
            sTrim[i] = s[i].trim();
        }
        if (sTrim.length == 2) { //элементов должно быть два, иначе введено что-то не то, выбрасываем exeption в else 
            if (listArabic.contains(sTrim[0]) && listArabic.contains(sTrim[1])) { //если оба элемента есть в массиве опроных значений (сначала арабских), можно работать с инжексами этих опорных значений

                switch (findArithmeticSign(input)) { //используем метод findArithmeticSign для опеределения арифм знака
                    case '+':
                        result = Integer.toString(((listArabic.indexOf(sTrim[0]) + 1) + (listArabic.indexOf(sTrim[1]) + 1)));
                        break;
                    case '-':
                        result = Integer.toString(((listArabic.indexOf(sTrim[0]) + 1) - (listArabic.indexOf(sTrim[1]) + 1)));
                        break;
                    case '*':
                        result = Integer.toString(((listArabic.indexOf(sTrim[0]) + 1) * (listArabic.indexOf(sTrim[1]) + 1)));
                        break;
                    case '/':
                        result = Integer.toString(((listArabic.indexOf(sTrim[0]) + 1) / (listArabic.indexOf(sTrim[1]) + 1)));
                        break;
                }

            } else if (listRoman.contains(sTrim[0]) && listRoman.contains(sTrim[1])) { //если не в опроных арабских, то проверяем в опорных римских, если и туту нету, то в след. else выброси exeption
                switch (findArithmeticSign(input)) {
                    case '+':
                        result = arrayRomanResult[((listRoman.indexOf(sTrim[0]) + 1) + (listRoman.indexOf(sTrim[1]) + 1)) - 1];
                        break;
                    case '-':

                        if ((((listRoman.indexOf(sTrim[0]) + 1) - (listRoman.indexOf(sTrim[1]) + 1)) - 1) <1) {
                            try {                                                   //провернка на отрицательное римское число
                            throw new InputMismatchException();
                            }
                            catch (InputMismatchException e){
                                System.out.println("римских чисел отрицательных не бывает, как не бывает и нуля :)");
                                result="ошибка, ввидите другие числа";
                            }
                            
                        } else {
                            result = arrayRomanResult[((listRoman.indexOf(sTrim[0]) + 1) - (listRoman.indexOf(sTrim[1]) + 1)) - 1];
                        }
                        break;
                    case '*':
                        result = arrayRomanResult[((listRoman.indexOf(sTrim[0]) + 1) * (listRoman.indexOf(sTrim[1]) + 1)) - 1];
                        break;
                    case '/':
                        result = arrayRomanResult[((listRoman.indexOf(sTrim[0]) + 1) / (listRoman.indexOf(sTrim[1]) + 1)) - 1];
                        break;
                }

            } else {
                throw new InputMismatchException("введите только числа, только от единицы до десяти, только арабскими или римскими числами");

            }
        } else {
            throw new InputMismatchException("введите выражение в правильном формате");
        }

        return (result);

    }

    public static char findArithmeticSign(String input) { //метод для нахождения арифм знака
        char arithmeticSign = '1';

        if (input.contains("+")) {
            arithmeticSign = '+';
        } else if (input.contains("-")) {
            arithmeticSign = '-';
        } else if (input.contains("*")) {
            arithmeticSign = '*';
        } else if (input.contains("/")) {
            arithmeticSign = '/';
        }

        return (arithmeticSign);

    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите выражение арабскими или римскими числами от единицы до десяти ");
            Scanner in = new Scanner(System.in);
            String num = in.nextLine();
            System.out.println("результат: " + calc(num));
            System.out.println();
        }
    }
}
