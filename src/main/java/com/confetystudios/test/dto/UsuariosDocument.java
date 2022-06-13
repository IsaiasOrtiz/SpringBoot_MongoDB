package com.confetystudios.test.dto;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Usuarios")
public class UsuariosDocument {

	@Override
	public String toString() {
		return "UsuariosDocument [id=" + id + ", nombre=" + nombre + ", usuario=" + usuario + ", clave=" + clave
				+ ", direccion=" + direccion + ", telefonos=" + telefonos.toString() + "]";
	}

	@Id
	private int id;

	private String nombre;
	private String usuario;
	private String clave;
	private String direccion;
	private List<String> telefonos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<String> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(List<String> telefonos) {
		this.telefonos = telefonos;
	}

}
