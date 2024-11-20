package com.gls.glsplugin.language;

import com.gls.glsplugin.language.psi.GillesAssignBlock;

import com.google.common.collect.Lists;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.gls.glsplugin.language.psi.GillesFile;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GillesUtil {

    /**
     * Searches the entire project for Gilles language files with instances of the Gilles property with the given key.
     *
     * @param project current project
     * @param key     to check
     * @return matching properties
     */
    public static List<GillesAssignBlock> findProperties(Project project, String key) {
        List<GillesAssignBlock> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(GillesFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            GillesFile gillesFile = (GillesFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (gillesFile != null) {
                Collection<GillesAssignBlock> properties = PsiTreeUtil.collectElementsOfType(gillesFile, GillesAssignBlock.class);
                for (GillesAssignBlock property : properties) {
                    if (key.equals(property.getKey())) {
                        result.add(property);
                    }
                }
            }
        }
        return result;
    }

    public static List<GillesAssignBlock> findProperties(Project project) {
        List<GillesAssignBlock> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(GillesFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            GillesFile gillesFile = (GillesFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (gillesFile != null) {
                Collection<GillesAssignBlock> properties = PsiTreeUtil.collectElementsOfType(gillesFile, GillesAssignBlock.class);
                result.addAll(properties);
            }
        }
        return result;
    }

    /**
     * Attempts to collect any comment elements above the Gilles key/value pair.
     */
    public static @NotNull String findDocumentationComment(GillesAssignBlock property) {
        List<String> result = new LinkedList<>();
        PsiElement element = property.getPrevSibling();
        while (element instanceof PsiComment || element instanceof PsiWhiteSpace) {
            if (element instanceof PsiComment) {
                String commentText = element.getText().replaceFirst("[$ ]+", "");
                result.add(commentText);
            }
            element = element.getPrevSibling();
        }
        return StringUtil.join(Lists.reverse(result), "\n ");
    }
}
