package com.sk.GatePass.view;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;

/**
 * A Designer generated component for the main-page template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("main-page")
@JsModule("./main-page.ts")
public class MainPage extends LitTemplate {

    @Id("h1")
    private H1 h1;

    /**
     * Creates a new MainPage.
     */
    public MainPage() {
        // You can initialise any data required for the connected UI components here.
    }

}
