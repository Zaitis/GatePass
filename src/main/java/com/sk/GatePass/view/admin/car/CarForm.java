package com.sk.GatePass.view.admin.car;


import com.sk.GatePass.model.Car;
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
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;


public class CarForm extends FormLayout {

    Binder<Car> binder = new BeanValidationBinder<>(Car.class);
    TextField brand = new TextField("Car Brand");
    TextField model = new TextField("Car Model");
    TextField plate = new TextField("Car Plate");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");
    private Car car;

    public CarForm() {

        binder.bindInstanceFields(this);
        add(
                brand,
                model,
                plate,

                createButtonLayout()
        );


    }

    public void setCompany(Car car){
        this.car = car;
    binder.readBean(car);
    }


    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event ->validateAndSave());
        delete.addClickListener(event ->fireEvent(new DeleteEvent(this, car)));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));
        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, cancel);
    }

    private void validateAndSave() {
        try {

            binder.writeBean(car);
            fireEvent(new SaveEvent(this, car));
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }

    // Events
    public static abstract class CarFormEvent extends ComponentEvent<CarForm> {
        private Car car;

        protected CarFormEvent(CarForm source, Car car) {
            super(source, false);
            this.car = car;
        }

        public Car getCar() {
            return car;
        }
    }

    public static class SaveEvent extends CarFormEvent {
        SaveEvent(CarForm source, Car car) {
            super(source, car);
        }
    }

    public static class DeleteEvent extends CarFormEvent {
        DeleteEvent(CarForm source, Car car) {
            super(source, car);
        }

    }

    public static class CloseEvent extends CarFormEvent {
        CloseEvent(CarForm source) {
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
