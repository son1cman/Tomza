package net.simplifiedcoding.androidmysqlsync;

/**
 * Created by cliente on 17/08/2017.
 */

public class catalogo {
    public String label;
    public int lbs;


    private Cliente _actual;

    public String getDesQTY() {
        return desQTY;
    }


    public void setDesQTY(String desQTY) {
        this.desQTY = desQTY;
    }
    public void clear(){

        this.Qty = 0;
        this.qtyTotal = 0d;
        this.total = 0d;
        this.descTotal = 0d;
        this.PrecioSel = 0d;
        this.DescSel = 0d;
        this.desQTY = null;
        this.desDESC = null;
    }

    public String getDesDESC() {
        return desDESC;
    }

    public void setDesDESC(String desDESC) {
        this.desDESC = desDESC;
    }

    private String desQTY;
    private String desDESC;

    public Double getDescTotal() {
        return descTotal;
    }

    public void setDescTotal(Double descTotal) {
        this.descTotal = descTotal;
    }

    private Double descTotal = 0d;

    public Double getQtyTotal() {
        return qtyTotal;
    }

    public void setQtyTotal(Double qtyTotal) {
        this.qtyTotal = qtyTotal;
    }

    private Double qtyTotal;
    private Double PrecioSel;
    private Double DescSel;
    public int Qty;
    public Double agencia;
    public Double consumidor;
    public Double planta;
    public int Tipo;
    public boolean PrecioAsignado;
    public boolean DescuentoAsignado;
    public Double descuento;
    public Double total;

    public void agregarProducto(boolean descuentoAsignado,boolean precioAsignado,int Qty){
        this.Qty = Qty;
        this.DescuentoAsignado = descuentoAsignado;

        this.PrecioAsignado = precioAsignado;
        if(Tipo == 2 || Tipo == 1) this.PrecioAsignado = false; //Solamente tiendas puede facturar a precio de consumidor

        calcular();
    }
    public Double getPrecioSel(){
        return PrecioSel;
    }
    public Double getDescuentoSel(){
        return DescSel;
    }
    public Cliente get_actual() {
        return _actual;
    }

    public void set_actual(Cliente _actual) {
        this._actual = _actual;
    }

    public boolean isDescuentoAsignado() {
        calcular();
        return DescuentoAsignado;
    }

    public void setDescuentoAsignado(boolean descuentoAsignado) {

        DescuentoAsignado = descuentoAsignado;
        if(_actual != null) calcular();
    }


    public boolean isPrecioAsignado() {
        calcular();
        return PrecioAsignado;
    }

    public void setPrecioAsignado(boolean precioAsignado) {

        PrecioAsignado = precioAsignado;
        calcular();
    }



    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }



    public Double getDescuento25() {
        return descuento;
    }

    public void setDescuento25(Double descuento25) {
        this.descuento = descuento25;
    }

      public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public Double getAgencia() {
        return agencia;
    }

    public void setAgencia(Double agencia) {
        this.agencia = agencia;
    }

    public Double getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Double consumidor) {
        this.consumidor = consumidor;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int tipo) {
        Tipo = tipo;
    }
    private double cc;
    private double ag;
    public catalogo(String label, Double agencia, Double consumidor,int lbs,Double planta){

        this.label = label; this.agencia = agencia; this.consumidor = consumidor; this.planta = planta;
        this.lbs = lbs;
        this.Qty = 0;
        this.setTotal(0d);
        this.descTotal = 0d;

        cc = this.agencia;
        ag = planta;


    }
