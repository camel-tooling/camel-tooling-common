/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.cameltooling.model.diagnostic;

import java.util.Arrays;
import java.util.Map;

import org.apache.camel.catalog.EndpointValidationResult;

public class EnumErrorMsg implements CamelDiagnosticEndpointMessage<Map.Entry<String, String>> {
    @Override
    public String getErrorMessage(EndpointValidationResult result, Map.Entry<String, String> entry) {
        String name = entry.getKey();
        String[] choices = result.getInvalidEnumChoices().get(name);
        String defaultValue = result.getDefaultValues() != null ? result.getDefaultValues().get(entry.getKey()) : null;
        String str = Arrays.asList(choices).toString();
        String msg = "Invalid enum value: " + entry.getValue() + ". Possible values: " + str;
        if (result.getInvalidEnumChoices() != null) {
            String[] suggestions = result.getInvalidEnumChoices().get(name);
            if (suggestions != null && suggestions.length > 0) {
                str = Arrays.asList(suggestions).toString();
                msg += ". Did you mean: " + str;
            }
        }
        if (defaultValue != null) {
            msg += ". Default value: " + defaultValue;
        }
        return msg;
    }
}