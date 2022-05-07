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

public class CorreoAPI {

    public CorreoAPI(){
    }


    private static final String api = "https://proyectos2bbddapi.herokuapp.com/%s";

    HttpRequest request;
    HttpClient client = HttpClient.newBuilder().build();
    HttpResponse<String> response;

    public Correo obtenerCorreoUsuario(int id_usuario){

        String id_usuario_string=Integer.toString(id_usuario);

        String resource = String.format(api,"encontrarCorreos/");

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
        Correo correo = gson.fromJson(response.body(), Correo.class);

        return correo;
    }

    public String aniadirCorreo(Correo nuevoCorreo){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String string = gson.toJson(nuevoCorreo,Correo.class);

        String resource = String.format(api,"nuevoCorreo");
        System.out.println(resource);

        System.out.print("EL gson del correo: \n"+string);

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

    public String modificarCorreo(Correo correoModificado){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String string = gson.toJson(correoModificado,Correo.class);

        String resource = String.format(api,"modificarCorreo");
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

    public String eliminarCorreo(int usuario_id){

        String usuario_id_string = Integer.toString(usuario_id);

        String resource = String.format(api,"eliminarCorreo/");

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
