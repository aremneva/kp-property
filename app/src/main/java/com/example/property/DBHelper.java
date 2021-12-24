package com.example.property;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    private Context context;

    public static final String LOG_TAG="MAIN";

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
    public static final String COLUMN_EMAIL="email";


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
                +COLUMN_PASSWORD+ " VARCHAR, "
                +COLUMN_EMAIL+ " VARCHAR);";
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



        ContentValues cv = new ContentValues();
        ContentValues cv1 = new ContentValues();
        Resources res_houses = context.getResources();
        Resources res_agency=context.getResources();


        //Загрузка данных из XML в таблицы агенства и дома
        XmlResourceParser _xml_agency=res_agency.getXml(R.xml.agency_record);
        XmlResourceParser _xml_houses=res_agency.getXml(R.xml.houses_record);

        try{
            int enTypeAgency = _xml_agency.getEventType();
            while (enTypeAgency!= XmlPullParser.END_DOCUMENT){
                if((enTypeAgency==XmlPullParser.START_TAG)&&(_xml_agency.getName().equals("record"))){
                    String name = _xml_agency.getAttributeValue(1);
                    String description = _xml_agency.getAttributeValue(0);
                    String year = _xml_agency.getAttributeValue(4);
                    String phone = _xml_agency.getAttributeValue(2);
                    String site = _xml_agency.getAttributeValue(3);

                    cv.put(COLUMN_NAME_AGENCY,name);
                    cv.put(COLUMN_DESCRIPTION_AGENCY,description);
                    cv.put(COLUMN_YEAR_AGENCY,year);
                    cv.put(COLUMN_PHONE_AGENCY,phone);
                    cv.put(COLUMN_SITE_AGENCY,site);
                    db.insert(TABLE_AGENCY,null,cv);
                }
                enTypeAgency=_xml_agency.next();
            }

            int enTypeHouses = _xml_houses.getEventType();
            while (enTypeHouses!= XmlPullParser.END_DOCUMENT){
                if((enTypeHouses==XmlPullParser.START_TAG)&&(_xml_houses.getName().equals("record"))){
                    String year = _xml_houses.getAttributeValue(3);
                    String elevator = _xml_houses.getAttributeValue(0);
                    String entrance = _xml_houses.getAttributeValue(1);
                    String heating = _xml_houses.getAttributeValue(2);

                    cv1.put(COLUMN_YEAR,year);
                    cv1.put(COLUMN_ELEVATOR,elevator);
                    cv1.put(COLUMN_ENTRANCE,entrance);
                    cv1.put(COLUMN_HEATING,heating);

                    db.insert(TABLE_HOUSE,null,cv1);
                }
                enTypeHouses=_xml_houses.next();
            }
            //https://drive.google.com/file/d/1RWzPqF5yuO0zXdvka8-iT_Jp60K6APqQ/view?usp=sharing
        }
        catch(Exception e){
         Log.d(LOG_TAG,e.getMessage(),e);
        }
        finally {
            _xml_agency.close();
            _xml_houses.close();
        }

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

    void getAllFromProperty(ArrayList<Property> arrayList){ //Получение всех записей для вывода
    //ДОДЕЛЫВАТЬ ТУТ
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_PROPERTY+" ;";

        SQLiteDatabase db2 = this.getWritableDatabase();
        String query2 = "SELECT * FROM "+TABLE_IMG_PROPERTY+" ;"; //Тут сделать по-другому, в зависимости от прошлого запроса?

        Cursor c=db.rawQuery(query,null);
        Cursor c2=db.rawQuery(query2,null);

        c.moveToFirst();
        c2.moveToFirst();

        do {
            // что-то страшное
            arrayList.add(new Property(c.getColumnIndex(COLUMN_ID_PROPERTY),
                    c.getString(c.getColumnIndex(COLUMN_ADDRESS)),
                    c.getDouble(c.getColumnIndex(COLUMN_PRICE)),
                    c.getString(c.getColumnIndex(COLUMN_METRO)),
                    c.getDouble(c.getColumnIndex(COLUMN_AREA)),
                    c.getInt(c.getColumnIndex(COLUMN_ROOMS)),
                    c.getInt(c.getColumnIndex(COLUMN_FLOOR)),
                    c.getInt(c.getColumnIndex(COLUMN_ID_USER)),
                    c.getInt(c.getColumnIndex(COLUMN_ID_AGENCY)),
                    c.getInt(c.getColumnIndex(COLUMN_ID_HOUSE)),
                    c2.getString(c2.getColumnIndex(COLUMN_IMAGE_PROPERTY)),
                    c2.getString(c2.getColumnIndex(COLUMN_DESCRIPTION_IMAGE))));
        } while (c.moveToNext()&&c2.moveToNext());

    }

    void addUser(String login, String password, String email){ //Добавление пользователя
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_LOGIN,login);
        cv.put(COLUMN_PASSWORD,password);
        cv.put(COLUMN_EMAIL,email);

        long res2 = db.insert(TABLE_USER,null,cv);
        if ((res2==-1)){    //Проверка на добавление пользователя
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
        }
    }

    void addHouse(String year,String elevator, String entrance,String heating){ //Добавление дома
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(COLUMN_YEAR,year);
        cv1.put(COLUMN_ELEVATOR,elevator);
        cv1.put(COLUMN_ENTRANCE,entrance);
        cv1.put(COLUMN_HEATING,heating);
        long res = db.insert(TABLE_HOUSE,null,cv1);
        checkOnFail(res);
    }

    int getCountUser(String login){ //Количество пользователей с заданным логином
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_USER+" WHERE "+COLUMN_LOGIN+" = '"+login+"' ;";
        Cursor c2=db.rawQuery(query,null);
        int count=c2.getCount();
        return count;
    }

    void getUser(String login){ //Получение пользователя по логину
        SQLiteDatabase db = this.getWritableDatabase();
        try{
        String query = "SELECT * FROM "+TABLE_USER+" WHERE "+COLUMN_LOGIN+" = '"+login+"' ;";
        Cursor c2=db.rawQuery(query,null);
        c2.moveToFirst();
        Log.d(LOG_TAG,"count: "+c2.getCount()+" c2:  login "+c2.getColumnIndex(COLUMN_LOGIN)+" pas: "+c2.getColumnIndex(COLUMN_PASSWORD));
        }catch (Exception e){
            Log.d(LOG_TAG,"Exception: "+ e);
        }
    }

    boolean againstPassword(String login,String password){ //Проверка корректности введенного пароля
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_USER+" WHERE "+COLUMN_LOGIN+" = '"+login+"' ;";
        Cursor c2=db.rawQuery(query,null);
       c2.moveToFirst();
        if(password.equals(c2.getString(c2.getColumnIndex(COLUMN_PASSWORD)))){
            return true;
        }
        else{
            return false;
        }

    }

    void addProperty(String desc,String address, String price,String metro, String area, String rooms, String floor, int id_user,int id_agency, int id_house){ //Добавление недвижимости
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(COLUMN_PROPERTY_DESCRIPTION,desc);
        cv1.put(COLUMN_ADDRESS,address);
        cv1.put(COLUMN_PRICE,price);
        cv1.put(COLUMN_METRO,metro);
        cv1.put(COLUMN_AREA,area);
        cv1.put(COLUMN_FLOOR,floor);
        cv1.put(COLUMN_ID_USER,id_user);
        cv1.put(COLUMN_ID_AGENCY,id_agency);
        cv1.put(COLUMN_ID_HOUSE,id_house);
        long res = db.insert(TABLE_PROPERTY,null,cv1);
        checkOnFail(res);

    }

    void addImageToProperty(String image, int id_property,String desc){ //Добавление изображения
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(COLUMN_IMAGE_PROPERTY,image);
        cv1.put(COLUMN_DESCRIPTION_IMAGE,desc);
        cv1.put(COLUMN_ID_PROPERTY,id_property);

        long res = db.insert(TABLE_IMG_PROPERTY,null,cv1);
        checkOnFail(res);
    }

    void checkOnFail(long result){ //Успешно ли добавлены данные
        if (result==-1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }

    }

    int getIdPropertyByAddress(String address){ //ID недвижимости по ее адресу
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_PROPERTY+" WHERE "+COLUMN_ADDRESS+" = '"+address+"' ;";
        Cursor c2=db.rawQuery(query,null);
        c2.moveToFirst();
        Log.d(LOG_TAG,"ID_PROPERTY: "+ c2.getInt(c2.getColumnIndex(COLUMN_ID_PROPERTY)));
        return c2.getInt(c2.getColumnIndex(COLUMN_ID_PROPERTY));
    }

    public long addImage(String url, String id_property)  //Что-то старое
    {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues cv=new ContentValues();
            cv.put(COLUMN_IMAGE_PROPERTY,url);
            cv.put(COLUMN_ID_PROPERTY,id_property);
            db.insert(TABLE_IMG_PROPERTY,null,cv);

            return 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setImage(ImageView view, String path,String id_property){ //Это тоже старое
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM "+TABLE_IMG_PROPERTY+" WHERE "+COLUMN_ID_PROPERTY+" = '"+id_property+"' ;";
        Cursor c2=db.rawQuery(query,null);
        c2.moveToFirst();

        //c2.getString(c2.getColumnIndex(COLUMN_IMAGE_PROPERTY));

        Picasso.with(context)
                .load(c2.getString(c2.getColumnIndex(COLUMN_IMAGE_PROPERTY)))
                .into(view);

        c2.close();
    }
}
