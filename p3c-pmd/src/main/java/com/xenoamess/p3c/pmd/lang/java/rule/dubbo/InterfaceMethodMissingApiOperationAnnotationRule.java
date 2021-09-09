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
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * dubbo 接口命名需要以 RemoteService 结尾
 *
 * @author zhangjiawu
 * @since 2021/09/01
 */
public class InterfaceMethodMissingApiOperationAnnotationRule extends AbstractAliXpathRule {

    private static final String XPATH = "//ClassOrInterfaceBodyDeclaration[@Kind='METHOD'][count(./Annotation/NormalAnnotation[@AnnotationName='ApiOperation'])=0][//ClassOrInterfaceDeclaration[@Interface and (matches(@BinaryName,'.*dubbo.interfaces.*')) and matches(@SimpleName,'.*RemoteService$')]]";

    public InterfaceMethodMissingApiOperationAnnotationRule() {
        setXPath(XPATH);
    }

    @Override
    public void addViolation(Object data, Node node, String arg) {
        System.out.println(node);
        List<ASTMethodDeclaration> childrenOfType = node.findChildrenOfType(ASTMethodDeclaration.class);

        if (CollectionUtils.isNotEmpty(childrenOfType)) {
            childrenOfType.forEach(astMethodDeclaration -> {
                ViolationUtils.addViolationWithPrecisePosition(this, astMethodDeclaration, data,
                        I18nResources.getMessage("java.naming.InterfaceMethodMissingApiOperationAnnotationRule.violation.msg",
                                astMethodDeclaration.getName()));
            });
        } else {
            super.addViolation(data, node, arg);
        }
    }

}
