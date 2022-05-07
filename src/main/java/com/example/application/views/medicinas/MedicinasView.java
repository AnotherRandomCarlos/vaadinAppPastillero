package com.example.application.views.medicinas;

import com.example.application.data.entity.Medicina;
import com.example.application.views.MainLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.security.PermitAll;

@PageTitle("Medicinas")
@Route(value = "medic", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class MedicinasView extends Div {

    private GridPro<Medicamento> grid;
    private GridListDataView<Medicamento> gridListDataView;

    private Grid.Column<Medicamento> nombreMedColumn;
    private Grid.Column<Medicamento> descripcionMedColumn;
    private Grid.Column<Medicamento> daysColumn;
    private Grid.Column<Medicamento> horasColumn;
    private Grid.Column<Medicamento> cantidaTomaColumn;
    private Grid.Column<Medicamento> cantidadCajaColumn;

    private Button eliminar = new Button("Eliminar seleecionado");


    public MedicinasView() {
        eliminar.setEnabled(false);
        eliminar.addThemeVariants(ButtonVariant.LUMO_ERROR);

        addClassName("medicinas-view");
        setSizeFull();
        createGrid();
        add(grid, eliminar);
        grid.addSelectionListener(selection -> {
            int size = selection.getAllSelectedItems().size();
            boolean isSingleSelection = size == 1;
            eliminar.setEnabled(size != 0);
            eliminar.addClickListener(e->{
                //insertar codigo que llama a la api y elimina lo que haya
                Set<Medicamento> medicinaseliminar = selection.getAllSelectedItems();
                MedicinasAPI apiMedicina = new MedicinasAPI();

                for(Medicamento i : medicinaseliminar){
                    apiMedicina.eliminarMedicamento(i.getId_medicamento());
                }

                UI.getCurrent().getPage().reload();
            });
        });
    }

    private void createGrid() {
        createGridComponent();
        addColumnsToGrid();
        //addFiltersToGrid();
    }

    private void createGridComponent() {
        grid = new GridPro<>();
        grid.setSelectionMode(SelectionMode.MULTI);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        grid.setHeight("95%");

        List<Medicamento> meds = getClients();
        gridListDataView = grid.setItems(meds);
    }

    private void addColumnsToGrid() {
        createnombreMedColumn();
        createdecripcionMedColumn();
        createdaysColumn();
        createHorasColumn();
        createcantidadTomaColumn();
        createcantidadCajaColumn();
    }

    private void createnombreMedColumn() {
        nombreMedColumn = grid.addColumn(new ComponentRenderer<>(medicamento -> {
            HorizontalLayout hl = new HorizontalLayout();
            hl.setAlignItems(Alignment.CENTER);
            //Image img = new Image(client.getImg(), "");
            Span span = new Span();
            span.setClassName("name");
            span.setText(medicamento.getNombre());
            hl.add(span);
            return hl;
        })).setComparator(medicamento -> medicamento.getNombre()).setHeader("Nombre");
    }

    private void createdecripcionMedColumn() {
        descripcionMedColumn = grid.addColumn(new ComponentRenderer<>(medicamento -> {
            HorizontalLayout hl = new HorizontalLayout();
            hl.setAlignItems(Alignment.CENTER);
            Span span = new Span();
            span.setClassName("name");
            span.setText(medicamento.getDescripcion());
            hl.add(span);
            return hl;
        })).setComparator(medicamento -> medicamento.getDescripcion()).setHeader("Descripción");
    }

    private void createdaysColumn() {
        daysColumn = grid.addColumn(new ComponentRenderer<>(medicamento -> {
            HorizontalLayout hl = new HorizontalLayout();
            hl.setAlignItems(Alignment.CENTER);
            //Image img = new Image(client.getImg(), "");
            Span span = new Span();
            span.setClassName("name");
            span.setText(medicamento.getDias_semana());
            hl.add(span);
            return hl;
        })).setComparator(medicamento -> medicamento.getDias_semana()).setHeader("Dias");
    }

    private void createHorasColumn() {
        horasColumn = cantidaTomaColumn = grid.addColumn(Medicamento::getHoras_entre_toma).setHeader("Intervalo de horas");
    }

    private void createcantidadTomaColumn() {
        cantidaTomaColumn = grid.addColumn(Medicamento::getPastillas_toma).setHeader("Cantidad por toma");
    }

    private void createcantidadCajaColumn() {
        cantidadCajaColumn = grid.addColumn(Medicamento::getPastillas_caja).setHeader("Cantidad por caja");
    }



    private List<Medicamento> getClients() {

        /*Pruebas de otras api */

        /*      user          */

        //UsuarioAPI apiUsuario = new UsuarioAPI();

        //int respuestaAPI=apiUsuario.loginUsuario("Carlos","123456");

        //System.out.println("\nLa respuesta de la api al login es: "+respuestaAPI);

        Usuario nuevoUsuario = new Usuario("pepa", "lopez miras", "pepa1", "pepa1234");
        //apiUsuario.aniadirUsuario(nuevoUsuario);

        nuevoUsuario.setId_usuario(6);

        nuevoUsuario.setNombre_usuario("Pepa90");


        //apiUsuario.modificarUsuario(nuevoUsuario);

        //apiUsuario.eliminarUsuario(8);

        /*      correo         */

        //CorreoAPI apiCorreo = new CorreoAPI();

        //Correo correo=apiCorreo.obtenerCorreoUsuario(1);

        System.out.println("\nEl correo del usuario con id 1 es:");
        //System.out.println(correo.toString());
        System.out.println("\nfin:");

        Correo nuevoCorreo = new Correo(10, "correo@gmail");

        //apiCorreo.aniadirCorreo(nuevoCorreo);

        //apiCorreo.eliminarCorreo(10);

        //apiCorreo.modificarCorreo(nuevoCorreo);

        /*Medicina*/

        MedicinasAPI apiMedicina = new MedicinasAPI();
        List<Medicamento> medicinas_usuario = apiMedicina.recibirMedicinasUsuario(1);

        return medicinas_usuario;

    }

};
