package net.simplifiedcoding.androidmysqlsync;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;

import java.text.SimpleDateFormat;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.content.SharedPreferences;
import android.widget.RadioButton;
import android.view.inputmethod.EditorInfo;
import android.view.KeyEvent;
import android.widget.ToggleButton;

import java.util.Date;

import static java.lang.Math.round;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*
    * CILINDROSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
    * */
    private boolean _FacCilindro = true;
    private  String _sDescuento = "";
    private  String _sTipo = "";

    private String efectivo,transC,transNum,chknum,chkmont,chkbank;

    private catalogo _10r,_20p,_20r,_25p,_25r,_30r,_35r,_40r,_45r,_50r,_60r,_lts,_kgs,_100r;
    public static String URL_SAVE_NAME = "http://www.tomzacr.com/t/saveitf.php"; //Factura
    public static String URL_GET_COMM = "http://www.tomzacr.com/t/comm.php"; //Cargar
    public static String URL_GET_COMM_PRICES = "http://www.tomzacr.com/t/commprices.php"; //Cargar
    public static String URL_GET_CLIENTE = "http://www.tomzacr.com/t/cliente.php"; //Clientes
    public static String URL_GET_BILLETES = "http://www.tomzacr.com/t/billete.php"; //Clientes

//JULIO 6
    //OSCAR 7
    // JORGE 8
    public static final String MyPREFERENCES = "SystemSettings";
    public static final String URL_MAIN = "URL_MAIN";
    public static final String _Tcredito = "tcredito";
    public static final String _Tcontado = "tcontado";
    public static final String _TdescuentoC = "descCredito";
    public static final String _TdescuentoCo = "descContado";

    public static final String _Precioa10 = "p10";
    public static final String _billete = "billete";
    public static final String _Precioa20 = "p20";
    public static final String _Precioa25 = "p25";
    public static final String _Precioa30 = "p30";
    public static final String _Precioa35  = "p35";
    public static final String _Precioa40  = "p40";
    public static final String _Precioa45  = "p45";
    public static final String _Precioa50  = "p50";
    public static final String _Precioa60  = "p60";
    public static final String _Precioa100  = "p100";

    public static final String _Precioc10 = "pc10";
    public static final String _Precioc20 = "pc20";
    public static final String _Precioc25 = "pc25";
    public static final String _Precioc30 = "pc30";
    public static final String _Precioc35  = "pc35";
    public static final String _Precioc40  = "pc40";
    public static final String _Precioc45  = "pc45";
    public static final String _Precioc50  = "pc50";
    public static final String _Precioc60  = "pc60";
    public static final String _Precioc100  = "pc100";

    public static final String _Preciolts = "lts";
    public static final String _Preciokgs = "kgs";
    public static final String _PreciokgsDemasa = "kgsdemasa";
    public static final String _PreciokgsMetalco = "kgsmetalco";
    public static final String _PreciokgsCrown = "kgscrown";
    public static final String _PreciokgsCuetara = "kgscuetara";

    public static final String URL_MAIN_2 = "URL_MAIN_2";
    public static final String URL_COMM = "URL_COMM";
    public static final String URL_CLIENTES = "URL_CLIENTES";
    public static final String MIRUTA = "MI_RUTA";
    public static final String TOTALFACTURADO = "TOTAL_FACTURADO";


    //public static final String TOTALDESCUENTO = "TOTAL_DESCUENTO";

    SharedPreferences sharedpreferences;



