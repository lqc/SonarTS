/*
 * SonarTS
 * Copyright (C) 2017-2018 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.plugin.typescript.rules;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.Arrays;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;

@Rule(key = "S4328")
public class NoImplicitDependencies extends TypeScriptRule {

  private static final String DEFAULT_WHITELIST = "";

  @RuleProperty(key = "whitelist",
    description = "Comma separated list of modules to ignore while checking in package.json",
    defaultValue = DEFAULT_WHITELIST)
  String whitelist = DEFAULT_WHITELIST;

  @Override
  public String tsLintKey() {
    return "no-implicit-dependencies";
  }

  @Override
  public JsonElement configuration() {
    JsonArray whitelistModules = new JsonArray();
    Arrays.stream(whitelist.split(",")).forEach(whitelistModules::add);
    return ruleConfiguration("dev", whitelistModules);
  }
}
