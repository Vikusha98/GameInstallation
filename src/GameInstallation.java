import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GameInstallation {
    public static void main(String[] args) {
        String gamesFolderPath = "C:/Users/victo/Desktop/Games";
        List<String> subfolders = Arrays.asList("src", "res", "savegames", "temp");

        File gamesFolder = new File(gamesFolderPath);
        boolean isGamesFolderCreated = createFolder(gamesFolder);

        StringBuilder logBuilder = new StringBuilder();

        logStatus(isGamesFolderCreated, "Папка Games", "создана", "Ошибка при создании папки Games", logBuilder);

        for (String subfolder : subfolders) {
            File folder = new File(gamesFolderPath + "/" + subfolder);
            boolean isFolderCreated = createFolder(folder);
            logStatus(isFolderCreated, "Папка " + subfolder, "создана", "Ошибка при создании папки " + subfolder, logBuilder);
        }

        createSubFoldersAndFiles(gamesFolderPath, logBuilder);

        try {
            FileWriter writer = new FileWriter(gamesFolderPath + "/temp/temp.txt");
            writer.write(logBuilder.toString());
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean createFolder(File folder) {
        return folder.mkdir();
    }

    private static boolean createFile(File file) {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void logStatus(boolean condition, String itemName, String successMessage, String errorMessage, StringBuilder logBuilder) {
        if (condition) {
            logBuilder.append(itemName).append(" успешно ").append(successMessage).append("\n");
        } else {
            logBuilder.append(errorMessage).append("\n");
        }
    }

    private static void createSubFoldersAndFiles(String gamesFolderPath, StringBuilder logBuilder) {
        List<String> subFoldersInSrc = Arrays.asList("main", "test");
        List<String> subFoldersInRes = Arrays.asList("drawables", "vectors", "icons");

        File srcFolder = new File(gamesFolderPath + "/src");
        createFolder(srcFolder);
        for (String subFolder : subFoldersInSrc) {
            File sub = new File(srcFolder.getPath() + "/" + subFolder);
            boolean isSubFolderCreated = createFolder(sub);
            logStatus(isSubFolderCreated, "Папка " + subFolder + " в src", "создана", "Ошибка при создании папки " + subFolder + " в src", logBuilder);
        }

        File resFolder = new File(gamesFolderPath + "/res");
        createFolder(resFolder);
        for (String subFolder : subFoldersInRes) {
            File sub = new File(resFolder.getPath() + "/" + subFolder);
            boolean isSubFolderCreated = createFolder(sub);
            logStatus(isSubFolderCreated, "Папка " + subFolder + " в res", "создана", "Ошибка при создании папки " + subFolder + " в res", logBuilder);
        }

        File mainJavaFile = new File(gamesFolderPath + "/src/main/Main.java");
        File utilsJavaFile = new File(gamesFolderPath + "/src/main/Utils.java");
        boolean areFilesCreated = createFile(mainJavaFile) && createFile(utilsJavaFile);
        logStatus(areFilesCreated, "Файлы Main.java и Utils.java в src/main", "созданы", "Ошибка при создании файлов Main.java и Utils.java в src/main", logBuilder);

        File tempTxtFile = new File(gamesFolderPath + "/temp/temp.txt");
        boolean isTempTxtFileCreated = createFile(tempTxtFile);
        logStatus(isTempTxtFileCreated, "Файл temp.txt", "создан", "Ошибка при создании файла temp.txt", logBuilder);
    }
}