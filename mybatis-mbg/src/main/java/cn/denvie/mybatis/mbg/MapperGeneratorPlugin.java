/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.mbg;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 自定义插件实现为生成的Mapper类添加注释。
 * <p>
 * 解决MBG默认没有调用{@link CommentGenerator#addClassComment(InnerClass, IntrospectedTable)}以及
 * {@link CommentGenerator#addClassComment(InnerClass, IntrospectedTable, boolean)}导致无法生成Mapper类注释的问题。
 * </p>
 *
 * @author denvie
 * @since 2020/8/16
 */
public class MapperGeneratorPlugin extends PluginAdapter {
    private boolean addRemarkComments = false;
    private DateFormat dateFormat = null;
    private String author;

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        this.addRemarkComments = StringUtility.isTrue(
                properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));
        this.dateFormat = new SimpleDateFormat(properties.getProperty(
                PropertyRegistry.COMMENT_GENERATOR_DATE_FORMAT, "yyyy-MM-dd HH:mm:ss"));
        this.author = properties.getProperty("author", System.getProperty("user.name"));
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    /**
     * 生成Mapper接口。
     */
    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
        String baseRecordType = introspectedTable.getBaseRecordType();
        // 添加父接口，比如通用接口：com.github.abel533.mapper.Mapper
        /*FullyQualifiedJavaType superJavaType = new FullyQualifiedJavaType("Mapper<" + baseRecordType + ">");
        interfaze.addSuperInterface(superJavaType);*/

        // 导包
        FullyQualifiedJavaType baseRecordTypeImportDeclare = new FullyQualifiedJavaType(baseRecordType);
        interfaze.addImportedType(baseRecordTypeImportDeclare);

        // 根据实际业务添加配置
        // interfaze.addAnnotation("@Component(\"" + introspectedTable.getFullyQualifiedTable() + "\")");

        // 添加类注释
        if (addRemarkComments) {
            String remarks = introspectedTable.getRemarks();
            if (!StringUtility.stringHasValue(remarks)) {
                remarks = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
            }
            doAddRemarkComments(interfaze, remarks + " Mapper");
        }

        return true;
    }

    private void doAddRemarkComments(Interface interfaze, String remarks) {
        interfaze.addJavaDocLine("/**");
        interfaze.addJavaDocLine(" * " + remarks);
        interfaze.addJavaDocLine(" * ");
        interfaze.addJavaDocLine(" * @author " + author);
        interfaze.addJavaDocLine(" * @since " + dateFormat.format(new Date()));
        interfaze.addJavaDocLine(" */");
    }
}
