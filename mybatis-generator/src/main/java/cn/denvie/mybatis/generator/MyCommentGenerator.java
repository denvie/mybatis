/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * 自定义注释生成器。
 *
 * @author Denvie
 * @since 2020/8/16
 */
public class MyCommentGenerator extends DefaultCommentGenerator {
    private boolean addRemarkComments = false;
    private SimpleDateFormat dateFormat = null;
    private String author;
    private String copyright;

    /**
     * 设置用户配置的参数
     */
    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);
        this.addRemarkComments = StringUtility.isTrue(
                properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));
        this.dateFormat = new SimpleDateFormat(properties.getProperty(
                PropertyRegistry.COMMENT_GENERATOR_DATE_FORMAT, "yyyy-MM-dd HH:mm:ss"));
        this.author = properties.getProperty("author", System.getProperty("user.name"));
        this.copyright = properties.getProperty("copyright",
                "Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.");
    }

    /**
     * 添加Java文件头注释
     */
    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        if (!addRemarkComments) {
            return;
        }
        // Java文件头加入版权信息
        GeneratorUtils.addFileComment(compilationUnit, copyright);

        // 为Model声明 import 导入信息
        if (!compilationUnit.getType().getFullyQualifiedName().endsWith("Example")
                && !compilationUnit.getType().getFullyQualifiedName().endsWith("Mapper")) {
            compilationUnit.addImportedType(
                    new FullyQualifiedJavaType("javax.persistence.Column"));
            compilationUnit.addImportedType(
                    new FullyQualifiedJavaType("javax.persistence.GeneratedValue"));
            compilationUnit.addImportedType(
                    new FullyQualifiedJavaType("javax.persistence.Id"));
            compilationUnit.addImportedType(
                    new FullyQualifiedJavaType("javax.persistence.Table"));
        }
    }

    /**
     * 添加Model类注释
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (!addRemarkComments) {
            return;
        }
        String remarks = introspectedTable.getRemarks();
        if (!StringUtility.stringHasValue(remarks)) {
            String tableName = introspectedTable.getFullyQualifiedTable().getIntrospectedTableName();
            remarks = "This class corresponds to the database table " + tableName;
        }
        // 给Model实体添加注释
        addComments(topLevelClass, remarks, true);
        // 给Model实体添加 @Table 注解
        topLevelClass.addAnnotation("@Table(name = \""
                + introspectedTable.getFullyQualifiedTableNameAtRuntime() + "\")");
    }

    /**
     * 添加字段注释
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        if (!addRemarkComments) {
            return;
        }
        String remarks = introspectedColumn.getRemarks();
        if (!StringUtility.stringHasValue(remarks)) {
            remarks = introspectedColumn.getActualColumnName();
        }
        // 给Model的字段添加注释
        addComments(field, remarks, false);
        // 给Model的字段添加 @Id 注解
        if (introspectedColumn.isIdentity()) {
            field.addAnnotation("@Id");
        }
        // 给Model的字段添加 @GeneratedValue 注解
        if (introspectedColumn.isAutoIncrement()) {
            field.addAnnotation("@GeneratedValue(generator = \"JDBC\")");
        }
        // 给Model的字段添加 @Column 注解
        field.addAnnotation("@Column(name = \"" + introspectedColumn.getActualColumnName() + "\")");
    }

    // add class or field comment
    private void addComments(JavaElement element, String remarks, boolean isClass) {
        // 数据库中特殊字符需要转义
        if (remarks.contains("\"")) {
            remarks = remarks.replace("\"", "'");
        }
        element.addJavaDocLine("/**");
        // 获取数据库字段的备注信息
        String[] remarkLines = remarks.split(System.getProperty("line.separator"));
        for (String remarkLine : remarkLines) {
            element.addJavaDocLine(" * " + remarkLine);
        }
        if (isClass) {
            element.addJavaDocLine(" * ");
            element.addJavaDocLine(" * @author " + author);
            element.addJavaDocLine(" * @since " + dateFormat.format(new Date()));
        }
        element.addJavaDocLine(" */");
    }

    /**
     * 添加普通方法的注释，主要是XxxMapper接口方法的注释
     */
    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        if (!addRemarkComments) {
            return;
        }
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * " + method.getName());
        // 添加 @param 注释
        List<Parameter> parameters = method.getParameters();
        if (parameters != null && parameters.size() > 0) {
            method.addJavaDocLine(" * ");
            for (Parameter parameter : parameters) {
                method.addJavaDocLine(" * @param " + parameter.getName() + " " + parameter.getType().getShortName());
            }
        }
        // 添加 @return 注释
        Optional<FullyQualifiedJavaType> returnTypeOptional = method.getReturnType();
        returnTypeOptional.ifPresent(fullyQualifiedJavaType ->
                method.addJavaDocLine(" * @return " + fullyQualifiedJavaType.getShortName()));
        // 添加 @throws 注释
        List<FullyQualifiedJavaType> exceptions = method.getExceptions();
        if (exceptions != null && exceptions.size() > 0) {
            for (FullyQualifiedJavaType exception : exceptions) {
                method.addJavaDocLine(" * @throws " + exception.getShortName() + " " + exception.getShortName());
            }
        }
        method.addJavaDocLine(" */");
    }
}
