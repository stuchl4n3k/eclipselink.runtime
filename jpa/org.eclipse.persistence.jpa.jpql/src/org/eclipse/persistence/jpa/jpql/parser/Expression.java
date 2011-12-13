/*******************************************************************************
 * Copyright (c) 2006, 2011 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 *
 ******************************************************************************/
package org.eclipse.persistence.jpa.jpql.parser;

import org.eclipse.persistence.jpa.jpql.util.iterator.IterableListIterator;

/**
 * This is the root interface of the parsed tree representation of a JPQL query. The way a JPQL
 * query is parsed is based on the {@link JPQLGrammar JPQL grammar} used.
 *
 * @see org.eclipse.persistence.jpa.jpql.spi.IJPQLExtension IJPQLExtension
 * @see JPQLGrammar
 *
 * @version 2.4
 * @since 2.3
 * @author Pascal Filion
 */
@SuppressWarnings("nls")
public interface Expression {

	/**
	 * The constant for 'ABS'.
	 */
	String ABS = "ABS";

	/**
	 * The constant for 'ALL'.
	 */
	String ALL = "ALL";

	/**
	 * The constant for 'AND'.
	 */
	String AND = "AND";

	/**
	 * The constant for 'ANY'.
	 */
	String ANY = "ANY";

	/**
	 * The constant for 'AS'.
	 */
	String AS = "AS";

	/**
	 * The constant for 'ASC'.
	 */
	String ASC = "ASC";

	/**
	 * The constant for 'AVG'.
	 */
	String AVG = "AVG";

	/**
	 * The constant for 'BETWEEN'.
	 */
	String BETWEEN = "BETWEEN";

	/**
	 * The constant for 'BIT_LENGTH', which is an unused keyword.
	 */
	String BIT_LENGTH = "BIT_LENGTH";

	/**
	 * The constant for 'BOTH'.
	 */
	String BOTH = "BOTH";

	/**
	 * The constant for the identifier 'CASE'.
	 */
	String CASE = "CASE";

	/**
	 * The constant for 'CHAR_LENGTH', which is an unused keyword.
	 */
	String CHAR_LENGTH = "CHAR_LENGTH";

	/**
	 * The constant for 'CHARACTER_LENGTH', which is an unused keyword.
	 */
	String CHARACTER_LENGTH = "CHARACTER_LENGTH";

	/**
	 * The constant for 'CLASS', which is an unused keyword.
	 */
	String CLASS = "CLASS";

	/**
	 * The constant for 'COALESCE'.
	 */
	String COALESCE = "COALESCE";

	/**
	 * The constant for 'CONCAT'.
	 */
	String CONCAT = "CONCAT";

	/**
	 * The constant for 'COUNT'.
	 */
	String COUNT = "COUNT";

	/**
	 * The constant for 'CURRENT_DATE'.
	 */
	String CURRENT_DATE = "CURRENT_DATE";

	/**
	 * The constant for 'CURRENT_DATE'.
	 */
	String CURRENT_TIME = "CURRENT_TIME";

	/**
	 * The constant for 'CURRENT_TIMESTAMP'.
	 */
	String CURRENT_TIMESTAMP = "CURRENT_TIMESTAMP";

	/**
	 * The constant for 'DELETE'.
	 */
	String DELETE = "DELETE";

	/**
	 * The constant for 'DELETE FROM'.
	 */
	String DELETE_FROM = "DELETE FROM";

	/**
	 * The constant for 'DESC'.
	 */
	String DESC = "DESC";

	/**
	 * The constant for '<>'.
	 */
	String DIFFERENT = "<>";

	/**
	 * The constant for 'DISTINCT'.
	 */
	String DISTINCT = "DISTINCT";

	/**
	 * The constant for the division sign '/'.
	 */
	String DIVISION = "/";

	/**
	 * The constant for the identifier 'ELSE'.
	 */
	String ELSE = "ELSE";

