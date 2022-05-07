package com.example.application.views.medicinas;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.aspectj.weaver.ast.Not;


public class NotificacionAPI {

    public NotificacionAPI(){
    }

    private static final String api = "https://proyectos2bbddapi.herokuapp.com/%s";

    HttpRequest request;
    HttpClient client = HttpClient.newBuilder().build();
    HttpResponse<String> response;

    public Notificacion obtenerNotificacionUsuario(int id_usuario){

        String id_usuario_string=Integer.toString(id_usuario);

        String resource = String.format(api,"encontrarNotificacion/");

        resource=resource+id_usuario_string;

        System.out.println(resource);
        try {
            request = HttpRequest
                    .newBuilder(new URI(resource))
                    .header("Content-Type","application/java")
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(response.body());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Notificacion notificacion = gson.fromJson(response.body(), Notificacion.class);

        return notificacion;
    }

    public String aniadirNotificacion(Notificacion nuevaNotificacion){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String string = gson.toJson(nuevaNotificacion,Notificacion.class);

        String resource = String.format(api,"nuevaNotificacion");
        System.out.println(resource);

        System.out.print("EL gson de la notificacion: \n"+string);

        try {
            request = HttpRequest
                    .newBuilder(new URI(resource))
                    .header("Content-Type","application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(string))
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(response.body());


        return response.body();
    }

    public String modificarNotificacion(Notificacion notificacionModificada){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String string = gson.toJson(notificacionModificada,Notificacion.class);

        String resource = String.format(api,"modificarNotificacion");
        System.out.println(resource);


        try {
            request = HttpRequest
                    .newBuilder(new URI(resource))
                    .header("Content-Type","application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(string))
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(response.body());


        return response.body();
    }

    public String eliminarNotificacion(int usuario_id){

        String usuario_id_string = Integer.toString(usuario_id);

        String resource = String.format(api,"eliminarNotificacion/");

        resource=resource+usuario_id_string;


        try {
            request = HttpRequest
                    .newBuilder(new URI(resource))
                    .DELETE()
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(response.body());


        return response.body();
    }

}
