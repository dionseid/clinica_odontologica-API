package com.dh.clinica.service.impl;

import com.dh.clinica.entity.dto.OdontologoDto;
import com.dh.clinica.service.impl.OdontologoService;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner/*JUnit4*//*SpringRunner*/.class)
@SpringBootTest
public class OdontologoServiceTests {
    //private static OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
    @Autowired
    private OdontologoService odontologoService;
    private OdontologoDto odontologo;

    @BeforeEach
    public void setUp() {
        odontologo = new OdontologoDto();
        odontologo.setNombre("Enzo");
        odontologo.setApellido("Perez");
        odontologo.setMatricula(24242424);
    }

    //@BeforeClass
    public /*static*/ void cargarDataSet() {
        //this.odontologoService.guardar(new Odontologo("Santiago", "Paz", 3455647));
        odontologoService.guardar(new OdontologoDto("Perez", "Enzo", 24242424));
        odontologoService.guardar(new OdontologoDto("Alvarez", "Julian", 9999));
        odontologoService.guardar(new OdontologoDto("Armani", "Franco", 1111));
    }

    @Test
    public void guardar() {
        this.cargarDataSet();
        OdontologoDto/*Odontologo*/ o = odontologoService.guardar(new OdontologoDto/*Odontologo*/("Ramirez", "Juan", 348971960));
        Assert.assertTrue(o.getId() != null);
    }

    @Test
    public void listarPorNombre() {
        odontologoService.guardar(new OdontologoDto("Armani", "Franco", 1111));
        odontologoService.guardar(new OdontologoDto/*Odontologo*/("Ramirez", "Juan", 348971960));
        assertNotNull(odontologoService.listarPorNombre("Armani"));
        assertNotNull(odontologoService.listarPorNombre("Ramirez"));
    }

    @Test
    public void eliminar() throws Exception {
        /*this.cargarDataSet();
        odontologoService.eliminar(1L);
        Assert.assertTrue(odontologoService.buscar(1L) == null/*.isEmpty()*///);

        boolean thrown = false;
        OdontologoDto o = odontologoService.guardar(new OdontologoDto("Quinteros", "Juanfer", 10101010));
        odontologoService.eliminar(o.getId());
        try {
            odontologoService.buscar(o.getId());
        } catch (NoSuchElementException e) {
            thrown = true;
        }
        assertFalse(thrown);

        /*OdontologoDto o = odontologoService.guardar(odontologo);
        assertNotNull(odontologoService.buscar(o.getId()));
        odontologoService.eliminar(o.getId());
        assertThrows(ConfigDataResourceNotFoundException.class, () -> odontologoService.buscar(o.getId()));*/
    }

    @Test
    public void traerTodos() {
        cargarDataSet();
        Set/*List*/<OdontologoDto/*Odontologo*/> odontologos = odontologoService.listar();
        Assert.assertTrue(!odontologos.isEmpty());
        Assert.assertTrue(odontologos.size() > 0/* == 1*/);
        System.out.println(odontologos);
    }
}