//1 luis
    //2 andres
    //valerin
    String _supervisor = "5";

    //Cartago

    int codigocliente = -1;
    Cliente _actual;


    //17PJL77239328
    //16PHR028965
    //17PJL772932
    String _CurrentGPS,_CurrentGPS2;



    int usardos;

    String[] _ruta1 = {"Jonathan  Villalobos","Carlos Silva","Centro Comercial Guacimo S.A.","Maria Elsa Gonzalez","Jorge Rojas","Erik Ardon","Inverciones Ching Chu y Asociados S.A","Xiao Mei Cen","Maria Solis Henrriques","Heriberto Fonseca","Enrrique Esquivel","Jose Vasquez","Jetty Maisi ","Centro Comercial Guacimo S.A.","Jun Feng","Juan Gonzalez","Miguel Fallas","Marielos  Lopez","Francini Chavarria","Rigoberto Brenes","Jorge Rojas","CSU SRL Pali Guacimo S.A.","Jian Zen","Michael Olivares","Shirley lopez","Feng lin","Mauren Parra","Vanesa Urena","Gerardo Castro","Eduardo Artavia","Zaida Urena","Juan Hui","Jose Esmith","Miguel Jimenez","Roberth Duran","Eduardo Artavia","Juan Hui","Zaida Urena","Jose Esmith","Miguel Jimenez","Roberth Duran","Recargas Chavarria","Juan Daniel Gonzalez Rodriguez","Cen Jian Sheng","Dagoberto Vargas Ulloa","Huang Chaowen","Ricardo Faith","Guisell Delgado","Jimmy Bonilla","Miguel  Jimenez","Rosa Castillo","Ingri Acevedo","Lincon Montes","Rosa Mora","Elizeth Zuniga","Doris Sanchez","Margarita Villegas","Aida Mendoza","Maria Salazar Mora","Bryan Arce","Huang Chaowen","Maria Salazar Mora","Jimmy Bonilla","Ricardo Faith","Bryan Arce","Miguel  Jimenez","Guisell Delgado","Rosa Castillo","Ingri Acevedo","Lincon Montes","Rosa Mora","Elizeth Zuniga","Doris Sanchez","Margarita Villegas","Aida Mendoza","Juan Zuniga","Yulieth Soto","Jose Luis Araya","Edwin Argin","Heidy Esquivel","Lorenzo Lizano","Jorge Saenz","Maira Najera","Gabriela Nunez","Jose Camacho","Carlos Najera","Jorge Nunez","Andrea Gonzalez","Neily Salguero","Yaneiry Rojas","Jordi Douglas","Raquel Fernandez","Raquel Fernandez","Centro Comercial Guacimo S.A."};
    String[] _nombre = {"Tienda Milano","Tienda Silva","Centro Comercial Guacimo","Abastecedor la casita","Super Hermanos Rojas","Industrias ferreteras Ardon del caribe","Super Pocora","Super el Amigo","Soda Pinales del Caribe","Pulperia la Victoria","Abastecedor la Puriscalena","Super san Ramon","Soda las Delicias","Centro Comercial Guacimo Rio Jimenez","Super Dragon de Oro","Super el Uno","Super Alegre","Soda Rosita","Recargas Chavarria","Super Hermanos Brenes"," Super Hermanos Rojas Guacimo","Pali Guacimo","Mini super Guayacan","Pulp Olifal","Pulperia Angel","Super 99"," Pulpeia Mau Mau","Pulperia Paola","Panaderia la parada","Panaderia la delicia","Pulperia el trailero","Super don Carlos","Comercial Smith","Super la Central","Pizza Movil","Panaderia la delicia","Super don Carlos","Pulperia el trailero","Comercial Smith","Super la central","Pizza Movil","Recargas Chavarria","Abastecedor el Uno","Mini Super Guayacan","Abastecedor Cruce de Cartagena","Super la selva"," Comercial Lumory","Abastecedor Geminis"," Esquineros del caribe","Pulperia la Hormiguita","Pulperia Quito","Abastecedor Kemlly","Panaderia Deylin","Abastecedor Bermudez y Mora","Soda Flor de Liz","Patronato Escolar","Pulperia la plaza","Pulperia Silvia","Escuela Irocois","Finmao CR","Super la selva","Escuela Irocois"," Esquineros del caribe"," Comercial Lumory","Finmao CR","Pulperia la Hormiguita","Abastecedor Geminis","Pulperia Quito","Abastecedor Kemlly","Panaderia Deylin","Abastecedor Bermudez y Mora","Soda Flor de Liz","Patronato Escolar","Pulperia la plaza","Pulperia Silvia","Mini super el Economico"," Mini super Parismina","Mini super san Lorenzo"," Mini super Arjin","American Pizza","Abastecedor Guayacan","Restaurante Apache","Abastecedor santa Marta","Pulperia Gaby","Abastecedor los angeles","Abastecedor el Ceibo","Restaurante Tres Equis"," Abastecedor Jimenez","Bar restaurante el cruce"," Pulperia Yaneiry","Comidas rapidas Joamy","Super el economico","Super el economico","Centro comercial Guacimo Pocora"};
    String[] _descuentos = {"0","0","700","500","450","0","0","0","0","0","150","600","0","700","600","0","0","0","250","700","450","240","700","0","0","792","0","0","0","0","0","400","0","0","0","0","400","0","0","0","0","250","0","700","500","700","350","300","0","0","0","250","0","0","0","0","0","400","0","0","700","0","0","350","0","0","300","0","250","0","0","0","0","0","400","500","300","0","0","0","0","0","0","0","250","200","0","250","0","0","0","400","400","700"};
    String[] _MSGR = {"17PKM1727","17PKM0630","17PKM0629","17PKM1524","17PKM1425","17PKM2119","17PKM1425","17PKM1433","17PKM1534","17PKM2238","17PKM1337","17PKM1337","17PKM1434","17PKM1434","17PKM0833","17PKM0833","17PKM0734","17PKM0630","17PKM0731","17PKM0630","17PKM0630","17PKM0529","17PKM0529","17PKM0329","17PKM0329","17PKM2119","17PKM0229","17PKM2119","17PKM2119","17PKM2120","17PKM1816","17PKM1717","17PKM1617","17PKM1416","17PKM1920","17PKM2120","17PKM1717","17PKM1816","17PKM1617","17PKM1416","17PKM1920","17PKM0731","17PKM0833","17PKM0529","17PKM0934","17PKM0329","17PKM2021","17PKM1921","17PKM0628","17PKM1519","17PKM1621","17PKM1822","17PKM1425","17PKM1426","17PKM1426","17PKM1121","17PKM1121","17PKM1121","17PKM1226","17PKM0628","17PKM0329","17PKM1226","17PKM0628","17PKM2021","17PKM0628","17PKM1519","17PKM1921","17PKM1621","17PKM1822","17PKM1425","17PKM1426","17PKM1426","17PKM1121","17PKM1121","17PKM1121","17PKM1438","17PKM0928","17PKM2011","17PKM2116","17PKM1425","17PKM2010","17PKM1909","17PKM1808","17PKM1807","17PKM1805","17PKM1701","17PKM1801","17PKL1699","17PKM1700","17PKL1799","17PKM1425","17PKM1424","17PKM1424","17PKM1425"};
    String[] _LAT = {"10.187048729300217","10.21393621738316","10.208713168834592","10.165762289794909","10.172638804014527","10.114725683775404","10.172290335582593","10.247539124484325","10.252099418238233","10.285873702765572","10.284008885421526","10.280364535138807","10.250030251383283","10.249057188433047","10.244689369259318","10.243939103596201","10.250061974042406","10.215542496061248","10.220710882038679","10.215930359033152","10.212713314975279","10.210746190055813","10.209649523812539","10.209964286958375","10.210155709999746","10.1193288283654","10.208474089083769","10.115719558231707","10.12082491632475","10.125965959053076","10.093602449003502","10.097402937408122","10.100719273345543","10.089875972300906","10.125444223183532","10.125965959053076","10.097402937408122","10.093602449003502","10.100719273345543","10.089875972300906","10.125444223183532","10.220705867897195","10.24378913569332","10.209603877537162","10.249901130057461","10.209550620052454","10.13452665993997","10.133176134351254","10.202286804929383","10.11660156841359","10.13136462444256","10.145548043900957","10.171812194733608","10.177927525864442","10.176420266562523","10.139177661625554","10.139423700308985","10.138067393262741","10.182434528021405","10.202286804929383","10.209550620052454","10.182434528021405","10.202286804929383","10.13452665993997","10.202286804929383","10.11660156841359","10.133176134351254","10.13136462444256","10.145548043900957","10.171812194733608","10.177927525864442","10.176420266562523","10.139177661625554","10.139423700308985","10.138067393262741","10.287598961557338","10.197177856833653","10.043461545901826","10.093342141877478","10.172382353681968","10.039508650874371","10.024601290312217","10.014446314787422","10.0126174126491","9.987812856701254","9.958874285711286","9.957152725821105","9.934918000854022","9.944377668164838","9.932659807331524","10.17142117414584","10.164150794720548","10.164150794720548","10.171709666742098"};
    String[] _LONG = {"-83.57802146243188","-83.67964415752877","-83.68114276317642","-83.59932935843274","-83.60535709695068","-83.54168009143335","-83.60575615577339","-83.60290089373332","-83.59888140679023","-83.53795150183339","-83.61934068994535","-83.61539233141687","-83.6018050186196","-83.60235733639759","-83.66106002486897","-83.66404173968658","-83.6695065886594","-83.67563785148936","-83.67287885511367","-83.67944587746904","-83.68292289793519","-83.68394149755085","-83.69154830192292","-83.70930284765137","-83.71073998564523","-83.53870777904072","-83.71128968730886","-83.54139076726811","-83.53769875022935","-83.54382038542923","-83.57019324173335","-83.57473319190126","-83.58415295604482","-83.60170481111167","-83.56383634080366","-83.54382038542923","-83.57473319190126","-83.57019324173335","-83.58415295604482","-83.60170481111167","-83.56383634080366","-83.67292625085081","-83.66381703877074","-83.6915447640191","-83.65213275749095","-83.71020534191248","-83.55471179428936","-83.5562654141202","-83.67667284969428","-83.60007085514776","-83.58369653253479","-83.5721782672163","-83.6048955349366","-83.601209186961","-83.60289157448041","-83.63344835103156","-83.63415709005207","-83.63486580688225","-83.62275251213109","-83.67667284969428","-83.71020534191248","-83.62275251213109","-83.67667284969428","-83.55471179428936","-83.67667284969428","-83.60007085514776","-83.5562654141202","-83.58369653253479","-83.5721782672163","-83.6048955349366","-83.601209186961","-83.60289157448041","-83.63344835103156","-83.63415709005207","-83.63486580688225","-83.60680633146018","-83.64846005434286","-83.54888817959721","-83.5387759874306","-83.60472536769716","-83.54599912974543","-83.55794355303524","-83.56426293194883","-83.56562836422601","-83.56890313494226","-83.57288164748131","-83.5692605156218","-83.58813899769396","-83.57452353266676","-83.57215612241936","-83.60526083535314","-83.60336196828595","-83.60336196828595","-83.60586083275784"};
    String[] _CREDITO = {"0","0","1","0","0","0","0","0","0","0","0","0","0","1","0","0","0","0","0","0","0","1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","1"};
    String[] _SUB_CANAL = {"4","4","0","6","0","14","1","5","11","7","6","1","11","0","5","1","1","11","6","1","0","0","5","7","7","5","7","7","12","12","7","5","14","1","11","12","5","7","14","1","11","7","6","5","6","5","5","6","14","7","7","6","12","6","11","11","7","7","11","14","5","11","14","5","14","7","6","7","6","12","6","11","11","7","7","1","1","1","1","11","6","9","6","7","6","6","9","6","9","7","11","1","1","0"};
    String[] _FORMAPAGO = {"0","0","1","0","0","0","0","0","0","0","0","0","0","1","0","0","0","0","0","0","0","1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","1"};
    String[] _PUEDOFACTURAR = {"0","0","1","0","0","0","0","0","0","0","0","0","0","1","0","0","0","0","0","0","0","1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","1"};
    String[] _TIPOFACTURA = {"0","0","1","0","0","0","0","0","0","0","0","0","0","1","0","0","0","0","0","0","0","1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","1"};


    String[] _ruta1_2 = {" "};


    private List<CharSequence> charSequences = new ArrayList<>();
    private List<CharSequence> clienteFromdb = new ArrayList<>();
    private List<CharSequence> Liquidacion = new ArrayList<>();
    private List<String> indexes = new ArrayList<>();
    private List<String> indexes2 = new ArrayList<>();
    private String _negocio,_descuento,_credito;


    int[] _descuento1 = {400,250,235,0,700,240,500,0,0,0,0,0,0,0,150,0,792,0,0,250,0,0,300,300,0,0,700,700,250,250,0,0,700,0,0,0,0,0,450,450,0,250,0,0,0,700,700,700,600,0,0,400,400,500,600,0,0,0,0,0,0,506,0,0,0,0,0,0,0,0,0,0,0,0,400,400,250,350,350,700,0,0,0,0,0,0,0,0,0,0,300,0,0};
    //{0,649,0,0,0,649,0,0,240,649,0,0,0,0,0,0,649,649,0,0,0,0,250,0,0,0,0,0,400,0,400,600,0,400,300,0,0,0,600,0,500,0,0,0,0,0,600,400,0,600,0,0,0,0,0,0,0,0,400,0,0,0,600,0,0,0,0,0,0,0,0,400,0,600,400,0,0,300,0,500,0,0,500,0,0,0,0,400,600};

    int[] _descuento1_2 = {600,0,0,0,0,0,0,0,0,0,400,0,0,0,0,0,649,0,0,0,649,240,0,649,0,0,0,0,0,649,649,0,0,0,0,0,0,0,0,400,0,0,0,0,0,0,0,0,0,0,0,0,250,0,0,600,0,0,0,0,0,0,0,0,0,0,0,600,0,400,500,500,0,500,500,0};

    int[] _credit1 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    int[] _credit1_2 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    String[] _TRON1 = {"17PKM1121","17PKL1699","17PKM1701","17PKM0630","17PKM0529","17PKM0529","17PKM0934","17PKM1121","17PKM1121","17PKM2120","17PKM2120","17PKM2116","17PKM1426","17PKM1426","17PKM1337","17PKM2119","17PKM2119","17PKM0628","17PKM0628","17PKM0731","17PKM1807","17PKM2119","17PKM1921","17PKM1921","17PKM1425","17PKM2238","17PKM0329","17PKM0329","17PKM1822","17PKM1822","17PKM1425","17PKM1434","17PKM0529","17PKM0628","17PKM0628","17PKM1727","17PKM1425","17PKM1801","17PKM1425","17PKM0630","17PKM1909","17PKM1805","17PKM1617","17PKM1617","17PKM2011","17PKM1434","17PKM0629","17PKM1425","17PKM1337","17PKM0833","17PKM0833","17PKM1717","17PKM1717","17PKM1438","17PKM0833","17PKM1425","17PKM1425","17PKM2010","17PKM1808","17PKM1121","17PKM1121","17PKM1524","17PKM1226","17PKM1226","17PKM1534","17PKM0630","17PKM0229","17PKM0329","17PKM1519","17PKM1519","17PKM0734","17PKM1416","17PKM1416","17PKM1700","17PKM1424","17PKM1424","17PKM0731","17PKM2021","17PKM2021","17PKM0630","17PKM1920","17PKM1920","17PKM1621","17PKM1621","17PKM1426","17PKM1426","17PKM0329","17PKM2119","17PKM1433","17PKL1799","17PKM0928","17PKM1816","17PKM1816"};

    String[] _TRON1_2 = {"00"};





    String[] _direccionDB = {"GRANO DE ORO 600NORTE DE LA PLAZA","75 ESTE DEL TRANSITO DE TURRIALBA","FRENTE AL PARQUE","CARRETERA HACIA PERALTA","DE LA ESCUELA DE CALLE DEL CAS 300 ESTE","DE LA ESCUELA DEL CAS 700 OESTE","600 ESTE DE LA ESCUELA EL CAS","BARRIOS LOS SAUSES","BARRIO LOS SAUCES","BARRIO LOS SAUCES","BARRIO EL ORIENTE SANTA ROSA","BARRIO EL ORIENTE SANTA ROSA","PEJIBALLE 300 SUROESTE DE LA ESCUELA","DIAGONAL AL PARQUE LA SUIZA","DE LA RURAL50 OESTE","CENTRO LA SUIZ","FRENTE AL BANCO NACIONEAL DE SUIZA","FRENTE A PLAZA DE DEPORTES DE LA SUIZA","FRENTE A PLAZA LAS COLONIAS","BARRIO 100 MANZANAS 200 SUR DE LA ESCUELA","BARRIO 100 MANZANAS 800 NORTE DE LA ESCULA","FRENTE AL CENTRO EDUCATIVO TAYUDIC","DE LA ESCULA DE TAYITIC 2 KM SUR","DE LA ESCUELA 75 NORTE","BAJOS DE PACUARE FRENTE A IGLESIA","BAJOS DE PACUARE SOBRE CARRETERA","GRANO DE ORO CENTRO","GRANO DE ORO QUETZAL ESCUELA 250SUR","GRANO DE ORO 300 ESTE","FRENTE A PLAZA DE DEPORTES DE GRANO DE ORO","DE LA CONCHA DEPORTIVA 50 ESTE GRANO DE ORO","GRANO DE ORO 200 NORTE","TERMINAL DE TRANSTUSA","DE LA ESCUELA DE SITIO MATA 300 ESTE","500 OESTE DE LA ESCUELA","CENTRO DE LLANOS PAVONES","100 SUR DE LA ESCUELA DE JOBILLOS","TURRIALBA CENTRO","DEL PARQUE DE AQUIARES 50 NORTE","DE COOPEANDE 50 NORTE","PEJIBALLE EL HUMO CENTRO","DE LA IGLESIA PEJIBAYE 400 SUR","PEJIBALLE ATIRRO 700 OESTE","DEL PARQUE DE TURRIALNA 200 NORTE","SANTA ELENA 50 NORTE DE FOTOS MARTINEZ","JUAN VILLAS 400 NORTE DE LA ESCUELA","JUAN VIÑAS LOS ALPES ESCUELA 100 ESTE","JUAN VIÑAS 50 OESTE","JUAN VIÑAS DE LA IGLESIA 50 NORTE","FRENTE A LA ESCUELA DE GUAYABO","SANTA CRUZ CENTRO","DEL CENTRO DE SANTA CRUZ KM AL NORTE","DIAGONAL A LA IGLESIA DEL TUIS","CANADA LASUIZA DE LA ESCUELA 50M OESTE","PERALTA CENTRO DE LA ESCUELA 25 ESTE","CAPELLADES DIAGONAL A LA IGLESIA","BARRIO CANADA 150 OESTE DE ESCUELA","DE LA ESCUELA DE LA LIRA 400 NORTE","CENTRO DE TURRIALBA DIAGONAL A LA PLAZA","TURIALBA CENTRO","DE LA ESCULEA DE PAVONES 300 NORTE","300 NORTE DE LA CRUZ ROJA LA SUIZA","SANTA CRUZ CENNTRO DE LA IGLESIA 200 NORTE","DEL MONUMENTO GUAYABO 5 KM NORTE","300 NORESTE DE LA IGLESIA DEL CARMEN DE SANTA CRUZ","DEL BAR ARC NOE 800 MT MANO DERACHA","DEL BAR ARC NOE 800 MT MANO DERACHA"};
    String[] _telefonoDB = {"8337-3517","2556-6621","2559-1020","8635-5975","8346,7764","8350-8612","2285-1700","8659-0449","2245-1709","2245-1861","2529-2047","2578-0187","8989-7322","2531-1281","2531-1919","8745-3214","2531-1084","6138-6734","8841-4225","7102-3673","8357-2837","8846-3392","","2554-8345","","","","8472-0883","2532-2083","","","","8950-3754","2539-1670","2538-1122","8648-7436","2538-1514","2556-1834","2556-2298","8790-7444","2531-3102","2272-9150","2272-2313","2274-4747","8753-7194","","2532-1020","2532-2010","8767-1688","2221-2733","6103-1234","8320-7649","2292-4583","2531-1101","2559-0915","","2531-1530","2577-1940","2574-2630","2577-1034","2538-1005","8488-6045","","7156-2072","8770-8047","8971-2653",""};
    Integer[] _descuentoDB = {0,425,425,0,0,0,0,0,0,0,0,0,240,0,649,0,240,200,649,0,0,649,0,0,0,0,0,0,0,0,0,0,649,0,649,649,200,300,649,-1,240,240,0,300,0,0,0,240,649,425,0,200,0,200,0,0,425,649,200,425,300,649,240,0,0,240,240};
    Integer[] _creditDB = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private int _SelectedOption = 0;

    TextView myLabel;
    Button sendButton;

    // UI Widgets.
    private Button mRequestUpdatesButton;
    private Button mRemoveUpdatesButton;
    private Button mRequestButton;
    private TextView mLocationUpdatesResultView;
    private EditText mTextView;
    /*********************************************************
     * Facturacion
     */

    RadioButton  m_i25,m_i25r,m_i20,m_i20r,m_i30r,m_i35r,m_lts,m_kgs,m_i50;
    RadioButton    m_i45r,m_i100r,m_i10r,m_i40r,m_i60r;



    Button m_add,m_substract;
    String _codigocliente = "0";
    String _client = "Libreria Hellen";
    String _ruta = "1";
    String _coords = "9.872890,-83.944350";
    String _date;
    double _totalfac = 0;
    double _totaldesc = 0;
    String _Quantities;
    int _facnum = 10001;
    String _credit = "c";
    String _desc = "y";

    int Q10,Q20,Q25,Q35,Q40,Q45,Q60,Q100,Qlts,Qkgs;
    int Q10r,Q20r,Q25r,Q35r,Q40r,Q45r,Q60r,Q100r;
    int Q50,Q30;



    double _precioCilindro10 = 2783;
    double _precioCilindro20 = 5567;
    double _precioCilindro25 = 5693;//6967
    double _precioCilindro30 = 6875;
    double _precioCilindro35 = 9742;
    double _precioCilindro40 = 11134;
    double _precioCilindro45 = 12526;
    double _precioCilindro60 = 16700;
    double _precioCilindro100 = _precioCilindro25 * 4;


    double _descCilindro25 = 0;
    double _descCilindro100 = _descCilindro25 * 4;

    double _descCilindro10 = round(_descCilindro25/25) * 10;
    double _descCilindro20 = round(_descCilindro25/25) * 20;
    double _descCilindro35 = round(_descCilindro25/25) * 35;
    double _descCilindro40 = round(_descCilindro25/25) * 40;
    double _descCilindro45 = round(_descCilindro25/25) * 45;
    double _descCilindro60 = round(_descCilindro25/25) * 60;

    LatLon2MGRS c;



    double _precioLts = 221.4;
    double _precioKgs = _precioLts * 11.67;
    //database helper object
    private DatabaseHelper db;

    //View objects
    private Button buttonAdd,buttonSync;
    EditText myUserName;

    private ListView listViewNames;
    private String LAT,LONG;

    private LocationManager locationManager;
    private LocationListener locationListener;

    //List to store all the names
    private List<Name> names;
    private List<Cliente> clientes;

    //1 means data is synced and 0 means data is not synced
    public static final int NAME_SYNCED_WITH_SERVER = 1;
    public static final int NAME_NOT_SYNCED_WITH_SERVER = 0;

    //a broadcast to know weather the data is synced or not
    public static final String DATA_SAVED_BROADCAST = "net.simplifiedcoding.datasaved";

    //Broadcast receiver to know the sync status
    private BroadcastReceiver broadcastReceiver;

    //adapterobject for list view
    private NameAdapter nameAdapter;

    private void resetUI(){
        mTextView.setText("");
        myUserName.setText("");
        m_i20r.setText("20r x 0");m_i25.setText("25 x 0");
        m_i25r.setText("25r x 0");m_i20.setText("20 x 0");
        m_i30r.setText("30 x 0");m_i35r.setText("35 x 0");
        m_i40r.setText("40 x 0"); m_i45r.setText("45 x 0");
        m_i100r.setText("100 x 0");m_i10r.setText("10 x 0");
        m_i50.setText("50 x 0");m_i60r.setText("60 x 0");
        m_kgs.setText("kgs x 0"); m_lts.setText("lts x 0");
        _lts.clear();_kgs.clear();
        _10r.clear();_20p.clear();_20r.clear();_25p.clear();_25r.clear();_30r.clear();_35r.clear();
        _40r.clear();_45r.clear();_50r.clear();_60r.clear();_100r.clear();
        codigocliente = -1;
        efectivo = String.valueOf(0);
        transC = "0";
        transNum = "0";
        chkmont = "0";
        chknum = "0";
        chkbank = "0";
        _totalfac = 0;
        _totaldesc = 0;






        Q50 = Q30 =Q10 = Q10r = Q20 = Q20r = Q25 = Q25r = Q35 = Q35r = Q40 = Q40r = Q45 = Q45r = Q60 = Q60r = Q100 = Q100r = 0;
    }


    private void saveName(final int id, final String name) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MainActivity.URL_SAVE_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                //updating the status in sqlite
                                db.updateNameStatus(id, MainActivity.NAME_SYNCED_WITH_SERVER);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _actual = new Cliente();
        if(!_FacCilindro){
            //Cambiar Direcciones para granel
            URL_SAVE_NAME = "http://www.tomzacr.com/t/saveitfg.php"; //Factura
            URL_GET_COMM = "http://www.tomzacr.com/t/commg.php"; //Cargar
            URL_GET_COMM_PRICES = "http://www.tomzacr.com/t/commprices.php"; //Cargar
            URL_GET_CLIENTE = "http://www.tomzacr.com/t/clienteg.php"; //Clientes
        }
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        _supervisor = sharedpreferences.getString(MIRUTA,null);
        if (_supervisor == null) _supervisor = "0";
        _10r = new catalogo("10 lbs Rosca",Double.valueOf(sharedpreferences.getString(_Precioa10,"0")),Double.valueOf(sharedpreferences.getString(_Precioc10,"0")),10);
        _20r = new catalogo("20 lbs Rosca",Double.valueOf(sharedpreferences.getString(_Precioa20,"0")),Double.valueOf(sharedpreferences.getString(_Precioc20,"0")),20);
        _20p = new catalogo("20 lbs Mejorado",Double.valueOf(sharedpreferences.getString(_Precioa20,"0")),Double.valueOf(sharedpreferences.getString(_Precioc20,"0")),20);
        _25r = new catalogo("25 lbs Rosca",Double.valueOf(sharedpreferences.getString(_Precioa25,"0")),Double.valueOf(sharedpreferences.getString(_Precioc25,"0")),25);
        _25p = new catalogo("25 lbs Mejorado",Double.valueOf(sharedpreferences.getString(_Precioa25,"0")),Double.valueOf(sharedpreferences.getString(_Precioc25,"0")),25);
        _30r = new catalogo("30 lbs Rosca",Double.valueOf(sharedpreferences.getString(_Precioa30,"0")),Double.valueOf(sharedpreferences.getString(_Precioc30,"0")),30);
        _35r = new catalogo("35 lbs Rosca",Double.valueOf(sharedpreferences.getString(_Precioa35,"0")),Double.valueOf(sharedpreferences.getString(_Precioc35,"0")),35);
        _40r = new catalogo("40 lbs Rosca",Double.valueOf(sharedpreferences.getString(_Precioa40,"0")),Double.valueOf(sharedpreferences.getString(_Precioc40,"0")),40);
        _45r = new catalogo("45 lbs Rosca",Double.valueOf(sharedpreferences.getString(_Precioa45,"0")),Double.valueOf(sharedpreferences.getString(_Precioc45,"0")),45);
        _50r = new catalogo("50 lbs Rosca",Double.valueOf(sharedpreferences.getString(_Precioa50,"0")),Double.valueOf(sharedpreferences.getString(_Precioc50,"0")),50);
        _100r = new catalogo("100 lbs Rosca",Double.valueOf(sharedpreferences.getString(_Precioa100,"0")),Double.valueOf(sharedpreferences.getString(_Precioc100,"0")),100);


        _60r = new catalogo("60 lbs",Double.valueOf(sharedpreferences.getString(_Precioa60,"0")),Double.valueOf(sharedpreferences.getString(_Precioc60,"0")),60);

        _lts = new catalogo("Litros GLP",Double.parseDouble(sharedpreferences.getString(_Preciolts,"0")),Double.parseDouble(sharedpreferences.getString(_Preciolts,"0")),0);
        _kgs = new catalogo("Kilogramos GLP",Double.parseDouble(sharedpreferences.getString(_Preciokgs,"0")),Double.parseDouble(sharedpreferences.getString(_Preciokgs,"0")),0);

        LAT = "-10000";


        //initializing views and objects
        db = new DatabaseHelper(this);
        names = new ArrayList<>();
        clientes = new ArrayList<>();

        Q10 = Q40 = Q60 = Q20 = Q25 = Q35 = Q45 = Q100 = Qlts = Qkgs = 0;

        m_i20 = (RadioButton) findViewById(R.id.i20); m_i25 = (RadioButton) findViewById(R.id.i25);
        m_i20r = (RadioButton) findViewById(R.id.i20r); m_i25r = (RadioButton) findViewById(R.id.i25r);
        m_i30r = (RadioButton) findViewById(R.id.i30); m_i35r = (RadioButton) findViewById(R.id.i35);
        m_i40r = (RadioButton) findViewById(R.id.i45);m_i45r = (RadioButton) findViewById(R.id.i45r);
        m_i100r= (RadioButton) findViewById(R.id.i100r);
        m_i10r = (RadioButton) findViewById(R.id.i10r);m_i50 = (RadioButton) findViewById(R.id.i50);m_i60r = (RadioButton) findViewById(R.id.i60r);
        m_lts = (RadioButton) findViewById(R.id.ilts);m_kgs = (RadioButton) findViewById(R.id.ikgs);

        if(!_FacCilindro){//granel
            m_i20.setVisibility(View.GONE);
            m_i20r.setVisibility(View.GONE);
            m_i25.setVisibility(View.GONE);
            m_i25r.setVisibility(View.GONE);
            m_i30r.setVisibility(View.GONE);
            m_i35r.setVisibility(View.GONE);
            m_i40r.setVisibility(View.GONE);
            m_i45r.setVisibility(View.GONE);
            m_i100r.setVisibility(View.GONE);
            m_i10r.setVisibility(View.GONE);
            m_i50.setVisibility(View.GONE);
            m_i60r.setVisibility(View.GONE);
        }

        m_add = (Button) findViewById(R.id.btn_add); m_substract = (Button) findViewById(R.id.btn_substract);

        mTextView = (EditText) findViewById(R.id.txtWebsite);
        //mTextView.setImeActionLabel("Cargar",KeyEvent.KEYCODE_ENTER);
        mTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {


                if (actionId == EditorInfo.IME_ACTION_DONE) { //IME_ACTION_DONE para cerrar editor
                    //m_i25.setText("25 lbs x "+mTextView.getText().toString()+"  Unids.");
                    switch (_SelectedOption ){
                        case 250:
                            Q50 = Integer.valueOf(mTextView.getText().toString());
                            m_i50.setText("50 x "+mTextView.getText().toString());

                            break;
                        case 230:
                            Q30 = Integer.valueOf(mTextView.getText().toString());
                            m_i30r.setText("30 x "+mTextView.getText().toString());

                            break;
                        case 0:
                            Q25 = Integer.valueOf(mTextView.getText().toString());
                            m_i25.setText("25 x "+mTextView.getText().toString());

                            break;
                        case 125:
                            Q25r = Integer.valueOf(mTextView.getText().toString());
                            m_i25r.setText("25r x "+mTextView.getText().toString());
                            break;
                        case 1:
                            Q20= Integer.valueOf(mTextView.getText().toString());
                            m_i20.setText("20 x "+mTextView.getText().toString());
                            break;
                        case 10:
                            Q20r= Integer.valueOf(mTextView.getText().toString());
                            m_i20r.setText("20r x "+mTextView.getText().toString());
                            break;


                        case 20:
                            Q35r= Integer.valueOf(mTextView.getText().toString());
                            m_i35r.setText("35 x"+mTextView.getText().toString());
                            break;

                        case 30:
                            Q45r= Integer.valueOf(mTextView.getText().toString());
                            m_i45r.setText("45 x "+mTextView.getText().toString());
                            break;


                        case 40:
                            Q100r= Integer.valueOf(mTextView.getText().toString());
                            m_i100r.setText("100 x "+mTextView.getText().toString());
                            break;
                        case 5:
                            Qlts= Integer.valueOf(mTextView.getText().toString());
                            m_lts.setText("Granel lts x "+mTextView.getText().toString());
                            break;
                        case 6:
                            Qkgs= Integer.valueOf(mTextView.getText().toString());
                            m_kgs.setText("Granel kgs x "+mTextView.getText().toString());
                            break;

                        case 70:
                            Q10r = Integer.valueOf(mTextView.getText().toString());
                            m_i10r.setText("10 x "+mTextView.getText().toString());
                            break;

                        case 80:
                            Q40r = Integer.valueOf(mTextView.getText().toString());
                            m_i40r.setText("40 x "+mTextView.getText().toString());
                            break;

                        case 90:
                            Q60r = Integer.valueOf(mTextView.getText().toString());
                            m_i60r.setText("60 x "+mTextView.getText().toString());
                            break;
                        default:
                            break;
                    }
                    //progressButton.performClick();
                    mTextView.setText("0");
                    //resetUI();
                    InputMethodManager inputManager = (InputMethodManager)
                            getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.toggleSoftInput(0, 0);
                    return true;
                }

                return false;
            }
        });
        //

        m_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetUI();
                //customDialog(); //Agregar Cliente
                //Toast.makeText(getApplicationContext(),sharedpreferences.getString(MIRUTA,null),Toast.LENGTH_LONG).show();
                //db.DeleteClients();
                //ReadCommand();
                //Toast.makeText(getApplicationContext(),sharedpreferences.getString(MIRUTA,null),Toast.LENGTH_LONG).show();

                //17PJL7793
                //db.updateClienteStatus(2,"Puleria lNOT HIM","250","0","SIR JACKS SPARROW","0","7","5","1","0","9.86547841","-84.978545","17PJL7793","1","1","1",0);
                //db.addCliente("Tienda las margaritas","250","0","Kevin Oscar Alberto Martinez Ruiz","0","7","5","1","0","9.86547841","-84.978545","JPHL7585","1","1","1",0);

            }
        });
        sendButton = (Button) findViewById(R.id.send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //codigocliente = getClienteFromGPS(_TRON6);
                //http://rextester.com/BAKNU7558
                if(codigocliente != -1){
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                    final View dialogView = inflater.inflate(R.layout.tipopago, null);
                    dialogBuilder.setView(dialogView);

                    final EditText edtEfeQ = (EditText) dialogView.findViewById(R.id.edtEfeQ);
                    final EditText edttransQ = (EditText) dialogView.findViewById(R.id.edttransQ);
                    final EditText edtNumeroCuenta = (EditText) dialogView.findViewById(R.id.edtNumeroCuenta);
                    final EditText edtchequeQ = (EditText) dialogView.findViewById(R.id.edtchequeQ);
                    final EditText edtchequeN = (EditText) dialogView.findViewById(R.id.edtchequeN);
                    final EditText edtchequeB = (EditText) dialogView.findViewById(R.id.edtchequeB);
                    final TextView txvDesglose = (TextView) dialogView.findViewById(R.id.desglose);



                    dialogBuilder.setTitle(_actual.getRazon());

                    dialogBuilder.setPositiveButton("Pura Vida", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {



                            efectivo = edtEfeQ.getText().toString();
                            transC = edttransQ.getText().toString();
                            transNum = edtNumeroCuenta.getText().toString();
                            chkmont = edtchequeQ.getText().toString();
                            chknum = edtchequeN.getText().toString();
                            chkbank = edtchequeB.getText().toString();



/*
                                            _totalfac = _10r.getTotal() + _20p.getTotal() + _20r.getTotal() + _25r.getTotal() + _25p.getTotal() + _30r.getTotal() +
                                                    _35r.getTotal() + _40r.getTotal() + _45r.getTotal() + _50r.getTotal() + _60r.getTotal() + _100r.getTotal();
                                            _totaldesc = _10r.getDescTotal() + _20p.getDescTotal() + _20r.getDescTotal() + _25r.getDescTotal() + _25p.getDescTotal() + _30r.getDescTotal() +
                                                    _35r.getDescTotal() + _40r.getDescTotal() + _45r.getDescTotal() + _50r.getDescTotal() + _60r.getDescTotal() + _100r.getDescTotal();
*/                            if(_FacCilindro){

                                saveNameToServer(_supervisor+","+ codigocliente +","+myUserName.getText()+"," +(_totalfac-_totaldesc) + ","+_totaldesc
                                        +","+ _actual.getDescuento()+","+_actual.getCredito() +"," + String.valueOf(Q30) + "," + String.valueOf(_20p.getQty())
                                        + "," + String.valueOf(_25p.getQty())+ "," + String.valueOf(_30r.getQty())+ "," + String.valueOf(_40r.getQty())+ "," + String.valueOf(_50r.getQty())
                                        + "," + String.valueOf(_100r.getQty())
                                        + "," + String.valueOf(_10r.getQty()) + "," + String.valueOf(_20r.getQty())+
                                        "," + String.valueOf(_25r.getQty())+ "," + String.valueOf(_35r.getQty())+ "," + String.valueOf(_45r.getQty())+ "," + String.valueOf(_60r.getQty())
                                        + "," + LAT + "," + LONG +","+_CurrentGPS);

                            }else{//GRANEL

                                _totalfac = _lts.getTotal() + _kgs.getTotal();
                                _totaldesc = _lts.getDescTotal() + _kgs.getDescTotal();
                                saveNameToServer(_supervisor+","+ codigocliente +","+myUserName.getText()+"," +(_totalfac-_totaldesc) + ","+_totaldesc
                                        +","+ _actual.getDescuento()+","+_actual.getCredito() +"," + String.valueOf(Q30) + "," + String.valueOf(Q20)
                                        + "," + String.valueOf(_lts.getQty())+ "," + String.valueOf(Q35)+ "," + String.valueOf(Q40r)+ "," + String.valueOf(Q50)
                                        + "," + String.valueOf(Q100r)
                                        + "," + String.valueOf(Q10r) + "," + String.valueOf(Q20r)+
                                        "," + String.valueOf(_kgs.getQty())+ "," + String.valueOf(Q35r)+ "," + String.valueOf(Q45r)+ "," + String.valueOf(Q60r)
                                        + "," + LAT + "," + LONG +","+_CurrentGPS);
                            }




                            codigocliente = -1;


                            resetUI();
                            Toast.makeText(getApplicationContext(),"Factura realizada con exito",Toast.LENGTH_LONG).show();



                            //do something with edt.getText().toString();
                        }
                    });
                    dialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            //pass
                        }
                    });
                    AlertDialog b = dialogBuilder.create();
                    if(_FacCilindro){//CILINDRO

                        fac_detail = "";
                        desc_detail = "";
                        _descCilindro25 = 0;

                        if(Integer.valueOf(_actual.getTipoFactura()) == 3){
                            //Tienda escala + descuento fijo
                            if (_10r.getDesQTY() != null) fac_detail += _10r.getDesQTY() + "\n";
                            if (_20p.getDesQTY() != null) fac_detail += _20p.getDesQTY() + "\n"; if (_20r.getDesQTY() != null) fac_detail += _20r.getDesQTY() + "\n";
                            if (_25p.getDesQTY() != null) fac_detail += _25p.getDesQTY() + "\n"; if (_25r.getDesQTY() != null) fac_detail += _25r.getDesQTY() + "\n";
                            if (_30r.getDesQTY() != null) fac_detail += _30r.getDesQTY() + "\n"; if (_35r.getDesQTY() != null) fac_detail += _35r.getDesQTY() + "\n";
                            if (_40r.getDesQTY() != null) fac_detail += _40r.getDesQTY()+ "\n";  if (_45r.getDesQTY() != null) fac_detail += _45r.getDesQTY()+ "\n";
                            if (_50r.getDesQTY() != null) fac_detail += _50r.getDesQTY()+ "\n";  if (_60r.getDesQTY() != null) fac_detail += _60r.getDesQTY()+ "\n";
                            if (_100r.getDesQTY() != null) fac_detail += _100r.getDesQTY()+ "\n";

                            if (_10r.getDesDESC() != null) desc_detail += _10r.getDesDESC() + "\n";
                            if (_20p.getDesDESC() != null) desc_detail += _20p.getDesDESC()+ "\n";if (_20r.getDesDESC() != null) desc_detail += _20r.getDesDESC()+ "\n";
                            if (_25p.getDesDESC() != null) desc_detail += _25p.getDesDESC()+ "\n";if (_25r.getDesDESC() != null) desc_detail += _25r.getDesDESC()+ "\n";
                            if (_30r.getDesDESC() != null) desc_detail += _30r.getDesDESC()+ "\n";if (_35r.getDesDESC() != null) desc_detail += _35r.getDesDESC()+ "\n";
                            if (_40r.getDesDESC() != null) desc_detail += _40r.getDesDESC()+ "\n";if (_45r.getDesDESC() != null) desc_detail += _45r.getDesDESC()+ "\n";
                            if (_50r.getDesDESC() != null) desc_detail += _50r.getDesDESC()+ "\n";if (_60r.getDesDESC() != null) desc_detail += _60r.getDesDESC()+ "\n";
                            if (_100r.getDesDESC() != null) desc_detail += _100r.getDesDESC()+ "\n";

                            double conso = (_25p.getQty() + _25r.getQty()) + ( _100r.getQty()*4) +
                                    (_20p.getQty() + _20r.getQty()) + (_10r.getQty() *0.4f) +  (_35r.getQty() *1.4f) +
                                    (_40r.getQty() *1.6f) + (_45r.getQty() *1.8f) + (_50r.getQty() *2f) + (_60r.getQty() *2.4f);
                            if((conso) < 10)
                                _descCilindro25 = 1574;
                            if((conso > 10) && (conso < 21))
                                _descCilindro25 = 1754;
                            if((conso > 20) && (conso < 31))
                                _descCilindro25 = 1858;
                            if((conso >= 30) && (conso < 41))
                                _descCilindro25 = 1898;
                            if((conso >= 40) && (conso < 51))
                                _descCilindro25 = 1908;
                            if((conso >= 50) && (conso < 61))
                                _descCilindro25 = 1923;
                            if(conso >= 60)
                                _descCilindro25 = 1933;

                            _totaldesc = Math.round(_totaldesc*100.0)/100.0;
                            _totalfac = Math.round(_totalfac *100.0)/100.0;


                                    /*builder.setMessage("Desglose de factura: " + "\n" + fac_detail + "\n Total Descuento" + String.format("%.2f", _totaldesc) +  "\n TOTAL A FACTURAR:" + "\n" +  String.format("%.2f", _totalfac - _totaldesc) )
                                            .setPositiveButton("Imprimir", dialogClickListener)
                                            .setNegativeButton("Cancelar", dialogClickListener).show();*/

                        }
                        //DESCUENTOS Y PRECIO SEGUN FACTOR NO OLVIDARRRR
                        if(Integer.valueOf(_actual.getTipoFactura()) == 2){
                            //ES TIENDA Descuento fijo
                            if (_10r.getDesQTY() != null) fac_detail += _10r.getDesQTY() + "\n";
                            if (_20p.getDesQTY() != null) fac_detail += _20p.getDesQTY() + "\n";if (_20r.getDesQTY() != null) fac_detail += _20r.getDesQTY() + "\n";
                            if (_25p.getDesQTY() != null) fac_detail += _25p.getDesQTY() + "\n";if (_25r.getDesQTY() != null) fac_detail += _25r.getDesQTY() + "\n";
                            if (_30r.getDesQTY() != null) fac_detail += _30r.getDesQTY() + "\n";if (_35r.getDesQTY() != null) fac_detail += _35r.getDesQTY() + "\n";
                            if (_40r.getDesQTY() != null) fac_detail += _40r.getDesQTY()+ "\n";if (_45r.getDesQTY() != null) fac_detail += _45r.getDesQTY()+ "\n";
                            if (_50r.getDesQTY() != null) fac_detail += _50r.getDesQTY()+ "\n";if (_60r.getDesQTY() != null) fac_detail += _60r.getDesQTY()+ "\n";
                            if (_100r.getDesQTY() != null) fac_detail += _100r.getDesQTY()+ "\n";

                            if (_10r.getDesDESC() != null) desc_detail += _10r.getDesDESC() + "\n";
                            if (_20p.getDesDESC() != null) desc_detail += _20p.getDesDESC()+ "\n";if (_20r.getDesDESC() != null) desc_detail += _20r.getDesDESC()+ "\n";
                            if (_25p.getDesDESC() != null) desc_detail += _25p.getDesDESC()+ "\n";if (_25r.getDesDESC() != null) desc_detail += _25r.getDesDESC()+ "\n";
                            if (_30r.getDesDESC() != null) desc_detail += _30r.getDesDESC()+ "\n";if (_35r.getDesDESC() != null) desc_detail += _35r.getDesDESC()+ "\n";
                            if (_40r.getDesDESC() != null) desc_detail += _40r.getDesDESC()+ "\n";if (_45r.getDesDESC() != null) desc_detail += _45r.getDesDESC()+ "\n";
                            if (_50r.getDesDESC() != null) desc_detail += _50r.getDesDESC()+ "\n";if (_60r.getDesDESC() != null) desc_detail += _60r.getDesDESC()+ "\n";
                            if (_100r.getDesDESC() != null) desc_detail += _100r.getDesDESC()+ "\n";

                            _totalfac = _10r.getQtyTotal() + _20p.getQtyTotal() + _20r.getQtyTotal() + _25r.getQtyTotal() + _25p.getQtyTotal() + _30r.getQtyTotal() +
                                    _35r.getQtyTotal() + _40r.getQtyTotal() + _45r.getQtyTotal() + _50r.getQtyTotal() + _60r.getQtyTotal() + _100r.getQtyTotal();
                            _totaldesc = _10r.getDescTotal() + _20p.getDescTotal() + _20r.getDescTotal() + _25r.getDescTotal() + _25p.getDescTotal() + _30r.getDescTotal() +
                                    _35r.getDescTotal() + _40r.getDescTotal() + _45r.getDescTotal() + _50r.getDescTotal() + _60r.getDescTotal() + _100r.getDescTotal();
                                   /* builder.setMessage("Desglose de factura: " + "\n" + fac_detail + "\n Desglose Descuento" + desc_detail +  "\n TOTAL A FACTURAR:" + "\n" +  String.valueOf(_totalfac - _totaldesc) )
                                            .setPositiveButton("Imprimir", dialogClickListener)
                                            .setNegativeButton("Cancelar", dialogClickListener).show();*/

                        }

                        if(Integer.valueOf(_actual.getTipoFactura()) == 1){
                            //ES TIENDA ESCALA NORMAL


                            double conso = (_25p.getQty() + _25r.getQty()) + ( _100r.getQty()*4) +
                                    ((_20p.getQty() * 0.8f) + (_20r.getQty() *0.8f)) + (_10r.getQty() *0.4f) +  (_35r.getQty() *1.4f) +
                                    (_40r.getQty() *1.6f) + (_45r.getQty() *1.8f) + (_50r.getQty() *2f) + (_60r.getQty() *2.4f) + (_30r.getQty() *1.2f) ;

                            if((conso) < 11)
                                _descCilindro25 = 1574;
                            if((conso > 10) && (conso < 21))
                                _descCilindro25 = 1754;
                            if((conso > 20) && (conso < 31))
                                _descCilindro25 = 1858;
                            if((conso >= 30) && (conso < 41))
                                _descCilindro25 = 1898;
                            if((conso >= 40) && (conso < 51))
                                _descCilindro25 = 1908;
                            if((conso >= 50) && (conso < 61))
                                _descCilindro25 = 1923;
                            if(conso >= 60)
                                _descCilindro25 = 1933;

                            _totalfac = _10r.getQtyTotal() + _20p.getQtyTotal() + _20r.getQtyTotal() + _25r.getQtyTotal() + _25p.getQtyTotal() + _30r.getQtyTotal() +
                                    _35r.getQtyTotal() + _40r.getQtyTotal() + _45r.getQtyTotal() + _50r.getQtyTotal() + _60r.getQtyTotal() + _100r.getQtyTotal();

                            _totaldesc = conso * _descCilindro25;


                            if (_10r.getDesQTY() != null) fac_detail += _10r.getDesQTY() + "\n";
                            if (_20p.getDesQTY() != null) fac_detail += _20p.getDesQTY() + "\n";if (_20r.getDesQTY() != null) fac_detail += _20r.getDesQTY() + "\n";
                            if (_25p.getDesQTY() != null) fac_detail += _25p.getDesQTY() + "\n";if (_25r.getDesQTY() != null) fac_detail += _25r.getDesQTY() + "\n";
                            if (_30r.getDesQTY() != null) fac_detail += _30r.getDesQTY() + "\n";if (_35r.getDesQTY() != null) fac_detail += _35r.getDesQTY() + "\n";
                            if (_40r.getDesQTY() != null) fac_detail += _40r.getDesQTY()+ "\n";if (_45r.getDesQTY() != null) fac_detail += _45r.getDesQTY()+ "\n";
                            if (_50r.getDesQTY() != null) fac_detail += _50r.getDesQTY()+ "\n";if (_60r.getDesQTY() != null) fac_detail += _60r.getDesQTY()+ "\n";
                            if (_100r.getDesQTY() != null) fac_detail += _100r.getDesQTY()+ "\n";


                            if (_10r.getDesDESC() != null) desc_detail += " " + String.valueOf(_10r.getQty()) + " 10 lbs" + String.valueOf(Math.round((_descCilindro25*0.4f)*100.0)/100.0) + "  = " + String.format("%.2f", _10r.getQty()*_descCilindro25*0.4f) + "\n";
                            if (_20p.getDesDESC() != null)desc_detail += " " + String.valueOf(_20p.getQty()) + " 20 lbs " + String.valueOf(Math.round((_descCilindro25*0.8f)*100.0)/100.0) + "  = " + String.format("%.2f", _20p.getQty()*_descCilindro25*0.8f) + "\n";
                            if (_20r.getDesDESC() != null)desc_detail += " " + String.valueOf(_20r.getQty()) + " 20 lbs " + String.valueOf(Math.round((_descCilindro25*0.8f)*100.0)/100.0) + "  = " + String.format("%.2f", _20r.getQty()*_descCilindro25*0.8f) + "\n";
                            if (_25p.getDesDESC() != null) desc_detail +=" " + String.valueOf(_25p.getQty()) + " 25 lbs " + String.valueOf(Math.round((_descCilindro25*1.0f)*100.0)/100.0) + "  = " + String.format("%.2f", _25p.getQty()*_descCilindro25*1.0f) + "\n";
                            if (_25r.getDesDESC() != null)desc_detail += " " + String.valueOf(_25r.getQty()) + " 25 lbs " + String.valueOf(Math.round((_descCilindro25*1.0f)*100.0)/100.0) + "  = " + String.format("%.2f", _25r.getQty()*_descCilindro25*1.0f) + "\n";
                            if (_30r.getDesDESC() != null)desc_detail += " " + String.valueOf(_30r.getQty()) + " 30 lbs" + String.valueOf(Math.round((_descCilindro25*1.2f)*100.0)/100.0) + "  = " + String.format("%.2f", _30r.getQty()*_descCilindro25*1.2f) + "\n";
                            if (_35r.getDesDESC() != null)desc_detail += " " + String.valueOf(_35r.getQty()) + " 35 lbs" + String.valueOf(Math.round((_descCilindro25*1.4f)*100.0)/100.0) + "  = " + String.format("%.2f", _35r.getQty()*_descCilindro25*1.4f) + "\n";
                            if (_40r.getDesDESC() != null)desc_detail += " " + String.valueOf(_40r.getQty()) + " 40 lbs" + String.valueOf(Math.round((_descCilindro25*1.6f)*100.0)/100.0) + "  = " + String.format("%.2f", _40r.getQty()*_descCilindro25*1.6f) + "\n";
                            if (_45r.getDesDESC() != null)desc_detail += " " + String.valueOf(_45r.getQty()) + " 45 lbs" + String.valueOf(Math.round((_descCilindro25*1.8f)*100.0)/100.0) + "  = " + String.format("%.2f", _45r.getQty()*_descCilindro25*1.8f) + "\n";
                            if (_50r.getDesDESC() != null)desc_detail += " " + String.valueOf(_50r.getQty()) + " 50 lbs" + String.valueOf(Math.round((_descCilindro25*2f)*100.0)/100.0)+ "  = " + String.format("%.2f", _50r.getQty()*_descCilindro25*2f) + "\n";
                            if (_60r.getDesDESC() != null)desc_detail += " " + String.valueOf(_60r.getQty()) + " 60 lbs" + String.valueOf(Math.round((_descCilindro25*2.4f)*100.0)/100.0) + "  = " + String.format("%.2f", _60r.getQty()*_descCilindro25*2.4f) + "\n";
                            if (_100r.getDesDESC() != null)desc_detail += " " + String.valueOf(_100r.getQty()) + " 100 lbs" + String.valueOf(Math.round((_descCilindro25*4f)*100.0)/100.0) + "  = " + String.format("%.2f", _100r.getQty()*_descCilindro25*4f) + "\n";
                            _totaldesc = Math.round(_totaldesc*100.0)/100.0;
                            _totalfac = Math.round(_totalfac *100.0)/100.0;


                                  /*  builder.setMessage("Desglose de factura: " + "\n" + fac_detail + "\n Total Descuento" + String.format("%.2f", _totaldesc) +  "\n TOTAL A FACTURAR:" + "\n" +  String.format("%.2f", _totalfac - _totaldesc) )
                                            .setPositiveButton("Imprimir", dialogClickListener)
                                            .setNegativeButton("Cancelar", dialogClickListener).show();*/


                        }


                        if(Integer.valueOf(_actual.getTipoFactura()) == 0){
                            //Precio Agencia
                            //No es tienda
                            if (_10r.getDesQTY() != null) fac_detail += _10r.getDesQTY() + "\n";
                            if (_20p.getDesQTY() != null) fac_detail += _20p.getDesQTY() + "\n";if (_20r.getDesQTY() != null) fac_detail += _20r.getDesQTY() + "\n";
                            if (_25p.getDesQTY() != null) fac_detail += _25p.getDesQTY() + "\n";if (_25r.getDesQTY() != null) fac_detail += _25r.getDesQTY() + "\n";
                            if (_30r.getDesQTY() != null) fac_detail += _30r.getDesQTY() + "\n";if (_35r.getDesQTY() != null) fac_detail += _35r.getDesQTY() + "\n";
                            if (_40r.getDesQTY() != null) fac_detail += _40r.getDesQTY()+ "\n";if (_45r.getDesQTY() != null) fac_detail += _45r.getDesQTY()+ "\n";
                            if (_50r.getDesQTY() != null) fac_detail += _50r.getDesQTY()+ "\n";if (_60r.getDesQTY() != null) fac_detail += _60r.getDesQTY()+ "\n";
                            if (_100r.getDesQTY() != null) fac_detail += _100r.getDesQTY()+ "\n";

                            if (_10r.getDesDESC() != null) desc_detail += _10r.getDesDESC() + "\n";
                            if (_20p.getDesDESC() != null) desc_detail += _20p.getDesDESC()+ "\n";if (_20r.getDesDESC() != null) desc_detail += _20r.getDesDESC()+ "\n";
                            if (_25p.getDesDESC() != null) desc_detail += _25p.getDesDESC()+ "\n";if (_25r.getDesDESC() != null) desc_detail += _25r.getDesDESC()+ "\n";
                            if (_30r.getDesDESC() != null) desc_detail += _30r.getDesDESC()+ "\n";if (_35r.getDesDESC() != null) desc_detail += _35r.getDesDESC()+ "\n";
                            if (_40r.getDesDESC() != null) desc_detail += _40r.getDesDESC()+ "\n";if (_45r.getDesDESC() != null) desc_detail += _45r.getDesDESC()+ "\n";
                            if (_50r.getDesDESC() != null) desc_detail += _50r.getDesDESC()+ "\n";if (_60r.getDesDESC() != null) desc_detail += _60r.getDesDESC()+ "\n";
                            if (_100r.getDesDESC() != null) desc_detail += _100r.getDesDESC()+ "\n";
                            _descCilindro25 = 0;

                            _totalfac = _10r.getQtyTotal() + _20p.getQtyTotal() + _20r.getQtyTotal() + _25r.getQtyTotal() + _25p.getQtyTotal() + _30r.getQtyTotal() +
                                    _35r.getQtyTotal() + _40r.getQtyTotal() + _45r.getQtyTotal() + _50r.getQtyTotal() + _60r.getQtyTotal() + _100r.getQtyTotal();
                            _totaldesc = _10r.getDescTotal() + _20p.getDescTotal() + _20r.getDescTotal() + _25r.getDescTotal() + _25p.getDescTotal() + _30r.getDescTotal() +
                                    _35r.getDescTotal() + _40r.getDescTotal() + _45r.getDescTotal() + _50r.getDescTotal() + _60r.getDescTotal() + _100r.getDescTotal();
                               /*     builder.setMessage("Desglose de factura: " + "\n" + fac_detail + "\n Desglose Descuento" + desc_detail +  "\n TOTAL A FACTURAR:" + "\n" +  String.valueOf(_totalfac - _totaldesc) )
                                            .setPositiveButton("Imprimir", dialogClickListener)
                                            .setNegativeButton("Cancelar", dialogClickListener).show();*/
                        }

                        if(Integer.valueOf(_actual.getTipoFactura()) == 4){
                            //Factura Especial
                            //No es tienda
                            if (_10r.getDesQTY() != null) fac_detail += _10r.getDesQTY() + "\n";
                            if (_20p.getDesQTY() != null) fac_detail += _20p.getDesQTY() + "\n";if (_20r.getDesQTY() != null) fac_detail += _20r.getDesQTY() + "\n";
                            if (_25p.getDesQTY() != null) fac_detail += _25p.getDesQTY() + "\n";if (_25r.getDesQTY() != null) fac_detail += _25r.getDesQTY() + "\n";
                            if (_30r.getDesQTY() != null) fac_detail += _30r.getDesQTY() + "\n";if (_35r.getDesQTY() != null) fac_detail += _35r.getDesQTY() + "\n";
                            if (_40r.getDesQTY() != null) fac_detail += _40r.getDesQTY()+ "\n";if (_45r.getDesQTY() != null) fac_detail += _45r.getDesQTY()+ "\n";
                            if (_50r.getDesQTY() != null) fac_detail += _50r.getDesQTY()+ "\n";if (_60r.getDesQTY() != null) fac_detail += _60r.getDesQTY()+ "\n";
                            if (_100r.getDesQTY() != null) fac_detail += _100r.getDesQTY()+ "\n";

                            if (_10r.getDesDESC() != null) desc_detail += _10r.getDesDESC() + "\n";
                            if (_20p.getDesDESC() != null) desc_detail += _20p.getDesDESC()+ "\n";if (_20r.getDesDESC() != null) desc_detail += _20r.getDesDESC()+ "\n";
                            if (_25p.getDesDESC() != null) desc_detail += _25p.getDesDESC()+ "\n";if (_25r.getDesDESC() != null) desc_detail += _25r.getDesDESC()+ "\n";
                            if (_30r.getDesDESC() != null) desc_detail += _30r.getDesDESC()+ "\n";if (_35r.getDesDESC() != null) desc_detail += _35r.getDesDESC()+ "\n";
                            if (_40r.getDesDESC() != null) desc_detail += _40r.getDesDESC()+ "\n";if (_45r.getDesDESC() != null) desc_detail += _45r.getDesDESC()+ "\n";
                            if (_50r.getDesDESC() != null) desc_detail += _50r.getDesDESC()+ "\n";if (_60r.getDesDESC() != null) desc_detail += _60r.getDesDESC()+ "\n";
                            if (_100r.getDesDESC() != null) desc_detail += _100r.getDesDESC()+ "\n";
                            _descCilindro25 = 0;

                            _totalfac = _10r.getTotal() + _20p.getTotal() + _20r.getTotal() + _25r.getTotal() + _25p.getTotal() + _30r.getTotal() +
                                    _35r.getTotal() + _40r.getTotal() + _45r.getTotal() + _50r.getTotal() + _60r.getTotal() + _100r.getTotal();
                            // _totaldesc = _10r.getDescTotal() + _20p.getDescTotal() + _20r.getDescTotal() + _25r.getDescTotal() + _25p.getDescTotal() + _30r.getDescTotal() +
                            //       _35r.getDescTotal() + _40r.getDescTotal() + _45r.getDescTotal() + _50r.getDescTotal() + _60r.getDescTotal() + _100r.getDescTotal();
                                 /*   builder.setMessage("Desglose de factura: " + "\n" + fac_detail +  "\n TOTAL A FACTURAR:" + "\n" +  String.valueOf(_totalfac ) )
                                            .setPositiveButton("Imprimir", dialogClickListener)
                                            .setNegativeButton("Cancelar", dialogClickListener).show();*/
                        }


                        if (Integer.valueOf(_actual.getTipoFactura()) == 5) {
                            //Factura Especial (Con descuentos 35 y 45 especial)
                            //No es tienda
                            if (_10r.getDesQTY() != null) fac_detail += _10r.getDesQTY() + "\n";
                            if (_20p.getDesQTY() != null) fac_detail += _20p.getDesQTY() + "\n";if (_20r.getDesQTY() != null) fac_detail += _20r.getDesQTY() + "\n";
                            if (_25p.getDesQTY() != null) fac_detail += _25p.getDesQTY() + "\n";if (_25r.getDesQTY() != null) fac_detail += _25r.getDesQTY() + "\n";
                            if (_30r.getDesQTY() != null) fac_detail += _30r.getDesQTY() + "\n";if (_35r.getDesQTY() != null) fac_detail += _35r.getDesQTY() + "\n";
                            if (_40r.getDesQTY() != null) fac_detail += _40r.getDesQTY()+ "\n";if (_45r.getDesQTY() != null) fac_detail += _45r.getDesQTY()+ "\n";
                            if (_50r.getDesQTY() != null) fac_detail += _50r.getDesQTY()+ "\n";if (_60r.getDesQTY() != null) fac_detail += _60r.getDesQTY()+ "\n";
                            if (_100r.getDesQTY() != null) fac_detail += _100r.getDesQTY()+ "\n";

                            if (_10r.getDesDESC() != null) desc_detail += _10r.getDesDESC() + "\n";
                            if (_20p.getDesDESC() != null) desc_detail += _20p.getDesDESC()+ "\n";if (_20r.getDesDESC() != null) desc_detail += _20r.getDesDESC()+ "\n";
                            if (_25p.getDesDESC() != null) desc_detail += _25p.getDesDESC()+ "\n";if (_25r.getDesDESC() != null) desc_detail += _25r.getDesDESC()+ "\n";
                            if (_30r.getDesDESC() != null) desc_detail += _30r.getDesDESC()+ "\n";if (_35r.getDesDESC() != null) desc_detail += _35r.getDesDESC()+ "\n";
                            if (_40r.getDesDESC() != null) desc_detail += _40r.getDesDESC()+ "\n";if (_45r.getDesDESC() != null) desc_detail += _45r.getDesDESC()+ "\n";
                            if (_50r.getDesDESC() != null) desc_detail += _50r.getDesDESC()+ "\n";if (_60r.getDesDESC() != null) desc_detail += _60r.getDesDESC()+ "\n";
                            if (_100r.getDesDESC() != null) desc_detail += _100r.getDesDESC()+ "\n";
                            _descCilindro25 = 0;

                            _totalfac = _10r.getTotal() + _20p.getTotal() + _20r.getTotal() + _25r.getTotal() + _25p.getTotal() + _30r.getTotal() +
                                    _35r.getTotal() + _40r.getTotal() + _45r.getTotal() + _50r.getTotal() + _60r.getTotal() + _100r.getTotal();
                            _totaldesc = _10r.getDescTotal() + _20p.getDescTotal() + _20r.getDescTotal() + _25r.getDescTotal() + _25p.getDescTotal() + _30r.getDescTotal() +
                                    _35r.getDescTotal() + _40r.getDescTotal() + _45r.getDescTotal() + _50r.getDescTotal() + _60r.getDescTotal() + _100r.getDescTotal();
                                   /* builder.setMessage("Desglose de factura: " + "\n" + fac_detail + "\n TOTAL A FACTURAR:" + "\n" +  String.valueOf(_totalfac) )
                                            .setPositiveButton("Imprimir", dialogClickListener)
                                            .setNegativeButton("Cancelar", dialogClickListener).show();*/

                        }

                    edtEfeQ.setText(String.valueOf(_totalfac-_totaldesc) );
                    txvDesglose.setText("Desglose de factura: " + "\n" + fac_detail + "\n Desglose Descuento" + "\n" + desc_detail +  "\n TOTAL A FACTURAR:" + "\n" +  String.valueOf(_totalfac - _totaldesc));
                    b.show();
/****************************************************************************************************/
                    }else{
                        if(_lts.getQty() > 0){
                            edtEfeQ.setText(String.valueOf(_totalfac-_totaldesc) );
                            txvDesglose.setText("Desglose de factura: " + "\n" + _lts.getDesQTY() +  "\n Desglose Descuento:" + "\n" + _lts.getDesDESC() +  "\n TOTAL A FACTURAR:" + "\n" +  _lts.getTotal().toString() );
                            b.show();
                        }

                        if(_kgs.getQty() > 0){
                            edtEfeQ.setText(String.valueOf(_totalfac-_totaldesc) );
                            txvDesglose.setText("Desglose de factura: " + "\n"  + _kgs.getDesQTY()  +  "\n Desglose Descuento:" + "\n"  + _kgs.getDesDESC() +  "\n TOTAL A FACTURAR:" + "\n" +  _kgs.getTotal().toString());
                            b.show();

                        }

                    }


                }//END IF
                else{
                    Toast.makeText( getApplicationContext(),"Presione el boton Detectar para selecionar cliente",Toast.LENGTH_LONG).show();
                }



                }




        });


        myUserName = (EditText)findViewById(R.id.txtUserName); //Boton menos
        m_substract.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
        m_i50.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _SelectedOption = 250;
                mTextView.setText("");
                m_i20.setChecked(false);m_i25.setChecked(false);
               m_lts.setChecked(false);m_kgs.setChecked(false);
                 m_i50.setChecked(true); m_i30r.setChecked(false);
                m_i20r.setChecked(false);m_i25r.setChecked(false);m_i60r.setChecked(false);
                m_i35r.setChecked(false);m_i45r.setChecked(false);m_i100r.setChecked(false);
                m_i10r.setChecked(false);m_i40r.setChecked(false);
                addQuantity(_50r,m_i50);
            }
        });
        m_i30r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _SelectedOption = 230;
                mTextView.setText("");
                m_i20.setChecked(false);m_i25.setChecked(false);
               m_lts.setChecked(false);
                m_kgs.setChecked(false);
                 m_i50.setChecked(false); m_i30r.setChecked(true);
                m_i20r.setChecked(false);m_i25r.setChecked(false);m_i60r.setChecked(false);
                m_i35r.setChecked(false);m_i45r.setChecked(false);m_i100r.setChecked(false);
                m_i10r.setChecked(false);m_i40r.setChecked(false);
                addQuantity(_30r,m_i30r);
            }
        });
        m_i10r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _SelectedOption = 70;
                mTextView.setText("");
                m_i20.setChecked(false);m_i25.setChecked(false);
               m_lts.setChecked(false);
                m_kgs.setChecked(false);

                m_i20r.setChecked(false);m_i25r.setChecked(false);m_i60r.setChecked(false);
                m_i35r.setChecked(false);m_i45r.setChecked(false);m_i100r.setChecked(false);
                m_i10r.setChecked(true);m_i40r.setChecked(false);m_i50.setChecked(false); m_i30r.setChecked(false);
                addQuantity(_10r,m_i10r);
            }
        });

        m_i40r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _SelectedOption = 80;
                mTextView.setText("");
                m_i20.setChecked(false);m_i25.setChecked(false);
               m_lts.setChecked(false);
                m_kgs.setChecked(false);

                m_i20r.setChecked(false);m_i25r.setChecked(false);m_i60r.setChecked(false);
                m_i35r.setChecked(false);m_i45r.setChecked(false);m_i100r.setChecked(false);
                m_i10r.setChecked(false);m_i40r.setChecked(true);m_i50.setChecked(false);
                addQuantity(_40r,m_i40r);
            }
        });

        m_i60r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _SelectedOption = 90;
                mTextView.setText("");
                m_i20.setChecked(false);m_i25.setChecked(false);
                m_lts.setChecked(false);
                m_kgs.setChecked(false);

                m_i20r.setChecked(false);m_i25r.setChecked(false);
                m_i35r.setChecked(false);m_i45r.setChecked(false);m_i100r.setChecked(false);
                m_i10r.setChecked(false);m_i40r.setChecked(false);m_i60r.setChecked(true);m_i50.setChecked(false); m_i30r.setChecked(false);
                addQuantity(_60r,m_i60r);
            }
        });

        m_i20.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _SelectedOption = 1;
                mTextView.setText("");
                m_i20.setChecked(true);m_i25.setChecked(false);
               m_lts.setChecked(false);
                m_kgs.setChecked(false);

                m_i20r.setChecked(false);m_i25r.setChecked(false);m_i60r.setChecked(false);
                m_i35r.setChecked(false);m_i45r.setChecked(false);m_i100r.setChecked(false);
                m_i10r.setChecked(false);m_i40r.setChecked(false);m_i50.setChecked(false); m_i30r.setChecked(false);
                addQuantity(_20p,m_i20);
            }
        });
        m_i20r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _SelectedOption = 10;
                mTextView.setText("");
                m_i20.setChecked(false);m_i25.setChecked(false);
                m_lts.setChecked(false);
                m_kgs.setChecked(false);

                m_i20r.setChecked(true);m_i25r.setChecked(false);m_i60r.setChecked(false);
                m_i35r.setChecked(false);m_i45r.setChecked(false);m_i100r.setChecked(false);
                m_i10r.setChecked(false);m_i40r.setChecked(false);m_i50.setChecked(false); m_i30r.setChecked(false);
                addQuantity(_20r,m_i20r);
            }
        });
        m_i25.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _SelectedOption = 0;
                mTextView.setText("");
                m_i20.setChecked(false);m_i25.setChecked(true);
                m_lts.setChecked(false);
                m_kgs.setChecked(false);

                m_i20r.setChecked(false);m_i25r.setChecked(false);m_i60r.setChecked(false);
                m_i35r.setChecked(false);m_i45r.setChecked(false);m_i100r.setChecked(false);
                m_i10r.setChecked(false);m_i40r.setChecked(false);m_i50.setChecked(false); m_i30r.setChecked(false);
                addQuantity(_25p,m_i25);
            }
        });
        m_i25r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _SelectedOption = 125;
                mTextView.setText("");
                m_i20.setChecked(false);m_i25.setChecked(false);
                m_lts.setChecked(false);
                m_kgs.setChecked(false);

                m_i20r.setChecked(false);m_i25r.setChecked(true);m_i60r.setChecked(false);
                m_i35r.setChecked(false);m_i45r.setChecked(false);m_i100r.setChecked(false);
                m_i10r.setChecked(false);m_i40r.setChecked(false);m_i50.setChecked(false); m_i30r.setChecked(false);
                addQuantity(_25r,m_i25r);
            }
        });

        m_i35r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _SelectedOption = 20;
                mTextView.setText("");
                m_i20.setChecked(false);m_i25.setChecked(false);
                m_lts.setChecked(false);
                m_kgs.setChecked(false);

                m_i20r.setChecked(false);m_i25r.setChecked(false);m_i60r.setChecked(false);
                m_i35r.setChecked(true);m_i45r.setChecked(false);m_i100r.setChecked(false);
                m_i10r.setChecked(false);m_i40r.setChecked(false);m_i50.setChecked(false); m_i30r.setChecked(false);
                addQuantity(_35r,m_i35r);
            }
        });

        m_i45r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _SelectedOption = 30;
                mTextView.setText("");
                m_i20.setChecked(false);m_i25.setChecked(false);
                m_lts.setChecked(false);
                m_kgs.setChecked(false);

                m_i20r.setChecked(false);m_i25r.setChecked(false);m_i60r.setChecked(false);
                m_i35r.setChecked(false);m_i45r.setChecked(true);m_i100r.setChecked(false);
                m_i10r.setChecked(false);m_i40r.setChecked(false);m_i50.setChecked(false); m_i30r.setChecked(false);
                addQuantity(_45r,m_i45r);
            }
        });

        m_i100r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _SelectedOption = 40;
                mTextView.setText("");
                m_i20.setChecked(false);m_i25.setChecked(false);
               m_lts.setChecked(false);
                m_kgs.setChecked(false);
               m_i50.setChecked(false); m_i30r.setChecked(false);
                m_i20r.setChecked(false);m_i25r.setChecked(false);m_i60r.setChecked(false);
                m_i35r.setChecked(false);m_i45r.setChecked(false);m_i100r.setChecked(true);
                m_i10r.setChecked(false);
                addQuantity(_100r,m_i100r);
            }
        });
        m_lts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _SelectedOption = 5;
                mTextView.setText("");
                m_i20.setChecked(false);m_i25.setChecked(false);
               m_lts.setChecked(true);
                m_kgs.setChecked(false);
              m_i50.setChecked(false); m_i30r.setChecked(false);
                m_i20r.setChecked(false);m_i25r.setChecked(false);m_i60r.setChecked(false);
                m_i35r.setChecked(false);m_i45r.setChecked(false);m_i100r.setChecked(false);
                m_i10r.setChecked(false);m_i40r.setChecked(false);
                addQuantity(_lts,m_lts);
            }
        });
        m_kgs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                _SelectedOption = 6;
                mTextView.setText("");
                m_i20.setChecked(false);m_i25.setChecked(false);
                m_lts.setChecked(false);
                m_kgs.setChecked(true);
                m_i50.setChecked(false); m_i30r.setChecked(false);
                m_i20r.setChecked(false);m_i25r.setChecked(false);m_i60r.setChecked(false);
                m_i35r.setChecked(false);m_i45r.setChecked(false);m_i100r.setChecked(false);
                m_i10r.setChecked(false);m_i40r.setChecked(false);
                addQuantity(_kgs,m_kgs);
            }
        });



        c = new LatLon2MGRS();

        //buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonAdd = (Button) findViewById(R.id.buttonAddCliente);



        listViewNames = (ListView) findViewById(R.id.listViewNames);
        listViewNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList =(listViewNames.getItemAtPosition(position).toString());
                Name data =  (Name) parent.getItemAtPosition(position);
                String[] tokens = data.getName().split(",");

                if(_FacCilindro){
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                    final View dialogView = inflater.inflate(R.layout.tipopago, null);
                    dialogBuilder.setView(dialogView);

                    final EditText edtEfeQ = (EditText) dialogView.findViewById(R.id.edtEfeQ);
                    final EditText edttransQ = (EditText) dialogView.findViewById(R.id.edttransQ);
                    final EditText edtNumeroCuenta = (EditText) dialogView.findViewById(R.id.edtNumeroCuenta);
                    final EditText edtchequeQ = (EditText) dialogView.findViewById(R.id.edtchequeQ);
                    final EditText edtchequeN = (EditText) dialogView.findViewById(R.id.edtchequeN);
                    final EditText edtchequeB = (EditText) dialogView.findViewById(R.id.edtchequeB);
                    final TextView txvDesglose = (TextView) dialogView.findViewById(R.id.desglose);



                    dialogBuilder.setTitle(tokens[2]);
                    dialogBuilder.setPositiveButton("Pura Vida", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    });
                    dialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            //pass
                        }
                    });
                    AlertDialog b = dialogBuilder.create();

                    String _dat = "Total factura: " + "\n" + tokens[3] + "\n Total Descuento:" + tokens[4]  + "\n" ;

                    if (!tokens[6].equals("0")) _dat += "Contado"  + "\n"; else _dat += "Credito" + "\n";
                    //if (tokens[7] != "0") _dat += "30 Lbs :" + "\n"; //Libre recarga
                    if (!tokens[8].equals("0"))  _dat += "20 Lbs :" + tokens[8] +"\n";
                    if (!tokens[9].equals("0"))  _dat += "25 Lbs Presion :" +tokens[9] + "\n";
                    if (!tokens[10].equals("0")) _dat += "30 Lbs Rosca :" +tokens[10] + "\n";
                    if (!tokens[11].equals("0")) _dat += "40 Lbs Rosca :" +tokens[11] + "\n";
                    if (!tokens[12].equals("0")) _dat += "50 Lbs Rosca :" +tokens[12] + "\n";
                    if (!tokens[13].equals("0")) _dat += "100 Lbs Rosca :" +tokens[13] + "\n";
                    if (!tokens[14].equals("0")) _dat += "10 Lbs Rosca :" +tokens[14] + "\n";
                    if (!tokens[15].equals("0")) _dat += "20 Lbs Rosca :" +tokens[15] + "\n";
                    if (!tokens[16].equals("0")) _dat += "25 Lbs Rosca :" +tokens[16] + "\n";
                    if (!tokens[17].equals("0")) _dat += "35 Lbs Rosca :" +tokens[17] + "\n";
                    if (!tokens[18].equals("0")) _dat += "45 Lbs Rosca :" +tokens[18] + "\n";
                    if (!tokens[19].equals("0")) _dat += "60 Lbs Rosca :" +tokens[19] + "\n";
                    if (!tokens[24].equals("0")) edtEfeQ.setText(tokens[24]);
                    if (!tokens[25].equals("0")) edttransQ.setText(tokens[25]);
                    if (!tokens[26].equals("0")) edtNumeroCuenta .setText(tokens[26]);
                    if (!tokens[27].equals("0")) edtchequeN.setText(tokens[27]);
                    if (!tokens[28].equals("0")) edtchequeB.setText(tokens[28]);
                    if (!tokens[29].equals("0")) edtchequeQ.setText(tokens[29]);

                    /*_supervisor+","+ codigocliente +","+myUserName.getText()+"," +(_totalfac-_totaldesc) + ","+_totaldesc
                            +","+ _actual.getDe5scuento()+","+_actual.getCr6edito() +"," + String.value7Of(Q30) + "," + String.val8ueOf(_20p.getQty())
                            + "," + String.val9ueOf(_25p.getQty())+ "," + String.value10Of(Q35)+ "," + Stri11ng.valueOf(_40r.getQty())+ "," + Strin12g.valueOf(_50r.getQty())
                            + "," + String.valueOf(_100r.getQty())
                            + "," + String.valueOf(_10r.getQty()) + "," + String.valueOf(_20r.getQty())+
                            "," + String.valueOf(_25r.getQty())+ "," + String.valueOf(_35r.getQty())+ "," + String.valueOf(_45r.getQty())+ "," + String.valu19eOf(_60r.getQty())
                            + "," + LAT20 + "," + LONG21 +","+_Current22GPS+","+ fec23ha + ","+ efect24ivo + ","+
                            transC + ","+ transNum + ","+ chknum+ ","+ chkbank + ","+ chkmont;*/


                    txvDesglose.setText(_dat);

                    b.show();



                }else{//GRANEL

                    _totalfac = _lts.getTotal() + _kgs.getTotal();
                    _totaldesc = _lts.getDescTotal() + _kgs.getDescTotal();
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                    final View dialogView = inflater.inflate(R.layout.tipopago, null);
                    dialogBuilder.setView(dialogView);

                    final EditText edtEfeQ = (EditText) dialogView.findViewById(R.id.edtEfeQ);
                    final EditText edttransQ = (EditText) dialogView.findViewById(R.id.edttransQ);
                    final EditText edtNumeroCuenta = (EditText) dialogView.findViewById(R.id.edtNumeroCuenta);
                    final EditText edtchequeQ = (EditText) dialogView.findViewById(R.id.edtchequeQ);
                    final EditText edtchequeN = (EditText) dialogView.findViewById(R.id.edtchequeN);
                    final EditText edtchequeB = (EditText) dialogView.findViewById(R.id.edtchequeB);
                    final TextView txvDesglose = (TextView) dialogView.findViewById(R.id.desglose);



                    dialogBuilder.setTitle(tokens[2]);
                    dialogBuilder.setPositiveButton("Pura Vida", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    });
                    dialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            //pass
                        }
                    });
                    AlertDialog b = dialogBuilder.create();

                    String _dat = "Total factura: " + "\n" + tokens[3] + "\n Total Descuento:" + tokens[4]  + "\n" ;

                    if (!tokens[6].equals("0")) _dat += "Contado"  + "\n"; else _dat += "Credito" + "\n";
                    //if (tokens[7] != "0") _dat += "30 Lbs :" + "\n"; //Libre recarga
                    if (!tokens[9].equals("0"))  _dat += "Litros :" + tokens[9] +"\n";
                    if (!tokens[16].equals("0"))  _dat += "Kilogramos :" +tokens[16] + "\n";
                    if (!tokens[17].equals("0")) _dat += "Litros Consignacion :" +tokens[17] + "\n";

                    if (!tokens[24].equals("0")) edtEfeQ.setText(tokens[24]);
                    if (!tokens[25].equals("0")) edttransQ.setText(tokens[25]);
                    if (!tokens[26].equals("0")) edtNumeroCuenta .setText(tokens[26]);
                    if (!tokens[27].equals("0")) edtchequeN.setText(tokens[27]);
                    if (!tokens[28].equals("0")) edtchequeB.setText(tokens[28]);
                    if (!tokens[29].equals("0")) edtchequeQ.setText(tokens[29]);

                   /* saveNameToServer(_supervisor+","+ codigocliente +","+myUserName.getText()+"," +(_totalfac-_totaldesc) + ","+_totaldesc
                            +","+ _actual.getDescuento()+","+_actual.getCredito() +"," + String.valueOf(Q30) + "," + String.valueOf(Q20)
                            + "," + String.va9lueOf(_lts.getQty())+ "," + String.va10lueOf(Q35)+ "," + String.va11lueOf(Q40r)+ "," + St12ring.valueOf(Q50)
                            + "," + String.valu13eOf(Q100r)
                            + "," + String.valu14eOf(Q10r) + "," + String.va15lueOf(Q20r)+
                            "," + String.valu16eOf(_kgs.getQty())+ "," + String.valueOf(Q35r)+ "," + String.valueOf(Q45r)+ "," + String.valueOf(Q60r)
                            + "," + LAT + "," + LONG +","+_CurrentGPS);*/


                    txvDesglose.setText(_dat);

                    b.show();


                }



                Toast.makeText(MainActivity.this, data.getName() , Toast.LENGTH_SHORT).show();


            }
        });







        //Detectar BOTON
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                //codigocliente = getClienteFromGPS(_TRON8);
                /*
                usardos = 0;
                if (getClienteFromGPS(_TRON1) == -1){
                    codigocliente = getClienteFromGPS(_TRON1_2);
                    charSequences.add(new String(_ruta1_2[codigocliente]));
                }else{
                    codigocliente = getClienteFromGPS(_TRON1);
                    charSequences.add(new String(_ruta1[codigocliente]));
                    usardos = 1;
                }*/


                //db.addCliente("NERD","250","0","NOTHIM","0","7","5","1","0","9.86547841","-84.978545","JPHL7685","1","1","1",0);

                getClienteFromGPS();



                if (clienteFromdb.size() > 0){
                    final CharSequence[] options = clienteFromdb.toArray(new CharSequence[clienteFromdb.size()]);



                clienteFromdb.clear();

                //CharSequence options[] = new CharSequence[] {"Call", "SMS", "Email"};

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                //builder.setCancelable(false);
                builder.setTitle(_supervisor.toString() + " Seleccione un cliente:");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] index = indexes.toArray(new String[indexes.size()]);
                        //String[] index2 = indexes2.toArray(new String[indexes2.size()]);
                        String _sel = String.valueOf(options[which]);

                            codigocliente = Integer.parseInt(index[which]);
                            loadCliente(codigocliente);



                        myUserName.setText(_actual.getRazon());
                        indexes.clear();






                        // the user clicked on options[which]
                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //the user clicked on Cancel
                    }
                });
                builder.show();
                }else{
                    Toast.makeText(getApplicationContext(),_CurrentGPS + "No se han detectado clientes",Toast.LENGTH_SHORT).show();

                }


                resetUI();

            }
        });

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //txtCoords.setText(String.valueOf(location.getLatitude()) + " " + String.valueOf(location.getLongitude()));
                LAT = String.valueOf(location.getLatitude());
                LONG = String.valueOf(location.getLongitude());

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        };


        registerReceiver(new NetworkStateChecker(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));



        //adding click listener to button
        //buttonSave.setOnClickListener(this);

        //calling the method to load all the stored names
        loadNames();

        //the broadcast receiver to update sync status
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                //loading the names again
                loadNames();
            }
        };

        //registering the broadcast receiver to update sync status
        registerReceiver(broadcastReceiver, new IntentFilter(DATA_SAVED_BROADCAST));
    }

    public void addQuantity(final catalogo _zPre,final RadioButton _sd){


    if (codigocliente == -1){
        Toast.makeText(getApplicationContext(),"Selecciona un cliente antes de facturar",Toast.LENGTH_LONG).show();
        return;
    }


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.cargar, null);
        dialogBuilder.setView(dialogView);

        final ToggleButton _tglprecio = (ToggleButton) dialogView.findViewById(R.id.tglprecio);
        final ToggleButton _tgldesc = (ToggleButton) dialogView.findViewById(R.id.tglDesc);
        final TextView _presentacion = (TextView)  dialogView.findViewById(R.id.lblPresentacion);
        final TextView _lblprice = (TextView)  dialogView.findViewById(R.id.lblPrice);
        final TextView _lbldesc = (TextView)  dialogView.findViewById(R.id.lblDesc);
        final TextView _lbltotal = (TextView)  dialogView.findViewById(R.id.lbltotal);
        final EditText _qt = (EditText) dialogView.findViewById(R.id.txtQuantity);

        _presentacion.setText(_zPre.getLabel());
        if(_zPre.get_actual() != null){
            _lblprice.setText("Precio: " + _zPre.getPrecioSel().toString());
            _lbldesc.setText("Descuento: " + _zPre.getDescuentoSel().toString());
            _lbltotal.setText("Resultado: " +  String.format("%.2f", _zPre.getTotal()));
        }




        _qt.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {


                if (actionId == EditorInfo.IME_ACTION_DONE) { //IME_ACTION_DONE para cerrar editor

                   if (_qt.getText().length() > 0){
                       _zPre.set_actual(_actual);
                       _zPre.agregarProducto(_tgldesc.isChecked(),_tglprecio.isChecked(),Integer.valueOf(_qt.getText().toString()));
                       _lblprice.setText("Precio: " + _zPre.getPrecioSel().toString());
                       _lbldesc.setText("Descuento: " + _zPre.getDescuentoSel().toString());
                       _lbltotal.setText("Resultado: " +  String.format("%.2f", _zPre.getTotal()));



                   }

                    InputMethodManager inputManager = (InputMethodManager)
                            getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.toggleSoftInput(0, 0);
                    return true;
                }

                return false;
            }
        });

        _tgldesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_qt.getText().length() > 0){
                    _zPre.set_actual(_actual);
                    _zPre.agregarProducto(_tgldesc.isChecked(),_tglprecio.isChecked(),Integer.valueOf(_qt.getText().toString()));
                    _lblprice.setText("Precio: " + _zPre.getPrecioSel().toString());
                    _lbldesc.setText("Descuento: " + _zPre.getDescuentoSel().toString());
                    _lbltotal.setText("Resultado: " + _zPre.getTotal().toString());
                }
            }
                            });
        _tglprecio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_qt.getText().length() > 0){
                    _zPre.set_actual(_actual);
                    _zPre.agregarProducto(_tgldesc.isChecked(),_tglprecio.isChecked(),Integer.valueOf(_qt.getText().toString()));
                    _lblprice.setText("Precio: " + _zPre.getPrecioSel().toString());
                    _lbldesc.setText("Descuento: " + _zPre.getDescuentoSel().toString());
                    _lbltotal.setText("Resultado: " + _zPre.getTotal().toString());
                }
            }
        });






        //dialogBuilder.setTitle("Cargar Cantidad");
        //dialogBuilder.setMessage("Introduce la cantidad a facturar");
        dialogBuilder.setPositiveButton("Pura Vida", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            _sd.setText(String.valueOf(_zPre.lbs) +"x"+ String.valueOf(_zPre.getQty()) );
                if (_qt.getText().length() > 0){
                    _zPre.set_actual(_actual);
                    _zPre.agregarProducto(_tgldesc.isChecked(),_tglprecio.isChecked(),Integer.valueOf(_qt.getText().toString()));
                }





                //do something with edt.getText().toString();
            }
        });
        dialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
        _qt.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

    }
    public void customDialog(){





        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView;
        if(_FacCilindro)
        dialogView = inflater.inflate(R.layout.comodato, null);
        else
            dialogView = inflater.inflate(R.layout.clienteg , null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.editNombre);
        final EditText edt2 = (EditText) dialogView.findViewById(R.id.editRazon);
        final EditText edt3 = (EditText) dialogView.findViewById(R.id.editDescuento);
        //final EditText edt7 = (EditText) dialogView.findViewById(R.id.editFormadepago);
        final Spinner spiTipo = (Spinner) dialogView.findViewById(R.id.spinner);

       // final Button pic = (Button) dialogView.findViewById(R.id.buttonFoto);

        //_img = (ImageView) dialogView.findViewById(R.id.img);
/*

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(getApplicationContext(),"Hi",Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //startActivityForResult(intent,0);

                //_comodato += edt.getText().toString() + "," + edt2.getText().toString() + "," + edt3.getText().toString()+",";

            }


        });*/





        dialogBuilder.setTitle("Agregar Cliente");
        dialogBuilder.setMessage("Puedes agregar nuevo cliente aqui");
        dialogBuilder.setPositiveButton("Pura Vida", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String _tiposub = "0";
                if (spiTipo.getSelectedItemPosition() == 0) _tiposub = "0";
                else _tiposub = "4";


                if (!edt.getText().toString().isEmpty() && !edt2.getText().toString().isEmpty() &&
                        !edt3.getText().toString().isEmpty()){
                    if (!LAT.equals("-10000")){
                        String _tt = c.convertLatLonToMGRS(Double.parseDouble(LAT),Double.parseDouble(LONG));

                        _tt = _tt.replace(" ","");
                        if(_FacCilindro)//cliente cilindros
                        saveClienteToServer(edt.getText().toString(), edt3.getText().toString(), _supervisor,edt2.getText().toString() , "1",
                                _tiposub,edt3.getText().toString(),"0","0",LAT,LONG, (_tt.substring(0,7) + _tt.substring(10,12)),
                                "0","1",String.valueOf(spiTipo.getSelectedItemPosition()));
                        else //granel
                        saveClienteToServer(edt.getText().toString(), edt3.getText().toString(), _supervisor,edt2.getText().toString() , String.valueOf(spiTipo.getSelectedItemPosition()),
                                _tiposub,edt3.getText().toString(),"0","0",LAT,LONG, (_tt.substring(0,7) + _tt.substring(10,12)),
                                "0","1","0");

                        Toast.makeText(getApplicationContext(),"Cliente Agregado Correctamente",Toast.LENGTH_SHORT).show();
                    }

                    else
                        Toast.makeText(getApplicationContext(),"GPS no detectado, intentar otra vez",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"No se agrego, asegurate que ningun campo este vacio",Toast.LENGTH_LONG).show();
                }


                //do something with edt.getText().toString();
            }
        });
        dialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();

    }
    public void custombilletes(){





        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.billetes, null);
        dialogBuilder.setView(dialogView);
        final EditText edt50k = (EditText) dialogView.findViewById(R.id.edt50k);
        final EditText edt20k = (EditText) dialogView.findViewById(R.id.edt20k);
        final EditText edt10k = (EditText) dialogView.findViewById(R.id.edt10k);
        final EditText edt5k = (EditText) dialogView.findViewById(R.id.edt5k);
        final EditText edt2k = (EditText) dialogView.findViewById(R.id.edt2k);
        final EditText edt1k = (EditText) dialogView.findViewById(R.id.edt1k);
        final EditText edt500 = (EditText) dialogView.findViewById(R.id.edt500);
        final EditText edt100 = (EditText) dialogView.findViewById(R.id.edt100);
        final EditText edt50 = (EditText) dialogView.findViewById(R.id.edt50);
        final EditText edt25 = (EditText) dialogView.findViewById(R.id.edt25);
        final EditText edt10 = (EditText) dialogView.findViewById(R.id.edt10);
        final EditText edt5 = (EditText) dialogView.findViewById(R.id.edt5);
        final EditText edtdol = (EditText) dialogView.findViewById(R.id.edtDol );
        final EditText edtvale = (EditText) dialogView.findViewById(R.id.edtVale);

