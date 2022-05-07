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

public class UsuarioAPI {

    public UsuarioAPI(){
    }

    private static final String api = "https://proyectos2bbddapi.herokuapp.com/%s";

    HttpRequest request;
    HttpClient client = HttpClient.newBuilder().build();
    HttpResponse<String> response;

    public Usuario loginUsuario(String nombre_usurario, String contrasenia){


        String resource = String.format(api,"loginUsuario/");

        resource=resource+nombre_usurario+"/"+contrasenia;

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

        if(response.body().equals(null)){
            return null;
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Usuario usuario = gson.fromJson(response.body(), Usuario.class);

        return usuario;
    }

    public String aniadirUsuario(Usuario nuevoUsuario){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String string = gson.toJson(nuevoUsuario,Usuario.class);

        String resource = String.format(api,"nuevoUsuario");
        System.out.println(resource);

        System.out.print("EL gson del usuario: \n"+string);

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

    public String modificarUsuario(Usuario usuarioModificado){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String string = gson.toJson(usuarioModificado,Usuario.class);

        String resource = String.format(api,"modificarUsuario");
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

    public String eliminarUsuario(int usuario_id){

        String usuario_id_string = Integer.toString(usuario_id);

        String resource = String.format(api,"eliminarUsuario/");

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
