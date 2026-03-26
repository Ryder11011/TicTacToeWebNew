package com.example.TicTacToe;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.util.concurrent.ThreadLocalRandom;

public class Play extends VerticalLayout {
    private Button[][] buttonPlayGround = new Button[3][3];
    private Xmovement engine = new Xmovement(); // Your existing logic engine
    private Background parentView;
    private boolean win;

    // Adjusted colors to match your dark-mode arcade design
    private final String O_ORANGE = "#F0B132"; // Golden Orange
    private final String X_BLUE = "#4CC9F0";    // Neon Cyan-Blue

    public Play(Background background) {
        this.parentView = background;

        // 1. Setup the Board Container Styling
        setSpacing(false);
        setPadding(true);
        setAlignItems(Alignment.CENTER);
        getStyle().set("background-color", "#150824");
        getStyle().set("padding", "40px");
        getStyle().set("border-radius", "0px");

        // 2. Create the Grid and map it to your internal matrix
        initializeGrid();

        // 3. Initial move logic from your original Swing code
        engine.playGround[0][0] = 1;
        mapButtons();
        engine.counter++;
    }

    private void initializeGrid() {
        // We use a random number like your mapping(int number) function
        int mappingType = ThreadLocalRandom.current().nextInt(1, 4);

        for (int i = 0; i < 3; i++) {
            HorizontalLayout row = new HorizontalLayout();
            row.setSpacing(true);
            for (int j = 0; j < 3; j++) {
                Button btn = new Button("");
                styleGridButton(btn);

                // Handle clicks
                btn.addClickListener(e -> buttonFunction(btn));

                // Map the buttons into the 2D array based on your random mapping logic
                mapButtonToMatrix(btn, i, j, mappingType);
                row.add(btn);
            }
            add(row);
            getStyle().set("gap", "10px");
        }
    }

    private void mapButtonToMatrix(Button btn, int i, int j, int type) {
        // Simplified version of your original mapping() function
        if (type == 2) { // Reverse horizontal
            buttonPlayGround[i][2 - j] = btn;
        } else if (type == 3) { // Reverse vertical
            buttonPlayGround[2 - i][j] = btn;
        } else { // Standard mapping
            buttonPlayGround[i][j] = btn;
        }
    }

    private void styleGridButton(Button btn) {
        btn.setWidth("120px");
        btn.setHeight("120px");
        btn.getStyle().set("font-size", "60px");
        btn.getStyle().set("font-weight", "bold");
        btn.getStyle().set("background-color", "#2D1B4E");
        btn.getStyle().set("border", "none");
        btn.getStyle().set("border-radius", "0px");
        btn.getStyle().set("box-shadow", "none");
        btn.getStyle().set("cursor", "pointer");
    }

    public void buttonFunction(Button btn) {
        if (btn.getText().isEmpty()) {
            btn.setText("O");
            btn.getStyle().set("color", O_ORANGE);

            mapMatrix();
            win = engine.xMove(); // Your engine handles the AI logic or game rules
            mapButtons();

            if (win) {
                showWinDialog();
            }
        }
    }

    private void mapButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (engine.playGround[i][j] == -1) {
                    buttonPlayGround[i][j].setText("O");
                    buttonPlayGround[i][j].getStyle().set("color", O_ORANGE);
                } else if (engine.playGround[i][j] == 1) {
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
                if ("X".equals(text)) engine.playGround[i][j] = 1;
                else if ("O".equals(text)) engine.playGround[i][j] = -1;
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