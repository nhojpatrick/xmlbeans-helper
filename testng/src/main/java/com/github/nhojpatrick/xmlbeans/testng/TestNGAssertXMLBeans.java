/*
 * Copyright (c) 2015 https://github.com/nhojpatrick
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.github.nhojpatrick.xmlbeans.testng;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.testng.Assert;

/**
 * TestNG Assert XmlBeans, assert logic for xml beans and TestNG.
 *
 * @author nhojpatrick
 * @since 1.0.0
 */
public class TestNGAssertXMLBeans extends Assert {

    /**
     * Asserts that a <code>XmlObject</code> is valid. If it isn't, an
     * AssertionError, with the given message, is thrown.
     *
     * @param actual
     *            the <code>XmlObject</code> to evaluate
     * @param message
     *            the assertion error message
     */
    public static void assertValid(final XmlObject actual, final String message) {
        final boolean condition = actual.validate();

        if (!condition) {
            String errorMessage = "expected xml <valid>, actual xml <invalid>";

            if (message != null) {
                errorMessage = message + " " + errorMessage;

                final List validationErrors = new ArrayList();
                final XmlOptions validationOptions = new XmlOptions();
                validationOptions.setErrorListener(validationErrors);
                final boolean isValid = actual.validate(validationOptions);

                if (!isValid) {
                    final Iterator iter = validationErrors.iterator();
                    while (iter.hasNext()) {
                        errorMessage = message + "\n>>Problem:" + iter.next();
                    }
                }
            }

            fail(errorMessage);
        }
    }

    /**
     * Asserts that a <code>XmlObject</code> is valid. If it isn't, an
     * AssertionError is thrown.
     *
     * @param actual
     *            the <code>XmlObject</code> to evaluate
     */
    public static void assertValid(final XmlObject actual) {
        assertValid(actual, null);
    }

    /**
     * Asserts that a <code>XmlObject</code> is invalid. If it isn't, an
     * AssertionError, with the given message, is thrown.
     *
     * @param actual
     *            the <code>XmlObject</code> to evaluate
     * @param message
     *            the assertion error message
     */
    public static void assertInvalid(final XmlObject actual, final String message) {
        final boolean condition = actual.validate();

        if (condition) {

            String errorMessage = "expected xml <invalid>, actual xml <valid>";
            if (message != null) {
                errorMessage = message + " " + errorMessage;
            }

            fail(errorMessage);
        }
    }

    /**
     * Asserts that a <code>XmlObject</code> is invalid. If it isn't, an
     * AssertionError is thrown.
     *
     * @param actual
     *            the <code>XmlObject</code> to evaluate
     */
    public static void assertInvalid(final XmlObject actual) {
        assertInvalid(actual, null);
    }

}
