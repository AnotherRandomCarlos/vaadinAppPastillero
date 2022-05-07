package com.example.application.views.datospersonales;

import com.example.application.data.entity.Usuarios;
import com.example.application.data.service.UsuarioService;
import com.example.application.views.MainLayout;
import com.example.application.views.medicinas.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import javax.annotation.security.PermitAll;

@PageTitle("Datos personales")
@Route(value = "users", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class DatospersonalesView extends Div {

    private TextField nombreUsu = new TextField("Nombre");
    private TextField apellidoUsu = new TextField("Apellido");
    private EmailField correoUsu = new EmailField("Correo electrónico usuario");
    private EmailField correoSup = new EmailField("Correo electrónico Supervisor");
    private TextField userUsu = new TextField("Usuario");
    private PasswordField passUsu = new PasswordField("Contraseña");

    private Checkbox mayusculas = new Checkbox("Correo en mayúsculas");
    private Checkbox resumen = new Checkbox("Resumen diario");
    private Checkbox avisos = new Checkbox("Aviso por horas");

    private Button cancel = new Button("Cancelar");
    private Button save = new Button("Guardar");

    private Binder<Usuarios> binder = new Binder<>(Usuarios.class);

    public DatospersonalesView(UsuarioService personService) {
        addClassName("datospersonales-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());


        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            Notificacion not = new Notificacion(1, mayusculas.getValue(), resumen.getValue(), avisos.getValue());

            NotificacionAPI apinot = new NotificacionAPI();

            apinot.aniadirNotificacion(not);

            UsuarioAPI apiuser = new UsuarioAPI();
            Usuario usermostrar = apiuser.loginUsuario("Carlos", "123456");

            usermostrar.setNombre(nombreUsu.getValue());
            usermostrar.setApellidos(apellidoUsu.getValue());

            apiuser.modificarUsuario(usermostrar);

            CorreoAPI apimail = new CorreoAPI();
            Correo mailmostrar = apimail.obtenerCorreoUsuario(1);
            mailmostrar.setCorreo1(correoUsu.getValue());
            mailmostrar.setCorreo_supervisor(correoSup.getValue());

            apimail.modificarCorreo(mailmostrar);


            binder.bindInstanceFields(this);
            personService.update(binder.getBean());
            Notification.show(binder.getBean().getClass().getSimpleName() + " details stored.");
            clearForm();
        });
    }

    private void clearForm() {
        binder.setBean(new Usuarios());
    }

    private Component createTitle() {
        return new H3("Introduce los datos del usuario");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        correoUsu.setErrorMessage("Please enter a valid email address");

        UsuarioAPI apiuser = new UsuarioAPI();
        Usuario usermostrar = apiuser.loginUsuario("Carlos", "123456");

        CorreoAPI apimail = new CorreoAPI();
        Correo mailmostrar = apimail.obtenerCorreoUsuario(1);

        NotificacionAPI apinot = new NotificacionAPI();
        Notificacion notver = apinot.obtenerNotificacionUsuario(1);

        nombreUsu.setValue(usermostrar.getNombre());
        apellidoUsu.setValue(usermostrar.getApellidos());
        userUsu.setValue(usermostrar.getNombre_usuario());
        userUsu.setReadOnly(true);
        passUsu.setValue(usermostrar.getContrasenia());
        passUsu.setReadOnly(true);

        correoUsu.setValue(mailmostrar.getCorreo1());
        correoSup.setValue(mailmostrar.getCorreo_supervisor());


        mayusculas.setValue(notver.isMayusculas());
        resumen.setValue(notver.isResumen_diario());
        avisos.setValue(notver.isAvisos_horarios());

        formLayout.add(nombreUsu, apellidoUsu, userUsu, passUsu, correoUsu, correoSup, mayusculas, resumen, avisos);
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


}
