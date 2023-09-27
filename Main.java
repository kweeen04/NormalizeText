import java.io.*;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            int lineCount = Controller.lineCount();
            int count = 1;
            br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "UTF-8"));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("output.txt", true)));
            String normalizedText;

            while ((normalizedText = br.readLine()) != null) {
                if (Controller.isEmptyLine(normalizedText)) {
                    continue;
                }
                normalizedText = normalizedText.replace("“", "\"").replace("”", "\"");
                normalizedText = Controller.oneSpace(normalizedText);
                normalizedText = Controller.spaceAfterSpecialCharacters(normalizedText);
                normalizedText = Controller.uppercaseAfterDot(normalizedText);
                normalizedText = Controller.spaceQuotes(normalizedText);
                normalizedText = Controller.uppercaseFirst(normalizedText);
                normalizedText = Controller.addDot(normalizedText);
                pw.print(normalizedText);
                if (count < lineCount) {
                    pw.print(System.getProperty("line.separator"));
                }
                count++;
            }
            br.close();
            pw.close();
            System.out.println("Normalize successfully!");
        } catch (FileNotFoundException e) {
            System.err.println("Can not found file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
