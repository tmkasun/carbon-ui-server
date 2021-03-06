/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.uis.api;

import org.wso2.carbon.uis.api.util.Multilocational;
import org.wso2.carbon.uis.api.util.Overridable;
import org.wso2.carbon.uis.internal.impl.OverriddenExtension;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents an extension of a web app.
 *
 * @since 0.8.0
 */
public class Extension implements Multilocational, Overridable<Extension> {

    private final String name;
    private final String type;
    private final List<String> paths;

    /**
     * Creates a new extension which can be located in the specified path.
     *
     * @param name name of the extension
     * @param type type of the extension
     * @param path path to the extension
     */
    public Extension(String name, String type, String path) {
        this(name, type, Collections.singletonList(path));
    }

    /**
     * Creates a new extension.
     *
     * @param name  name of the extension
     * @param type  type of the extension
     * @param paths paths to the extension
     */
    protected Extension(String name, String type, List<String> paths) {
        this.name = name;
        this.type = type;
        this.paths = paths;
    }

    /**
     * Returns the name of this extension.
     *
     * @return name of this extension
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of this extension.
     *
     * @return type of the extension
     */
    public String getType() {
        return type;
    }

    /**
     * Returns paths that this extension can be located.
     *
     * @return paths of the theme
     */
    @Override
    public List<String> getPaths() {
        return paths;
    }

    @Override
    public Extension override(Extension override) {
        if (!canOverrideBy(override)) {
            throw new IllegalArgumentException(this + " cannot be overridden by " + override + " .");
        }
        return new OverriddenExtension(this, override);
    }

    @Override
    public Extension getBase() {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Extension)) {
            return false;
        }
        Extension other = (Extension) obj;
        return Objects.equals(name, other.name) && Objects.equals(type, other.type) &&
               Objects.equals(paths, other.paths);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return "Extension{name='" + name + "', type='" + type + "', paths=" + paths + "}";
    }
}
