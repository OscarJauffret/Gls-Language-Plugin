package com.gls.glsplugin.language;

import com.gls.glsplugin.language.psi.GillesAssignBlock;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.impl.source.tree.java.PsiJavaTokenImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class GillesLineMarkerProvider extends RelatedItemLineMarkerProvider {

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        // This must be an element with a literal expression as a parent
        if (!(element instanceof PsiJavaTokenImpl) || !(element.getParent() instanceof PsiLiteralExpression literalExpression)) {
            return;
        }

        // The literal expression must start with the Gilles language literal expression
        String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
        if ((value == null) ||
                !value.startsWith(GillesAnnotator.GILLES_PREFIX_STR + GillesAnnotator.GILLES_SEPARATOR_STR)) {
            return;
        }

        // Get the Gilles language property usage
        Project project = element.getProject();
        String possibleProperties = value.substring(
                GillesAnnotator.GILLES_PREFIX_STR.length() + GillesAnnotator.GILLES_SEPARATOR_STR.length()
        );
        final List<GillesAssignBlock> properties = GillesUtil.findProperties(project, possibleProperties);
        if (!properties.isEmpty()) {
            // Add the property to a collection of line marker info
            NavigationGutterIconBuilder<PsiElement> builder =
                    NavigationGutterIconBuilder.create(GillesIcons.FILE)
                            .setTargets(properties)
                            .setTooltipText("Navigate to Gilles language property");
            result.add(builder.createLineMarkerInfo(element));
        }
    }
}
