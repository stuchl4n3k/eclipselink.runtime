/*******************************************************************************
 * Copyright (c) 1998, 2015 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/
package org.eclipse.persistence.testing.tests.jpql;

import org.eclipse.persistence.testing.framework.*;

public class JPQLValidationTestSuite extends TestSuite {
    public void addTests() {
        JPQLExceptionTest.addTestsTo(this);

        addTest(IdentifierTest.badIdentifierTest1());
        addTest(IdentifierTest.badIdentifierTest2());

        //validate that the parsing is done only once
        addTest(new ParseOnceTest());

        addTest(new ParameterNameMismatchTest());
        addTest(new OneToManyJoinOptimizationTest());
    }
}
