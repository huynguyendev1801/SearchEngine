package src.a1_12123;
import java.io.*;
import java.util.*;

public class Word {
    private String prefix;
    private String suffix;
    private String text;

    public Word(String prefix, String suffix, String text) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.text = text;
    }

    public static Word createWord(String rawText) {
        String prefix = "";
        String suffix = "";
        String text = rawText;

        if(text.startsWith(",") && text.endsWith(".")) {
        	
        	return new Word(prefix, suffix, text);
        }
        
        if (text.startsWith("\"")) {
            prefix = "\"";
            text = text.substring(1);
        }
        if (text.startsWith("(")) {
            prefix = "(";
            text = text.substring(1);
        }
        if (text.startsWith("<")) {
            prefix = "<";
            text = text.substring(1);
        }
        if (text.startsWith("«")) {
            prefix = "«";
            text = text.substring(1);
        }
        if (text.endsWith(").")) {
            suffix = ").";
            text = text.substring(0, text.length() - 2);
        }
        if (text.endsWith("\".")) {
            suffix = "\".";
            text = text.substring(0, text.length() - 2);
        }
        if (text.endsWith(".")) {
            suffix = ".";
            text = text.substring(0, text.length() - 1);
        }

        // Check if the text part ends with "'s", and if so, consider it as part of the suffix
        if (text.endsWith("'s")) {
            suffix = "'s";
            text = text.substring(0, text.length() - 2);
        }
        if (text.endsWith("\":")) {
            suffix = "\":";
            text = text.substring(0, text.length() - 2);
        }
        if (text.endsWith(":")) {
            suffix = ":";
            text = text.substring(0, text.length() - 1);
        }
        if (text.endsWith(")")) {
            suffix = ")";
            text = text.substring(0, text.length() - 1);
        }
       
        if (text.endsWith(">")) {
            suffix = ">";
            text = text.substring(0, text.length() - 1);
        }
        if (text.endsWith("»")) {
            suffix = "»";
            text = text.substring(0, text.length() - 1);
        }
        if (text.endsWith(",")) {
            suffix = ",";
            text = text.substring(0, text.length() - 1);
        }
       
        return new Word(prefix, suffix, text);
    }




    public static boolean loadStopWords(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            stopWords = new HashSet<>();

            while ((line = reader.readLine()) != null) {
                stopWords.add(line);
            }

            reader.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean isKeyword() {
        String lowercaseText = text.toLowerCase().trim(); 
        
        if (lowercaseText.isEmpty()) {
            return false;
        }

        if (!lowercaseText.matches("^[a-z-]*$")) {
            return false;
        }

        return !stopWords.contains(lowercaseText);
    }



    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return this.getText().equalsIgnoreCase(word.getText());
    }


    @Override
    public String toString() {
        return prefix + text + suffix;
    }

    // Define the stopWords set
    public static Set<String> stopWords = new HashSet<>();
}
