package mundroid.apps.basicbankingapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object {
        private const val TAG = "DatabaseHandler"

        private const val DATABASE_NAME = "UserDatabase"
        private const val DATABASE_VERSION = 1
        private const val TABLE_CONTACTS = "UserTable"

        private const val KEY_ID = "_id"
        private const val KEY_name = "name"
        private const val KEY_email = "email"
        private const val KEY_personalID = "personalID"
        private const val KEY_currentBalance = "currentBalance"
        private const val KEY_cvv = "csv"
        private const val KEY_cardNum = "cardNum"
        private const val KEY_cardType = "cardType"
    }

    //creating table with fields

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE =
            ("Create Table" + TABLE_CONTACTS + "(" + KEY_ID + "INTEGER PRIMARY KEY," + KEY_name + " TEXT,"
                    + KEY_email + " TEXT," + KEY_personalID + " INTEGER,"
                    + KEY_currentBalance + " TEXT," + KEY_cvv + " INTEGER," + KEY_cardNum + " INTEGER," + KEY_cardType + " TEXT," + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db)
    }

    // add user
    fun addUser(user: User): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_name, user.name) // inserting name
        contentValues.put(KEY_email, user.email) // inserting email
        contentValues.put(KEY_personalID, user.personalID) // inserting personalID
        contentValues.put(KEY_currentBalance, user.currentBalance) // inserting currentBalance
        contentValues.put(KEY_cvv, user.cvv) // inserting cvv
        contentValues.put(KEY_cardNum, user.cardNum) // inserting cardNum
        contentValues.put(KEY_cardType, user.cardType) // inserting cardType


        // inserting row
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        db.close()
        return success
    }


    // add user
    fun updateUser(user: User): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_name, user.name) // inserting name
        contentValues.put(KEY_email, user.email) // inserting email
        contentValues.put(KEY_personalID, user.personalID) // inserting personalID
        contentValues.put(KEY_currentBalance, user.currentBalance) // inserting currentBalance
        contentValues.put(KEY_cvv, user.cvv) // inserting cvv
        contentValues.put(KEY_cardNum, user.cardNum) // inserting cardNum
        contentValues.put(KEY_cardType, user.cardType) // inserting cardType


        // inserting row
        val success = db.update(TABLE_CONTACTS, contentValues, KEY_ID + "=" + user.id, null )
        db.close()
        return success
    }

    //read data
    @SuppressLint("Range")
    fun getData(): ArrayList<User> {
        val userList: ArrayList<User> = ArrayList()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var email: String
        var currentBalance: Int
        var personalID: Long
        var cvv: Int
        var cardNum: Long
        var cardType: String
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                name = cursor.getString(cursor.getColumnIndex(KEY_name))
                email = cursor.getString(cursor.getColumnIndex(KEY_email))
                currentBalance = cursor.getInt(cursor.getColumnIndex(KEY_currentBalance))
                personalID = cursor.getLong(cursor.getColumnIndex(KEY_personalID))
                cvv = cursor.getInt(cursor.getColumnIndex(KEY_cvv))
                cardNum = cursor.getLong(cursor.getColumnIndex(KEY_cardNum))
                cardType = cursor.getString(cursor.getColumnIndex(KEY_cardType))
                val user = User(
                    id,
                    name,
                    email,
                    currentBalance,
                    personalID,
                    cvv,
                    cardNum,
                    cardType
                )
                userList.add(user)
            } while (cursor.moveToFirst())
        }
        return userList
    }
}