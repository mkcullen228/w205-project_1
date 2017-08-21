// ORM class for table 'weblog'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Sun Aug 20 21:06:01 UTC 2017
// For connector: org.apache.sqoop.manager.PostgresqlManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class weblog extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("utc_datetime", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weblog.this.utc_datetime = (java.sql.Timestamp)value;
      }
    });
    setters.put("mt_datetime", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weblog.this.mt_datetime = (java.sql.Timestamp)value;
      }
    });
    setters.put("utc_time_ms", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weblog.this.utc_time_ms = (Integer)value;
      }
    });
    setters.put("s_ip", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weblog.this.s_ip = (String)value;
      }
    });
    setters.put("cs_method", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weblog.this.cs_method = (String)value;
      }
    });
    setters.put("s_sitename", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weblog.this.s_sitename = (String)value;
      }
    });
    setters.put("cs_uri_stem", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weblog.this.cs_uri_stem = (String)value;
      }
    });
    setters.put("s_computername", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weblog.this.s_computername = (String)value;
      }
    });
    setters.put("cs_uri_query", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weblog.this.cs_uri_query = (String)value;
      }
    });
    setters.put("cs_user_agent", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weblog.this.cs_user_agent = (String)value;
      }
    });
    setters.put("s_port", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weblog.this.s_port = (String)value;
      }
    });
    setters.put("cs_referer", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weblog.this.cs_referer = (String)value;
      }
    });
    setters.put("cs_host", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weblog.this.cs_host = (String)value;
      }
    });
    setters.put("sc_status", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weblog.this.sc_status = (String)value;
      }
    });
    setters.put("sc_win32_status", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weblog.this.sc_win32_status = (String)value;
      }
    });
    setters.put("sc_bytes", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weblog.this.sc_bytes = (Long)value;
      }
    });
    setters.put("cs_bytes", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weblog.this.cs_bytes = (Long)value;
      }
    });
    setters.put("time_taken_ms", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weblog.this.time_taken_ms = (Long)value;
      }
    });
    setters.put("visitor_id_hash", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weblog.this.visitor_id_hash = (String)value;
      }
    });
  }
  public weblog() {
    init0();
  }
  private java.sql.Timestamp utc_datetime;
  public java.sql.Timestamp get_utc_datetime() {
    return utc_datetime;
  }
  public void set_utc_datetime(java.sql.Timestamp utc_datetime) {
    this.utc_datetime = utc_datetime;
  }
  public weblog with_utc_datetime(java.sql.Timestamp utc_datetime) {
    this.utc_datetime = utc_datetime;
    return this;
  }
  private java.sql.Timestamp mt_datetime;
  public java.sql.Timestamp get_mt_datetime() {
    return mt_datetime;
  }
  public void set_mt_datetime(java.sql.Timestamp mt_datetime) {
    this.mt_datetime = mt_datetime;
  }
  public weblog with_mt_datetime(java.sql.Timestamp mt_datetime) {
    this.mt_datetime = mt_datetime;
    return this;
  }
  private Integer utc_time_ms;
  public Integer get_utc_time_ms() {
    return utc_time_ms;
  }
  public void set_utc_time_ms(Integer utc_time_ms) {
    this.utc_time_ms = utc_time_ms;
  }
  public weblog with_utc_time_ms(Integer utc_time_ms) {
    this.utc_time_ms = utc_time_ms;
    return this;
  }
  private String s_ip;
  public String get_s_ip() {
    return s_ip;
  }
  public void set_s_ip(String s_ip) {
    this.s_ip = s_ip;
  }
  public weblog with_s_ip(String s_ip) {
    this.s_ip = s_ip;
    return this;
  }
  private String cs_method;
  public String get_cs_method() {
    return cs_method;
  }
  public void set_cs_method(String cs_method) {
    this.cs_method = cs_method;
  }
  public weblog with_cs_method(String cs_method) {
    this.cs_method = cs_method;
    return this;
  }
  private String s_sitename;
  public String get_s_sitename() {
    return s_sitename;
  }
  public void set_s_sitename(String s_sitename) {
    this.s_sitename = s_sitename;
  }
  public weblog with_s_sitename(String s_sitename) {
    this.s_sitename = s_sitename;
    return this;
  }
  private String cs_uri_stem;
  public String get_cs_uri_stem() {
    return cs_uri_stem;
  }
  public void set_cs_uri_stem(String cs_uri_stem) {
    this.cs_uri_stem = cs_uri_stem;
  }
  public weblog with_cs_uri_stem(String cs_uri_stem) {
    this.cs_uri_stem = cs_uri_stem;
    return this;
  }
  private String s_computername;
  public String get_s_computername() {
    return s_computername;
  }
  public void set_s_computername(String s_computername) {
    this.s_computername = s_computername;
  }
  public weblog with_s_computername(String s_computername) {
    this.s_computername = s_computername;
    return this;
  }
  private String cs_uri_query;
  public String get_cs_uri_query() {
    return cs_uri_query;
  }
  public void set_cs_uri_query(String cs_uri_query) {
    this.cs_uri_query = cs_uri_query;
  }
  public weblog with_cs_uri_query(String cs_uri_query) {
    this.cs_uri_query = cs_uri_query;
    return this;
  }
  private String cs_user_agent;
  public String get_cs_user_agent() {
    return cs_user_agent;
  }
  public void set_cs_user_agent(String cs_user_agent) {
    this.cs_user_agent = cs_user_agent;
  }
  public weblog with_cs_user_agent(String cs_user_agent) {
    this.cs_user_agent = cs_user_agent;
    return this;
  }
  private String s_port;
  public String get_s_port() {
    return s_port;
  }
  public void set_s_port(String s_port) {
    this.s_port = s_port;
  }
  public weblog with_s_port(String s_port) {
    this.s_port = s_port;
    return this;
  }
  private String cs_referer;
  public String get_cs_referer() {
    return cs_referer;
  }
  public void set_cs_referer(String cs_referer) {
    this.cs_referer = cs_referer;
  }
  public weblog with_cs_referer(String cs_referer) {
    this.cs_referer = cs_referer;
    return this;
  }
  private String cs_host;
  public String get_cs_host() {
    return cs_host;
  }
  public void set_cs_host(String cs_host) {
    this.cs_host = cs_host;
  }
  public weblog with_cs_host(String cs_host) {
    this.cs_host = cs_host;
    return this;
  }
  private String sc_status;
  public String get_sc_status() {
    return sc_status;
  }
  public void set_sc_status(String sc_status) {
    this.sc_status = sc_status;
  }
  public weblog with_sc_status(String sc_status) {
    this.sc_status = sc_status;
    return this;
  }
  private String sc_win32_status;
  public String get_sc_win32_status() {
    return sc_win32_status;
  }
  public void set_sc_win32_status(String sc_win32_status) {
    this.sc_win32_status = sc_win32_status;
  }
  public weblog with_sc_win32_status(String sc_win32_status) {
    this.sc_win32_status = sc_win32_status;
    return this;
  }
  private Long sc_bytes;
  public Long get_sc_bytes() {
    return sc_bytes;
  }
  public void set_sc_bytes(Long sc_bytes) {
    this.sc_bytes = sc_bytes;
  }
  public weblog with_sc_bytes(Long sc_bytes) {
    this.sc_bytes = sc_bytes;
    return this;
  }
  private Long cs_bytes;
  public Long get_cs_bytes() {
    return cs_bytes;
  }
  public void set_cs_bytes(Long cs_bytes) {
    this.cs_bytes = cs_bytes;
  }
  public weblog with_cs_bytes(Long cs_bytes) {
    this.cs_bytes = cs_bytes;
    return this;
  }
  private Long time_taken_ms;
  public Long get_time_taken_ms() {
    return time_taken_ms;
  }
  public void set_time_taken_ms(Long time_taken_ms) {
    this.time_taken_ms = time_taken_ms;
  }
  public weblog with_time_taken_ms(Long time_taken_ms) {
    this.time_taken_ms = time_taken_ms;
    return this;
  }
  private String visitor_id_hash;
  public String get_visitor_id_hash() {
    return visitor_id_hash;
  }
  public void set_visitor_id_hash(String visitor_id_hash) {
    this.visitor_id_hash = visitor_id_hash;
  }
  public weblog with_visitor_id_hash(String visitor_id_hash) {
    this.visitor_id_hash = visitor_id_hash;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof weblog)) {
      return false;
    }
    weblog that = (weblog) o;
    boolean equal = true;
    equal = equal && (this.utc_datetime == null ? that.utc_datetime == null : this.utc_datetime.equals(that.utc_datetime));
    equal = equal && (this.mt_datetime == null ? that.mt_datetime == null : this.mt_datetime.equals(that.mt_datetime));
    equal = equal && (this.utc_time_ms == null ? that.utc_time_ms == null : this.utc_time_ms.equals(that.utc_time_ms));
    equal = equal && (this.s_ip == null ? that.s_ip == null : this.s_ip.equals(that.s_ip));
    equal = equal && (this.cs_method == null ? that.cs_method == null : this.cs_method.equals(that.cs_method));
    equal = equal && (this.s_sitename == null ? that.s_sitename == null : this.s_sitename.equals(that.s_sitename));
    equal = equal && (this.cs_uri_stem == null ? that.cs_uri_stem == null : this.cs_uri_stem.equals(that.cs_uri_stem));
    equal = equal && (this.s_computername == null ? that.s_computername == null : this.s_computername.equals(that.s_computername));
    equal = equal && (this.cs_uri_query == null ? that.cs_uri_query == null : this.cs_uri_query.equals(that.cs_uri_query));
    equal = equal && (this.cs_user_agent == null ? that.cs_user_agent == null : this.cs_user_agent.equals(that.cs_user_agent));
    equal = equal && (this.s_port == null ? that.s_port == null : this.s_port.equals(that.s_port));
    equal = equal && (this.cs_referer == null ? that.cs_referer == null : this.cs_referer.equals(that.cs_referer));
    equal = equal && (this.cs_host == null ? that.cs_host == null : this.cs_host.equals(that.cs_host));
    equal = equal && (this.sc_status == null ? that.sc_status == null : this.sc_status.equals(that.sc_status));
    equal = equal && (this.sc_win32_status == null ? that.sc_win32_status == null : this.sc_win32_status.equals(that.sc_win32_status));
    equal = equal && (this.sc_bytes == null ? that.sc_bytes == null : this.sc_bytes.equals(that.sc_bytes));
    equal = equal && (this.cs_bytes == null ? that.cs_bytes == null : this.cs_bytes.equals(that.cs_bytes));
    equal = equal && (this.time_taken_ms == null ? that.time_taken_ms == null : this.time_taken_ms.equals(that.time_taken_ms));
    equal = equal && (this.visitor_id_hash == null ? that.visitor_id_hash == null : this.visitor_id_hash.equals(that.visitor_id_hash));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof weblog)) {
      return false;
    }
    weblog that = (weblog) o;
    boolean equal = true;
    equal = equal && (this.utc_datetime == null ? that.utc_datetime == null : this.utc_datetime.equals(that.utc_datetime));
    equal = equal && (this.mt_datetime == null ? that.mt_datetime == null : this.mt_datetime.equals(that.mt_datetime));
    equal = equal && (this.utc_time_ms == null ? that.utc_time_ms == null : this.utc_time_ms.equals(that.utc_time_ms));
    equal = equal && (this.s_ip == null ? that.s_ip == null : this.s_ip.equals(that.s_ip));
    equal = equal && (this.cs_method == null ? that.cs_method == null : this.cs_method.equals(that.cs_method));
    equal = equal && (this.s_sitename == null ? that.s_sitename == null : this.s_sitename.equals(that.s_sitename));
    equal = equal && (this.cs_uri_stem == null ? that.cs_uri_stem == null : this.cs_uri_stem.equals(that.cs_uri_stem));
    equal = equal && (this.s_computername == null ? that.s_computername == null : this.s_computername.equals(that.s_computername));
    equal = equal && (this.cs_uri_query == null ? that.cs_uri_query == null : this.cs_uri_query.equals(that.cs_uri_query));
    equal = equal && (this.cs_user_agent == null ? that.cs_user_agent == null : this.cs_user_agent.equals(that.cs_user_agent));
    equal = equal && (this.s_port == null ? that.s_port == null : this.s_port.equals(that.s_port));
    equal = equal && (this.cs_referer == null ? that.cs_referer == null : this.cs_referer.equals(that.cs_referer));
    equal = equal && (this.cs_host == null ? that.cs_host == null : this.cs_host.equals(that.cs_host));
    equal = equal && (this.sc_status == null ? that.sc_status == null : this.sc_status.equals(that.sc_status));
    equal = equal && (this.sc_win32_status == null ? that.sc_win32_status == null : this.sc_win32_status.equals(that.sc_win32_status));
    equal = equal && (this.sc_bytes == null ? that.sc_bytes == null : this.sc_bytes.equals(that.sc_bytes));
    equal = equal && (this.cs_bytes == null ? that.cs_bytes == null : this.cs_bytes.equals(that.cs_bytes));
    equal = equal && (this.time_taken_ms == null ? that.time_taken_ms == null : this.time_taken_ms.equals(that.time_taken_ms));
    equal = equal && (this.visitor_id_hash == null ? that.visitor_id_hash == null : this.visitor_id_hash.equals(that.visitor_id_hash));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.utc_datetime = JdbcWritableBridge.readTimestamp(1, __dbResults);
    this.mt_datetime = JdbcWritableBridge.readTimestamp(2, __dbResults);
    this.utc_time_ms = JdbcWritableBridge.readInteger(3, __dbResults);
    this.s_ip = JdbcWritableBridge.readString(4, __dbResults);
    this.cs_method = JdbcWritableBridge.readString(5, __dbResults);
    this.s_sitename = JdbcWritableBridge.readString(6, __dbResults);
    this.cs_uri_stem = JdbcWritableBridge.readString(7, __dbResults);
    this.s_computername = JdbcWritableBridge.readString(8, __dbResults);
    this.cs_uri_query = JdbcWritableBridge.readString(9, __dbResults);
    this.cs_user_agent = JdbcWritableBridge.readString(10, __dbResults);
    this.s_port = JdbcWritableBridge.readString(11, __dbResults);
    this.cs_referer = JdbcWritableBridge.readString(12, __dbResults);
    this.cs_host = JdbcWritableBridge.readString(13, __dbResults);
    this.sc_status = JdbcWritableBridge.readString(14, __dbResults);
    this.sc_win32_status = JdbcWritableBridge.readString(15, __dbResults);
    this.sc_bytes = JdbcWritableBridge.readLong(16, __dbResults);
    this.cs_bytes = JdbcWritableBridge.readLong(17, __dbResults);
    this.time_taken_ms = JdbcWritableBridge.readLong(18, __dbResults);
    this.visitor_id_hash = JdbcWritableBridge.readString(19, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.utc_datetime = JdbcWritableBridge.readTimestamp(1, __dbResults);
    this.mt_datetime = JdbcWritableBridge.readTimestamp(2, __dbResults);
    this.utc_time_ms = JdbcWritableBridge.readInteger(3, __dbResults);
    this.s_ip = JdbcWritableBridge.readString(4, __dbResults);
    this.cs_method = JdbcWritableBridge.readString(5, __dbResults);
    this.s_sitename = JdbcWritableBridge.readString(6, __dbResults);
    this.cs_uri_stem = JdbcWritableBridge.readString(7, __dbResults);
    this.s_computername = JdbcWritableBridge.readString(8, __dbResults);
    this.cs_uri_query = JdbcWritableBridge.readString(9, __dbResults);
    this.cs_user_agent = JdbcWritableBridge.readString(10, __dbResults);
    this.s_port = JdbcWritableBridge.readString(11, __dbResults);
    this.cs_referer = JdbcWritableBridge.readString(12, __dbResults);
    this.cs_host = JdbcWritableBridge.readString(13, __dbResults);
    this.sc_status = JdbcWritableBridge.readString(14, __dbResults);
    this.sc_win32_status = JdbcWritableBridge.readString(15, __dbResults);
    this.sc_bytes = JdbcWritableBridge.readLong(16, __dbResults);
    this.cs_bytes = JdbcWritableBridge.readLong(17, __dbResults);
    this.time_taken_ms = JdbcWritableBridge.readLong(18, __dbResults);
    this.visitor_id_hash = JdbcWritableBridge.readString(19, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeTimestamp(utc_datetime, 1 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(mt_datetime, 2 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeInteger(utc_time_ms, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(s_ip, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(cs_method, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(s_sitename, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(cs_uri_stem, 7 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(s_computername, 8 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(cs_uri_query, 9 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(cs_user_agent, 10 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(s_port, 11 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(cs_referer, 12 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(cs_host, 13 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(sc_status, 14 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(sc_win32_status, 15 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(sc_bytes, 16 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(cs_bytes, 17 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(time_taken_ms, 18 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(visitor_id_hash, 19 + __off, 12, __dbStmt);
    return 19;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeTimestamp(utc_datetime, 1 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(mt_datetime, 2 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeInteger(utc_time_ms, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(s_ip, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(cs_method, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(s_sitename, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(cs_uri_stem, 7 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(s_computername, 8 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(cs_uri_query, 9 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(cs_user_agent, 10 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(s_port, 11 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(cs_referer, 12 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(cs_host, 13 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(sc_status, 14 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(sc_win32_status, 15 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(sc_bytes, 16 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(cs_bytes, 17 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(time_taken_ms, 18 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(visitor_id_hash, 19 + __off, 12, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.utc_datetime = null;
    } else {
    this.utc_datetime = new Timestamp(__dataIn.readLong());
    this.utc_datetime.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.mt_datetime = null;
    } else {
    this.mt_datetime = new Timestamp(__dataIn.readLong());
    this.mt_datetime.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.utc_time_ms = null;
    } else {
    this.utc_time_ms = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.s_ip = null;
    } else {
    this.s_ip = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.cs_method = null;
    } else {
    this.cs_method = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.s_sitename = null;
    } else {
    this.s_sitename = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.cs_uri_stem = null;
    } else {
    this.cs_uri_stem = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.s_computername = null;
    } else {
    this.s_computername = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.cs_uri_query = null;
    } else {
    this.cs_uri_query = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.cs_user_agent = null;
    } else {
    this.cs_user_agent = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.s_port = null;
    } else {
    this.s_port = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.cs_referer = null;
    } else {
    this.cs_referer = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.cs_host = null;
    } else {
    this.cs_host = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.sc_status = null;
    } else {
    this.sc_status = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.sc_win32_status = null;
    } else {
    this.sc_win32_status = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.sc_bytes = null;
    } else {
    this.sc_bytes = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.cs_bytes = null;
    } else {
    this.cs_bytes = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.time_taken_ms = null;
    } else {
    this.time_taken_ms = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.visitor_id_hash = null;
    } else {
    this.visitor_id_hash = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.utc_datetime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.utc_datetime.getTime());
    __dataOut.writeInt(this.utc_datetime.getNanos());
    }
    if (null == this.mt_datetime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.mt_datetime.getTime());
    __dataOut.writeInt(this.mt_datetime.getNanos());
    }
    if (null == this.utc_time_ms) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.utc_time_ms);
    }
    if (null == this.s_ip) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, s_ip);
    }
    if (null == this.cs_method) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, cs_method);
    }
    if (null == this.s_sitename) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, s_sitename);
    }
    if (null == this.cs_uri_stem) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, cs_uri_stem);
    }
    if (null == this.s_computername) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, s_computername);
    }
    if (null == this.cs_uri_query) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, cs_uri_query);
    }
    if (null == this.cs_user_agent) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, cs_user_agent);
    }
    if (null == this.s_port) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, s_port);
    }
    if (null == this.cs_referer) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, cs_referer);
    }
    if (null == this.cs_host) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, cs_host);
    }
    if (null == this.sc_status) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, sc_status);
    }
    if (null == this.sc_win32_status) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, sc_win32_status);
    }
    if (null == this.sc_bytes) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.sc_bytes);
    }
    if (null == this.cs_bytes) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.cs_bytes);
    }
    if (null == this.time_taken_ms) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.time_taken_ms);
    }
    if (null == this.visitor_id_hash) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, visitor_id_hash);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.utc_datetime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.utc_datetime.getTime());
    __dataOut.writeInt(this.utc_datetime.getNanos());
    }
    if (null == this.mt_datetime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.mt_datetime.getTime());
    __dataOut.writeInt(this.mt_datetime.getNanos());
    }
    if (null == this.utc_time_ms) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.utc_time_ms);
    }
    if (null == this.s_ip) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, s_ip);
    }
    if (null == this.cs_method) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, cs_method);
    }
    if (null == this.s_sitename) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, s_sitename);
    }
    if (null == this.cs_uri_stem) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, cs_uri_stem);
    }
    if (null == this.s_computername) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, s_computername);
    }
    if (null == this.cs_uri_query) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, cs_uri_query);
    }
    if (null == this.cs_user_agent) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, cs_user_agent);
    }
    if (null == this.s_port) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, s_port);
    }
    if (null == this.cs_referer) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, cs_referer);
    }
    if (null == this.cs_host) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, cs_host);
    }
    if (null == this.sc_status) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, sc_status);
    }
    if (null == this.sc_win32_status) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, sc_win32_status);
    }
    if (null == this.sc_bytes) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.sc_bytes);
    }
    if (null == this.cs_bytes) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.cs_bytes);
    }
    if (null == this.time_taken_ms) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.time_taken_ms);
    }
    if (null == this.visitor_id_hash) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, visitor_id_hash);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(utc_datetime==null?"null":"" + utc_datetime, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(mt_datetime==null?"null":"" + mt_datetime, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(utc_time_ms==null?"null":"" + utc_time_ms, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(s_ip==null?"null":s_ip, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cs_method==null?"null":cs_method, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(s_sitename==null?"null":s_sitename, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cs_uri_stem==null?"null":cs_uri_stem, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(s_computername==null?"null":s_computername, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cs_uri_query==null?"null":cs_uri_query, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cs_user_agent==null?"null":cs_user_agent, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(s_port==null?"null":s_port, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cs_referer==null?"null":cs_referer, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cs_host==null?"null":cs_host, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(sc_status==null?"null":sc_status, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(sc_win32_status==null?"null":sc_win32_status, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(sc_bytes==null?"null":"" + sc_bytes, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cs_bytes==null?"null":"" + cs_bytes, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(time_taken_ms==null?"null":"" + time_taken_ms, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(visitor_id_hash==null?"null":visitor_id_hash, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(utc_datetime==null?"null":"" + utc_datetime, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(mt_datetime==null?"null":"" + mt_datetime, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(utc_time_ms==null?"null":"" + utc_time_ms, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(s_ip==null?"null":s_ip, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cs_method==null?"null":cs_method, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(s_sitename==null?"null":s_sitename, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cs_uri_stem==null?"null":cs_uri_stem, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(s_computername==null?"null":s_computername, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cs_uri_query==null?"null":cs_uri_query, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cs_user_agent==null?"null":cs_user_agent, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(s_port==null?"null":s_port, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cs_referer==null?"null":cs_referer, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cs_host==null?"null":cs_host, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(sc_status==null?"null":sc_status, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(sc_win32_status==null?"null":sc_win32_status, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(sc_bytes==null?"null":"" + sc_bytes, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cs_bytes==null?"null":"" + cs_bytes, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(time_taken_ms==null?"null":"" + time_taken_ms, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(visitor_id_hash==null?"null":visitor_id_hash, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 9, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.utc_datetime = null; } else {
      this.utc_datetime = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.mt_datetime = null; } else {
      this.mt_datetime = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.utc_time_ms = null; } else {
      this.utc_time_ms = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.s_ip = null; } else {
      this.s_ip = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.cs_method = null; } else {
      this.cs_method = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.s_sitename = null; } else {
      this.s_sitename = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.cs_uri_stem = null; } else {
      this.cs_uri_stem = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.s_computername = null; } else {
      this.s_computername = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.cs_uri_query = null; } else {
      this.cs_uri_query = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.cs_user_agent = null; } else {
      this.cs_user_agent = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.s_port = null; } else {
      this.s_port = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.cs_referer = null; } else {
      this.cs_referer = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.cs_host = null; } else {
      this.cs_host = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.sc_status = null; } else {
      this.sc_status = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.sc_win32_status = null; } else {
      this.sc_win32_status = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.sc_bytes = null; } else {
      this.sc_bytes = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.cs_bytes = null; } else {
      this.cs_bytes = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.time_taken_ms = null; } else {
      this.time_taken_ms = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.visitor_id_hash = null; } else {
      this.visitor_id_hash = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.utc_datetime = null; } else {
      this.utc_datetime = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.mt_datetime = null; } else {
      this.mt_datetime = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.utc_time_ms = null; } else {
      this.utc_time_ms = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.s_ip = null; } else {
      this.s_ip = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.cs_method = null; } else {
      this.cs_method = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.s_sitename = null; } else {
      this.s_sitename = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.cs_uri_stem = null; } else {
      this.cs_uri_stem = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.s_computername = null; } else {
      this.s_computername = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.cs_uri_query = null; } else {
      this.cs_uri_query = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.cs_user_agent = null; } else {
      this.cs_user_agent = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.s_port = null; } else {
      this.s_port = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.cs_referer = null; } else {
      this.cs_referer = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.cs_host = null; } else {
      this.cs_host = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.sc_status = null; } else {
      this.sc_status = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.sc_win32_status = null; } else {
      this.sc_win32_status = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.sc_bytes = null; } else {
      this.sc_bytes = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.cs_bytes = null; } else {
      this.cs_bytes = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.time_taken_ms = null; } else {
      this.time_taken_ms = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.visitor_id_hash = null; } else {
      this.visitor_id_hash = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    weblog o = (weblog) super.clone();
    o.utc_datetime = (o.utc_datetime != null) ? (java.sql.Timestamp) o.utc_datetime.clone() : null;
    o.mt_datetime = (o.mt_datetime != null) ? (java.sql.Timestamp) o.mt_datetime.clone() : null;
    return o;
  }

  public void clone0(weblog o) throws CloneNotSupportedException {
    o.utc_datetime = (o.utc_datetime != null) ? (java.sql.Timestamp) o.utc_datetime.clone() : null;
    o.mt_datetime = (o.mt_datetime != null) ? (java.sql.Timestamp) o.mt_datetime.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("utc_datetime", this.utc_datetime);
    __sqoop$field_map.put("mt_datetime", this.mt_datetime);
    __sqoop$field_map.put("utc_time_ms", this.utc_time_ms);
    __sqoop$field_map.put("s_ip", this.s_ip);
    __sqoop$field_map.put("cs_method", this.cs_method);
    __sqoop$field_map.put("s_sitename", this.s_sitename);
    __sqoop$field_map.put("cs_uri_stem", this.cs_uri_stem);
    __sqoop$field_map.put("s_computername", this.s_computername);
    __sqoop$field_map.put("cs_uri_query", this.cs_uri_query);
    __sqoop$field_map.put("cs_user_agent", this.cs_user_agent);
    __sqoop$field_map.put("s_port", this.s_port);
    __sqoop$field_map.put("cs_referer", this.cs_referer);
    __sqoop$field_map.put("cs_host", this.cs_host);
    __sqoop$field_map.put("sc_status", this.sc_status);
    __sqoop$field_map.put("sc_win32_status", this.sc_win32_status);
    __sqoop$field_map.put("sc_bytes", this.sc_bytes);
    __sqoop$field_map.put("cs_bytes", this.cs_bytes);
    __sqoop$field_map.put("time_taken_ms", this.time_taken_ms);
    __sqoop$field_map.put("visitor_id_hash", this.visitor_id_hash);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("utc_datetime", this.utc_datetime);
    __sqoop$field_map.put("mt_datetime", this.mt_datetime);
    __sqoop$field_map.put("utc_time_ms", this.utc_time_ms);
    __sqoop$field_map.put("s_ip", this.s_ip);
    __sqoop$field_map.put("cs_method", this.cs_method);
    __sqoop$field_map.put("s_sitename", this.s_sitename);
    __sqoop$field_map.put("cs_uri_stem", this.cs_uri_stem);
    __sqoop$field_map.put("s_computername", this.s_computername);
    __sqoop$field_map.put("cs_uri_query", this.cs_uri_query);
    __sqoop$field_map.put("cs_user_agent", this.cs_user_agent);
    __sqoop$field_map.put("s_port", this.s_port);
    __sqoop$field_map.put("cs_referer", this.cs_referer);
    __sqoop$field_map.put("cs_host", this.cs_host);
    __sqoop$field_map.put("sc_status", this.sc_status);
    __sqoop$field_map.put("sc_win32_status", this.sc_win32_status);
    __sqoop$field_map.put("sc_bytes", this.sc_bytes);
    __sqoop$field_map.put("cs_bytes", this.cs_bytes);
    __sqoop$field_map.put("time_taken_ms", this.time_taken_ms);
    __sqoop$field_map.put("visitor_id_hash", this.visitor_id_hash);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
