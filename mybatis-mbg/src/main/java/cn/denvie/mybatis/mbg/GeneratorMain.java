/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.mbg;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis代码生成器。
 *
 * @author denvie
 * @since 2020/8/16
 */
public class GeneratorMain {
    public static void main(String[] args) throws URISyntaxException, IOException, XMLParserException,
            InvalidConfigurationException, SQLException, InterruptedException {
        // 收集MBG执行过程中的警告信息
        List<String> warnings = new ArrayList<>();
        // 读取MBG配置文件
        File configFile = new File(GeneratorMain.class.getResource("/generatorConfig.xml").toURI());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        // 当生成的代码重复时，覆盖原代码
        boolean overwrite = true;
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        // 执行生成代码
        myBatisGenerator.generate(null);
        // 输出警告信息
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }
}
