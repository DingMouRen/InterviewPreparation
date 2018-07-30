package com.dingmouren.interviewpreparation.z_classes;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by dingmouren on 2018/7/28.
 */

public class MyContentProvider extends ContentProvider {

    public static final int TABLE_1_DIR = 0;

    public static final int TABLE_1_ITEM = 1;

    public static final int TABLE_2_DIR = 2;

    public static final int TABLE_2_ITEM = 3;

    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.app.provider","table1",TABLE_1_DIR);
        uriMatcher.addURI("com.example.app.provider","table1/#",TABLE_1_ITEM);
        uriMatcher.addURI("com.example.app.provider","table2",TABLE_2_DIR);
        uriMatcher.addURI("com.example.app.provider","table2/#",TABLE_2_ITEM);
    }

    /**
     * 初始化内容提供器时调用，返回false表示失败,返回true表示成功
     * 只有ContentResolver尝试访问我们程序中的数据时，才会被初始化
     * @return
     */
    @Override
    public boolean onCreate() {
        return false;
    }

    /**
     * 从内容提供器中查询数据，查询的结果存放在Cursor对象中返回
     * @param uri 指定查询的哪张表
     * @param projection 指定查询哪些列
     * @param selection 用于约束查询哪些行
     * @param selectionArgs 用于约束查询哪些行
     * @param sortOrder 用于对结果进行排序
     * @return
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (uriMatcher.match(uri)){
            case TABLE_1_DIR:
                /*查询table1表中所有的数据*/
                break;
            case TABLE_1_ITEM:
                /*查询table1表中的单条数据*/
                break;
            case TABLE_2_DIR:
                /*查询table2表中所有的数据*/
                break;
            case TABLE_2_ITEM:
                /*查询table2表中的单条数据*/
                break;
            default:
                break;
        }
        return null;
    }

    /**
     * 根据传入内容URI返回相应的MIME类型
     * 必须提供的方法
     * 1.必须以vnd开头
     * 2.如果内容URI以路径结尾，后接android.cursor.dir/,如果内容URI以id结尾，则后接android.cursor.item/
     * 3.最后接上vnd.包名.provider.表名
     * content://com.example.app.provider/table1 => vnd.android..cursor.dir/vnd.com.example.app.provider.table1
     * @param uri
     * @return
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case TABLE_1_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.table1";
            case TABLE_1_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.table1";
            case TABLE_2_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.table2";
            case TABLE_2_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.table2";
            default:
                break;
        }
        return null;
    }

    /**
     * 向内容提供器中查询数据。添加完成后，返回一个表示这条新纪录的URI
     * @param uri 指定要添加的表
     * @param values 待添加的数据保存在values中
     * @return
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    /**
     * 从内容提供器中删除数据。返回被删除的行数
     * @param uri 指定删除哪张表中的数据
     * @param selection 约束删除哪些行
     * @param selectionArgs 约束删除哪些行
     * @return
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    /**
     * 更新内容提供器中已有的数据。返回受影响的行数
     * @param uri 指定更新哪张表中的数据
     * @param values 新数据保存在values中
     * @param selection 约束更新哪些行
     * @param selectionArgs 约束更新哪些行
     * @return
     */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