	/**
	 * The constant for 'EMPTY'.
	 */
	String EMPTY = "EMPTY";

	/**
	 * The constant for the identifier 'END'.
	 */
	String END = "END";

	/**
	 * The constant for 'ENTRY'.
	 */
	String ENTRY = "ENTRY";

	/**
	 * The constant for '='.
	 */
	String EQUAL = "=";

	/**
	 * The constant for 'ESCAPE'.
	 */
	String ESCAPE = "ESCAPE";

	/**
	 * The constant for 'EXISTS'.
	 */
	String EXISTS = "EXISTS";

	/**
	 * The constant for 'FALSE'.
	 */
	String FALSE = "FALSE";

	/**
	 * A constant for 'FETCH'.
	 */
	String FETCH = "FETCH";

	/**
	 * The constant for 'FROM'.
	 */
	String FROM = "FROM";

	/**
	 * The constant 'FUNC', which is an EclipseLink specific identifier that was added in version 2.1.
	 */
	String FUNC = "FUNC";

	/**
	 * The constant for '>'.
	 */
	String GREATER_THAN = ">";

	/**
	 * The constant for '>='.
	 */
	String GREATER_THAN_OR_EQUAL = ">=";

	/**
	 * The constant for 'GROUP BY'.
	 */
	String GROUP_BY = "GROUP BY";

	/**
	 * The constant for 'HAVING'.
	 */
	String HAVING = "HAVING";

	/**
	 * The constant for 'IN'.
	 */
	String IN = "IN";

	/**
	 * The constant for 'INDEX'.
	 */
	String INDEX = "INDEX";

	/**
	 * The constant for 'INNER'.
	 */
	String INNER = "INNER";

	/**
	 * The constant for 'INNER JOIN'.
	 */
	String INNER_JOIN = "INNER JOIN";

	/**
	 * The constant for 'INNER JOIN FETCH'.
	 */
	String INNER_JOIN_FETCH = "INNER JOIN FETCH";

	/**
	 * The constant for 'IS'.
	 */
	String IS = "IS";

	/**
	 * The constant for 'IS EMPTY'.
	 */
	String IS_EMPTY = "IS EMPTY";

	/**
	 * The constant for 'IS NOT EMPTY'.
	 */
	String IS_NOT_EMPTY = "IS NOT EMPTY";

	/**
	 * The constant for 'IS NOT NULL'.
	 */
	String IS_NOT_NULL = "IS NOT NULL";

	/**
	 * The constant for 'IS NULL'.
	 */
	String IS_NULL = "IS NULL";

	/**
	 * The constant for 'JOIN'.
	 */
	String JOIN = "JOIN";

	/**
	 * The constant for 'JOIN FETCH'.
	 */
	String JOIN_FETCH = "JOIN FETCH";

	/**
	 * The constant for 'KEY'.
	 */
	String KEY = "KEY";

	/**
	 * The constant for 'LEADING'.
	 */
	String LEADING = "LEADING";

	/**
	 * The constant for 'LEFT'.
	 */
	String LEFT = "LEFT";

	/**
	 * The constant for 'LEFT JOIN'.
	 */
	String LEFT_JOIN = "LEFT JOIN";

	/**
	 * The constant for 'LEFT JOIN FETCH'.
	 */
	String LEFT_JOIN_FETCH = "LEFT JOIN FETCH";

	/**
	 * The constant for 'LEFT OUTER JOIN'.
	 */
	String LEFT_OUTER_JOIN = "LEFT OUTER JOIN";

	/**
	 * The constant for 'LEFT OUTER JOIN FETCH'.
	 */
	String LEFT_OUTER_JOIN_FETCH = "LEFT OUTER JOIN FETCH";

	/**
	 * The constant for 'LENGTH'.
	 */
	String LENGTH = "LENGTH";

	/**
	 * The constant for 'LIKE'.
	 */
	String LIKE = "LIKE";

