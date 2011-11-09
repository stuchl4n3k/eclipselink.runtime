/*******************************************************************************
 * Copyright (c) 2011 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     David McCann - September 06, 2011 - 2.4 - Initial implementation
 ******************************************************************************/
package dbws.testing.simpleplsqlsp;

//javase imports
import java.io.StringReader;
import java.sql.SQLException;

import org.w3c.dom.Document;

//java eXtension imports
import javax.wsdl.WSDLException;

//JUnit4 imports
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

//EclipseLink imports
import org.eclipse.persistence.internal.xr.Invocation;
import org.eclipse.persistence.internal.xr.Operation;
import org.eclipse.persistence.oxm.XMLMarshaller;
import org.eclipse.persistence.tools.dbws.DBWSBuilder;

//test imports
import dbws.testing.DBWSTestSuite;

/**
 * Tests PL/SQL procedures with simple arguments.
 *
 */
public class SimplePLSQLSPTestSuite extends DBWSTestSuite {

    static final String CREATE_SIMPLEPACKAGE1_PACKAGE =
        "CREATE OR REPLACE PACKAGE SIMPLEPACKAGE1 AS" +
            "\nPROCEDURE NOARGPLSQLSP;" +
            "\nPROCEDURE VARCHARPLSQLSP(X IN VARCHAR);" +
            "\nPROCEDURE INOUTARGSPLSQLSP(T IN VARCHAR, U OUT VARCHAR, V OUT NUMERIC);" +
            "\nPROCEDURE INOUTARGPLSQLSP(T IN OUT VARCHAR);" +
        "\nEND SIMPLEPACKAGE1;";
    static final String CREATE_SIMPLEPACKAGE1_BODY =
        "CREATE OR REPLACE PACKAGE BODY SIMPLEPACKAGE1 AS" +
            "\nPROCEDURE NOARGPLSQLSP AS" +
            "\nBEGIN" +
                "\nnull;" +
            "\nEND NOARGPLSQLSP;" +
            "\nPROCEDURE VARCHARPLSQLSP(X IN VARCHAR) AS" +
            "\nBEGIN" +
                "\nnull;" +
            "\nEND VARCHARPLSQLSP;" +
            "\nPROCEDURE INOUTARGSPLSQLSP(T IN VARCHAR, U OUT VARCHAR, V OUT NUMERIC) AS" +
            "\nBEGIN" +
                "\nU := CONCAT('barf-' , T);" +
                "\nV := 55;" +
            "\nEND INOUTARGSPLSQLSP;" +
            "\nPROCEDURE INOUTARGPLSQLSP(T IN OUT VARCHAR) AS" +
            "\nBEGIN" +
                "\nT := CONCAT('barf-' , T);" +
            "\nEND INOUTARGPLSQLSP;" +
        "\nEND SIMPLEPACKAGE1;";

    static final String DROP_SIMPLEPACKAGE1_PACKAGE =
        "DROP PACKAGE SIMPLEPACKAGE1";

    // JUnit test fixtures
    static String ddl = "false";

