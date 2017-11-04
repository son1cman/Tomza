package net.simplifiedcoding.androidmysqlsync;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
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
import android.os.Handler;
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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
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
import java.util.Set;
import java.util.UUID;

import static java.lang.Math.round;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*
    * CILINDROSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
    * */
    private boolean _FacCilindro = true;
    private boolean _bt = true;
    private  String _sDescuento = "";
    private int _facc = 0;
    public int _tipodoc = 0; //0 = tomza, 1 = cilza, 2= tramite
    private int _facz = 0;
    private int _fact = 0;
    private String _cQ25 = "";
    private String _cQ25r,_cQ100r,_cwQ25,_cwQ25r,_cwQ100r;
    private String _f1,_f2,_f3,_ff1,_ff2,_ff3,_fff1,_fff2,_fff3;
    private double TotalCil = 0;
    private double TotalImpCil = 0;
    private double SubTotalCil = 0;

    private  String _sTipo = "";
    private String _printer = "BlueTooth Printer"; //cashino = PTP-III    Koolertron = BlueTooth Printer



    private String efectivo,transC,transNum,chknum,chkmont,chkbank;

    private catalogo _10r,_20p,_20r,_25p,_25r,_30r,_35r,_40r,_45r,_50r,_60r,_lts,_kgs,_100r;
    private catalogo _c25,_c25r,_c100,_wc25,_wc25r,_wc100;
    public static String URL_SAVE_NAME = "http://www.tomzacr.com/t/saveitf.php"; //Factura
    public static String URL_GET_COMM = "http://www.tomzacr.com/t/comm.php"; //Cargar
    public static String URL_GET_COMM_PRICES = "http://www.tomzacr.com/t/commprices.php"; //Cargar
    public static String URL_GET_CLIENTE = "http://www.tomzacr.com/t/cliente.php"; //Clientes
    public static String URL_GET_BILLETES = "http://www.tomzacr.com/t/billete.php"; //Clientes
    public static String COUNT_CLIENTES = "COUNTCLI";

//JULIO 6
    //OSCAR 7
    // JORGE 8
    public static final String MyPREFERENCES = "SystemSettings";
    public static final String URL_MAIN = "URL_MAIN";
    public static final String _Tcredito = "tcredito";
    public static final String _Tcontado = "tcontado";
    public static final String _Tfactura = "reimpresion";
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
    public static final String CORRECILZA = "CORRECILZA";
    public static final String CORRETRAM = "CORRETRAM";
    public static final String URL_COMM = "URL_COMM";
    public static final String URL_CLIENTES = "URL_CLIENTES";
    public static final String MIRUTA = "MI_RUTA";
    public static final String TOTALFACTURADO = "TOTAL_FACTURADO";

    // android built in classes for bluetooth operations
    BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice;
    // needed for communication to bluetooth device / network
    OutputStream mmOutputStream;
    InputStream mmInputStream;
    Thread workerThread;



    byte[] readBuffer;
    int readBufferPosition;
    volatile boolean stopWorker;

    //public static final String TOTALDESCUENTO = "TOTAL_DESCUENTO";

    SharedPreferences sharedpreferences;



