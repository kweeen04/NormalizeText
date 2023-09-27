import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Controller {

    public static String oneSpaceAfterSpecial(String normalizedText, String chars) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] strings = normalizedText.split("\\s*\\" + chars + "\\s*");
        for (String word : strings) {
            stringBuffer.append(word + " " + chars);
            stringBuffer.append(" ");
        }
        return stringBuffer.toString().trim().substring(0, stringBuffer.length() - 3);
    }

    public static String oneSpace(String normalizedText) {
        normalizedText = normalizedText.toLowerCase();
        normalizedText = normalizedText.replaceAll("\\s+", " ");
        normalizedText = oneSpaceAfterSpecial(normalizedText, ".");
        normalizedText = oneSpaceAfterSpecial(normalizedText, ",");
        normalizedText = oneSpaceAfterSpecial(normalizedText, ":");
        normalizedText = oneSpaceAfterSpecial(normalizedText, "\"");
        return normalizedText.trim();
    }

    public static String spaceAfterSpecialCharacters(String normalizedTetx) {
        StringBuffer stringBuffer = new StringBuffer(normalizedTetx);
        for (int i = 0; i < stringBuffer.length() - 1; i++) {
            if (stringBuffer.charAt(i) == ' ' && stringBuffer.charAt(i + 1) == '.'
                    || stringBuffer.charAt(i + 1) == ','
                    || stringBuffer.charAt(i + 1) == ':') {
                stringBuffer.deleteCharAt(i);
            }
        }
        return stringBuffer.toString().trim();
    }

    public static String uppercaseAfterDot(String normalizedText) {
        StringBuffer stringBuffer = new StringBuffer(normalizedText);
        int lineLength = stringBuffer.length();
        for (int i = 0; i < lineLength - 2; i++) {
            if (stringBuffer.charAt(i) == '.') {
                char afterDot = stringBuffer.charAt(i + 2);
                stringBuffer.setCharAt(i + 2, Character.toUpperCase(afterDot));
            }
        }
        return stringBuffer.toString().trim();
    }

    public static String spaceQuotes(String normalizedText) {
        StringBuffer stringBuffer = new StringBuffer(normalizedText);
        int quotesCount = 0;
        for ( int i = 0; i < stringBuffer.length(); i ++) {
            if (stringBuffer.charAt(i) == '"' && quotesCount % 2 == 0) {
                stringBuffer.deleteCharAt(i + 1);
                quotesCount ++;
            } else if (stringBuffer.charAt(i) == '"' && quotesCount % 2 == 1 && i != 0) {
                stringBuffer.deleteCharAt(i - 1);
                quotesCount++;
            }
        }
        return stringBuffer.toString().trim();
    }

    public static String uppercaseFirst(String normalizedText) {
        normalizedText = normalizedText.substring(3);
        StringBuffer stringBuffer = new StringBuffer(normalizedText);
        for ( int i = 0; i < normalizedText.length(); i ++) {
            if (Character.isLetter(normalizedText.charAt(i))) {
                stringBuffer.setCharAt(i, Character.toUpperCase(normalizedText.charAt(i)));
                break;
            }
        }
        return stringBuffer.toString().trim();
    }

    public static String addDot(String normalizedText) {
        if (!normalizedText.endsWith(".")) {
            normalizedText += ".";
        }
        return normalizedText;
    }

    public static boolean isEmptyLine(String normalizedText) {
        if (normalizedText.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static int lineCount() {
        int lineCount = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("output.txt", true)));
            String normalizedText;

            while ((normalizedText = br.readLine()) != null) {
                if (isEmptyLine(normalizedText)) {
                    continue;
                }
                lineCount++;
            }
            br.close();
            pw.close();
        } catch (FileNotFoundException e) {
            System.err.println("Can not found file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineCount;
    }
    
}