/*
        final EditText edt = (EditText) dialogView.findViewById(R.id.editNombre);
        final EditText edt2 = (EditText) dialogView.findViewById(R.id.editRazon);
        final EditText edt3 = (EditText) dialogView.findViewById(R.id.editDescuento);
        //final EditText edt7 = (EditText) dialogView.findViewById(R.id.editFormadepago);
        final Spinner spiTipo = (Spinner) dialogView.findViewById(R.id.spinner);
*/
        // final Button pic = (Button) dialogView.findViewById(R.id.buttonFoto);

        //_img = (ImageView) dialogView.findViewById(R.id.img);
/*

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(getApplicationContext(),"Hi",Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //startActivityForResult(intent,0);

                //_comodato += edt.getText().toString() + "," + edt2.getText().toString() + "," + edt3.getText().toString()+",";

            }


        });*/





        dialogBuilder.setTitle("Billetes");
        dialogBuilder.setMessage("Puedes agregar desglose de billetes");
        dialogBuilder.setPositiveButton("Pura Vida", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                    saveBilletesToServer(edt50k.getText().toString(),edt20k.getText().toString(),edt10k.getText().toString(),edt5k.getText().toString(),edt2k.getText().toString(),edt1k.getText().toString(),edt500.getText().toString(),
                            edt100.getText().toString(),edt50.getText().toString(),edt25.getText().toString(),edt10.getText().toString(),edt5.getText().toString(),
                            edtdol.getText().toString(),edtvale.getText().toString());
                    //Toast.makeText(getApplicationContext(),"El desglose agregado con exito",Toast.LENGTH_LONG).show();



                //do something with edt.getText().toString();
            }
        });
        dialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();

    }
    public void custommotivos(){





        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.nocompra, null);
        dialogBuilder.setView(dialogView);
