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
package org.dgroup.dockertest.text;

import java.io.File;
import java.io.UncheckedIOException;
import org.dgroup.dockertest.Assert;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Unit tests for class {@link FileAsString}.
 *
 * @author Yurii Dubinka (yurii.dubinka@gmail.com)
 * @version $Id$
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class FileAsStringTest {

    @Test
    public void fileContentWasReadedAsString() {
        MatcherAssert.assertThat(
            new FileAsString(
                new File(
                    new FormattedTextWithRepeatableArguments(
                        "src{0}test{0}resources{0}txt{0}tests{0}simple.txt",
                        File.separator
                    ).asString()
                )
            ).content(),
            Matchers.equalTo(
                "test:\nassume: \"curl version is 7.xxx\"\ncmd"
            )
        );
    }

    @Test
    public void fileNotFound() {
        new Assert().thatThrows(
            () -> new FileAsString(
                new File(".gitignoreeeeeee")
            ).content(),
            UncheckedIOException.class,
            "^java\\.io\\.FileNotFoundException:\\s\\.gitignoreeeeeee\\s\\(.*$"
        );
    }
}