//1 luis
    //2 andres
    //valerin
    String _supervisor = "0";
    String _clienteC = "0";

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









    private int _SelectedOption = 0;

    TextView myLabel;
    Button sendButton,cilzaButton;

    // UI Widgets.

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




    double _precioCilindro25 = 5693;//6967



    double _descCilindro25 = 0;


    LatLon2MGRS c;



    double _precioLts = 221.4;
    double _precioKgs = _precioLts * 11.67;
    //database helper object
    private DatabaseHelper db;

    //View objects
    private Button buttonAdd;
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
        _tipodoc = 0;
        TotalCil = 0;
        TotalImpCil = 0;
        SubTotalCil = 0;

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
        _supervisor = sharedpreferences.getString(MIRUTA,"0");
        _clienteC = sharedpreferences.getString(COUNT_CLIENTES,"0");
        String _tmfac = sharedpreferences.getString(URL_MAIN_2,"0");
        _facc =Integer.valueOf(_tmfac);
        _facz = Integer.valueOf(sharedpreferences.getString(CORRECILZA,"0"));
        _fact = Integer.valueOf(sharedpreferences.getString(CORRETRAM,"0"));

        _10r = new catalogo("10lbRosca",Double.valueOf(sharedpreferences.getString(_Precioa10,"0")),Double.valueOf(sharedpreferences.getString(_Precioc10,"0")),10);
        _20r = new catalogo("20lbRosca",Double.valueOf(sharedpreferences.getString(_Precioa20,"0")),Double.valueOf(sharedpreferences.getString(_Precioc20,"0")),20);
        _20p = new catalogo("20lbPresion",Double.valueOf(sharedpreferences.getString(_Precioa20,"0")),Double.valueOf(sharedpreferences.getString(_Precioc20,"0")),20);
        _25r = new catalogo("25lbRosca",Double.valueOf(sharedpreferences.getString(_Precioa25,"0")),Double.valueOf(sharedpreferences.getString(_Precioc25,"0")),25);
        _25p = new catalogo("25lbPresion",Double.valueOf(sharedpreferences.getString(_Precioa25,"0")),Double.valueOf(sharedpreferences.getString(_Precioc25,"0")),25);
        _30r = new catalogo("30lb",Double.valueOf(sharedpreferences.getString(_Precioa30,"0")),Double.valueOf(sharedpreferences.getString(_Precioc30,"0")),30);
        _35r = new catalogo("35lb",Double.valueOf(sharedpreferences.getString(_Precioa35,"0")),Double.valueOf(sharedpreferences.getString(_Precioc35,"0")),35);
        _40r = new catalogo("40lb",Double.valueOf(sharedpreferences.getString(_Precioa40,"0")),Double.valueOf(sharedpreferences.getString(_Precioc40,"0")),40);
        _45r = new catalogo("45lb",Double.valueOf(sharedpreferences.getString(_Precioa45,"0")),Double.valueOf(sharedpreferences.getString(_Precioc45,"0")),45);
        _50r = new catalogo("50lb",Double.valueOf(sharedpreferences.getString(_Precioa50,"0")),Double.valueOf(sharedpreferences.getString(_Precioc50,"0")),50);
        _100r = new catalogo("100lb",Double.valueOf(sharedpreferences.getString(_Precioa100,"0")),Double.valueOf(sharedpreferences.getString(_Precioc100,"0")),100);

        _c25 = new catalogo("Cil25Presion",Double.valueOf(355),Double.valueOf(355),25);
        _c25r = new catalogo("Cil25Rosca",Double.valueOf(355),Double.valueOf(355),25);
        _c100 = new catalogo("Cil100Rosca",Double.valueOf(355),Double.valueOf(355),100);

        _wc25 = new catalogo("Cil25Presion",Double.valueOf(355),Double.valueOf(355),25);
        _wc25r = new catalogo("Cil25Rosca",Double.valueOf(355),Double.valueOf(355),25);
        _wc100 = new catalogo("Cil100Rosca",Double.valueOf(355),Double.valueOf(355),100);



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
        }else{
            m_lts.setVisibility(View.GONE);
            m_kgs.setVisibility(View.GONE);
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


            }
        });
        cilzaButton = (Button) findViewById(R.id.btnCil);
        cilzaButton.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               if(codigocliente != -1)
                cilza();
               else
                   Toast.makeText(getApplicationContext(),"Seleccionar cliente primero",Toast.LENGTH_LONG).show();
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
                    final Button btnPrint = (Button) dialogView.findViewById(R.id.btnReimprimir);

                    btnPrint.setVisibility(View.INVISIBLE);


                    dialogBuilder.setTitle(_actual.getRazon());

                    dialogBuilder.setPositiveButton("Pura Vida", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {



                            efectivo = edtEfeQ.getText().toString();
                            transC = edttransQ.getText().toString();
                            transNum = edtNumeroCuenta.getText().toString();
                            chkmont = edtchequeQ.getText().toString();
                            chknum = edtchequeN.getText().toString();
                            chkbank = edtchequeB.getText().toString();
                            if (_tipodoc == 0) _facc++;
                            if (_tipodoc == 1) _facz++;
                            if (_tipodoc == 2) _fact++;
                            SharedPreferences.Editor editor = sharedpreferences.edit();


                            if (_tipodoc == 0) editor.putString(URL_MAIN_2, String.valueOf(_facc));
                            if (_tipodoc == 1) editor.putString(CORRECILZA, String.valueOf(_facz));
                            if (_tipodoc == 2) editor.putString(CORRETRAM, String.valueOf(_fact));



                            editor.commit();


                            if(_FacCilindro){

                                if (_tipodoc == 0)
                                    saveNameToServer(_supervisor+","+ _actual.getCodigo() +","+myUserName.getText()+"," +(_totalfac-_totaldesc) + ","+_totaldesc
                                            +","+ _actual.getDescuento()+","+_actual.getCredito() +"," + String.valueOf(Q30) + "," + String.valueOf(_20p.getQty())
                                            + "," + String.valueOf(_25p.getQty())+ "," + String.valueOf(_30r.getQty())+ "," + String.valueOf(_40r.getQty())+ "," + String.valueOf(_50r.getQty())
                                            + "," + String.valueOf(_100r.getQty())
                                            + "," + String.valueOf(_10r.getQty()) + "," + String.valueOf(_20r.getQty())+
                                            "," + String.valueOf(_25r.getQty())+ "," + String.valueOf(_35r.getQty())+ "," + String.valueOf(_45r.getQty())+ "," + String.valueOf(_60r.getQty())
                                            + "," + LAT + "," + LONG +","+_CurrentGPS);
                                if (_tipodoc == 1)
                                    saveNameToServer(_supervisor+","+ _actual.getCodigo() +","+myUserName.getText()+"," +String.valueOf(TotalCil) + ","+"0"
                                            +","+ "0"+","+_actual.getCredito() +"," + "0" + "," + "0"
                                            + "," + "0"+ "," + "0"+ "," + "0"+ "," + "0"
                                            + "," + "0"
                                            + "," + "0" + "," + "0"+
                                            "," + "0"+ "," + "0"+ "," + "0"+ "," + "0"
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
                            try {
                                try {
                                    double _tt = _totalfac-_totaldesc; //Bug granel
                                    /*if(_tipodoc == 1)
                                    sendDataCilza(fac_detail,"");
                                    else*/
                                        sendData(String.valueOf(_tt),String.valueOf(_totaldesc));

                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            } catch(Exception e) {


                                e.printStackTrace();
                            }





                            codigocliente = -1;



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
                            if (_10r.getDesQTY() != null) fac_detail += _10r.getDesQTY() + "\n" + "(-)" +_10r.getDesDESC() + "\n";
                            if (_20p.getDesQTY() != null) fac_detail += _20p.getDesQTY() + "\n" + "(-)"+ _20p.getDesDESC()+ "\n"; if (_20r.getDesQTY() != null) fac_detail += _20r.getDesQTY() + "\n" + "(-)"+ _20r.getDesDESC()+ "\n";
                            if (_25p.getDesQTY() != null) fac_detail += _25p.getDesQTY() + "\n" + "(-)"+ _25p.getDesDESC()+ "\n"; if (_25r.getDesQTY() != null) fac_detail += _25r.getDesQTY() + "\n" + "(-)"+ _25r.getDesDESC()+ "\n";
                            if (_30r.getDesQTY() != null) fac_detail += _30r.getDesQTY() + "\n" + "(-)"+ _30r.getDesDESC()+ "\n"; if (_35r.getDesQTY() != null) fac_detail += _35r.getDesQTY() + "\n" + "(-)"+ _35r.getDesDESC()+ "\n";
                            if (_40r.getDesQTY() != null) fac_detail += _40r.getDesQTY()+ "\n" + "(-)"+ _40r.getDesDESC()+ "\n";  if (_45r.getDesQTY() != null) fac_detail += _45r.getDesQTY()+ "\n" + "(-)"+ _45r.getDesDESC()+ "\n";
                            if (_50r.getDesQTY() != null) fac_detail += _50r.getDesQTY()+ "\n" + "(-)"+  _50r.getDesDESC()+ "\n";  if (_60r.getDesQTY() != null) fac_detail += _60r.getDesQTY()+ "\n" + "(-)"+ _60r.getDesDESC()+ "\n";
                            if (_100r.getDesQTY() != null)fac_detail += _100r.getDesQTY()+ "\n" + "(-)"+ _100r.getDesDESC()+ "\n";

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
                           /* if (_10r.getDesQTY() != null) fac_detail += "    " +_10r.getDesQTY() + "\n";
                            if (_20p.getDesQTY() != null) fac_detail += "    " +_20p.getDesQTY() + "\n";if (_20r.getDesQTY() != null) fac_detail += "    " +_20r.getDesQTY() + "\n";
                            if (_25p.getDesQTY() != null) fac_detail += "    " +_25p.getDesQTY() + "\n";if (_25r.getDesQTY() != null) fac_detail += "    " +_25r.getDesQTY() + "\n";
                            if (_30r.getDesQTY() != null) fac_detail += "    " +_30r.getDesQTY() + "\n";if (_35r.getDesQTY() != null) fac_detail += "    " +_35r.getDesQTY() + "\n";
                            if (_40r.getDesQTY() != null) fac_detail += "    " +_40r.getDesQTY()+ "\n";if (_45r.getDesQTY() != null) fac_detail += "    " +_45r.getDesQTY()+ "\n";
                            if (_50r.getDesQTY() != null) fac_detail += "    " +_50r.getDesQTY()+ "\n";if (_60r.getDesQTY() != null) fac_detail += "    " +_60r.getDesQTY()+ "\n";
                            if (_100r.getDesQTY() != null) fac_detail += "    " +_100r.getDesQTY()+ "\n";*/

                            if (_10r.getDesQTY() != null) fac_detail += _10r.getDesQTY() + "\n" + "(-)" +_10r.getDesDESC() + "\n";
                            if (_20p.getDesQTY() != null) fac_detail += _20p.getDesQTY() + "\n" + "(-)"+ _20p.getDesDESC()+ "\n"; if (_20r.getDesQTY() != null) fac_detail += _20r.getDesQTY() + "\n" + "(-)"+ _20r.getDesDESC()+ "\n";
                            if (_25p.getDesQTY() != null) fac_detail += _25p.getDesQTY() + "\n" + "(-)"+ _25p.getDesDESC()+ "\n"; if (_25r.getDesQTY() != null) fac_detail += _25r.getDesQTY() + "\n" + "(-)"+ _25r.getDesDESC()+ "\n";
                            if (_30r.getDesQTY() != null) fac_detail += _30r.getDesQTY() + "\n" + "(-)"+ _30r.getDesDESC()+ "\n"; if (_35r.getDesQTY() != null) fac_detail += _35r.getDesQTY() + "\n" + "(-)"+ _35r.getDesDESC()+ "\n";
                            if (_40r.getDesQTY() != null) fac_detail += _40r.getDesQTY()+ "\n" + "(-)"+ _40r.getDesDESC()+ "\n";  if (_45r.getDesQTY() != null) fac_detail += _45r.getDesQTY()+ "\n" + "(-)"+ _45r.getDesDESC()+ "\n";
                            if (_50r.getDesQTY() != null) fac_detail += _50r.getDesQTY()+ "\n" + "(-)"+  _50r.getDesDESC()+ "\n";  if (_60r.getDesQTY() != null) fac_detail += _60r.getDesQTY()+ "\n" + "(-)"+ _60r.getDesDESC()+ "\n";
                            if (_100r.getDesQTY() != null)fac_detail += _100r.getDesQTY()+ "\n" + "(-)"+ _100r.getDesDESC()+ "\n";

                            if (_10r.getDesDESC() != null) desc_detail += "    " +_10r.getDesDESC() + "\n";
                            if (_20p.getDesDESC() != null) desc_detail += "    " +_20p.getDesDESC()+ "\n";if (_20r.getDesDESC() != null) desc_detail += "    " +_20r.getDesDESC()+ "\n";
                            if (_25p.getDesDESC() != null) desc_detail += "    " +_25p.getDesDESC()+ "\n";if (_25r.getDesDESC() != null) desc_detail += "    " +_25r.getDesDESC()+ "\n";
                            if (_30r.getDesDESC() != null) desc_detail += "    " +_30r.getDesDESC()+ "\n";if (_35r.getDesDESC() != null) desc_detail += "    " +_35r.getDesDESC()+ "\n";
                            if (_40r.getDesDESC() != null) desc_detail += "    " +_40r.getDesDESC()+ "\n";if (_45r.getDesDESC() != null) desc_detail += "    " +_45r.getDesDESC()+ "\n";
                            if (_50r.getDesDESC() != null) desc_detail += "    " +_50r.getDesDESC()+ "\n";if (_60r.getDesDESC() != null) desc_detail += "    " +_60r.getDesDESC()+ "\n";
                            if (_100r.getDesDESC() != null) desc_detail += "    " +_100r.getDesDESC()+ "\n";

                            _totalfac = _10r.getQtyTotal() + _20p.getQtyTotal() + _20r.getQtyTotal() + _25r.getQtyTotal() + _25p.getQtyTotal() + _30r.getQtyTotal() +
                                    _35r.getQtyTotal() + _40r.getQtyTotal() + _45r.getQtyTotal() + _50r.getQtyTotal() + _60r.getQtyTotal() + _100r.getQtyTotal();
                            _totaldesc = _10r.getDescTotal() + _20p.getDescTotal() + _20r.getDescTotal() + _25r.getDescTotal() + _25p.getDescTotal() + _30r.getDescTotal() +
                                    _35r.getDescTotal() + _40r.getDescTotal() + _45r.getDescTotal() + _50r.getDescTotal() + _60r.getDescTotal() + _100r.getDescTotal();


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

                            /*
                            if (_10r.getDesQTY() != null) fac_detail += _10r.getDesQTY() + "\n";
                            if (_20p.getDesQTY() != null) fac_detail += _20p.getDesQTY() + "\n";if (_20r.getDesQTY() != null) fac_detail += _20r.getDesQTY() + "\n";
                            if (_25p.getDesQTY() != null) fac_detail += _25p.getDesQTY() + "\n";if (_25r.getDesQTY() != null) fac_detail += _25r.getDesQTY() + "\n";
                            if (_30r.getDesQTY() != null) fac_detail += _30r.getDesQTY() + "\n";if (_35r.getDesQTY() != null) fac_detail += _35r.getDesQTY() + "\n";
                            if (_40r.getDesQTY() != null) fac_detail += _40r.getDesQTY()+ "\n";if (_45r.getDesQTY() != null) fac_detail += _45r.getDesQTY()+ "\n";
                            if (_50r.getDesQTY() != null) fac_detail += _50r.getDesQTY()+ "\n";if (_60r.getDesQTY() != null) fac_detail += _60r.getDesQTY()+ "\n";
                            if (_100r.getDesQTY() != null) fac_detail += _100r.getDesQTY()+ "\n";*/

                            if (_10r.getDesQTY() != null) fac_detail += _10r.getDesQTY() + "\n" + "(-)" + String.valueOf(_10r.getQty()) + " 10 lbs" + String.valueOf(Math.round((_descCilindro25*0.4f)*100.0)/100.0) + " " + String.format("%.2f", _10r.getQty()*_descCilindro25*0.4f) + "\n";
                            if (_20p.getDesQTY() != null) fac_detail += _20p.getDesQTY() + "\n" + "(-)"+ String.valueOf(_20p.getQty()) + " 20 lbs " + String.valueOf(Math.round((_descCilindro25*0.8f)*100.0)/100.0) + " " + String.format("%.2f", _20p.getQty()*_descCilindro25*0.8f) + "\n";
                            if (_20r.getDesQTY() != null) fac_detail += _20r.getDesQTY() + "\n" + String.valueOf(_20r.getQty()) + " 20 lbs " + String.valueOf(Math.round((_descCilindro25*0.8f)*100.0)/100.0) + " " + String.format("%.2f", _20r.getQty()*_descCilindro25*0.8f) + "\n";
                            if (_25p.getDesQTY() != null) fac_detail += _25p.getDesQTY() + "\n" + "(-)"+ String.valueOf(_25p.getQty()) + " 25 lbs " + String.valueOf(Math.round((_descCilindro25*1.0f)*100.0)/100.0) + " " + String.format("%.2f", _25p.getQty()*_descCilindro25*1.0f) + "\n";
                            if (_25r.getDesQTY() != null) fac_detail += _25r.getDesQTY() + "\n" + "(-)"+ String.valueOf(_25r.getQty()) + " 25 lbs " + String.valueOf(Math.round((_descCilindro25*1.0f)*100.0)/100.0) + " " + String.format("%.2f", _25r.getQty()*_descCilindro25*1.0f) + "\n";
                            if (_30r.getDesQTY() != null) fac_detail += _30r.getDesQTY() + "\n" + "(-)" + String.valueOf(_30r.getQty()) + " 30 lbs " + String.valueOf(Math.round((_descCilindro25*1.2f)*100.0)/100.0) + " " + String.format("%.2f", _30r.getQty()*_descCilindro25*1.2f) + "\n";
                            if (_35r.getDesQTY() != null) fac_detail += _35r.getDesQTY() + "\n" + "(-)" + String.valueOf(_35r.getQty()) + " 35 lbs " + String.valueOf(Math.round((_descCilindro25*1.4f)*100.0)/100.0) + " " + String.format("%.2f", _35r.getQty()*_descCilindro25*1.4f) + "\n";
                            if (_40r.getDesQTY() != null) fac_detail += _40r.getDesQTY()+ "\n" +  "(-)" + String.valueOf(_40r.getQty()) + " 40 lbs " + String.valueOf(Math.round((_descCilindro25*1.6f)*100.0)/100.0) + " " + String.format("%.2f", _40r.getQty()*_descCilindro25*1.6f) + "\n";
                            if (_45r.getDesQTY() != null) fac_detail += _45r.getDesQTY()+ "\n" +  "(-)" + String.valueOf(_45r.getQty()) + " 45 lbs " + String.valueOf(Math.round((_descCilindro25*1.8f)*100.0)/100.0) + " " + String.format("%.2f", _45r.getQty()*_descCilindro25*1.8f) + "\n";
                            if (_50r.getDesQTY() != null) fac_detail += _50r.getDesQTY()+ "\n" +  "(-)" + String.valueOf(_50r.getQty()) + " 50 lbs " + String.valueOf(Math.round((_descCilindro25*2f)*100.0)/100.0)+ " " + String.format("%.2f", _50r.getQty()*_descCilindro25*2f) + "\n";
                            if (_60r.getDesQTY() != null) fac_detail += _60r.getDesQTY()+ "\n" +  "(-)" + String.valueOf(_60r.getQty()) + " 60 lbs " + String.valueOf(Math.round((_descCilindro25*2.4f)*100.0)/100.0) + " " + String.format("%.2f", _60r.getQty()*_descCilindro25*2.4f) + "\n";
                            if (_100r.getDesQTY() != null)fac_detail += _100r.getDesQTY()+ "\n" + "(-)" + String.valueOf(_100r.getQty()) + " 100 lbs " + String.valueOf(Math.round((_descCilindro25*4f)*100.0)/100.0) + " " + String.format("%.2f", _100r.getQty()*_descCilindro25*4f) + "\n";


                            if (_10r.getDesDESC() != null) desc_detail += " " + String.valueOf(_10r.getQty()) + " 10 lbs" + String.valueOf(Math.round((_descCilindro25*0.4f)*100.0)/100.0) + "  = " + String.format("%.2f", _10r.getQty()*_descCilindro25*0.4f) + "\n";
                            if (_20p.getDesDESC() != null)desc_detail += " " + String.valueOf(_20p.getQty()) + " 20 lbs " + String.valueOf(Math.round((_descCilindro25*0.8f)*100.0)/100.0) + "  = " + String.format("%.2f", _20p.getQty()*_descCilindro25*0.8f) + "\n";
                            if (_20r.getDesDESC() != null)desc_detail += " " + String.valueOf(_20r.getQty()) + " 20 lbs " + String.valueOf(Math.round((_descCilindro25*0.8f)*100.0)/100.0) + "  = " + String.format("%.2f", _20r.getQty()*_descCilindro25*0.8f) + "\n";
                            if (_25p.getDesDESC() != null) desc_detail +=" " + String.valueOf(_25p.getQty()) + " 25 lbs " + String.valueOf(Math.round((_descCilindro25*1.0f)*100.0)/100.0) + "  = " + String.format("%.2f", _25p.getQty()*_descCilindro25*1.0f) + "\n";
                            if (_25r.getDesDESC() != null)desc_detail += " " + String.valueOf(_25r.getQty()) + " 25 lbs " + String.valueOf(Math.round((_descCilindro25*1.0f)*100.0)/100.0) + "  = " + String.format("%.2f", _25r.getQty()*_descCilindro25*1.0f) + "\n";
                            if (_30r.getDesDESC() != null)desc_detail += " " + String.valueOf(_30r.getQty()) + " 30 lbs " + String.valueOf(Math.round((_descCilindro25*1.2f)*100.0)/100.0) + "  = " + String.format("%.2f", _30r.getQty()*_descCilindro25*1.2f) + "\n";
                            if (_35r.getDesDESC() != null)desc_detail += " " + String.valueOf(_35r.getQty()) + " 35 lbs " + String.valueOf(Math.round((_descCilindro25*1.4f)*100.0)/100.0) + "  = " + String.format("%.2f", _35r.getQty()*_descCilindro25*1.4f) + "\n";
                            if (_40r.getDesDESC() != null)desc_detail += " " + String.valueOf(_40r.getQty()) + " 40 lbs " + String.valueOf(Math.round((_descCilindro25*1.6f)*100.0)/100.0) + "  = " + String.format("%.2f", _40r.getQty()*_descCilindro25*1.6f) + "\n";
                            if (_45r.getDesDESC() != null)desc_detail += " " + String.valueOf(_45r.getQty()) + " 45 lbs " + String.valueOf(Math.round((_descCilindro25*1.8f)*100.0)/100.0) + "  = " + String.format("%.2f", _45r.getQty()*_descCilindro25*1.8f) + "\n";
                            if (_50r.getDesDESC() != null)desc_detail += " " + String.valueOf(_50r.getQty()) + " 50 lbs " + String.valueOf(Math.round((_descCilindro25*2f)*100.0)/100.0)+ "  = " + String.format("%.2f", _50r.getQty()*_descCilindro25*2f) + "\n";
                            if (_60r.getDesDESC() != null)desc_detail += " " + String.valueOf(_60r.getQty()) + " 60 lbs " + String.valueOf(Math.round((_descCilindro25*2.4f)*100.0)/100.0) + "  = " + String.format("%.2f", _60r.getQty()*_descCilindro25*2.4f) + "\n";
                            if (_100r.getDesDESC()!= null)desc_detail += " " + String.valueOf(_100r.getQty()) + " 100 lbs " + String.valueOf(Math.round((_descCilindro25*4f)*100.0)/100.0) + "  = " + String.format("%.2f", _100r.getQty()*_descCilindro25*4f) + "\n";
                            _totaldesc = Math.round(_totaldesc*100.0)/100.0;
                            _totalfac = Math.round(_totalfac *100.0)/100.0;


                                  /*  builder.setMessage("Desglose de factura: " + "\n" + fac_detail + "\n Total Descuento" + String.format("%.2f", _totaldesc) +  "\n TOTAL A FACTURAR:" + "\n" +  String.format("%.2f", _totalfac - _totaldesc) )
                                            .setPositiveButton("Imprimir", dialogClickListener)
                                            .setNegativeButton("Cancelar", dialogClickListener).show();*/


                        }


                        if(Integer.valueOf(_actual.getTipoFactura()) == 0){
                            //Precio Agencia
                            //No es tienda
                            /*if (_10r.getDesQTY() != null) fac_detail += "    " +_10r.getDesQTY() + "\n";
                            if (_20p.getDesQTY() != null) fac_detail += "    " +_20p.getDesQTY() + "\n";if (_20r.getDesQTY() != null) fac_detail += "    " +_20r.getDesQTY() + "\n";
                            if (_25p.getDesQTY() != null) fac_detail += "    " +_25p.getDesQTY() + "\n";if (_25r.getDesQTY() != null) fac_detail += "    " +_25r.getDesQTY() + "\n";
                            if (_30r.getDesQTY() != null) fac_detail += "    " +_30r.getDesQTY() + "\n";if (_35r.getDesQTY() != null) fac_detail += "    " +_35r.getDesQTY() + "\n";
                            if (_40r.getDesQTY() != null) fac_detail += "    " +_40r.getDesQTY()+ "\n";if (_45r.getDesQTY() != null) fac_detail += "    " +_45r.getDesQTY()+ "\n";
                            if (_50r.getDesQTY() != null) fac_detail += "    " +_50r.getDesQTY()+ "\n";if (_60r.getDesQTY() != null) fac_detail += "    " +_60r.getDesQTY()+ "\n";
                            if (_100r.getDesQTY() != null) fac_detail += "    " +_100r.getDesQTY()+ "\n";*/

                            if (_10r.getDesQTY() != null) fac_detail += _10r.getDesQTY() + "\n" + "(-)" +_10r.getDesDESC() + "\n";
                            if (_20p.getDesQTY() != null) fac_detail += _20p.getDesQTY() + "\n" + "(-)"+ _20p.getDesDESC()+ "\n"; if (_20r.getDesQTY() != null) fac_detail += _20r.getDesQTY() + "\n" + "(-)"+ _20r.getDesDESC()+ "\n";
                            if (_25p.getDesQTY() != null) fac_detail += _25p.getDesQTY() + "\n" + "(-)"+ _25p.getDesDESC()+ "\n"; if (_25r.getDesQTY() != null) fac_detail += _25r.getDesQTY() + "\n" + "(-)"+ _25r.getDesDESC()+ "\n";
                            if (_30r.getDesQTY() != null) fac_detail += _30r.getDesQTY() + "\n" + "(-)"+ _30r.getDesDESC()+ "\n"; if (_35r.getDesQTY() != null) fac_detail += _35r.getDesQTY() + "\n" + "(-)"+ _35r.getDesDESC()+ "\n";
                            if (_40r.getDesQTY() != null) fac_detail += _40r.getDesQTY()+ "\n" + "(-)"+ _40r.getDesDESC()+ "\n";  if (_45r.getDesQTY() != null) fac_detail += _45r.getDesQTY()+ "\n" + "(-)"+ _45r.getDesDESC()+ "\n";
                            if (_50r.getDesQTY() != null) fac_detail += _50r.getDesQTY()+ "\n" + "(-)"+  _50r.getDesDESC()+ "\n";  if (_60r.getDesQTY() != null) fac_detail += _60r.getDesQTY()+ "\n" + "(-)"+ _60r.getDesDESC()+ "\n";
                            if (_100r.getDesQTY() != null)fac_detail += _100r.getDesQTY()+ "\n" + "(-)"+ _100r.getDesDESC()+ "\n";

                            if (_10r.getDesDESC() != null) desc_detail += "    " +_10r.getDesDESC() + "\n";
                            if (_20p.getDesDESC() != null) desc_detail += "    " +_20p.getDesDESC()+ "\n";if (_20r.getDesDESC() != null) desc_detail += "    " +_20r.getDesDESC()+ "\n";
                            if (_25p.getDesDESC() != null) desc_detail += "    " +_25p.getDesDESC()+ "\n";if (_25r.getDesDESC() != null) desc_detail += "    " +_25r.getDesDESC()+ "\n";
                            if (_30r.getDesDESC() != null) desc_detail += "    " +_30r.getDesDESC()+ "\n";if (_35r.getDesDESC() != null) desc_detail += "    " +_35r.getDesDESC()+ "\n";
                            if (_40r.getDesDESC() != null) desc_detail += "    " +_40r.getDesDESC()+ "\n";if (_45r.getDesDESC() != null) desc_detail += "    " +_45r.getDesDESC()+ "\n";
                            if (_50r.getDesDESC() != null) desc_detail += "    " +_50r.getDesDESC()+ "\n";if (_60r.getDesDESC() != null) desc_detail += "    " +_60r.getDesDESC()+ "\n";
                            if (_100r.getDesDESC() != null) desc_detail += "    " +_100r.getDesDESC()+ "\n";
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
                            if (_10r.getDesQTY() != null) fac_detail += "    " +_10r.getDesQTY() + "\n";
                            if (_20p.getDesQTY() != null) fac_detail += "    " +_20p.getDesQTY() + "\n";if (_20r.getDesQTY() != null) fac_detail += "    " +_20r.getDesQTY() + "\n";
                            if (_25p.getDesQTY() != null) fac_detail += "    " +_25p.getDesQTY() + "\n";if (_25r.getDesQTY() != null) fac_detail += "    " +_25r.getDesQTY() + "\n";
                            if (_30r.getDesQTY() != null) fac_detail += "    " +_30r.getDesQTY() + "\n";if (_35r.getDesQTY() != null) fac_detail += "    " +_35r.getDesQTY() + "\n";
                            if (_40r.getDesQTY() != null) fac_detail += "    " +_40r.getDesQTY()+ "\n";if (_45r.getDesQTY() != null) fac_detail += "    " +_45r.getDesQTY()+ "\n";
                            if (_50r.getDesQTY() != null) fac_detail += "    " +_50r.getDesQTY()+ "\n";if (_60r.getDesQTY() != null) fac_detail += "    " +_60r.getDesQTY()+ "\n";
                            if (_100r.getDesQTY() != null) fac_detail += "    " +_100r.getDesQTY()+ "\n";

                            if (_10r.getDesDESC() != null) desc_detail += "    " +_10r.getDesDESC() + "\n";
                            if (_20p.getDesDESC() != null) desc_detail += "    " +_20p.getDesDESC()+ "\n";if (_20r.getDesDESC() != null) desc_detail += "    " +_20r.getDesDESC()+ "\n";
                            if (_25p.getDesDESC() != null) desc_detail += "    " +_25p.getDesDESC()+ "\n";if (_25r.getDesDESC() != null) desc_detail += "    " +_25r.getDesDESC()+ "\n";
                            if (_30r.getDesDESC() != null) desc_detail += "    " +_30r.getDesDESC()+ "\n";if (_35r.getDesDESC() != null) desc_detail += "    " +_35r.getDesDESC()+ "\n";
                            if (_40r.getDesDESC() != null) desc_detail += "    " +_40r.getDesDESC()+ "\n";if (_45r.getDesDESC() != null) desc_detail += "    " +_45r.getDesDESC()+ "\n";
                            if (_50r.getDesDESC() != null) desc_detail += "    " +_50r.getDesDESC()+ "\n";if (_60r.getDesDESC() != null) desc_detail += "    " +_60r.getDesDESC()+ "\n";
                            if (_100r.getDesDESC() != null) desc_detail += "    " +_100r.getDesDESC()+ "\n";
                            _descCilindro25 = 0;

                            _totalfac = _10r.getQtyTotal() + _20p.getQtyTotal() + _20r.getQtyTotal() + _25r.getQtyTotal() + _25p.getQtyTotal() + _30r.getQtyTotal() +
                                    _35r.getQtyTotal() + _40r.getQtyTotal() + _45r.getQtyTotal() + _50r.getQtyTotal() + _60r.getQtyTotal() + _100r.getQtyTotal();
                            _totaldesc = 0d;
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

                            _totalfac = _10r.getQtyTotal() + _20p.getQtyTotal() + _20r.getQtyTotal() + _25r.getQtyTotal() + _25p.getQtyTotal() + _30r.getQtyTotal() +
                                    _35r.getQtyTotal() + _40r.getQtyTotal() + _45r.getQtyTotal() + _50r.getQtyTotal() + _60r.getQtyTotal() + _100r.getQtyTotal();
                            _totaldesc = _10r.getDescTotal() + _20p.getDescTotal() + _20r.getDescTotal() + _25r.getDescTotal() + _25p.getDescTotal() + _30r.getDescTotal() +
                                    _35r.getDescTotal() + _40r.getDescTotal() + _45r.getDescTotal() + _50r.getDescTotal() + _60r.getDescTotal() + _100r.getDescTotal();
                                   /* builder.setMessage("Desglose de factura: " + "\n" + fac_detail + "\n TOTAL A FACTURAR:" + "\n" +  String.valueOf(_totalfac) )
                                            .setPositiveButton("Imprimir", dialogClickListener)
                                            .setNegativeButton("Cancelar", dialogClickListener).show();*/

                        }
                        if (_tipodoc == 1){
                            fac_detail =  ((_cwQ25 == null) ? "":_cwQ25) + ((_cwQ25r == null) ? "":_cwQ25r) + ((_cwQ100r == null) ? "":_cwQ100r);
                            TotalCil = TotalImpCil + SubTotalCil;
                            edtEfeQ.setText(String.valueOf(TotalCil) );
                            txvDesglose.setText("Desglose de factura: " + "\n" + fac_detail + "\n TOTAL A FACTURAR:" + "\n" +  String.valueOf(TotalCil));
                            b.show();
                        }else{
                            edtEfeQ.setText(String.valueOf(_totalfac-_totaldesc) );
                            txvDesglose.setText("Desglose de factura: " + "\n" + fac_detail + "\n Desglose Descuento" + "\n" + desc_detail +  "\n TOTAL A FACTURAR:" + "\n" +  String.valueOf(_totalfac-_totaldesc));
                            b.show();
                        }


/****************************************************************************************************/
                    }else{
                        if(_lts.getQty() > 0){
                            //edtEfeQ.setText(String.valueOf(_totalfac-_totaldesc) );
                            fac_detail = _lts.getDesQTY(); desc_detail = _lts.getDesDESC();
                            _totalfac = _lts.getQtyTotal();_totaldesc = _lts.getDescTotal();
                            edtEfeQ.setText(String.valueOf(_totalfac-_totaldesc));
                            txvDesglose.setText("Desglose de factura: " + "\n" + _lts.getDesQTY() +  "\n Desglose Descuento:" + "\n" + _lts.getDesDESC() +  "\n TOTAL A FACTURAR:" + "\n" +  _lts.getTotal().toString() );
                            b.show();
                        }

                        if(_kgs.getQty() > 0){
                            //edtEfeQ.setText(String.valueOf(_totalfac-_totaldesc) );
                            fac_detail =  _kgs.getDesQTY(); desc_detail = _kgs.getDesDESC();
                            _totalfac = _kgs.getQtyTotal();_totaldesc = _kgs.getDescTotal();
                            edtEfeQ.setText(String.valueOf(_totalfac-_totaldesc));
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
                final String[] tokens = data.getName().split(",");

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
                    final Button btnPrint = (Button) dialogView.findViewById(R.id.btnReimprimir);
                    btnPrint.setVisibility(View.INVISIBLE);












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
                    Toast.makeText(getApplicationContext(), _CurrentGPS + "No se han detectado clientes",Toast.LENGTH_SHORT).show();

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

    void sendData(String t, String d) throws IOException {
        _date = DateFormat.getDateTimeInstance().format(new Date());
        try {

            // the text typed by the user
            String msg = "\n";// myTextbox.getText().toString();
            if(_tipodoc == 2) {
                msg += "   Gas Tomza de Costa Rica S.A.";
                msg += "\n";
                msg += "    Ced. Jur. 3-101-349880  ";
                msg += "\n";
                msg += "  La lima de Cartago, Costa Rica";
                msg += "\n";
                msg += "  Telefono:(506)2201-6000";
                msg += "\n";
                msg += "    www.tomzacr.com ";
                msg += "\n";
                msg += " servicioalcliente.cr@tomza.com ";
                msg += "\n";
                msg += "\n";
                msg += "Tramite de Pago: " + _actual.getRuta() + String.valueOf(_fact);
                msg += "\n";
                msg += "Rutero: "+_actual.getRuta();
                msg += "\n";
                msg += "Fecha:"+ _date;
                msg += "\n";
                msg += "Señores: " + _actual.getRazon();
                msg += "\n";
                msg += "Con fecha de hoy recibido en calidad de deposito y para tramite de pago las siguientes facturas originales de Gas Tomza de Costa Rica.\n";
                msg += "\n";
                msg += "Factura No   FECHA    VALOR";
                msg += "\n";
                msg += fac_detail;
                msg += "\n";
                msg += "Este documento sustituye a las facturas recibidas en el caso de que los originales de las mismas se extravien, mientras se encuentren en nuestro poder y como factura misma constituye Titulo Ejecutivo, en caso de eventual Cobro Judicial se regira por los Articulos 460 y siguientes del Codigo de Comercio. La posesion de los originales no significa la cancelacion de las mismas. Exija el respectivo recibo de pago.\n";
                msg += "\n";
                msg += "RECIBIDO NOMBRE:\n\n_____________________"+"\n";
                msg += "FIRMA RESPONSABLE:\n\n______________________"+"\n";
                msg += "# DE CEDULA:\n\n______________________"+"\n";


            }else {
                if(_tipodoc == 0) {
                msg += "   Gas Tomza de Costa Rica S.A.";
                msg += "\n";
                msg += "    Ced. Jur. 3-101-349880  ";
                msg += "\n";
                msg += "  La lima de Cartago, Costa Rica";
                msg += "\n";
                msg += "  Telefono:(506)2201-6000";
                msg += "\n";
                msg += "    www.tomzacr.com ";
                msg += "\n";
                msg += " servicioalcliente.cr@tomza.com ";
                msg += "\n";
                msg += "\n";
                msg += "Factura: " + _actual.getRuta() + String.valueOf(_facc);
            }
            if(_tipodoc == 1)
            {
                msg += "   Cilindros Zaragoza S.A.";
                msg += "\n";
                msg += "    Ced. Jur. 3-101-387254  ";
                msg += "\n";
                msg += "  La lima de Cartago, Costa Rica";
                msg += "\n";
                msg += "  Telefono:(506)2573-7789";
                msg += "\n";
                msg += "    www.tomzacr.com ";
                msg += "\n";
                msg += "\n";
                msg += "Factura: " + _actual.getRuta() + String.valueOf(_facz);
            }
            //


            msg += "\n";
            msg += "Rutero: "+_actual.getRuta();
            msg += "\n";
            msg += "Fecha:"+ _date;
            msg += "\n";
            if(_actual.getCredito().equals("0"))
                msg += "FORMA PAGO: CREDITO ";
            else
                msg += "FORMA PAGO: CONTADO ";
            msg += "\n";
            msg += "Razon Social:"+ _actual.getRazon();
            msg += "\n";
            msg += "Nombre:"+ _actual.getNombre();
            msg += "\nDireccion:____________________";
            //msg += "\n";
            //msg += "Codigo Cliente:" +  _codigocliente;
            msg += "\n";
            msg += "\n";
            msg += "CANT.  ART.   P.UNIT    TOTAL";
            msg += "\n";
            msg += "   -------------------------";
            msg += "\n";

            if(fac_detail != "" )
                msg += fac_detail;
            //msg += "\n";
            //msg += "     275                Granel GLP      61050   ";

            msg += "   ---------------------- ";
            msg += "\n";
            msg += "\n";

            if(_tipodoc == 0){

                if(!d.equals("0.0")) msg += "TOTAL A PAGAR(CRC):" + t;
                else msg += "TOTAL(CRC):"+String.format("%.2f", _totalfac)+"   ";
            }else{
                msg += "SUB TOTAL:"+String.format("%.2f", SubTotalCil)+"\n";
                msg += "IMPUESTO:"+String.format("%.2f", TotalImpCil)+"\n";
                msg += "TOTAL(CRC):"+String.format("%.2f", TotalCil)+"\n";
                msg += "\n";
                msg += "\n";
                msg += "NOMBRE:________________"+"\n";
                msg += "CEDULA:________________"+"\n";
                msg += "RECIBIDO CONFORME:\n\n___________________"+"\n";


            }



            msg += "\n";
            msg += "\n";

            msg += "AUTORIZADO MEDIANTE EL OFICIO NUMERO";

            msg += "03-0001-2003 del 6 de feb de D.G.T.D";
            msg += "\n";
            msg += "\n";
            msg += "\n";
            msg += "\n";
            msg += "\n";





            }
            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString(_Tfactura ,msg);


            editor.commit();
            String _buffer = "            *ORGINAL* \n" + msg + "            *COPIA 1* \n" + msg;

            mmOutputStream.write(_buffer.getBytes());

            // tell the user data were sent
            myLabel.setText("Data sent."); _facok = true;


        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    void sendData2(String t, String d) throws IOException {

        try {

            // the text typed by the user
            String msg = "\n";// myTextbox.getText().toString();
            msg += "     Inversiones  Robus";
            msg += "\n";
            msg += "    Ced. Jur. 3-101-314880";
            msg += "\n";
            msg += "   Gerente Mario Rodriguez";
            msg += "\n";
            msg += "  Telefono:(506)2201-9888           ";
            msg += "\n";



            msg += "\n";

            msg += "\n";
            msg += "Factura: " +"2130000";
            msg += "\n";

            msg += "Fecha: 30/09/2017 06:30:42";
            msg += "\n";

                msg += "CONTADO ";
            msg += "\n";
            msg += "Razon Social: GAS TOMZA DE COSTA RICA";
            msg += "\n";
            msg += "Caja: 1";


            msg += "\n";

            msg += "\n";
            msg += "Unitario  Desc   Cant   Total \n";
            msg += "--------------------------------";
            msg += "\n";
            /*if(_SelectedOption == 0)
                msg += "     "+ mTextView.getText().toString()  +"              Cilind. 25 lbs      "+ String.valueOf(Result_25) +"    ";
            else
                msg += "     "+ mTextView.getText().toString()  +"                 GLP lts          "+ String.valueOf(Result_lts) +"    ";
*/
            msg += "500   Papel Term   90    45,000";
            //msg += "\n";
            //msg += "     275                Granel GLP      61050   ";
            msg += "\n";
            msg += "--------------------------------";
            msg += "\n";
            msg += " TOTAL(CRC):"+"45,000.00  ";

            msg += "\n";
            msg += "\n";
            msg += "\n";


            msg += "AUTORIZADO MEDIANTE EL OFICIO NUMERO";

            msg += " 03-0001-2017 del 15 de feb de D.G.T.D";
            msg += "\n";
            msg += "\n";
            msg += "\n";
            msg += "\n";
            msg += "\n";








            mmOutputStream.write(msg.getBytes());

            // tell the user data were sent
            myLabel.setText("Data sent."); _facok = true;


        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    void sendData3(String t) throws IOException {

        try {

            // the text typed by the user
            String msg = t;

            mmOutputStream.write(msg.getBytes());

            // tell the user data were sent
            myLabel.setText("Data sent."); _facok = true;


        } catch (Exception e) {

            e.printStackTrace();
        }
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
                        _clienteC = String.valueOf(Integer.valueOf(_clienteC) + 1);
                        SharedPreferences.Editor editor = sharedpreferences.edit();


                        editor.putString(COUNT_CLIENTES,_clienteC);
                        editor.commit();

                    if(_FacCilindro)//clien te cilindros
                        saveClienteToServer(_supervisor + _clienteC,edt.getText().toString(), edt3.getText().toString(), _supervisor,edt2.getText().toString() , "1",
                                _tiposub,edt3.getText().toString(),"0","0",LAT,LONG, (_tt.substring(0,7) + _tt.substring(10,12)),
                                "0","1",String.valueOf(spiTipo.getSelectedItemPosition()));
                        else //granel
                        saveClienteToServer(_supervisor +  _clienteC,edt.getText().toString(), edt3.getText().toString(), _supervisor,edt2.getText().toString() , String.valueOf(spiTipo.getSelectedItemPosition()),
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
    public void tramite(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.tramite, null);
        dialogBuilder.setView(dialogView);
        final EditText f1 = (EditText) dialogView.findViewById(R.id.f1);
        final EditText f2 = (EditText) dialogView.findViewById(R.id.f2);
        final EditText f3 = (EditText) dialogView.findViewById(R.id.f3);
        final EditText ff1 = (EditText) dialogView.findViewById(R.id.ff1);
        final EditText ff2 = (EditText) dialogView.findViewById(R.id.ff2);
        final EditText ff3 = (EditText) dialogView.findViewById(R.id.ff3);
        final EditText fff1 = (EditText) dialogView.findViewById(R.id.fff1);
        final EditText fff2 = (EditText) dialogView.findViewById(R.id.fff2);
        final EditText fff3 = (EditText) dialogView.findViewById(R.id.fff3);






        dialogBuilder.setTitle("Cilza");
        dialogBuilder.setMessage("Crear factura de cilza aqui");
        dialogBuilder.setPositiveButton("Pura Vida", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                f1 = 


                if(!_cQ25.equals("") ||!_cQ25r.equals("") ||!_cQ100r.equals("")){
                    if(spiTipo.getSelectedItemPosition() == 0){ // Precio normal GESSA
                        if(!_cQ25.equals("") ) {
                            _cwQ25 = _cQ25 + " " + "25Presion" + " 17522 " + String.valueOf(Integer.valueOf(_cQ25) * 17522);
                            SubTotalCil += Integer.valueOf(_cQ25) * 17522;
                            TotalImpCil += Integer.valueOf(_cQ25) * 2278;
                            //TotalCil += SubTotalCil + TotalImpCil;
                        }
                        if(!_cQ25r.equals("") ) {
                            _cwQ25r = _cQ25r + " " + "25Rosca" + " 19823 " + String.valueOf(Integer.valueOf(_cQ25r) * 19823);
                            SubTotalCil += Integer.valueOf(_cQ25r) * 19823;
                            TotalImpCil += Integer.valueOf(_cQ25r) * 2577;
                            //TotalCil += SubTotalCil + TotalImpCil;
                        }
                        if(!_cQ100r.equals("") ) {
                            _cwQ100r = _cQ100r + " " + "100Rosca" + " 90000 " + String.valueOf(Integer.valueOf(_cQ100r) * 90000);

                            SubTotalCil += Integer.valueOf(_cQ100r) * 90000;
                            TotalImpCil += Integer.valueOf(_cQ100r) * 11700;
                            //TotalCil += SubTotalCil + TotalImpCil;
                        }

                    }
                    if(spiTipo.getSelectedItemPosition() == 1){ // Precio Walmart

                        if(!_cQ25.equals("") ) {
                            _cwQ25 = _cQ25 + " " + "25Presion" + " 16500 " + String.valueOf(Integer.valueOf(_cQ25) * 16500);
                            SubTotalCil += Integer.valueOf(_cQ25) * 16500;
                            TotalImpCil += Integer.valueOf(_cQ25) * 2145;
                            //TotalCil += SubTotalCil + TotalImpCil;
                        }
                        if(!_cQ25r.equals("") ) {
                            _cwQ25r = _cQ25r + " " + "25Rosca" + " 20240 " + String.valueOf(Integer.valueOf(_cQ25r) * 20240);
                            SubTotalCil += Integer.valueOf(_cQ25r) * 20240;
                            TotalImpCil += Integer.valueOf(_cQ25r) * 2631.2;
                            //TotalCil += SubTotalCil + TotalImpCil;
                        }
                        if(!_cQ100r.equals("") ) {
                            _cwQ100r = _cQ100r + " " + "100Rosca" + " 90000 " + String.valueOf(Integer.valueOf(_cQ100r) * 90000);

                            SubTotalCil += Integer.valueOf(_cQ100r) * 90000;
                            TotalImpCil += Integer.valueOf(_cQ100r) * 11700;
                            //TotalCil += SubTotalCil + TotalImpCil;
                        }
                    }

                    _tipodoc = 1;
                }



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
    public void cilza(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.cilza, null);
        dialogBuilder.setView(dialogView);
        final EditText c25 = (EditText) dialogView.findViewById(R.id.c25);
        final EditText c25r = (EditText) dialogView.findViewById(R.id.c25r);
        final EditText c100r = (EditText) dialogView.findViewById(R.id.c100r);
        final Spinner spiTipo = (Spinner) dialogView.findViewById(R.id.spinner);




        dialogBuilder.setTitle("Cilza");
        dialogBuilder.setMessage("Crear factura de cilza aqui");
        dialogBuilder.setPositiveButton("Pura Vida", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                _cQ25 = c25.getText().toString();
                _cQ25r = c25r.getText().toString();
                _cQ100r = c100r.getText().toString();


                if(!_cQ25.equals("") ||!_cQ25r.equals("") ||!_cQ100r.equals("")){
                    if(spiTipo.getSelectedItemPosition() == 0){ // Precio normal GESSA
                        if(!_cQ25.equals("") ) {
                            _cwQ25 = _cQ25 + " " + "25Presion" + " 17522 " + String.valueOf(Integer.valueOf(_cQ25) * 17522);
                            SubTotalCil += Integer.valueOf(_cQ25) * 17522;
                            TotalImpCil += Integer.valueOf(_cQ25) * 2278;
                            //TotalCil += SubTotalCil + TotalImpCil;
                        }
                        if(!_cQ25r.equals("") ) {
                            _cwQ25r = _cQ25r + " " + "25Rosca" + " 19823 " + String.valueOf(Integer.valueOf(_cQ25r) * 19823);
                            SubTotalCil += Integer.valueOf(_cQ25r) * 19823;
                            TotalImpCil += Integer.valueOf(_cQ25r) * 2577;
                            //TotalCil += SubTotalCil + TotalImpCil;
                        }
                        if(!_cQ100r.equals("") ) {
                            _cwQ100r = _cQ100r + " " + "100Rosca" + " 90000 " + String.valueOf(Integer.valueOf(_cQ100r) * 90000);

                            SubTotalCil += Integer.valueOf(_cQ100r) * 90000;
                            TotalImpCil += Integer.valueOf(_cQ100r) * 11700;
                            //TotalCil += SubTotalCil + TotalImpCil;
                        }

                    }
                    if(spiTipo.getSelectedItemPosition() == 1){ // Precio Walmart

                            if(!_cQ25.equals("") ) {
                                _cwQ25 = _cQ25 + " " + "25Presion" + " 16500 " + String.valueOf(Integer.valueOf(_cQ25) * 16500);
                                SubTotalCil += Integer.valueOf(_cQ25) * 16500;
                                TotalImpCil += Integer.valueOf(_cQ25) * 2145;
                                //TotalCil += SubTotalCil + TotalImpCil;
                            }
                            if(!_cQ25r.equals("") ) {
                                _cwQ25r = _cQ25r + " " + "25Rosca" + " 20240 " + String.valueOf(Integer.valueOf(_cQ25r) * 20240);
                                SubTotalCil += Integer.valueOf(_cQ25r) * 20240;
                                TotalImpCil += Integer.valueOf(_cQ25r) * 2631.2;
                                //TotalCil += SubTotalCil + TotalImpCil;
                            }
                            if(!_cQ100r.equals("") ) {
                                _cwQ100r = _cQ100r + " " + "100Rosca" + " 90000 " + String.valueOf(Integer.valueOf(_cQ100r) * 90000);

                                SubTotalCil += Integer.valueOf(_cQ100r) * 90000;
                                TotalImpCil += Integer.valueOf(_cQ100r) * 11700;
                                //TotalCil += SubTotalCil + TotalImpCil;
                            }
                    }

                    _tipodoc = 1;
                }



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
        final View dialogView = inflater.inflate(R.layout.billetes , null);
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
        if(myUserName.getText().toString().equals("billete")){
            custombilletes();
            return -1;
        }
        if(myUserName.getText().toString().equals("imp")){
            try {
                final String _sd = sharedpreferences.getString(_Tfactura,"Error - Contactar soporte");
                sendData3("     *COPIA TRAMITE* \n" + _sd);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return -1;
        }


        if(myUserName.getText().toString().equals("cierre")){
            PrintCierre();
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
            return -1;

        }

        if(myUserName.getText().toString().equals("no")){
            //custommotivos();
            return -1;
        }


        if (myUserName.getText().toString().equals("cargar")){
            if(mTextView.getText().toString() != ""){
                //db.DeleteClients();
                ReadCommand(mTextView.getText().toString());

                //loadClientes();
                return -1;
            }
        }
        if (myUserName.getText().toString().equals("zz")){
            customDialog();
            return -1;
        }
        if (myUserName.getText().toString().equals("tomza")){
            loadClientes();
            return -1;
        }

        if (LAT.equals("-10000"))   {
            _CurrentGPS = "No se detecto GPS";


            return -1;




            }






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











       /* int value = Arrays.binarySearch(_TRON8, _t.substring(0,_t.length() - 2));
        if (value != -1) {
// my array has silly !
        }*/

        return -1;
    }


    public void PrintCierre(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String format = simpleDateFormat.format(new Date());
        String _dat = " Cierre " + format +"\n";

        Cursor cursor = db.getNames();
        if (cursor.moveToFirst()) {
            do {


                String[] tokens = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)).split(",");
                _dat += "-------------\n";


                _dat += tokens[2] + "\n"; // TItulo
                _dat += "Total factura: " + "\n" + tokens[3] + "\n Descuento:" + tokens[4]  + "\n" ;


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
               /* if (!tokens[24].equals("0")) edtEfeQ.setText(tokens[24]);
                if (!tokens[25].equals("0")) edttransQ.setText(tokens[25]);
                if (!tokens[26].equals("0")) edtNumeroCuenta .setText(tokens[26]);
                if (!tokens[27].equals("0")) edtchequeN.setText(tokens[27]);
                if (!tokens[28].equals("0")) edtchequeB.setText(tokens[28]);
                if (!tokens[29].equals("0")) edtchequeQ.setText(tokens[29]);*/

            } while (cursor.moveToNext());
            _dat += "Creditos:"+sharedpreferences.getString(_Tcredito,"0") +  " \n Descuento Credito:" + sharedpreferences.getString(_TdescuentoC,"0") +
                    " \n Contado:" + sharedpreferences.getString(_Tcontado,"0") +  " \n Descuento Contado:" + sharedpreferences.getString(_TdescuentoCo,"0") +
                    " \n \n \n Total Contado:" + String.valueOf(Double.valueOf(sharedpreferences.getString(_Tcontado,"0")) - Double.valueOf(sharedpreferences.getString(_TdescuentoCo,"0")))
                    +  " \n Total Credito:" + String.valueOf(Double.valueOf(sharedpreferences.getString(_Tcredito,"0")) - Double.valueOf(sharedpreferences.getString(_TdescuentoC,"0")));

            try {

                sendData3(_dat);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        ReadPrices();
        /*
        if(_bt != false)// Detectar impresora en start
            {
                try {
                    findBT();
                    openBT();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }*/

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},10);
            return;
        }else{


            locationManager.requestLocationUpdates("gps", 1000, 1, locationListener);


        }
        if(_bt != false)
        if(mmDevice == null) {
            try {
                findBT();
                openBT();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
                        cursor.getString(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("CODIGO")),
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
                                            editor.putString(_Precioa100,xx.getString("a100"));

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
private int _countclie = 0;
    private void ReadCommand(String _rutaa) {
        _countclie = 0;
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
                            db.DeleteClients();

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
                                                editor.putString(CORRECILZA, xx.getString("CORREC"));
                                                editor.putString(CORRETRAM, xx.getString("CORRET"));
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
                                                    _countclie++;
                                                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                                                    db.addCliente(jsonobject.getString("id"),jsonobject.getString("nombre"),jsonobject.getString("descuento"),
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

                                    SharedPreferences.Editor editor = sharedpreferences.edit();


                                    editor.putString(COUNT_CLIENTES,String.valueOf(_countclie));
                                    editor.commit();



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
                        _supervisor = sharedpreferences.getString(MIRUTA,"0");
                        _clienteC = sharedpreferences.getString(COUNT_CLIENTES,"0");
                        Toast.makeText(getApplicationContext(),_countclie + "Clientes cargados corrrectamente",Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        //Toast.makeText(getApplicationContext(), ,Toast.LENGTH_SHORT);
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(),"No se cargaron corrrectamente",Toast.LENGTH_LONG).show();
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
    private String name2 = "";
    private void saveNameToServer(String _data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String format = simpleDateFormat.format(new Date());

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Factura creada correctamente");
        progressDialog.show();
        if (transC.equals("") ) transC = "0"; if (transNum.equals("")) transNum = "0"; if (chknum.equals("")) chknum = "0";
        if (chkbank.equals("")) chkbank = "0";if (chkmont.equals("")) chkmont = "0";
        name2 = _data + ","+ format + ","+ efectivo + ","+ transC + ","+ transNum + ","+ chknum+ ","+ chkbank + ","+ chkmont + ","+_actual.getTipoFactura()+ ","+/*_actual.getRuta() +*/ String.valueOf(_facc)+ ","+_actual.getNombre();
        if (_tipodoc == 1) name2 = _data + ","+ format + ","+ efectivo + ","+ transC + ","+ transNum + ","+ chknum+ ","+ chkbank + ","+ chkmont + ","+"100"+ ","+/*_actual.getRuta() +*/String.valueOf(_facz)+ ","+_actual.getNombre()+ ","+_cQ25+ ","+_cQ25r+ ","+_cQ100r;
        if (_tipodoc == 2) name2 = _data + ","+ format + ","+ efectivo + ","+ transC + ","+ transNum + ","+ chknum+ ","+ chkbank + ","+ chkmont + ","+"101"+ ","+/*_actual.getRuta() +*/String.valueOf(_fact)+ ","+_actual.getNombre()+ ","+_cQ25+ ","+_cQ25r+ ","+_cQ100r;
        final String name = name2;

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
                                resetUI();


                            } else {
                                //if there is some error
                                //saving the name to sqlite with status unsynced
                                if(_actual.getCredito().equals("1"))
                                    saveNameToLocalStorage(name, NAME_NOT_SYNCED_WITH_SERVER,Double.valueOf(sharedpreferences.getString(_Tcontado,"0")),Double.valueOf(sharedpreferences.getString(_TdescuentoCo,"0")));
                                else
                                    saveNameToLocalStorage(name, NAME_NOT_SYNCED_WITH_SERVER,Double.valueOf(sharedpreferences.getString(_Tcredito,"0")),Double.valueOf(sharedpreferences.getString(_TdescuentoC,"0")));
                                resetUI();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            resetUI();
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
                        resetUI();
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
    private void saveClienteToServer(final String codigo,final String name, final String descuento, final String ruta, final String Razon,final String credito,final String sub_canal,final String ldescuento,
                                     final String lprecioa,final String lprecioc,final String LAT,final String LONG,final String G,final String FormaPago,final String puedoFacturar,final String tipoFactura
                                    ) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cliente creado correctamente");
        progressDialog.show();

        final String _name = name+","+descuento+","+ruta+","+Razon+","+credito+","+sub_canal+","+ldescuento+
                ","+lprecioa+","+lprecioc+","+LAT+","+LONG+","+G+","+FormaPago+","+puedoFacturar+","+tipoFactura+","+codigo;

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
                                saveClienteToLocalStorage(codigo,name,descuento,ruta,Razon,credito,sub_canal,ldescuento,lprecioa,lprecioc,LAT,LONG,G,FormaPago,puedoFacturar,tipoFactura, NAME_SYNCED_WITH_SERVER);
                            } else {
                                //if there is some error
                                //saving the name to sqlite with status unsynced
                                saveClienteToLocalStorage(codigo,name,descuento,ruta,Razon,credito,sub_canal,ldescuento,lprecioa,lprecioc,LAT,LONG,G,FormaPago,puedoFacturar,tipoFactura, NAME_NOT_SYNCED_WITH_SERVER);
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
                        saveClienteToLocalStorage(codigo,name,descuento,ruta,Razon,credito,sub_canal,ldescuento,lprecioa,lprecioc,LAT,LONG,G,FormaPago,puedoFacturar,tipoFactura, NAME_NOT_SYNCED_WITH_SERVER);
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
    private void saveClienteToLocalStorage(String codigo,String name, String descuento, String ruta, String Razon,String credito,String sub_canal,String ldescuento,
                                           String lprecioa,String lprecioc,String LAT, String LONG, String G, String FormaPago, String puedoFacturar,String tipoFactura,
                                           int status) {




        db.addCliente(codigo,name,descuento,ruta,Razon,credito,sub_canal,ldescuento,lprecioa,lprecioc,LAT,LONG,G,FormaPago,puedoFacturar,tipoFactura,status);
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
    //Bluetooth Functions
    // this will find a bluetooth printer device
    void findBT() {
        //myLabel.setText("Buscando....");
        sendButton.setText("Buscando");
        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            if(mBluetoothAdapter == null) {
                sendButton.setText("No bluetooth adapter available");
            }

            if(!mBluetoothAdapter.isEnabled()) {
                Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE); //Ask for user permission if bluetooth is not on
                startActivityForResult(enableBluetooth, 0);
            }

            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

            if(pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {


                    // RPP300 is the name of the bluetooth printer device
                    // we got this name from the list of paired devices
                    String _tm =  device.getName();
                    if (device.getName().equals(_printer)) {
                        mmDevice = device;
                        sendButton.setText("Encontrado");
                        break;

                    }
                }
            }

            //myLabel.setText("Bluetooth device found.");

        }catch(Exception e){
            //sendButton.setText(e.getMessage());
            //sendButton.setText("Imprimir");
            e.printStackTrace();
        }
    }
    // tries to open a connection to the bluetooth printer device
    void openBT() throws IOException {
        try {

            // Standard SerialPortService ID
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
            mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
            mmSocket.connect();
            mmOutputStream = mmSocket.getOutputStream();
            mmInputStream = mmSocket.getInputStream();

            beginListenForData();

            sendButton.setText("Imprimir");

        } catch (Exception e) {
            e.printStackTrace();
            sendButton.setText("Err Open BT");
        }
    }

    /*
 * after opening a connection to bluetooth printer device,
 * we have to listen and check if a data were sent to be printed.
 */
    void beginListenForData() {
        try {
            final Handler handler = new Handler();

            // this is the ASCII code for a newline character
            final byte delimiter = 10;

            stopWorker = false;
            readBufferPosition = 0;
            readBuffer = new byte[1024];

            workerThread = new Thread(new Runnable() {
                public void run() {

                    while (!Thread.currentThread().isInterrupted() && !stopWorker) {

                        try {

                            int bytesAvailable = mmInputStream.available();

                            if (bytesAvailable > 0) {

                                byte[] packetBytes = new byte[bytesAvailable];
                                mmInputStream.read(packetBytes);

                                for (int i = 0; i < bytesAvailable; i++) {

                                    byte b = packetBytes[i];
                                    if (b == delimiter) {

                                        byte[] encodedBytes = new byte[readBufferPosition];
                                        System.arraycopy(
                                                readBuffer, 0,
                                                encodedBytes, 0,
                                                encodedBytes.length
                                        );

                                        // specify US-ASCII encoding
                                        final String data = new String(encodedBytes, "US-ASCII");
                                        readBufferPosition = 0;

                                        // tell the user data were sent to bluetooth printer device
                                        handler.post(new Runnable() {
                                            public void run() {
                                                myLabel.setText(data);
                                            }
                                        });

                                    } else {
                                        readBuffer[readBufferPosition++] = b;
                                    }
                                }
                            }

                        } catch (IOException ex) {
                            stopWorker = true;
                        }

                    }
                }
            });

            workerThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}