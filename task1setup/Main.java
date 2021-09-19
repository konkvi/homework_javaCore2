package task1setup;

import java.io.*;

public class Main {
    //метод для записи создания каталога
    public static void writeDir(StringBuilder sb, String name) {
        sb.append(" Каталог ").append(name).append(" создан \n");
    }

    //метод для записи создания файла
    public static void writeFile(StringBuilder sb, String name) {
        sb.append(" Файл ").append(name).append(" создан \n");
    }

    public static void main(String[] args){
        //для сохранения лога используйте экземпляр класса StringBuilder.
        //добавляйте в него всю информацию о создании файлов и каталогов
        StringBuilder sb = new StringBuilder();

        //В папке Games создайте несколько директорий: src, res, savegames, temp
        File srcDir = new File("C://Games//src");
        File resDir = new File("C://Games//res");
        File saveGamesDir = new File("C://Games//saveGames");
        File tempDir = new File("C://Games//temp");
        //В каталоге src создайте две директории: main, test
        File mainDir = new File("C://Games//src//main");
        File testDir = new File("C://Games//src//test");
        //В каталог res создайте три директории: drawables, vectors, icons
        File drawablesDir = new File("C://Games//res//drawables");
        File vectorsDir = new File("C://Games//res//vectors");
        File iconsDir = new File("C://Games//res//icons");
        //В подкаталоге main создайте два файла: Main.java, Utils.java
        File mainFile = new File("C://Games//src//main//Main.java");
        File utilsFile = new File("C://Games//src//main//Utils.java");
        //В директории temp создайте файл temp.txt
        File tempFile = new File("C://Games//temp//temp.txt");

        if (srcDir.mkdir()) writeDir(sb, srcDir.getName());
        if (resDir.mkdir()) writeDir(sb, resDir.getName());
        if (saveGamesDir.mkdir()) writeDir(sb, saveGamesDir.getName());
        if (tempDir.mkdir()) writeDir(sb, tempDir.getName());
        if (mainDir.mkdir()) writeDir(sb, mainDir.getName());
        if (testDir.mkdir()) writeDir(sb, testDir.getName());
        if (drawablesDir.mkdir()) writeDir(sb, drawablesDir.getName());
        if (vectorsDir.mkdir()) writeDir(sb, vectorsDir.getName());
        if (iconsDir.mkdir()) writeDir(sb, iconsDir.getName());

        //возьмите из StringBuilder текст и запишите его в файл temp.txt с помощью класса FileWriter
        //класс FileWriter используется для записи текстовых файлов
        //Параметр append указывает, должны ли данные дозаписываться в конец файла
        //(true), либо файл должен перезаписываться (false).
        try (FileWriter writer = new FileWriter(tempFile, true)) {
            if (mainFile.createNewFile()) writeFile(sb, mainFile.getName());
            if (utilsFile.createNewFile()) writeFile(sb, utilsFile.getName());
            if (tempFile.createNewFile()) writeFile(sb, tempFile.getName());
            writer.write(sb.toString());
            //метод flush() дозаписывает буфер и очищает его
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
