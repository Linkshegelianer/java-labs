package exercise;

import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String filePath1, String filePath2, String outputPath) throws Exception {
        CompletableFuture<String> future = new CompletableFuture<String>();
        Path path1 = Paths.get(filePath1);
        Path path2 = Paths.get(filePath2);
        Path outputPathObj = Paths.get(outputPath);

        CompletableFuture<String> readFile1 = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(path1);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
            return null;
        });

        CompletableFuture<String> readFile2 = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(path2);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
            return null;
        });

//        return readFile1.thenCombine(readFile2, (first, second) -> {
//            String file1Content = readFile1.join();
//            String file2Content = readFile2.join();
//            try {
//                Files.writeString(outputPathObj, file1Content, StandardOpenOption.CREATE);
//                Files.writeString(outputPathObj, file2Content, StandardOpenOption.APPEND);
//            } catch (Exception e) {
//                future.completeExceptionally(e);
//            }
//        }).writeToFile.exceptionally(e -> {
//            System.out.println("Something went wrong - " + e.getMessage());
//            return null;
//        });



        CompletableFuture<Void> writeToFile = CompletableFuture.allOf(readFile1, readFile2).thenRunAsync(() -> {
            String file1Content = readFile1.join();
            String file2Content = readFile2.join();

            Runnable writeTask = () -> {
                try {
                    Files.writeString(outputPathObj, file1Content, StandardOpenOption.CREATE);
                    Files.writeString(outputPathObj, file2Content, StandardOpenOption.APPEND);
                    future.complete(outputPath);
                } catch (Exception e) {
                    future.completeExceptionally(e);
                }
            };
            Thread writeThread = new Thread(writeTask);
            writeThread.start();
        });
        return future;
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

