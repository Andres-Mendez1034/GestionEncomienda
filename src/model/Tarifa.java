package model;

public class Tarifa {

    private float costoBase;

    public Tarifa() {
        this.costoBase = 5000f;
    }

    public Tarifa(float costoBase) {
        this.costoBase = costoBase;
    }

    public float calcularCosto(Encomienda encomienda) {
        return costoBase * encomienda.getPeso();  
    }
}
