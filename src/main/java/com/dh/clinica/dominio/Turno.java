package com.dh.clinica.dominio;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table
public class Turno {
    @Id
    @SequenceGenerator(name = "turno_sequence", sequenceName = "turno_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turno_sequence")
    private Long id;

    private Date fecha;
    private LocalTime hora;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_paciente", referencedColumnName = "id")
    private Paciente paciente;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_odontologo", referencedColumnName = "id")
    private Odontologo odontologo;

    public Turno() {
    }

    public Turno(Date fecha, LocalTime hora, Paciente paciente, Odontologo odontologo) {
        this.fecha = fecha;
        this.hora = hora;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date date) {
        this.fecha = date;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                '}';
    }
}