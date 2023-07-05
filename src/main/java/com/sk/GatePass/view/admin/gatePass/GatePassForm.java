package com.sk.GatePass.view.admin.gatePass;


import com.sk.GatePass.model.GatePass;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.shared.Registration;

import java.time.LocalDateTime;


public class GatePassForm extends FormLayout {

    Binder<GatePass> binder = new BeanValidationBinder<>(GatePass.class);
    TextField carBrand = new TextField("Brand");
    TextField carModel = new TextField("Model");
    TextField carPlate = new TextField("Plate Number");
    TextField createdDate = new TextField("Request Date");
    TextField acceptedDate = new TextField("Accepted Date");
    TextField isAccepted = new TextField("Accepted");


    Button accept = new Button("Accept");
    Button reject = new Button("Reject");
    Button cancel = new Button("Cancel");
    private GatePass gatePass;

    Converter<LocalDateTime, String> converter = new LocalDateTimeToStringConverter();


    public GatePassForm() {

        add(
                createButtonLayout()
        );


    }

    public void setCompany(GatePass gatePass){
        this.gatePass = gatePass;
    binder.readBean(gatePass);
    }


    private Component createButtonLayout() {
        accept.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        reject.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

//        accept.addClickListener(event ->validateAndSave());
     //   reject.addClickListener(event ->fireEvent(new DeleteEvent(this, company)));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));
        accept.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(accept, reject, cancel);
    }

    private void validateAndSave() {
//        try {
//
//            binder.writeBean(company);
//            fireEvent(new SaveEvent(this, company));
//        } catch (ValidationException e) {
//            throw new RuntimeException(e);
//        }
    }

    // Events
    public static abstract class GatePassFormEvent extends ComponentEvent<GatePassForm> {
        private GatePass gatePass;

        protected GatePassFormEvent(GatePassForm source, GatePass gatePass) {
            super(source, false);
            this.gatePass = gatePass;
        }

        public GatePass getGatePass() {
            return gatePass;
        }
    }

    public static class SaveEvent extends GatePassFormEvent {
        SaveEvent(GatePassForm source, GatePass gatePass) {
            super(source, gatePass);
        }
    }

    public static class DeleteEvent extends GatePassFormEvent {
        DeleteEvent(GatePassForm source, GatePass gatePass) {
            super(source, gatePass);
        }

    }

    public static class CloseEvent extends GatePassFormEvent {
        CloseEvent(GatePassForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }
    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}
