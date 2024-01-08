package com.instream.auth.monolithic.common.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

// PostgreSQL은 테이블명을 큰따옴표로 감싸야만 대소문자를 구별해준다. 그렇지 않으면 대소문자 가리지 않는다.
// 따라서 snake case를 사용하는게 낫다. 캡디할 때도 MariaDB가 이랬던거 같은데... => 민석이가 그냥 소문자로 한거 같다.
public class TablePostfixPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    private final static String POSTFIX = "_TBL";

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        if (name == null) {
            return null;
        }
        final String newName = name.getText() + POSTFIX;
        return Identifier.toIdentifier(newName);
    }
    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return convertToSnakeCase(name);
    }

    private Identifier convertToSnakeCase(Identifier identifier) {
        if (identifier == null) {
            return null;
        }
        String name = identifier.getText();
        String newName = name.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
        return Identifier.toIdentifier(newName);
    }
}