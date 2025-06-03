package models;

public class Editorial {
    private int id;
    private String nombre;
    private String pais;
    private int añoFundacion;

    public Editorial() {}

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

    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public int getAñoFundacion() {
        return añoFundacion;
    }
    public void setAñoFundacion(int añoFundacion) {
        this.añoFundacion = añoFundacion;
    }

}
