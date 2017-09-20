package net.simplifiedcoding.androidmysqlsync;

/**
 * Created by cliente on 7/22/2017.
 */

public class Cliente {


    private int id;
    private String nombre;
    private String descuento;
    private int status;
    private String ruta;
    private String Razon;
    //private String L,K,M,J,V,S;
    private String credito,sub_canal,ldescuento,lprecioA,lprecioC;
    private String LAT,LONG,G;
    private String FormaPago,puedoFacturar;
    private String tipoFactura;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getRazon() {
        return Razon;
    }

    public void setRazon(String razon) {
        Razon = razon;
    }

    public String getCredito() {
        return credito;
    }

    public void setCredito(String credito) {
        this.credito = credito;
    }

    public String getSub_canal() {
        return sub_canal;
    }

    public void setSub_canal(String sub_canal) {
        this.sub_canal = sub_canal;
    }

    public String getLdescuento() {
        return ldescuento;
    }

    public void setLdescuento(String ldescuento) {
        this.ldescuento = ldescuento;
    }

    public String getLprecioA() {
        return lprecioA;
    }

    public void setLprecioA(String lprecioA) {
        this.lprecioA = lprecioA;
    }

    public String getLprecioC() {
        return lprecioC;
    }

    public void setLprecioC(String lprecioC) {
        this.lprecioC = lprecioC;
    }

    public String getLAT() {
        return LAT;
    }

    public void setLAT(String LAT) {
        this.LAT = LAT;
    }

    public String getLONG() {
        return LONG;
    }

    public void setLONG(String LONG) {
        this.LONG = LONG;
    }

    public String getG() {
        return G;
    }

    public void setG(String g) {
        G = g;
    }

    public String getFormaPago() {
        return FormaPago;
    }

    public void setFormaPago(String formaPago) {
        FormaPago = formaPago;
    }

    public String getPuedoFacturar() {
        return puedoFacturar;
    }

    public void setPuedoFacturar(String puedoFacturar) {
        this.puedoFacturar = puedoFacturar;
    }

    public String getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
    }





public Cliente(){

}

    public Cliente(String nombre, String descuento, String ruta, String Razon,String credito,String sub_canal,String ldescuento,
                   String lprecioa,String lprecioc,String LAT, String LONG, String G, String FormaPago, String puedoFacturar,String tipoFactura,
                   int status) {
        this.nombre = nombre;
        this.descuento = descuento;
        this.status = status;
        this.ruta = ruta;
        this.Razon = Razon;
        this.credito = credito;
        this.sub_canal = sub_canal;
        this.ldescuento = ldescuento;
        this.lprecioA = lprecioa;
        this.lprecioC = lprecioc;
        this.LAT = LAT;
        this.LONG = LONG;
        this.G = G;
        this.FormaPago = FormaPago;
        this.puedoFacturar = puedoFacturar;
        this.tipoFactura = tipoFactura;
    }
    public Cliente(String nombre,String descuento,int status) {
        this.nombre = nombre;
        this.descuento = descuento;
        this.status = status;
    }
    public Cliente(int id,String nombre,String razon, String descuento,int status) {
        this.id = id;
        this.nombre = nombre;
        this.Razon = razon;
        this.descuento = descuento;
        this.status = status;
    }
    public void AddCliente(String nombre, String descuento, String ruta, String Razon,String credito,String sub_canal,String ldescuento,
                   String lprecioa,String lprecioc,String LAT, String LONG, String G, String FormaPago, String puedoFacturar,String tipoFactura,
                   int status) {
        this.nombre = nombre;
        this.descuento = descuento;
        this.status = status;
        this.ruta = ruta;
        this.Razon = Razon;
        this.credito = credito;
        this.sub_canal = sub_canal;
        this.ldescuento = ldescuento;
        this.lprecioA = lprecioa;
        this.lprecioC = lprecioc;
        this.LAT = LAT;
        this.LONG = LONG;
        this.G = G;
        this.FormaPago = FormaPago;
        this.puedoFacturar = puedoFacturar;
        this.tipoFactura = tipoFactura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