	/**
	 * The constant for 'LOCATE'.
	 */
	String LOCATE = "LOCATE";

	/**
	 * The constant for 'LOWER'.
	 */
	String LOWER = "LOWER";

	/**
	 * The constant for '<'.
	 */
	String LOWER_THAN = "<";

	/**
	 * The constant for '<='.
	 */
	String LOWER_THAN_OR_EQUAL = "<=";

	/**
	 * The constant for 'MAX'.
	 */
	String MAX = "MAX";

	/**
	 * The constant for 'MEMBER'.
	 */
	String MEMBER = "MEMBER";

	/**
	 * The constant for 'MEMBER OF'.
	 */
	String MEMBER_OF = "MEMBER OF";

	/**
	 * The constant for 'MIN'.
	 */
	String MIN = "MIN";

	/**
	 * The constant for the minus sign '-'.
	 */
	String MINUS = "-";

	/**
	 * The constant for 'MOD'.
	 */
	String MOD = "MOD";

	/**
	 * The constant for multiplication sign '*'.
	 */
	String MULTIPLICATION = "*";

	/**
	 * The constant for ':'.
	 */
	String NAMED_PARAMETER = ":";

	/**
	 * The constant for 'NEW'.
	 */
	String NEW = "NEW";

	/**
	 * The constant for 'NOT'.
	 */
	String NOT = "NOT";

	/**
	 * The constant for 'NOT BETWEEN'.
	 */
	String NOT_BETWEEN = "NOT BETWEEN";

	/**
	 * The constant for 'NOT EXISTS'.
	 */
	String NOT_EXISTS = "NOT EXISTS";

	/**
	 * The constant for 'NOT IN'.
	 */
	String NOT_IN = "NOT IN";

	/**
	 * The constant for 'NOT LIKE'.
	 */
	String NOT_LIKE = "NOT LIKE";

	/**
	 * The constant for 'NOT MEMBER'.
	 */
	String NOT_MEMBER = "NOT MEMBER";

	/**
	 * The constant for 'NOT MEMBER OF'.
	 */
	String NOT_MEMBER_OF = "NOT MEMBER OF";

	/**
	 * The constant for 'NULL'.
	 */
	String NULL = "NULL";

	/**
	 * The constant for 'NULLIF'.
	 */
	String NULLIF = "NULLIF";

	/**
	 * The constant for 'OBJECT'.
	 */
	String OBJECT = "OBJECT";

	/**
	 * The constant for 'OF'.
	 */
	String OF = "OF";

	/**
	 * The constant for 'ON', which is an EclipseLink specific identifier that was added in
	 * version 2.4.
	 *
	 * @since 2.4
	 */
	String ON = "ON";

	/**
	 * The constant for 'OR'.
	 */
	String OR = "OR";

	/**
	 * The constant for 'ORDER BY'.
	 */
	String ORDER_BY = "ORDER BY";

	/**
	 * The constant for 'OUTER'.
	 */
	String OUTER = "OUTER";

	/**
	 * The constant for for the plus sign '+'.
	 */
	String PLUS = "+";

	/**
	 * The constant for 'POSITION', which is an unused keyword.
	 */
	String POSITION = "POSITION";

	/**
	 * The constant for '?'.
	 */
	String POSITIONAL_PARAMETER = "?";

	/**
	 * The constant for single quote.
	 */
	String QUOTE = "'";

	/**
	 * The constant for 'SELECT'.
	 */
	String SELECT = "SELECT";

	/**
	 * The constant for 'SET'.
	 */
	String SET = "SET";

	/**
	 * The constant for 'SIZE'.
	 */
	String SIZE = "SIZE";

	/**
	 * The constant for 'SOME'.
	 */
	String SOME = "SOME";

	/**
	 * The constant for 'SQRT'.
	 */
	String SQRT = "SQRT";

	/**
	 * The constant for 'SUBSTRING'.
	 */
	String SUBSTRING = "SUBSTRING";

