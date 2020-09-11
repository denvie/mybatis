/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * MyBatis代码生成器。
 *
 * @author Denvie
 * @since 2020/8/16
 */
public class GeneratorMain {
    /**
     * 代码生成主程序。
     */
    public static void main(String[] args) throws IOException, XMLParserException,
            InvalidConfigurationException, SQLException, InterruptedException {
        // 读取MBG配置文件列表
        String mbgDir = Objects.requireNonNull(
                GeneratorMain.class.getClassLoader().getResource("mbg-config")).getFile();
        File[] files = new File(mbgDir).listFiles((dir, name) -> name != null && name.endsWith(".xml"));
        if (files == null || files.length == 0) {
            System.out.println("no configuration file found.");
            return;
        }
        // 收集MBG执行过程中的警告信息
        List<String> warnings = new ArrayList<>();
        for (File file : files) {
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(file);
            // 当生成的文件重复时，是否覆盖原代码
            boolean overwrite = true;
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            // 执行生成代码
            myBatisGenerator.generate(null);
        }
        // 输出警告信息
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }
}
