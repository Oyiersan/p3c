/*
 * Copyright 1999-2017 Alibaba Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xenoamess.p3c.pmd.lang.java.rule.dubbo;

import com.xenoamess.p3c.pmd.I18nResources;
import com.xenoamess.p3c.pmd.lang.AbstractAliXpathRule;
import com.xenoamess.p3c.pmd.lang.java.util.ViolationUtils;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;

/**
 * dubbo 接口命名需要以 RemoteService 结尾
 *
 * @author zhangjiawu
 * @since 2021/09/01
 */
public class InterfaceClassShouldEndWithRemoteServiceNamingRule extends AbstractAliXpathRule {

    private static final String XPATH = "//ClassOrInterfaceDeclaration[@Interface=true() and (matches(@BinaryName,'.*dubbo.interfaces.*'))]" +
            "[not (matches(@SimpleName,'.*RemoteService$'))]";

    public InterfaceClassShouldEndWithRemoteServiceNamingRule() {
        setXPath(XPATH);
    }

    @Override
    public void addViolation(Object data, Node node, String arg) {
        if (node instanceof ASTClassOrInterfaceDeclaration) {
            ViolationUtils.addViolationWithPrecisePosition(this, node, data,
                    I18nResources.getMessage("java.naming.InterfaceClassShouldEndWithRemoteServiceNamingRule.violation.msg",
                            node.getImage()));
        } else {
            super.addViolation(data, node, arg);
        }
    }
}