	/**
	 * The constant for 'SUM'.
	 */
	String SUM = "SUM";

	/**
	 * The constant for 'THEN'.
	 */
	String THEN = "THEN";

	/**
	 * The constant for 'TRAILING'.
	 */
	String TRAILING = "TRAILING";

	/**
	 * The constant for 'TREAT', which is an EclipseLink specific identifier that was added in
	 * version 2.1.
	 */
	String TREAT = "TREAT";

	/**
	 * The constant for 'TRIM'.
	 */
	String TRIM = "TRIM";

	/**
	 * The constant for 'TRUE'.
	 */
	String TRUE = "TRUE";

	/**
	 * The constant for 'TYPE'.
	 */
	String TYPE = "TYPE";

	/**
	 * The constant for 'UNKNOWN', which is an unused keyword.
	 */
	String UNKNOWN = "UNKNOWN";

	/**
	 * The constant for 'UPDATE'.
	 */
	String UPDATE = "UPDATE";

	/**
	 * The constant for 'UPPER'.
	 */
	String UPPER = "UPPER";

	/**
	 * The constant for 'VALUE'.
	 */
	String VALUE = "VALUE";

	/**
	 * The constant for the identifier 'WHEN'.
	 */
	String WHEN = "WHEN";

	/**
	 * The constant for 'WHERE'.
	 */
	String WHERE = "WHERE";

	/**
	 * Visits this {@link Expression} by the given {@link ExpressionVisitor visitor}.
	 *
	 * @param visitor The {@link ExpressionVisitor} to visit this object
	 */
	void accept(ExpressionVisitor visitor);

	/**
	 * Visits the children of this {@link Expression}. This method can be used to optimize traversing
	 * the children since a new list is not created every time {@link #children()} or {@link
	 * #getChildren()} is called.
	 * <p>
	 * This does not traverse the {@link Expression} sub-hierarchy, use a subclass of
	 * {@link AbstractTraverseChildrenVisitor} in order to traverse the entire sub-hierarchy.
	 *
	 * @param visitor The {@link ExpressionVisitor visitor} to visit the children of this object.
	 */
	void acceptChildren(ExpressionVisitor visitor);

	/**
	 * Returns the children of this {@link Expression}.
	 *
	 * @return The children of this {@link Expression} or an empty iterable iterator
	 */
	IterableListIterator<Expression> children();

	/**
	 * Returns the {@link JPQLGrammar} that defines how the JPQL query was parsed.
	 *
	 * @return The {@link JPQLGrammar} that was used to parse this {@link Expression}
	 * @since 2.4
	 */
	JPQLGrammar getGrammar();

	/**
	 * Returns the parent of this {@link Expression}.
	 *
	 * @return The parent of this {@link Expression, which is never <code>null</code> except for the
	 * root of the tree
	 */
	Expression getParent();

	/**
	 * Retrieves the root node of the parsed tree hierarchy.
	 *
	 * @return The root of the {@link Expression} tree
	 */
	JPQLExpression getRoot();

	/**
	 * Determines whether this {@link Expression} is a parent of the given {@link Expression}.
	 *
	 * @param expression The {@link Expression} to verify its paternity with this {@link Expression}
	 * @return <code>true</code> if this {@link Expression} is the same as the given {@link Expression}
	 * or one of its parent; <code>false</code> otherwise
	 */
	boolean isAncestor(Expression expression);

	/**
	 * Generates a string representation of this {@link Expression}, which needs to include any
	 * characters that are considered virtual, i.e. that was parsed when the query is incomplete and
	 * is needed for functionality like content assist.
	 *
	 * @return The string representation of this {@link Expression}
	 */
	String toActualText();

	/**
	 * Generates a string representation of this {@link Expression} and its children.
	 *
	 * @return The generated portion of the JPQL query starting with this {@link Expression}
	 */
	String toParsedText();
}