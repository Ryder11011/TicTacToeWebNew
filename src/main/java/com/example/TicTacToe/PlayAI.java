package com.example.TicTacToe;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class PlayAI extends VerticalLayout {
    private Button[][] buttonPlayGround = new Button[3][3];
    private AI engine = new AI();
    private Background2 parentView;

    private final String O_ORANGE = "#F0B132"; // Golden Orange
    private final String X_BLUE = "#4CC9F0";    // Neon Cyan-Blue

    public PlayAI(Background2 background) {
        this.parentView = background;

        setSpacing(false);
        setPadding(true);
        setAlignItems(Alignment.CENTER);

        getStyle().set("background-color", "#150824");
        getStyle().set("padding", "40px");
        getStyle().set("border-radius", "0px");

        for (int i = 0; i < 3; i++) {
            HorizontalLayout row = new HorizontalLayout();
            row.setSpacing(true);
            for (int j = 0; j < 3; j++) {
                Button btn = new Button("");
                styleGridButton(btn);

                final int rowIdx = i;
                final int colIdx = j;
                btn.addClickListener(e -> handleButtonClick(btn, rowIdx, colIdx));

                buttonPlayGround[i][j] = btn;
                row.add(btn);
            }
            add(row);
            // Add vertical spacing between rows
            getStyle().set("gap", "10px");
        }
    }

    private void styleGridButton(Button btn) {
        btn.setWidth("120px");
        btn.setHeight("120px");
        btn.getStyle().set("font-size", "60px");
        btn.getStyle().set("font-weight", "bold");

        // The "Flat Tile" Look
        btn.getStyle().set("background-color", "#2D1B4E");
        btn.getStyle().set("border", "none");
        btn.getStyle().set("border-radius", "0px");
        btn.getStyle().set("box-shadow", "none");
        btn.getStyle().set("cursor", "pointer");
    }

    private void handleButtonClick(Button btn, int row, int col) {
        if (btn.getText().isEmpty()) {
            btn.setText("X");
            btn.getStyle().set("color", X_BLUE);

            mapMatrix();
            engine.algorithm(0, true, engine.playGround, true, 0, 0);
            engine.play();

            mapButtons();
            engine.reset();

            if (engine.checkWin() == 10) {
                showWinDialog();
            }
        }
    }

    private void mapButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (engine.playGround[i][j] == 1) {
                    buttonPlayGround[i][j].setText("O");
                    buttonPlayGround[i][j].getStyle().set("color", O_ORANGE);
                } else if (engine.playGround[i][j] == -1) {
                    buttonPlayGround[i][j].setText("X");
                    buttonPlayGround[i][j].getStyle().set("color", X_BLUE);
                }
            }
        }
    }

    private void mapMatrix() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String text = buttonPlayGround[i][j].getText();
                if ("X".equals(text)) engine.playGround[i][j] = -1;
                else if ("O".equals(text)) engine.playGround[i][j] = 1;
            }
        }
    }

    public void showWinDialog() {
        Dialog dialog = new Dialog();
        dialog.add(new Span("You Lose!"));
        Button closeAndReset = new Button("Try Again", e -> {
            parentView.reset();
            dialog.close();
        });
        dialog.add(new VerticalLayout(closeAndReset));
        dialog.open();
    }
}