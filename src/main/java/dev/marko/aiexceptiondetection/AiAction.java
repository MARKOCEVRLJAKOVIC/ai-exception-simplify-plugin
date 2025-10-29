package dev.marko.aiexceptiondetection;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import org.jetbrains.annotations.NotNull;

public class AiAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {

        var editor = anActionEvent.getData(CommonDataKeys.EDITOR);
        if (editor == null) return;


    }
}
