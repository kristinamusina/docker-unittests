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
package org.dgroup.dockertest.docker.command;

import java.util.List;
import org.cactoos.list.Joined;
import org.cactoos.list.ListOf;
import org.dgroup.dockertest.docker.DockerCommand;

/**
 * Represents a command for stateless docker container.
 * Container will be removed automatically by docker after execution of command.
 * See `docker --rm` options for details.
 *
 * @author Yurii Dubinka (yurii.dubinka@gmail.com)
 * @version $Id$
 * @since 0.1.0
 */
public final class StatelessDockerContainer implements DockerCommand {

    /**
     * Docker container commands.
     */
    private final List<String> cmd;

    /**
     * Ctor.
     * @param image Docker image for testing.
     * @param cmd Command to be executed inside of docker container.
     */
    public StatelessDockerContainer(final String image, final String... cmd) {
        this.cmd = new Joined<>(
            new ListOf<>("docker", "run", "--rm", image),
            new ListOf<>(cmd)
        );
    }

    /**
     * Docker container arguments.
     * @return Container arguments.
     */
    public List<String> args() {
        return this.cmd;
    }

}