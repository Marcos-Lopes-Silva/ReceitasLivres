package com.testreact.reactjs.controller;

import org.springframework.web.bind.annotation.RestController;

import com.testreact.reactjs.repository.ClienteRepository;

@RestController
public class ControllerCliente {

    private ClienteRepository clienteRepository;

    public void registrarCliente(@Valid ){

        clienteRepository.save(cliente);

        
    }
}
