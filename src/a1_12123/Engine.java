package src.a1_12123;
import java.util.*;
import java.io.*;

public class Engine {
    private List<Doc> documents;

    public int loadDocs(String dirname) {
        File directory = new File(dirname);
        File[] files = directory.listFiles();
        if (files == null) {
            return 0;
        }

        documents = new ArrayList<>();

        for (File file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String title = reader.readLine();
                String body = reader.readLine();
                Doc doc = new Doc(title + "\n" + body);
                documents.add(doc);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return documents.size();
    }

    public Doc[] getDocs() {
        return documents.toArray(new Doc[0]);
    }

    public List<Result> search(Query q) {
        List<Result> results = new ArrayList<>();

        for (Doc doc : documents) {
            List<Match> matches = q.matchAgainst(doc);
            if (!matches.isEmpty()) {
                results.add(new Result(doc, matches));
            }
        }

        results.sort(Comparator.reverseOrder()); 
        return results;
    }

    public String htmlResult(List<Result> results) {
        StringBuilder html = new StringBuilder();
    
        for (Result result : results) {
            //html.append(result.getDoc().getTitle()).append("<br>");
            html.append(result.htmlHighlight());
        }
    
        return html.toString();
    }
    
}