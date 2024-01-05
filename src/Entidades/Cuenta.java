package Entidades;

public class Cuenta {
    private int CBU;
    private String alias;
    private double credito;
    private double debito;
    private int tipo;
    private int DNI_PROP;

    public int getCBU() {
        return CBU;
    }

    public void setCBU(int CBU) {
        this.CBU = CBU;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public double getDebito() {
        return debito;
    }

    public void setDebito(double debito) {
        this.debito = debito;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getDNI_PROP() {
        return DNI_PROP;
    }

    public void setDNI_PROP(int DNI_PROP) {
        this.DNI_PROP = DNI_PROP;
    }
}
