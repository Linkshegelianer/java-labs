package exercise;

import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String filePath1, String filePath2, String outputPath) throws Exception {
        // create Path objects
        Path path1 = Paths.get(filePath1);
        Path path2 = Paths.get(filePath2);
        Path outputPathObj = Paths.get(outputPath);

        // read content of the first file
        CompletableFuture<String> content1 = CompletableFuture.supplyAsync(() -> {
            String content = "";

            try {
                content = Files.readString(path1);
            } catch (Exception e) {
                throw new RuntimeException(e); // but the test wait for NoSuchFileException, dunno why
            }
            return content;
        });

        CompletableFuture<String> content2 = CompletableFuture.supplyAsync(() -> {

            String content = "";
            try {
                content = Files.readString(path2);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return content;
        });

        return content1.thenCombine(content2, (cont1, cont2) -> {
            String union = cont1 + cont2;
            try {
                Files.writeString(outputPathObj, union, StandardOpenOption.CREATE);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return "ok!";

        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "Unknown!";
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        String path1 = "src/resources/file1.txt";
        String path2 = "src/resources/file2.txt";
        String outputPath = "src/main/resources/output.txt";

        CompletableFuture<String> result = App.unionFiles(path1, path2, outputPath);
        System.out.println(result);
        // END
    }
}

