package com.sk.GatePass.GUI;


import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.Route;

@Route("addcar")
public class AddCar extends VerticalLayout {

    
        TextField name= new TextField("Name");
        TextField surname= new TextField("Surname");
        TextField brand= new TextField("brand");
        TextField model= new TextField("Model");
        TextField plate= new TextField("Plate");
        TextField phone= new TextField("Phone");
        EmailField mail= new EmailField("Mail");
        TextField cardID= new TextField("Card ID");
        TextField company= new TextField("Company");

        Button submit= new Button("Submit");

    public AddCar(){
        add(new Text("Add Your Car"));
       add(name, surname, brand, model, plate, phone,
         mail, cardID, company);
       add(submit);
        add(new Text("Description"));

    }
}
