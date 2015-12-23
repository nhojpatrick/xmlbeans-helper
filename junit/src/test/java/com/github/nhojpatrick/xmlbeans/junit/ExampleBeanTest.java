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
package com.github.nhojpatrick.xmlbeans.junit;

import static com.github.nhojpatrick.xmlbeans.junit.JUnitAssertXMLBeans.assertInvalid;
import static com.github.nhojpatrick.xmlbeans.junit.JUnitAssertXMLBeans.assertValid;

import java.util.UUID;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.nhojpatrick.xmlbeans.testing.schema.v1.ExampleBeanDocument;
import com.github.nhojpatrick.xmlbeans.testing.schema.v1.ExampleBeanDocument.ExampleBean;

public class ExampleBeanTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void assertInvalid_invalid() throws Exception {
        assertInvalid(getInvalidXmlBean());
    }

    @Test
    public void assertInvalid_invalidMsg() throws Exception {
        assertInvalid(getInvalidXmlBean(), "Xml shouldn't be valid");
    }

    @Test
    public void assertInvalid_invalidNull() throws Exception {
        assertInvalid(getInvalidXmlBean(), null);
    }

    @Test
    public void assertInvalid_valid() throws Exception {
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("expected xml <invalid>, actual xml <valid>");

        assertInvalid(getValidXmlBean());
    }

    @Test
    public void assertInvalid_validMsg() throws Exception {
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("expected xml <invalid>, actual xml <valid>");

        assertInvalid(getValidXmlBean(), "Should be valid xmlbean object");
    }

    @Test
    public void assertInvalid_validNull() throws Exception {
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("expected xml <invalid>, actual xml <valid>");

        assertInvalid(getValidXmlBean(), null);
    }

    @Test
    public void assertValid_invalid() throws Exception {
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("expected xml <valid>, actual xml <invalid>");

        assertValid(getInvalidXmlBean());
    }

    @Test
    public void assertValid_invalidMsg() throws Exception {
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("Xml shouldn't be valid");

        assertValid(getInvalidXmlBean(), "Xml shouldn't be valid");
    }

    @Test
    public void assertValid_invalidNull() throws Exception {
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("expected xml <valid>, actual xml <invalid>");

        assertValid(getInvalidXmlBean(), null);
    }

    @Test
    public void assertValid_valid() throws Exception {
        assertValid(getValidXmlBean());
    }

    @Test
    public void assertValid_validMsg() throws Exception {
        assertValid(getValidXmlBean(), "Should be valid xmlbean object");
    }

    @Test
    public void assertValid_validNull() throws Exception {
        assertValid(getValidXmlBean(), null);
    }

    private ExampleBeanDocument getInvalidXmlBean() {
        return ExampleBeanDocument.Factory.newInstance();
    }

    private ExampleBean getValidXmlBean() {
        final ExampleBeanDocument exampleBeanDoc = ExampleBeanDocument.Factory.newInstance();

        final ExampleBean exampleBean = exampleBeanDoc.addNewExampleBean();
        exampleBean.setExampleString("exampleString");
        exampleBean.setExampleUuid(UUID.randomUUID().toString());

        return exampleBean;
    }

}
