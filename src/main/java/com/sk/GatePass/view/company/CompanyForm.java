package com.sk.GatePass.view.company;


import com.sk.GatePass.model.Company;
import com.sk.GatePass.service.CompanyService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;


public class CompanyForm extends FormLayout {

    Binder<Company> binder = new BeanValidationBinder<>(Company.class);
    TextField companyName = new TextField("Company Name");
    TextField phone = new TextField("Phone numer");
    EmailField mail = new EmailField("Email");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");
    private Company company;

    public CompanyForm() {

        binder.bindInstanceFields(this);
        add(
                companyName,
                phone,
                mail,

                createButtonLayout()
        );


    }

    public void setCompany(Company company){
        this.company = company;
    binder.readBean(company);
    }


    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event ->validateAndSave());
        delete.addClickListener(event ->fireEvent(new DeleteEvent(this, company)));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));
        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, cancel);
    }

    private void validateAndSave() {
        try {
            System.out.println("tutaj jestesmy");
            System.out.println(company);
            binder.writeBean(company);
            fireEvent(new SaveEvent(this, company));
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }

    // Events
    public static abstract class CompanyFormEvent extends ComponentEvent<CompanyForm> {
        private Company company;

        protected CompanyFormEvent(CompanyForm source, Company company) {
            super(source, false);
            this.company = company;
        }

        public Company getCompany() {
            return company;
        }
    }

    public static class SaveEvent extends CompanyFormEvent {
        SaveEvent(CompanyForm source, Company company) {
            super(source, company);
        }
    }

    public static class DeleteEvent extends CompanyFormEvent {
        DeleteEvent(CompanyForm source, Company company) {
            super(source, company);
        }

    }

    public static class CloseEvent extends CompanyFormEvent {
        CloseEvent(CompanyForm source) {
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
