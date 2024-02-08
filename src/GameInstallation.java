import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GameInstallation {
    public static void main(String[] args) {
        String gamesFolderPath = "C:/Users/victo/Desktop/Games";
        List<String> directoriesToCreate = Arrays.asList(
                "C:/Users/victo/Desktop/Games/src",
                "C:/Users/victo/Desktop/Games/res",
                "C:/Users/victo/Desktop/Games/savegames",
                "C:/Users/victo/Desktop/Games/temp"
        );

        createDirectories(directoriesToCreate);

        List<String> subFoldersInSrc = Arrays.asList("main", "test");
        List<String> subFoldersInRes = Arrays.asList("drawables", "vectors", "icons");

        createSubDirectories(gamesFolderPath + "/src", subFoldersInSrc);
        createSubDirectories(gamesFolderPath + "/res", subFoldersInRes);

        createFiles(gamesFolderPath);

        StringBuilder logBuilder = new StringBuilder("Лог создания директорий и файлов:\n");
        logBuilder.append("Все директории и файлы успешно созданы.");

        writeLogToFile(gamesFolderPath + "/temp/temp.txt", logBuilder.toString());
    }

    private static void createDirectories(List<String> directories) {
        for (String directory : directories) {
            File folder = new File(directory);
            boolean isFolderCreated = folder.mkdir();
            logStatus(isFolderCreated, "Директория " + directory, "создана", "Ошибка при создании директории " + directory);
        }
    }

    private static void createSubDirectories(String parentDirectory, List<String> subDirectories) {
        for (String subDirectory : subDirectories) {
            String directoryPath = parentDirectory + "/" + subDirectory;
            File folder = new File(directoryPath);
            boolean isFolderCreated = folder.mkdir();
            logStatus(isFolderCreated, "Директория " + directoryPath, "создана", "Ошибка при создании директории " + directoryPath);
        }
    }

    private static void createFiles(String gamesFolderPath) {
        List<String> filesToCreate = Arrays.asList(
                gamesFolderPath + "/src/main/Main.java",
                gamesFolderPath + "/src/main/Utils.java",
                gamesFolderPath + "/temp/temp.txt"
        );

        for (String file : filesToCreate) {
            File newFile = new File(file);
            try {
                boolean isFileCreated = newFile.createNewFile();
                logStatus(isFileCreated, "Файл " + file, "создан", "Ошибка при создании файла " + file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeLogToFile(String filePath, String log) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(log);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void logStatus(boolean condition, String itemName, String successMessage, String errorMessage) {
        if (condition) {
            System.out.println(itemName + " " + successMessage);
        } else {
            System.out.println(errorMessage);
        }
    }
}