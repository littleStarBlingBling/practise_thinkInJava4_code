package com.practise.annotations.SQL;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {

    public static void main(String[] args) throws Exception {

        String className = "com.practise.annotations.SQL.Member";
        Class<?> clazz = Class.forName(className);
        DBTable dbTable = clazz.getAnnotation(DBTable.class);
        if (dbTable == null) {
            System.out.println("No DBTable annotations in class " + className);
        }

        String tableName = dbTable.name();
        // 没有指定表名就用大写的类名
        if (tableName.length() < 1) {
            tableName = clazz.getName().toUpperCase();
        }

        // 保存每一列的 SQL 语句
        List<String> columnDefs = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            String columnName = null;
            Annotation[] annotations = field.getDeclaredAnnotations();
            // 这个字段上没有注解
            if (annotations.length < 1) {
                continue;
            }

            // 在这个示例中，字段上只有一个注解
            if (annotations[0] instanceof SQLInteger) {
                SQLInteger sqlInteger = (SQLInteger) annotations[0];
                // 没有指定列名就用大写的字段名
                if (sqlInteger.name().length() < 1) {
                    columnName = field.getName().toUpperCase();
                } else {
                    className = sqlInteger.name();
                }

                // 组装列名、类型、约束条件
                columnDefs.add(columnName + " INT " + getConstraints(sqlInteger.constraints()));
            }

            if (annotations[0] instanceof SQLString) {
                SQLString sqlString = (SQLString) annotations[0];
                if (sqlString.name().length() < 1) {
                    columnName = field.getName().toUpperCase();
                } else {
                    columnName = sqlString.name();
                }

                // String 类型的注解多了个 value
                columnDefs.add(columnName + " VARCHAR(" + sqlString.value() + ")" + getConstraints(sqlString.constraints()));
            }
        }

        // 组装生成表结构的语句
        StringBuilder stringBuilder = new StringBuilder("CREATE TABLE " + tableName + "(");
        for (String columnDef : columnDefs) {
            stringBuilder.append("\n    ").append(columnDef).append(",");
        }

        // 去掉最后一个语句跟着的逗号
        String tableCreated = stringBuilder.substring(0, stringBuilder.length() - 1) + ");";
        System.out.println("Table Creation SQL for " + className + " is :\n" + tableCreated);

    }

    // 组装约束条件
    private static String getConstraints(Constraints cons) {
        String constraints = "";
        if (!cons.allowNull()) {
            constraints += " NOT NULL";
        }

        if (cons.primaryKey()) {
            constraints += " PRIMARY KEY";
        }

        if (cons.unique()) {
            constraints += " UNIQUE";
        }

        return constraints;

    }
}
