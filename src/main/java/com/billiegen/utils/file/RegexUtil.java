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

    public static boolean isMatch(String content, String regex) {
        return true;
    }

    public static List<String> getList(String content, String regex) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    public static List<String> getList(String content, String regex, int group) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            result.add(matcher.group(group));
        }
        return result;
    }

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
            return result;
        }
        return result;
    }

    public static List<String> getListFromFiles(List<String> filePaths, String regex, int group) {
        List<String> result = new ArrayList<>();
        filePaths.forEach(s -> result.addAll(getListFromFile(s, regex, group)));
        return result;
    }

    public static void main(String[] args) {
        String regex = "^\\.(?<name>.*?):before\\s*\\{";
        String file = "C:/xinhuanet/projects/billiegen/metronic/assets/global/plugins/font-awesome/css/font-awesome.css";
        List<String> icons = getListFromFile(file, regex, 1);
        icons.forEach(System.out::println);
    }
}
