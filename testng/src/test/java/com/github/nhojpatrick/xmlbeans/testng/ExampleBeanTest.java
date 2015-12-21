package com.github.nhojpatrick.xmlbeans.testng;

import static com.github.nhojpatrick.xmlbeans.testng.TestNGAssertXMLBeans.assertInvalid;
import static com.github.nhojpatrick.xmlbeans.testng.TestNGAssertXMLBeans.assertValid;

import java.util.UUID;

import org.testng.annotations.Test;

import com.github.nhojpatrick.xmlbeans.testing.schema.v1.ExampleBeanDocument;
import com.github.nhojpatrick.xmlbeans.testing.schema.v1.ExampleBeanDocument.ExampleBean;

public class ExampleBeanTest {

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

    @Test(expectedExceptions = { AssertionError.class }, expectedExceptionsMessageRegExp = "expected xml <invalid>, actual xml <valid>")
    public void assertInvalid_valid() throws Exception {
        assertInvalid(getValidXmlBean());
    }

    @Test(expectedExceptions = { AssertionError.class }, expectedExceptionsMessageRegExp = "Should be valid xmlbean object expected xml <invalid>, actual xml <valid>")
    public void assertInvalid_validMsg() throws Exception {
        assertInvalid(getValidXmlBean(), "Should be valid xmlbean object");
    }

    @Test(expectedExceptions = { AssertionError.class }, expectedExceptionsMessageRegExp = "expected xml <invalid>, actual xml <valid>")
    public void assertInvalid_validNull() throws Exception {
        assertInvalid(getValidXmlBean(), null);
    }

    @Test(expectedExceptions = { AssertionError.class }, expectedExceptionsMessageRegExp = "expected xml <valid>, actual xml <invalid>")
    public void assertValid_invalid() throws Exception {
        assertValid(getInvalidXmlBean());
    }

    @Test(expectedExceptions = { AssertionError.class }, expectedExceptionsMessageRegExp = "Xml shouldn't be valid\n>>Problem:error: cvc-complex-type.2.4c: Expected element 'example-bean@http://github.com/nhojpatrick/xmlbeans/testing/schema/v1' before the end of the content")
    public void assertValid_invalidMsg() throws Exception {
        assertValid(getInvalidXmlBean(), "Xml shouldn't be valid");
    }

    @Test(expectedExceptions = { AssertionError.class }, expectedExceptionsMessageRegExp = "expected xml <valid>, actual xml <invalid>")
    public void assertValid_invalidNull() throws Exception {
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
