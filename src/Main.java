import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress progress1 = new GameProgress(100, 3, 10, 150);
        GameProgress progress2 = new GameProgress(80, 5, 10, 200);
        GameProgress progress3 = new GameProgress(120, 6, 20, 150);

        String filePath = "C:/Users/victo/Desktop/Games/savegames";

        saveGame(filePath + "/save1.dat", progress1);
        saveGame(filePath + "/save2.dat", progress2);
        saveGame(filePath + "/save3.dat", progress3);

        String zipPath = "C:/Users/victo/Desktop/Games/savegames/zip.zip";

        List<String> files = new ArrayList<>();
        files.add(filePath + "/save1.dat");
        files.add(filePath + "/save2.dat");
        files.add(filePath + "/save3.dat");

        zipFiles(zipPath, files);

        deleteFiles();


    }

    public static void saveGame(String filePath, GameProgress progress) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(progress);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void zipFiles(String zipPath, List<String> files) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath))) {
            for (String filePath : files) {
                File file = new File(filePath);
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zos.putNextEntry(zipEntry);


                    byte[] buffer = new byte[1024];
                    int length;

                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }


                }
            }
            zos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFiles() {
        File savegamesFolder = new File("C:/Users/victo/Desktop/Games/savegames");
        File[] files = savegamesFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.getName().equals("zip.zip") && !file.isDirectory()) {
                    file.delete();

                }
            }
        }
    }
}