/*
        final EditText edt = (EditText) dialogView.findViewById(R.id.editNombre);
        final EditText edt2 = (EditText) dialogView.findViewById(R.id.editRazon);
        final EditText edt3 = (EditText) dialogView.findViewById(R.id.editDescuento);
        //final EditText edt7 = (EditText) dialogView.findViewById(R.id.editFormadepago);
        final Spinner spiTipo = (Spinner) dialogView.findViewById(R.id.spinner);
*/
        // final Button pic = (Button) dialogView.findViewById(R.id.buttonFoto);

        //_img = (ImageView) dialogView.findViewById(R.id.img);
/*

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(getApplicationContext(),"Hi",Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //startActivityForResult(intent,0);

                //_comodato += edt.getText().toString() + "," + edt2.getText().toString() + "," + edt3.getText().toString()+",";

            }


        });*/





        dialogBuilder.setTitle("Motivo de no compra");
        dialogBuilder.setMessage("Por que el cliente no compro");
        dialogBuilder.setPositiveButton("Pura Vida", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                Toast.makeText(getApplicationContext(),"El motivo agregado con exito",Toast.LENGTH_LONG).show();



                //do something with edt.getText().toString();
            }
        });
        dialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();

    }
    public void customtipopago(){





        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.tipopago, null);
        dialogBuilder.setView(dialogView);

        final EditText edtEfeQ = (EditText) dialogView.findViewById(R.id.edtEfeQ);
        final EditText edttransQ = (EditText) dialogView.findViewById(R.id.edttransQ);
        final EditText edtNumeroCuenta = (EditText) dialogView.findViewById(R.id.edtNumeroCuenta);
        final EditText edtchequeQ = (EditText) dialogView.findViewById(R.id.edtchequeQ);
        final EditText edtchequeN = (EditText) dialogView.findViewById(R.id.edtchequeN);
        final EditText edtchequeB = (EditText) dialogView.findViewById(R.id.edtchequeB);



        dialogBuilder.setTitle("Tipo de pago");

        dialogBuilder.setPositiveButton("Pura Vida", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {



                efectivo = edtEfeQ.getText().toString();
                 transC = edttransQ.getText().toString();
                 transNum = edtNumeroCuenta.getText().toString();
                 chkmont = edtchequeQ.getText().toString();
                 chknum = edtchequeN.getText().toString();
                 chkbank = edtchequeB.getText().toString();
                Toast.makeText(getApplicationContext(),"El tipo de pago agregado con exito",Toast.LENGTH_LONG).show();



                //do something with edt.getText().toString();
            }
        });
        dialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        edtEfeQ.setText(String.valueOf(_totalfac-_totaldesc) );
        b.show();

    }

    String fac_detail,desc_detail;
    boolean _facok = true;
    public void GenerateMotivo(){


            //final CharSequence[] options = {"xD"};
            final CharSequence[] options = {"Economico","Competencia","No estaba","Otros"};

            clienteFromdb.clear();




            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
            //builder.setCancelable(false);
            builder.setTitle("Motivo de no compra:");
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    //options[which]



                    // the user clicked on options[which]
                }
            });
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    //the user clicked on Cancel
                }
            });
            builder.show();


    }

    //MODIFICAR ANTES DE CARGAR CLIENTES
    public int getClienteFromGPS(){

        if (LAT.equals("-10000")){
            _CurrentGPS = "Aun no";

            if (myUserName.getText().toString().equals("zz")){
                customDialog();
            }
            if (myUserName.getText().toString().equals("tomza")){
                loadClientes();
            }
            if (myUserName.getText().toString().equals("cargar")){
                if(mTextView.getText().toString() != ""){
                    db.DeleteClients();
                    ReadCommand(mTextView.getText().toString());
                }
            }
            if(myUserName.getText().toString().equals("billete")){
                custombilletes();
            }
            if(myUserName.getText().toString().equals("no")){
                //custommotivos();
            }

            if(myUserName.getText().toString().equals("cierre")){
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int choice) {
                        switch (choice) {
                            case DialogInterface.BUTTON_POSITIVE:
                                Cursor cursor = db.getUnsyncedNames();
                                if (cursor.getCount() == 0){
                                    SharedPreferences.Editor editor = sharedpreferences.edit();

                                    editor.putString(_Tcontado ,String.valueOf(0));
                                    editor.putString(_TdescuentoC ,String.valueOf(0));
                                    editor.putString(_TdescuentoCo ,String.valueOf(0));
                                    editor.putString(_Tcredito,String.valueOf(0));

                                    editor.commit();

                                    Toast.makeText(getApplicationContext(),"Cierre Hecho con exito",Toast.LENGTH_SHORT).show();
                                    db.Delete();
                                }
                                else
                                if (cursor.moveToFirst()) {
                                    do {
                                        //calling the method to save the unsynced name to MySQL
                                        saveName(
                                                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)),
                                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME))
                                        );
                                    } while (cursor.moveToNext());
                                    Toast.makeText(getApplicationContext(),"No Sincronizado",Toast.LENGTH_SHORT).show();
                                }

                                resetUI();

                                //saveNameToServer("0,"+editTextName.getText().toString()+","+LAT+","+LONG+","+_supervisor);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };


                //calcularFactura(codigocliente);




                //calcularFactura(_descuento8,_credit8,_ruta8,codigocliente);
                //calcularFactura(_descuento8,_credit8,_ruta8,codigocliente);

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Deseas hacer el cierre?");

                builder.setMessage("Asegurate de estar en el CEDI \n Creditos:"+sharedpreferences.getString(_Tcredito,"0") +  " \n Descuento Credito:" + sharedpreferences.getString(_TdescuentoC,"0") +
                        " \n Contado:" + sharedpreferences.getString(_Tcontado,"0") +  " \n Descuento Contado:" + sharedpreferences.getString(_TdescuentoCo,"0") +
                        " \n \n \n Total Contado:" + String.valueOf(Double.valueOf(sharedpreferences.getString(_Tcontado,"0")) - Double.valueOf(sharedpreferences.getString(_TdescuentoCo,"0")))
                        +  " \n Total Credito:" + String.valueOf(Double.valueOf(sharedpreferences.getString(_Tcredito,"0")) - Double.valueOf(sharedpreferences.getString(_TdescuentoC,"0"))))
                        .setPositiveButton("Pura Vida", dialogClickListener)
                        .setNegativeButton("Cancelar", dialogClickListener).show();

            }
            return -1;




            }

        if (myUserName.getText().toString().equals("cargar")){
            if(mTextView.getText().toString() != ""){
                db.DeleteClients();
                ReadCommand(mTextView.getText().toString());
                return -1;
            }
        }
        if (myUserName.getText().toString().equals("zz")){
            customDialog();
            return -1;
        }
        if (myUserName.getText().toString().equals("tomza")){
            loadClientes();
        }
            else{


            String _t = c.convertLatLonToMGRS(Double.parseDouble(LAT),Double.parseDouble(LONG));

            _t = _t.replace(" ","");
            _CurrentGPS2 = _t;


            _CurrentGPS = _t.substring(0,7) + _t.substring(10,12);

            clienteFromdb.clear();
            indexes.clear();
            clientes.clear();
            Cursor cursor = db.getGpsClientes(_CurrentGPS);
            if (cursor.moveToFirst()) {
                do {
                    //Cliente name = new Cliente(

                    //      cursor.getString(cursor.getColumnIndex("NOMBRE")),cursor.getString(cursor.getColumnIndex("DESCUENTO")),
                    //      cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS))
                    //);
                    //clientes.add(name);
                    clienteFromdb.add(cursor.getString(cursor.getColumnIndex("NOMBRE")) +","+ cursor.getString(cursor.getColumnIndex("RAZON")));
                    indexes.add(cursor.getString(cursor.getColumnIndex("id")));
                } while (cursor.moveToNext());
            }



        }







       /* int value = Arrays.binarySearch(_TRON8, _t.substring(0,_t.length() - 2));
        if (value != -1) {
// my array has silly !
        }*/

        return -1;
    }


    void calcularFactura(int codigocliente){
        if (codigocliente != -1){
            //int codigocliente = Integer.parseInt(myUserName.getText().toString());
            //_codigocliente = razon_soc[codigocliente];
            _client = _actual.getRazon();
            _descCilindro25 = Integer.parseInt(_actual.getDescuento());

            float _precioCilindro30 = Float.parseFloat(sharedpreferences.getString(_Precioa30,null));
            float _precioCilindro50 = Float.parseFloat(sharedpreferences.getString(_Precioa50,null));
            if(Integer.valueOf(_actual.getTipoFactura()) == 3){
                //ES TIENDA ESCALA + FIJO
                _precioCilindro25 = Float.parseFloat(sharedpreferences.getString(_Precioc25,null));//Integer.valueOf(_actual.getLprecioC());//6967
                _precioCilindro10 = Float.parseFloat(sharedpreferences.getString(_Precioc10,null)); //round(_precioCilindro25/25) * 10;
                _precioCilindro20 = Float.parseFloat(sharedpreferences.getString(_Precioc20,null));//round(_precioCilindro25/25) * 20;
                _precioCilindro35 = Float.parseFloat(sharedpreferences.getString(_Precioc35,null));//round(_precioCilindro25/25) * 35;
                _precioCilindro40 = Float.parseFloat(sharedpreferences.getString(_Precioc40,null));//round(_precioCilindro25/25) * 40;
                _precioCilindro45 = Float.parseFloat(sharedpreferences.getString(_Precioc45,null));//round(_precioCilindro25/25) * 45;
                _precioCilindro60 = round(_precioCilindro25/25) * 60;
                _precioCilindro100 =Float.parseFloat(sharedpreferences.getString(_Precioc100,null)); //_precioCilindro25 * 4;
                _precioCilindro30 = Float.parseFloat(sharedpreferences.getString(_Precioc30,null));
                _precioCilindro50 = Float.parseFloat(sharedpreferences.getString(_Precioc50,null));
                _desc = "y";
                int conso = (Q25 + Q25r) + ( Q100r*4);
                if((conso) < 10)
                    _descCilindro25 = 1574;
                if((conso > 9) && (conso < 21))
                    _descCilindro25 = 1754;
                if((conso > 19) && (conso < 31))
                    _descCilindro25 = 1858;
                if((conso >= 30) && (conso < 41))
                    _descCilindro25 = 1898;
                if((conso >= 40) && (conso < 51))
                    _descCilindro25 = 1908;
                if((conso >= 50) && (conso < 61))
                    _descCilindro25 = 1923;
                if(conso >= 60)
                    _descCilindro25 = 1933;

            }
//DESCUENTOS Y PRECIO SEGUN FACTOR NO OLVIDARRRR
            if(Integer.valueOf(_actual.getTipoFactura()) == 2){
                //ES TIENDA Descuento fijo
                _precioCilindro25 = Float.parseFloat(sharedpreferences.getString(_Precioc25,null));//Integer.valueOf(_actual.getLprecioC());//6967
                _precioCilindro10 = Float.parseFloat(sharedpreferences.getString(_Precioc10,null)); //round(_precioCilindro25/25) * 10;
                _precioCilindro20 = Float.parseFloat(sharedpreferences.getString(_Precioc20,null));//round(_precioCilindro25/25) * 20;
                _precioCilindro35 = Float.parseFloat(sharedpreferences.getString(_Precioc35,null));//round(_precioCilindro25/25) * 35;
                _precioCilindro40 = Float.parseFloat(sharedpreferences.getString(_Precioc40,null));//round(_precioCilindro25/25) * 40;
                _precioCilindro45 = Float.parseFloat(sharedpreferences.getString(_Precioc45,null));//round(_precioCilindro25/25) * 45;
                _precioCilindro60 = round(_precioCilindro25/25) * 60;
                _precioCilindro100 =Float.parseFloat(sharedpreferences.getString(_Precioc100,null)); //_precioCilindro25 * 4;
                _desc = "y";
                _precioCilindro30 = Float.parseFloat(sharedpreferences.getString(_Precioc30,null));
                _precioCilindro50 = Float.parseFloat(sharedpreferences.getString(_Precioc50,null));

                _descCilindro25 = Integer.parseInt(_actual.getDescuento());

            }

            if(Integer.valueOf(_actual.getTipoFactura()) == 1){
                //ES TIENDA ESCALA
                _precioCilindro25 = Float.parseFloat(sharedpreferences.getString(_Precioc25,null));//Integer.valueOf(_actual.getLprecioC());//6967
                _precioCilindro10 = Float.parseFloat(sharedpreferences.getString(_Precioc10,null)); //round(_precioCilindro25/25) * 10;
                _precioCilindro20 = Float.parseFloat(sharedpreferences.getString(_Precioc20,null));//round(_precioCilindro25/25) * 20;
                _precioCilindro35 = Float.parseFloat(sharedpreferences.getString(_Precioc35,null));//round(_precioCilindro25/25) * 35;
                _precioCilindro40 = Float.parseFloat(sharedpreferences.getString(_Precioc40,null));//round(_precioCilindro25/25) * 40;
                _precioCilindro45 = Float.parseFloat(sharedpreferences.getString(_Precioc45,null));//round(_precioCilindro25/25) * 45;
                _precioCilindro60 = round(_precioCilindro25/25) * 60;
                _precioCilindro100 =Float.parseFloat(sharedpreferences.getString(_Precioc100,null)); //_precioCilindro25 * 4;
                _desc = "y";
                _precioCilindro30 = Float.parseFloat(sharedpreferences.getString(_Precioc30,null));
                _precioCilindro50 = Float.parseFloat(sharedpreferences.getString(_Precioc50,null));
                int conso = (Q25 + Q25r) + ( Q100r*4);
                if((conso) < 10)
                    _descCilindro25 = 1975;
                if((conso > 9) && (conso < 21))
                    _descCilindro25 = 1754;
                if((conso > 19) && (conso < 31))
                    _descCilindro25 = 1858;
                if((conso >= 30) && (conso < 41))
                    _descCilindro25 = 1898;
                if((conso >= 40) && (conso < 51))
                    _descCilindro25 = 1908;
                if((conso >= 50) && (conso < 61))
                    _descCilindro25 = 1923;
                if(conso >= 60)
                    _descCilindro25 = 1933;

            }


            if(Integer.valueOf(_actual.getTipoFactura()) == 0){
                //Precio Agencia
                //No es tienda
                _precioCilindro25 = Float.parseFloat(sharedpreferences.getString(_Precioa25,null));//Integer.valueOf(_actual.getLprecioA());//5711
                _precioCilindro10 =  Float.parseFloat(sharedpreferences.getString(_Precioa10,null));//round(_precioCilindro25/25) * 10;

                _precioCilindro20 = Float.parseFloat(sharedpreferences.getString(_Precioa20,null));//round(_precioCilindro25/25) * 20;

                _precioCilindro35 = Float.parseFloat(sharedpreferences.getString(_Precioa35,null));;
                _precioCilindro40 = Float.parseFloat(sharedpreferences.getString(_Precioa40,null));//round(_precioCilindro25/25) * 40;
                _precioCilindro45 = Float.parseFloat(sharedpreferences.getString(_Precioa45,null));;
                _precioCilindro60 = Float.parseFloat(sharedpreferences.getString(_Precioa60,null));;
                _precioCilindro100 = Float.parseFloat(sharedpreferences.getString(_Precioa100,null));;//_precioCilindro25 * 4;
                _precioCilindro30 = Float.parseFloat(sharedpreferences.getString(_Precioa30,null));
                _precioCilindro50 = Float.parseFloat(sharedpreferences.getString(_Precioa50,null));



                if(_actual.getDescuento() != "0"){
                    _desc = "y";


                }else{
                    _desc = "n";
                }



            }
            if(Integer.valueOf(_actual.getTipoFactura()) == 4){
                //Precio Agencia
                //No es tienda
                _precioCilindro25 = Float.parseFloat(sharedpreferences.getString(_Precioa25,null));//Integer.valueOf(_actual.getLprecioA());//5711
                _precioCilindro10 =  Float.parseFloat(sharedpreferences.getString(_Precioa10,null));//round(_precioCilindro25/25) * 10;

                _precioCilindro20 = Float.parseFloat(sharedpreferences.getString(_Precioa20,null));//round(_precioCilindro25/25) * 20;

                _precioCilindro35 = Float.parseFloat(sharedpreferences.getString(_Precioa35,null));;
                _precioCilindro40 = Float.parseFloat(sharedpreferences.getString(_Precioa40,null));//round(_precioCilindro25/25) * 40;
                _precioCilindro45 = Float.parseFloat(sharedpreferences.getString(_Precioa45,null));;
                _precioCilindro60 = Float.parseFloat(sharedpreferences.getString(_Precioa60,null));;
                _precioCilindro100 = Float.parseFloat(sharedpreferences.getString(_Precioa100,null));;//_precioCilindro25 * 4;
                _precioCilindro30 = Float.parseFloat(sharedpreferences.getString(_Precioa30,null));
                _precioCilindro50 = Float.parseFloat(sharedpreferences.getString(_Precioa50,null));



                if(_actual.getDescuento() != "0"){
                    _desc = "y";


                }else{
                    _desc = "n";
                }



            }


            _descCilindro100 = _descCilindro25 * 4;

            _descCilindro10 = (_descCilindro25/25) * 10;
            _descCilindro20 = (_descCilindro25/25) * 20;
            _descCilindro35 = (_descCilindro25/25) * 35;
            _descCilindro40 = (_descCilindro25/25) * 40;
            _descCilindro45 = (_descCilindro25/25) * 45;
            double _descCilindro30 = (_descCilindro25/25) * 30;
            double _descCilindro50 = (_descCilindro25/25) * 50;
            _descCilindro60 = (_descCilindro25/25) * 60;


            if(_actual.getCredito() != "0"){
                _credit = "y";
            }else{
                _credit = "n";
            }


            float _fd25 = (float)((Q25+Q25r) * _descCilindro25);
            float _fd100 = (float)(Q100r * _descCilindro100);
            float _fd10 = (float)(Q10r * _descCilindro10);
            float _fd30 = (float)(Q30 * _descCilindro30);
            float _fd50 = (float)(Q50 * _descCilindro50);
            float _fd20 = (float)((Q20r+Q20) * _descCilindro20);
            double _fd35 = (double)(Q35r * _descCilindro35);
            float _fd40 = (float)(Q40r * _descCilindro40);
            float _fd45 = (float)(Q45r * _descCilindro45);
            float _fd60 = (float)(Q60r * _descCilindro60);


            float _f =  (float)((Q25+Q25r) * _precioCilindro25);float _f0 = (float)((Q20+Q20r) * _precioCilindro20);float _f2 = (float)(Q35r * _precioCilindro35);
            float _f3 = (float)(Q45r * _precioCilindro45);float _f4 = (float)(Q100r * _precioCilindro100);float _f5 = (float)(Qlts * _precioLts);
            float _f6 = (float)(Qkgs * _precioKgs); float _f7 = (float)(Q10r * _precioCilindro10); float _f8 = (float)(Q40r * _precioCilindro40); float _f9 = (float)(Q60r * _precioCilindro60);
            float _f10 = (float)(Q30 * _precioCilindro30);float _f11 = (float)(Q50 * _precioCilindro50);




            fac_detail = "";
            desc_detail = "";
            _Quantities = String.valueOf(Q20)+","+String.valueOf(Q25)+","+String.valueOf(Q35)+","+String.valueOf(Q45)+","+String.valueOf(Q100)+","+String.valueOf(Qlts)+","+String.valueOf(Qkgs)+","+String.valueOf(Q10)+","+String.valueOf(Q40)+","+String.valueOf(Q60);
            _totalfac = _f+_f0+_f2+_f3+_f4+_f5+_f6+_f7+_f8+_f9+_f10+_f11;
            _totaldesc = _fd10 + _fd20 + _fd25 + _fd35 + _fd40 + _fd45 + _fd45 + _fd60 + _fd100 + _fd30 + _fd50;
            if ( Q50 > 0){
                fac_detail += "    "+String.valueOf(Q50)+ "    "+ String.valueOf(_precioCilindro50)  +"       Cilndro 50   "+ String.format("%.2f", _f11);
                fac_detail += "\n";
                if (_desc == "y"){
                    desc_detail += "    "+String.valueOf(Q50)+ "    "+ String.valueOf(_descCilindro50)  +"       50 Lbs   "+  String.format("%.2f", _fd50);
                    desc_detail += "\n";
                }
            }
            if ( Q30 > 0){
                fac_detail += "    "+String.valueOf(Q30)+ "    "+ String.valueOf(_precioCilindro30)  +"       Cilndro 30   "+ String.format("%.2f", _f10);
                fac_detail += "\n";
                if (_desc == "y"){
                    desc_detail += "    "+String.valueOf(Q30)+ "    "+ String.valueOf(_descCilindro30)  +"       30 Lbs   "+  String.format("%.2f", _fd30);
                    desc_detail += "\n";
                }
            }
            if (Q10r > 0){
                fac_detail += "    "+String.valueOf(Q10r)+ "    "+ String.valueOf(_precioCilindro10)  +"       Cilndro 10   "+ String.format("%.2f", _f7);
                fac_detail += "\n";
                if (_desc == "y"){
                    desc_detail += "    "+String.valueOf(Q10r)+ "    "+ String.valueOf(_descCilindro10)  +"       10 Lbs   "+  String.format("%.2f", _fd10);
                    desc_detail += "\n";
                }
            }

            if (Q20 > 0 || Q20r > 0){
                fac_detail += "    "+String.valueOf(Q20+Q20r)+ "    "+ String.valueOf(_precioCilindro20)  +"       Cilndro 20   "+ String.format("%.2f", _f0);
                fac_detail += "\n";
                if (_desc == "y"){
                    desc_detail += "    "+String.valueOf(Q20+Q20r)+ "    "+ String.valueOf(_descCilindro20)  +"       20 Lbs   "+  String.format("%.2f", _fd20);
                    desc_detail += "\n";
                }
            }
            if (Q25 > 0 || Q25r > 0){
                fac_detail += "    "+String.valueOf(Q25+Q25r)+ "    "+ String.valueOf(_precioCilindro25)  +"       Cilndro 25   "+  String.format("%.2f", _f);
                fac_detail += "\n";
                if (_desc == "y"){
                    desc_detail += "    "+String.valueOf(Q25+Q25r)+ "    "+ String.valueOf(_descCilindro25)  +"       25 Lbs   "+  String.format("%.2f", _fd25);
                    desc_detail += "\n";
                }
            }

            if (Q35 > 0 || Q35r > 0){
                fac_detail += "    "+String.valueOf(Q35+Q35r)+ "    "+ String.valueOf(_precioCilindro35)  +"       Cilndro 35   "+ String.format("%.2f", _f2);
                fac_detail += "\n";
                if (_desc == "y"){
                    desc_detail += "    "+String.valueOf(Q35+Q35r)+ "    "+ String.valueOf(_descCilindro35)  +"       35 Lbs   "+  String.format("%.2f", _fd35);
                    desc_detail += "\n";
                }
            }

            if (Q40 > 0 || Q40r > 0){
                fac_detail += "    "+String.valueOf(Q40+Q40r)+ "    "+ String.valueOf(_precioCilindro40)  +"       Cilndro 40   "+ String.format("%.2f", _f8);
                fac_detail += "\n";
                if (_desc == "y"){
                    desc_detail += "    "+String.valueOf(Q40+Q40r)+ "    "+ String.valueOf(_descCilindro40)  +"       40 Lbs   "+  String.format("%.2f", _fd40);
                    desc_detail += "\n";
                }

            }
            if (Q45 > 0 || Q45r > 0){
                fac_detail += "    "+String.valueOf(Q45+Q45r)+ "    "+ String.valueOf(_precioCilindro45)  +"       Cilndro 45   "+ String.format("%.2f", _f3);
                fac_detail += "\n";
                if (_desc == "y"){
                    desc_detail += "    "+String.valueOf(Q45+Q45r)+ "    "+ String.valueOf(_descCilindro45)  +"       20 Lbs   "+  String.format("%.2f", _fd45);
                    desc_detail += "\n";
                }
            }
            if (Q60 > 0 || Q60r > 0){
                fac_detail += "    "+String.valueOf(Q60+Q60r)+ "    "+ String.valueOf(_precioCilindro60)  +"       Cilndro 60   "+ String.format("%.2f", _f9);
                fac_detail += "\n";
                if (_desc == "y"){
                    desc_detail += "    "+String.valueOf(Q60+Q60r)+ "    "+ String.valueOf(_descCilindro60)  +"       60 Lbs   "+  String.format("%.2f", _fd60);
                    desc_detail += "\n";
                }

            }
            if (Q100r > 0){
                fac_detail += "    "+String.valueOf(Q100r)+ "    "+ String.valueOf(_precioCilindro100)  +"       Cilndro 100   "+ String.format("%.2f", _f4);
                fac_detail += "\n";
                if (_desc == "y"){
                    desc_detail += "    "+String.valueOf(Q100+Q100r)+ "    "+ String.valueOf(_descCilindro100)  +"      100 Lbs   "+  String.format("%.2f", _fd100);
                    desc_detail += "\n";

                }
            }
            if (Qlts > 0){
                fac_detail += "    "+String.valueOf(Qlts)+ "  "+ String.format("%.2f", _precioLts)  +"     Granel Lts   "+ String.format("%.2f", _f5
                );
                fac_detail += "\n";
            }
            if (Qkgs > 0){
                fac_detail += "    "+String.valueOf(Qkgs)+ "  "+ String.format("%.2f", _precioKgs)  +"     Granel Kgs   "+ String.format("%.2f", _f6);
                fac_detail += "\n";
            }
            String _tp = String.valueOf(_totalfac - _totaldesc);
            fac_detail += "  TOTAL A FACTURAR:  " + _tp;
            fac_detail += "\n";
        }

        //ruta 59 perfil fuera de linea


    }

    @Override
    protected void onStart() {
        super.onStart();

        ReadPrices();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},10);
            return;
        }else{


            locationManager.requestLocationUpdates("gps", 1000, 1, locationListener);


        }


    }


    /*
    * this method will
    * load the names from the database
    * with updated sync status
    * */
    private void loadNames() {
        names.clear();
        Cursor cursor = db.getNames();
        if (cursor.moveToFirst()) {
            do {
                Name name = new Name(
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS))
                );
                names.add(name);
            } while (cursor.moveToNext());
        }

        nameAdapter = new NameAdapter(this, R.layout.names, names);
        listViewNames.setAdapter(nameAdapter);
    }

    private void loadCliente(int id){
        clienteFromdb.clear();
        clientes.clear();
        indexes.clear();
        Cursor cursor = db.getCliente(id);
        if (cursor.moveToFirst()) {
            do {
                _actual.AddCliente(

                        cursor.getString(cursor.getColumnIndex("NOMBRE")),cursor.getString(cursor.getColumnIndex("DESCUENTO")),
                        cursor.getString(cursor.getColumnIndex("RUTA")),cursor.getString(cursor.getColumnIndex("RAZON")),
                        cursor.getString(cursor.getColumnIndex("CREDITO")),cursor.getString(cursor.getColumnIndex("SUB_CANAL")),
                        cursor.getString(cursor.getColumnIndex("LDESCUENTO")),cursor.getString(cursor.getColumnIndex("LPRECIOA")),
                        cursor.getString(cursor.getColumnIndex("LPRECIOC")),cursor.getString(cursor.getColumnIndex("LAT")),
                        cursor.getString(cursor.getColumnIndex("LONG")),cursor.getString(cursor.getColumnIndex("G")),
                        cursor.getString(cursor.getColumnIndex("FORMAPAGO")),cursor.getString(cursor.getColumnIndex("PUEDOFACTURAR")),
                        cursor.getString(cursor.getColumnIndex("TIPOFACTURA")),

                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS))
                );




            } while (cursor.moveToNext());

        }
    }

    private void loadClientes(){

        clienteFromdb.clear();
        clientes.clear();
        indexes.clear();
        Cursor cursor = db.getNombre();
        if (cursor.moveToFirst()) {
            do {
                Cliente name = new Cliente(

                        cursor.getString(cursor.getColumnIndex("NOMBRE")),cursor.getString(cursor.getColumnIndex("DESCUENTO")),
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS))
                );
                clientes.add(name);
                clienteFromdb.add(name.getNombre());
                indexes.add( cursor.getString(cursor.getColumnIndex("id")));

            } while (cursor.moveToNext());
        }
    }

    /*
    * this method will simply refresh the list
    * */
    private void refreshList() {
        nameAdapter.notifyDataSetChanged();
    }
    private void ReadPrices() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Obteniendo Precios");
        progressDialog.show();

        //final String name = _rutaa;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_COMM_PRICES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);

                            try {



                                //String jsonstring = "{ "child": { "something": "value", "something2": "value" } }";
                                //JSONObject resobj = new JSONObject(jsonstring);
                                Iterator<?> keys = obj.keys();
                                while(keys.hasNext() ) {
                                    String key = (String)keys.next();

                                    if ( obj.get(key) instanceof JSONObject ) {
                                        JSONObject xx = new JSONObject(obj.get(key).toString());
                                        //String t = xx.getString("country");
                                        if (key.equals("Settings")){
                                            SharedPreferences.Editor editor = sharedpreferences.edit();

                                            editor.putString(_Precioa10, xx.getString("a10"));
                                            editor.putString(_Precioa20, xx.getString("a20"));
                                            editor.putString(_Precioa25, xx.getString("a25"));
                                            editor.putString(_Precioa30, xx.getString("a30"));
                                            editor.putString(_Precioa35, xx.getString("a35"));
                                            editor.putString(_Precioa40, xx.getString("a40"));
                                            editor.putString(_Precioa45, xx.getString("a45"));
                                            editor.putString(_Precioa50, xx.getString("a50"));
                                            editor.putString(_Precioa60, xx.getString("a60"));
                                            editor.putString(_Precioa100, xx.getString("a100"));

                                            editor.putString(_Precioc10, xx.getString("c10"));
                                            editor.putString(_Precioc20, xx.getString("c20"));
                                            editor.putString(_Precioc25, xx.getString("c25"));
                                            editor.putString(_Precioc30, xx.getString("c30"));
                                            editor.putString(_Precioc35, xx.getString("c35"));
                                            editor.putString(_Precioc40, xx.getString("c40"));
                                            editor.putString(_Precioc45, xx.getString("c45"));
                                            editor.putString(_Precioc50, xx.getString("c50"));
                                            editor.putString(_Precioc60, xx.getString("c60"));
                                            editor.putString(_Precioc100, xx.getString("c100"));

                                            editor.putString(_Preciolts,xx.getString("lts"));
                                            editor.putString(_Preciokgs,xx.getString("kgs"));

                                            editor.commit();

                                            Log.d("Sett",xx.getString("a100"));
                                        }

                                    }


                                }




                            } catch (final JSONException e) {
                                Log.e("TT" , "Json parsing error: " + e.getMessage());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Json parsing error: " + e.getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                });
                            }

                            //if there is a success
                            //storing the name to sqlite with status synced
                            //saveNameToLocalStorage(name, NAME_SYNCED_WITH_SERVER);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //_supervisor = sharedpreferences.getString(MIRUTA,null);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        //Toast.makeText(getApplicationContext(), ,Toast.LENGTH_SHORT);
                        error.printStackTrace();
                        //Log.e("okas:", Log.getStackTraceString(error) );

                        //on error storing the name to sqlite with status unsynced
                        //saveNameToLocalStorage(name, NAME_NOT_SYNCED_WITH_SERVER);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", "xD");
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));



        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
    /*
    * this method is saving the name to ther server
    * */
    private void ReadCommand(String _rutaa) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Obteniendo Datos");
        progressDialog.show();

        final String name = _rutaa;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_COMM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);

                                try {



                                    //String jsonstring = "{ "child": { "something": "value", "something2": "value" } }";
                                    //JSONObject resobj = new JSONObject(jsonstring);
                                    Iterator<?> keys = obj.keys();
                                    while(keys.hasNext() ) {
                                        String key = (String)keys.next();

                                        if ( obj.get(key) instanceof JSONObject ) {
                                            JSONObject xx = new JSONObject(obj.get(key).toString());
                                            //String t = xx.getString("country");
                                            if (key.equals("Settings")){
                                                SharedPreferences.Editor editor = sharedpreferences.edit();

                                                editor.putString(URL_MAIN, xx.getString("URLMAIN"));
                                                editor.putString(URL_MAIN_2, xx.getString("URLMAIN2"));
                                                editor.putString(URL_CLIENTES,xx.getString("URLCLIENTES"));
                                                editor.putString(URL_COMM, xx.getString("URLCOMM"));
                                                editor.putString(MIRUTA,xx.getString("RUTA"));
                                                editor.commit();

                                                Log.d("Sett",xx.getString("URLMAIN"));
                                            }

                                        }
                                        if ( obj.get(key) instanceof JSONArray ) {
                                            if (key.equals("AddClient")){
                                                JSONArray jsonarray = obj.getJSONArray(key); //new JSONArray(key);
                                                for (int i = 0; i < jsonarray.length(); i++) {
                                                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                                                    db.addCliente(jsonobject.getString("nombre"),jsonobject.getString("descuento"),
                                                            jsonobject.getString("ruta"),jsonobject.getString("razon"),
                                                            jsonobject.getString("credito"),jsonobject.getString("subcanal"),
                                                            jsonobject.getString("ldescuento"),jsonobject.getString("lprecioa"),
                                                            jsonobject.getString("lprecioc"),jsonobject.getString("LAT"),
                                                            jsonobject.getString("LONG"),jsonobject.getString("GRID"),
                                                            jsonobject.getString("formapago"),jsonobject.getString("puedofacturar"),
                                                            jsonobject.getString("tipofactura"),1);

                                                    //String name = jsonobject.getString("nombre");
                                                    //String url = jsonobject.getString("descuento");
                                                    //String xd = jsonobject.getString("descuento");
                                                }
                                            }
                                        }

                                    }




                                } catch (final JSONException e) {
                                    Log.e("TT" , "Json parsing error: " + e.getMessage());
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(),
                                                    "Json parsing error: " + e.getMessage(),
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }

                                //if there is a success
                                //storing the name to sqlite with status synced
                                //saveNameToLocalStorage(name, NAME_SYNCED_WITH_SERVER);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        _supervisor = sharedpreferences.getString(MIRUTA,null);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        //Toast.makeText(getApplicationContext(), ,Toast.LENGTH_SHORT);
                        error.printStackTrace();
                        //Log.e("okas:", Log.getStackTraceString(error) );

                        //on error storing the name to sqlite with status unsynced
                        //saveNameToLocalStorage(name, NAME_NOT_SYNCED_WITH_SERVER);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));



        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
    private void saveNameToServer(String _data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String format = simpleDateFormat.format(new Date());

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Factura creada correctamente");
        progressDialog.show();
        if (transC.equals("") ) transC = "0"; if (transNum.equals("")) transNum = "0"; if (chknum.equals("")) chknum = "0";
        if (chkbank.equals("")) chkbank = "0";if (chkmont.equals("")) chkmont = "0";
        final String name = _data + ","+ format + ","+ efectivo + ","+ transC + ","+ transNum + ","+ chknum+ ","+ chkbank + ","+ chkmont;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVE_NAME,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                //if there is a success
                                //storing the name to sqlite with status synced
                                if(_actual.getCredito().equals("1"))
                                    saveNameToLocalStorage(name, NAME_NOT_SYNCED_WITH_SERVER,Double.valueOf(sharedpreferences.getString(_Tcontado,"0")),Double.valueOf(sharedpreferences.getString(_TdescuentoCo,"0")));
                                else
                                    saveNameToLocalStorage(name, NAME_NOT_SYNCED_WITH_SERVER,Double.valueOf(sharedpreferences.getString(_Tcredito,"0")),Double.valueOf(sharedpreferences.getString(_TdescuentoC,"0")));


                            } else {
                                //if there is some error
                                //saving the name to sqlite with status unsynced
                                if(_actual.getCredito().equals("1"))
                                    saveNameToLocalStorage(name, NAME_NOT_SYNCED_WITH_SERVER,Double.valueOf(sharedpreferences.getString(_Tcontado,"0")),Double.valueOf(sharedpreferences.getString(_TdescuentoCo,"0")));
                                else
                                    saveNameToLocalStorage(name, NAME_NOT_SYNCED_WITH_SERVER,Double.valueOf(sharedpreferences.getString(_Tcredito,"0")),Double.valueOf(sharedpreferences.getString(_TdescuentoC,"0")));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        //Toast.makeText(getApplicationContext(), ,Toast.LENGTH_SHORT);
                        error.printStackTrace();
                        //Log.e("okas:", Log.getStackTraceString(error) );

                        //on error storing the name to sqlite with status unsynced
                        if(_actual.getCredito().equals("1"))
                            saveNameToLocalStorage(name, NAME_NOT_SYNCED_WITH_SERVER,Double.valueOf(sharedpreferences.getString(_Tcontado,"0")),Double.valueOf(sharedpreferences.getString(_TdescuentoCo,"0")));
                        else
                            saveNameToLocalStorage(name, NAME_NOT_SYNCED_WITH_SERVER,Double.valueOf(sharedpreferences.getString(_Tcredito,"0")),Double.valueOf(sharedpreferences.getString(_TdescuentoC,"0")));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("tipo", "f");
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));



        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
    private void saveBilletesToServer(final String _50k,final String _20k,final String _10k,final String _5k,final String _2k,final String _1k,
                                      final String _500,final String _100,final String _50,final String _25,final String _10,final String _5,final String _dol,final String _vale
    ) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Subiendo corte de liquidacion");
        progressDialog.show();

        final String _name = _supervisor + ","+ _50k + "," + _20k + "," + _10k + "," + _5k + "," + _2k + "," + _1k + "," + _500 + "," + _100 + ","
                + _50 + "," + _25 + "," + _10 + "," + _5 + "," + _dol +"," +_vale;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_BILLETES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            boolean _tmps = obj.getBoolean("error");
                            if (_tmps == true) {
                                //if there is a success
                                //storing the name to sqlite with status synced
                                SharedPreferences.Editor editor = sharedpreferences.edit();

                                editor.putString(_billete, _name);

                                editor.commit();
                                Toast.makeText( getApplicationContext(),"Error, intentar otra vez",Toast.LENGTH_LONG).show();
                                //saveClienteToLocalStorage(name,descuento,ruta,Razon,credito,sub_canal,ldescuento,lprecioa,lprecioc,LAT,LONG,G,FormaPago,puedoFacturar,tipoFactura, NAME_SYNCED_WITH_SERVER);
                            } else {
                                SharedPreferences.Editor editor = sharedpreferences.edit();

                                editor.putString(_billete, _name);

                                editor.commit();
                                Toast.makeText( getApplicationContext(),"Desglose agregado correctamente",Toast.LENGTH_LONG).show();
                                //if there is some error
                                //saving the name to sqlite with status unsynced
                                //saveClienteToLocalStorage(name,descuento,ruta,Razon,credito,sub_canal,ldescuento,lprecioa,lprecioc,LAT,LONG,G,FormaPago,puedoFacturar,tipoFactura, NAME_NOT_SYNCED_WITH_SERVER);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        //Toast.makeText(getApplicationContext(), ,Toast.LENGTH_SHORT);
                        error.printStackTrace();
                        Toast.makeText( getApplicationContext(),"Error, contactar a soporte",Toast.LENGTH_LONG).show();
                        //Log.e("okas:", Log.getStackTraceString(error) );

                        //on error storing the name to sqlite with status unsynced
                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString(_billete, _name);

                        editor.commit();
                        //saveClienteToLocalStorage(name,descuento,ruta,Razon,credito,sub_canal,ldescuento,lprecioa,lprecioc,LAT,LONG,G,FormaPago,puedoFacturar,tipoFactura, NAME_NOT_SYNCED_WITH_SERVER);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", _name);
                params.put("tipo", "c");
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));



        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
    private void saveClienteToServer(final String name, final String descuento, final String ruta, final String Razon,final String credito,final String sub_canal,final String ldescuento,
                                     final String lprecioa,final String lprecioc,final String LAT,final String LONG,final String G,final String FormaPago,final String puedoFacturar,final String tipoFactura
                                    ) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cliente creado correctamente");
        progressDialog.show();

        final String _name = name+","+descuento+","+ruta+","+Razon+","+credito+","+sub_canal+","+ldescuento+
                ","+lprecioa+","+lprecioc+","+LAT+","+LONG+","+G+","+FormaPago+","+puedoFacturar+","+tipoFactura;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_CLIENTE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                //if there is a success
                                //storing the name to sqlite with status synced
                                saveClienteToLocalStorage(name,descuento,ruta,Razon,credito,sub_canal,ldescuento,lprecioa,lprecioc,LAT,LONG,G,FormaPago,puedoFacturar,tipoFactura, NAME_SYNCED_WITH_SERVER);
                            } else {
                                //if there is some error
                                //saving the name to sqlite with status unsynced
                                saveClienteToLocalStorage(name,descuento,ruta,Razon,credito,sub_canal,ldescuento,lprecioa,lprecioc,LAT,LONG,G,FormaPago,puedoFacturar,tipoFactura, NAME_NOT_SYNCED_WITH_SERVER);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        //Toast.makeText(getApplicationContext(), ,Toast.LENGTH_SHORT);
                        error.printStackTrace();
                        //Log.e("okas:", Log.getStackTraceString(error) );

                        //on error storing the name to sqlite with status unsynced
                        saveClienteToLocalStorage(name,descuento,ruta,Razon,credito,sub_canal,ldescuento,lprecioa,lprecioc,LAT,LONG,G,FormaPago,puedoFacturar,tipoFactura, NAME_NOT_SYNCED_WITH_SERVER);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", _name);
                params.put("tipo", "c");
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));



        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    private void getClients(String _data) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando Clientes");
        progressDialog.show();

        final String name = _data;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://tomzacr.com/t/addCliente.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                //if there is a success
                                //storing the name to sqlite with status synced
                                //saveNameToLocalStorage(name, NAME_SYNCED_WITH_SERVER);
                                //Toast.makeText(this,obj.getString(""),Toast.LENGTH_SHORT);
                            } else {
                                //if there is some error
                                //saving the name to sqlite with status unsynced
                                //saveNameToLocalStorage(name, NAME_NOT_SYNCED_WITH_SERVER);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        //Toast.makeText(getApplicationContext(), ,Toast.LENGTH_SHORT);
                        error.printStackTrace();
                        //Log.e("okas:", Log.getStackTraceString(error) );

                        //on error storing the name to sqlite with status unsynced
                        if(_actual.getCredito().equals("1"))
                            saveNameToLocalStorage(name, NAME_NOT_SYNCED_WITH_SERVER,Double.valueOf(sharedpreferences.getString(_Tcontado,"0")),Double.valueOf(sharedpreferences.getString(_TdescuentoCo,"0")));
                        else
                            saveNameToLocalStorage(name, NAME_NOT_SYNCED_WITH_SERVER,Double.valueOf(sharedpreferences.getString(_Tcredito,"0")),Double.valueOf(sharedpreferences.getString(_TdescuentoC,"0")));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    //saving the name to local storage
    private void saveClienteToLocalStorage(String name, String descuento, String ruta, String Razon,String credito,String sub_canal,String ldescuento,
                                           String lprecioa,String lprecioc,String LAT, String LONG, String G, String FormaPago, String puedoFacturar,String tipoFactura,
                                           int status) {




        db.addCliente(name,descuento,ruta,Razon,credito,sub_canal,ldescuento,lprecioa,lprecioc,LAT,LONG,G,FormaPago,puedoFacturar,tipoFactura,status);
        //Name n = new Name(name, status);
        //names.add(n);
        loadNames();
        refreshList();
    }
    //saving the name to local storage
    private void saveNameToLocalStorage(String name, int status,double _acum,double _acumD) {



        SharedPreferences.Editor editor = sharedpreferences.edit();
        if (_actual.getCredito().equals("1")){
            editor.putString(_Tcontado ,String.valueOf( _totalfac  + _acum));
            editor.putString(_TdescuentoCo ,String.valueOf( _totaldesc  + _acumD));
        }

        else{
            editor.putString(_Tcredito,String.valueOf( _totalfac  + _acum));
            editor.putString( _TdescuentoC,String.valueOf( _totaldesc  + _acumD));
        }



        editor.commit();
        db.addName(name, status);
        //Name n = new Name(name, status);
        //names.add(n);
        loadNames();
        refreshList();
    }

    @Override
    public void onClick(View view) {



            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int choice) {
                    switch (choice) {
                        case DialogInterface.BUTTON_POSITIVE:

                               // saveNameToServer("0,"+","+LAT+","+LONG+","+_supervisor);


                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            break;
                    }
                }
            };


            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder( this );
            builder.setMessage("Actualizar coordenadas para: ")
                    .setPositiveButton("Pura Vida", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();





        }




}