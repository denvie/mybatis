/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.generator;

import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.JavaElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * GeneratorUtils。
 *
 * @author Denvie
 * @since 2020/9/11
 */
public class GeneratorUtils {
    /**
     * 为Java文件头加入版权信息。
     *
     * @param compilationUnit CompilationUnit
     * @param copyright       版权信息
     */
    public static void addFileComment(CompilationUnit compilationUnit, String copyright) {
        compilationUnit.addFileCommentLine("/*");
        compilationUnit.addFileCommentLine(" * " + copyright);
        compilationUnit.addFileCommentLine(" */");
        compilationUnit.addFileCommentLine("");
    }

    /**
     * 添加Java类注解。
     *
     * @param element JavaElement
     * @param remarks 注释内容
     * @param author  作者
     * @param date    日期
     */
    public static void addClassComment(JavaElement element, String remarks, String author, String date) {
        element.addJavaDocLine("/**");
        element.addJavaDocLine(" * " + remarks);
        element.addJavaDocLine(" * ");
        element.addJavaDocLine(" * @author " + author);
        element.addJavaDocLine(" * @since " + date);
        element.addJavaDocLine(" */");
    }

    /**
     * 将字符串首字母转换成小写。
     *
     * @param text 字符串
     * @return String
     */
    public static String lowerFirstChar(String text) {
        return Character.toLowerCase(text.charAt(0)) + text.substring(1);
    }

    /**
     * 驼峰命名转换。
     *
     * @param text        字符串
     * @param replacement 目标字符
     * @return String
     */
    public static String changeHumpTo(String text, String replacement) {
        Matcher matcher = Pattern.compile("[A-Z]").matcher(text);
        StringBuffer sb = new StringBuffer();
        int index = 0;
        while (matcher.find()) {
            if (index++ == 0) {
                matcher.appendReplacement(sb, matcher.group(0).toLowerCase());
            } else {
                matcher.appendReplacement(sb, replacement + matcher.group(0).toLowerCase());
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
