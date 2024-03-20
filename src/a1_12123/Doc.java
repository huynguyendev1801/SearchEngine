package src.a1_12123;
import java.util.ArrayList;
import java.util.List;

public class Doc {
    private List<Word> title;
    private List<Word> body;

    public Doc(String content) {
        createDoc(content);
    }

    public List<Word> getTitle() {
        return title;
    }

    public List<Word> getBody() {
        return body;
    }

    public void createDoc(String content) {
        String[] lines = content.split("\n");
        if (lines.length != 2) {
            throw new IllegalArgumentException("Content should have exactly two lines: title and body");
        }

        title = createWords(lines[0]);
        body = createWords(lines[1]);
    }

    private List<Word> createWords(String text) {
        String[] words = text.split("\\s+");
        List<Word> wordList = new ArrayList<>();

        for (String wordText : words) {
            Word word = Word.createWord(wordText);
            wordList.add(word);
        }

        return wordList;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doc doc = (Doc) o;
        return title.equals(doc.title) && body.equals(doc.body);
    }
}
