package com.example.TicTacToe;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("background")
public class Background extends HorizontalLayout {

    private VerticalLayout mainPanel = new VerticalLayout();
    private VerticalLayout sidePanel = new VerticalLayout();
    private Play gui;

    private Button resetButton = new Button("Reset");
    private Button menuButton = new Button("Main Menu");
    private Button aiEnemyButton = new Button("AI Enemy");

    public Background() {
        // Initialize game board inside constructor to avoid startup crashes
        this.gui = new Play(this);

        setSizeFull();
        setSpacing(false);
        setPadding(false);

        // Side Panel Setup
        sidePanel.setWidth("300px");
        sidePanel.setHeightFull();
        sidePanel.getStyle().set("background-color", "#1E0A32");
        sidePanel.setJustifyContentMode(JustifyContentMode.CENTER);
        sidePanel.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        sidePanel.setSpacing(true);

        styleYellowButton(aiEnemyButton);
        styleYellowButton(menuButton);
        sidePanel.add(aiEnemyButton, menuButton);

        // Main Panel Setup
        mainPanel.setSizeFull();
        mainPanel.getStyle().set("background-color", "#2D1946");
        mainPanel.setJustifyContentMode(JustifyContentMode.CENTER);
        mainPanel.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        styleYellowButton(resetButton);
        resetButton.getStyle().set("margin-top", "30px");
        mainPanel.add(gui, resetButton);

        add(sidePanel, mainPanel);

        // --- BUTTON FUNCTIONS START HERE ---

        // 1. Reset Button: Refreshes the game board
        resetButton.addClickListener(e -> reset());

        // 2. Main Menu Button: Navigates back to the root page ("")
        menuButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate(""));
        });

        // 3. AI Enemy Button: Navigates to the AI version of the game
        aiEnemyButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate("background2"));
        });

        // --- BUTTON FUNCTIONS END HERE ---
    }

    public void reset() {
        mainPanel.remove(gui);
        this.gui = new Play(this);
        // Using addComponentAtIndex ensures the board stays above the Reset button
        mainPanel.addComponentAtIndex(0, gui);
    }

    private void styleYellowButton(Button button) {
        button.setWidth("200px");
        button.setHeight("60px");
        button.getStyle().set("background-color", "#F0B132");
        button.getStyle().set("color", "black");
        button.getStyle().set("font-weight", "bold");
        button.getStyle().set("font-size", "16px");
        button.getStyle().set("border-radius", "8px");
        button.getStyle().set("cursor", "pointer");
    }
}