package com.billiegen.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author CodePorter
 * @date 2017-12-06
 */
public class RegexUtil {

    /**
     * 给定内容是否匹配指定的正则
     *
     * @param content string content
     * @param regex   regex
     * @return true if matches false otherwise.
     */
    public static boolean isMatches(String content, String regex) {
        return Pattern.matches(regex, content);
    }

    public static boolean isNotMatches(String content, String regex) {
        return !isMatches(content, regex);
    }

    /**
     * 从指定的文本中收集符合正则的内容（不指定分组）
     *
     * @param content 文本
     * @param regex   正则
     * @return 无匹配时返回空的List
     */
    public static List<String> getList(String content, String regex) {
        return getList(content, regex, 0);
    }

    /**
     * 从指定的文本中收集符合正则的内容中的某个部分（指定分组）
     *
     * @param content 文本
     * @param regex   正则
     * @param group   分组号
     * @return 无匹配时返回空的List
     */
    public static List<String> getList(String content, String regex, int group) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        try {
            while (matcher.find()) {
                result.add(matcher.group(group));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从指定路径的文件中收集符合正则的内容（不指定分组）
     *
     * @param filePath 文件路径
     * @param regex    正则表达式
     * @return 无匹配时返回空的List
     */
    public static List<String> getListFromFile(String filePath, String regex) {
        return getListFromFile(filePath, regex, 0);
    }

    /**
     * 从指定路径的文件中收集符合正则的内容（指定分组）
     *
     * @param filePath 文件路径
     * @param regex    正则表达式
     * @return 无匹配时返回空的List
     */
    public static List<String> getListFromFile(String filePath, String regex, int group) {
        List<String> result = new ArrayList<>();
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.addAll(getList(line, regex, group));
                }
                read.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从指定路径的多个文件中收集符合正则的内容（不指定分组）
     *
     * @param filePaths 文件路径
     * @param regex     正则表达式
     * @return 无匹配或异常时返回空List
     */
    public static List<String> getListFromFiles(List<String> filePaths, String regex) {
        List<String> result = new ArrayList<>();
        filePaths.forEach(s -> result.addAll(getListFromFile(s, regex, 0)));
        return result;
    }

    /**
     * 从指定路径的多个文件中收集符合正则的内容（指定分组）
     *
     * @param filePaths 文件路径
     * @param regex     正则表达式
     * @return 无匹配或异常时返回空List
     */
    public static List<String> getListFromFiles(List<String> filePaths, String regex, int group) {
        List<String> result = new ArrayList<>();
        try {
            filePaths.forEach(s -> result.addAll(getListFromFile(s, regex, group)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String regex = "^\\.(?<name>.*?):before\\s*\\{$";
        String file = "C:/xinhuanet/projects/billiegen/metronic/assets/global/plugins/font-awesome/css/font-awesome.css";
        List<String> icons = getListFromFile(file, regex);
        icons.forEach(System.out::println);
    }
}
