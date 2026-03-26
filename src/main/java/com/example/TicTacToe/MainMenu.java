package com.example.TicTacToe;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.FlexComponent;

@Route("") // This makes MainMenu the first thing people see at http://localhost:8080/
public class MainMenu extends VerticalLayout {

    private VerticalLayout mainPanel = new VerticalLayout();
    private VerticalLayout midPanel = new VerticalLayout();

    private Button aiButton = new Button("AI Enemy");
    private Button algButton = new Button("Math Enemy");

    public MainMenu() {
        // 1. Setup the Main Background (Full Screen)
        setSizeFull();
        setPadding(false);
        setSpacing(false);
        getStyle().set("background-color", "#2D1946"); // Matches Color(45, 25, 70)
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        // 2. Setup the Middle Card (The dark box in the center)
        midPanel.setWidth("500px");
        midPanel.setHeight("600px");
        midPanel.getStyle().set("background-color", "#1E0A32"); // Matches Color(30, 10, 50)
        midPanel.getStyle().set("border-radius", "15px");
        midPanel.setJustifyContentMode(JustifyContentMode.CENTER);
        midPanel.setAlignItems(Alignment.CENTER);
        midPanel.setSpacing(true);

        // 3. Style and Add Buttons
        styleYellowButton(aiButton);
        styleYellowButton(algButton);

        midPanel.add(aiButton, algButton);
        add(midPanel);

        // 4. Navigation Logic (Replaces frame.dispose())
        aiButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate("background2"));
        });

        algButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate("background"));
        });
    }

    private void styleYellowButton(Button button) {
        button.setWidth("250px");
        button.setHeight("70px");
        button.getStyle().set("background-color", "#F0B132"); // Golden yellow
        button.getStyle().set("color", "black");
        button.getStyle().set("font-weight", "bold");
        button.getStyle().set("font-size", "20px");
        button.getStyle().set("border-radius", "10px");
        button.getStyle().set("cursor", "pointer");
    }
}