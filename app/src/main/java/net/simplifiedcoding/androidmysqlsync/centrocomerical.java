package net.simplifiedcoding.androidmysqlsync;

/**
 * Created by cliente on 27/12/2017.
 */

public class centrocomerical {
    public String label;
    private Double linicial,lfinal,litros;
    private Boolean metroscubicos;
    private Double mtstotal,mts,fcorre;
    private Cliente _actual;

    private void Calcular(){
        if (metroscubicos){
            mtstotal = linicial-lfinal;
            litros = mtstotal * fcorre * 3.921;

        }else{
            mtstotal = ((linicial-lfinal)*100) * 0.0283168;
            litros = mtstotal * fcorre * 3.921;
        }


    }


}
