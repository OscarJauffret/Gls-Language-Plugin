package com.gls.glsplugin.language;

import com.intellij.lang.Commenter;
import org.jetbrains.annotations.Nullable;

public class GillesCommenter implements Commenter {
    @Override
    public String getLineCommentPrefix() {
        return "$";
    }

    @Override
    public String getBlockCommentPrefix() {
        return "!!";
    }

    @Override
    public String getBlockCommentSuffix() {
        return "!!";
    }

    @Nullable
    @Override
    public String getCommentedBlockCommentPrefix() {
        return null;
    }

    @Nullable
    @Override
    public String getCommentedBlockCommentSuffix() {
        return null;
    }
}
