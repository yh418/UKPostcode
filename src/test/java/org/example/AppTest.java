package org.example;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.example.util.HTTPutil;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue(HTTPutil.getInstance().validate("CB3 0FA"));
        assertFalse(HTTPutil.getInstance().validate("AAA AAA"));
        assertFalse(HTTPutil.getInstance().validate(null));
    }
}