public void DescuentoEspecial35y45(int _presentacion){
    if((Tipo == 5 || Tipo == 6 || Tipo == 7) && _presentacion == 35){ //Litros Precio Especial
        this.DescSel = Double.parseDouble(_actual.getLprecioA());
        this.descuento = Double.parseDouble(_actual.getLprecioA());
        //this.PrecioSel = Double.parseDouble(_actual.getLprecioC());

    }
    if((Tipo == 5 || Tipo == 6 || Tipo == 7) && _presentacion == 45){ //Litros Precio Especial
        this.DescSel = Double.parseDouble(_actual.getLprecioC());
        this.descuento = Double.parseDouble(_actual.getLprecioC());
        //this.PrecioSel = Double.parseDouble(_actual.getLprecioC());

    }
    if((Tipo == 6 || Tipo == 7)&& _presentacion == 100){ //Litros Precio Especial
        this.DescSel = Double.parseDouble(_actual.getLdescuento());
        this.descuento = Double.parseDouble(_actual.getLdescuento());
        //this.PrecioSel = Double.parseDouble(_actual.getLprecioC());

    }



}
    private void calcular(){

        if (lbs != 0){//cilindro
            this.descuento = (Double.valueOf(_actual.getDescuento()) / 25) * lbs;
            this.descuento = (double)Math.round(this.descuento);
            this.Tipo = Integer.valueOf(_actual.getTipoFactura());
            if(Tipo == 2 || Tipo == 1) this.PrecioAsignado = false; //Solamente tiendas puede facturar a precio de consumidor

            if(_actual.getRuta().equals("600") || _actual.getRuta().equals("601") || _actual.getRuta().equals("602") || _actual.getRuta().equals("603")) {
                consumidor = cc;
                this.agencia = ag;
            }


            if(PrecioAsignado == true && DescuentoAsignado == true)//agencia + descuento
            {

                if(Tipo == 4 || Tipo == 5){

                    this.PrecioSel = agencia;
                    this.DescSel = descuento;
                    DescuentoEspecial35y45(lbs);
                    double age  = agencia - descuento;
                    total = (Qty * age);
                    qtyTotal = (Qty * age);
                    descTotal = 0d;
                    desQTY = String.valueOf(Qty) + " " + label + " "+String.valueOf(age) + " " + String.format("%.2f", (Qty * age) );
                    desDESC = String.valueOf(Qty) + " " + label +" " + String.valueOf(descuento) + " " + String.format("%.2f", descTotal) + "\n"; ;

                }
                else {
                    this.PrecioSel = agencia;


                    this.DescSel = descuento;
                    DescuentoEspecial35y45(lbs);
                    total = ((Qty * agencia)-(Qty*descuento));
                    qtyTotal = (Qty * agencia);
                    descTotal = (Qty*descuento);
                    if(_actual.getRuta().equals("600") || _actual.getRuta().equals("601") || _actual.getRuta().equals("602") || _actual.getRuta().equals("603")) {
                        total = ((Qty * ag)-(Qty*descuento));
                        qtyTotal = (Qty * ag);
                        descTotal = (Qty*descuento);
                    }
                    desQTY = String.valueOf(Qty) + " " + label + " "+String.valueOf(agencia) + " " + String.format("%.2f", (Qty * agencia) );
                    desDESC = String.valueOf(Qty) + " " + label +" " + String.valueOf(descuento) + " " + String.format("%.2f", descTotal) + "\n"; ;

                }



            }
            if(PrecioAsignado == true && DescuentoAsignado == false)//agencia + descuento
            {
                this.PrecioSel = agencia;
                this.DescSel = 0d;
                total = (Qty * agencia);
                qtyTotal = (Qty * agencia);
                if(_actual.getRuta().equals("600") || _actual.getRuta().equals("601") || _actual.getRuta().equals("602") || _actual.getRuta().equals("603")) {
                    total = (Qty * ag);
                    qtyTotal = (Qty * ag);

                }
                descTotal = 0d;
                desQTY = String.valueOf(Qty) + " " + label + " "+String.valueOf(agencia) + " " + String.format("%.2f", (Qty * agencia) ) + "\n";
                desDESC = "null";

            }
            if(PrecioAsignado == false && DescuentoAsignado == false)//consumidor + sin descuento
            {
                this.PrecioSel = consumidor;
                this.DescSel = 0d;
                qtyTotal = (Qty * consumidor);
                total = (Qty * consumidor);
                if(_actual.getRuta().equals("600") || _actual.getRuta().equals("601") || _actual.getRuta().equals("602") || _actual.getRuta().equals("603")) {
                    total = ((Qty * cc));
                    qtyTotal = (Qty * cc);

                }
                descTotal = 0d;
                desQTY = String.valueOf(Qty) + " " + label + " "+String.valueOf(consumidor) + " " + String.format("%.2f", (Qty * consumidor) ) + "\n";
                desDESC = "null";

            }
            if(PrecioAsignado == false && DescuentoAsignado == true)//consumidor + con descuento
            {

                this.PrecioSel = consumidor;
                this.DescSel = descuento;
                DescuentoEspecial35y45(lbs);
                if(Tipo == 5){
                    double consu  = consumidor - descuento;
                    total = (Qty * consu);
                    qtyTotal = (Qty * consu);
                    descTotal = 0d;
                    desQTY = String.valueOf(Qty) + " " + label + " "+String.valueOf(consu) + " " + String.format("%.2f", (Qty * consu) ) + "\n";

                }else{
                    total = ((Qty * consumidor) - (Qty*descuento));
                    qtyTotal = (Qty * consumidor);
                    descTotal = (Qty*descuento);
                    desQTY = String.valueOf(Qty) + " " + label + " "+String.valueOf(consumidor) + " " + String.format("%.2f", (Qty * consumidor) ) + "\n";
                    }

                if(Tipo == 7){
                    double consu  = consumidor - descuento;
                    total = (Qty * consu);
                    qtyTotal = (Qty * consu);
                    descTotal = (Qty*descuento);
                    desQTY = String.valueOf(Qty) + " " + label + " "+String.valueOf(consu) + " " + String.format("%.2f", (Qty * consu) ) + "\n";

                }


                if(_actual.getRuta().equals("600") || _actual.getRuta().equals("601") || _actual.getRuta().equals("602") || _actual.getRuta().equals("603")) {
                    total = ((Qty * cc)  - (Qty*descuento));
                    qtyTotal = (Qty * cc);
                    desQTY = String.valueOf(Qty) + " " + label + " "+String.valueOf(consumidor) + " " + String.format("%.2f", (Qty * consumidor) ) + "\n";

                }

                desDESC = String.valueOf(Qty) + " " + label +" " +String.valueOf(descuento) + " " + String.format("%.2f", descTotal) + "\n"; ;

            }
            //}
        }
        if (lbs == 0){//granel
            this.DescSel = Double.parseDouble(_actual.getDescuento());
            this.PrecioSel = agencia;
           /* if(_actual.getLprecioA() == "0"){ //Litros
                this.descuento = Double.valueOf(_actual.getDescuento());
            }*/
            if(_actual.getLprecioA().equals("1")){ //Litros Precio Especial
                this.DescSel = Double.parseDouble(_actual.getDescuento());
                this.PrecioSel = Double.parseDouble(_actual.getLprecioC());
            }

            if(_actual.getLprecioA().equals("2")){ //Kilos
                this.DescSel = Double.parseDouble(_actual.getDescuento());
                this.PrecioSel = Double.parseDouble(_actual.getLprecioC());
            }
            total = ((Qty * PrecioSel)-(Qty*DescSel));
            qtyTotal = (Qty * PrecioSel);
            descTotal = (Qty*DescSel);
            desQTY = String.valueOf(Qty) + " " + label + " "+String.valueOf(PrecioSel) + " " + String.format("%.2f", qtyTotal);
            desDESC = String.valueOf(Qty) + " " + label + " "+String.valueOf(DescSel) + " " + String.format("%.2f", descTotal);


        }//end if


    }//end method





}
