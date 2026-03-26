package com.example.TicTacToe;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout; // Changed to HorizontalLayout!
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("background2")
public class Background2 extends HorizontalLayout { // <--- Must be Horizontal to go left-to-right
    private VerticalLayout mainPanel = new VerticalLayout();
    private VerticalLayout sidePanel = new VerticalLayout();

    // 1. Declare here, but DO NOT initialize yet to prevent the Spring Boot crash
    private PlayAI gui;

    private Button resetButton = new Button("Reset");
    private Button menuButton = new Button("Main Menu");
    private Button mathEnemyButton = new Button("Math Enemy");

    public Background2() {
        // 2. Initialize the GUI here inside the constructor
        this.gui = new PlayAI(this);

        // Setup the Root Layout (The whole screen)
        setSizeFull();
        setSpacing(false);
        setPadding(false);

        // Setup the Side Panel
        sidePanel.setWidth("300px");
        sidePanel.setHeightFull();
        sidePanel.getStyle().set("background-color", "#211136"); // Tweaked slightly to match your image
        sidePanel.setJustifyContentMode(JustifyContentMode.CENTER);
        sidePanel.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        // Add some spacing between the buttons
        sidePanel.setSpacing(true);

        // Style the buttons using our helper method below
        styleYellowButton(mathEnemyButton);
        styleYellowButton(menuButton);
        sidePanel.add(mathEnemyButton, menuButton);

        // Setup the Main Panel
        mainPanel.setSizeFull();
        mainPanel.getStyle().set("background-color", "#2D1B4E"); // Matched to your image
        mainPanel.setJustifyContentMode(JustifyContentMode.CENTER);
        mainPanel.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        // Style the reset button and give it some margin so it doesn't touch the grid
        styleYellowButton(resetButton);
        resetButton.getStyle().set("margin-top", "30px");

        // Add UI and Reset Button to Main Panel
        mainPanel.add(gui, resetButton);

        // Add Panels to the main HorizontalLayout
        add(sidePanel, mainPanel);

        menuButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate(""));
        });

        mathEnemyButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate("background"));
        });

        // Action Listeners
        resetButton.addClickListener(e -> reset());
    }

    public void reset() {
        mainPanel.remove(gui);
        this.gui = new PlayAI(this);
        mainPanel.addComponentAsFirst(gui);
    }

    // --- NEW HELPER METHOD ---
    // This applies your custom CSS to any button passed into it
    private void styleYellowButton(Button button) {
        button.setWidth("200px");
        button.setHeight("60px");
        button.getStyle().set("background-color", "#F0B132"); // The golden yellow
        button.getStyle().set("color", "black");              // Black text
        button.getStyle().set("font-weight", "bold");
        button.getStyle().set("font-size", "16px");
        button.getStyle().set("border-radius", "8px");        // Rounded corners
    }
}