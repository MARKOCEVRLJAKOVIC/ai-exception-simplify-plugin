package dev.marko.aiexceptiondetection;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.IOException;

public class AiAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {

        var editor = anActionEvent.getData(CommonDataKeys.EDITOR);
        if (editor == null) return;

        String selection = editor.getSelectionModel().getSelectedText();
        if (selection == null || selection.isEmpty()) return;

        Messages.showInfoMessage("Analyzing exception...\n" + selection, "AI Exception Advisor");

        new Thread(() -> {
            try {
                String response = AiService.analyze(selection);
                SwingUtilities.invokeLater(() ->
                        Messages.showInfoMessage(response, "AI Analysis Result")
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();

    }
}
