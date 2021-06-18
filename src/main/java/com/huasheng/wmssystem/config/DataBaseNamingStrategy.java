package com.huasheng.wmssystem.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/6/17 15:40
 * @Description ：
 */
@Component
public class DataBaseNamingStrategy implements PhysicalNamingStrategy {

    protected String addUnderscores(String name) {
        if (name == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(name.replace('.', '_'));
        for (int i = 1; i < stringBuffer.length() - 1; i++) {
            if (Character.isLowerCase(stringBuffer.charAt(i - 1)) && Character.isUpperCase(stringBuffer.charAt(i)) && Character.isLowerCase(stringBuffer.charAt(i + 1))) {
                stringBuffer.insert(i++, '_');
            }
        }
        return stringBuffer.toString().toLowerCase();
    }

    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier;
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier;
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier;
    }

    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return new Identifier(identifier.getText(), identifier.isQuoted());
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return Identifier.toIdentifier(addUnderscores(identifier.getText()));
    }

}
