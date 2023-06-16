package nodomain.freeyourgadget.gadgetbridge.entities;
import android.os.Build;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;

import nodomain.freeyourgadget.gadgetbridge.entities.BaseActivitySummary;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BASE_ACTIVITY_SUMMARY".
*/
public class BaseActivitySummaryDao extends AbstractDao<BaseActivitySummary, Long> {

    public static final String TABLENAME = "BASE_ACTIVITY_SUMMARY";

    /**
     * Properties of entity BaseActivitySummary.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property StartTime = new Property(2, java.util.Date.class, "startTime", false, "START_TIME");
        public final static Property EndTime = new Property(3, java.util.Date.class, "endTime", false, "END_TIME");
        public final static Property ActivityKind = new Property(4, int.class, "activityKind", false, "ACTIVITY_KIND");
        public final static Property BaseLongitude = new Property(5, Integer.class, "baseLongitude", false, "BASE_LONGITUDE");
        public final static Property BaseLatitude = new Property(6, Integer.class, "baseLatitude", false, "BASE_LATITUDE");
        public final static Property BaseAltitude = new Property(7, Integer.class, "baseAltitude", false, "BASE_ALTITUDE");
        public final static Property GpxTrack = new Property(8, String.class, "gpxTrack", false, "GPX_TRACK");
        public final static Property RawDetailsPath = new Property(9, String.class, "rawDetailsPath", false, "RAW_DETAILS_PATH");
        public final static Property DeviceId = new Property(10, long.class, "deviceId", false, "DEVICE_ID");
        public final static Property UserId = new Property(11, long.class, "userId", false, "USER_ID");
        public final static Property SummaryData = new Property(12, String.class, "summaryData", false, "SUMMARY_DATA");
        public final static Property RawSummaryData = new Property(13, byte[].class, "rawSummaryData", false, "RAW_SUMMARY_DATA");
    };

    private DaoSession daoSession;


    public BaseActivitySummaryDao(DaoConfig config) {
        super(config);
    }
    
    public BaseActivitySummaryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BASE_ACTIVITY_SUMMARY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"START_TIME\" INTEGER NOT NULL ," + // 2: startTime
                "\"END_TIME\" INTEGER NOT NULL ," + // 3: endTime
                "\"ACTIVITY_KIND\" INTEGER NOT NULL ," + // 4: activityKind
                "\"BASE_LONGITUDE\" INTEGER," + // 5: baseLongitude
                "\"BASE_LATITUDE\" INTEGER," + // 6: baseLatitude
                "\"BASE_ALTITUDE\" INTEGER," + // 7: baseAltitude
                "\"GPX_TRACK\" TEXT," + // 8: gpxTrack
                "\"RAW_DETAILS_PATH\" TEXT," + // 9: rawDetailsPath
                "\"DEVICE_ID\" INTEGER NOT NULL ," + // 10: deviceId
                "\"USER_ID\" INTEGER NOT NULL ," + // 11: userId
                "\"SUMMARY_DATA\" TEXT," + // 12: summaryData
                "\"RAW_SUMMARY_DATA\" BLOB);"); // 13: rawSummaryData
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BASE_ACTIVITY_SUMMARY\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, BaseActivitySummary entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
        stmt.bindLong(3, entity.getStartTime().getTime());
        stmt.bindLong(4, entity.getEndTime().getTime());
        stmt.bindLong(5, entity.getActivityKind());
 
        Integer baseLongitude = entity.getBaseLongitude();
        if (baseLongitude != null) {
            stmt.bindLong(6, baseLongitude);
        }
 
        Integer baseLatitude = entity.getBaseLatitude();
        if (baseLatitude != null) {
            stmt.bindLong(7, baseLatitude);
        }
 
        Integer baseAltitude = entity.getBaseAltitude();
        if (baseAltitude != null) {
            stmt.bindLong(8, baseAltitude);
        }
 
        String gpxTrack = entity.getGpxTrack();
        if (gpxTrack != null) {
            stmt.bindString(9, gpxTrack);
        }
 
        String rawDetailsPath = entity.getRawDetailsPath();
        if (rawDetailsPath != null) {
            stmt.bindString(10, rawDetailsPath);
        }
        stmt.bindLong(11, entity.getDeviceId());
        stmt.bindLong(12, entity.getUserId());
 
        String summaryData = entity.getSummaryData();
        if (summaryData != null) {
            stmt.bindString(13, summaryData);
        }
 
        byte[] rawSummaryData = entity.getRawSummaryData();
        if (rawSummaryData != null) {
            stmt.bindBlob(14, rawSummaryData);
        }
    }

    @Override
    protected void attachEntity(BaseActivitySummary entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public BaseActivitySummary readEntity(Cursor cursor, int offset) {
        BaseActivitySummary entity = new BaseActivitySummary( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            new java.util.Date(cursor.getLong(offset + 2)), // startTime
            new java.util.Date(cursor.getLong(offset + 3)), // endTime
            cursor.getInt(offset + 4), // activityKind
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // baseLongitude
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // baseLatitude
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // baseAltitude
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // gpxTrack
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // rawDetailsPath
            cursor.getLong(offset + 10), // deviceId
            cursor.getLong(offset + 11), // userId
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // summaryData
            cursor.isNull(offset + 13) ? null : cursor.getBlob(offset + 13) // rawSummaryData
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, BaseActivitySummary entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setStartTime(new java.util.Date(cursor.getLong(offset + 2)));
        entity.setEndTime(new java.util.Date(cursor.getLong(offset + 3)));
        entity.setActivityKind(cursor.getInt(offset + 4));
        entity.setBaseLongitude(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setBaseLatitude(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setBaseAltitude(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setGpxTrack(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setRawDetailsPath(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setDeviceId(cursor.getLong(offset + 10));
        entity.setUserId(cursor.getLong(offset + 11));
        entity.setSummaryData(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setRawSummaryData(cursor.isNull(offset + 13) ? null : cursor.getBlob(offset + 13));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(BaseActivitySummary entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(BaseActivitySummary entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getDeviceDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getUserDao().getAllColumns());
            builder.append(" FROM BASE_ACTIVITY_SUMMARY T");
            builder.append(" LEFT JOIN DEVICE T0 ON T.\"DEVICE_ID\"=T0.\"_id\"");
            builder.append(" LEFT JOIN USER T1 ON T.\"USER_ID\"=T1.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected BaseActivitySummary loadCurrentDeep(Cursor cursor, boolean lock) {
        BaseActivitySummary entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Device device = loadCurrentOther(daoSession.getDeviceDao(), cursor, offset);
         if(device != null) {
            entity.setDevice(device);
        }
        offset += daoSession.getDeviceDao().getAllColumns().length;

        User user = loadCurrentOther(daoSession.getUserDao(), cursor, offset);
         if(user != null) {
            entity.setUser(user);
        }

        return entity;    
    }

    public BaseActivitySummary loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<BaseActivitySummary> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<BaseActivitySummary> list = new ArrayList<BaseActivitySummary>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<BaseActivitySummary> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<BaseActivitySummary> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
