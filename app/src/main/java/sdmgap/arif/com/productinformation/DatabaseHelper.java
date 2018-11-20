package sdmgap.arif.com.productinformation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Product.db";
    public static final String TABLE_NAME = "product_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "BRAND";
    public static final String COL_4 = "CODE";
    public static final String COL_5 = "COLOR";
    public static final String COL_6 = "SIZE";
    public static final String COL_7 = "PRICE";
    public static final String COL_8 = "DESCRIPTION";
    public static final String COL_9 = "TYPE";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT,BRAND TEXT,CODE TEXT,COLOR TEXT,SIZE TEXT,PRICE TEXT,DESCRIPTION TEXT, TYPE TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData (String name, String brand, String code, String color, String size, String price, String description, String type){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,brand);
        contentValues.put(COL_4,code);
        contentValues.put(COL_5,color);
        contentValues.put(COL_6,size);
        contentValues.put(COL_7,price);
        contentValues.put(COL_8,description);
        contentValues.put(COL_9,type);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery("select * from "+TABLE_NAME,null);
        return result;
    }


    public boolean updateData (String id, String name, String brand, String code, String color, String size, String price, String description, String type){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,brand);
        contentValues.put(COL_4,code);
        contentValues.put(COL_5,color);
        contentValues.put(COL_6,size);
        contentValues.put(COL_7,price);
        contentValues.put(COL_8,description);
        contentValues.put(COL_9,type);

        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });

        return true;
    }

    public Integer deleteData (String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
}
