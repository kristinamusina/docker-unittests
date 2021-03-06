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
package org.dgroup.dockertest.yml.tag.test;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.cactoos.collection.Filtered;
import org.cactoos.list.StickyList;
import org.dgroup.dockertest.text.PlainFormattedText;
import org.dgroup.dockertest.yml.IllegalYmlFileFormatException;
import org.dgroup.dockertest.yml.tag.YmlTag;
import org.dgroup.dockertest.yml.tag.YmlTagOf;
import org.dgroup.dockertest.yml.tag.output.YmlTagOutputOf;
import org.dgroup.dockertest.yml.tag.output.YmlTagOutputPredicate;

/**
 * Represents yml tag {@code /tests/test}.
 * Tag can contain {@code assume}, {@code cmd} and {@link YmlTagOutputOf}.
 *
 * @author Yurii Dubinka (yurii.dubinka@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@SuppressWarnings("PMD")
public final class YmlTagTestOf implements YmlTagTest {

    /**
     * Yml tag `test` transformed to object.
     */
    private final YmlTag tag;

    /**
     * Ctor.
     * @param yml Yml tag `test` transformed to object.
     */
    public YmlTagTestOf(final Map<String, Object> yml) {
        this(new YmlTagOf(yml, "test"));
    }

    /**
     * Ctor.
     * @param tag Yml tag transformed to object.
     */
    @SuppressWarnings("unchecked")
    public YmlTagTestOf(final YmlTag tag) {
        this.tag = tag;
    }

    @Override
    public String name() {
        return this.tag.name();
    }

    @Override
    public String assume() throws IllegalYmlFileFormatException {
        return this.child("assume");
    }

    @Override
    public String cmd() throws IllegalYmlFileFormatException {
        return this.child("cmd");
    }

    @Override
    public String[] containerCommandAsArray()
        throws IllegalYmlFileFormatException {
        return this.cmd().split(" ");
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<YmlTagOutputPredicate> output()
        throws IllegalYmlFileFormatException {
        final String child = "output";
        this.tag.assertThatTagContain(child);
        return new YmlTagOutputOf(
            new StickyList<>(
                new Filtered<>(
                    Objects::nonNull,
                    (List<Map<String, String>>) this.tag.asMap().get(child)
                )
            )
        ).conditions();
    }

    @Override
    public String asString() throws IllegalYmlFileFormatException {
        return new PlainFormattedText(
            "tag `%s`, assume `%s`, cmd `%s`, output `%s`",
            this.tag,
            this.assume(),
            this.cmd(),
            this.output().size()
        ).asString();
    }

    @Override
    public Object asObject() throws IllegalYmlFileFormatException {
        return this.tag.asObject();
    }

    /**
     * Give child tag value as a string.
     * @param child Tag name.
     * @return Child tag
     * @throws IllegalYmlFileFormatException in case if tag is null/missing
     *  or has no value.
     */
    private String child(final String child)
        throws IllegalYmlFileFormatException {
        this.tag.assertThatTagContain(child);
        return this.tag.asMap().get(child).toString();
    }
}
