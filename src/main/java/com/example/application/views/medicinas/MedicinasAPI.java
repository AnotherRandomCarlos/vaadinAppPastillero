package com.example.application.views.medicinas;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class MedicinasAPI {

    public MedicinasAPI() {
    }

    private static final String api = "https://proyectos2bbddapi.herokuapp.com/%s";

    HttpRequest request;
    HttpClient client = HttpClient.newBuilder().build();
    HttpResponse<String> response;

    public List<Medicamento> recibirMedicinasUsuario(int user_id){

        String user_id_string = Integer.toString(user_id);

        String resource = String.format(api,"encontrarMedicamentos/");

        resource=resource+user_id_string;

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
        String medicinas_usuario = response.body();
        ArrayList<Medicamento> lista;
        Type userListType = new TypeToken<ArrayList<Medicamento>>(){}.getType();
        lista = gson.fromJson(medicinas_usuario, userListType);


        return lista;
    }

    public String aniadirMedicina(Medicamento nuevoMedicamento){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String string = gson.toJson(nuevoMedicamento,Medicamento.class);

        String resource = String.format(api,"nuevoMedicamento");
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

    public String modificarMedicamento(Medicamento medicamentoModificado){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String string = gson.toJson(medicamentoModificado,Medicamento.class);

        String resource = String.format(api,"modificarMedicamento");
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

    public String eliminarMedicamento(int medicamento_id){

        String medicamento_id_string = Integer.toString(medicamento_id);

        String resource = String.format(api,"eliminarMedicamento/");

        resource=resource+medicamento_id_string;


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
