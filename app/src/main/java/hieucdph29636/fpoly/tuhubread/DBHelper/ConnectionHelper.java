package hieucdph29636.fpoly.tuhubread.DbHelper;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    Connection connection;
    String user,pass,ip,port,database;
    @SuppressLint("NewApi")
    public Connection connectionClass(){
        {
            ip ="192.168.100.30";
            database = "TuhuBread";
            user ="tuhubread";
            pass="123456";
            port="1433";

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Connection connection = null;
            String ConnectionURL = null ;
            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                ConnectionURL= "jdbc:jtds:sqlserver://"+ ip + ":"+ port+";"+ "databasename="+ database+";user="+user+";password="+pass+";";
                connection = DriverManager.getConnection(ConnectionURL);
            }catch (Exception ex){
                Log.e("Error", ex.getMessage() );
            }
            return connection;
        }
    }
}
