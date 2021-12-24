package com.example.property;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    public  static final String DATABASE_NAME="Property.dp";
    public static final int DATABASE_VERSION=1;
    public static final String TABLE_PROPERTY="property";

    public static final String COLUMN_ID_PROPERTY="id_property";
    public static final String COLUMN_PROPERTY_DESCRIPTION="description";
    public static final String COLUMN_ADDRESS="address";
    public static final String COLUMN_PRICE="price";
    public static final String COLUMN_METRO = "metro";
    public static final String COLUMN_AREA="area";
    public static final String COLUMN_ROOMS="rooms";
    public static final String COLUMN_FLOOR="floor";
    public static final String COLUMN_ID_USER="id_user";
    public static final String COLUMN_ID_AGENCY="id_agency";
    public static final String COLUMN_ID_HOUSE="id_house";

    public static final String TABLE_IMG_PROPERTY="img_property";

    public static final String COLUMN_ID_IMG_PROPERTY="id_img_property";
    public static final String COLUMN_IMAGE_PROPERTY="image_property";
    public static final String COLUMN_DESCRIPTION_IMAGE="description_image";

    public static final String TABLE_HOUSE="house";
    public static final String COLUMN_YEAR="year";
    public static final String COLUMN_ELEVATOR="elevator";
    public static final String COLUMN_ENTRANCE="entrance";
    public static final String COLUMN_HEATING="heating";

    public static final String TABLE_AGENCY="agency";

    public static final String COLUMN_NAME_AGENCY="name_agency";
    public static final String COLUMN_DESCRIPTION_AGENCY="description_agency";
    public static final String COLUMN_YEAR_AGENCY="year_agency";
    public static final String COLUMN_PHONE_AGENCY="phone_agency";
    public static final String COLUMN_SITE_AGENCY="site_agency";

    public static final String TABLE_USER="user";
    public static final String COLUMN_LOGIN="login";
    public static final String COLUMN_PASSWORD="password";


    public static final String TABLE_USER_DETAIL="user_detail";

    public static final String COLUMN_ID_USER_DETAIL="id_user_detail";
    public static final String COLUMN_USER_SURNAME="user_surname";
    public static final String COLUMN_USER_NAME="user_name";
    public static final String COLUMN_USER_PATRONYMIC="user_patronymic";
    public static final String COLUMN_USER_PHONE="user_phone";
    public static final String COLUMN_USER_IMAGE="user_image";

    public static final String TABLE_USERS_FAV="users_fav";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createHouse="CREATE TABLE "+TABLE_HOUSE+" ("
                +COLUMN_ID_HOUSE+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COLUMN_YEAR+ " INTEGER, "
                +COLUMN_ELEVATOR+ " INTEGER, "
                +COLUMN_ENTRANCE+ " INTEGER, "
                + COLUMN_HEATING+" VARCHAR );";
        db.execSQL(createHouse);

        String createAgency="CREATE TABLE "+TABLE_AGENCY+" ("
                +COLUMN_ID_AGENCY+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COLUMN_NAME_AGENCY+ " VARCHAR, "
                +COLUMN_DESCRIPTION_AGENCY+ " VARCHAR, "
                +COLUMN_YEAR_AGENCY+ " INTEGER, "
                + COLUMN_PHONE_AGENCY+" VARCHAR, " +
                COLUMN_SITE_AGENCY+" VARCHAR);";
        db.execSQL(createAgency);



        String createUser="CREATE TABLE "+TABLE_USER+" ("
                +COLUMN_ID_USER+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COLUMN_LOGIN+ " VARCHAR, "
                +COLUMN_PASSWORD+ " VARCHAR )";
        db.execSQL(createUser);

        String createUserDetail="CREATE TABLE "+TABLE_USER_DETAIL+" ("
                +COLUMN_ID_USER_DETAIL+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COLUMN_ID_USER+ " INTEGER NOT NULL, "
                +COLUMN_USER_SURNAME+ " VARCHAR, "
                +COLUMN_USER_NAME+ " VARCHAR, "
                +COLUMN_USER_PATRONYMIC+ " VARCHAR, "
                + COLUMN_USER_PHONE+" VARCHAR, " +
                COLUMN_USER_IMAGE+" VARCHAR, "+
                "FOREIGN KEY ( "+COLUMN_ID_USER+" ) REFERENCES "+TABLE_USER+"( "+COLUMN_ID_USER+" ));";
        db.execSQL(createUserDetail);

        String createProperty="CREATE TABLE "+TABLE_PROPERTY+" ("
                +COLUMN_ID_PROPERTY+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COLUMN_PROPERTY_DESCRIPTION+ " VARCHAR, "
                +COLUMN_ADDRESS+ " VARCHAR, "
                +COLUMN_PRICE+ " DOUBLE, "
                +COLUMN_METRO+ " VARCHAR, "
                +COLUMN_AREA+" DOUBLE, "
                +COLUMN_ROOMS+" INTEGER, "
                +COLUMN_FLOOR+ " INTEGER, "
                +COLUMN_ID_USER+" INTEGER NOT NULL, "
                +COLUMN_ID_AGENCY+ " INTEGER NOT NULL, "
                +COLUMN_ID_HOUSE+ " INTEGER NOT NULL, " +
                "FOREIGN KEY ( "+COLUMN_ID_USER+" ) REFERENCES "+TABLE_USER+"( "+COLUMN_ID_USER+" ), " +
                "FOREIGN KEY ( "+COLUMN_ID_AGENCY+" ) REFERENCES "+TABLE_AGENCY+"( "+COLUMN_ID_AGENCY+" ), " +
                "FOREIGN KEY ( "+COLUMN_ID_HOUSE+" ) REFERENCES "+TABLE_HOUSE+"( "+COLUMN_ID_HOUSE+" ));";
        db.execSQL(createProperty);

        String createImgProperty="CREATE TABLE "+TABLE_IMG_PROPERTY+" ("
                +COLUMN_ID_IMG_PROPERTY+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COLUMN_ID_PROPERTY+ " INTEGER NOT NULL, "
                +COLUMN_IMAGE_PROPERTY+ " VARCHAR, "
                +COLUMN_DESCRIPTION_IMAGE+ " VARCHAR, " +
                "FOREIGN KEY ( "+COLUMN_ID_PROPERTY+" ) REFERENCES "+TABLE_PROPERTY+"( "+COLUMN_ID_PROPERTY+" ));";
        db.execSQL(createImgProperty);

        String createUsersFav="CREATE TABLE "+TABLE_USERS_FAV+" ("
                +COLUMN_ID_USER+ " INTEGER NOT NULL, "
                +COLUMN_ID_PROPERTY+ " INTEGER NOT NULL, " +
                "FOREIGN KEY ( "+COLUMN_ID_USER+" ) REFERENCES "+TABLE_USER+"( "+COLUMN_ID_USER+" )," +
                "FOREIGN KEY ( " +COLUMN_ID_PROPERTY+" ) REFERENCES "+TABLE_PROPERTY+"( "+ COLUMN_ID_PROPERTY+ " ));";
        db.execSQL(createUsersFav);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PROPERTY);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS_FAV);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER_DETAIL);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_AGENCY);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_HOUSE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_IMG_PROPERTY);

        onCreate(db);
    }

    void addUser(String login, String password, String name, String surname, String patronymic, String phone ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        ContentValues cv2 = new ContentValues();
        //cv1.put(COLUMN_USER_NAME,name);
        //cv1.put(COLUMN_USER_SURNAME,surname);
        //cv1.put(COLUMN_USER_PATRONYMIC,patronymic);
        //cv1.put(COLUMN_USER_PHONE,phone);


        cv2.put(COLUMN_LOGIN,login);
        cv2.put(COLUMN_PASSWORD,password);
        //Забрать отсюда id деталей и передать в cv2
       // long res = db.insert(TABLE_USER_DETAIL,null,cv1);
        Log.d("MAIN","Elogin: "+login);
        long res2 = db.insert(TABLE_USER,null,cv2);
        Log.d("MAIN","res2: "+res2);
        if ((res2==-1)){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
        }
    }
    void addHouse(String year,String elevator, String entrance,String heating){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(COLUMN_YEAR,year);
        cv1.put(COLUMN_ELEVATOR,elevator);
        cv1.put(COLUMN_ENTRANCE,entrance);
        cv1.put(COLUMN_HEATING,heating);
        long res = db.insert(TABLE_HOUSE,null,cv1);
        if (res==-1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }
    }
    void getUser(String login){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_USER,null);
        c.moveToFirst();
        Log.d("MAIN","user: "+c.getString(1));
    }
}
