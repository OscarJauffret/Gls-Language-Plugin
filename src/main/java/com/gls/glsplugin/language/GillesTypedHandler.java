package com.gls.glsplugin.language;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.EditorModificationUtil;

public class GillesTypedHandler extends TypedHandlerDelegate {
    @Override
    public @NotNull Result charTyped(char c, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
        if (!(file.getFileType() instanceof GillesFileType)) {
            return Result.CONTINUE;
        }

        // Complete {, (, |, [ with }, ), |, ]
        if (c == '{') {
            EditorModificationUtil.insertStringAtCaret(editor, "}", false, 0);
            return Result.STOP;
        }

        if (c == '(') {
            EditorModificationUtil.insertStringAtCaret(editor, ")", false, 0);
            return Result.STOP;
        }

        if (c == '|') {
            EditorModificationUtil.insertStringAtCaret(editor, "|", false, 0);
            return Result.STOP;
        }

        if (c == '[') {
            EditorModificationUtil.insertStringAtCaret(editor, "]", false, 0);
            return Result.STOP;
        }

        return Result.CONTINUE;

    }

}
