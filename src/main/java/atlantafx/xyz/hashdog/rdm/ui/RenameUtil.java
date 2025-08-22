package atlantafx.xyz.hashdog.rdm.ui;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class RenameUtil {
    public static void main(String[] args) {
        String path="C:\\Users\\Administrator\\Downloads\\2.2.1";
        renameAndMoveFiles(path);
    }

    /**
     * 遍历文件夹A中的目录B，递归遍历目录B下的所有文件，
     * 将文件重命名并拼接上目录B的名称，然后移动到文件夹A
     *
     * @param basePath 文件夹A的路径
     */
    public static void renameAndMoveFiles(String basePath) {
        File baseDir = new File(basePath);
        if (!baseDir.exists() || !baseDir.isDirectory()) {
            System.out.println("指定路径不存在或不是目录: " + basePath);
            return;
        }

        // 遍历文件夹A中的所有目录B
        File[] subDirs = baseDir.listFiles(File::isDirectory);
        if (subDirs == null) {
            System.out.println("无法读取目录内容: " + basePath);
            return;
        }

        for (File subDir : subDirs) {
            if(subDir.isFile()){
                continue;
            }
            // 获取目录B的名称
            String dirName = subDir.getName();
            if(dirName.contains("ubuntu")){
                if(dirName.contains("arm")){
                    dirName="linux-arm64";
                }else {
                    dirName="linux-x86_64";
                }
            }
            if(dirName.contains("macos")){
                if(dirName.contains("latest")){
                    dirName="macos-arm64";
                }else {
                    dirName="macos-x86_64";
                }
            }

            if(dirName.contains("windows")){
                dirName="windows-x86_64";
            }
            // 递归遍历目录B下的所有文件
            processDirectory(subDir, dirName, baseDir.toPath());
        }
    }

    /**
     * 递归处理目录中的文件
     *
     * @param dir 当前处理的目录
     * @param prefix 文件名前缀（目录B的名称）
     * @param targetDir 目标目录路径（文件夹A）
     */
    private static void processDirectory(File dir, String prefix, Path targetDir) {
        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                // 递归处理子目录
                processDirectory(file, prefix, targetDir);
            } else {
                // 处理文件：重命名并移动到目标目录
                renameAndMoveFile(file, prefix, targetDir);
            }
        }
    }

    /**
     * 重命名文件并移动到目标目录
     *
     * @param file 原文件
     * @param prefix 文件名前缀（目录B的名称）
     * @param targetDir 目标目录路径
     */
    private static void renameAndMoveFile(File file, String prefix, Path targetDir) {
        try {
            // 获取原文件名和扩展名
            String originalName = file.getName();
            String extension = "";
            int dotIndex = originalName.lastIndexOf('.');
            if (dotIndex > 0) {
                extension = originalName.substring(dotIndex);
                originalName = originalName.substring(0, dotIndex);
            }
            originalName="rdm-ui-2.2.1-";
            // 构造新文件名：目录B名称_原文件名.扩展名
            String newName = originalName+prefix  + extension;
            Path targetPath = targetDir.resolve(newName);

            // 移动文件到目标目录并重命名
            Files.move(file.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("已移动并重命名: " + file.toPath() + " -> " + targetPath);
        } catch (Exception e) {
            System.err.println("处理文件时出错: " + file.getAbsolutePath());
            e.printStackTrace();
        }
    }
}
