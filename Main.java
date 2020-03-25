import java.io.*;
import java.util.Scanner;

public class Main {

    static int maxLength = 1000;
    static String [] signs = {".", ",", "!", "?", ";", ":", "(", ")","\""};
    static String repeatedWords [];
    static int numberOfRepeatedWords = 0;

    public static int rewriteWords(Scanner file, String [] wordsFromFile){
        int numberOfWords = 0;
        int i = 0;
        while (file.hasNext()) {
            wordsFromFile[numberOfWords] = file.next();
            for (i = 0; i < signs.length; i++) {
                while (wordsFromFile[numberOfWords].endsWith(signs[i])) {
                    wordsFromFile[numberOfWords] = wordsFromFile[numberOfWords].substring(0, (wordsFromFile[numberOfWords].length()) - 1);
                }
            }
            numberOfWords++;
        }

        return numberOfWords;
    }

    public static int repeatedWordsInFile(int numberOfWords, String [] fileWords){
        int repeatedWordsInFile = 0, i = 0, j = 0;
        for(i = 0; i < numberOfWords; i++)
            for(j = 0; j < numberOfRepeatedWords; j++){
                if(fileWords[i].equals(repeatedWords[j])){
                    repeatedWordsInFile++;
                }
            }
        return repeatedWordsInFile;
    }

    public static double percentageOfRepeatedWords(int repeatedWords, int numberOfWords){

        double repeatedWordsDouble = (double)repeatedWords;
        double numberOfWordsDouble = (double)numberOfWords;
        return (repeatedWordsDouble/numberOfWordsDouble)*100;
    }

    public static void createFile(FileWriter output, Scanner input, String fileName) throws IOException {
        input = new Scanner(new BufferedReader(new FileReader(fileName)));
        String[] inputWords = new String[maxLength];
        int inputElement = 0, numberOfSignElem = 0;
        String[] tmpSigns = new String[5];
        while (input.hasNext()) {
            inputWords[inputElement] = input.next();
            for (int i = 0; i < signs.length; i++) {
                while (inputWords[inputElement].endsWith(signs[i])) {
                    tmpSigns[numberOfSignElem] = inputWords[inputElement].substring((inputWords[inputElement].length()) - 1, (inputWords[inputElement].length()));
                    inputWords[inputElement] = inputWords[inputElement].substring(0, (inputWords[inputElement].length()) - 1);
                    numberOfSignElem++;
                }

                //words modification (if it's necessary)
                for (int j = 0; j < numberOfRepeatedWords; j++) {
                    if (inputWords[inputElement].equals(repeatedWords[j])) {
                        inputWords[inputElement] = "[" + inputWords[inputElement] + "]";
                        if(numberOfSignElem != 0) {
                            for (int last = numberOfSignElem-1; last >= 0; last--) {
                                inputWords[inputElement] = inputWords[inputElement] + tmpSigns[last];
                            }
                            tmpSigns = new String[5];
                            numberOfSignElem = 0;
                        }
                    }
                    }
                }
            if(numberOfSignElem != 0) {
                for (int last = numberOfSignElem - 1; last >= 0; last--) {
                    inputWords[inputElement] = inputWords[inputElement] + tmpSigns[last];
                }
                tmpSigns = new String[5];
                numberOfSignElem = 0;
            }
                output.write(inputWords[inputElement] + " ");
                inputElement++;
            }
    }

    public static void main(String[] args) throws IOException {

        Scanner fileInFirst = null, fileInSecond = null;
        String [] firstFileWords, secondFileWords;
        firstFileWords = new String [maxLength];
        secondFileWords = new String [maxLength];
        repeatedWords = new String [maxLength];
        int firstRepeatedWords = 0, secondRepeatedWords = 0, i, j;
        int firstNumberOfWords = 0, secondNumberOfWords = 0;
        numberOfRepeatedWords = 0;
        FileWriter fileOut1 = null, fileOut2 = null;

        try {
            fileInFirst = new Scanner(new BufferedReader(new FileReader(args[0])));
            firstNumberOfWords = Main.rewriteWords(fileInFirst, firstFileWords);
            fileInSecond = new Scanner(new BufferedReader(new FileReader(args[1])));
            secondNumberOfWords = Main.rewriteWords(fileInSecond, secondFileWords);

            //searching for repetitive words
            for(i = 0; i < firstNumberOfWords; i++)
                for(j = 0; j < secondNumberOfWords; j++){
                    if(firstFileWords[i].equals(secondFileWords[j])){
                        repeatedWords[numberOfRepeatedWords] = firstFileWords[i];
                        numberOfRepeatedWords++;
                        break;
                    }
                }

            firstRepeatedWords = Main.repeatedWordsInFile(firstNumberOfWords, firstFileWords);
            secondRepeatedWords = Main.repeatedWordsInFile(secondNumberOfWords, secondFileWords);

            double firstPercentage = Main.percentageOfRepeatedWords(firstRepeatedWords, firstNumberOfWords);
            double secondPercentage = Main.percentageOfRepeatedWords(secondRepeatedWords, secondNumberOfWords);
            System.out.println(args[0] + ": " + firstPercentage + "% of repeated words in the first file");
            System.out.println(args[1] + ": " + secondPercentage + "% of repeated words in the second file");

            fileOut1 = new FileWriter("file1.txt");
            fileOut2 = new FileWriter("file2.txt");
            Main.createFile(fileOut1, fileInFirst, args[0]);
            Main.createFile(fileOut2, fileInSecond, args[1]);

        }
        catch (FileNotFoundException ex) {
            System.out.println("File error");
        }
        finally{
            if (fileInFirst != null)
                fileInFirst.close();

            if (fileInSecond != null)
                fileInSecond.close();

            if (fileOut1 != null)
                fileOut1.close();

            if (fileOut2 != null)
                fileOut2.close();

        }

    }
}
