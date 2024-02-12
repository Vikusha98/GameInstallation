import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GameInstallation {
    static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) {
        List<String> directoriesToCreate = Arrays.asList(
                "C:/Users/victo/Desktop/Games/src",
                "C:/Users/victo/Desktop/Games/res",
                "C:/Users/victo/Desktop/Games/savegames",
                "C:/Users/victo/Desktop/Games/temp",
                "C:/Users/victo/Desktop/Games/src/main",
                "C:/Users/victo/Desktop/Games/src/test",
                "C:/Users/victo/Desktop/Games/res/drawables",
                "C:/Users/victo/Desktop/Games/res/vectors",
                "C:/Users/victo/Desktop/Games/res/icons"
        );

        for (String directory : directoriesToCreate) {
            createDirectory(directory);
        }

        List<String> filesToCreate = Arrays.asList(
                "C:/Users/victo/Desktop/Games/src/main/Main.java",
                "C:/Users/victo/Desktop/Games/src/main/Utils.java",
                "C:/Users/victo/Desktop/Games/temp/temp.txt"
        );

        for (String file : filesToCreate) {
            createFile(file);
        }

        writeLogToFile("C:/Users/victo/Desktop/Games/temp/temp.txt", builder.toString());
    }

    public static void createDirectory(String path) {
        File folder = new File(path);
        if (folder.mkdir()) {
            builder.append("Директория " + path + " создана\n");
        } else {
            builder.append("Ошибка создания директории " + path + "\n");
        }
    }

    public static void createFile(String path) {
        File newFile = new File(path);
        try {
            if (newFile.createNewFile()) {
                builder.append("Файл " + path + " создан\n");
            } else {
                builder.append("Ошибка создания файла " + path + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLogToFile(String filePath, String log) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(log);
            writer.close;
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
