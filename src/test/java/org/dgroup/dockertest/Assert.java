/**
 * MIT License
 *
 * Copyright (c) 2017 Yurii Dubinka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom
 * the Software is  furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.dgroup.dockertest;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;

/**
 * Simplify unit testing of exception throwing.
 *
 * @author Yurii Dubinka (yurii.dubinka@gmail.com)
 * @version $Id$
 * @since 1.0
 * @checkstyle NonStaticMethodCheck (200 lines)
 */
public final class Assert {

    /**
     * Verify that exception was thrown during particular operation.
     *
     * @param task Particular operation where exception is required.
     * @param exp Details which expected to be thrown.
     */
    public void thatThrows(final ThrowingCallable task, final Exception exp) {
        Assertions.assertThatThrownBy(task)
            .isInstanceOf(exp.getClass())
            .hasMessage(exp.getMessage());
    }

    /**
     * Verify that exception was thrown during particular operation.
     *
     * @param task A particular operation where exception must be raised.
     * @param exp Exception class which expected to be thrown.
     * @param regexp A regular expression which should match to occurred
     *  exception message.
     */
    public void thatThrows(final ThrowingCallable task,
        final Class<? extends Exception> exp, final String regexp) {
        Assertions.assertThatThrownBy(task)
            .isInstanceOf(exp)
            .hasMessageMatching(regexp);
    }
}
