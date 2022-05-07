package com.example.application.views.añadirmedicina;

import com.example.application.data.entity.Medicina;
import com.example.application.data.service.MedicinaService;
import com.example.application.views.MainLayout;
import com.example.application.views.medicinas.Medicamento;
import com.example.application.views.medicinas.MedicinasAPI;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PageTitle("Añadir Medicina")
@Route(value = "addMedi", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class AñadirMedicinaView extends Div {

    private TextField nombreMed = new TextField("Nombre");
    private TextField descripcionMed = new TextField("Descripción");
    private IntegerField cantidadToma= new IntegerField("Cantidad por toma");
    private IntegerField cantidadCaja= new IntegerField("Cantidad restante");
    private CheckboxVertical days = new CheckboxVertical("days");
    private IntegerField horas = new IntegerField("Intervalo entre tomas");


    private Button cancel = new Button("Cancelar");
    private Button save = new Button("Guardar");

    private Binder<Medicina> binder = new Binder<>(Medicina.class);

    public AñadirMedicinaView(MedicinaService personService) {
        addClassName("añadir-medicina-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        cantidadCaja.setHasControls(true);
        cantidadToma.setHasControls(true);
        horas.setHasControls(true);
        horas.setMin(0);
        horas.setMax(24);

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            personService.update(binder.getBean());
            Notification.show(binder.getBean().getClass().getSimpleName() + " details stored.");

            Medicamento medicina = new Medicamento(1, nombreMed.getValue(), descripcionMed.getValue(), "M", horas.getValue(), cantidadToma.getValue(), cantidadCaja.getValue());

            MedicinasAPI apimedicina = new MedicinasAPI();

            apimedicina.aniadirMedicina(medicina);

            clearForm();
        });
    }

    private void clearForm() {
        binder.setBean(new Medicina());
    }

    private Component createTitle() {
        return new H3("Introduce los datos de la medicina");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(nombreMed, descripcionMed, cantidadToma, cantidadCaja, horas, days);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }

    @Route("checkbox-vertical")
    public class CheckboxVertical extends Div {

        public CheckboxVertical(String days) {
            CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
            checkboxGroup.setLabel("Dias de toma");
            checkboxGroup.setItems("L", "M", "X", "J", "V", "S", "D");
            checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
            add(checkboxGroup);
        }

    }

}
