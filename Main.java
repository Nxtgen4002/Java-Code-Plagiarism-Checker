import java.nio.file.*;
import java.util.*;

public class Main {

    // Tokenize and normalize Java source code
    static List<String> tokenize(String code) {

        // Remove single-line comments
        code = code.replaceAll("//.*", "");

        // Remove multi-line comments
        code = code.replaceAll("(?s)/\\*.*?\\*/", "");

        // Remove string literals
        code = code.replaceAll("\".*?\"", "STRING");

        // Replace numeric literals
        code = code.replaceAll("\\b\\d+\\b", "NUM");

        // Remove extra whitespace
        code = code.replaceAll("\\s+", " ");

        String[] keywords = {
                "abstract","assert","boolean","break","byte","case","catch","char",
                "class","const","continue","default","do","double","else","enum",
                "extends","final","finally","float","for","goto","if","implements",
                "import","instanceof","int","interface","long","native","new",
                "package","private","protected","public","return","short","static",
                "strictfp","super","switch","synchronized","this","throw","throws",
                "transient","try","void","volatile","while","true","false","null"
        };

        Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));

        List<String> tokens = new ArrayList<>();

        String[] words = code.split("[^A-Za-z0-9_]+");

        for (String token : words) {

            if (token.isEmpty())
                continue;

            // Normalize identifiers
            if (!keywordSet.contains(token) && Character.isLetter(token.charAt(0))) {
                tokens.add("ID");
            } else {
                tokens.add(token);
            }
        }

        return tokens;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.print("Enter first Java file: ");
            String file1 = sc.nextLine();

            System.out.print("Enter second Java file: ");
            String file2 = sc.nextLine();

            String code1 = Files.readString(Paths.get(file1));
            String code2 = Files.readString(Paths.get(file2));

            List<String> tokens1 = tokenize(code1);
            List<String> tokens2 = tokenize(code2);

            // Smaller window gives better matching
            Set<Integer> fp1 = Fingerprint.fingerprints(tokens1, 3);
            Set<Integer> fp2 = Fingerprint.fingerprints(tokens2, 3);

            // Debug Information
            System.out.println("\n========== ANALYSIS ==========");
            System.out.println("Tokens in File 1        : " + tokens1.size());
            System.out.println("Tokens in File 2        : " + tokens2.size());

            System.out.println("Fingerprints in File 1  : " + fp1.size());
            System.out.println("Fingerprints in File 2  : " + fp2.size());

            Set<Integer> common = new HashSet<>(fp1);
            common.retainAll(fp2);

            System.out.println("Matching Fingerprints   : " + common.size());

            double similarity = Similarity.jaccard(fp1, fp2);

            System.out.printf("\nSimilarity: %.2f%%%n", similarity);

            if (similarity >= 80)
                System.out.println("Result: HIGH Plagiarism Risk");
            else if (similarity >= 50)
                System.out.println("Result: MEDIUM Plagiarism Risk");
            else
                System.out.println("Result: LOW Plagiarism Risk");

        }
        catch (NoSuchFileException e) {
            System.out.println("\nError: One or both files were not found.");
            System.out.println("Place the Java files in the project folder or enter the correct path.");
        }
        catch (Exception e) {
            System.out.println("\nUnexpected Error:");
            e.printStackTrace();
        }

        sc.close();
    }
}