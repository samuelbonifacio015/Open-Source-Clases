package com.citizen.platform.u202317269.shared.infrastructure.persistence.jpa.configuration;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import static io.github.encryptorcode.pluralize.Pluralize.pluralize;

/**
 * Snake Case Physical Naming Strategy for Hibernate
 * @summary
 * This class implements the PhysicalNamingStrategy interface to convert
 * database object names to snake_case format and pluralizes table names.
 * @author Samuel Bonifacio
 */
public class SnakeCaseWithPluralizedWithPhysicalNamingStrategy implements PhysicalNamingStrategy {
  @Override
  public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
    return this.toSnakeCase(identifier);
  }

  /**
   * Converts schema names to Snake Case
   * @param identifier schema identifier
   * @param jdbcEnvironment JDBC environment
   * @return Snake Case schema identifier
   */
  @Override
  public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
    return this.toSnakeCase(identifier);
  }

  /**
   * Converts table names to pluralized Snake Case
   * @param identifier table identifier
   * @param jdbcEnvironment JDBC environment
   * @return Pluralized Snake Case table identifier
   */
  @Override
  public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
    return this.toSnakeCase(this.toPlural(identifier));
  }

  /**
   * Converts sequence names to Snake Case
   * @param identifier sequence identifier
   * @param jdbcEnvironment JDBC environment
   * @return Snake Case sequence identifier
   */
  @Override
  public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
    return this.toSnakeCase(identifier);
  }

  /**
   * Converts column names to Snake Case
   * @param identifier column identifier
   * @param jdbcEnvironment JDBC environment
   * @return Snake Case column identifier
   */
  @Override
  public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
    return this.toSnakeCase(identifier);
  }

  /**
   * Converts the Identifier to Snake Case
   * @param identifier object identifier
   * @return Snake Case Identifier
   */
  private Identifier toSnakeCase(final Identifier identifier) {
    if (identifier == null) return null;

    final String regex = "([a-z])([A-Z])";
    final String replacement = "$1_$2";
    final String newName = identifier.getText()
        .replaceAll(regex, replacement)
        .toLowerCase();
    return  Identifier.toIdentifier(newName);
  }

  /**
   * Pluralizes the identifier
   * @param identifier object identifier
   * @return Pluralized Identifier
   */
  private Identifier toPlural( final Identifier identifier) {
    final String newName = pluralize(identifier.getText());
    return Identifier.toIdentifier(newName);
  }
}
