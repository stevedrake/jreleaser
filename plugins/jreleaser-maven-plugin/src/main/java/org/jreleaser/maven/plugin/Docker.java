/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2020-2021 The JReleaser authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jreleaser.maven.plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andres Almiray
 * @since 0.1.0
 */
public class Docker extends AbstractDockerConfiguration implements Tool {
    private final List<DockerSpec> specs = new ArrayList<>();
    protected Boolean continueOnError;

    void setAll(Docker docker) {
        super.setAll(docker);
        this.continueOnError = docker.continueOnError;
        setSpecs(docker.specs);
    }

    @Override
    public boolean isContinueOnError() {
        return continueOnError != null && continueOnError;
    }

    @Override
    public void setContinueOnError(Boolean continueOnError) {
        this.continueOnError = continueOnError;
    }

    @Override
    public boolean isContinueOnErrorSet() {
        return continueOnError != null;
    }

    public List<DockerSpec> getSpecs() {
        return specs;
    }

    public void setSpecs(List<DockerSpec> specs) {
        this.specs.clear();
        this.specs.addAll(specs);
    }

    public boolean isSet() {
        return super.isSet() ||
            !specs.isEmpty();
    }
}
