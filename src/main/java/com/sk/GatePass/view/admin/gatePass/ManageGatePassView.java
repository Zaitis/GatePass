package com.sk.GatePass.view.admin.gatePass;



import com.sk.GatePass.model.GatePass;

import com.sk.GatePass.service.GatePassService;
import com.sk.GatePass.view.admin.AdminLayout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;

import java.time.LocalDate;
import java.time.LocalDateTime;

@PageTitle("Manage Gate Pass")
@AnonymousAllowed
@Route(value = "manage-gatepass", layout = AdminLayout.class)
public class ManageGatePassView extends VerticalLayout {

    Grid<GatePass> grid = new Grid<>(GatePass.class);
 //   TextField filterText= new TextField();
    GatePassForm form;
    private GatePassService gatePassService;
    private final Converter<LocalDateTime, String> dateTimeConverter = new LocalDateTimeToStringConverter();


    public ManageGatePassView(GatePassService gatePassService){
        this.gatePassService = gatePassService;


        addClassName("gate-pass-view");
    setSizeFull();

    configureGrid();
    configureForm();

    add(
            getContent()
        );

      updateCompanies();
      closeEditor();
    }

    private void closeEditor() {
        form.setCompany(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateCompanies() {
        grid.setItems(gatePassService.filterGatePass(""));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2,grid);
        content.setFlexGrow(1, form);
        content.addClassName("gate-pass");
        content.setSizeFull();

        return content;
    }

    private void configureForm() {
        form = new GatePassForm();
        form.setWidth("25em");

        form.addSaveListener(this::saveCompany);
        form.addDeleteListener(this::deleteCompany);
        form.addCloseListener(e -> closeEditor());

    }

    private void deleteCompany(GatePassForm.DeleteEvent event) {
        gatePassService.deleteGatePass(event.getGatePass().getId());
        updateCompanies();
        closeEditor();
    }

    private void saveCompany(GatePassForm.SaveEvent event){
        gatePassService.addGatePass(event.getGatePass());
        updateCompanies();
        closeEditor();
    }



    private void addCompany() {
        grid.asSingleSelect().clear();
        editContact(new GatePass());
    }


    private void configureGrid() {
        grid.addClassName("gate-pass-grid");

        grid.setSizeFull();
        grid.setColumns("id");
        grid.addColumn(gatePass -> gatePass.getCars().getBrand()).setHeader("Brand");
        grid.addColumn(gatePass -> gatePass.getCars().getModel()).setHeader("Model");
        grid.addColumn(gatePass -> gatePass.getCars().getPlate()).setHeader("Plate");
        grid.addColumn(gatePass -> dateTimeConverter.convertToModel(gatePass.getCreatedDate(), null))
                .setHeader("Created Date");
        grid.addColumn(gatePass -> {
            LocalDateTime acceptedDate = gatePass.getAcceptedDate();
            return acceptedDate != null ? dateTimeConverter.convertToModel(acceptedDate, null) : "-";
        }).setHeader("Accepted Date");

        grid.asSingleSelect().addValueChangeListener(e -> editContact(e.getValue()));
    }

    private void editContact(GatePass gatepass) {
        if(gatepass == null){
            closeEditor();
        } else {
            form.setCompany(gatepass);
            form.setVisible(true);
            addClassName("editing");
        }
    }
}