    @BeforeClass
    public static void setUp() throws WSDLException {
        if (conn == null) {
            try {
                conn = buildConnection();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        ddl = System.getProperty(DATABASE_DDL_KEY, DEFAULT_DATABASE_DDL);
        if ("true".equalsIgnoreCase(ddl)) {
            try {
                createDbArtifact(conn, CREATE_SIMPLEPACKAGE1_PACKAGE);
                createDbArtifact(conn, CREATE_SIMPLEPACKAGE1_BODY);
            }
            catch (SQLException e) {
                //e.printStackTrace();
            }
        }
        DBWS_BUILDER_XML_USERNAME =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<dbws-builder xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">" +
              "<properties>" +
                  "<property name=\"projectName\">simplePLSQLSP</property>" +
                  "<property name=\"logLevel\">off</property>" +
                  "<property name=\"username\">";
          DBWS_BUILDER_XML_PASSWORD =
                  "</property><property name=\"password\">";
          DBWS_BUILDER_XML_URL =
                  "</property><property name=\"url\">";
          DBWS_BUILDER_XML_DRIVER =
                  "</property><property name=\"driver\">";
          DBWS_BUILDER_XML_PLATFORM =
                  "</property><property name=\"platformClassname\">";
          DBWS_BUILDER_XML_MAIN =
                  "</property>" +
              "</properties>" +
              "<plsql-procedure " +
                  "name=\"VarcharTest\" " +
                  "catalogPattern=\"SIMPLEPACKAGE1\" " +
                  "procedurePattern=\"VARCHARPLSQLSP\" " +
                  "returnType=\"xsd:int\" " +
              "/>" +
              "<plsql-procedure " +
                  "name=\"NoArgsTest\" " +
                  "catalogPattern=\"SIMPLEPACKAGE1\" " +
                  "procedurePattern=\"NOARGPLSQLSP\" " +
                  "returnType=\"xsd:int\" " +
              "/>" +
              "<plsql-procedure " +
                  "name=\"InOutArgsTest\" " +
                  "catalogPattern=\"SIMPLEPACKAGE1\" " +
                  "procedurePattern=\"INOUTARGSPLSQLSP\" " +
                  "isSimpleXMLFormat=\"true\" " +
              "/>" +
              "<plsql-procedure " +
                  "name=\"InOutArgTest\" " +
                  "catalogPattern=\"SIMPLEPACKAGE1\" " +
                  "procedurePattern=\"INOUTARGPLSQLSP\" " +
              "/>" +
            "</dbws-builder>";
          builder = new DBWSBuilder();
          DBWSTestSuite.setUp(".");
    }

    @AfterClass
    public static void tearDown() {
        if ("true".equalsIgnoreCase(ddl)) {
            dropDbArtifact(conn, DROP_SIMPLEPACKAGE1_PACKAGE);
        }
    }

    @Test
    public void varcharTest() {
        Invocation invocation = new Invocation("VarcharTest");
        invocation.setParameter("X", "this is a test");
        Operation op = xrService.getOperation(invocation.getName());
        Object result = op.invoke(xrService, invocation);
        assertNotNull("result is null", result);
        Document doc = xmlPlatform.createDocument();
        XMLMarshaller marshaller = xrService.getXMLContext().createMarshaller();
        marshaller.marshal(result, doc);
        Document controlDoc = xmlParser.parse(new StringReader(VALUE_1_XML));
        assertTrue("Expected:\n" + documentToString(controlDoc) + "\nActual:\n" + documentToString(doc), comparer.isNodeEqual(controlDoc, doc));
    }
    public static final String VALUE_1_XML =
      "<?xml version = '1.0' encoding = 'UTF-8'?>" +
      "<value>1</value>";

    @Test
    public void noargsTest() {
        Invocation invocation = new Invocation("NoArgsTest");
        Operation op = xrService.getOperation(invocation.getName());
        Object result = op.invoke(xrService, invocation);
        assertNotNull("result is null", result);
        Document doc = xmlPlatform.createDocument();
        XMLMarshaller marshaller = xrService.getXMLContext().createMarshaller();
        marshaller.marshal(result, doc);
        Document controlDoc = xmlParser.parse(new StringReader(VALUE_1_XML));
        assertTrue("Expected:\n" + documentToString(controlDoc) + "\nActual:\n" + documentToString(doc), comparer.isNodeEqual(controlDoc, doc));
    }

    @Test
    public void inOutArgsTest() {
        Invocation invocation = new Invocation("InOutArgsTest");
        invocation.setParameter("T", "yuck");
        Operation op = xrService.getOperation(invocation.getName());
        Object result = op.invoke(xrService, invocation);
        assertNotNull("result is null", result);
        Document doc = xmlPlatform.createDocument();
        XMLMarshaller marshaller = xrService.getXMLContext().createMarshaller();
        marshaller.marshal(result, doc);
        Document controlDoc = xmlParser.parse(new StringReader(IN_OUT_ARGS_XML));
        assertTrue("Expected:\n" + documentToString(controlDoc) + "\nActual:\n" + documentToString(doc), comparer.isNodeEqual(controlDoc, doc));
    }
    public static final String IN_OUT_ARGS_XML =
      "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
      "<simple-xml-format>" +
        "<simple-xml>" +
          "<U>barf-yuck</U>" +
          "<V>55</V>" +
        "</simple-xml>" +
      "</simple-xml-format>";

    @Test
    public void inOutArgTest() {
        Invocation invocation = new Invocation("InOutArgTest");
        invocation.setParameter("T", "yuck");
        Operation op = xrService.getOperation(invocation.getName());
        Object result = op.invoke(xrService, invocation);
        assertNotNull("result is null", result);
        Document doc = xmlPlatform.createDocument();
        XMLMarshaller marshaller = xrService.getXMLContext().createMarshaller();
        marshaller.marshal(result, doc);
        Document controlDoc = xmlParser.parse(new StringReader(IN_OUT_ARG_XML));
        assertTrue("Expected:\n" + documentToString(controlDoc) + "\nActual:\n" + documentToString(doc), comparer.isNodeEqual(controlDoc, doc));
    }
    public static final String IN_OUT_ARG_XML =
      "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
      "<value>barf-yuck</value>";
}