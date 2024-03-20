package src.a1_12123;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Query {
    private List<Word> keywords;

    public Query(String searchPhrase) {
        createQuery(searchPhrase);
    }

    public List<Word> getKeywords() {
        return keywords;
    }

    public List<Match> matchAgainst(Doc d) {
        List<Word> documentWords = new ArrayList<>(d.getTitle());
        documentWords.addAll(d.getBody());

        List<Match> matches = new ArrayList<>();

        for (Word keyword : keywords) {
            int freq = 0;
            int firstIndex = -1;

            for (int i = 0; i < documentWords.size(); i++) {
                Word documentWord = documentWords.get(i);
                if (keyword.equals(documentWord)) {
                    if (firstIndex == -1) {
                        firstIndex = i;
                    }
                    freq++;
                }
            }

            if (freq > 0) {
                matches.add(new Match(d, keyword, freq, firstIndex));
            }
        }

        // Sort the matches by firstIndex
        Collections.sort(matches);

        return matches;
    }


    public void createQuery(String searchPhrase) {
        // Split the search phrase by spaces
        String[] words = searchPhrase.split("\\s+");
        keywords = new ArrayList<>();

        for (String wordText : words) {
            // Remove any special characters from the word text
            String cleanedText = wordText;

            // Create a Word object from the cleaned text
            Word word = Word.createWord(cleanedText);

            // Check if the text is a keyword
            if (word.isKeyword()) {
                keywords.add(word);
            }
        }
    }
}
