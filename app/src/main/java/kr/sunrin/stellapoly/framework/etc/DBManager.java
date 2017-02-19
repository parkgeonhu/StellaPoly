package kr.sunrin.stellapoly.framework.etc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by 건후 on 2015-08-25.
 */
public class DBManager {

    public static DBManager mInstance;
    public MySQLiteHelper helper;
    public SQLiteDatabase db;
    public static boolean state = false;

    public static DBManager getInstance(){
        if(mInstance == null){
            mInstance = new DBManager();
            state = true;
        }
        return mInstance;
    }

    public void initHelper(Context context)
    {
        if(helper == null) {
            helper = new MySQLiteHelper(context, "stellapoly.db", null, 1);
            Log.d("Init Helper Success", "GOODD!!!");
        }
        else {
            return;
        }

    }

    public void insert(int time)
    {
        String table = "STELLA";
        db = helper.getWritableDatabase();
        Log.d("Insert DB Values", String.valueOf(time));
        ContentValues values = new ContentValues();
        values.put("time", time);
        Log.d("Insert " + time + "Success", String.valueOf(time));
        db.insert("STELLA",null, values);
    }

    public void update(int target, String text)
    {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("text", text);
        db.update("STELLA",
                values,
                "num=?",
                new String[]{String.valueOf(target)});
        Log.d("Update " + target + "Success", text);
    }

    public void delete(int target)
    {
        db = helper.getWritableDatabase();
        db.delete("STELLA",
                "day=?",
                new String[]{String.valueOf(target)});
        Log.d("SQLITE DELETE", String.valueOf(target));
    }

    public String select(int target)
    {
        db = helper.getReadableDatabase();
        Cursor c = db.query("STELLA",
                null,
                null,
                null,
                null,
                null,
                null);
        String text = "";
        while(c.moveToNext())
        {
            int num = c.getInt(c.getColumnIndex("time"));

            if(num == target)
            {
                text = c.getString(c.getColumnIndex("id"));
                Log.d("SQL SELECT", target + ": " + String.valueOf(text));
                break;
            }
        }
        return text;

    }

    public int[] selectTop()
    {
        db = helper.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT id, time FROM STELLA order by time desc limit 3", null);

        int[] buf = new int[3];
        int pivot = 0;
        result.moveToFirst();

        while (!result.isAfterLast()){
            int id = result.getInt(0);
            int time = result.getInt(1);
            Log.d("SELCETTOP", String.valueOf(time));
            buf[pivot++] = time;

            result.moveToNext();
        }
        result.close();
        return buf;
    }
}
