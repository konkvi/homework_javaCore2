package task2save;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        //Создать три экземпляра класса GameProgress.
        GameProgress progress1 = new GameProgress(10, 9, 1, 88);
        GameProgress progress2 = new GameProgress(9, 10, 2, 888);
        GameProgress progress3 = new GameProgress(8, 11, 3, 8888);

        //Сохранить сериализованные объекты GameProgress в папку savegames из предыдущей задачи.
        String savegamesPath = "C://Games//savegames/";
        saveGame(savegamesPath + "save1.dat", progress1);
        saveGame(savegamesPath + "save2.dat", progress2);
        saveGame(savegamesPath + "save3.dat", progress3);

        //Созданные файлы сохранений из папки savegames запаковать в архив zip.
        String zipPath = savegamesPath + "/mySaveGames.zip";
        File saveGamesDir = new File(savegamesPath);
        if (saveGamesDir.isDirectory())
            zipFiles(zipPath, saveGamesDir.listFiles());
        else
            System.out.println("Запрашиваемая директория не существует");

        //Удалить файлы сохранений, лежащие вне архива.
        File zip = new File(zipPath);
        deleteFiles(saveGamesDir.listFiles(), zip.getName());

    }
    //Реализуйте метод saveGame(), принимающий в качестве аргументов
    // полный путь к файлу типа String и объект класса GameProgress.
    public static void saveGame(String path, GameProgress progress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
             oos.writeObject(progress);
             System.out.println("Новый файл был создан " + progress.toString());
        }
        catch (Exception ex) {
             System.out.println(ex.getMessage());
        }
    }

    //Реализуйте метод zipFiles(), принимающий в качестве аргументов String полный путь к файлу архива
    // и список запаковываемых объектов в виде списка строчек String полного пути к файлу.
    public static void zipFiles(String zipPath, File[] filesList){
        try {
            byte[] buffer = new byte[1024];
            FileOutputStream fos = new FileOutputStream(zipPath);
            ZipOutputStream zos = new ZipOutputStream(fos);
            for (int i=0; i < filesList.length; i++) {
                FileInputStream fis = new FileInputStream(filesList[i]);
                ZipEntry entry = new ZipEntry(filesList[i].getName());
                zos.putNextEntry(entry);
                fis.read(buffer);
                zos.write(buffer);
                zos.closeEntry();
                // закрываем InputStream
                fis.close();
                System.out.println("Файл " + filesList[i].getName() + " был добавлен в zip архив");
            }
            // закрываем ZipOutputStream
            zos.close();
            File zip = new File(zipPath);
            System.out.println("zip архив " + zip.getName() + " был успешно создан");
        }
        catch (IOException ioe) {
            System.out.println("Ошибка в zip архиве: " + ioe);
        }
    }

    public static void deleteFiles(File[] filesList, String zipName) {
        for (int i=0; i < filesList.length; i++) {
            if (!filesList[i].getName().equals(zipName)) {
                if (filesList[i].delete()) {
                    System.out.println("Файлы " + filesList[i].getName() + " были удалены");
                }
            }
        }
    }
}
