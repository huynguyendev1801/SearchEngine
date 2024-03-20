package src.a1_12123;

import java.util.*;

public class Result implements Comparable<Result> {
    private Doc doc;
    private List<Match> matches;

    public Result(Doc doc, List<Match> matches) {
        this.doc = doc;
        this.matches = matches;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public int getTotalFrequency() {
        int totalFreq = 0;
        for (Match match : matches) {
            totalFreq += match.getFreq();
        }
        return totalFreq;
    }

    public Doc getDoc() {
        return doc;
    }

    public double getAverageFirstIndex() {
        if (matches.isEmpty()) {
            return 0.0;
        }
        int totalFirstIndex = 0;
        for (Match match : matches) {
            totalFirstIndex += match.getFirstIndex();
        }
        return (double) totalFirstIndex / matches.size();
    }
    public String htmlHighlight() {
        StringBuilder html = new StringBuilder();

        html.append("<h3>"); // Add an <h3> tag for the title

        for (Word word : doc.getTitle()) {
            boolean matched = false;
            for (Match match : matches) {
                if (match.getWord().equals(word)) {
                    html.append("<u>").append(word.getPrefix()).append(word.getText()).append(word.getSuffix()).append("</u> ");
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                html.append(word.getPrefix()).append(word.getText()).append(word.getSuffix()).append(" ");
            }
        }
        if (html.length() > 0 && Character.isWhitespace(html.charAt(html.length() - 1))) {
            html.deleteCharAt(html.length() - 1); // Xóa ký tự cuối cùng nếu nó là khoảng trắng và StringBuilder không rỗng
        }

        html.append("</h3>"); // Close the <h3> tag without extra space
        html.append("<p>");  // Add a paragraph tag to separate title and body.

        for (Word word : doc.getBody()) {
            boolean matched = false;
            for (Match match : matches) {
                if (match.getWord().equals(word)) {
                	html.append("<b>").append(word.getPrefix()).append(word.getText()).append("</b>").append(word.getSuffix()).append(" ");
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                html.append(word.getPrefix()).append(word.getText()).append(word.getSuffix()).append(" ");
            }
        }
        if (html.length() > 0 && Character.isWhitespace(html.charAt(html.length() - 1))) {
            html.deleteCharAt(html.length() - 1); // Xóa ký tự cuối cùng nếu nó là khoảng trắng và StringBuilder không rỗng
        }

        html.append("</p>");
        return html.toString();
    }



    @Override
    public int compareTo(Result o) {
        // Compare results based on specified criteria
        if (this.matches.size() != o.matches.size()) {
            return Integer.compare(this.matches.size(), o.matches.size()); // Compare match count
        } else if (this.getTotalFrequency() != o.getTotalFrequency()) {
            return Integer.compare(this.getTotalFrequency(), o.getTotalFrequency()); // Compare total frequency
        } else {
            return Double.compare(this.getAverageFirstIndex(), o.getAverageFirstIndex()); // Compare average first index
        }
    }
}
